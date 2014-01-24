package com.streamcipher.svcmaster.config.test;


import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.streamcipher.sm4j.config.CsvServiceConfigReader;
import com.streamcipher.sm4j.config.ManagedServiceConfig;

@RunWith(JUnit4.class)
public class CsvServiceConfigReaderTest {
	
	@Test
	public void readConfigurationValidFile() {
		CsvServiceConfigReader configReader = new CsvServiceConfigReader();
		configReader.setUpReader("src/integration/java/valid.serviceconfig.csv");
		List<ManagedServiceConfig> managedServicesConfig=null;
		try {
			managedServicesConfig = configReader.readConfiguration();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assert(managedServicesConfig!=null);
		assertThat("There are 6 entries in the config file", managedServicesConfig.size() == 6);
		
	}

}
