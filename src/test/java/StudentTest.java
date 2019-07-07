import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class StudentTest {

    @Test
    public void addToGroup() {
        Student student = new Student(1, "Petia Pupkin");
        Group group = new Group("First");
        student.addToGroup(group);
        assertEquals("First", student.group.title);
    }

    @Test
    public void addNewMark() {
        Student student = new Student(1, "Petia Pupkin");
        student.addNewMark(5);
        student.addNewMark(3);
        assertEquals(2, student.marks.size());
        assertEquals((long) 3, (long) student.marks.get(1));
    }

    @Test
    public void averageMark() {
        Student student = new Student(1, "Petia Pupkin");
        student.addNewMark(5);
        student.addNewMark(3);
        assertEquals(4, student.averageMark(), 0.001);
    }
}