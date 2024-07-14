package Univer;

import java.io.Serializable;
import java.util.Date;

public class News  implements Serializable{
	String text;
	Date date;
	String topic;
	public News(String text,Date date,String topic) {
		this.text = text;
		this.date = date;
		this.topic = topic;
	}
	public String toString() {
		return "Topic: " + this.topic + "\ndate:" + date + "\nText:" + text + "\n";
	}
}
