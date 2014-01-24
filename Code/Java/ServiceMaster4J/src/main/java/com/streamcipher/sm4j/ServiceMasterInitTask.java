package com.streamcipher.sm4j;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.streamcipher.sm4j.config.IServiceConfigReader;
import com.streamcipher.sm4j.config.ManagedServiceConfig;

/**
 * Callable that executes all tasks necessary to initialise ServiceMaster and
 * returns a reference once fully initialised.
 * 
 * @author Sachin
 * 
 */
public class ServiceMasterInitTask implements Callable<ServiceMaster> {

    private static final String configFileName = "svcmaster.serviceconfig.csv";
    private static final Logger log = Logger
	    .getLogger(ServiceMasterInitTask.class);
    private final IServiceConfigReader configReader;
    private final ApplicationContext appContext;

    public ServiceMasterInitTask(ApplicationContext appContext) {
	this.appContext = appContext;
	configReader = appContext.getBean(IServiceConfigReader.class);
    }

    @Override
    public ServiceMaster call() throws Exception {
	// TODO Read configuration for services

	log.info("Initialising ServiceMaster4J...");
	Path configFilePath = RuntimeEnvironment.CONFIG_DIR_PATH
		.resolve(configFileName);
	configReader.setUpReader(configFilePath.toString());
	List<ManagedServiceConfig> managedServices = configReader
		.readConfiguration();
	//RegistryResource.setManagedServices(managedServices);

	ServiceMaster sm = new ServiceMaster(appContext,managedServices);
	sm.start();
	return sm;
    }

}
