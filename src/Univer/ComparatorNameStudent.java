package Univer;

import java.io.Serializable;
import java.util.Comparator;

public class ComparatorNameStudent implements Comparator<Student>,Serializable{

	@Override
	public int compare(Student s1, Student s2) {
		return s1.name.compareTo(s2.name);
	}
	

}
