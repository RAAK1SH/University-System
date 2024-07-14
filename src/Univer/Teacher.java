package Univer;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Scanner;
import java.util.Vector;

/**
 */
public class Teacher extends Employee implements Info, Timetable,Serializable,Researcher {
	TeacherType type;
	Vector<Integer> rates;
    public Teacher(int salary,String name, String login,String password,String category) {
		super(salary,name, login,password);
		setTeacherType(category);
		// TODO Auto-generated constructor stub
	}
    public void setTeacherType(String category) {
    	if(category.equals("lector")) {
    		type = TeacherType.LECTOR;
    	}
    	if(category.equals("Professor")) {
    		type = TeacherType.PROFESSOR;
    	}
    	if(category.equals("Senior lector")) {
    		type = TeacherType.SENIORLECTOR;
    	}
    	if(category.equals("Tutor")) {
    		type = TeacherType.TUTOR;
    	}
    }
	/**
     */
    Vector<Course> courses = new Vector<Course>();
	@SuppressWarnings("unlikely-arg-type")
	public Vector<Student> getStudents(Course c) {
    	Vector<Student> temp = new Vector<Student>();
    	for(Student s : Database.getInstance().getStudents()) {
    		if(s.getTranscript().contains(c)) {
    			temp.add(s);
    		}
    	}
    	return temp;
	}

    /**
     * 
     */
    public void viewTimetable() {
      Lesson[][] timetable = new Lesson[23][7];
      for(Course course : courses) {
        for(Lesson lesson : course.getListOfLesson()) {
          timetable[lesson.getT().getBegin()][lesson.getT().getDay()] = lesson;
        }
      }
      
      for(int begin = 8;begin <= 22;begin++) {
        for(int day = 0;day < 7;day++) {
          System.out.println(timetable[day][begin]);
        }
      }    
    }

    /**
     */
    public void markAttendance(Lesson ls, Student student) {
      for(Course course : courses) {
            for(Lesson lesson : course.getListOfLesson()) {
              if(lesson.equals(ls)) {
                HashMap<Student, Mark> list = lesson.getAttendanceList();
                list.get(student).setAttendance(true);
                //lesson.setAttendanceList(list);
                break;
              }
            }
          }
    }
    

    /**
     */
    public void viewGrades(Course c) {
    	for(Student s : Database.getInstance().getStudents()) {
    		for(int i = 0; i < s.getTranscript().size(); i++) {
    			if(s.getTranscript().get(i).T1.equals(c)) {
    				System.out.println(s.name + ": " + s.getTranscript().get(i).T2.getLetter());
    				break;
    			}
    		}
    	}
    }
    public void viewMarks() {
        for(Course c : courses) {
          System.out.println(c.getName()+": ");
          for(Lesson lesson : c.getListOfLesson()) {
            System.out.println("\t"+lesson.getT().getDay()+", "+lesson.getT().getBegin()+": "+lesson.getAttendanceList());
          }
        }
      }

    /**
     */
    public void putMarks(Lesson ls, Student student, Mark mrk) {
      for(Course course : courses) {
        for(Lesson lesson : course.getListOfLesson()) {
          if(lesson.equals(ls)) {
            HashMap<Student, Mark> list = lesson.getAttendanceList();
            if(list.get(student).isAttendance() == false) return;
            list.put(student, mrk);
            lesson.setAttendanceList(list);
            break;
          }
        }
      }
    }
    public void setFull(int finalMark,Student st, Course c) {
    	Grade g = new Grade(finalMark);
    	g.convert();
    	for(pair<Course,Grade> p : st.getTranscript()) {
    		if(p.T1.equals(c)) {
    			p.T2 = g;
    			break;
    		}
    	}
    }
    public void setFull(int pointsFirstAtt, int pointsSecondAtt, int pointsFinal,Student st, Course c) {
    	Grade g = new Grade(pointsFirstAtt, pointsSecondAtt,pointsFinal);
    	g.convert();
    	for(pair<Course,Grade> p : st.getTranscript()) {
    		if(p.T1.equals(c)) {
    			p.T2 = g;
    			break;
    		}
    	}
    }
    @SuppressWarnings("null")
	public void setGrade(Student st, Course c) throws NumberFormatException, IOException {
    	Vector<Mark> temp = null;
    	int sumFirst = 0;
    	int sumSecond = 0;
    	int skipping = 0;
		for(int i = 0; i < c.getListOfLesson().size() / 2; i++) {
			HashMap<Student,Mark> list = c.getListOfLesson().get(i).getAttendanceList();
			if(list.get(st).isAttendance() == true && list.containsKey(st)){
				temp.add((Mark) list.values());
				sumFirst += list.get(st).getPoints();
				if(list.get(st).isAttendance() == false) {
					skipping++;
				}
			}
		}
		for(int i = c.getListOfLesson().size() / 2; i < c.getListOfLesson().size(); i++) {
			HashMap<Student,Mark> list = c.getListOfLesson().get(i).getAttendanceList();
			if(list.get(st).isAttendance() == true && list.containsKey(st)){
				temp.add((Mark) list.values());
				sumSecond += list.get(st).getPoints();
				if(list.get(st).isAttendance() == false) {
					skipping++;
				}
			}
		}
    	int Final = Integer.parseInt(br.readLine());
    	Grade g = new Grade(sumFirst + sumSecond + Final,sumFirst,sumSecond,Final);
    	g.setMarks(temp);
    	g.convert();
    	if(skipping > 9) {
    		g.setLetter("F");
    	}
    	for(pair<Course,Grade> p : st.getTranscript()) {
    		if(p.T1.equals(c)) {
    			p.T2 = g;
    			break;
    		}
    	}
    }
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

    /**
     */
    public void viewStudentsInfo() {
      for(Course course : courses) {
        System.out.println(course.getName());
        for(Lesson lesson : course.getListOfLesson()) {
          for(Entry<Student, Mark> student : lesson.getAttendanceList().entrySet()) {
            System.out.println("\t"+student.getKey());
          }
        }
      }
    }
    
    public void viewStudentInfo(Student st, Course cr) {
        for(Course course : courses) {
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
    public void viewCourses() {
      for(Course course : courses) {
        System.out.println("Name of the course:"+course.getName()+", credits: "+course.getCredits());
        //System.out.println(course); // toString
      }
    }

    /**
     */
    public void viewJournal() {
      for(Course course : courses) {
        System.out.println(course.getName());
        for(Lesson lesson : course.getListOfLesson()) {
          System.out.println(lesson);
        }
      }
    }
    @Override
    public int hashCode() {
      return Objects.hash();
    }
    @Override
    public boolean equals(Object obj) {
      if (this == obj)return true;
      if (obj == null)return false;
      if (getClass() != obj.getClass())return false;
      Teacher other = (Teacher) obj;
      return type == other.type && name.equals(other.name);
    }
//
//    public int compareTo(Object o) {
//      Teacher s = (Teacher)o;
//      if(this.credits > s.credits)return 1;
//      else if(this.credits < s.credits)return -1;
//      return 0;
//    }
    @Override
	public void researchPapers(Paper p) {
		Date dateOfPost = p.dateOfPost;
		if(dateOfPost.getYear() < 2000) {
			System.out.println("it is old paper");
		}
		else {
			System.out.println("it is new paper");
		}
	}
	@Override
	public void researchProjects(Project p) {
		if(p.type.equals("Diploma")) {
			System.out.println("We need to research it deeper");
		}
		else {
			System.out.println("We need more information");
		}
	}





}
