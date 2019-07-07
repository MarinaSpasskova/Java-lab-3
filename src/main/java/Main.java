public class Main {
    public static void main(String args[]) {
        Deanery dekanat = new Deanery();
        dekanat.addStudentsFromFile();
        dekanat.addGroupsFromFile();

        for (Student student : dekanat.studentsList) {
            int groupNumber = dekanat.randomInt(0, 2);
            dekanat.groupList.get(groupNumber).addStudent(student);
        }

        dekanat.setRandomMarks();
        dekanat.printAllInfo();

        dekanat.voteForHeadinGroups();

        for (int i = 0; i < 20; i++) {
            dekanat.studentsList.get(0).addMark(1);
        }

        dekanat.sendDownStudent(dekanat.studentsList.get(0));
        dekanat.printAllInfo();

        Group.addGroup("NewTestGroup", dekanat);
        dekanat.changeGroup(dekanat.studentsList.get(0), dekanat.groupList.get(3));
        dekanat.printAllInfo();

        System.out.println(dekanat.groupList.get(3).getAverageMark());

        dekanat.saveInfoToFile();


    }
}
