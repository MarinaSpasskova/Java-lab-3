import org.junit.Test;

import static org.junit.Assert.*;

public class StudentTest {

   

    Student newStudent = new Student("1", "Osipova");
    Group newGroup = new Group("Group_7");

    @Test
    public void testGetGroupTitle() {
        newStudent.setGroup(newGroup);
        assertEquals("Group_7", newStudent.getGroupTitle());
    }

    @Test
    public void testGetGroupByGroup() {
        newStudent.setGroup(newGroup);
        assertEquals("Group_7", newStudent.getGroupByGroup().getTitle());

    }

    @Test
    public void testSetMarks() {
        newStudent.setMarks(5);
        newStudent.setMarks(4);
        newStudent.setMarks(3);
    }

    @Test
    public void testGetMarks() {
        testSetMarks();
        Integer five = 5;
        Integer four = 4;
        Integer free = 3;
        assertEquals(five, newStudent.getMarks().get(0));
        assertEquals(four, newStudent.getMarks().get(1));
        assertEquals(free, newStudent.getMarks().get(2));
    }

    @Test
    public void testAverageMark() {
        testSetMarks();
        Double average = 4.0;
        assertEquals(average, newStudent.averageMark());

    }

    @Test
    public void testGetId() {
        assertEquals("1", newStudent.getId());
    }

    @Test
    public void testGetFio() {
        assertEquals("Osipova", newStudent.getFio());
    }

}