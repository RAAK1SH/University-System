package Univer;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

public class Application  implements Serializable{
	Date dateOfApplication = new Date();
	String topic;
	String answer;
	Date dateOfIssue;
	User u;
	Course course;
	Lesson l;
	Time t;
	public Application(String topic, User u) throws NumberFormatException, IOException{
		setTopic(topic);
		this.u = u;
	}
	public void setTopic(String topic) throws NumberFormatException, IOException{
		this.topic = topic;
		if(topic.equals("drop Course") || topic.equals("add Course")) {
			setCourse();
		}
		if(topic.equals("Change Lesson day")) {
			setTime();
			setLesson();
		}
	}
	
	public void setLesson() throws NumberFormatException, IOException {
		int auditory = Integer.parseInt(Database.br.readLine());
		for(Course c : Database.getInstance().getCourses()) {
			for(Lesson les : c.listOfLesson) {
				if(les.getT().equals(t) && les.getAuditory() == auditory) {
					l = les;
					System.out.println("Application accepted!");
					return;
				}
			}
		}
		System.out.println("Application rejected!");
//	    Database.courses.stream().forEach(el -> {
//			Vector<Lesson> v = (Vector<Lesson>) el.listOfLesson.stream().filter(l -> l.getT().equals(t) && l.getAuditory() == auditory);
//	        if (v.size() > 0) {
//	            l = v.firstElement();
//	        }
//	    });
	    
	}
	public void setTime() throws NumberFormatException, IOException{
		int day = Integer.parseInt(Database.br.readLine());
		int begin = Integer.parseInt(Database.br.readLine());
		t = new Time(day,begin);
	}
	public void setCourse()  throws NumberFormatException, IOException{
		String courseName = Database.br.readLine();
		if(Database.getInstance().getCourses().stream().filter(c->c.getName().equals(courseName)).limit(1) == null) {
			System.out.println("We don't have that course");
		}
		else {
			for(Course c :Database.getInstance().getCourses()) {
				if(c.getName().equals(courseName)) {
					course = c;
					System.out.println("Course found");
					return;
				}
			}
		}
		System.out.println("Course doesn't exist");
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public void setDateOfIssue(Date dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}
}
