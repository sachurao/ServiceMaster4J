package com.streamcipher.sm4j.webservice.resources;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.streamcipher.sm4j.config.ManagedServiceConfig;

/**
 * @author Sachin
 * Allows you to add/remove/query services.
 */
@Path("/admin")
public class AdministrationResource {
    
    
    private static Collection<ManagedServiceConfig> managedServices;

    public AdministrationResource() {
	
    }

    @Produces({ "application/json" })
    @Path("/view")
    @GET
    public Collection<ManagedServiceConfig> getManagedServices() {
	return managedServices;
    }
    
    public static void setManagedServices(Collection<ManagedServiceConfig> managedServices) {
	AdministrationResource.managedServices = managedServices;
    }

}
