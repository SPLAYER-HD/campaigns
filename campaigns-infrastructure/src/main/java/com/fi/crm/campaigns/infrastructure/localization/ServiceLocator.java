package com.fi.crm.campaigns.infrastructure.localization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceLocator {
	private Hashtable< String, Object > cache = new Hashtable< String, Object >();
    private static ServiceLocator instance = null;
    private static Context context;
    private String configFilePath;
    private static Logger logger = LoggerFactory.getLogger(ServiceLocator.class);

    protected ServiceLocator(String configFilePath) {
        try { 
        	this.configFilePath = configFilePath;
            context = getInitialContext();
        } catch (NamingException e) {
            logger.error(e.getMessage(), e);
        } catch (FileNotFoundException e) {
        	logger.error(e.getMessage(), e);
		}
    }
    
    /**
     * @return
     */
    public static ServiceLocator getInstance(String configFilePath) {
    	if(instance == null) {
    		instance = new ServiceLocator(configFilePath);
    	}
        return instance;
    }
    
    public static ServiceLocator getInstance() {
        return instance;
    }
    
    private Context getInitialContext() throws NamingException, FileNotFoundException {
        if(this.configFilePath != null) {
        	/* Lookup the remote object */
            Properties params = new Properties();
            InputStream iostream = new FileInputStream(new File(this.configFilePath));

            /*
            ResourceBundle bundle = ResourceBundle.getBundle("resources.jndi");
            Properties params = new Properties();
            params.setProperty("java.naming.factory.initial", bundle.getString("java.naming.factory.initial"));
            params.setProperty("java.naming.provider.url", bundle.getString("java.naming.provider.url"));
            */

            try {
                params.load(iostream);
            } catch (IOException e) {
                logger.error("Error al leer configuración jndi");
            }
            return new InitialContext(params);
        }
        return new InitialContext();
    }
    

    public DataSource getDataSource(String jndiName) {
        try {
                      
            /* Use the cache to improve performance */
            if( cache.containsKey(jndiName))
                return (DataSource) cache.get(jndiName);
            
            DataSource dataSource = (DataSource) context.lookup(jndiName);
            cache.put(jndiName, (Object) dataSource);
            return dataSource;
            
        } catch (NamingException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
    
    /*public QueueConnectionFactory getQueueConnectionFactory(String jndiName) {
        try {
            
            if( cache.containsKey(jndiName))
                return (QueueConnectionFactory) cache.get(jndiName);
            
            QueueConnectionFactory connFactory = (QueueConnectionFactory) context.lookup(jndiName);
            cache.put(jndiName, (T) connFactory);
            return connFactory;
            
        } catch (NamingException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
    
    public Queue getQueue(String jndiName) {
        try {
            
            if( cache.containsKey(jndiName))
                return (Queue) cache.get(jndiName);
            
            Queue queue = (Queue) context.lookup(jndiName);
            cache.put(jndiName, (T) queue);
            return queue;
            
        } catch (NamingException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
    */

    
    public void rebind(String jdniName, Object o){

        try {
            context.rebind(jdniName, o);
        } catch (NamingException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void unbind(String jdniName){

        try {
            context.unbind(jdniName);
        } catch (NamingException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
