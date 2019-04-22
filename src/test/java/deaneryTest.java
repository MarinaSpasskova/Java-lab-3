import org.junit.Test;

import static org.junit.Assert.*;

public class deaneryTest {

    @Test
    public void relocate() {
        deanery one = new deanery();
        one.setGroups("1");
        one.getGroups(0).setStudents(101,"Рябова В.Н.");
        one.getGroups(0).getStudents(0).setMarks(3);
        one.getGroups(0).getStudents(0).setMarks(4);
        one.getGroups(0).setStudents(102,"Грибоедов С.В.");
        one.getGroups(0).getStudents(1).setMarks(4);
        one.getGroups(0).getStudents(1).setMarks(5);
        one.setGroups("2");
        one.getGroups(1).setStudents(103,"Осинников Н.В.");
        one.getGroups(1).getStudents(0).setMarks(2);
        one.getGroups(1).getStudents(0).setMarks(3);
        one.getGroups(1).setStudents(104,"Грачева Д.В.");
        one.getGroups(1).getStudents(1).setMarks(5);
        one.getGroups(1).getStudents(1).setMarks(5);
        one.relocate(0, 1, "Рябова В.Н.");
        assertEquals(-1, one.getGroups(0).seachFIO("Рябова В.Н."));
        assertEquals(101, one.getGroups(1).seachFIO("Рябова В.Н."));
    }
}