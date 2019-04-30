package Test3.Decanat;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Deanery {
	static ArrayList<Student> studentsDeanery = new ArrayList<Student>();
	static int count = 0;
	static String fio = "";
	static String id = "";
	static Student first;

	static ArrayList<Group> groupsInDec = new ArrayList<Group>();
	static Group group;

	public static void writeToXMLusingJDOM(ArrayList<Student> newstudentsDeanery) {
		try {
			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = documentBuilder.parse("test.xml");
			// addNewStudent(document);
			saveToDokument(document, newstudentsDeanery);

		} catch (ParserConfigurationException ex) {
			ex.printStackTrace(System.out);
		} catch (SAXException ex) {
			ex.printStackTrace(System.out);
		} catch (IOException ex) {
			ex.printStackTrace(System.out);
		}
	}

	public static void addNewStudent(Document document, String groupName, String studentName, String studentId)
			throws TransformerFactoryConfigurationError, DOMException {
		Node root = document.getDocumentElement();
		Element group = document.createElement("Group");
		group.setAttribute("name", groupName);
		Element student = document.createElement("Student");

		Element fio = document.createElement("Fio");
		fio.setTextContent(studentName);

		Element id = document.createElement("ID");
		id.setTextContent(studentId);

		group.appendChild(student);
		student.appendChild(fio);
		student.appendChild(id);
		root.appendChild(group);
		writeDocument(document);
	}

	public static void writeDocument(Document document) {
		try {
			Transformer tr = TransformerFactory.newInstance().newTransformer();
			DOMSource source = new DOMSource(document);
			FileOutputStream fos = new FileOutputStream("test.xml");
			StreamResult result = new StreamResult(fos);
			tr.transform(source, result);
		} catch (TransformerException | IOException e) {
			e.printStackTrace(System.out);
		}
	}

	public static void saveToDokument(Document document, ArrayList<Student> newstudentsDeanery)
			throws TransformerFactoryConfigurationError, DOMException {
		Node root = document.getDocumentElement();
		for (Group i : groupsInDec) {
			String groupName = i.getTitle();
			// ArrayList<Student> studentsInGroup = i.getStudents();
			Element group = document.createElement("Group");
			group.setAttribute("name", groupName);

			for (Student j : newstudentsDeanery) {
				if (j.getGroupByGroup().equals(i)) {
					Element student = document.createElement("Student");

					Element fio = document.createElement("Fio");
					String fullName = j.getFio();
					fio.setTextContent(fullName);

					Element id = document.createElement("ID");
					String identifical = j.getId();
					id.setTextContent(identifical);

					group.appendChild(student);
					student.appendChild(fio);
					student.appendChild(id);
					root.appendChild(group);
					writeDocument(document);
				}
			}
		}
	}

	public static void statistic() {
		for (Group i : groupsInDec) {
			System.out.println("Group title: " + i.getTitle());
			System.out.println("Group average mark: " + i.avarageMarkForGroup());
			System.out.println("Group head: " + i.getHead());
		}
	}

	public static void findHead() {
		Student headGroup = null;
		for (Group i : groupsInDec) {
			headGroup = i.getStudents().get(0);
			i.setHead(headGroup);
		}
	}

	public static ArrayList<Student> removeStudFromDecanat() {
		ArrayList<Student> removedStudent = new ArrayList<Student>();
		Student man = new Student(null, null);
		for (Student i : studentsDeanery) {
			if (i.averageMark() < 2.5) {
				System.out.println(
						"Bed students removed from decanat: " + i.getFio() + " with average mark " + i.averageMark());
				man = i;
			}
			removedStudent.add(man);
		}
		for (Student i : removedStudent) {
			studentsDeanery.remove(i);
		}
		return studentsDeanery;
	}

	public static void randMarks() {
		for (Student stud : studentsDeanery) {
			for (int i = 0; i < 3; i++) {
				int a = 1;
				int b = 5;
				stud.setMarks(a + (int) (Math.random() * b));

			}
		}
	}

	public static void changeGroupForStudent(Student stud, Group newGroup) {
		Group oldGroup = stud.getGroupByGroup();
		oldGroup.deleteStudentFromGroup(stud);
		newGroup.addStudentToGroup(stud);
	}

	public static void createStudentsFromFile() {
		try {
			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = documentBuilder.parse("Property.xml");
			Node deanery = document.getDocumentElement();
			System.out.println("List of students in groups:");
			System.out.println(deanery.getNodeName());
			NodeList deaneryList = deanery.getChildNodes();
			for (int i = 0; i < deaneryList.getLength(); i++) {
				Node dec = deaneryList.item(i);
				if (dec instanceof Element && dec.hasChildNodes()) {
					System.out.println();
					group = new Group(dec.getAttributes().getNamedItem("name").getNodeValue().toString());
					groupsInDec.add(group);
					NodeList decList = dec.getChildNodes();
					for (int j = 0; j < decList.getLength(); j++) {
						Node student = decList.item(j);
						if (student instanceof Element && student.hasChildNodes()) {
							NodeList studentList = student.getChildNodes();
							for (int k = 0; k < studentList.getLength(); k++) {
								Node prop = studentList.item(k);
								if (prop.hasChildNodes()) {
									if (prop.getNodeName().equals("Fio")) {
										fio = prop.getFirstChild().getNodeValue();
										count++;
									} else if (prop.getNodeName().equals("ID")) {
										id = prop.getFirstChild().getNodeValue();
										count++;
									}
									if (count == 2) {
										first = new Student(id, fio);
										group.addStudentToGroup(first);
										studentsDeanery.add(first);
										count = 0;
									}

								}
							}
						}
					}
				}

			}
		} catch (ParserConfigurationException ex) {
			ex.printStackTrace(System.out);
		} catch (SAXException ex) {
			ex.printStackTrace(System.out);
		} catch (IOException ex) {
			ex.printStackTrace(System.out);
		}
	}

	public static void main(String[] args) {

		createStudentsFromFile();
		randMarks();
		removeStudFromDecanat();
		writeToXMLusingJDOM(studentsDeanery);
	}

}
