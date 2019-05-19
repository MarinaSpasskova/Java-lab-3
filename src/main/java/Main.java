import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Main {

    public static void main(String[] args) {
        Deanery deanery = new Deanery();

        emulateUser(deanery);

        // initiate(deanery);
    }

    private static void emulateUser(Deanery deanery) {
        deanery.loadAll();
        Student student = new Student((int) (Math.random() * 100), ReaderUtils.generateRandomPerson());
        System.out.println(student.getName());

        deanery.addStudent(student);
        deanery.addRandomMark(student);
        deanery.addRandomMark(student);
        deanery.moveStudentToGroup(student, deanery.getGroups().get(1));


        deanery.removeOutsiders();
        deanery.assignHeads();
        deanery.printStudents();
        deanery.printGroups();
        deanery.saveAll();
    }

    private static void initiate(Deanery deanery) {
        Group group1 = new Group("r1");
        deanery.addGroup(group1);
        for (int i = 0; i< 10; i++) {
            Student student = new Student((int) (Math.random() * 100), ReaderUtils.generateRandomPerson());
            deanery.addStudent(student);
            deanery.addRandomMark(student);
            deanery.addRandomMark(student);
            deanery.addRandomMark(student);
            deanery.moveStudentToGroup(student, group1);
        }


        Group group2 = new Group("r2");
        deanery.addGroup(group2);
        for (int i = 0; i< 10; i++) {
            Student student = new Student((int) (Math.random() * 100), ReaderUtils.generateRandomPerson());
            deanery.addStudent(student);
            deanery.addRandomMark(student);
            deanery.addRandomMark(student);
            deanery.addRandomMark(student);
            deanery.moveStudentToGroup(student, group2);
        }


        Group group3 = new Group("r3");
        deanery.addGroup(group3);
        for (int i = 0; i< 10; i++) {
            Student student = new Student((int) (Math.random() * 100), ReaderUtils.generateRandomPerson());
            deanery.addStudent(student);
            deanery.addRandomMark(student);
            deanery.addRandomMark(student);
            deanery.addRandomMark(student);
            deanery.moveStudentToGroup(student, group3);
        }


        deanery.saveAll();
    }
}
