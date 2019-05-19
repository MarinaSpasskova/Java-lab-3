public class Person {
    private String lname;
    private String fname;
    private String patronymic;

    public String getPersonName() {
        return lname + " " + fname + " " + patronymic;
    }
}
