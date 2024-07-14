package Univer;

import java.io.Serializable;
import java.util.HashMap;

public class Lesson  implements Serializable{
    /**
     */
    private Time t;

    /**
     */
    private String name;

    /**
     */
    private String teacher;

    /**
     */
    private int auditory;

    /**
     */
    private FormatType format;

    /**
     */
    private LessonType type;

    /**
     */
    HashMap<Student, Mark> attendanceList;
    
    public Lesson() {
	}
    
	public Lesson( Time t, String name, String teacher, int auditory, FormatType format, LessonType type) {
		this.t = t;
		this.name = name;
		this.teacher = teacher;
		this.auditory = auditory;
		this.format = format;
		this.type = type;
	}

	public Time getT() {
		return t;
	}

	public void setT(Time t) {
		this.t = t;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public int getAuditory() {
		return auditory;
	}

	public void setAuditory(int auditory) {
		this.auditory = auditory;
	}

	public FormatType getFormat() {
		return format;
	}

	public void setFormat(FormatType format) {
		this.format = format;
	}

	public HashMap<Student, Mark> getAttendanceList() {
		return attendanceList;
	}

	public void setAttendanceList(HashMap<Student, Mark> attendanceList) {
		this.attendanceList = attendanceList;
	}

	public LessonType getType() {
		return type;
	}

	public void setType(LessonType type) {
		this.type = type;
	}
    /**
     * @return 
     */
    public String toString() {
        return "Name of lesson: " + name + ", teacher: " + teacher + ", format:" + format + ", type: " + type + ", lesson auditory: " + auditory + ", time: " + t;
    }

    
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
    
}
