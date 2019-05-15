import java.util.ArrayList;

class Student {
    private int id; //идентификационный номер
    private String fio; //фамилия и инициалы
    private Group group; //сcылка на группу (объект Group)
    private ArrayList<Integer> marks; //контейнер оценок (ArrayList).

    public Student(int id, String fio) {
        this.id = id;
        this.fio = fio;
        this.marks = new ArrayList<Integer>();
    }

    public int getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public ArrayList<Integer> getMarks() {
        return marks;
    }

    void printStudentData(){
        System.out.println(id + " " + fio + " group " + group.getTitle());
    }


    void printMarksForStudent(){
        for (Integer mark: marks){
            System.out.print(mark + " ");
        }
        System.out.println();
    }

    void printAverageMarkForStudent(){
        System.out.printf("Average mark is %.2f\n", averageMark());
    }

    public Group getGroup() {
        return group;
    }

    void setGroup(Group group) {
        this.group = group;
    }

    void addMark(Integer mark){
        if ((mark >= 0) && (mark <= 5)) {
            marks.add(mark);
        }
    }

    Double averageMark(){
        Double sum = 0.0;
        for(Integer mark: marks){
            sum += mark;
        }
        return sum / marks.size();
    }
}

