import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class StudentTest {
    Student student = new Student(0, "");
    List<Mark> marks = new ArrayList<>();
    Mark mockMark1 = mock(Mark.class);
    Mark mockMark2 = mock(Mark.class);
    Group mockGroup = mock(Group.class);

    @Before
    public void setUp() throws Exception {
        marks.add(mockMark1);
        marks.add(mockMark2);
        student.setMarks(marks);
        student.assignToGroup(mockGroup);
    }

    @Test
    public void calculateAverageScore(){
        final int expected = 300;
        final int score1 = 100;
        final int score2 = 500;
        when(mockMark1.getScore()).thenReturn(score1);
        when(mockMark2.getScore()).thenReturn(score2);

        Assert.assertEquals(expected, student.calculateAverageScore(), 0.0);
    }
}