package Univer;

import java.io.Serializable;
import java.util.*;

/**
 */
public class Book  implements Serializable{
    /**
     */
    String name;

    /**
     */
    int yearOfIssue;

    /**
     */
    String author;

    /**
     */
    private Date dateOfGetting;
    public Book(String name,int yearOfIssue,String author) {
    	this.name = name;
    	this.yearOfIssue = yearOfIssue;
    	this.author = author;
    }
    public void setDateOfGetting() {
    	this.dateOfGetting = new Date();
    }
    public Date getDateOfGetting() {
    	return this.dateOfGetting;
    }
    /**
     * @return 
     */
    public String toString() {
        return "Name:" + name + ", year of issue: " + yearOfIssue + ", author: " + author;
    }
}

