import java.util.ArrayList;
import java.util.Random;

public class Group {
String title="";// имя группы
Student head; // староста
ArrayList<Student> students = new ArrayList<Student>();//ArrayList студентов

 Group(String title){
   this.title=title;
 }

void addStudentToGroup(Student student){
     students.add(student); //добавили в группу студента
     student.addToGroup(this);//сказали студенту в какой он группе
}

public void headElection(){
    if (students.size()==0) {
        System.out.println("в группе" +this.title+ " нет студентов");
        this.head = null;
        return ;}
    Random rand = new Random();
    head=students.get(rand.nextInt(students.size()));
}

Student findStudentInGroup(long id){
    for (Student stud : students){
        if(stud.getId()==id){return stud;}
        }
        return null;
    }

Student findStudentInGroup(String fio){
    for (Student stud : students){
        if(stud.getFio()==fio){return stud;}
        }
        return null;
    }

float calcAverageMarkInGroup(){
    if (students.size()==0) {return 0;}
    float sum=0;
    for (Student stud : students){
        sum+=stud.calcAverageMark();
        }
        return sum/students.size();
    }

 public void removeStudentFromGroup(Student stud){
     if( findStudentInGroup(stud.getId())!= null ){
         stud.group=null; //обнулили ссылку на группу у студента
         students.remove(stud);//убрали студента из листа группы
         //при попытке обратиться group.title будет исключение
         if( stud.equals(this.head) ){//если удаляем старосту, то выбираем нового
             this.headElection();
         }
     }
     else System.out.println("в группе" +this.title+ " нет студента по имени: "+stud.getFio()+"(id)"+stud.getId() );

}
}

