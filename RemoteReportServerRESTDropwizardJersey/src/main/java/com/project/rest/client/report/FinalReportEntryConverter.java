package com.project.rest.client.report;

import com.googlecode.jcsv.writer.CSVEntryConverter;

/**
 * It converts a final report in CSV format. 
 * 
 * @author imetaxas
 *
 */
public final class FinalReportEntryConverter implements CSVEntryConverter<FinalReport> {
	
	/**
	 * Constructs a newly allocated FinalReportEntryConverter object.
	 */
	public FinalReportEntryConverter(){}
	
	@Override
	public String[] convertEntry(FinalReport report) {
		String[] columns = new String[3];

		columns[0] = report.getId();
		columns[1] = report.getCustomId();
		columns[2] = String.valueOf(report.getTypes());

		return columns;
	}
}
