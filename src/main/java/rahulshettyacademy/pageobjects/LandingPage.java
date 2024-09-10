package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;


// Page Object Design Pattern

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;  // local variable
	
	public LandingPage(WebDriver driver) // this is the constructor which is got executed first.
	{
		// to initialize the code at the beginning
		super(driver);  // this will send driver from child to Parent class
		this.driver=driver;
		PageFactory.initElements(driver, this); // this refers to current class driver
	}
	
	//WebElement userEmail = driver.findElement(By.cssSelector("input[id='userEmail']"));
	
	//PageFactory
	@FindBy(id="userEmail")
	WebElement userEmail;

	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	//Action Method
	
	public ProductCatalogue loginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String getErrorMessage()
	{	
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}

