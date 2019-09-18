package com.fi.crm.campaigns.sheduler.job;

import javax.servlet.ServletContext;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fi.crm.campaigns.business.services.tracing.TracingServiceInterface;
import com.fi.crm.campaigns.web.util.SpringContextHelper;

public class FreeCallsCustomerJob implements Job {

	
	/**
	 * static logger for class FreeCallsCustomerJob
	 */
	private static final Logger logger = LoggerFactory.getLogger(FreeCallsCustomerJob.class);
	
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.debug("Staring execute method");
		try {
			SpringContextHelper helper = new SpringContextHelper((ServletContext) context.getJobDetail().getJobDataMap().get("servletContext"));
			TracingServiceInterface tracingService = (TracingServiceInterface) helper.getBean("tracingService");
			tracingService.freeCallsCustomer();
		} catch (Exception e) {
			logger.error("Error execute method ", e);
		} finally {
			logger.debug("Ending execute method");
		}
	}

}
