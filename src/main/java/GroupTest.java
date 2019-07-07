import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GroupTest {

    Group group = new Group("");
    Student mockStudent1 = mock(Student.class);
    Student mockStudent2 = mock(Student.class);
    List<Student> students = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        students.add(mockStudent1);
        students.add(mockStudent2);
        group.setStudents(students);
    }

    @Test
    public void addStudent(){
        group.addStudent(mockStudent1);
        verify(mockStudent1, never()).assignToGroup(group);

        Student mockStudent3 = mock(Student.class);
        group.addStudent(mockStudent3);
        verify(mockStudent3).assignToGroup(group);
    }

    @Test
    public void chooseHead(){
        boolean result = group.chooseHead(mockStudent1);
        assertTrue(result);

        Student mockStudent3 = mock(Student.class);
        result = group.chooseHead(mockStudent3);
        assertFalse(result);
    }

    @Test
    public void findStudentByName(){
        final String correctName = "Петя";
        final String incorrectName = "Вася";
        when(mockStudent1.getName())
                .thenReturn(correctName)
                .thenReturn(incorrectName);
        when(mockStudent2.getName()).thenReturn(incorrectName);
        Student student = group.findStudent(correctName);
        assertEquals(student, mockStudent1);

        student = group.findStudent(correctName);
        assertNull(student);
    }

    @Test
    public void findStudentById(){
        final int correctId = 1;
        final int incorrectId = 2;
        when(mockStudent1.getId()).thenReturn(correctId).thenReturn(incorrectId);
        Student student = group.findStudent(correctId);
        assertEquals(student, mockStudent1);

        student = group.findStudent(correctId);
        assertNull(student);
    }

    @Test
    public void calculateGroupAverageScore(){
        final double score1 = 100.0;
        final double score2 = 200.0;
        final double expectedResult = 150.0;
        when(mockStudent1.calculateAverageScore()).thenReturn(score1);
        when(mockStudent2.calculateAverageScore()).thenReturn(score2);
        double result = group.calculateGroupAverageScore();
        assertTrue(result == expectedResult);
    }

    @Test
    public void deleteStudent(){
        group.deleteStudent(mockStudent1);
        assertFalse(group.getStudents().contains(mockStudent1));
        verify(mockStudent1).assignToGroup(null);
    }

    @Test
    public void findBestStudent(){
        final double score1 = 100.0;
        final double score2 = 200.0;
        when(mockStudent1.calculateAverageScore()).thenReturn(score1);
        when(mockStudent2.calculateAverageScore()).thenReturn(score2);
        Student student = group.findBestStudent();
        assertEquals(mockStudent2, student);
    }


}