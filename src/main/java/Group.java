import java.util.ArrayList;

public class Group {
    String title;
    ArrayList<Student> students = new ArrayList<Student>();
    public Student head;

    public Group(String title) {
        this.title = title;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public boolean chooseHead(Student student) {
        if (this.students.contains(student)) {
            this.head = student;
            return true;
        }
        return false;
    }

    public Student findByFIO(String fio) {
        for (Student i : this.students) {
            if (i.fio == fio)
                return i;
        }
        return null;
    }

    public Student findByID(int id) {
        for (Student i : this.students) {
            if (i.ID == id)
                return i;
        }
        return null;
    }

    public double averageMark() {
        double sum = 0;
        for (Student i : this.students) {
            sum += i.averageMark();
        }
        return sum / this.students.size();
    }

    public boolean deleteStudent(int id) {
        for (Student i : this.students) {
            if (i.ID == id) {
                this.students.remove(i);
                return true;
            }
        }
        return false;
    }
}
