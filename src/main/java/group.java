import java.util.ArrayList;

public class group {
    private String title;
    private String head;
    private ArrayList<Student> students = new  ArrayList<Student>();

    group (String title){
        this.title = title;
    }

    void setHead(String head){
        this.head = head;
    }

    String getHead(){
        return head;
    }

    void setStudents(int id, String fio){
        students.add(new Student(id, fio, title));
    }

    Student getStudents(int num){
        return students.get(num);
    }
    ArrayList<Student> getStudents(){
        return students;
    }

    String getTitle(){
        return title;
    }

    void deleteStudent(String FIO){
        for (int i = 0; i < students.size(); i++){
            if (getStudents(i).getFio().equals(FIO)){
                students.remove(i);
                break;
            }
        }
    }

    double averageRatingGroup(){
        double sum = 0;
        for (Student i : students){
            sum += i.averageRating();
        }
        return sum/students.size();
    }

    int seachFIO(String FIO){
        for (Student i: students){
            if (i.getFio().equals(FIO)){
                System.out.println(i.getId() +" "+ i.getFio()+" "+i.getMarks()+" "+i.averageRating());
                return i.getId();
            }
        }
        return -1;
    }

    String seachID(int ID){
        for (Student i: students){
            if (i.getId()==ID){
                System.out.println(i.getId() +" "+ i.getFio()+" "+i.getMarks()+" "+i.averageRating());
                return i.getFio();
            }
        }
        return "not found";
    }
}


