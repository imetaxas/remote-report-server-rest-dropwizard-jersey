package com.project.rest.report;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Bean for representing the campaign report list as defined in the campaign.xml
 * 
 * @author imetaxas
 *
 */
@XmlRootElement(name = "collection")
@XmlAccessorType(XmlAccessType.FIELD)
public final class CampaignReportList implements ReportList {
	
	/**
	 * Stores the list of all the campaign reports.
	 */
	@XmlElement(name = "campaignBean")
	List<CampaignReport> reports = new ArrayList<CampaignReport>();

	/**
	 * Constructs a newly allocated CampaignReportList object.
	 */
	public CampaignReportList(){}
	
	/**
	 * Gets the list of all the campaign reports.
	 */
	public List<CampaignReport> getReports() {
		return reports;
	}

	@Override
	public String toString() {
		return "CampaignReportList [reports=" + reports + "]";
	}
	
}
