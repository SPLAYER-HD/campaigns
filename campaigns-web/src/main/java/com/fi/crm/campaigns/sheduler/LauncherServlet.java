package com.fi.crm.campaigns.sheduler;

import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class LauncherServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private org.quartz.Scheduler scheduler;
	
	public void init(ServletConfig config) throws ServletException {
		try{
			CampaignsScheduler.getInstance(getScheduler(), config.getServletContext());            
        }catch( Exception ex ){
            ex.printStackTrace();
        }
	}
	
    private org.quartz.Scheduler getScheduler() throws Exception {
        if( scheduler == null ){
            //Instancia de fabrica para definir el programador de tarea
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
            //SchedulerFactory schedulerFactory = new org.quartz.impl.StdSchedulerFactory();

            //Instancia del programador de tarea a partir de la fabrica de programador de tareas
            scheduler = schedulerFactory.getScheduler();
        }
        return scheduler;
    }
}