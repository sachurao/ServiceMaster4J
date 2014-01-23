package com.streamcipher.svcmaster.webservice;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.streamcipher.svcmaster.config.ManagedServiceConfig;

@Path("/registry")
public class RegistryResource {
    
    
    private static Collection<ManagedServiceConfig> managedServices;

    public RegistryResource() {
	
    }

    @Produces({ "application/json" })
    @Path("/getAll")
    @GET
    public Collection<ManagedServiceConfig> getManagedServices() {
	return managedServices;
    }
    
    public static void setManagedServices(Collection<ManagedServiceConfig> managedServices) {
	RegistryResource.managedServices = managedServices;
    }

}
