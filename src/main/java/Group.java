import java.util.ArrayList;

public class Group {
    private String title;
    ArrayList<Student> studentsInGroup = new ArrayList<Student>();
    private Student head;

    //Constructor:
    Group(String groupName) {
        this.title = groupName;
    }

    String getTitle(){
        return this.title;
    }

    static void addGroup(String groupName, Deanery deanery)    {
        deanery.groupList.add(new Group(groupName));
    }

    void addStudent(Student student) {
        studentsInGroup.add(student);
        student.setGroup(this);
    }

    void setHead(int id) {
        int index = findStudent(id);                  // Find student in a group
        if (index != (-1)) {
            this.head = studentsInGroup.get(index);
        }
    }

    Student getHead(){
        return this.head;
    }

    // Find by id
    int findStudent(int id) {
        try {
            for (Student student : studentsInGroup) {
                if (student.getId() == id) {
                    return studentsInGroup.indexOf(student);
                }
            }
        } catch (Exception ex) {
            System.out.println("Student not found!");
        }
        return -1;

    }

    // Find by FIO (from the beginning of the string)
    int findStudent(String fio) {
        try {
            for (Student student : studentsInGroup) {
                if (student.getFio().startsWith(fio) == true) {
                    return studentsInGroup.indexOf(student);
                }
            }
        } catch (Exception ex) {
            System.out.println("Student not found!");
        }
        return -1;

    }

    float getAverageMark() {
        int count = 0;
        float sum = 0;

        for (Student student : studentsInGroup) {
            count++;
            sum += Student.getAverageMark(student);
        }
        return (sum / (float)count);
    }

    void deleteStudentFromGroup(Student student) {
        try {
            studentsInGroup.remove(studentsInGroup.indexOf(student));
        } catch (Exception ex) {
            System.out.println("No such student!");
        }
    }
}

