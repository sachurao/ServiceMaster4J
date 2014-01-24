package com.streamcipher.sm4j.model;

/**
 * @author Sachin
 * Represents a service or java daemon process
 * managed by sm4j.
 *
 */
public class ManagedService {
    private final String serviceId;
    private final String serviceName;
    private final int instance;
    private final String hostName;
    private final int jmxPort;
    public ManagedService(String serviceName, int instance, String hostName,
	    int jmxPort) {
	super();
	this.serviceName = serviceName;
	this.instance = instance;
	this.hostName = hostName;
	this.jmxPort = jmxPort;
	//TODO: Generate a base36 id.
	this.serviceId=serviceName+"_"+Integer.toString(instance)
		+"_"+hostName;
    }
    /**
     * @return the hostName
     */
    public String getHostName() {
        return hostName;
    }
    /**
     * @return the serviceId
     */
    public String getServiceId() {
        return serviceId;
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
     * @return the jmxPort
     */
    public int getJmxPort() {
        return jmxPort;
    }
    
    
    
    
}
