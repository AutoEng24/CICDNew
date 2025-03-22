package Framework.Tests;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Framework.TestComponents.BaseTest;
import Framework.pageObjects.CartPage;
import Framework.pageObjects.ConfirmationPage;
import Framework.pageObjects.OrderPage;
import Framework.pageObjects.PaymentPage;
import Framework.pageObjects.ProductCatalog;

public class OnlineShoppingModified extends BaseTest {
	
	//String productNeeded= "IPHONE 13 PRO";

	@Test(dataProvider= "getData",groups= {"Purchase"})
		
		public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException
		{
		
		
		
		ProductCatalog pc=lp.loginApplication(input.get("email"), input.get("pwd") );
		
		 
		List<WebElement> products=pc.getProducts();
		
		String itemAdded=pc.addProductToCart(input.get("product"));
		
		System.out.println(itemAdded);
		
		Thread.sleep(3000);
		
		CartPage cp=pc.goToCart();
		
		Boolean isItemPresent=cp.verifyItemsInCart(input.get("product"));
		
		Assert.assertTrue(isItemPresent); //No assertions shud be inside page object files
		
		System.out.println(isItemPresent);
		
		PaymentPage pp=cp.goToCheckOut();	
		String countryName="India";
		pp.shippingInformation(countryName);
		ConfirmationPage conf =pp.placeOrder();
		
		String confirmationMsg=conf.getConfirmMessage();
		 
		Assert.assertTrue(confirmationMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
		
	}
	@Test(dependsOnMethods= {"submitOrder"})
	
	public void orderHistory() throws InterruptedException, IOException
	{
	
	String productNeeded= "ZARA COAT 3";
	
	ProductCatalog pc=lp.loginApplication("pushpa.v@gmail.com","A232829@a");
	OrderPage op= pc.goToOrderHistory();
	Thread.sleep(3000);
	Boolean isItemPresent=op.verifyItemsOrderHistory(productNeeded);
	Thread.sleep(3000);
	Assert.assertTrue(isItemPresent);
	
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		//HashMap<String,String> map= new HashMap<String,String>();
		//map.put("email", "pushpa.v@gmail.com");
		//map.put("pwd", "A232829@a");
		//map.put("product", "ZARA COAT 3");
		
		//HashMap<String,String> map1= new HashMap<String,String>();
		//map1.put("email", "pushpa.v@gmail.com");
		//map1.put("pwd", "A232829@a");
		//map1.put("product", "IPHONE 13 PRO");
		
		//return new Object[][] {{map},{map1}}; 
		
		List<HashMap<String, String>> data=getJsonDataMap(System.getProperty("user.dir")+"\\src\\test\\java\\Framework\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	} 
	

}
