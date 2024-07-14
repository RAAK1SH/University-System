package Univer;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;
import java.util.Map.Entry;


/**
 */
public class Manager extends Employee implements Info,Serializable{
    /**
     */
    String phoneNumber;
    /**
     */
    ManagerType type;

    /**
     */
    public Manager(String phoneNumber, String category,int salary,String name, String login, String password) {
    	super(salary,name,login,password);
    	this.phoneNumber = phoneNumber;
    	setManagerType(category);
    }
    public void setManagerType(String category) {
    	if(category.equals("OR")) {
    		type = ManagerType.OR;
    	}
    	if(category.equals("Department")){
    		type = ManagerType.DEPARTMENT;
    	}
    }
    public void manageNews(String text,String topic) {
    	Database.getInstance().getNews().add(new News(text,new Date(),topic));
    }
    public static void changeDept(int dept, User u) {
		u.debt -= dept;
	}
    /**
     */
    public void addCourse(User u, Course c){
    	if(u instanceof Student) {
	    	Student s = (Student) u;
	    	Vector<pair<Course, Grade>> temp = s.getTranscript();
	    	temp.add(new pair<Course, Grade>(c, null));
	    	s.setTranscript(temp);
    	}
    	if(u instanceof Teacher) {
    		Teacher t = (Teacher) u;
	    	t.courses.add(c);
    	}
    }

    /**
     */
    @SuppressWarnings("unlikely-arg-type")
	public void viewStudentInfo(Student st, Course cr) {
        for(Course course : Database.getInstance().getCourses()) {
          if(!course.equals(cr)) continue;  
          for(Lesson lesson : course.getListOfLesson()) {
            for(Entry<Student, Mark> student : lesson.getAttendanceList().entrySet()) {
              if(student.equals(st)) {
              System.out.println("\t"+student.getKey());
              return;
              }
            }
          }
        }
      }

    /**
     */
    public void changeLessonDay(Lesson l,Time t) {
    	l.setT(t);
    }

    /**
     * @throws IOException 
     * @throws NumberFormatException 
     */
    public void getApplication() throws NumberFormatException, IOException{
    	if(!Database.getInstance().getAplications().isEmpty()) {
    		Application a = Database.getInstance().getAplications().remove();
    		if(a.topic.equals("drop Course")) {
    			dropCourse(a.u, a.course);
    		}
    		if(a.topic.equals("add Course")) {
    			if(a.u instanceof Teacher) {
    				addCourse(a.u, a.course);
    			}
    			else if(canHaveCourse(a.u,a.course) == true) {
    				addCourse(a.u,a.course);
    			}
    		}
    		if(a.topic.equals("Change Lesson day")) {
    			int day = Integer.parseInt(br.readLine());
    			int begin = Integer.parseInt(br.readLine());
    			changeLessonDay(a.l,new Time(day,begin));
    		}
    	}
    }

    /**
     */
    @SuppressWarnings("unlikely-arg-type")
	public void dropCourse(User u, Course course) {
    	if(u instanceof Student) {
    		Student s = (Student) u;
    		Vector<pair<Course, Grade>> temp = s.getTranscript();
    		temp.remove(course);
    		s.setTranscript(temp);
    	}
    	if(u instanceof Teacher) {
    		Teacher t = (Teacher) u;
    		Database.getInstance().getCourses().stream().map(c->c != (Course) t.courses.stream().filter(cour->cour == course));
    		t.courses.stream().map(c->c != course);
    		Database.getInstance().getStudents().stream().map(s->s.getTranscript().remove(course));
    	}
    }
    public void expulsion() {
    	for(Student s: Database.getInstance().getStudents()) {
    		for(pair<Course, Grade>  v : s.getTranscript()) {
    			if(v.T2.getRetakes() > 3) {
    				Database.getInstance().getUsers().keySet().remove(s);
    				break;
    			}
    		}
    	}
    }
    /**
     * @return 
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
    public void statisticReport(String order) {
    	if(order.equals("gpa")) {
	    	gpaComparator gpaSort = null;
	    	@SuppressWarnings("unchecked")
			Vector<Student> sorted = (Vector<Student>) Database.getInstance().getStudents().stream().sorted(gpaSort);
	    	if(sorted != null) {
	    		for(Student s : sorted) {
	    			System.out.println(s);
	    		}
	    	}
    	}
    	if(order.equals("ABC")) {
    		ComparatorNameStudent abc = null;
    		@SuppressWarnings("unchecked")
			Vector<Student> sorted = (Vector<Student>) Database.getInstance().getStudents().stream().sorted(abc);
	    	if(sorted != null) {
	    		for(Student s : sorted) {
	    			System.out.println(s);
	    		}
	    	}
    	}
    }
    @SuppressWarnings("unlikely-arg-type")
	public boolean canHaveCourse(User u,Course c) {
    	Student s = (Student) u;
    	if(c.faculty != s.getDepartment() && c.type != CourseType.ELECTIVE){
    		return false;
    	}
    	if(s.getCredits() - c.getCredits() >= 0){
    		if(s.getTranscript().contains(c.getPrerequisite()) == true) {
    			for(pair<Course,Grade> p : s.getTranscript()) {
    				if(p.T2.getLetter() != "F" && p.T1.getName().equals(c)) {
    					return true;
    				}
    			}
    		}
    		if(c.getPrerequisite().equals("")) {
    			return true;
    		}
    	}
        return false;
    }
}

