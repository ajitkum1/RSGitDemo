package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;


// Page Object Design Pattern

public class OrderPage extends AbstractComponent{
	
	WebDriver driver;  // local variable
	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	public OrderPage(WebDriver driver) // this is the constructor which is got executed first.
	{
		// to initialize the code at the beginning
		super(driver);  // this will send driver from child to Parent class
		this.driver=driver;
		PageFactory.initElements(driver, this); // this refers to current class driver
	}
	
	public Boolean VerifyOrderDisplay(String productName)
	{
		Boolean match = productNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOut goToCheckout() {
		
		checkoutEle.click();
		return new CheckOut(driver);
				
	}

}

