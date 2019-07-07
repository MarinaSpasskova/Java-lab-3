import org.junit.After;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GroupTest {

    @org.junit.Test
    public void getTitle() {
        Deanery dekanat=new Deanery();
        Group.addGroup("testTitle", dekanat);
        assertEquals("testTitle", dekanat.groupList.get(0).getTitle());
    }

    @org.junit.Test
    public void addStudent() {
        Deanery dekanat=new Deanery();
        Group.addGroup("testGroup", dekanat);
        Student.newStudent(123, "testName",dekanat);
        dekanat.groupList.get(0).addStudent(dekanat.studentsList.get(0));
        assertEquals("testName", dekanat.groupList.get(0).studentsInGroup.get(0).getFio());
    }

    @org.junit.Test
    public void setHead() {
        Deanery dekanat=new Deanery();
        Group.addGroup("testGroup",dekanat);
        Student.newStudent(1, "I am the head",dekanat);
        Student.newStudent(2, "I am not a head",dekanat);
        dekanat.groupList.get(0).addStudent(dekanat.studentsList.get(0));
        dekanat.groupList.get(0).addStudent(dekanat.studentsList.get(1));
        dekanat.groupList.get(0).setHead(1);
        assertEquals(1, dekanat.groupList.get(0).getHead().getId());
    }

    @org.junit.Test
    public void findStudent() {
        Deanery dekanat=new Deanery();
        Group.addGroup("testGroup",dekanat);
        Student.newStudent(123, "Petrov",dekanat);
        Student.newStudent(124, "Sidorov",dekanat);
        dekanat.groupList.get(0).addStudent(dekanat.studentsList.get(0));
        dekanat.groupList.get(0).addStudent(dekanat.studentsList.get(1));
        assertEquals(1, dekanat.groupList.get(0).findStudent(124));
        assertEquals(-1, dekanat.groupList.get(0).findStudent(999));

    }

    @org.junit.Test
    public void findStudent1() {
        Deanery dekanat=new Deanery();
        Group.addGroup("testGroup",dekanat);
        Student.newStudent(123, "Petrov",dekanat);
        Student.newStudent(124, "Sidorov",dekanat);
        dekanat.groupList.get(0).addStudent(dekanat.studentsList.get(0));
        dekanat.groupList.get(0).addStudent(dekanat.studentsList.get(1));
        assertEquals(1, dekanat.groupList.get(0).findStudent("Sidor"));
        assertEquals(-1, dekanat.groupList.get(0).findStudent("ksdfjsdhf"));
    }

}