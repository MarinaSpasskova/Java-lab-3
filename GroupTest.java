package DeaneryDemo;

import org.junit.Test;

import static org.junit.Assert.*;

public
class GroupTest {

    @Test
    public
    void setHead () {
        Group test = new Group("Test");
        Student testStudent = new Student("Иванов");
        test.setHead(testStudent);
        assertEquals(testStudent.getFIO(), test.getHead().getFIO());
    }

    @Test
    public
    void addStudents () {
        Group test = new Group("Test");
        Student testStudent = new Student("Иванов");
        test.addStudents(testStudent);
        assertEquals(testStudent, test.getStudents().get(0));
    }

    @Test
    public
    void headEllect () {
        Group test = new Group("Test");
        Student testStudent = new Student("Иванов");
        Student testStudent2 = new Student("Сидоров");
        for(int i = 0; i < 10; i++) {
            testStudent.addMark(5);
            testStudent2.addMark(4);
        }
        test.addStudents(testStudent);
        test.addStudents(testStudent2);
        test.headEllect();
        assertEquals(testStudent, test.getHead());
    }

    @Test
    public
    void searchStudent () {
        Group test = new Group("Test");
        Student testStudent = new Student("Иванов");
        Student testStudent2 = new Student("Сидоров");
        test.addStudents(testStudent);
        test.addStudents(testStudent2);
        assertEquals(true, test.SearchStudent("Иванов"));
        assertNotEquals(true, test.SearchStudent("Васечкин"));
    }

    @Test
    public
    void searchStudent1 () {
        Group test = new Group("Test");
        Student testStudent = new Student("Иванов");
        testStudent.setID(1);
        Student testStudent2 = new Student("Сидоров");
        testStudent2.setID(2);
        test.addStudents(testStudent);
        test.addStudents(testStudent2);
        assertNotEquals(false, test.SearchStudent(2));
        assertEquals(true, test.SearchStudent(1));
    }

    @Test
    public
    void calculateGroupAvarageMark () {
        Group test = new Group("Test");
        Student testStudent = new Student("Иванов");
        Student testStudent2 = new Student("Сидоров");
        for(int i = 0; i < 10; i++) {
            testStudent.addMark(5);
            testStudent2.addMark(3);
        }
        test.addStudents(testStudent);
        test.addStudents(testStudent2);
        test.CalculateGroupAvarageMark();
        assertEquals(4.0, test.getGroupAvarageMark(), 0.01);
    }

    @Test
    public
    void deleteStudents () {
        Group test = new Group("Test");
        Student testStudent = new Student("Иванов");
        Student testStudent2 = new Student("Сидоров");
        test.addStudents(testStudent);
        test.addStudents(testStudent2);
        test.DeleteStudents(testStudent);
        assertEquals(1, test.getStudents().size());
    }
}