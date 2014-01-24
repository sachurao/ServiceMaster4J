package com.streamcipher.sm4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

/**
 * Single-threaded executor whose sole responsibility is to initialise ServiceMaster
 * and return a reference, once fully initialised.
 * @author Sachin
 *
 */
public class ServiceMasterExecutor {
	static Logger log = Logger.getLogger(ServiceMasterExecutor.class);
	private ExecutorService asyncExecutor = Executors.newSingleThreadExecutor();
	private ApplicationContext appContext;
	
	public ServiceMasterExecutor(ApplicationContext appContext) {
		this.appContext = appContext;
	}
	
	public ServiceMaster initialiseServiceMaster() throws Exception {
		ServiceMasterInitTask initialiser = new ServiceMasterInitTask(appContext);
		Future<ServiceMaster> future = asyncExecutor.submit(initialiser);
		return future.get();
	}

}
