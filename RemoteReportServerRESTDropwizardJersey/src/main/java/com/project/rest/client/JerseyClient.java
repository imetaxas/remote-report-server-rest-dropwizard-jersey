package com.project.rest.client;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import com.project.rest.report.CampaignReport;
import com.project.rest.report.CampaignReportList;
import com.project.rest.report.GoalReport;
import com.project.rest.report.GoalReportList;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * The wrapper class of the Jersey client which calls the REST services.
 * 
 * @author imetaxas
 *
 */
public final class JerseyClient {
	
	/**
	 * Instance of the Jersey client
	 */
	private final Client client;
	
	/**
	 * Constructor
	 * 
	 * @param client
	 */
	private JerseyClient(Client client) {
		this.client = client;
	}

	/**
	 * Creates a new default client
	 * 
	 * @return a new default client
	 */
	public static JerseyClient newDefaultClient() {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		client.setConnectTimeout((int) TimeUnit.SECONDS.toMillis(5));
		client.setReadTimeout((int) TimeUnit.SECONDS.toMillis(30));

		return new JerseyClient(client);
	}
	
	/**
	 * Calls the campaign report resource service
	 * 
	 * @param url
	 * @param params
	 * @return the campaign reports
	 */
	public List<CampaignReport> getCampaigns(String url, MultivaluedMap<String, String> params) {
		try {
			WebResource resource = client.resource(getBaseURI(url));
			ClientResponse response = resource.path("campaign").queryParams(params).get(ClientResponse.class);
			CampaignReportList campaignResponse = response.getEntity(CampaignReportList.class);
			
			return campaignResponse.getReports();
		} catch (IllegalStateException e) {
			System.err.println("Remote server not configured");
			return null;
		} catch (UniformInterfaceException e) {
			System.err.println("Got error from remote server");
			return null;
		} catch (ClientHandlerException e) {
			System.err.println("Could not connect to remote server");
			return null;
		}
	}
	
	/**
	 * Calls the goals report resource service
	 * 
	 * @param url
	 * @param params
	 * @return the goals report
	 */
	public List<GoalReport> getCampaignGoals(String url, MultivaluedMap<String, String> params) {
		try {
			WebResource resource = client.resource(getBaseURI(url));
			ClientResponse response = resource.path("goal").path("by_campaign_id").type("application/x-www-form-urlencoded").post(ClientResponse.class, params);
			GoalReportList goalResponse = response.getEntity(GoalReportList.class);
			
			return goalResponse.getReports();
		} catch (IllegalStateException e) {
			System.err.println("Remote server not configured");
			return null;
		} catch (UniformInterfaceException e) {
			System.err.println("Got error from remote server");
			return null;
		} catch (ClientHandlerException e) {
			System.err.println("Could not connect to remote server");
			return null;
		}
		
	}
	
	/**
	 * Builds the base URI
	 * 
	 * @param url
	 * @return the base URI
	 */
	public static URI getBaseURI(String url) {
		return UriBuilder.fromUri(url).build();
	}
}





