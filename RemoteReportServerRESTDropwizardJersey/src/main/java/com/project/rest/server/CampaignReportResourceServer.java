package com.project.rest.server;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.project.rest.report.CampaignReportList;
import com.project.rest.report.ReportList;
import com.project.rest.report.util.ReportUtil;

/**
 * Server's wrapper class of the remote campaigns report resource service.
 * 
 * @author imetaxas
 *
 */
@Path("campaign")
public final class CampaignReportResourceServer {
	
	/**
	 * Stores the location of the file campaign.xml 
	 */
	private static String CAMPAIGN_XML_RESOURCE = "xml/campaign.xml";
	
	
	/**
	 * Constructs a newly allocated CampaignReportResourceServer object.
	 */
	public CampaignReportResourceServer(){}
	
	/**
	 * Service which uploads the campaign.xml file.
	 * 
	 * @return the campaign.xml file
	 * @throws IOException
	 * 
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getCampaignReportXML()  {
		ReportList report = (ReportList) ReportUtil.xmlToObject(CAMPAIGN_XML_RESOURCE, CampaignReportList.class);
		
		return Response.ok(report).build();
	}
	
}
