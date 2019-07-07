import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.Random;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;


public class Deanery {
    private ArrayList<Student> students; //массив из ссылок на студентов
    private ArrayList<Group> groups; //массив групп

    public Deanery() {
        students = new ArrayList<Student>();
        groups = new ArrayList<Group>();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void createStudents(String fileName){
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(fileName)){
            JSONObject object = (JSONObject)parser.parse(reader);
            JSONArray studentsArr = (JSONArray) object.get("students");
            Iterator studentsIterator = studentsArr.iterator();
            Long id = 0l;
            String fio = null;
            while(studentsIterator.hasNext()) {
                JSONObject st = (JSONObject) studentsIterator.next();
                id = (Long) st.get("id");
                fio = (String) st.get("fio");
                Student student = new Student (id.intValue(), fio);
                students.add(student);
            }
        }
        catch (ParseException | IOException e){
            e.printStackTrace();
        }
    }


    public void createGroups(String fileName){
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(fileName)){
            JSONObject object = (JSONObject)parser.parse(reader);
            JSONArray groupsArr = (JSONArray) object.get("groups");
            Iterator groupsIterator = groupsArr.iterator();
            String title = null;
            while(groupsIterator.hasNext()) {
                JSONObject gr = (JSONObject) groupsIterator.next();
                title = (String) gr.get("title");
                Group group = new Group (title);
                groups.add(group);
            }
        }
        catch (ParseException | IOException e){
            e.printStackTrace();
        }
    }

    public boolean fillingGroups() {
        if (students.isEmpty() || groups.isEmpty()){
            return false;
        }
        int amountOfStudentsInGroup = students.size() / groups.size();
        Iterator<Student> iterator = students.iterator();
        int i = 0;//counter students
        int j = 0;//number of group
        boolean isTail = false;
        while (iterator.hasNext()) {
            Student next = iterator.next();
            if (j >= groups.size()){
                j = 0;
                isTail = true;
            }
            if (!isTail){
                groups.get(j).addStudent(next);//add next students to the group
                i++;
                if (amountOfStudentsInGroup == i){
                    i = 0;
                    j++;
                }
            }
            else{
                groups.get(j).addStudent(next);//add next students to the group
                j++;
            }
        }
        return true;
    }


    public boolean generateRandomStudentsMarks(){
        if (students.isEmpty()){
            return false;
        }
        Random random = new Random();
        Random randomMark = new Random();
        int numberOfMarks = 0;
        for (Student student: students) {
            numberOfMarks = random.nextInt(19) + 2; //the amount of marks for each student
            for (int j = 0; j < numberOfMarks; j++) {
                int nextMark = randomMark.nextInt(4) + 2;
                student.addMark(nextMark);
            }
        }
        return true;
    }



    public void transferStudentToOtherGroup(Student student, Group group){
        if (null != student.getGroup()){
            student.getGroup().deleteStudentById(student.getId());
        }
        group.addStudent(student);
        System.out.println("Student " + student.getFio() + " was transfered to the group " +
                student.getGroup().getTitle());
    }

    //dismiss all students with average mark lower than minimal parameter
    public void dismissalOfStudentsBecauseOfLowMarks(String minAvMark){
        double minimumAverageMark = Double.parseDouble(minAvMark);
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student nextStudent = iterator.next();
            if (nextStudent.averageMark() < minimumAverageMark){
                System.out.print("Student " + nextStudent.getFio() + " was dismissed from group " +
                        nextStudent.getGroup().getTitle() + " with average mark ");
                System.out.printf("%.2f\n", nextStudent.averageMark());
                //deleting student from his group
                nextStudent.getGroup().deleteStudentById(nextStudent.getId());
                //deleting student from deanery
                iterator.remove();
            }
        }
    }

    public boolean headElectionsInGroups(){
        if (students.isEmpty() || groups.isEmpty()){
            return false;
        }
        for (Group group: groups){
            group.setHead();
            System.out.println("The elected head of " + group.getTitle() + " is " + group.getHead().getFio());
        }
        return true;
    }

    public boolean changeHeadInGroupById(String id, String groupTitle){
        if (!students.contains(this.searchStudentByIdInDeanery(id))){
            System.out.println("Wrong student's ID");
            return false;
        }
        if (!groups.contains(this.searchGroupByTitleInDeanery(groupTitle))){
            System.out.println("Wrong group's title");
            return false;
        }
        if (this.searchGroupByTitleInDeanery(groupTitle).getStudents().isEmpty()) {
            System.out.println("The group is empty");
            return false;
        }
        if (!this.searchStudentByIdInDeanery(id).getGroup().getTitle().equals(groupTitle)){
            System.out.println("There is no such student in this group");
            return false;
        }
        Group group = this.searchGroupByTitleInDeanery(groupTitle);
        Student newHead = this.searchStudentByIdInDeanery(id);
        group.setHead(newHead);
        return true;

    }

    //accessory methods
    public Student searchStudentByIdInDeanery(String studentsId){
        int id = Integer.parseInt(studentsId);
        for(Student student: students){
            if (student.getId() == id){
                return student;
            }
        }
        return null;
    }

    public Group searchGroupByTitleInDeanery(String title){
        for(Group group: groups){
            if (group.getTitle().equals(title)){
                return group;
            }
        }
        return null;
    }

    public void printAllStudentsData(){
        for (Student student: students){
            student.printStudentData();
        }
    }

    public void printAllStudentsWithMarks(){
        for (Student student: students){
            student.printStudentData();
            student.printMarksForStudent();
        }
    }

    public void printAllGroupsData(){
        for (Group group: groups){
            group.printGroupData();
        }
    }

    public void printStatisticDataToConsole(){
        for (Group group: groups){
            group.printGroupData();
            group.printAverageMarkForGroup();
            group.printAverageMarkForEachStudentInGroup();
        }
    }

    public boolean studentsStatisticsToJSONFile(String studentsFileName){
        if (students.isEmpty() || groups.isEmpty()){
            return false;
        }
        JSONObject object = new JSONObject();
        JSONArray studs = new JSONArray();
        for (Student student: students){
            JSONObject st = new JSONObject(); //create object for each student
            st.put("id", student.getId());
            st.put("fio", student.getFio());
            st.put("group", student.getGroup().getTitle());
            st.put("markAv",  String.format("%.2f", student.averageMark()));
            JSONArray marks = new JSONArray();
            marks.addAll(student.getMarks());
            st.put("marks", marks);

            studs.add(st);
        }
        object.put("students", studs);

        try (FileWriter writer = new FileWriter(studentsFileName)){
            writer.write(object.toJSONString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean groupsStatisticsToJSONFile(String groupsFileName){
        if (students.isEmpty() || groups.isEmpty()){
            return false;
        }
        JSONObject object = new JSONObject();
        JSONArray groupsArray = new JSONArray();
        for (Group group: groups){
            JSONObject gr = new JSONObject(); //create object for each group
            gr.put("Title", group.getTitle());
            if (null == group.getHead()){
                gr.put("Head", "not elected yet");
            }
            else{
                gr.put("Head", group.getHead().getFio());
            }
            gr.put("Amount", group.getStudents().size());  //the amount of students in group
            gr.put("markAv",  String.format("%.2f", group.averageMarkInGroup()));
            groupsArray.add(gr);
        }
        object.put("students", groupsArray);

        try (FileWriter writer = new FileWriter(groupsFileName)){
            writer.write(object.toJSONString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
/*
    +создание студентов на основе данных из файла
     +   создание групп на основе данных из файла
      +  добавление случайных оценок студентам
        + накопление статистики по успеваемости студентов и групп
       + перевод студентов из группы в группу
        + отчисление студентов за неуспеваемость
        сохранение обновленных данных в файлах
       + инициация выборов старост в группах
       + вывод данных на консоль
*/