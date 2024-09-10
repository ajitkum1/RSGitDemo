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
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahlshettyacademy.TestComponents.BaseTest;
import rahlshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOut;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidation extends BaseTest {

	@Test (groups = {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void standAloneTest1() throws IOException, InterruptedException
	{
		
		String productName = "ZARA COAT 3";

		landingPage.loginApplication("ajitkum@abc.com", "@Ajit123");

		//Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		Assert.assertEquals("Login Successfully", landingPage.getErrorMessage());
	}
	
	@Test
	
	public void productErrorValidation() throws IOException, InterruptedException
	{
		
		String productName = "ZARA COAT 3";
		//LandingPage landingPage = launchApplication();
		ProductCatalogue productCatalogue = landingPage.loginApplication("ajitkum@abc.com", "@Ajit123");
		List<WebElement> products= productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 3");
		Assert.assertTrue(match);
	
		
	}

}
