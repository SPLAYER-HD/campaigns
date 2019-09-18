package com.fi.crm.campaigns.sheduler;

import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Application Lifecycle Listener implementation class ShutdownListener
 *
 */
@WebListener
public class ShutdownListener implements ServletContextListener {
	
	/**
	 * static logger for class ShutdownListener
	 */
	final static Logger logger = LoggerFactory
			.getLogger(ShutdownListener.class);

    /**
     * Default constructor. 
     */
    public ShutdownListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
    	 logger.debug("Servlet context listener: context destroyed.");

         try {
        	 Properties props = new Properties();
             props.setProperty(StdSchedulerFactory.PROP_SCHED_INSTANCE_NAME, "CampaignsQuartzScheduler");
             props.setProperty(StdSchedulerFactory.PROP_SCHED_RMI_EXPORT, "false");
             props.setProperty(StdSchedulerFactory.PROP_SCHED_RMI_PROXY, "false");
             props.setProperty(StdSchedulerFactory.PROP_SCHED_WRAP_JOB_IN_USER_TX, "false");
             props.setProperty(StdSchedulerFactory.PROP_THREAD_POOL_CLASS, "org.quartz.simpl.SimpleThreadPool");
             props.setProperty(StdSchedulerFactory.PROP_SCHED_INTERRUPT_JOBS_ON_SHUTDOWN, "true");
             props.setProperty("org.quartz.threadPool.threadCount", "1");
             props.setProperty("org.quartz.threadPool.threadPriority", "1");
             props.setProperty("org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread", "true");
             props.setProperty(StdSchedulerFactory.PROP_JOB_STORE_CLASS, "org.quartz.simpl.RAMJobStore");
             props.setProperty("org.quartz.jobStore.misfireThreshold", "60000");
             props.setProperty(StdSchedulerFactory.PROP_SCHED_SKIP_UPDATE_CHECK, "true");
             SchedulerFactory schedulerFactory = new org.quartz.impl.StdSchedulerFactory(props);
             
             if (schedulerFactory.getScheduler().isStarted()) {
            	 schedulerFactory.getScheduler().shutdown(true);
             }
         } catch (Exception e) {
             logger.error(e.getMessage(), e);
         }
    }	
}
