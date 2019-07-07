public class task3 {
    private static final String FILENAME1 = "deanery1.json";
    private static final String FILENAME2 = "deanery2.json";

    public static void main(String arg []){

        Deanery deanery =new Deanery();
        //заполнили деканат
        deanery.readFromFile(FILENAME1);
        //добавили оценок
        deanery.addMarksToAllStudent(10);
        //выбрали старост
        deanery.headsInAllGroupElection();
        //смотрим список студентов с оценками по группам
        deanery.showDeanery();
        //отчисляем за неуспеваимость
        deanery.deductionFromDeanari(3);
        System.out.println("");
        //смотрим список студентов с оценками по группам
        deanery.showDeanery();
        //переводим всех студентов у кого средний балл ниже 3,5 в первую группу
        //а всех у кого балл выше 3.5 во вторую
        for(Student stud : deanery.students){
            if(stud.calcAverageMark()< 3.5){
                deanery.transferToAnotherGroup(stud.getId(),"1");
            }
            if(stud.calcAverageMark()> 3.5){
                deanery.transferToAnotherGroup(stud.getId(),"2");
            }
        }
        //смотрим список студентов с оценками по группам
        deanery.showDeanery();
        //записываем в  новый файл (с той же структурой что и у исходного)
        deanery.writeToFile(FILENAME2);
    }
}