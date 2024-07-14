package Univer;

import java.io.Serializable;

public class Time implements Serializable{
    /**
     */
    private int day;

    /**
     */
    private int begin;
    
    
	public Time() {
	}
	
	public Time(int day) {
		this.day = day;
	}
	
	public Time(int day, int begin) {
		this(day);
		this.begin = begin;
	}

	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null) return false;
		if(o.getClass() != getClass()) return false;
		Time t = (Time) o;
		return t.begin == begin && t.day == day;
	}
	
	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}
	
    /**
     * @return 
     */
    public String toString() {
        return "Day: " + day + ", starting time: " + begin + ":00";
    }
    
	public int compareTo(Object o) {
		Time t = (Time)o;
		if(this.begin > t.begin)return 1;
		else if(this.begin < t.begin)return -1;
		return 0;
	}
}