import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Deanery {
    ArrayList<Group> groups = new ArrayList<Group>();
    ArrayList<Student> students = new ArrayList<Student>();

    //чтение массива студентов из json файла разбиение по группам
    void readFromFile(String fileName){
        JSONParser parser = new JSONParser();
        JSONArray groupsJA = new JSONArray();
        try {
            FileReader reader = new FileReader(fileName);
                try {
                    groupsJA = (JSONArray) parser.parse(reader);
                }
                catch ( ParseException ex){
                    System.err.println(ex.toString());
                }

                for (Object item :groupsJA) {
                    JSONObject groupJO = new JSONObject();
                    groupJO = (JSONObject) item;
                    JSONArray studInGroup =new JSONArray();

                    String groupTitle =(String) groupJO.get("title");
                    Group currentGroup = new Group(groupTitle);
                    groups.add(currentGroup);

                    studInGroup = (JSONArray) groupJO.get("stud");
                    for (Object item2 : studInGroup) {
                        JSONObject stud =new JSONObject();
                        stud = (JSONObject)item2;
                        Student currentStud = new Student(stud.get("fio").toString(),(Long)stud.get("id") );
                        students.add(currentStud);
                        currentGroup.addStudentToGroup(currentStud);
                    }
                }
            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.toString());
        }
          catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    //добавление случайных оценок студентам
    public void addMarksToAllStudent(int numOfMarks) {
        for (Student stud: students) {
            Random rand = new Random();
            for(int i=0; i< numOfMarks; i++){
                stud.addMark(1+rand.nextInt(5));
            }
        }
    }

    //перевод студентов из группы в группу
    public void transferToAnotherGroup(long studId, String newGroupTitle){
        Group newGroup=null;
        Student foundStud = null;

        for(Group group : groups) {
            if(group.title.equals(newGroupTitle)){
                newGroup = group;
            }
            if( group.findStudentInGroup(studId)!=null ){
                foundStud = group.findStudentInGroup(studId);
                System.out.println("Student id: "+ foundStud.getId() +" fio "+ foundStud.getFio() + " from group: " +foundStud.group.title);
                group.removeStudentFromGroup(foundStud);
            }
        }
        newGroup.addStudentToGroup(foundStud);
        System.out.println("removed into new group: " +foundStud.group.title);
    }

    //отчисление студентов за неуспеваемость
    public void deductionFromDeanari(float minAverageMark) {
        for (int i =0; i<students.size(); i++){
            if (students.get(i).calcAverageMark()< minAverageMark){
                System.out.println("id "+students.get(i).getId()+ " "+students.get(i).getFio()+" средний балл "+students.get(i).calcAverageMark()+" исключен из деканата");
                students.get(i).group.removeStudentFromGroup(students.get(i));
                students.remove(students.get(i));
                i--;
            }
        }
    }

    //инициация выборов старост в группах
    public void headsInAllGroupElection(){
        for(Group group : groups){
            group.headElection();
        }
    }

    //вывод данных на консоль
    void showDeanery(){
        for(Group group : groups){
            System.out.println("Группа "+group.title);
            if (group.students.size() == 0) {
                System.out.println("В этой группе не осталось студентов :(");
                continue;
            }
            System.out.print("Староста группы "+group.title+ " "+group.head.getFio()+"(id: "+group.head.getId() +")");
            System.out.println(" средний балл группы "+group.title+ " равен "+group.calcAverageMarkInGroup());
            System.out.println("список студентов группы:"+group.title);
            for(Student stud : group.students){
                System.out.println("id: "+stud.getId() + "   ФИО: "+stud.getFio()+"   средний балл: "+stud.calcAverageMark());
            }
            System.out.println("");
        }
    }

    //запись обратно в файл
    void writeToFile(String fileName){
        try {
            FileWriter writer = new FileWriter(fileName);
            JSONArray groupsJA = new JSONArray();

            for(Group group : groups){
                JSONObject groupJO = new JSONObject();
                groupJO.put("title",group.title);
                JSONArray studInGroupJA = new JSONArray();
                for(Student stud : group.students){
                    JSONObject jStud = new JSONObject();
                    jStud.put("id", stud.getId());
                    jStud.put("fio", stud.getFio());
                    studInGroupJA.add(jStud);
                }
                groupJO.put("stud",studInGroupJA);
                groupsJA.add(groupJO);
            }

            writer.write(groupsJA.toJSONString());
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            System.out.println("EXEPTION" + ex);
        }
    }
}
