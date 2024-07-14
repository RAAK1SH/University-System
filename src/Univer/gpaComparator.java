package Univer;

import java.io.Serializable;
import java.util.Comparator;

public class gpaComparator implements Comparator<Student>,Serializable {
	public int compare(Student c1, Student c2) {
		if(c1.gpa > c2.gpa) {
			return 1;
		}
		if(c1.gpa < c2.gpa) {
			return -1;
		}
		return 0;
	}
	
}
