package Univer;

import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;

public class Test implements Serializable{

public static void main(String[] args) throws Exception,NotSerializableException{
//    Student hiroharu = new Student("FIT","Hiroharu","h_login","qwerty","21B032312");
//    Student pakita = new Student("FIT","Pakita","p_login","pakita123","18BD231202");
//    Teacher atsushi = new Teacher(1000000,"Atsushi","a_login", "atsushi123","Professor");
//    Course neural_networks = new Course("Neural networks",5,"Atsushi","Major","1QB32", "FIT");
//    Database.getInstance().getCourses().add(neural_networks);
//    atsushi.sendApplication("add Course");
//    hiroharu.sendApplication("add Course");
//    Manager aigul = new Manager("87071232187","OR",300000,"Aigul","ai_login","aigul123");
//    aigul.getApplication();
//    aigul.getApplication();
//    pakita.sendApplication("add Course");
//    aigul.getApplication();
//    atsushi.setFull(100, hiroharu, neural_networks);
//    atsushi.setFull(10, 10, 10, pakita, neural_networks);
//    atsushi.viewGrades(neural_networks);
//    Lesson l = new Lesson(new Time(4,13), "neutral networks","Atsushi",150,FormatType.OFFLINE,LessonType.PRACTICE);
//    neural_networks.listOfLesson.add(l);
//    hiroharu.viewTimetable();
//    Database.writeData(Database.getInstance());
//    Database.deserialize(Database.getInstance());
    System.out.println(Database.readData().getUsers());
  }
}