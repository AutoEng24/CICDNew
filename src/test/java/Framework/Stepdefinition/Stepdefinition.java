package Framework.Stepdefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Framework.TestComponents.BaseTest;
import Framework.pageObjects.CartPage;
import Framework.pageObjects.ConfirmationPage;
import Framework.pageObjects.LandingPage;
import Framework.pageObjects.PaymentPage;
import Framework.pageObjects.ProductCatalog;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class Stepdefinition extends BaseTest {
	public LandingPage landingpage;
	public ProductCatalog pc;
	public ConfirmationPage conf ;
	 @Given("User landed on Ecommerce app")
	 public void User_landed_on_Ecommerce_app() throws IOException
	 {
		 landingpage=launchApplication();
		 
	 }
	@Given("^Login to the app with (.+) and (.+)$")
	public void Login_username_pwd(String username,String password)
	{
		 pc=lp.loginApplication(username,password);
	}
	
	@When("^Add product (.+) to the cart$")
	public void Add_product_to_cart(String product)
	{
        List<WebElement> products=pc.getProducts();
		
		pc.addProductToCart(product);
	}
	
	@And("^Checkout product (.+) and submit the order$")
	public void Checkout_and_submitOrder(String product) throws InterruptedException
	{
		CartPage cp=pc.goToCart();
		Boolean isItemPresent=cp.verifyItemsInCart(product);
		Assert.assertTrue(isItemPresent); //No assertions shud be inside page object files
		System.out.println(isItemPresent);
		PaymentPage pp=cp.goToCheckOut();	
		String countryName="India";
		pp.shippingInformation(countryName);
		conf =pp.placeOrder();
	}
	
	@Then("{string} confirmation message is displayed in Confirmation page")
	public void verify_confirmation_message(String Successmsg)
	{
		String confirmationMsg=conf.getConfirmMessage();
		Assert.assertTrue(confirmationMsg.equalsIgnoreCase(Successmsg));
		driver.close();
		
	}
	@Then("{string} error message is displayed in Confirmation page")
	public void verify_IncorrectCredentials_message(String expectedErrorMsg) 
	{
		Assert.assertEquals(lp.incorrectCredentialsError(), expectedErrorMsg);
		System.out.println(lp.incorrectCredentialsError());
		driver.close();
	}
	
}
