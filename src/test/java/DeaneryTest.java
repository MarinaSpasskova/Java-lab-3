import org.junit.Test;

import static org.junit.Assert.*;

public class DeaneryTest {

    @Test
    public void addMarksToAllStudent() {
        Student stud1 = new Student("Вася Пупкин",1);
        Student stud2 = new Student("Вася Пупкин",2);
        Student stud3 = new Student("Вася Пупкин",3);
        Deanery deanery = new Deanery();
        deanery.students.add(stud1);
        deanery.students.add(stud2);
        deanery.students.add(stud3);

        deanery.addMarksToAllStudent(2);
        for (Student stud : deanery.students){
            assertEquals(2, stud.marks.size());
        }
    }

    @Test
    public void transferToAnotherGroup() {
        Student stud1 = new Student("Вася Пупкин",1);
        Group group1 = new Group("1");
        Group group2 = new Group("2");
        Deanery deanery = new Deanery();
        deanery.groups.add(group1);
        deanery.groups.add(group2);
        group1.addStudentToGroup(stud1);
        deanery.transferToAnotherGroup(1,"2");
        assertEquals(0,group1.students.size());
        assertEquals(1,group2.students.size());


    }

    @Test
    public void deductionFromDeanari() {
        Student stud1 = new Student("Вася Пупкин",1);
        Student stud2 = new Student("Вася Пупкин",2);
        Deanery deanery = new Deanery();
        deanery.students.add(stud1);
        deanery.students.add(stud2);
        deanery.groups.add(new Group("1"));
        deanery.groups.get(0).addStudentToGroup(stud1);
        deanery.groups.get(0).addStudentToGroup(stud2);
        stud1.addMark(5);
        stud2.addMark(2);
        deanery.deductionFromDeanari(3);
        assertTrue(!deanery.students.contains(stud2));
        assertTrue(deanery.students.size()==1);
        assertEquals(stud1,deanery.students.get(0));

    }

    @Test
    public void headsInAllGroupElection() {
        Student stud1 = new Student("Вася Пупкин",1);
        Student stud2 = new Student("Вася Пупкин",2);
        Student stud3 = new Student("Вася Пупкин",3);
        Group group1 = new Group("1");
        Group group2 = new Group("2");
        Deanery deanery = new Deanery();
        deanery.groups.add(group1);
        deanery.groups.add(group2);
        group1.addStudentToGroup(stud1);
        group2.addStudentToGroup(stud2);
        deanery.headsInAllGroupElection();
        assertEquals(stud1,group1.head);
        assertEquals(stud2,group2.head);
        group1.addStudentToGroup(stud3);
        deanery.transferToAnotherGroup(1,"2");
        assertEquals(stud3,group1.head);
    }
}