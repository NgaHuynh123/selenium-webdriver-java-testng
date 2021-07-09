package xpath;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Xpath_Exercise_01 {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}

	@Test
	public void TC_01_Login_With_Empty_Email_And_Password() {
		driver.findElement(By.id("email")).sendKeys();
		driver.findElement(By.id("pass")).sendKeys();
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),
				"This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),
				"This is a required field.");

	}

	@Test
	public void TC_02_Login_With_Invalid_Email() {

		driver.navigate().refresh();
		driver.findElement(By.id("email")).sendKeys("1111@11.111");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),
				"Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TC_03_Login_With_Password_Less_Character() {
		driver.navigate().refresh();
		driver.findElement(By.id("email")).sendKeys("nhuynh123@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),
				"Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test
	public void TC_04_Login_With_Incorrect_Email_Or_Password() {
		driver.navigate().refresh();
		driver.findElement(By.id("email")).sendKeys("nhuynh123@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("1234567");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),
				"Invalid login or password.");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();

	}

}
