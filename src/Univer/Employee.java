package Univer;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

public class Employee extends User  implements Serializable,Researcher{
 
 /**
 */
 private int salary;
 /**
 */
 public Working work = Working.TRUE;
 private HashMap <Employee,Vector<String>> messages = new HashMap<Employee,Vector<String>>();
 public Employee(int salary, String name, String login,String password) {
	 super(name,login,password);
	 this.salary = salary;
 }
 public void leaveTheService() {
  work = Working.FALSE;
 }
 public void sendMessage(Employee e, String message) {
	 e.receiveMessages(this, message);
 }
 public HashMap<Employee,Vector<String> > getMessages() {
	 return messages;
 }
 public void receiveMessages(Employee e, String message) {
	 if(messages.get(e) == null) {
		 Vector<String> temp = new Vector<String>();
		 temp.add(message);
		 messages.put(e,temp);
	 }
	 else {
		 Vector<String> temp = messages.get(e);
		 temp.add(message);
		 messages.put(e, temp);
	 }
 }
 /**
 */
 public void retire() {
  work = Working.FALSE; 
 }
 
 /**
 */
 public void restore() {
  work = Working.TRUE; 
 }
 
 public int getSalary() {
  return salary;
 }
 
 public void setSalary(int salary) {
  this.salary = salary;
 }
 public String toString() {
	 return super.toString();
 }
 @Override
	public void researchPapers(Paper p) {
		Date dateOfPost = p.dateOfPost;
		if(dateOfPost.getYear() < 2000) {
			System.out.println("it is old paper");
		}
		else {
			System.out.println("it is new paper");
		}
	}
	@Override
	public void researchProjects(Project p) {
		if(p.type.equals("Diploma")) {
			System.out.println("We need to research it deeper");
		}
		else {
			System.out.println("We need more information");
		}
	}
}