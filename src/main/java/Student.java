import java.util.ArrayList;

public class Student {
    int ID;
    String fio;
    Group group;
    ArrayList<Integer> marks = new ArrayList<Integer>();

    public Student(int ID, String fio) {
        this.ID = ID;
        this.fio = fio;
    }

    public void studentToGroup(Group group) {
        if (this.group != null) {
            this.group.students.remove(this);
        }
        this.group = group;
        group.students.add(this);
    }

    public void putMark(int mark) {
        this.marks.add(mark);
    }

    public double averageMark() {
        int sum = 0;
        for (int i : this.marks) {
            sum += i;
        }
        if (sum==0){
            return 0;
        }
        return (double) sum / this.marks.size();
    }

}

