package rahulshettyacademy.SeleniumFrameworkDgn;

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

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class standAlonetest {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize(); //maximize Window
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://rahulshettyacademy.com/client");
		
		// Create an object for the Landing page class --  For landing Page access
		LandingPage landingPage = new LandingPage(driver);
		
		//driver.findElement(By.cssSelector("input[id='userEmail']")).sendKeys("ajitkum@abc.com");
		//driver.findElement(By.cssSelector("input[id='userPassword']")).sendKeys("@Ajit123");
		
		driver.findElement(By.id("userEmail")).sendKeys("ajitkum@abc.com");
		driver.findElement(By.id("userPassword")).sendKeys("@Ajit123");
		driver.findElement(By.id("login")).click();
		//driver.findElement(By.cssSelector("input[value='Login']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		//div[class='row']
		
		List<WebElement> items = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement itm = items.stream().filter(item-> 
		item.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		
		itm.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
		
		
		List<WebElement> prod = driver.findElements(By.cssSelector("div[class='cart'] h3"));
		Boolean match = prod.stream().anyMatch(prd->prd.getText().contains("ZARA COAT 3"));
		Assert.assertTrue(match);
		
	/*	driver.findElement(By.cssSelector("div[class='subtotal cf ng-star-inserted'] button[class='btn btn-primary']")).click();
		
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
		
		List<WebElement> options = driver.findElements(By.cssSelector("button.ta-item.list-group-item.ng-star-inserted"));

		for (WebElement option : options) {
			if (option.getText().equalsIgnoreCase("India")) {
				option.click();
				break;
			}
		}
		
		driver.findElement(By.cssSelector("a[class='btnn action__submit ng-star-inserted']")).click();
		
		System.out.println(driver.findElement(By.cssSelector("tr[class='ng-star-inserted']")).getText().trim()); */
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String msg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		Assert.assertTrue(msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
	}

}
