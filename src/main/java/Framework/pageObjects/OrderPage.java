package Framework.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import Framework.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {

	WebDriver driver;
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
    
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderedProducts;
	
	
	
    
	public Boolean verifyItemsOrderHistory( String productNeeded)
	{
		
		
		
		Boolean isItemPresent=orderedProducts.stream().anyMatch(p->p.getText().equalsIgnoreCase(productNeeded));
		
		return isItemPresent;
		
	}
	
	
	
}
