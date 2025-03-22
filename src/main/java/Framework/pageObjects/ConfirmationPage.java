package Framework.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Framework.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {

	
	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	//driver.findElement(By.cssSelector(".hero-primary")).getText();
	@FindBy(css=".hero-primary")
	WebElement confirmMessage;
	
	


	
	public String getConfirmMessage()
	
	{
		String confirmationMsg=confirmMessage.getText();
		return confirmationMsg;

		

		
		
	}

	

	
	

}
