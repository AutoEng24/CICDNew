package Framework.pageObjects;

import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.AbstractComponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents {

	WebDriver driver;
	public ProductCatalog(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
    
	//List<WebElement> products=driver.findElements(By.cssSelector(".col-lg-4")); 
	
	
	@FindBy(css=".col-lg-4")
	List<WebElement>  products;
	@FindBy(css="#toast-container")
	WebElement  tostMsg;

	
	
	
	By addToCart=By.cssSelector("button[class='btn w-10 rounded']");
	
	
	By findProductsBy= By.cssSelector(".col-lg-4");
	public List<WebElement> getProducts()
	{
		waitForElementToAppear(findProductsBy);
		return products;
	}
	
	public WebElement getProductFromList(String productNeeded)
    {
		System.out.println("getProductFromList method");
		WebElement prod = getProducts().stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productNeeded)).findFirst().orElse(null);
		return prod;
	}
	By spinner=By.cssSelector(".ng-animating");
	public String addProductToCart( String productNeeded)
	{
		WebElement prod = getProductFromList(productNeeded);
		System.out.println(prod.getText());
		if(prod!= null) 
		{
		System.out.println("Item found");
		prod.findElement(addToCart).click();
		}
		else if(prod== null) 
		{
		System.out.println("Item not found");
		}
		waitForElementToDisappear(spinner);
		//System.out.println(tostMsg.getText());
		return tostMsg.getText();
	}
    
	
	
}
