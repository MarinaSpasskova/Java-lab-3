import java.util.ArrayList;

public class Student {
    private int id;
    private String fio;
    private String group;
    private ArrayList<Integer> marks = new ArrayList<Integer>();//Byte ertuierithertiheritheritheritheruitheruit

    Student(int id, String fio, String title){
        this.id = id;
        this.fio = fio;
        group = title;
    }

    void setMarks(Integer mark){
        marks.add (mark);
    }

    int getId(){
        return id;
    }

    String getFio(){
        return fio;
    }

    String getGroup(){
        return group;
    }

    ArrayList<Integer> getMarks(){
        return marks;
    }

    int getMarks(int num){
        return marks.get(num);
    }

    double averageRating(){
        int sum = 0;
        for (int i : marks){
            sum += i;
        }
        return sum/(double)marks.size();
    }
}
