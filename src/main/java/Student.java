import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private int mId;
    private String mName;
    private transient Group mGroup;
    private List<Mark> mMarksList = new ArrayList<>();

    @Override
    public String toString() {
        return "Students ID = " + mId + "\n" + "Name = " + mName + "\n" + "Marks = " + mMarksList.toString();
    }

    public Student(int id, String name) {
        this.mId = id;
        this.mName = name;
    }

    public static Student createStudent(int id, String name){
        return new Student(id, name);
    }

    public void addMark(Mark mark){
        mMarksList.add(mark);
    }

    public double calculateAverageScore(){
        double sum = 0.0;
        int count = 0;
        for(Mark mark : mMarksList){
            sum += mark.getScore();
            count++;
        }
        return sum / count;
    }

    public void assignToGroup(Group group){
        mGroup = group;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public Group getGroup() {
        return mGroup;
    }

    public List<Mark> getMarks() {
        return mMarksList;
    }

    public void setMarks(List<Mark> marks) {
        this.mMarksList = marks;
    }
}
