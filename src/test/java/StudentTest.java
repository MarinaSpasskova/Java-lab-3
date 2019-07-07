import java.util.ArrayList;

import static org.junit.Assert.*;

public class StudentTest {

    @org.junit.Test
    public void newStudent() {
        Student student = new Student(123, "Test name");
        assertEquals(123,student.getId());
        assertEquals("Test name",student.getFio());
    }

    @org.junit.Test
    public void setGroup() {
        Student student = new Student(123, "Test name");
        Group testGroup=new Group("Test group");
        student.setGroup(testGroup);

        assertEquals(testGroup,student.getGroup());
    }

    @org.junit.Test
    public void addMark() {
        ArrayList<Integer> testMarks = new ArrayList<Integer>();
        testMarks.add(3);
        testMarks.add(1);
        testMarks.add(5);

        Student student = new Student(123, "Test name");
        student.addMark(3);
        student.addMark(1);
        student.addMark(5);
        assertEquals(testMarks,student.getMarks());
    }

    @org.junit.Test
    public void getAverageMark() {
        Student student = new Student(123, "Test name");
        student.addMark(3);
        student.addMark(2);
        student.addMark(5);
        assertEquals(3.33,Student.getAverageMark(student),0.1);

    }


    @org.junit.Test
    public void findStudent() {
        Deanery dekanat = new Deanery();
        Student.newStudent(123, "Test name1", dekanat);
        Student.newStudent(124, "Test name2", dekanat);
        Student.newStudent(125, "Test name3", dekanat);

        assertEquals(-1,Student.findStudent(126, dekanat));
        assertEquals(1,Student.findStudent(124, dekanat));

    }
}