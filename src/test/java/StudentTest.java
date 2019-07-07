import org.junit.Test;

import static org.junit.Assert.*;

public class StudentTest {

    @Test
    public void addMark() {
        Student newStudent = new Student (1234, "vasya ivanov");
        newStudent.addMark(3);
        Integer expected = 3;
        assertEquals(expected, newStudent.getMarks().get(0));
    }

    @Test
    public void averageMark() {
        Student newStudent = new Student (1234, "vasya ivanov");
        newStudent.addMark(3);
        newStudent.addMark(5);
        assertEquals(4.0, newStudent.averageMark(), 0.001);
    }
}