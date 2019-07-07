import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class StudentTest {

    @Test
    public void addToGroup() {
        Student stud = new Student("Вася Пупкин",5);
        Group group = new Group("1");
        stud.addToGroup(group);
        assertEquals(group,stud.group);
    }

    @Test
    public void addMark() {
        Student stud = new Student("Вася Пупкин",5);
        stud.addMark(5);
        stud.addMark(4);
        stud.addMark(3);
        ArrayList<Integer> MARKS = new ArrayList<Integer>();
        MARKS.add(5);
        MARKS.add(4);
        MARKS.add(3);
        assertEquals(MARKS,stud.marks);
    }

    @Test
    public void calcAverageMark() {
        Student stud = new Student("Вася Пупкин",5);
        stud.addMark(5);
        stud.addMark(4);
        stud.addMark(3);
        ArrayList<Integer> MARKS = new ArrayList<Integer>();
        MARKS.add(5);
        MARKS.add(4);
        MARKS.add(3);
        assertEquals(4, stud.calcAverageMark(),0.1);
    }
}