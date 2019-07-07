import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class StudentTest {

    @Test
    public void studentToGroup() {
        Student student=new Student(3,"Martynov");
        Group group=new Group("Java");
        student.studentToGroup(group);
        assertEquals(student.group,group);
        Group group2=new Group("C++");
        student.studentToGroup(group2);
        assertEquals(student.group,group2);
        assertNotSame(student.group,group);
    }

    @Test
    public void putMark() {
        Student student=new Student(3,"Martynov");
        ArrayList<Integer> exp=new ArrayList<Integer>();
        assertEquals(0,student.marks.size());
        student.putMark(5);
        student.putMark(6);
        assertEquals(2,student.marks.size());
        assertEquals((long)5,(long)student.marks.get(0));
        assertEquals((long)6,(long)student.marks.get(1));
    }

    @Test
    public void averageMark() {
        Student student=new Student(3,"Martynov");
        assertEquals(0,student.averageMark(),0.001);
        student.putMark(5);
        student.putMark(6);
        assertEquals(5.5F,student.averageMark(),0.001);
    }
}