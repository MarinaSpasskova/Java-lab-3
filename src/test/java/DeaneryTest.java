import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class DeaneryTest {

    @Test
    public void createStudents() {
        Deanery myDeanery = new Deanery();
        myDeanery.createStudents("students.json");
        assertEquals(31, myDeanery.getStudents().size());
    }

    @Test
    public void createGroups() {
        Deanery myDeanery = new Deanery();
        myDeanery.createGroups("groups.json");
        assertEquals(3, myDeanery.getGroups().size());
    }

    @Test
    public void fillingGroups(){
        Deanery myDeanery = new Deanery();
        myDeanery.createStudents("students.json");
        myDeanery.createGroups("groups.json");
        assertEquals(true, myDeanery.fillingGroups());
        assertEquals(11, myDeanery.getGroups().get(0).getStudents().size());
        assertEquals(10, myDeanery.getGroups().get(1).getStudents().size());
    }

    @Test
    public void transferStudentToOtherGroup() {
        Deanery myDeanery = new Deanery();
        myDeanery.createStudents("students.json");
        myDeanery.createGroups("groups.json");
        myDeanery.fillingGroups();
        myDeanery.transferStudentToOtherGroup(myDeanery.searchStudentByIdInDeanery("101"),
                myDeanery.getGroups().get(1));
        assertEquals("А-2", myDeanery.searchStudentByIdInDeanery("101").getGroup().getTitle());
    }

    @Test
    public void dismissalOfStudentsBecauseOfLowMarks() {
        Deanery myDeanery = new Deanery();
        myDeanery.createStudents("students.json");
        myDeanery.createGroups("groups.json");
        myDeanery.fillingGroups();
        myDeanery.generateRandomStudentsMarks();
        myDeanery.dismissalOfStudentsBecauseOfLowMarks("3.0");
        Iterator<Student> iterator = myDeanery.getStudents().iterator();
        double minMark = 3.0;
        while (iterator.hasNext()) {
            Student nextStudent = iterator.next();
            assertTrue(nextStudent.averageMark() >= minMark);
        }
    }

    @Test
    public void headElectionsInGroups() {
        Deanery myDeanery = new Deanery();
        myDeanery.createStudents("students.json");
        myDeanery.createGroups("groups.json");
        myDeanery.fillingGroups();
        myDeanery.headElectionsInGroups();
        for (int i = 0; i < myDeanery.getGroups().size(); i++) {
            assertNotNull(myDeanery.getGroups().get(i).getHead());
        }

    }

    @Test
    public void changeHeadInGroupById() {
        Deanery myDeanery = new Deanery();
        myDeanery.createStudents("students.json");
        myDeanery.createGroups("groups.json");
        myDeanery.fillingGroups();
        myDeanery.headElectionsInGroups();
        assertEquals(true, myDeanery.changeHeadInGroupById("101", "А-1"));
        assertEquals(101, myDeanery.getGroups().get(0).getHead().getId());
        assertEquals(false, myDeanery.changeHeadInGroupById("-23", "А-1"));
        assertEquals(false, myDeanery.changeHeadInGroupById("101", "А-3"));
        assertEquals(false, myDeanery.changeHeadInGroupById("101", "А-564"));
    }

    @Test
    public void searchStudentByIdInDeanery() {
        Deanery myDeanery = new Deanery();
        myDeanery.createStudents("students.json");
        Student expected = myDeanery.getStudents().get(0);
        assertSame(expected, myDeanery.searchStudentByIdInDeanery("101"));
    }

    @Test
    public void searchGroupByTitleInDeanery() {
        Deanery myDeanery = new Deanery();
        myDeanery.createGroups("groups.json");
        Group expected = myDeanery.getGroups().get(0);
        assertSame(expected, myDeanery.searchGroupByTitleInDeanery("А-1"));
    }
}