package hr.java.web.helloworld.controller;

import hr.java.web.helloworld.domain.Polaznik; // import entiteta Polaznik
import hr.java.web.helloworld.dto.PolaznikDTO; // import DTO klase za Polaznik
import hr.java.web.helloworld.service.PolaznikService; // import servisa PolaznikService
import org.junit.jupiter.api.BeforeEach; // import JUnit metode za setup prije svakog testa
import org.junit.jupiter.api.DisplayName; // import za postavljanje imena testa
import org.junit.jupiter.api.Test; // import oznake @Test
import org.mockito.Mockito; // import Mockito utility klase

import org.springframework.http.ResponseEntity; // import klase za HTTP odgovor

import java.util.Arrays; // import Arrays utility klase
import java.util.List; // import List interface-a
import java.util.Optional; // import Optional klase

import static org.junit.jupiter.api.Assertions.*; // import svih static metoda za asertacije
import static org.mockito.Mockito.*; // import svih static metoda iz Mockito za mock i verify

public class PolaznikControllerTest { // početak test klase

    private PolaznikService polaznikService; // deklaracija mock servisa
    private PolaznikController polaznikController; // deklaracija kontrolera koji testiramo

    @BeforeEach // metoda koja se izvršava prije svakog testa
    void setUp() { // metoda za postavljanje početnih vrijednosti
        polaznikService = Mockito.mock(PolaznikService.class); // kreiranje mock instance servisa
        polaznikController = new PolaznikController(polaznikService); // kreiranje instance kontrolera s mock servisom
        System.out.println("Priprema za testiranje PolaznikController!"); // ispis u konzolu radi praćenja izvođenja
    }

    @Test // označava da je metoda test
    @DisplayName("Dohvat svih polaznika") // postavljanje čitljivog imena testa
    void testGetAllPolaznici() { // metoda koja testira dohvat svih polaznika
        Polaznik p1 = new Polaznik(1, "Ana", "Anić"); // kreiranje prvog testnog polaznika
        Polaznik p2 = new Polaznik(2, "Ivan", "Ivić"); // kreiranje drugog testnog polaznika

        when(polaznikService.findAll()).thenReturn(Arrays.asList(p1, p2)); // definiramo ponašanje mock servisa da vrati listu s dva polaznika

        List<Polaznik> result = polaznikController.getAllPolaznici(); // poziv metode iz kontrolera koju testiramo

        assertEquals(2, result.size(), "Treba vratiti 2 polaznika!"); // provjera da je vraćena veličina liste 2
        assertEquals("Ana", result.get(0).getIme()); // provjera da je ime prvog polaznika "Ana"
        assertEquals("Ivić", result.get(1).getPrezime()); // provjera da je prezime drugog polaznika "Ivić"
    }

    @Test // označava da je metoda test
    @DisplayName("Dohvat polaznika po ID") // postavljanje čitljivog imena testa
    void testGetPolaznikById() { // metoda koja testira dohvat polaznika po ID-u
        Polaznik p = new Polaznik(1, "Marko", "Marić"); // kreiranje testnog polaznika
        when(polaznikService.findById(1)).thenReturn(Optional.of(p)); // definiranje ponašanja mock servisa da vrati polaznika za ID 1

        ResponseEntity<Polaznik> response = polaznikController.getPolaznikById(1); // poziv metode iz kontrolera koju testiramo

        assertTrue(response.getStatusCode().is2xxSuccessful(), "Status treba biti 200 OK!"); // provjera da je HTTP status 200
        assertEquals("Marko", response.getBody().getIme()); // provjera da je ime vraćenog polaznika "Marko"
    }

    @Test // označava da je metoda test
    @DisplayName("Kreiranje polaznika") // postavljanje čitljivog imena testa
    void testCreatePolaznik() { // metoda koja testira kreiranje polaznika
        PolaznikDTO dto = new PolaznikDTO("Petra", "Petrović"); // kreiranje DTO objekta za slanje u kreiranje
        Polaznik created = new Polaznik(10, "Petra", "Petrović"); // kreiranje objekta koji mock servis vraća

        when(polaznikService.save(dto)).thenReturn(created); // definiranje ponašanja mock servisa da vrati kreirani objekt

        Polaznik result = polaznikController.createPolaznik(dto); // poziv metode iz kontrolera koju testiramo

        assertNotNull(result.getId(), "ID kreiranog polaznika ne smije biti null!"); // provjera da ID nije null
        assertEquals("Petra", result.getIme()); // provjera da je ime kreiranog polaznika "Petra"
    }

    @Test // označava da je metoda test
    @DisplayName("Brisanje polaznika") // postavljanje čitljivog imena testa
    void testDeletePolaznik() { // metoda koja testira brisanje polaznika
        Integer idZaBrisanje = 5; // definiranje ID-a koji ćemo obrisati

        polaznikController.deletePolaznik(idZaBrisanje); // poziv metode iz kontrolera koju testiramo

        verify(polaznikService, times(1)).deleteById(idZaBrisanje); // provjera da je metoda servisa za brisanje pozvana točno jednom s tim ID-em
    }

} // end class
