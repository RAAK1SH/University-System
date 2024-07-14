package Univer;

import java.io.Serializable;
import java.util.Objects;

/**
*/
public class Mark  implements Serializable{
    /**
     */
    private int points;
    /**
     */
    private boolean attendance;
    /**
     * @return 
     */
	public Mark() {
		
	}
    
	public Mark(int points) {
		this.points = points;
	}
  
	public Mark(int points, boolean attendance) {
		this.points = points;
		this.attendance = attendance;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public boolean isAttendance() {
		return attendance;
	}
	
	public void setAttendance(boolean attendance) {
		this.attendance = attendance;
	}
	
	
	public String toString() {
        return "Points: " + points + ",/ " + "Attendance: " + attendance + "/.";
    }
    @Override
	public int hashCode() {
		return Objects.hash(attendance, points);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)return true;
		if (obj == null)return false;
		if (getClass() != obj.getClass())return false;
		Mark other = (Mark) obj;
		return attendance == other.attendance && points == other.points;
	}
	
	public int compareTo(Object o) {
		Mark m = (Mark)o;
		if(this.points > m.points)return 1;
		else if(this.points < m.points)return -1;
		return 0;
	}
	
	
}