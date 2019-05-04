package DeaneryDemo;

import java.util.ArrayList;

class Group {
    private String Title;
    private ArrayList<Student> Students;
    private int studentCount;
    private Student head;
    private double groupAvarageMark;

    public
    Group ( String title ) {
        this.Title = title;
        this.Students = new ArrayList<Student>();
        this.studentCount = 0;
        this.head = new Student("");
        this.groupAvarageMark = 0;
    }

    public
    Student getHead () {
        return head;
    }

    public
    ArrayList<Student> getStudents () {
        return Students;
    }

    public
    String getTitle () {
        return Title;
    }

    public
    int getStudentCount () {
        return studentCount;
    }

    public
    double getGroupAvarageMark () {
        return groupAvarageMark;
    }

    public
    void setHead ( Student head ) {
        this.head = head;
    }

    public void addStudents ( Student student){
        this.Students.add(student);
        this.studentCount = Students.size();
    }

    public void headEllect (){
        double averageMark = 0;
        for(int i = 0; i < this.Students.size(); i++){
            if(this.Students.get(i).getAverageMark() >= averageMark){
                this.head = this.Students.get(i);
                averageMark = this.Students.get(i).getAverageMark();
            }
        }
    }

    public boolean SearchStudent(String FIO) {
        for (Student name : Students) {
            if (FIO.equals(name.getFIO())) {
                return true;
            }
        }
        return false;
    }

    public boolean SearchStudent(int id){
        for(Student name: Students){
            if(id == name.getID()){
                return true;
            }
        }
        return false;
    }

    public void CalculateGroupAvarageMark(){
        double sumAllMarks = 0;
        for(int  i = 0; i < this.Students.size(); i++){
            sumAllMarks += this.Students.get(i).getAverageMark();
        }
        this.groupAvarageMark = sumAllMarks / this.studentCount;
    }

    public void DeleteStudents (Student student){
        Students.remove(student);
        this.studentCount--;
        if(student==this.head){
            headEllect();
            student.setGroup(null);
        }
    }

    public void groupStat(){
        System.out.println(getTitle() + " || " + "HEAD: " + getHead().getFIO() + " || Quantity of students:" + getStudentCount() +
                " || Average mark:" + getGroupAvarageMark());
    }


}
