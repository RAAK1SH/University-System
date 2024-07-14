package Univer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;
import java.util.Map.Entry;

/**
 */
public class Librarian extends Employee implements Info, Serializable{
	static HashMap<Book,Integer> availableBooks = new HashMap<Book,Integer>();
    public Librarian(int salary, String name, String login,String password) {
		super(salary, name, login,password);
	}

	/**
     */
    @Override
	public void viewTeacherInfo(String teacherName, Course cr) {
		@SuppressWarnings("unchecked")
		Vector<User> temp = (Vector<User>) Database.getInstance().getUsers().keySet().stream().filter(user -> user.name.equals(teacherName));
		for(User users : temp) {
			Teacher t = (Teacher) users; 
			if(t.courses.contains(cr)) {
				System.out.println(t);
				return;
			}
		}
	}
    public static void receiveBook(Book b) {
    	if(availableBooks.get(b) == null) {
    		availableBooks.put(b,1);
    	}
    	else {
    		availableBooks.put(b, availableBooks.get(b) + 1);
    	}
    }
	public void viewStudentInfo(Student st, Course cr) {
        for(Course course : Database.getInstance().getCourses()) {
          if(!course.equals(cr)) continue;  
          for(Lesson lesson : course.getListOfLesson()) {
            for(Entry<Student, Mark> student : lesson.getAttendanceList().entrySet()) {
            	if(student.getKey().equals(st)) {
	              System.out.println("\t"+student.getKey());
	              return;
	              }
            }
          }
        }
      }
    /**
     */
    public void checkBook(User u,Book b) {
    	if(availableBooks.get(b) > 0) {
    		u.getBook(b);
    	}
    }

    /**
     */
    @SuppressWarnings("deprecation")
	public void checkDate(User u,Book b) {
		if(b.getDateOfGetting().getMonth() - 6 < 0) {
			takeFee(u);
		}
//    	if(b.getDateOfGetting().compareTo(new Date("2022-12-31")) < 0) { // b.date before new year
//    		if(new Date().compareTo(new Date("2022-12-31")) > 0) { // now after new year
//    			takeFee(u);
//    		}
//    	}
//    	if(b.getDateOfGetting().compareTo(new Date("2023-06-01")) < 0) {
//    		if(new Date().compareTo(new Date("2023-06-01")) > 0) {
//    			takeFee(u);
//    		}
//    	}
    }

    /**
     */
    public void takeFee(User u) {
    	Manager.changeDept(3000,u);
    }
}

