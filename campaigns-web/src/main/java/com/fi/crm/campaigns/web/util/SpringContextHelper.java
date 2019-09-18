package com.fi.crm.campaigns.web.util;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringContextHelper {
	private ApplicationContext context;
	
	/**
	 * static logger for class SpringContextHelper
	 */
	private static final Logger logger = LoggerFactory.getLogger(SpringContextHelper.class);
	
    public SpringContextHelper(ServletContext servletContext) {

        logger.debug("Staring SpringContextHelper method");
		try {
			context = WebApplicationContextUtils.
	                getRequiredWebApplicationContext(servletContext);
		} catch (Exception e) {
			//System.err.println("error spring context >> "+e);
			logger.error("Error SpringContextHelper method ", e);
		} finally {
			//System.err.println("2 context >> "+context);
			logger.debug("Ending SpringContextHelper method");
		}
    }

    public Object getBean(final String beanRef) {
    	//System.out.println("4 beanRef >>>>>>"+beanRef);
        return context.getBean(beanRef);
    } 
}
