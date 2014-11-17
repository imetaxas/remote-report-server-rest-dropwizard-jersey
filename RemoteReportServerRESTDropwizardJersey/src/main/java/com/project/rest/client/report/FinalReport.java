package com.project.rest.client.report;

import java.util.ArrayList;

import com.project.rest.report.CampaignReport;

/**
 * Bean for representing the Final report which is a combination
 * of the CampaignReport and the GoalReport classes.
 * 
 * @author imetaxas
 *
 */
public final class FinalReport extends CampaignReport {
	
	/**
	 * A list of types as the type is defined in the goal.xml
	 */
	private ArrayList<String> types;

	/**
	 * Constructs a newly allocated FinalReport object.
	 */
	public FinalReport(){}
	
	/**
	 * Adds a type in the types list
	 * 
	 * @param type
	 */
	public void addType(String type) {
		if(types == null){
			types = new ArrayList<>();
		}
		types.add(type);
	}
	
	/**
	 * Gets the list of types
	 * 
	 * @return the list of types
	 */
	public ArrayList<String> getTypes(){
		return types;
	}
	
	@Override
	public String toString() {
		return "FinalReport [" + super.toString() + ", types=" + types + "]";
	}
	
}
