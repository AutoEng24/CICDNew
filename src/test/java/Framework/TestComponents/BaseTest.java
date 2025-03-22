package Framework.TestComponents;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Framework.pageObjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage lp;
	
	public WebDriver initializeDriver() throws IOException
	{
		
		
		
		Properties prop= new Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Framework//resources//application.properties");
		prop.load(fis);	
		
		//mvn test -P errorValidations -D browser=firefox  - command to run test and pass browser vaIue during runtime from command prompt
		String browserName= System.getProperty("browser")!=null ?System.getProperty("browser") : prop.getProperty("browserName");
		
		//String browserName = prop.getProperty("browserName");

		if(browserName.contains("chrome"))
		{
		    System.setProperty("webdriver.chrome.driver", "C:/Users/Admin/Documents/Automation/Chromedriver/chromedriver.exe");
		    ChromeOptions options = new ChromeOptions();
		    options.addExtensions(new File("C:/Users/Admin/Documents/Automation/Selectorhub/NDGIMIBANHLABGDGJCPBBNDIEHLJCPFH_5_2_4_0.crx"));
		    if(browserName.contains("headless"))
		    {
		     options.addArguments("headless"); 
		    }
		    driver= new ChromeDriver(options);
		    driver.manage().window().fullscreen();
		}
		else if(browserName.equalsIgnoreCase("fireFox"))
			
		{
			
			System.out.println("Firefox");
			//firefox
		}
        else if(browserName.equalsIgnoreCase("edge"))
			
		{
			//edge
		}	
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		driver.manage().window().maximize();
		return driver;
		
		
		
	}
	
	public String getScreenshot(String testCaseName , WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest= new File(System.getProperty("user.dir")+"//reports//"+ testCaseName +".png");
		FileUtils.copyFile(src, dest);
		return System.getProperty("user.dir")+"//reports//"+ testCaseName +".png";

	}
	
	public List<HashMap<String, String>> getJsonDataMap(String fileName) throws IOException
	{
		
		String jsonContent=FileUtils.readFileToString(new File(fileName), StandardCharsets.UTF_8);
		
		ObjectMapper objmap= new ObjectMapper();
		List<HashMap<String, String>> data= objmap.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
		
	}
	
	
	
	
	
	@BeforeMethod(alwaysRun= true)
	public LandingPage launchApplication() throws IOException
	{
		
		driver=initializeDriver();
		lp= new LandingPage(driver);
		lp.goTo();
		return lp;
		
	}
	
	@AfterMethod(alwaysRun= true)
	public void closeDriver() throws IOException
	{
		
		driver.close();
		
	}

}
