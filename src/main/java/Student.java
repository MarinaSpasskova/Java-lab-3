import java.util.ArrayList;

public class Student {
    private int id;
    private String fio;
    public Group group;
    public ArrayList<Integer> marks = new ArrayList<Integer>();

    public Student(int id, String fio) {
        this.fio = fio;
        this.id = id;
    }

    public void addToGroup(Group group) {
        this.group = group;
    }

    public void addNewMark(int newMark) {
        marks.add(newMark);
    }

    public float averageMark() {

        float sum = 0;

        for (Integer i : marks) {
            sum += i;
        }
        return sum / marks.size();
    }

    public int getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }
}