package rahulshettyacademy.SeleniumFrameworkDgn;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahlshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOut;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class standAlonetest3_bkp extends BaseTest {
    String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(String email, String password, String productName) throws IOException, InterruptedException {

		ProductCatalogue productCatalogue = landingPage.loginApplication(email,password);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOut checkoutpage = cartPage.goToCheckout();
		checkoutpage.selectCountry("india");
		ConfirmationPage confirmationpage = checkoutpage.submitOrder();
		String cinfirmMessage = confirmationpage.getconfirmationMessage();
		Assert.assertTrue(cinfirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		// driver.close();

	}

	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest() 
	{
		ProductCatalogue productCatalogue = landingPage.loginApplication("ajitkum@abc.com","@Ajit123");
		OrderPage ordpage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordpage.VerifyOrderDisplay("ZARA COAT 3"));
		
	}
	// The below DataProvider provide the data for all test in the particular class.
	// @DataProvider --> help to drive the data and pass the multiple data sets.
	
	// Object --is a generic data type which accepts all types of data types (int,float,double,string etc..)
	
	// below data will run with 2 data set of emailId.
	@DataProvider 
	public Object[][] getData()
	{
		return new Object[][] {{"ajitkum@abc.com","@Ajit123","ZARA COAT 3"},{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}}; //two dimensional array
	}

}