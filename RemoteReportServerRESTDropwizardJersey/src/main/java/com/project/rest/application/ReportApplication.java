package com.project.rest.application;

import com.bazaarvoice.dropwizard.assets.ConfiguredAssetsBundle;
import com.project.rest.client.RemoteReportResource;
import com.project.rest.server.CampaignReportResourceServer;
import com.project.rest.server.GoalReportResourceServer;

import io.dropwizard.Application;
import io.dropwizard.Bundle;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * The Main method and start of the Dropwizard application.
 * It sets the configuration as defined in the report.yml configuration file and
 * registers all the remote services.
 * 
 * @author imetaxas
 *
 */
public final class ReportApplication extends Application<ServiceConfiguration> {
	
	/**
	 * Disables the constructor.
	 */
	private ReportApplication(){}
	
	/**
	 * The Main method
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
        new ReportApplication().run(args);
    }
	
	/**
     * Initializes the application bootstrap.
     *
     * @param bootstrap the application bootstrap
     */
	@Override
	public void initialize(Bootstrap<ServiceConfiguration> bootstrap) {
		bootstrap.addBundle(new ConfiguredAssetsBundle());
	}

	/**
     * When the application runs, this is called after the {@link Bundle}s are run. Override it to add
     * providers, resources, etc. for your application.
     *
     * @param conf the parsed {@link Configuration} object
     * @param env   the application's {@link Environment}
     * @throws Exception if something goes wrong
     */
	@Override
	public void run(ServiceConfiguration conf, Environment env) throws Exception {
		env.jersey().register(new RemoteReportResource(conf.getBaseURI()));
		env.jersey().register(new CampaignReportResourceServer());
		env.jersey().register(new GoalReportResourceServer());
	}

}
