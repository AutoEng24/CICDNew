package Framework.Tests;


import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Framework.TestComponents.BaseTest;
import Framework.TestComponents.Retry;
import Framework.pageObjects.CartPage;
import Framework.pageObjects.ProductCatalog;

public class ErrorValidations extends BaseTest {

	@Test(groups= {"ErrorValidations"} , retryAnalyzer=Retry.class)
	//@Test
		
		public void invalidCredentials() throws InterruptedException, IOException
		{
		
		
		
		lp.loginApplication("pushpa.v@gmail.com","A2329@a");
		String ErrorMsg=lp.incorrectCredentialsError();
		 
		Assert.assertEquals(ErrorMsg, "Incorrect email or password.");
		System.out.println(ErrorMsg);
		
		
	}
	
	@Test(groups= {"ErrorValidations"} , retryAnalyzer=Retry.class)
	//@Test
	
	public void invalidProduct() throws InterruptedException, IOException
	{
	
	
	
		ProductCatalog pc=lp.loginApplication("pushpa.v@gmail.com","A232829@a");
		String productNeeded= "ZARA COAT 3";
		 
		List<WebElement> products=pc.getProducts();
		pc.getProductFromList(productNeeded);
		String itemAdded=pc.addProductToCart(productNeeded);
		
		System.out.println(itemAdded);
		
		Thread.sleep(3000);
		
		CartPage cp=pc.goToCart();
		
		
		Boolean isItemPresent=cp.verifyItemsInCart("ZARA COAT 33");
		
		Assert.assertFalse(isItemPresent); //No assertions shud be inside page object files
		
		
	
	
}

}
