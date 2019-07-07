package DeaneryDemo;

import java.io.File;
import java.io.IOException;


public
class DeaneryDemo {
    public static
    void main ( String[] args ) throws IOException {
        File fileStudents = new File("студенты.txt");
        File jsonStudents = new File("addStudentsTo.json");
        File finalJSON = new File("readyDeanery.json");

        Deanery deanery = new Deanery(fileStudents);
        deanery.saveArraayAsJSON(jsonStudents);
        Deanery workingDeanery = new Deanery(jsonStudents, finalJSON);
        workingDeanery.ellectHead();
        workingDeanery.marks(10);
        workingDeanery.changeGroup();
        workingDeanery.ellectHead();
        workingDeanery.deaneryStat();
        workingDeanery.printStudents();
        workingDeanery.saveArraayAsJSON(finalJSON);
    }
}
