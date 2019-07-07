import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String mTitle;
    private List<Student> mStudentsList = new ArrayList<>();
    private Student mHead;

    public Group(String title){
        this.mTitle = title;
    }

    public static Group createGroup(String title){
        return new Group(title);
    }

    public void addStudent(Student student){
        if(mStudentsList.contains(student)) return;
        mStudentsList.add(student);
        student.assignToGroup(this);
    }

    public boolean chooseHead(Student head){
        if(mStudentsList.contains(head)){
            mHead = head;
            return true;
        } else return false;
    }

    public Student findStudent(String name){
        for(Student student : mStudentsList){
            if(student.getName().equals(name)) return student;
        }
        return null;
    }

    public Student findStudent(int id){
        for(Student student : mStudentsList){
            if(student.getId() == id) return student;
        }
        return null;
    }

    public double calculateGroupAverageScore(){
        double sum = 0.0;
        for(Student student : mStudentsList){
            sum += student.calculateAverageScore();
        }
        return sum / mStudentsList.size();
    }

    public void deleteStudent(Student student){
        mStudentsList.remove(student);
        ((ArrayList<Student>) mStudentsList).trimToSize();
        student.assignToGroup(null);
    }

    public Student findBestStudent(){
        Student bestStudent = null;
        double bestScore = 0.0;
        for(Student student : mStudentsList){
            double currentScore = student.calculateAverageScore();
            if(currentScore > bestScore){
                bestStudent = student;
                bestScore = currentScore;
            }
        }
        return bestStudent;
    }

    @Override
    public String toString() {
        return "Group Title = " + mTitle + "\n" + "Students = " + mStudentsList.toString();
    }

    public List<Student> getStudents() {
        return mStudentsList;
    }

    public void setStudents(List<Student> students) {
        mStudentsList = students;
    }

}
