import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Deanery {
    public static final double THRESHOLD = 3.0;
    private static final String STUDENTS_PATH = "Students.txt";
    private static final String GROUPS_PATH = "Groups.txt";
    private ArrayList<Student> mStudents = new ArrayList<>();
    private ArrayList<Group> mGroups = new ArrayList<>();
    private Gson mGson;

    public Deanery(){
        mGson = new Gson();
    }

    public void removeOutsiders(){
        for(Student student : mStudents){
            if(student.calculateAverageScore() < THRESHOLD){
                student.getGroup().deleteStudent(student);
            }
        }
    }

    public void moveStudentToGroup(Student student, Group newGroup){
        Group group = student.getGroup();
        if (group != null && group.getStudents().contains(student)) group.deleteStudent(student);
        newGroup.addStudent(student);
    }

    public void assignHeads(){
        for (Group group : mGroups){
            Student student = group.findBestStudent();
            if(student != null) group.chooseHead(student);
        }
    }

    public void printGroups(){
        System.out.println(mGroups.toString());
    }

    public void printStudents(){
        System.out.println(mStudents.toString());
    }

    public void addRandomMark(Student student){
        student.addMark(new Mark((int) (Math.random() * 5)));
    }

    public void loadAll(){
        String textStudents = ReaderUtils.readTextFromFile(STUDENTS_PATH);
        Type typeStudents = new TypeToken<ArrayList<Student>>(){}.getType();
        mStudents = mGson.fromJson(textStudents, typeStudents);

        String textGroups = ReaderUtils.readTextFromFile(GROUPS_PATH);
        Type typeGroups = new TypeToken<ArrayList<Group>>(){}.getType();
        mGroups = mGson.fromJson(textGroups, typeGroups);

        for (Group group : mGroups) {
            for (Student student : group.getStudents()) {
                student.assignToGroup(group);
                mStudents.clear();
                mStudents.add(student);
            }
        }
    }

    public void saveAll(){
        Type typeStudents = new TypeToken<ArrayList<Student>>(){}.getType();
        String students = mGson.toJson(mStudents, typeStudents);
        ReaderUtils.writeTextToFile(STUDENTS_PATH, students);

        Type typeGroups = new TypeToken<ArrayList<Group>>(){}.getType();
        String groups = mGson.toJson(mGroups, typeGroups);
        ReaderUtils.writeTextToFile(GROUPS_PATH, groups);
    }

    public void addStudent(Student student){
        mStudents.add(student);
    }

    public void addGroup(Group group){
        mGroups.add(group);
    }

    public void deleteStudent(Student student){
        student.getGroup().deleteStudent(student);
        mStudents.remove(student);
    }

    public void deleteGroup(Group group){
        for(Student student : group.getStudents()){
            student.assignToGroup(null);
        }
        mGroups.remove(group);
    }

    public List<Group> getGroups(){
        return mGroups;
    }

    public void setGroups(ArrayList<Group> groups){
        mGroups = groups;
    }

    public void setStudents(ArrayList<Student> students){
        mStudents = students;
    }
}
