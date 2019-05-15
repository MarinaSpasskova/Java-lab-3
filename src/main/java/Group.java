import java.util.ArrayList;
import java.util.Random;

class Group {
    private String title; //название группы
    private ArrayList<Student> students; //массив из ссылок на студентов
    private Student head; //ссылка на старосту (из членов группы)

    public Group(String title) {
        this.title = title;
        this.students = new ArrayList<Student>();
    }

    public String getTitle() {
        return title;
    }

    public Student getHead() {
        return head;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student){
        students.add(student);
        student.setGroup(this);
    }

    //variant of head election for appointmet of the particular student as a head
    void setHead(Student head) {
        if (!students.isEmpty() && students.contains(head)){
            this.head = head;
            System.out.println("The new elected head of " + title + " is " + this.head.getFio());
        }
    }

    //variant of head election for appointment random student as a head
    void setHead() {
        if (!students.isEmpty()){
            Random random = new Random();
            int indexOfHead = random.nextInt(this.students.size());
            head = students.get(indexOfHead);
        }
    }

    public Student searchById(int id){
        for(Student student: students){
            if (student.getId() == id){
                return student;
            }
        }
        return null;
    }

    public Student searchByFio(String fio){
        for(Student student: students){
            if (student.getFio().equals(fio)){
                return student;
            }
        }
        return null;
    }

    public Double averageMarkInGroup() {
        Double sum = 0.0;
        for(Student student: students){
            sum += student.averageMark();
        }
        return sum / students.size();
    }


    void deleteStudentById(int id) {
        Student student = searchById(id);
        if (student != null){
            if ((head != null) && (id == head.getId())){
                students.remove(student);
                this.setHead();
                System.out.println("The new elected head of " + title + " is " + head.getFio());
            }
            else{
                students.remove(student);
            }
            //System.out.println("The student " + student.getFio() + " was deleted from group " + title);
        }
    }

    void printGroupData(){
        String head;
        if (null == this.head){
            head = "not elected yet.";
        }
        else{
            head = this.head.getFio();
        }
        System.out.println();
        System.out.println(title + ", amount of students is " + students.size() + ", head is " + head);
    }

    void printStudentsInGroup(){
        if (!students.isEmpty()){
            for (Student student: students){
                student.printStudentData();
            }
        }
    }

    void printStudentsInGroupWithMarks(){
        if (!students.isEmpty()){
            for (Student student: students){
                student.printStudentData();
                student.printMarksForStudent();
            }
            System.out.println();
        }
    }

    void printAverageMarkForGroup(){
        if (!students.isEmpty()){
            System.out.printf("Average mark in group is %.2f\n", averageMarkInGroup());
            System.out.println();
        }
    }

    void printAverageMarkForEachStudentInGroup(){
        if (!students.isEmpty()){
            for (Student student: students){
                student.printStudentData();
                student.printAverageMarkForStudent();
            }
            System.out.println();
        }
    }
}

