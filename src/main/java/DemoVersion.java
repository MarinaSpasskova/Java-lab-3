import java.sql.SQLOutput;

public class DemoVersion {
    public static void main(String[] args) {
        Deanery myDeanery = new Deanery();
        myDeanery.createStudents("students.json");
        myDeanery.createGroups("groups.json");
        myDeanery.fillingGroups();
        myDeanery.headElectionsInGroups();
        myDeanery.generateRandomStudentsMarks();

        myDeanery.printAllStudentsWithMarks();
        myDeanery.printAllGroupsData();

        myDeanery.transferStudentToOtherGroup(myDeanery.searchStudentByIdInDeanery("101"),
                myDeanery.getGroups().get(1));
        System.out.println();

        //changing head in one of the groups
        myDeanery.changeHeadInGroupById("131", "А-1");
        myDeanery.printAllGroupsData();

        myDeanery.dismissalOfStudentsBecauseOfLowMarks("3.4");
        myDeanery.printStatisticDataToConsole();

        myDeanery.studentsStatisticsToJSONFile("studentsNew.json");
        myDeanery.groupsStatisticsToJSONFile("groupsNew.json");
    }


}
