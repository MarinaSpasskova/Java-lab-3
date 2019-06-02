import org.junit.Test;

import static org.junit.Assert.*;

public class GroupTest {

    @Test
    public void addStudent() {
        Group group=new Group("First");
        Student student=new Student(12,"Petia Pupkin");
        group.addStudent(student);
        assertEquals(group,student.group);
    }

    @Test
    public void setHead() {
        Group group=new Group("First");
        Student student=new Student(12,"Petia Pupkin");
        group.addStudent(student);
        group.setHead();
        assertEquals(student,group.head);
    }

    @Test
    public void findStudentByFIO() {
        Group group=new Group("First");
        Student student=new Student(12,"Petia Pupkin");
        group.addStudent(student);
        Student student2=new Student(13,"Vasya Petrov");
        group.addStudent(student2);
        assertEquals("Petia Pupkin",group.findStudentByFIO("Petia Pupkin").getFio());
        assertEquals("Vasya Petrov",group.findStudentByFIO("Vasya Petrov").getFio());
    }

    @Test
    public void findStudentByID() {
        Group group=new Group("First");
        Student student=new Student(12,"Petia Pupkin");
        group.addStudent(student);
        Student student2=new Student(13,"Vasya Petrov");
        group.addStudent(student2);
        assertEquals(12,group.findStudentByID(12).getId());
        assertEquals(13,group.findStudentByID(13).getId());
    }

    @Test
    public void averageGroupMark() {
        Group group=new Group("First");
        Student student=new Student(12,"Petia Pupkin");
        group.addStudent(student);
        Student student2=new Student(13,"Vasya Petrov");
        group.addStudent(student2);
        student.addNewMark(5);
        student2.addNewMark(2);
        assertEquals(3.5,group.averageGroupMark(),0.01);
    }

    @Test
    public void deleteStudent() {
        Group group=new Group("First");
        Student student=new Student(12,"Petia Pupkin");
        group.addStudent(student);
        Student student2=new Student(13,"Vasya Petrov");
        group.addStudent(student2);
        group.deleteStudent(12);
        assertEquals(1,group.studentsInGroup.size());
    }
}