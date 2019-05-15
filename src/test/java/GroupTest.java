import org.junit.Test;

import static org.junit.Assert.*;

public class GroupTest {

    @Test
    public void addStudent() {
        Group myGroup = new Group("new Group");
        Student newStudent = new Student (1234, "vasya ivanov");
        myGroup.addStudent(newStudent);
        assertTrue(myGroup.getStudents().contains(newStudent));
        assertEquals(myGroup, newStudent.getGroup());
    }

    @Test
    public void setHead() {
        Group myGroup = new Group("new Group");
        Student newStudent = new Student (1234, "vasya ivanov");
        myGroup.addStudent(newStudent);
        myGroup.setHead(newStudent);
        assertEquals(newStudent, myGroup.getHead());
    }

    @Test
    public void setHead1() {
        Group myGroup = new Group("new Group");
        Student newStudent = new Student (1234, "vasya ivanov");
        myGroup.addStudent(newStudent);
        myGroup.setHead();
        assertNotNull(myGroup.getHead());
    }

    @Test
    public void searchById() {
        Group myGroup = new Group("new Group");
        Student newStudent = new Student (1234, "vasya ivanov");
        Student newStudent2 = new Student (1235, "ivan petrov");
        myGroup.addStudent(newStudent);
        myGroup.addStudent(newStudent2);
        assertEquals(newStudent2, myGroup.searchById(1235));
        assertNotSame(newStudent2, myGroup.searchById(1234));
    }

    @Test
    public void searchByFio() {
        Group myGroup = new Group("new Group");
        Student newStudent = new Student (1234, "vasya ivanov");
        Student newStudent2 = new Student (1235, "ivan petrov");
        myGroup.addStudent(newStudent);
        myGroup.addStudent(newStudent2);
        assertEquals(newStudent2, myGroup.searchByFio("ivan petrov"));
        assertNotSame(newStudent2, myGroup.searchByFio("vasya ivanov"));
    }

    @Test
    public void averageMarkInGroup() {
        Group myGroup = new Group("new Group");
        Student newStudent = new Student (1234, "vasya ivanov");
        Student newStudent2 = new Student (1235, "ivan petrov");
        newStudent.addMark(3);
        newStudent2.addMark(4);
        myGroup.addStudent(newStudent);
        myGroup.addStudent(newStudent2);
        double expected = 3.5;
        assertEquals(expected, myGroup.averageMarkInGroup(), 0.001);
    }

    @Test
    public void deleteStudentById() {
        Group myGroup = new Group("new Group");
        Student newStudent = new Student (1234, "vasya ivanov");
        myGroup.addStudent(newStudent);
        myGroup.deleteStudentById(1234);
        assertTrue(myGroup.getStudents().isEmpty());
    }
}