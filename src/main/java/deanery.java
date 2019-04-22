import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class deanery {

    private ArrayList<group> groups = new ArrayList<group>();

    void setGroups(String title) {
        groups.add(new group(title));
    }

    group getGroups(int num){
        return groups.get(num);
    }

    ArrayList<group> getGroups(){
        return groups;
    }

    void createJson() {
        JSONArray obj = new JSONArray();
        for (int m = 0; m < groups.size(); m++) {
            JSONObject group = new JSONObject();

            group.put("title", groups.get(m).getTitle());
            group.put("head", groups.get(m).getHead());

            JSONArray students = new JSONArray();
            for (Student i : groups.get(m).getStudents()){
                JSONObject stud = new JSONObject();
                stud.put("id", i.getId());
                stud.put("fio",i.getFio());
                JSONArray marks = new JSONArray();
                stud.put("marks", i.getMarks());
                students.add(stud);
            }
            group.put("students111",students);
            obj.add(group);
        }
        try {
            FileWriter file = new FileWriter("students.json");
            file.write(obj.toJSONString());

            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void readJson(){
        try {
            FileReader file = new FileReader("students.json");
            JSONParser jsonParser = new JSONParser();
            try {
                JSONArray obj = (JSONArray) jsonParser.parse(file);
                for (int i = 0; i < obj.size(); i++){

                    JSONObject groupJS = (JSONObject)obj.get(i);
                    groups.add(new group((String) groupJS.get("title")));
                    groups.get(i).setHead((String)groupJS.get("head"));
                    JSONArray studentsJS = (JSONArray) groupJS.get("students111");
                    for(int m = 0; m < studentsJS.size(); m++){
                        JSONObject studentJS = (JSONObject) studentsJS.get(m);
                        groups.get(i).setStudents((int)((Long) studentJS.get("id")).longValue(), (String) studentJS.get("fio"));
                        JSONArray marksJS = (JSONArray) studentJS.get("marks");
                        for(int h=0; h < marksJS.size(); h++){
                            groups.get(i).getStudents(m).setMarks((int)((Long) marksJS.get(h)).longValue());
                        }
                    }
                }
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    void randomMarksGenerate(){//генератор случайных оценок для всех студентов всех групп
        for (int i=0; i < groups.size(); i++){
            for (int m = 0; m < groups.get(i).getStudents().size(); m++){
                for(int h = 0; h < 10; h++){
                    groups.get(i).getStudents(m).setMarks(2 + (int) (Math.random() * 4));
                }
            }
        }
    }

    void relocate(int last, int future, String FIO){
        for (int i = 0; i < groups.get(last).getStudents().size(); i++){
            if (groups.get(last).getStudents(i).getFio().equals(FIO)){
                groups.get(future).setStudents(groups.get(last).getStudents(i).getId(), FIO);
                for (int h = 0; h < groups.get(last).getStudents(i).getMarks().size(); h++){
                    groups.get(future).getStudents(groups.get(future).getStudents().size()-1).setMarks(groups.get(last).getStudents(i).getMarks(h));
                }
                groups.get(last).deleteStudent(FIO);
                break;
            }
        }
    }

    void printStudent(){
        for(group i: groups){
            System.out.printf("Группа %s, староста %s, средняя успеваемость %.2f\n", i.getTitle(), i.getHead(),i.averageRatingGroup());
            for(Student m:i.getStudents()){
                System.out.println(m.getId() +" "+ m.getFio()+" "+m.averageRating());
            }
        }
    }
}


