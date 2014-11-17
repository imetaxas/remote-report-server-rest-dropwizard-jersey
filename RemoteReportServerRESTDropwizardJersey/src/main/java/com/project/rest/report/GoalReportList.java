package com.project.rest.report;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Bean for representing the campaign goal report list as defined in the goal.xml
 * 
 * @author imetaxas
 *
 */
@XmlRootElement(name = "collection")
@XmlAccessorType(XmlAccessType.FIELD)
public final class GoalReportList implements ReportList {
	
	/**
	 * Stores the list of all the campaign goal reports.
	 */
	@XmlElement(name = "goalBean")
	List<GoalReport> reports = new ArrayList<GoalReport>();

	/**
	 * Constructs a newly allocated GoalReportList object.
	 */
	public GoalReportList(){}
	
	/**
	 * Gets the list of all the campaign goal reports.
	 */
	@Override
	public List<GoalReport> getReports() {
		return reports;
	}

	@Override
	public String toString() {
		return "GoalReportList [reports=" + reports + "]";
	}
	
}
