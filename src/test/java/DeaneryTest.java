import java.util.Iterator;

import static org.junit.Assert.*;

public class DeaneryTest {

    @org.junit.Test
    public void createGroupsAndStudents() {
        Deanery deanery = new Deanery();
        deanery.createGroupsAndStudents("students.json");
        assertEquals(3, deanery.groups.size());
    }

    @org.junit.Test
    public void createGroupsAndStudents1() {
        Deanery deanery = new Deanery();
        deanery.createGroupsAndStudents("students.json");
        assertEquals(60, deanery.allStudents.size());
    }

    @org.junit.Test
    public void addRandomMarks() {
        Deanery deanery = new Deanery();
        deanery.createGroupsAndStudents("students.json");
        deanery.addRandomMarks();
        for (Student student : deanery.allStudents) {
            assertNotNull(student.marks);
            assertEquals(10, student.marks.size());
        }
    }

    @org.junit.Test
    public void chooseHead() {
        Deanery deanery = new Deanery();
        deanery.createGroupsAndStudents("students.json");
        deanery.chooseHead();
        for (int i = 0; i < deanery.groups.size(); i++) {
            assertNotNull(deanery.groups.get(i).head);
        }
    }

    @org.junit.Test
    public void groupStatistics() {
        Deanery deanery = new Deanery();
        deanery.createGroupsAndStudents("students.json");
        for (Group group : deanery.groups) {
            assertNotNull(group.averageGroupMark());
        }
    }

    @org.junit.Test
    public void studentStatistics() {
        Deanery deanery = new Deanery();
        deanery.createGroupsAndStudents("students.json");
        for (Student student : deanery.allStudents) {
            assertNotNull(student.averageMark());
        }
    }

    @org.junit.Test
    public void removeStudentFromGroup() {
        Deanery deanery = new Deanery();
        deanery.createGroupsAndStudents("students.json");
        deanery.addRandomMarks();
        deanery.removeStudentFromGroup(3.0);
        for (Student student : deanery.allStudents) {
            assertTrue(student.averageMark() >= 3.0);
        }
    }

    @org.junit.Test
    public void transferStudentToAnotherGroup() {
        Deanery deanery = new Deanery();
        deanery.createGroupsAndStudents("students.json");
        deanery.transferStudentToAnotherGroup(deanery.findStudentByIDInDeanery(21), deanery.groups.get(2));
        assertEquals("Intermediate", deanery.findStudentByIDInDeanery(21).group.title);
    }

    @org.junit.Test
    public void findStudentByFIOInDeanery() {
        Deanery deanery = new Deanery();
        deanery.createGroupsAndStudents("students.json");
        assertEquals("Касатый Инна Карповна ", deanery.findStudentByFIOInDeanery("Касатый Инна Карповна ").getFio());
    }

    @org.junit.Test
    public void findStudentByIDInDeanery() {
        Deanery deanery = new Deanery();
        deanery.createGroupsAndStudents("students.json");
        assertEquals(30, deanery.findStudentByIDInDeanery(30).getId());
    }

    @org.junit.Test
    public void findGroupByTitleInDeanery() {
        Deanery deanery = new Deanery();
        deanery.createGroupsAndStudents("students.json");
        assertEquals("Begginer", deanery.findGroupByTitleInDeanery("Begginer").title);
    }

}