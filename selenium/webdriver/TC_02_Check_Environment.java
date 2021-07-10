package webdriver;

import java.sql.Driver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_02_Check_Environment {
	// khai bao 1 bien dai dien cho Selenium WedDriver
	WebDriver driver;

	@BeforeClass
	// mo man hinh trinh duyet

	// set timeout tim element
	// mo application len

	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}

	@Test
	public void TC_O1_Test() {
driver.findElement(By.className(null)).click();
	}
	@Test
	public void TC_02_test() {
List<WebElement> buttons = driver.findElement(By.className(""));
buttons.get(0).click();
	}

	@Test
	public void TC_03_test() {

	}

	@AfterClass
	// Clean data test
	// Close browser
	public void afterClass() {
		driver.quit();
	}
}