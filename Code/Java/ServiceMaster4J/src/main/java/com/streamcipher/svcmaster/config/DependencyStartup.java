package com.streamcipher.svcmaster.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.streamcipher.svcmaster.webservice.IWebServerWrapper;
import com.streamcipher.svcmaster.webservice.WebServerWrapper;

@Configuration
public class DependencyStartup {

    private static final Logger log = Logger
	    .getLogger(DependencyStartup.class);

    @Bean
    public IServiceConfigReader getServiceConfigReader() {
	return new CsvServiceConfigReader();
    }

    @Bean
    public IWebServerWrapper getWebServer() {
	return new WebServerWrapper();
    }

}
