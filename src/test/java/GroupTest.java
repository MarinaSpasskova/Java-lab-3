import org.junit.Test;

import static org.junit.Assert.*;
import java.util.ArrayList;

public class GroupTest {



    Group newGroup = new Group("Group");
    Student newStudent = new Student("1", "Osipova");

    @Test
    public void testGetTitle() {
        assertEquals("Group", newGroup.getTitle());
    }

    @Test
    public void testGetStudents() {
        testAddStudentToGroup();
        assertEquals("Osipova", newGroup.getStudents().get(0).getFio());

    }

    @Test
    public void testAddStudentToGroup() {
        newGroup.addStudentToGroup(newStudent);
    }

    @Test
    public void testGetHead() {
        testSetHead();
        assertEquals("Osipova", newGroup.getHead());
    }

    @Test
    public void testSetHead() {
        newGroup.setHead(newStudent);
    }

    @Test
    public void testFindStudentById() {
        testAddStudentToGroup();
        assertEquals("Osipova", newGroup.findStudentById("1").getFio());
        ;
    }

    @Test
    public void testFindStudentByFio() {
        testAddStudentToGroup();
        assertEquals("1", newGroup.findStudentByFio("Osipova").getId());
    }

    @Test
    public void testAvarageMarkForGroup() {
        testAddStudentToGroup();
        newStudent.setMarks(5);
        Double five = 5.0;
        Double average = newGroup.avarageMarkForGroup();
        assertEquals(five, average);
    }

    @Test
    public void testDeleteStudentFromGroup() {
        testAddStudentToGroup();
        assertEquals("1", newGroup.findStudentByFio("Osipova").getId());

        ArrayList<Student> expected = new ArrayList<Student>();
        assertEquals(expected, newGroup.deleteStudentFromGroup(newStudent));
    }

}