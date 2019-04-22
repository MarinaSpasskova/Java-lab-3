import static org.junit.Assert.*;

public class StudentTest {

    @org.junit.Test
    public void averageRating() {
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
        //one.createJson();
        //one.readJson();
        assertEquals(3.5, one.getGroups(0).getStudents(0).averageRating(), 0.1);
        assertEquals(4.5, one.getGroups(0).getStudents(1).averageRating(), 0.1);
        assertEquals(2.5, one.getGroups(1).getStudents(0).averageRating(), 0.1);
        assertEquals(5, one.getGroups(1).getStudents(1).averageRating(), 0.1);
    }
}