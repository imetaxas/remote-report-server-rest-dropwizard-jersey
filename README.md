remote-report-server-rest-dropwizard-jersey
===========================================
This application implements a REST Report Service for pulling custom reports from a remote report server.   
The Dropwizard framework is used for setting the server and the Jersey client for calling the services.

Business Rules
---------------
The web interface contains a simple download button which calls a remote resource service in order to download a report file in CSV format. 

The CSV report file has content combined by two other remote resources which reside in the remote server.
The first remote resource is an xml file, which is called campaign.xml, and contains a collection of campaigns.
The second remote resource is an xml file, which is called goal.xml, and contains a collection of campaign goals.

The final report should contain the following columns for all the campaigns and goals:
	-	id from the Campaign.xml
	-	customId from the Campaign.xml and
	-	type from the Goal.xml

Goals have a n-1 relationship with Campaigns.

Two different REST API services is used, the CampaignReportResourceServer and the GoalReportResourceServer.
The URLs for calling them are:

Lists campaigns using HTTP method GET
http://localhost:8080/application/campaign

Lists goals for campaigns using HTTP method POST
http://localhost:8080/application/goal/by_campaign_id;id=456;id=123 (Posts as many ids as possible)

Note that the base url in these examples is the http://localhost:8080/application/
This url can be set in the report.yml configuration file as it is defined by Dropwizard.
