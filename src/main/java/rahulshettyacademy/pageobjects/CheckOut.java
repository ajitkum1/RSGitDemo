package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;


// Page Object Design Pattern

public class CheckOut extends AbstractComponent{
	
	WebDriver driver;  // local variable

	public CheckOut(WebDriver driver) // this is the constructor which is got executed first.
	{
		// to initialize the code at the beginning
		super(driver);  // this will send driver from child to Parent class
		this.driver=driver;
		PageFactory.initElements(driver, this); // this refers to current class driver
	}
	
	@FindBy(css= ".action__submit")
	WebElement submit;
	
	@FindBy(css= "[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath= "//button[contains(@class,'ta-item')][2]")
	WebElement selectCountry;
	
	By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(country,countryName).build().perform();
		waitForElementToAppear(By.cssSelector(".ta-results"));
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder()
	{
		submit.click();
		return new ConfirmationPage(driver);
	}
	
}


