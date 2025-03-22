package Framework.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.AbstractComponents.AbstractComponents;

public class PaymentPage extends AbstractComponents {

	WebDriver driver;
	public PaymentPage(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
    
	By findBy=By.cssSelector("input[placeholder='Select Country']");
	By dropDown=By.cssSelector(".ta-results");
	By orderBtn=By.cssSelector(".action__submit");
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement countryField;
	
	
	@FindBy(css=".action__submit")
	WebElement placeOrderBtn;
	
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement SelectCountry;

	
	public void shippingInformation(String countryName) throws InterruptedException
	
	{
		waitForElementToAppear(findBy);
		//Thread.sleep(10000);
		Actions a = new Actions(driver);

		a.sendKeys(countryField, countryName).build().perform();
		waitForElementToAppear(dropDown);
		SelectCountry.click();		
		
	}



	public ConfirmationPage placeOrder() throws InterruptedException {
		
		waitForElementToAppear(orderBtn);
		//Thread.sleep(6000);
		//placeOrderBtn.click();
		WebElement Submit = placeOrderBtn;
		
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click();", Submit);
		ConfirmationPage conf = new ConfirmationPage(driver);
		return conf;
		
	}


}
