package com.project.rest.report;

/**
 * Bean for representing the Goal report as defined in the goal.xml
 * 
 * @author imetaxas
 *
 */
public final class GoalReport {
	
	/**
	 * Stores the field campaignId of a campaign goal.
	 */
	private String campaignId;
	
	/**
	 * Stores the field type of a campaign goal.
	 */
	private String type;
	
	/**
	 * Constructs a newly allocated GoalReport object.
	 */
	public GoalReport(){}
	
	/**
	 * Gets the field campaignId of a campaign goal.
	 * 
	 * @return the field campaignId of a campaign goal.
	 */
	public String getCampaignId() {
		return campaignId;
	}

	/**
	 * Sets the field campaignId of a campaign goal.
	 * 
	 * @param campaignId
	 */
	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}

	/**
	 * Gets the field type of a campaign goal.
	 * 
	 * @return the field type of a campaign goal.
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Sets the field type of a campaign goal.
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "GoalReport [campaignId=" + getCampaignId() + ", type=" + type + "]";
	}
	
}
