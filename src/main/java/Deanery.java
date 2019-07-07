import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Deanery {
    public ArrayList<Student> allStudents = new ArrayList<Student>();
    public ArrayList<Group> groups = new ArrayList<Group>();

    public void createGroupsAndStudents(String fileName) {
        try {
            File f = new File(fileName);
            JSONParser parser = new JSONParser();
            FileReader fr = new FileReader(f);

            Object obj = parser.parse(fr);
            JSONArray gr = (JSONArray) obj;

            for (Object i : gr) {
                Group group = new Group((String) ((JSONObject) i).get("title"));
                groups.add(group);
                JSONArray st = (JSONArray) ((JSONObject) i).get("students");
                for (Object j : st) {
                    Object idStr = ((JSONObject) j).get("id");
                    Integer id = Integer.parseInt(idStr.toString());
                    String fio = (String) ((JSONObject) j).get("fio");
                    Student student = new Student(id, fio);
                    group.addStudent(student);
                    allStudents.add(student);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void addRandomMarks() {
        if (allStudents != null) {
            int randomMark;
            for (int i = 0; i < 10; i++) {  //the amount of marks for each student
                for (Student student : allStudents) {
                    Random r = new Random();
                    randomMark = r.nextInt(4) + 2;
                    student.addNewMark(randomMark);
                }
            }
        }
    }

    public void printStudentsInGroups() {
        if (groups != null) {
            for (Group group : groups) {
                System.out.println("Group " + group.title + " consists of:");
                if (group.studentsInGroup != null) {
                    for (int k = 0, count = 1; k < group.studentsInGroup.size(); k++, count++) {
                        System.out.println(count + ". ID:" + group.studentsInGroup.get(k).getId());
                        System.out.println("    FIO: " + group.studentsInGroup.get(k).getFio());
                        System.out.println("    Average mark: " + group.studentsInGroup.get(k).averageMark());
                        System.out.println("    All marks: " + group.studentsInGroup.get(k).marks);
                    }
                    System.out.println();
                }
            }
        }
    }

    public void chooseHead() {
        if (groups != null) {
            for (Group group : groups) {
                group.setHead();
                System.out.println("Староста группы " + group.title + ": " + group.head.getFio() + ", ID: " + group.head.getId());
            }
            System.out.println();
        }
    }

    public void groupStatistics() {
        if (groups != null) {
            for (Group group : groups) {
                group.averageGroupMark();
                System.out.println("Средняя оценка в группе " + group.title + ": " + group.averageGroupMark());
            }
            System.out.println();
        }
    }

    public void studentStatistics() {
        if (allStudents != null) {
            for (Student student : allStudents) {
                student.averageMark();
            }
        }
    }

    public void removeStudentFromGroup(double mark) {
        if (allStudents != null) {
            Iterator<Student> studentIterator = allStudents.iterator();                //create iterator
            while (studentIterator.hasNext()) {                //while we have elements in our array list
                Student nextStudent = studentIterator.next();          //get next element
                if (nextStudent.averageMark() < mark) {
                    nextStudent.group.deleteStudent(nextStudent.getId()); //delete student from his group
                    studentIterator.remove();
                    System.out.println("Студент " + nextStudent.getFio() + ", ID: "
                            + nextStudent.getId() + " отчислен за неуспеваемость! Средний балл: " + nextStudent.averageMark());
                }
            }
        }
    }


    public void transferStudentToAnotherGroup(Student student, Group group) {
            student.group.deleteStudent(student.getId());
            student.addToGroup(group);
            System.out.println("Студент " + student.getFio() + " переведен в группу " + group.title);
            System.out.println();
    }

    public Student findStudentByFIOInDeanery(String fio) {
        if (allStudents != null) {
            for (Student student : allStudents) {
                if (student.getFio().equals(fio)) {
                    return student;
                }
            }
        }
        return null;
    }

    public Student findStudentByIDInDeanery(int id) {
        if (allStudents != null) {
            for (Student student : allStudents) {
                if (student.getId() == id) {
                    return student;
                }
            }
        }
        return null;
    }

    public Group findGroupByTitleInDeanery(String title) {
        if (groups != null) {
            for (Group group : groups) {
                if (group.title.equals(title)) {
                    return group;
                }
            }
        }
        return null;
    }

    public void printToJSON(String newFileName) {
        if (groups != null) {
            JSONObject object = new JSONObject();
            JSONArray groupsInJson = new JSONArray();
            for (Group group : groups) {
                JSONObject gr = new JSONObject();  //create object for each group
                gr.put("title", group.title);
                JSONObject headOfGroup = new JSONObject();
                headOfGroup.put("id", group.head.getId());
                headOfGroup.put("fio", group.head.getFio());
                gr.put("head", headOfGroup);
                gr.put("group average mark", group.averageGroupMark());
                gr.put("amount of students", group.studentsInGroup.size());
                JSONArray studentsInJson = new JSONArray();
                if (group.studentsInGroup != null) {
                    for (Student student : group.studentsInGroup) {
                        JSONObject st = new JSONObject();  //create object for each student
                        st.put("id", student.getId());
                        st.put("fio", student.getFio());
                        st.put("average mark", student.averageMark());
                        JSONArray marks = new JSONArray();
                        marks.addAll(student.marks);
                        st.put("marks", marks);
                        studentsInJson.add(st);
                    }
                    gr.put("students", studentsInJson);
                    groupsInJson.add(gr);
                }
            }
            object.put("Groups", groupsInJson);

            try (FileWriter writer = new FileWriter(newFileName)) {
                writer.write(object.toJSONString());
                writer.flush();
                writer.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
}