package com.streamcipher.svcmaster;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.streamcipher.svcmaster.config.ManagedServiceConfig;
import com.streamcipher.svcmaster.webservice.IWebServerWrapper;
import com.streamcipher.svcmaster.webservice.RegistryResource;

public class ServiceMaster {

    private static final Logger log = Logger.getLogger(ServiceMaster.class);
    private IWebServerWrapper webServer;
    private Collection<ManagedServiceConfig> managedServices;

    public ServiceMaster(ApplicationContext appContext,
	    Collection<ManagedServiceConfig> managedServices) {
	this.webServer = appContext.getBean(IWebServerWrapper.class);
	this.managedServices = managedServices;
    }

    public void start() {
	log.info("Starting up ServiceMaster...");

	webServer.start(8080);
	RegistryResource.setManagedServices(managedServices);
	

	// TODO Display configured services on ServiceMonitor page
	// TODO Establish remote connection with services to see if they are
	// active
	// TODO Add cluster-wide functions
	// TODO Add component-wide functions

	log.info("Started up ServiceMaster...");
    }

    public void shutDown() {
	log.info("Shutting down connection to all services and web server...");
	webServer.shutDown();
    }

}
