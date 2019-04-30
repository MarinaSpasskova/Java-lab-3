package Test3.Decanat;

import java.util.ArrayList;

public class Group {

	String title; // название группы
	protected ArrayList<Student> students = new ArrayList<Student>(); // массив из ссылок на студентов
	Student head; // ссылка на старосту (из членов группы)

	public Group(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void addStudentToGroup(Student student) {
		this.students.add(student);
		student.setGroup(this);
	}

	public String getHead() {
		return head.getFio();
	}

	public void setHead(Student student) {
		this.head = student;
	}

	public Student findStudentById(String id) {
		for (Student i : this.students) {
			if (i.getId() == id) {
				return i;
			}
		}
		return null;
	}

	public Student findStudentByFio(String fio) {
		for (Student i : this.students) {
			if (i.getFio().equals(fio)) {
				return i;
			}
		}
		return null;
	}

	public double avarageMarkForGroup() {
		double avarageMarkForGroup = 0;
		for (Student i : this.students) {
			avarageMarkForGroup += i.averageMark();
		}
		return avarageMarkForGroup / this.students.size();
	}

	public ArrayList<Student> deleteStudentFromGroup(Student student) {
		this.students.remove(student);
		return students;
	}

}
