import java.util.ArrayList;
public class Student {

    private String id; // идентификационный номер
    private String fio; // фамилия и инициалы
    Group group; // ссылка на группу (объект Group)
    private ArrayList<Integer> marks = new ArrayList<Integer>(); // контейнер оценок (ArrayList)

    public Student(String id, String fio) {
        this.id = id;
        this.fio = fio;
    }

    public String getGroupTitle() {
        return group.getTitle();
    }

    public Group getGroupByGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setMarks(int mark) {
        this.marks.add(mark);
    }

    public ArrayList<Integer> getMarks() {
        return marks;
    }

    public Double averageMark() {
        Double averageMark;
        double all = 0;
        for (Integer mark : this.marks) {
            all += mark;
        }
        averageMark = all / this.marks.size();
        return averageMark;

    }

    public String getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }
}
