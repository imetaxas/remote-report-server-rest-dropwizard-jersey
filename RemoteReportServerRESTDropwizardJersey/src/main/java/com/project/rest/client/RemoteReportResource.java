package com.project.rest.client;

import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.project.rest.client.report.FinalReport;
import com.project.rest.report.CampaignReport;
import com.project.rest.report.GoalReport;
import com.project.rest.report.util.ReportUtil;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * Wrapper class for the remote report resource service.
 * The client calls this service which will call the server's services
 * for bringing the campaigns and its goals.
 * After combining both results a final report is pushed to the client.
 * 
 * @author imetaxas
 *
 */
@Path("/report")
public final class RemoteReportResource {
	
	/**
	 * Initializes the Singleton Jersey client instance.
	 */
	private final static JerseyClient client = JerseyClient.newDefaultClient();
	
	/**
	 * Stores the base URI for calling services.
	 */
	private final String baseURI;
	
	/**
	 * Constructs a newly allocated RemoteReportResource object.
	 * 
	 * @param baseURI
	 */
	public RemoteReportResource(String baseURI){
		this.baseURI = baseURI;
	}
	
	/**
	 * Service which downloads a combined final report in CSV format.
	 * 
	 * @return a combined final report
	 * 
	 */
	@GET
	@Path("download")
	@Produces(MediaType.MULTIPART_FORM_DATA)
	public Response getFinalReport() {
		MultivaluedMap<String, String> params = new MultivaluedMapImpl();
		//Calls the REST service to get the campaigns list
		List<CampaignReport> campaignList = client.getCampaigns(baseURI, params);
		for(CampaignReport campaignReport: campaignList){
			params.add("id", campaignReport.getId());
		}
		
		//Calls the REST service to get the campaign goals list
		List<GoalReport> goalReportList = client.getCampaignGoals(baseURI, params);
		
		//Merges the two reports into a final report
		List<FinalReport> finalReport = ReportUtil.mergeGoalAndCampaignReportLists(goalReportList, campaignList);
		
		//Converts the final report to CSV format
		Writer writer = new StringWriter();
		String content = ReportUtil.convertReportListsToCSV(writer, finalReport).toString();
		
		return Response.status(200).entity(content).header("Content-Disposition", "attachment; filename=report.csv").build();
	}
	
}
