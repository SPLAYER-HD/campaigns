package com.fi.crm.campaigns.sheduler;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletContext;

import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fi.crm.campaigns.business.services.constant.ConstantServiceInterface;
import com.fi.crm.campaigns.common.dto.ConstantDTO;
import com.fi.crm.campaigns.common.enums.ConstantsIdentifierEnum;
import com.fi.crm.campaigns.sheduler.job.EndCampaignsAndEventsJob;
import com.fi.crm.campaigns.sheduler.job.ExecuteAutomaticCampaignsJob;
import com.fi.crm.campaigns.sheduler.job.FreeCallsCustomerJob;
import com.fi.crm.campaigns.web.util.SpringContextHelper;

public class CampaignsScheduler {
	private static Logger logger = LoggerFactory.getLogger(CampaignsScheduler.class);
	private static CampaignsScheduler instancia;

	private SpringContextHelper helper;
	private ConstantServiceInterface constantService;

	private JobDetail freeCallsCustomerJob;
	private CronTrigger freeCallsCustomerTrigger;
	private JobDetail executeAutomaticCampaignsJob;
	private CronTrigger executeAutomaticCampaignsTrigger;
	private JobDetail endCampaignsAndEventsJob;
	private CronTrigger endCampaignsAndEventsTrigger;
	private HashMap<String, ConstantDTO> constantsCache;
	
	public CampaignsScheduler(Scheduler scheduler, ServletContext context) throws Exception {
		try {
			//System.out.println("VaadinServlet.getCurrent()>>> " +VaadinServlet.getCurrent());
			helper = new SpringContextHelper(context);
			constantService = (ConstantServiceInterface) helper.getBean("constantService");
			constantsCache = constantService.getAllConstants();

			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put("servletContext", context);

			this.freeCallsCustomer(context);
			this.executeAutomaticCampaigns(context);
			this.endCampaigsAndEvents(context);
			
			scheduler.start();

			// Linking the programmer against job using trigger
			Date fcc = scheduler.scheduleJob(freeCallsCustomerJob, freeCallsCustomerTrigger);
			Date eac = scheduler.scheduleJob(executeAutomaticCampaignsJob, executeAutomaticCampaignsTrigger);
			Date ece = scheduler.scheduleJob(endCampaignsAndEventsJob, endCampaignsAndEventsTrigger);

			logger.info(freeCallsCustomerJob.getKey() + " cron job has been launched: " + fcc + " it's repeats under the expression: "
					+ freeCallsCustomerTrigger.getCronExpression());
			logger.info(executeAutomaticCampaignsJob.getKey() + " cron job has been launched: " + eac + " it's repeats under the expression: "
					+ executeAutomaticCampaignsTrigger.getCronExpression());
			logger.info(endCampaignsAndEventsJob.getKey() + " cron job has been launched: " + ece + " it's repeats under the expression: "
					+ endCampaignsAndEventsTrigger.getCronExpression());
		} catch (SchedulerException ex) {
			throw new Exception("Excepcion al inicializar scheduler");
		}
	}

	private void freeCallsCustomer(ServletContext context) {
		// Defining job to process files
		freeCallsCustomerJob = newJob(FreeCallsCustomerJob.class).withIdentity("FreeCallsCustomerStatusJob", "campaigns").build();

		// Defining job to process files
		freeCallsCustomerJob.getJobDataMap().put("servletContext", context);

		// Launching trigger to process fine files
		String freeCallsCustomerCronExpr = constantsCache.get(ConstantsIdentifierEnum.FREE_CALLS_CUSTOMER_CRON_EXPR.getValue()).getValue();

		freeCallsCustomerTrigger = newTrigger().withIdentity("FreeCallsCustomerTrigger", "campaigns").withSchedule(cronSchedule(freeCallsCustomerCronExpr))
				.build();
	}
	
	private void executeAutomaticCampaigns(ServletContext context) {
		// Defining job to process files
		executeAutomaticCampaignsJob = newJob(ExecuteAutomaticCampaignsJob.class).withIdentity("ExecuteAutomaticCampaignsJob", "campaigns").build();

		// Defining job to process files
		executeAutomaticCampaignsJob.getJobDataMap().put("servletContext", context);

		// Launching trigger to process fine files
		String executeAutomaticCampaignsCronExpr = constantsCache.get(ConstantsIdentifierEnum.EXECUTE_AUTOMATIC_CAMPAIGNS_CRON_EXPR.getValue()).getValue();

		executeAutomaticCampaignsTrigger = newTrigger().withIdentity("executeAutomaticCampaignsTrigger", "campaigns").withSchedule(cronSchedule(executeAutomaticCampaignsCronExpr))
				.build();
	}
	
	private void endCampaigsAndEvents(ServletContext context) {
		// Defining job to process files
		endCampaignsAndEventsJob = newJob(EndCampaignsAndEventsJob.class).withIdentity("EndCampaignsAndEventsJob", "campaigns").build();
		
		// Defining job to process files
		endCampaignsAndEventsJob.getJobDataMap().put("servletContext", context);
		
		// Launching trigger to process fine files
		String executeEndCampaignsAndEventsCronExpr = constantsCache.get(ConstantsIdentifierEnum.END_CAMPAIGNS_AND_EVENTS_CRON_EXPR.getValue()).getValue();
		
		endCampaignsAndEventsTrigger = newTrigger().withIdentity("endCampaignsAndEventsTrigger", "campaigns").withSchedule(cronSchedule(executeEndCampaignsAndEventsCronExpr))
				.build();
	}

	static public CampaignsScheduler getInstance(Scheduler schedulerParam, ServletContext context) throws Exception {

		if (instancia == null) {
			instancia = new CampaignsScheduler(schedulerParam, context);
		}

		return instancia;
	}

	static public void deleteInstance() throws Exception {
		if (instancia != null) {
			instancia = null;
		}
	}
}
