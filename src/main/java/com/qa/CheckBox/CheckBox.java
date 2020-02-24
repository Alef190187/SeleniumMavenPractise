package com.qa.CheckBox;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CheckBox {
	
	WebDriver driver;
	@Parameters("browsername")
	@BeforeMethod
	public void setUp(String browsername) {
		System.out.println("the browser name is ==>"+browsername);
		System.out.println("the thread count is ==>"+ Thread.currentThread().getId());
		if(browsername.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		} else if(browsername.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
		}else if(browsername.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			driver= new InternetExplorerDriver();
			
			
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get("http://seleniumpractise.blogspot.com/2016/08/how-to-automate-radio-button-in.html");
		
		
	}
	
	@Test(priority =1)
	public void radioBtncheckBoxTest() {
    List<WebElement>checkboxs =driver.findElements(By.xpath("//input[@name='lang' and  @type='radio']"));
    
    System.out.println("Total checkboxs are ==> "+checkboxs.size());
    
    for(int i=0; i<checkboxs.size(); i++) {
    	//checkboxs.get(i).click();//check all the radio button.
    	String name = checkboxs.get(i).getAttribute("value");
    	System.out.println("the check box's names are ==>" +name);
    	if(checkboxs.get(i).getAttribute("value").equalsIgnoreCase("C#")) {
    		checkboxs.get(i).click();
    	}else if(checkboxs.get(i).getAttribute("value").equalsIgnoreCase("JAVA")) {
    		checkboxs.get(i).click();
    	}else if(checkboxs.get(i).getAttribute("value").equalsIgnoreCase("RUBY")) {
    		checkboxs.get(i).click();
    	}
    	
    }
		
	}
   
	@Test(priority=2)
	public void checkBoxTest() {
		List<WebElement>element = driver.findElements(By.xpath("//input[@name='lang' and  @type='checkbox']"));
		System.out.println("the size of the check box's is ==>"+element.size());
		
		for( int i=0; i<element.size(); i++) {
			String name= element.get(i).getAttribute("text");
			System.out.println("the name of check box's are ==>"+name);
			element.get(i).click();
			
		}
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
		
	}
}
