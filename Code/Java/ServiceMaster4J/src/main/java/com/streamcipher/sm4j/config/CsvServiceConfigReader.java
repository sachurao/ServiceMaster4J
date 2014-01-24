package com.streamcipher.sm4j.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.log4j.Logger;

public class CsvServiceConfigReader implements IServiceConfigReader {

    private static Logger log = Logger.getLogger(CsvServiceConfigReader.class);

    private final AtomicBoolean initOnce = new AtomicBoolean(false);
    private File serviceConfigFile;

    public CsvServiceConfigReader() {
	log.info("Creating new Csv Service Config Reader");
    }

    @Override
    public List<ManagedServiceConfig> readConfiguration() throws Exception {

	if (!initOnce.get()) {
	    throw new IllegalStateException(
		    "You have to set up the Csv Config Reader "
			    + "by calling the method setUpReader with the csv file location as "
			    + "the first parameter");
	}

	/*
	 * Reads from svcmaster.serviceconfig.csv in the config directory.
	 */
	BufferedReader br = null;
	String line = "";
	String csvDelimiter = ",";
	List<ManagedServiceConfig> services = new ArrayList<ManagedServiceConfig>();

	try {
	    br = new BufferedReader(new FileReader(serviceConfigFile));
	    while ((line = br.readLine()) != null) {
		String[] contents = line.split(csvDelimiter);
		// serviceName,instance,hostName,jmxPort
		String serviceName = contents[0];
		int instance = (contents[1] != null && !contents[1].equals("")) ? Integer
			.parseInt(contents[1]) : 0;
		String hostName = contents[2];
		int jmxPort = Integer.parseInt(contents[3]);
		services.add(new ManagedServiceConfig(serviceName, instance,
			hostName, jmxPort));
	    }
	} finally {
	    if (br != null) {
		br.close();
	    }
	}
	return services;

    }

    @Override
    public void setUpReader(String... params) {
	if (params == null || params.length != 1) {
	    throw new IllegalArgumentException(
		    "Please pass in the csv file location.");
	}

	File f = new File(params[0]);
	if (!f.exists()) {
	    throw new IllegalArgumentException("The csv file " + params[0]
		    + " does not exist.");
	} else if (f.exists() && !f.canRead()) {
	    throw new IllegalArgumentException("The csv file " + params[0]
		    + " cannot be read.");
	}

	if (initOnce.compareAndSet(false, true)) {
	    serviceConfigFile = f;
	}

    }

}
