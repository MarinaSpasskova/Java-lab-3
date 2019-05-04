package DeaneryDemo;

import org.junit.Test;

import static org.junit.Assert.*;

public
class StudentTest {

    @Test
    public
    void addMark () {
        Student test = new Student("Иванов Иван Иванович");
        test.addMark(5);
        assertEquals( 5, (int)test.getMarks().get(0));
        test.addMark(2);
        test.addMark(3);
        assertEquals(3, test.getMarks().size());

    }

    @Test
    public
    void setAverageMark () {
        Student test = new Student("Иванов Иван Иванович");
        for(int i = 0; i < 5; i++){
            test.addMark(4);
        }
        assertEquals( 4.0, test.getAverageMark(), 0.01);
    }
}