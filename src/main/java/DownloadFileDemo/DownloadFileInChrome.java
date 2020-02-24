package DownloadFileDemo;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.json.JsonOutput;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DownloadFileInChrome {
	
	static WebDriver driver;
	static File file;
	@BeforeMethod
	
	public void setUp() {
		String downloadPath = "C:/Users/alef1/Desktop/FileDownLoad";	
     //file = new File(UUID.randomUUID().toString());
		file = new File(downloadPath);
	  file.mkdir();
		WebDriverManager.chromedriver().setup();
		//System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDocuments\\WebDriver\\chromedriver_win32 (3)\\chromedriver.exe");
		
		Map<String, Object> chromeprefs = new HashMap<String, Object>();
		
		chromeprefs.put("profile.default_content_settings.popups", 0);
		chromeprefs.put("download.default_directory", file.getAbsolutePath());
		
		ChromeOptions option = new ChromeOptions();
		option.setExperimentalOption("prefs", chromeprefs);
		driver = new ChromeDriver(option);
		//driver.get("https://skpatro.github.io/demo/links/");
		driver.get("http://the-internet.herokuapp.com/download");
	}
      @Test
      
      public void fileDownloadTest() throws InterruptedException {
    	  //driver.findElement(By.xpath("//div[@class='download']//input[@class='btn']")).click();
    	  
    	  driver.findElement(By.xpath("//a[contains(text(),'pom.xml')]")).click();
    	  Thread.sleep(2000);
    	  driver.findElement(By.xpath("//a[contains(text(),'this.jpg')]")).click();
    	  Thread.sleep(2000);
    	 File filelist[] = file.listFiles();
    	 for( File filename: filelist) {
    		 System.out.println(filename.getName());
    		 if(filename.equals("qav_test.zip")) {
    			 System.out.println(filename.getName()); 
    		 }
    	 }
      }
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
