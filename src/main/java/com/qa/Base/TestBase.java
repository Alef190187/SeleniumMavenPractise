package com.qa.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	public int GET_HTTPRESPONSE_CODE_200 = 200;
	
	public Properties prop;
	
	
	public TestBase() {
		 try {
			prop = new  Properties();
			 FileInputStream ip = new FileInputStream("C:\\Users\\alef1\\eclipse-workspace\\SeleniumMavenPractice\\"
			 		+ "src\\main\\java\\com\\qa\\config\\Config.properties");
			 prop.load(ip);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		}
				
		
				
	

	

}
