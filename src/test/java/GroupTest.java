import org.junit.Test;

import static org.junit.Assert.*;

public class GroupTest {

    @Test
    public void addStudent() {
        Group group = new Group("JAVA");
        assertEquals(0, group.students.size());
        Student student = new Student(1, "Martynov");
        Student student1 = new Student(2, "Gandzha");
        group.addStudent(student);
        group.addStudent(student1);
        assertEquals(2, group.students.size());
        assertTrue(group.students.get(0).fio.equals("Martynov"));
        assertTrue(group.students.get(1).fio.equals("Gandzha"));
    }

    @Test
    public void chooseHead() {
        Group group = new Group("JAVA");
        assertEquals(group.head, null);
        Student student = new Student(1, "Martynov");
        Student student1 = new Student(2, "Gandzha");
        group.addStudent(student);
        group.addStudent(student1);
        group.chooseHead(student1);
        assertEquals(group.head, student1);
        assertTrue(group.head != student);
    }

    @Test
    public void findByFIO() {
        Group group = new Group("JAVA");
        assertEquals(group.findByFIO("Martynov"), null);
        Student student = new Student(1, "Martynov");
        Student student1 = new Student(2, "Gandzha");
        group.addStudent(student);
        group.addStudent(student1);
        assertEquals(group.findByFIO("Martynov"), student);
        assertEquals(group.findByFIO("Gandzha"), student1);
    }

    @Test
    public void findByID() {
        Group group = new Group("JAVA");
        assertEquals(group.findByID(1), null);
        Student student = new Student(1, "Martynov");
        Student student1 = new Student(2, "Gandzha");
        group.addStudent(student);
        group.addStudent(student1);
        assertEquals(group.findByID(1), student);
        assertEquals(group.findByID(2), student1);
        assertEquals(group.findByID(3), null);

    }

    @Test
    public void deleteStudent() {
        Group group = new Group("JAVA");
        Student student = new Student(1, "Martynov");
        Student student1 = new Student(2, "Gandzha");
        group.addStudent(student);
        group.addStudent(student1);
        group.deleteStudent(1);
        assertEquals(1, group.students.size());

    }
}