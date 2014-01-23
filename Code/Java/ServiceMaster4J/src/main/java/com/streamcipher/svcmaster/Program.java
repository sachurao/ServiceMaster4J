package com.streamcipher.svcmaster;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.streamcipher.svcmaster.config.DependencyStartup;


public class Program {
	
	static Logger log = Logger.getLogger(Program.class);
	static AnnotationConfigApplicationContext appContext;
	static ServiceMaster serviceMaster;
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		//Reading default config file to start things off...
		Properties props = new Properties();
		
		File propsFile = RuntimeEnvironment.CONFIG_DIR_PATH.resolve( 
				"svcmaster.properties").toFile();
		BufferedReader br = new BufferedReader(new FileReader(propsFile));
		props.load(br);
		
		
		//Set up logging...
		File logPropsFile = RuntimeEnvironment.CONFIG_DIR_PATH.resolve( 
				props.getProperty("log.props")).toFile();
		PropertyConfigurator.configure(logPropsFile.toString());
		
		//Set up spring
		appContext = new AnnotationConfigApplicationContext();
		appContext.register(DependencyStartup.class);
		appContext.refresh();
		System.out.println(appContext.toString());
		
		
		
		//Set up shutdown hook.
		Runtime.getRuntime().addShutdownHook(new Thread()
		{
			public void run() {
				log.info("Shutting down ServiceMaster4J...");
				if (serviceMaster!=null) {
					serviceMaster.shutDown();
				}
				if (appContext!=null) {
					appContext.close();
				}
			}
		});
		
		//Now start setting up ServiceMaster...
		ServiceMasterExecutor sme = new ServiceMasterExecutor(appContext);
		try {
			serviceMaster = sme.initialiseServiceMaster();
		} catch (Exception exception) {
			log.fatal("Could not initialise ServiceMaster cleanly...", exception);
			System.exit(-1);
		}
		
		System.out.println("Press any key to exit...");
		try {
			System.in.read();
			serviceMaster.shutDown();
			System.exit(0);
		} catch (IOException e) {
			log.error("Failed to read user input", e);
			System.exit(-1);
		}
	}

}
