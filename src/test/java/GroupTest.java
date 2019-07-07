import org.junit.Test;

import static org.junit.Assert.*;

public class GroupTest {

    @Test
    public void addStudentToGroup() {
        Student stud = new Student("Вася Пупкин",5);
        Group group = new Group("1");
        group.addStudentToGroup(stud);
        assertEquals(1,group.students.size());
        assertEquals(group,stud.group);
    }
    @Test
    public void removeStudentFromGroup(){
        Student stud = new Student("Вася Пупкин",5);
        Group group = new Group("1");
        group.addStudentToGroup(stud);
        group.removeStudentFromGroup(stud);
        assertEquals(null, stud.group);
        assertEquals(0,group.students.size());

        group.addStudentToGroup(stud);
        Student stud2 = new Student("Вася Пупкин",2);
        group.removeStudentFromGroup(stud2);
        assertNotSame(null, stud.group.findStudentInGroup(5));
        assertEquals(null, stud.group.findStudentInGroup(2));
    }

    @Test
    public void headElection() {
        Student stud = new Student("Вася Пупкин",5);
        Group group = new Group("1");
        group.addStudentToGroup(stud);
        group.headElection();
        assertEquals(stud,group.head);
    }
}