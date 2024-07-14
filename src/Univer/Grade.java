package Univer;

import java.io.Serializable;
import java.util.Objects;
import java.util.Vector;

public class Grade  implements Serializable{
    /**
     */
    private int points;
    /**
     */
    private String letter;
    /**
     */
    private Vector<Mark> marks = new Vector<Mark>();
    /**
     */
    private int retakes = 0;
    private int pointsFirstAtt;
    /**
     */
    private int pointsSecondAtt;

    /**
     */
    private int pointsFinal;
    /**
     */
    
	public Grade(int points) {
		this.points = points;
	}
    public Grade(int pointsFirstAtt, int pointsSecondAtt, int pointsFinal) {
    	this.pointsFirstAtt = pointsFirstAtt;
		this.pointsSecondAtt = pointsSecondAtt;
		this.pointsFinal = pointsFinal;
		this.points = pointsFirstAtt + pointsSecondAtt + pointsFinal;
    }
	public Grade(int points, int pointsFirstAtt, int pointsSecondAtt, int pointsFinal) {
		this.points = points;
		this.pointsFirstAtt = pointsFirstAtt;
		this.pointsSecondAtt = pointsSecondAtt;
		this.pointsFinal = pointsFinal;
	}
	public void setLetter(String letter) {
		this.letter = letter;
	}
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	public int getPointsFinal() {
		return pointsFinal;
	}
	
	public void setPointsFinal(int pointsFinal) {
		this.pointsFinal = pointsFinal;
	}
	
	public int getPointsSecondAtt() {
		return pointsSecondAtt;
	}
	
	public void setPointsSecondAtt(int pointsSecondAtt) {
		this.pointsSecondAtt = pointsSecondAtt;
	}
	
	public int getPointsFirstAtt() {
		return pointsFirstAtt;
	}
	
	public void setPointsFirstAtt(int pointsFirstAtt) {
		this.pointsFirstAtt = pointsFirstAtt;
	}
	
	public Vector<Mark> getMarks() {
		return marks;
	}
	
	public void setMarks(Vector<Mark> marks) {
		this.marks = marks;
	}
	public int getRetakes() {
		return this.retakes;
	}
	public String getLetter() {
		return this.letter;
	}
	public void convert(){
			
			String grades[] = {"F","F","F","F","F","F","F","F","F","F","D","D+","C-","C","C+","B-","B","B+","A-","A"};
			if(pointsFirstAtt == 0) {
				letter = grades[(int)(Math.round((this.points - 1) / 5))];
				return;
			}
			if(pointsFinal < 10 || (pointsFirstAtt + pointsSecondAtt < 30)) {
				letter = "F";
				retakes++;
			}
			else if(pointsFinal < 20) letter = "FX";
			else letter = grades[(int)(Math.round(points / 5))];
	}
	
	public int compareTo(Object o) {
		Grade t = (Grade)o;
		if(this.points > t.points)return 1;
		else if(this.points < t.points)return -1;
		return 0;
	}
	
	public int hashCode() {
		return Objects.hash(letter, points, pointsFinal, pointsFirstAtt, pointsSecondAtt);
	}

	public boolean equals(Object obj) {
		if (this == obj)return true;
		if (obj == null)return false;
		if (getClass() != obj.getClass())return false;
		Grade other = (Grade) obj;
		return Objects.equals(letter, other.letter) && points == other.points
				&& pointsFinal == other.pointsFinal && pointsFirstAtt == other.pointsFirstAtt
				&& pointsSecondAtt == other.pointsSecondAtt;
	}

	public String toString() {
		return "Grade [points: " + points + ", letter: " + letter + ", marks: " + marks.toString()
		+ ", pointsFirstAtt: " + pointsFirstAtt + ", pointsSecondAtt: " + pointsSecondAtt + ", pointsFinal: "
		+ pointsFinal + "]";
	}
	

}
