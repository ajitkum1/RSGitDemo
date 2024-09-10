package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;


// Page Object Design Pattern

public class CartPage extends AbstractComponent{
	
	WebDriver driver;  // local variable
	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartProducts;
	
	public CartPage(WebDriver driver) // this is the constructor which is got executed first.
	{
		// to initialize the code at the beginning
		super(driver);  // this will send driver from child to Parent class
		this.driver=driver;
		PageFactory.initElements(driver, this); // this refers to current class driver
	}
	
	public Boolean VerifyProductDisplay(String productName)
	{
		Boolean match = cartProducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOut goToCheckout() {
		
		checkoutEle.click();
		return new CheckOut(driver);
				
	}

}

