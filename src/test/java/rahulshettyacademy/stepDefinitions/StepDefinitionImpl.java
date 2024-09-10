package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahlshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOut;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationpage;
	@Given("I landed on ECommerce page")
	public void I_landed_on_ECommerce_page() throws IOException
	{
		landingPage=launchApplication();
	}
	
	@Given("^logged in with username (.+) and password (.+)$") 
	// (.+) -- this is a regular expression which starts with ^ and end with $
	public void logged_in_with_username_and_password(String username, String password)
	{
		productCatalogue = landingPage.loginApplication(username,password);
	}
	
	@When("^I add the product (.+) to Cart$")
	public void I_add_the_product_to_Cart(String productName) throws InterruptedException 
	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) 
	{
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOut checkoutpage = cartPage.goToCheckout();
		checkoutpage.selectCountry("india");
		confirmationpage = checkoutpage.submitOrder();
	}
	
	@Then("{string} message is displayed on Confirmation page")
	public void message_is_displayed_on_confirmation_page(String string)
	{
		String cinfirmMessage = confirmationpage.getconfirmationMessage();
		Assert.assertTrue(cinfirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("^\"([^\"]*)\" message is displayed")
	public void  error_validation(String strArg1) throws Throwable
	{
		Assert.assertEquals(strArg1, landingPage.getErrorMessage());
		driver.close();
	}
}
