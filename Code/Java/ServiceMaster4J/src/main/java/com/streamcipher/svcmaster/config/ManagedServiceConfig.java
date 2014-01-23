package com.streamcipher.svcmaster.config;

/**
 * Represents each service that will be controlled via ServiceMaster.
 * @author Sachin
 *
 */
public class ManagedServiceConfig {
	private final String serviceName;
	private final int instance;
	private final String hostName;
	private final int jmxPort;
	
	
	public ManagedServiceConfig(String serviceName, int instance, String hostName,
			int jmxPort) {
		super();
		this.serviceName = serviceName;
		this.instance = instance;
		this.hostName = hostName;
		this.jmxPort = jmxPort;
	}
	
	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}
	/**
	 * @return the instance
	 */
	public int getInstance() {
		return instance;
	}
	/**
	 * @return the hostName
	 */
	public String getHostName() {
		return hostName;
	}
	/**
	 * @return the jmxPort
	 */
	public int getJmxPort() {
		return jmxPort;
	}
	
	
}
