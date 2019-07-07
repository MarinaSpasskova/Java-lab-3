package DeaneryDemo;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;


import java.io.*;
import java.util.ArrayList;


public class Deanery {
    private ArrayList<Group> groups;
    private ArrayList<Student> students;

    public
    Deanery ( File fileStudents ) {
        System.out.println("Students from txt are reading!");
        this.groups = new ArrayList<Group>();
        this.students = new ArrayList<Student>();
        addStudentsFromTxt(fileStudents);
        for(NameGroup group : NameGroup.values()){
            String s = group.toString();
            groups.add(new Group(s));
        }
        System.out.println("ID adding for students and building groups");
        int studentCount = 1;
        for(Student student:students){
            student.setID(studentCount);
            if((double)students.size() / studentCount >= 3){
                student.setGroup(groups.get(0));
                groups.get(0).addStudents(student);
            }
            else if(((double)students.size() / studentCount < 3) && ((double)students.size() / studentCount >= 1.5)){
                student.setGroup(groups.get(1));
                groups.get(1).addStudents(student);
            }
            else {
                student.setGroup(groups.get(2));
                groups.get(2).addStudents(student);
            }
            studentCount++;
        }

        for(Group group:groups){
            group.setHead(group.getStudents().get(0));
        }
        System.out.println("ID was added to student. Groups were builded!");
        deaneryStat();

    }

    public
    Deanery ( File allStudents, File readyDeanery ) {
        this.groups = new ArrayList<Group>();
        this.students = new ArrayList<Student>();
        for(NameGroup group : NameGroup.values()){
            String s = group.toString();
            groups.add(new Group(s));
        }
        readStudentsFromJSON();
        deaneryStat();
    }
    public void readStudentsFromJSON (){
        System.out.println("Start JSON reading!");
           File file = new File("addStudentsTo.json");
           JSONParser parser = new JSONParser();
        try {
            FileReader fr = new FileReader(file);
            Object obj=parser.parse(fr);
            JSONObject js=(JSONObject)obj;
            JSONArray items=(JSONArray)js.get("students");
            for(Object i : items) {
                students.add(new Student(Integer.parseInt(((JSONObject)i).get("id").toString()),
                        ((JSONObject)i).get("FIO").toString(), groupSearcher(((JSONObject)i).get("Group").toString())));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Student student: students){
            student.getGroup().addStudents(student);
        }
        for(Group group:groups){
            group.setHead(group.getStudents().get(0));
        }
        System.out.println("JSON-file read suspected!");

    }
    public void marks (int quantity){
        System.out.println("Start of student-marking. Every student get " + quantity + " marks");
        int min = 2;
        int max = 5;
        for(Student student: students){
            for(int i = 0; i < quantity; i++){
                int randomMark = min + (int) (Math.random() * (max -1));
                student.addMark(randomMark);
            }
        }
        System.out.println("Student-marking finished!");
        for(Student student: students){
            student.printStudent();
        }
    }

    public void ellectHead(){
        System.out.println("Head election!");
        for(Group group: groups){
            group.headEllect();
            System.out.println("The head of " + group.getTitle() + " is elected - " + group.getHead().getFIO()
            + " with average mark:" + group.getHead().getAverageMark());
        }
        System.out.println();
    }

    public void changeGroup(){

        for(Student student: this.students){
            if(student.getAverageMark()<2.5){
                System.out.println("Expelled:");
                student.printStudent();
                students.remove(student);
            }
            if((student.getAverageMark() > 2.5) && (student.getAverageMark() <= 3.0)){
                student.setGroup(groups.get(2));
            }
            if((student.getAverageMark() > 3.0) && (student.getAverageMark()<=4)){
                student.setGroup(groups.get(1));
            }
            if(student.getAverageMark() > 4.0){
                student.setGroup(groups.get(0));
            }
        }
        for(Group group: groups){
            group.getStudents().clear();
        }
        for(Student student: students){
            student.getGroup().addStudents(student);
        }
        for(Group group: groups){
            group.CalculateGroupAvarageMark();
        }
        deaneryStat();
        printStudents();
    }
    public void printStudents(){
        for(Group group: groups){
            System.out.println(group.getTitle() + "||" + group.getHead().getFIO() + " || average mark: "
                    + group.getHead().getAverageMark());
            for(Student student: group.getStudents()){
                System.out.print("" + student.getID() + " " + student.getFIO() + " || "
                        + student.getAverageMark() + " || " + student.getGroup().getTitle() + "\n");
            }
        }
    }

    public
    Group groupSearcher ( String s){
        for (Group g: groups){
            if(g.getTitle().equals(s)){
                return g;
            }
        }
        return null;
    }
    public void saveArraayAsJSON (File fileName){
        System.out.println("Start saving deanery in JSON-file!");
        JSONObject object = new JSONObject();
        JSONArray arrayStudents = new JSONArray();

        for(Student student: this.students){
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("id", student.getID());
            jsonObj.put("FIO", student.getFIO());
            jsonObj.put("Group", student.getGroup().getTitle());
            jsonObj.put("Marks", student.getMarks());
            jsonObj.put("AverageMark", student.getAverageMark());
            arrayStudents.add(jsonObj);
        }

        object.put("students", arrayStudents);

        try {
            //File saveStudents = new File(String.valueOf(fileName));
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(object.toJSONString());
            fileWriter.flush();
            fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        System.out.println("JSON-file is saved suspected!");
        System.out.println("_______________________________________________\n");
    }

    public void addStudentsFromTxt ( File fileStudents)  {

        BufferedReader bufferStudent;
            try {
                bufferStudent = new BufferedReader(new FileReader(fileStudents));
                String s;
                while(bufferStudent.readLine() != null) {
                    s = bufferStudent.readLine();
                    students.add(new Student(s));
                }
            } catch (IOException e) {
                System.out.println("Error!");
            }
        System.out.println("Students from txt was added!");
        }

    public
    void setGroups ( ArrayList<Student> Students ) {
        int studentsNumberInGroup = 0;
        studentsNumberInGroup = Students.size() / 3;
        for(int i = 0, j = studentsNumberInGroup; i < Students.size(); i++){
        }
    }

    public void deaneryStat(){
        System.out.println("Deanery stat:");
        for(Group group: groups){
            group.groupStat();
        }
        System.out.print("______________________________________________________\n\n");
    }
}
