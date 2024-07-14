package Univer;

import java.io.Serializable;

public class pair<T1, T2>  implements Serializable{
	Course T1;
	Grade T2;
	public pair(Course course, Grade grade) {
		T1 = course;
		T2 = grade;
	}
	public Grade getGrade() {
		return T2;
	}
}
