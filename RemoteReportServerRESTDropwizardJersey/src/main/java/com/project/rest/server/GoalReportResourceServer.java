package com.project.rest.server;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.project.rest.report.GoalReportList;
import com.project.rest.report.ReportList;
import com.project.rest.report.util.ReportUtil;

/**
 * Server's wrapper class of the remote campaign goals report resource service.
 * 
 * @author imetaxas
 *
 */
@Path("goal")
public final class GoalReportResourceServer {
	
	/**
	 * Stores the location of the file goal.xml 
	 */
	private static String GOAL_XML_RESOURCE = "xml/goal.xml";
	
	
	/**
	 * Constructs a newly allocated GoalReportResourceServer object.
	 */
	public GoalReportResourceServer(){}
	
	/**
	 * Service which uploads the goal.xml file.
	 * 
	 * @return the goal.xml file
	 * @throws IOException
	 * 
	 */
	@POST
	@Path("by_campaign_id")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_XML)
	public Response getGoalReportXML(MultivaluedMap<String,String> item)  {
		ReportList report = (ReportList) ReportUtil.xmlToObject(GOAL_XML_RESOURCE, GoalReportList.class);
		
		return Response.ok(report).build();
	}
}
