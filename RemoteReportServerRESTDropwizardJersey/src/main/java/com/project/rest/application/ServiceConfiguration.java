package com.project.rest.application;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.bazaarvoice.dropwizard.assets.AssetsBundleConfiguration;
import com.bazaarvoice.dropwizard.assets.AssetsConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

/**
 * Custom configuration for this Dropwizard application
 * which it serves as a wrapper of the report.yml configuration file.
 * 
 * @author imetaxas
 *
 */
public final class ServiceConfiguration extends Configuration implements AssetsBundleConfiguration {
	
	/**
	 * The apiKey as defined in the report.yml configuration file.
	 */
	@NotNull
    private String apiKey;
	
	/**
	 * The baseURI as defined in the report.yml configuration file.
	 */
	@NotNull
    private String baseURI;
	
	/**
	 * The Dropwizard assets configuration
	 */
	@Valid
	@NotNull
	@JsonProperty
	private final AssetsConfiguration assets = new AssetsConfiguration();
	
	/**
	 * Disables the constructor.
	 */
	private ServiceConfiguration(){}
	
	/**
	 * Gets the Api Key if one is needed for calling the services
	 * 
	 * @return the Api Key if one is needed for calling the services
	 */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * Sets the Api Key if one is needed for calling the services
     * 
     * @param apiKey
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Get the base URI for calling the services
     * 
     * @return the base URI for calling the services
     */
	public String getBaseURI() {
		return baseURI;
	}

	/**
	 * Sets the base URI for calling the services
	 * 
	 * @param baseURI
	 */
	public void setBaseURI(String baseURI) {
		this.baseURI = baseURI;
	}

	/**
	  * {@inheritDoc}
	  */
	@Override
	public AssetsConfiguration getAssetsConfiguration() {
		return assets;
	}
    
}
