package org.example;

import org.example.model.Polaznik;
import org.example.model.ProgramObrazovanja;
import org.example.model.Upis;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Primjeri funkcija
        unesiNovogPolaznika("Iva", "Ivic");
        unesiNoviProgram("Java Developer", 60);
        upisiPolaznikaNaProgram(1, 1);
        prebaciPolaznikaNaDrugiProgram(1, 2);
        ispisiPolaznikeZaProgram(1);

        HibernateUtil.shutdown();
    }

    public static void unesiNovogPolaznika(String ime, String prezime) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            Polaznik p = new Polaznik(ime, prezime);
            session.persist(p);
            t.commit();
        }
    }

    public static void unesiNoviProgram(String naziv, int csvet) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            ProgramObrazovanja po = new ProgramObrazovanja(naziv, csvet);
            session.persist(po);
            t.commit();
        }
    }

    public static void upisiPolaznikaNaProgram(int polaznikId, int programId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            Polaznik p = session.get(Polaznik.class, polaznikId);
            ProgramObrazovanja po = session.get(ProgramObrazovanja.class, programId);
            Upis u = new Upis(p, po);
            session.persist(u);
            t.commit();
        }
    }

    public static void prebaciPolaznikaNaDrugiProgram(int polaznikId, int noviProgramId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();

            String hql = "FROM Upis WHERE polaznik.IDPolaznik = :pid";
            Upis upis = session.createQuery(hql, Upis.class)
                    .setParameter("pid", polaznikId)
                    .setMaxResults(1)
                    .uniqueResult();

            if (upis != null) {
                ProgramObrazovanja novi = session.get(ProgramObrazovanja.class, noviProgramId);
                upis.setProgramObrazovanja(novi);
                session.merge(upis);
            }

            t.commit();
        }
    }

    public static void ispisiPolaznikeZaProgram(int programId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = """
                SELECT u FROM Upis u
                WHERE u.programObrazovanja.IDProgramObrazovanja = :pid
                """;

            List<Upis> upisi = session.createQuery(hql, Upis.class)
                    .setParameter("pid", programId)
                    .list();

            for (Upis u : upisi) {
                Polaznik p = u.getPolaznik();
                ProgramObrazovanja po = u.getProgramObrazovanja();
                System.out.printf("Polaznik: %s %s, Program: %s, CSVET: %d%n",
                        p.getIme(), p.getPrezime(), po.getNaziv(), po.getCSVET());
            }
        }
    }
}

