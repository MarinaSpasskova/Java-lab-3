import java.io.File;
import java.util.*;
import java.io.*;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Deanery {
    protected ArrayList<Student> students = new ArrayList<Student>();
    protected ArrayList<Group> group = new ArrayList<Group>();

    public void putMarks() {
        for (Integer j = 0; j < 5; j++) { //put marks
            for (Student i : students) {
                Random random = new Random();
                Integer mark = random.nextInt(5) + 1;
                i.putMark(mark);
            }
        }
    }

    public void changeGroup(){
        Group del = new Group("to army");
        for (Student i : students) { //change group
            if (i.averageMark() <= 2.4) {
                i.studentToGroup(del);
                System.out.printf("Student %s is going to army because average mark is %.2f\n", i.fio, i.averageMark());
            } else if (i.averageMark() <= 3.4) {
                i.studentToGroup(group.get(0));
                System.out.printf("Student %s is moving to group E because average mark is %.2f\n", i.fio, i.averageMark());
            } else if (i.averageMark() <= 4.4) {
                i.studentToGroup(group.get(1));
                System.out.printf("Student %s is moving to group B because average mark is %.2f\n", i.fio, i.averageMark());
            } else {
                i.studentToGroup(group.get(2));
                System.out.printf("Student %s is moving to group I because average mark is %.2f\n", i.fio, i.averageMark());
            }
        }
        //delete students
        for (Integer i = students.size() - 1; i >= 0; i--) {
            if (students.get(i).group == del) {
                students.remove(students.get(i));
            }
        }
    }

    public void makeGroupsList(File f) throws Exception {
        JSONParser parser = new JSONParser();
        FileReader fr = new FileReader(f);
        Object obj = parser.parse(fr);
        JSONObject js = (JSONObject) obj;
        JSONArray items = (JSONArray) js.get("groups");
        for (Object i : items) {
            Group current = new Group((String) (((JSONObject) i).get("title")));
            this.group.add(current);
        }
        fr.close();
    }

    public void chooseHeads(){
        //choose a head by average mark
        ArrayList<Double> maxMark = new ArrayList<Double>(Collections.nCopies(group.size(), 0.));
        for (Student i : students) {
            if (i.averageMark() > maxMark.get(group.indexOf(i.group))) {
                i.group.head = i;
                maxMark.set(group.indexOf(i.group), i.averageMark());
            }
        }
        for (Group i : group) {
            if (i.head == null) {
                System.out.printf("Group %s is empty\n", i.title);
            } else {
                System.out.printf("The head of group %s is %s because his average mark is %.2f\n", i.title, i.head.fio, i.head.averageMark());
            }
        }
    }

    public void makeStudentsList(File f) throws Exception {
        JSONParser parser = new JSONParser();
        FileReader fr = new FileReader(f);
        Object obj = parser.parse(fr);
        JSONObject js = (JSONObject) obj;
        JSONArray items = (JSONArray) js.get("students");
        for (Object i : items) {
            Object obj1 = ((JSONObject) i).get("id");
            Integer id = Integer.parseInt(obj1.toString());
            String fio = (String) ((JSONObject) i).get("fio");
            String group = (String) ((JSONObject) i).get("group");
            Student current = new Student(id, fio);
            for (Group j : this.group) {
                if (group.equals(j.title)) {
                    current.studentToGroup(j);
                }
            }
            this.students.add(current);
        }
        fr.close();
    }

    public void makeGroupFile(File f) throws Exception {
        JSONObject js = new JSONObject();
        JSONArray items = new JSONArray();
        for (Group group : this.group) {
            Map<String, String> i = new HashMap<String, String>();
            i.put("title", group.title);
            items.add(i);
        }
        js.put("groups", items);
        FileWriter fw = new FileWriter(f);
        fw.write(js.toJSONString());
        fw.close();
    }

    public void makeStudentFile(File f) throws Exception {
        JSONObject js = new JSONObject();
        JSONArray items = new JSONArray();
        for (Student student : this.students) {
            JSONObject obj = new JSONObject();
            Map<String, Integer> i1 = new HashMap<String, Integer>();
            i1.put("id", student.ID);
            Map<String, String> i2 = new HashMap<String, String>();
            i2.put("fio", student.fio);
            Map<String, String> i3 = new HashMap<String, String>();
            i3.put("group", student.group.title);
            obj.put("id", student.ID);
            obj.put("fio", student.fio);
            obj.put("group", student.group.title);
            items.add(obj);
        }
        js.put("students", items);
        FileWriter fw = new FileWriter(f);
        fw.write(js.toJSONString());
        fw.close();

    }
}
