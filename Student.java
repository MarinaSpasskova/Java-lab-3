package DeaneryDemo;

import java.util.ArrayList;

class Student {
    private int ID;
    private String FIO;
    private Group group;
    private String groupS;
    private ArrayList<Integer>marks;
    private int markCounter;
    private double averageMark;


    public
    Student ( int ID, String FIO, Group group ) {
        this.ID = ID;
        this.FIO = FIO;
        this.group = group;
        this.marks = new ArrayList<Integer>();
        this.markCounter = 0;
        this.averageMark = 0;
    }

    public
    String getGroupS () {
        return groupS;
    }

    public
    void setID ( int ID ) {
        this.ID = ID;
    }

    public
    Student ( String FIO ) {
        this.FIO = FIO;
        this.group = group;
        this.marks = new ArrayList<Integer>();
        this.markCounter = 0;
        this.averageMark = 0;
    }
    public void printStudent (){
        System.out.println("ID: " + ID + " || FIO: " + FIO + " || Group: " + group.getTitle());
        System.out.print("Marks: ");
        for(int i: marks){
            System.out.print(i + "\t");
        }
        System.out.println("Average mark: " + getAverageMark() + "\n");
    }

    private double fAverageMark ( ArrayList<Integer> marks){
        int num = 0;
        for(int i = 0; i < marks.size(); i++){
            num += marks.get(i);
        }
        return (double) num / marks.size();
    }

    public
    void addMark ( int mark ) {
        marks.add(mark);
        markCounter++;
        this.averageMark = fAverageMark(this.marks);
    }


    public
    void setGroup ( Group group ) {
        this.group = group;
    }

    public
    void setGroupEnum ( Group group ) {
        this.group = group;
    }

    public
    Group getGroup () {
        return group;
    }

    public
    String getFIO () {
        return FIO;
    }

    public
    int getID () {
        return ID;
    }

    public
    void setAverageMark ( ArrayList <Integer> marks ) {
        this.averageMark = fAverageMark(marks);
    }

    public
    ArrayList<Integer> getMarks () {
        return marks;
    }

    public
    double getAverageMark () {
        return averageMark;
    }



}
