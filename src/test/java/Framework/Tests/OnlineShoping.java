package Framework.Tests;

import java.io.File;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class OnlineShoping {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:/Users/Admin/Documents/Automation/Chromedriver/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addExtensions(new File("C:/Users/Admin/Documents/Automation/Selectorhub/NDGIMIBANHLABGDGJCPBBNDIEHLJCPFH_5_2_4_0.crx"));
		WebDriver driver= new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		
		driver.manage().window().maximize();		
		
		
		
		driver.findElement(By.id("userEmail")).sendKeys("pushpa.v@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("A232829@a");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(50) );
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[routerlink*='cart']"))));
		
		
		String productNeeded= "IPHONE 13 PRO";
		List<WebElement> products=driver.findElements(By.cssSelector(".col-lg-4"));  //.findFirst().orElse(null);
		
		WebElement prod=products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals("IPHONE 13 PRO")).findAny().orElse(null);
		
		prod.findElement(By.cssSelector("button[class='btn w-10 rounded']")).click();
		
		
		//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[routerlink*='cart']"))));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		System.out.println(driver.findElement(By.cssSelector("#toast-container")).getText());   
		
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean isItemPresent=cartProducts.stream().anyMatch(p->p.getText().equalsIgnoreCase(productNeeded));
		/*SoftAssert sa = new SoftAssert();
		sa.assertEquals(isItemPresent, true, "Selected Product Listed in Cart");
		sa.assertAll();*/
		Assert.assertTrue(isItemPresent);
		
		System.out.println(isItemPresent);
		
		driver.findElement(By.cssSelector("li[class=\"totalRow\"] button")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Select Country\"]")));
		
		Actions a = new Actions(driver);

		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		Thread.sleep(8000);
		
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		WebElement Submit = driver.findElement(By.cssSelector(".action__submit"));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click();", Submit);

		//driver.findElement(By.cssSelector(".action__submit")).click();

		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		
		
		/*Actions a = new Actions(driver);
		
		//a.sendKeys(driver.findElement(By.cssSelector("input[placeholder=\"Select Country\"]")), "India").build().perform();
		WebElement move =driver.findElement(By.cssSelector("input[placeholder=\"Select Country\"]"));
		//a.moveToElement(move).click().sendKeys("India").build().perform();
		move.sendKeys("India");
		move.sendKeys(Keys.RETURN);
		WebElement Submit = driver.findElement(By.cssSelector(".action__submit"));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click();", Submit);*/
		
		
		
		
		
		
		//driver.findElement(By.cssSelector("button.ta-item:nth-of-type(2)")).click();
		
		//a.moveToElement(move).click().keyDown(move, Keys.ENTER).build().perform();
		//a.moveToElement(move).sendKeys(Keys.ENTER).build().perform();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit")));
		//Thread.sleep(5000);
		//driver.findElement(By.cssSelector(".action__submit")).click();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.ta-item:nth-of-type(2)")));
		
		//Thread.sleep(5000);
		
		//button.ta-item:nth-of-type(2)       
		//driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		//driver.findElement(By.cssSelector("button.ta-item:nth-of-type(2)")).click();
		
		
		
		//driver.findElement(By.cssSelector("input[placeholder=\"Select Country\"]")).sendKeys("ind");
		
		
		/*List<WebElement> countries=driver.findElements(By.cssSelector("button[class='ta-item list-group-item ng-star-inserted']"));
		
		for(WebElement country:countries)
		{ 
			if(country.getText().equalsIgnoreCase("INDIA"))
			{
				System.out.println(country.getText());
				Thread.sleep(10000);
				//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
				country.click();
				
			}

		}
		
		
		/*String[] itemsNeeded= {"ZARA COAT 3"};
		List<WebElement> products=driver.findElements(By.cssSelector(".col-lg-4")); 
		List itemsList= Arrays.asList(itemsNeeded); 
		
		int j=0;
		for(int i=0;i<products.size();i++)
		{   
			
			//b[text()='ZARA COAT 3']
			String itemfound=products.get(i).findElement(By.xpath("//div[@class='card-body']/h5/b")).getText(); //div[class*='col-lg-4'] 
			System.out.println(itemfound);
			
			if(itemsList.contains(itemfound))
			{
				
				//driver.findElement(By.xpath("//button[text()='Add To Cart']")).click();
				products.get(i).findElement(By.cssSelector("button[class='btn w-10 rounded']")).click();
				j++;
				if(j==itemsList.size())
					break;
				
			}
			
			
		}*/
		
		/*WebElement Submit = driver.findElement(By.cssSelector(".action__submit"));:

			This line finds a web element on the web page using a CSS selector.

			driver is an instance of the Selenium WebDriver, representing the browser session.

			findElement(By.cssSelector(".action__submit")) is a method that locates the HTML element on the web page based on a CSS selector. In this case, it looks for an element with the class name "action__submit".

			The located element is assigned to the variable Submit, which is of type WebElement.

			JavascriptExecutor js = (JavascriptExecutor) driver;:

			This line creates an instance of the JavascriptExecutor interface, allowing execution of JavaScript code within the context of the WebDriver.

			It casts the WebDriver instance driver to JavascriptExecutor interface type.

			js.executeScript("arguments[0].click();", Submit);:

			This line executes JavaScript code to click on the previously located WebElement.

			executeScript() is a method provided by the JavascriptExecutor interface to execute JavaScript code.

			The first argument is the JavaScript code to be executed, in this case, arguments[0].click();. This code clicks on the element passed as the second argument (Submit).

			arguments[0] refers to the first argument passed to executeScript(), which is the WebElement Submit.

			.click() is a JavaScript method to simulate a click event on the element.*/
			
	
		
	}

}
