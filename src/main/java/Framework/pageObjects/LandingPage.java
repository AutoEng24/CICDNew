package Framework.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
    
	//WebElement userEmail= driver.findElement(By.id("userEmail"));
	
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	//WebElement password = driver.findElement(By.id("userPassword"));
	
	@FindBy(id="userPassword")
	WebElement password;
	
	//WebElement submit=driver.findElement(By.id("login")).click();
	
	@FindBy(id="login")
	WebElement submit;
	//div[@class='ng-tns-c4-4 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error']

	@FindBy(css="[class*='toast-message']")
	WebElement loginError;

	
	public void goTo()
	
	{
		driver.get("https://rahulshettyacademy.com/client/");
		
		
	}



	public ProductCatalog loginApplication(String user, String pwd) {
		userEmail.sendKeys(user);
		password.sendKeys(pwd);
		submit.click();
		ProductCatalog pc = new ProductCatalog(driver);
		return pc;
	}
	
	public String incorrectCredentialsError()
	
	{
		waitForWebElementToAppear(loginError);
		return loginError.getText();
		
	}
	


}
