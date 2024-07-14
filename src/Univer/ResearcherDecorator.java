package Univer;

import java.util.Date;

public class ResearcherDecorator implements Researcher{
	protected final Researcher decoratedResearcher;
	public ResearcherDecorator(Researcher r) {
		this.decoratedResearcher = r;
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
