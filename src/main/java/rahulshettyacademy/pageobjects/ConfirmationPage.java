package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{

	
	WebDriver driver;  // local variable

	public ConfirmationPage(WebDriver driver) // this is the constructor which is got executed first.
	{
		// to initialize the code at the beginning
		super(driver);  // this will send driver from child to Parent class
		this.driver=driver;
		PageFactory.initElements(driver, this); // this refers to current class driver
	}
	
	@FindBy(css= ".hero-primary")
	WebElement confirmationMessage;
	
	public String getconfirmationMessage()
	{
		return confirmationMessage.getText();
	}
}
