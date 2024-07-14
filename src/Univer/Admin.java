package Univer;

import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;
import java.util.Vector;

/**
*/
public class Admin extends User  implements Serializable{
/**
 * @throws IOException 
 * @throws NumberFormatException 
*/
public void createUser(String name,String login, String password,String type) throws NumberFormatException, IOException{
	if(type.equals("Employee")){
		int salary = Integer.parseInt(br.readLine());
		String typeOfEmployee = br.readLine(); 
		if(typeOfEmployee.equals("Teacher")) {
			String category = br.readLine(); 
			Teacher t = new Teacher(salary,name,login,password,category);
			add(t);
		}
		if(typeOfEmployee.equals("Manager")) {
			String category = br.readLine(); 
			String phoneNumber = br.readLine(); 
			Manager m = new Manager(phoneNumber,category,salary, name,login,password);
			add(m);
		}
		if(typeOfEmployee.equals("Librarian")) {
			Librarian l = new Librarian(salary,name,login,password);
			add(l);
		}
	}
	else {
		String faculty = br.readLine(); 
		String id = br.readLine(); 
		Student s = new Student(faculty,name,login,password,id);
		add(s);
	}
}
public void add(User u){
	Database.getInstance().getUsers().put(u, null);
}
/**
*/
public void remove(String login) {
	Database.getInstance().getUsers().remove((User) Database.getInstance().getUsers().keySet().stream().filter(s->s.getLogin().equals(login)).limit(1));
}
/**
*/
public Vector<String> getLogFile(User u) {
	return Database.getInstance().getUsers().get(u);
}
public void update(String login, String operation) throws IOException {
	 
	 User u = (User) Database.getInstance().getUsers().keySet().stream().filter(s->s.getLogin().equals(login)).limit(1);
	 
	 if(operation.equals("Change password")) {
	  String password = br.readLine(); 
	  u.setPassword(password);
	 }
	 
	 else if(operation.equals("Change login")) {
	  String log = br.readLine(); 
	  u.setLogin(log);
	 }
	 
	 else if(operation.equals("Change name")) {
	  String name = br.readLine(); 
	  u.name = name;
	 }
	 
	 else if(operation.equals("Change Id")) {
	  Student s = (Student) u;
	  String id = br.readLine(); 
	  s.setId(id);
	 }
	 
	 else if(operation.equals("Change salary")) {
	  Employee e = (Employee) u;
	  int salary = Integer.parseInt(br.readLine());
	  e.setSalary(salary);
	 }
	 
	 else if(operation.equals("Change phonenumber")) {
	  Manager m = (Manager) u;
	  String phoneNumber = br.readLine(); 
	  m.phoneNumber = phoneNumber;
	 }
	 else if(u instanceof Teacher) {
		 Teacher t = (Teacher) u;
		 t.sendApplication(operation);
//		 if(operation.equals("add course")) {
//			 t.sendApplication("add Course", new Date());
//		 }
//		 if(operation.equals("drop Course")) {
//			 t.sendApplication("drop Course", new Date());
//		 }
	 }
	 else if(u instanceof Student) {
		 Student s = (Student) u;
		 s.sendApplication(operation);
	 }
}
}

