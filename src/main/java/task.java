import java.util.ArrayList;

public class task {
    public static void main(String[] args){
        deanery one = new deanery();
        one.readJson();//читаем json-файл
        one.getGroups(0).seachFIO("Иванов Николай Николаевич");//ищем Иванова из 1 группы и выводим на анель
        one.getGroups(0).seachID(106);//ищем id: 106 из первой группы и выводим на панель
        one.getGroups(0).deleteStudent("Гартунг Валерий Карлович");//отчисляем Гартнунга из 1 группы
        one.relocate(0, 1, "Терентьев Михаил Борисович");//переводим Терентьева из 1 группы во 2
        one.printStudent();//вывод на консоль всей информации: группы (со старостами и средней успеваемостью), студенты(с id средней успеваемостью)
        one.createJson();//запись результатов в json-файл


        /*deanery one = new deanery();//заполнение списка студентов и групп с "нуля"
        one.setGroups("groupe_1");
        one.getGroups(0).setHead("Аршба Отари Ионович");
        one.getGroups(0).setStudents(101,"Аршба Отари Ионович");
        one.getGroups(0).setStudents(102,"Гартунг Валерий Карлович");
        one.getGroups(0).setStudents(103,"Иванов Николай Николаевич");
        one.getGroups(0).setStudents(104,"Карелин Александр Александрович");
        one.getGroups(0).setStudents(105,"Кармазина Раиса Васильевна");
        one.getGroups(0).setStudents(106,"Кравец Александр Алексеевич");
        one.getGroups(0).setStudents(107,"Крашенинников Павел Владимирович");
        one.getGroups(0).setStudents(108,"Нилов Ярослав Евгеньевич");
        one.getGroups(0).setStudents(109,"Поздняков Владимир Георгиевич");
        one.getGroups(0).setStudents(110,"Роднина Ирина Константиновна");
        one.getGroups(0).setStudents(111,"Саралиев Шамсаил Юнусович");
        one.getGroups(0).setStudents(112,"Терентьев Михаил Борисович");

        one.setGroups("groupe_2");
        one.getGroups(1).setHead("Балыхин Григорий Артёмович");
        one.getGroups(1).setStudents(113,"Балыхин Григорий Артёмович");
        one.getGroups(1).setStudents(114,"Заварзин Виктор Михайлович");
        one.getGroups(1).setStudents(115,"Зубарев Виктор Владиславович");
        one.getGroups(1).setStudents(116,"Кулиева Василина Васильевна");
        one.getGroups(1).setStudents(117,"Курдюмов Александр Борисович");
        one.getGroups(1).setStudents(118,"Николаев Олег Алексеевич");
        one.getGroups(1).setStudents(119,"Синельщиков Юрий Петрович");
        one.getGroups(1).setStudents(120,"Шилков Данил Евгеньевич");
        one.getGroups(1).setStudents(121,"Щапов Михаил Викторович");

        one.setGroups("groupe_3");
        one.getGroups(2).setHead("Аршинова Алёна Игоревна");
        one.getGroups(2).setStudents(122,"Аршинова Алёна Игоревна");
        one.getGroups(2).setStudents(123,"Гильмутдинов Ильдар Ирекович");
        one.getGroups(2).setStudents(124,"Диденко Алексей Николаевич");
        one.getGroups(2).setStudents(125,"Духанина Любовь Николаевна");
        one.getGroups(2).setStudents(126,"Иванов Сергей Владимирович");
        one.getGroups(2).setStudents(127,"Казаков Виктор Алексеевич");
        one.getGroups(2).setStudents(128,"Корниенко Алексей Викторович");
        one.getGroups(2).setStudents(129,"Панина Елена Владимировна");
        one.getGroups(2).setStudents(130,"Рыжак Николай Иванович");
        one.getGroups(2).setStudents(131,"Тен Сергей Юрьевич");
        one.getGroups(2).setStudents(132,"Тетерин Иван Михайлович");
        one.getGroups(2).setStudents(133,"Шурчанов Валентин Сергеевич");

        one.randomMarksGenerate();
        one.createJson();*/
    }
}


