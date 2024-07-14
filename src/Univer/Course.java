package Univer;

import java.io.Serializable;
import java.util.Objects;
import java.util.Vector;
/**
 */
public class Course  implements Serializable{
	/**
	 */
	Vector<Lesson> listOfLesson = new Vector<Lesson>();
	/**
	 */
	private String name;
	/**
	 */
	private int credits;
	private String teacherName;
	private String prerequisite = "";
	private String requisite;
	Faculty faculty;
	CourseType type;
	public Course() {
	}
//
	public Course( String name, int credits, String teacherName, String type,String requisite,String faculty) {
		this.name = name;
		this.credits = credits;
		this.teacherName = teacherName;
		setType(type);
		this.requisite = requisite;
		setFaculty(faculty);
	}
	public void setFaculty(String faculty) {
		if(faculty.equals("BS")) {
			this.faculty = Faculty.BS;
		}
		if(faculty.equals("FIT")) {
			this.faculty = Faculty.FIT;
		}
		if(faculty.equals("FOGI")) {
			this.faculty = Faculty.FOGI;
		}
		if(faculty.equals("KMA")){
			this.faculty = Faculty.KMA;
		}
	}
	public void setType(String type) {
		if(type.equals("Major")) {
			this.type = CourseType.MAJOR;
		}
		if(type.equals("Elective")) {
			this.type = CourseType.ELECTIVE;
		}
		if(type.equals("NonElective")) {
			this.type = CourseType.NONELECTIVE;
		}
	}
	public String getPrerequisite() {
		return this.prerequisite;
	}
	public String getRequisitie() {
		return this.requisite;
	}
	public void setPrerequisite(String prerequisite) {
		this.prerequisite = prerequisite;
	}
	public Vector<Lesson> getListOfLesson() {
		return listOfLesson;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setListOfLesson(Vector<Lesson> listOfLesson) {
		this.listOfLesson = listOfLesson;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public String toString() {
		return "Name of course: "+ name + ", take credits: " + credits + ", list of lesson: {" + listOfLesson + "}.";
	}

	public int hashCode() {
		return Objects.hash(credits, listOfLesson, name);
	}

	public boolean equals(Object obj) {
		if (this == obj)return true;
		if (obj == null)return false;
		if (getClass() != obj.getClass())return false;
		Course other = (Course) obj;
		return credits == other.credits && Objects.equals(listOfLesson, other.listOfLesson)
				&& Objects.equals(name, other.name);
	}

	public int compareTo(Object o) {
		Course c = (Course)o;
		if(this.credits > c.credits)return 1;
		else if(this.credits < c.credits)return -1;
		return 0;
	}

}
