import java.util.ArrayList;
import java.util.Random;

public class Group {
    public String title;
    public ArrayList<Student> studentsInGroup = new ArrayList<Student>();
    public Student head;

    public Group(String title) {
        this.title = title;
    }

    public void addStudent(Student newStudent) {
        studentsInGroup.add(newStudent);
        newStudent.addToGroup(this);
    }

    public void setHead() {
        if(studentsInGroup!=null) {
            Random r = new Random();
            head = studentsInGroup.get(r.nextInt(studentsInGroup.size()));
        }
    }

    public Student findStudentByFIO(String fio) {
        if(studentsInGroup!=null) {
            for (Student student : studentsInGroup) {
                if (student.getFio().equals(fio)) {
                    return student;
                }
            }
        }
        return null;
    }

    public Student findStudentByID(int id) {
        if(studentsInGroup!=null) {
            for (Student student : studentsInGroup) {
                if (student.getId() == id) {
                    return student;
                }
            }
        }
        return null;
    }

    public float averageGroupMark() {
        if(studentsInGroup!=null) {
            float sumGroupMarks = 0;
            for (Student i : studentsInGroup) {
                sumGroupMarks += i.averageMark();
            }
            return sumGroupMarks / studentsInGroup.size();
        }
        return -1;
    }

    public boolean deleteStudent(int id) {
        if (studentsInGroup != null) {
            for (Student student : studentsInGroup) {
                if (student.getId() == id) {
                    studentsInGroup.remove(student);
                    if (head != null && id == head.getId()) {
                        setHead();
                        System.out.println("Новый староста группы " + title + " это " + head.getFio());
                    }
                    return true;
                }
            }
        }
        return false;
    }
}