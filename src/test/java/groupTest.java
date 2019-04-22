import org.junit.Test;

import static org.junit.Assert.*;

public class groupTest {

    @Test
    public void averageRatingGroup() {
        deanery one = formGroups();
        assertEquals(4, one.getGroups(0).averageRatingGroup(), 0.1);
        assertEquals(3.5, one.getGroups(1).averageRatingGroup(), 0.1);
    }

    @Test
    public void seachFIO() {
        deanery one = formGroups();
        assertEquals(101, one.getGroups(0).seachFIO("Рябова В.Н."));
        assertEquals(104, one.getGroups(1).seachFIO("Грачева Д.В."));
        assertEquals(-1, one.getGroups(0).seachFIO("qwerty"));
    }

    @Test
    public void seachID() {
        deanery one = formGroups();
        assertEquals("Рябова В.Н.", one.getGroups(0).seachID(101));
        assertEquals("Грачева Д.В.", one.getGroups(1).seachID(104));
        assertEquals("not found", one.getGroups(0).seachID(105));
    }

    @Test
    public void deleteStudent() {
        deanery one = formGroups();
        assertEquals(101, one.getGroups(0).seachFIO("Рябова В.Н."));
        one.getGroups(0).deleteStudent("Рябова В.Н.");
        assertEquals(-1, one.getGroups(0).seachFIO("Ряюова В.Н."));
    }

    private deanery formGroups() {
        deanery one = new deanery();
        one.setGroups("1");
        one.getGroups(0).setStudents(101,"Рябова В.Н.");
        one.getGroups(0).getStudents(0).setMarks(3);
        one.getGroups(0).getStudents(0).setMarks(3);
        one.getGroups(0).setStudents(102,"Грибоедов С.В.");
        one.getGroups(0).getStudents(1).setMarks(5);
        one.getGroups(0).getStudents(1).setMarks(5);
        one.setGroups("2");
        one.getGroups(1).setStudents(103,"Осинников Н.В.");
        one.getGroups(1).getStudents(0).setMarks(3);
        one.getGroups(1).getStudents(0).setMarks(3);
        one.getGroups(1).setStudents(104,"Грачева Д.В.");
        one.getGroups(1).getStudents(1).setMarks(4);
        one.getGroups(1).getStudents(1).setMarks(4);
        return one;
    }


}