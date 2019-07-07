import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DeaneryTest {
    private Deanery deanery = new Deanery();
    private ArrayList<Student> mStudents = new ArrayList<>();
    private ArrayList<Group> mGroups = new ArrayList<>();
    private Student mockStudent1 = mock(Student.class);
    private Student mockStudent2 = mock(Student.class);
    private Student mockStudent3 = mock(Student.class);
    private Student mockStudent4 = mock(Student.class);
    private Group mockGroup1 = mock(Group.class);
    private Group mockGroup2 = mock(Group.class);

    @Before
    public void setUp() throws Exception {
        mStudents.add(mockStudent1);
        mStudents.add(mockStudent2);
        mStudents.add(mockStudent3);
        mStudents.add(mockStudent4);
        deanery.setStudents(mStudents);

        mGroups.add(mockGroup1);
        mGroups.add(mockGroup2);
        deanery.setGroups(mGroups);
    }

    @Test
    public void removeOutsiders(){
        final double score1 = 5;
        final double score2 = 4;
        final double score3 = 2.5;
        final double score4 = 3;
        when(mockStudent1.calculateAverageScore()).thenReturn(score1);
        when(mockStudent2.calculateAverageScore()).thenReturn(score2);
        when(mockStudent3.calculateAverageScore()).thenReturn(score3);
        when(mockStudent4.calculateAverageScore()).thenReturn(score4);
        when(mockStudent3.getGroup()).thenReturn(mockGroup1);
        deanery.removeOutsiders();
        verify(mockGroup1).deleteStudent(mockStudent3);
    }

    @Test
    public void moveStudentToGroup(){
        deanery.moveStudentToGroup(mockStudent1, mockGroup1);
        verify(mockGroup1).addStudent(mockStudent1);

        when(mockStudent1.getGroup()).thenReturn(mockGroup1);
        when(mockGroup1.getStudents()).thenReturn(mStudents);
        deanery.moveStudentToGroup(mockStudent1, mockGroup2);
        verify(mockGroup1).deleteStudent(mockStudent1);
        verify(mockGroup2).addStudent(mockStudent1);
    }

    @Test
    public void assignHeads(){
        when(mockGroup1.findBestStudent()).thenReturn(mockStudent1);
        when(mockGroup2.findBestStudent()).thenReturn(null);
        deanery.assignHeads();
        verify(mockGroup1).chooseHead(mockStudent1);
        verify(mockGroup2, never()).chooseHead(any());
    }

    @Test
    public void addRandomMark(){
        deanery.addRandomMark(mockStudent1);
        verify(mockStudent1).addMark(any());
    }

    @Test
    public void deleteStudent(){
        when(mockStudent1.getGroup()).thenReturn(mockGroup1);
        deanery.deleteStudent(mockStudent1);
        verify(mockGroup1).deleteStudent(mockStudent1);
        assertFalse(mStudents.contains(mockStudent1));
    }

    @Test
    public void deleteGroup(){
        when(mockGroup2.getStudents()).thenReturn(mStudents);
        deanery.deleteGroup(mockGroup2);
        verify(mockStudent1).assignToGroup(null);
        verify(mockStudent2).assignToGroup(null);
        verify(mockStudent4).assignToGroup(null);
        verify(mockStudent3).assignToGroup(null);
        assertFalse(mGroups.contains(mockGroup2));
    }

}