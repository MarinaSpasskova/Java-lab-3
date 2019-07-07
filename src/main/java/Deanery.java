import java.io.*;
import java.util.ArrayList;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Deanery {
    public ArrayList<Student> studentsList = new ArrayList<Student>();
    public ArrayList<Group> groupList = new ArrayList<Group>();


    void addStudentsFromFile() {
        try {
            File f = new File("Names.json");
            JSONParser parser = new JSONParser();
            FileReader fr = new FileReader(f);

            Object obj = parser.parse(fr);
            JSONObject js = (JSONObject) obj;
            JSONArray items = (JSONArray) js.get("students");
            for (Object i : items) {
                Student.newStudent(Integer.parseInt(((JSONObject) i).get("id").toString()),
                        (((JSONObject) i).get("fio").toString()), this);
            }
        } catch (
                FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    void addGroupsFromFile() {
        try {
            File f = new File("Groups.json");
            JSONParser parser = new JSONParser();
            FileReader fr = new FileReader(f);

            Object obj = parser.parse(fr);
            JSONObject js = (JSONObject) obj;
            JSONArray items = (JSONArray) js.get("groups");
            for (Object i : items) {
                Group.addGroup((((JSONObject) i).get("groupname").toString()), this);
            }
        } catch (
                FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    void setRandomMarks() {
        for (Student student : studentsList) {
            for (int i = 0; i < 20; i++) {
                student.addMark(randomInt(1, 5));
            }
        }
    }

    int randomInt(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    void changeGroup(Student student, Group targetGroup) {
        student.getGroup().deleteStudentFromGroup(student);
        targetGroup.addStudent(student);
        student.setGroup(targetGroup);
    }

    void sendDownStudent(Student student) {
        if (Student.getAverageMark(student) <= 2.50) {
            studentsList.remove(student);
            student.getGroup().deleteStudentFromGroup(student);
            System.out.println("Student " + student.getFio() + " was send down");
        } else {
            System.out.println("This student cannot be send down, because his average mark is " + Student.getAverageMark(student) + " (>=2.5");
        }
    }

    void saveInfoToFile() {
        JSONObject object = new JSONObject();
        JSONArray studentsJSON = new JSONArray();

        for (Student student : studentsList) {
            JSONObject obj = new JSONObject();
            obj.put("id", student.getId());
            obj.put("fio", student.getFio());
            obj.put("group", student.getGroup().getTitle());
            obj.put("marks", student.getMarks());
            studentsJSON.add(obj);
        }

        object.put("students", studentsJSON);

        try {
            File f = new File("Output.json");
            FileWriter writer = new FileWriter(f);
            writer.write(object.toJSONString());
            writer.flush();
            writer.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    void voteForHeadinGroups() {          // 'Zaglushka'. First member of the each group is beginning to be a head of this group.
        for (Group group : groupList) {
            group.setHead(group.studentsInGroup.get(0).getId());
        }
    }

    void printAllInfo() {
        for (Group group : groupList) {
            System.out.println(group.getTitle());
            for (Student student : group.studentsInGroup) {
                System.out.println(student.getId() + " - " + student.getFio() + "\nmarks:");
                for (int mark : student.getMarks()) {
                    System.out.print(mark + ",");
                }
                System.out.println("");
            }
        }
    }
}
