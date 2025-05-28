import java.util.*;

public class Student implements Comparable<Student> {
    String name;
    int grade;

    public Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    public int compareTo(Student s) {
        int gradeComparison = Integer.compare(this.grade, s.grade);
        return gradeComparison;
    }


    public String toString() {
        return name + " - " + grade;
    }

}

class StudentSorter {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice", 90));
        students.add(new Student("Bob", 85));
        students.add(new Student("Charlie", 100));
        students.add(new Student("David", 75));
        students.add(new Student("Ante", 999999));
        students.add(new Student("Mrle", 462352342));

        // Sorting by grade, then by name
        //students.sort(Comparator.comparingInt((Student s) -> s.grade)
        //      .thenComparing(s -> s.name));
        Collections.sort(students);
        // Printing sorted students
        for (Student s : students) {
            System.out.println(s);
        }
    }
}