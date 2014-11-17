package com.project.rest.report;


/**
 * Bean for representing the Campaign report as defined in the campaign.xml
 * 
 * @author imetaxas
 *
 */
public class CampaignReport {
	/**
	 * Stores the field id of a campaign.
	 */
	private String id;
	
	/**
	 * Stores the field customId of a campaign.
	 */
	private String customId;
	
	/**
	 * Constructs a newly allocated CampaignReport object.
	 */
	public CampaignReport(){}
	
	/**
	 * Gets the field id of a campaign
	 * 
	 * @return the field id of a campaign
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Sets the field id of a campaign
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Gets the field customId of a campaign
	 * 
	 * @return the field customId of a campaign
	 */
	public String getCustomId() {
		return customId;
	}
	
	/**
	 * Sets the field customId of a campaign
	 * 
	 * @param customId
	 */
	public void setCustomId(String customId) {
		this.customId = customId;
	}
	
	@Override
	public String toString() {
		return "CampaignReport [getId()=" + getId() + ", getCustomId()=" + customId + "]";
	}
	
}
