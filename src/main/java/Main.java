import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        Deanery D = new Deanery();
        File f = new File("groups.json");
        D.makeGroupsList(f); // parsing groups
        f = new File("students.json");
        D.makeStudentsList(f); //parsing students
        D.putMarks();
        D.changeGroup();
        D.chooseHeads();
        f = new File("groups_out.json");
        D.makeGroupFile(f);
        f = new File("students_out.json");
        D.makeStudentFile(f);


        Deanery D1 = new Deanery();
    }
}

