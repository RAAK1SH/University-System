package Univer;
import java.util.Map.Entry;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Vector;
/**
 */
public class Student extends User implements Timetable,Info,Serializable,Researcher{
  /**
   */
  private Vector<pair<Course,Grade>> transcript = new Vector<pair<Course,Grade>>();
  /**
   */
  private int credits = 21;
  /**
   */
  private Faculty department;
  /**
   */
  private String id;
  /**
   */
  double gpa = 0;
  int numberOfSemester = 0;
  public Student(String faculty,String name, String login, String password, String id) {
    super(name,login,password);
    setFaculty(faculty);
    this.id = id;
  }
  public void setFaculty(String faculty) {
    if(faculty.equals("BS")) {
      department = Faculty.BS;
    }
    if(faculty.equals("FIT")) {
      department = Faculty.FIT;
    }
    if(faculty.equals("FOGI")) {
      department = Faculty.FOGI;
    }
    if(faculty.equals("KMA")){
      department = Faculty.KMA;
    }
  }
  public void setGpa() {
    double tempGpa = 0;
    if(transcript.size() < 5) {
    	return;
    }
    for(int i = transcript.size() - 5; i < transcript.size(); i++) {
      tempGpa += transcript.get(i).T2.getPoints();
    }
    tempGpa = tempGpa / 5;
    numberOfSemester++;
    gpa += tempGpa;
    gpa = gpa / numberOfSemester;
  }
  public Vector<pair<Course,Grade>>getTranscript() {
    return transcript;
  }

  public void setTranscript(Vector<pair<Course,Grade>> transcript) {
    this.transcript = transcript;
  }

  public int getCredits() {
    return credits;
  }

  public void setCredits(int credits) {
    this.credits = credits;
  }

  public Faculty getDepartment() {
    return department;
  }

  public void setDepartment(Faculty department) {
    this.department = department;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
  
  private static final int limitCellWidth = 30;
  private static final String bigSpace = "                              ";
  private static final String lowSpace = " ______________________________";
  private String[] weekDays = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
  
  private String compressTimetableDisplay(String str) {
    if(str.length() >= limitCellWidth) {
      return str.substring(0, limitCellWidth);
    }
    boolean ok = true;
    while(str.length() < limitCellWidth) {
      if(ok) {
        str = " " + str;
        ok = !ok;
      }else {
        str += " ";
        ok = !ok;
      }
    }
    
    return str;
  }
  
  private String toTime(int begin) {
    return (begin < 10 ? "0" : "") + begin + ":00";
  }
  
  public void viewTimetable() {
    Lesson[][] ar = new Lesson[24][7];
    
    for(int day = 0;day < 7;day++) {
      for(int begin = 8;begin <= 22;begin++) {
        for(pair<Course, Grade> st : transcript) {
          for(Lesson lesson : st.T1.getListOfLesson()) {
            if(lesson.getT().getBegin() == begin && lesson.getT().getDay() == day) {
              ar[begin][day] = lesson;
            }
          }
        }
      }
    }
    
    for(int day = 0;day < 7;day++) {
      System.out.print(lowSpace);
    }
    
    System.out.print("\n");
    
    for(int day = 0;day < 7;day++) {
      System.out.print(this.compressTimetableDisplay(weekDays[day]));
    }
    System.out.print("\n");
    for(int day = 0;day < 7;day++) {
      System.out.print(lowSpace);
    }
    System.out.print("\n");
    for(int begin = 8;begin <= 22;begin++) {
      for(int day = 0;day < 7;day++) {
        System.out.print(toTime(begin)+"                          ");
      }
      System.out.print("\n");
      for(int day = 0;day < 7;day++) {
        System.out.print("|");
        if(ar[begin][day] == null) {
          System.out.print(bigSpace);
        }else {
          System.out.print(this.compressTimetableDisplay(ar[begin][day].getName()));
        }
      }
      System.out.print("|\n");
      
      for(int day = 0;day < 7;day++) {
        System.out.print("|");
        if(ar[begin][day] == null) {
          System.out.print(bigSpace);
        }else {
          System.out.print(compressTimetableDisplay(Integer.toString(ar[begin][day].getAuditory())));

        }
      }
      System.out.print("|\n");
      
      for(int day = 0;day < 7;day++) {
        System.out.print("|");
        if(ar[begin][day] == null) {
          System.out.print(bigSpace);
        }else {
          System.out.print(this.compressTimetableDisplay(ar[begin][day].getTeacher()));
        }
      }
      System.out.print("|\n");
      
      for(int day = 0;day < 7;day++) {
        System.out.print("|");
        if(ar[begin][day] == null) {
          System.out.print(bigSpace);
        }else {
          System.out.print(this.compressTimetableDisplay(ar[begin][day].getFormat().toString()));
        }
      }
      System.out.print("|\n");
      
      for(int day = 0;day < 7;day++) {
        System.out.print(lowSpace);
      }
      
      System.out.print("\n");
    }
  }
  /**
   */
  public void markAttendance(Lesson ls) {
	  for(pair<Course,Grade> p : transcript) {
		  for(Lesson l : p.T1.listOfLesson) {
			  if(l.equals(ls)) {
				  l.attendanceList.get(this).setAttendance(true);
				  return;
			  }
		  }
	  }
  }
  /**
   */
  public void viewMarks() {
    for(pair<Course, Grade> o : transcript) {
      System.out.println(o.T1.getName()+": ");
      for(Lesson lesson : o.T1.getListOfLesson()) {
        System.out.println("\t"+lesson.getT().getDay()+", "+lesson.getT().getBegin()+": "+lesson.getAttendanceList().get(this));
      }
    }
  }
  /**
   */
  public void viewTranscript() {
    for(pair<Course, Grade> o : transcript) {
      System.out.println(o.T1+": "+o.T2);
    }
  }
  /**
   */
  public void viewCourses() {
	for(pair<Course,Grade> o : transcript) {
	   System.out.println(o.T1.getName()+" : "+o.T1.getCredits());
	}
  }
  /**
   */
  public void viewJournal() {
    for(pair<Course, Grade> o : transcript) {
      System.out.println(o.T1.getName());
      for(Lesson lesson : o.T1.getListOfLesson()) {
        System.out.println("\t"+lesson.getT().getDay()+", "+lesson.getT().getBegin()+": "+lesson.getAttendanceList().get(this));
      }
    }
  }
  @Override
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
  public void rateTeacher(int rate, Teacher t) {
    t.rates.add(rate);
  }
  @Override
  public int hashCode() {
    return Objects.hash(credits, department, id, transcript);
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)return true;
    if (obj == null)return false;
    if (getClass() != obj.getClass())return false;
    Student other = (Student) obj;
    return credits == other.credits && department == other.department && Objects.equals(id, other.id)
        && Objects.equals(transcript, other.transcript);
  }

  public int compareTo(Object o) {
    Student s = (Student)o;
    if(this.credits > s.credits)return 1;
    else if(this.credits < s.credits)return -1;
    return 0;
  }
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