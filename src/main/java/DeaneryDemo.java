public class DeaneryDemo {
    public static void main(String[] args) {
        Deanery deanery = new Deanery();
        deanery.createGroupsAndStudents("students.json");
        deanery.addRandomMarks();
        deanery.printStudentsInGroups();
        deanery.chooseHead();
        deanery.groupStatistics();
        deanery.transferStudentToAnotherGroup(deanery.findStudentByIDInDeanery(20), deanery.findGroupByTitleInDeanery("Elementary"));
        deanery.removeStudentFromGroup(3.5);
        deanery.printToJSON("newStudentsFile.json");
    }
}
