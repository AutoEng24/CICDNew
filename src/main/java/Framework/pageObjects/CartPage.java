package Framework.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import Framework.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
    By findBy= By.cssSelector("li[class=\"totalRow\"] button");
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	
	
	@FindBy(css="li[class='totalRow'] button")
	WebElement checkOut;
	
	
    
	public Boolean verifyItemsInCart( String productNeeded)
	{
		
		
		
		Boolean isItemPresent=cartProducts.stream().anyMatch(p->p.getText().equalsIgnoreCase(productNeeded));
		
		return isItemPresent;
		
	}
	
	public PaymentPage goToCheckOut()
	{
		
		waitForElementToAppear(findBy);
		
		checkOut.click();
		PaymentPage pp=new PaymentPage(driver);
        return pp;
		
	}
	
}
