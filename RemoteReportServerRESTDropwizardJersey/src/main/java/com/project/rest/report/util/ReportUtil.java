package com.project.rest.report.util;

import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.googlecode.jcsv.writer.CSVWriter;
import com.googlecode.jcsv.writer.internal.CSVWriterBuilder;
import com.project.rest.client.report.FinalReport;
import com.project.rest.client.report.FinalReportEntryConverter;
import com.project.rest.report.CampaignReport;
import com.project.rest.report.GoalReport;

/**
 * Convenient utility for handling reports
 * 
 * @author imetaxas
 *
 */
public final class ReportUtil {
	
	
	/**
	 * Disables the constructor.
	 */
	private ReportUtil(){}
	
	/**
	 * Generic method for unmarshalling an xml file to an object. 
	 * 
	 * @param resource
	 * @param objectClass
	 * @return an xml-unmarshalled object.
	 */
	public static Object xmlToObject(String resource, Class<?> objectClass) {
        try {
        	JAXBContext context = JAXBContext.newInstance(objectClass);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classloader.getResourceAsStream(resource);
            Object object = unmarshaller.unmarshal(inputStream);
            
            return object;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	/**
	 * Converts a campaign and a campaign goal list to CVS format.
	 * 
	 * @param writer
	 * @param report
	 * @return a campaign and a campaign goal list to CVS format.
	 */
	public static Writer convertReportListsToCSV(Writer writer, List<FinalReport> report){
		try {
			CSVWriter<FinalReport> csvWriter = new CSVWriterBuilder<FinalReport>(writer).entryConverter(new FinalReportEntryConverter()).build();
			
			FinalReport reportHeaders = new FinalReport();
			reportHeaders.setId("CampaignId");
			reportHeaders.setCustomId("CustomId");
			reportHeaders.addType("Types");
			csvWriter.write(reportHeaders);
			csvWriter.writeAll(report);
			
			StreamSource source = new StreamSource();
	        StreamResult result = new StreamResult(writer);
	        TransformerFactory tFactory = TransformerFactory.newInstance();
	        Transformer transformer = tFactory.newTransformer();
	        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	        transformer.transform(source, result);
	        
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	System.err.println("Couldn't generate report...");
	    }
		
		return writer;
	}
	
	/**
	 * Merges a campaign and a campaign goals report into a final report.
	 * 
	 * @param goalReportList
	 * @param campaignReportList
	 * @return a final report by merging a campaign and a campaign goals report.
	 */
	public static List<FinalReport> mergeGoalAndCampaignReportLists(List<GoalReport> goalReportList, List<CampaignReport> campaignReportList){
		Map<String, FinalReport> reportMap = new HashMap<>();
		FinalReport finalReport = null;
		for(GoalReport goalReport: goalReportList){
			for(CampaignReport campaignReport: campaignReportList){
				if(campaignReport.getId().equals(goalReport.getCampaignId())){
					if(reportMap.containsKey(campaignReport.getId())){
						finalReport = reportMap.get(campaignReport.getId());
						if(!finalReport.getTypes().contains(goalReport.getType())){
							finalReport.addType(goalReport.getType());
						}
					}else{
						finalReport = new FinalReport();
						finalReport.setId(campaignReport.getId());
						finalReport.setCustomId(campaignReport.getCustomId());
						finalReport.addType(goalReport.getType());
					}
					reportMap.put(campaignReport.getId(), finalReport);
					break;
				}
			}
		}
		return new ArrayList<FinalReport>(reportMap.values());
	}
}
