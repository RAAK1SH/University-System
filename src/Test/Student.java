package Test;

public class Student {
	String name = "";
	int grade = 0;
	public static int numOfStuds;
	static int lowest = 105;
	public static String nameOfLow = "";
	public static String nameOfHigh = "";
	static int High = 0;
	static String ans = "";
	public Student() {
	}
	public Student(int grade, String name) {
		numOfStuds++;
		this.grade = grade;
		this.name = name;
		LowMax();
	}
	public void LowMax() {
		if(grade < lowest) {
			lowest = grade;
			nameOfLow = name;
		}
		if(grade > High) {
			High = grade;
			nameOfHigh = name;
		}
		ans = "Lowest grade is " + nameOfLow + " " + Integer.toString(lowest) + ".\nHighest grade is " + nameOfHigh + " " + Integer.toString(High) + ".\n";
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String Info() {
		return ans;
	}
	public String toString() {
		return this.name + " " + this.getGrade();
	}
}
