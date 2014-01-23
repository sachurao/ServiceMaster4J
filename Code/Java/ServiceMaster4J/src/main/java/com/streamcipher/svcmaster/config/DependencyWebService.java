package com.streamcipher.svcmaster.config;

import java.util.Arrays;

import javax.ws.rs.ext.RuntimeDelegate;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.log4j.Logger;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.springframework.context.annotation.Bean;

import com.streamcipher.svcmaster.webservice.RegistryResource;
import com.streamcipher.svcmaster.webservice.RestWebServiceEndPoint;

public class DependencyWebService {
    
    private static final Logger log = Logger.getLogger(DependencyWebService.class);

    @Bean(destroyMethod = "shutdown")
    public SpringBus cxf() {
	return new SpringBus();
    }

    @Bean
    public Server jaxRsServer() {
	log.info("Creating new JAX-RS endpoint");
	JAXRSServerFactoryBean factory = RuntimeDelegate.getInstance()
		.createEndpoint(getRestWebServiceEndPoint(),
			JAXRSServerFactoryBean.class);
	factory.setServiceBeans(Arrays.<Object> asList(getRegistryResource()));
	factory.setProviders(Arrays.<Object> asList(getJsonProvider()));
	return factory.create();
    }

    @Bean
    public RestWebServiceEndPoint getRestWebServiceEndPoint() {
	return new RestWebServiceEndPoint();
    }

    @Bean
    public RegistryResource getRegistryResource() {
	return new RegistryResource();
    }

    @Bean
    public JacksonJsonProvider getJsonProvider() {
	return new JacksonJsonProvider();
    }
}
