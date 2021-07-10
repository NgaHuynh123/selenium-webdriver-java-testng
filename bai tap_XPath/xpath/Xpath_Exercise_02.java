package xpath;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Xpath_Exercise_02 {
	WebDriver driver;

//
	By nameTextBoxBy = By.id("txtFirstname");
	By emailTextBoxBy = By.id("txtEmail");
	By confirmEmailTextBoxBy = By.id("txtCEmail");
	By passwordTextBoxBy = By.id("txtPassword");
	By ConfirmPasswordTextBox = By.id("txtCPassword");
	By phoneNumberTextBox = By.id("txtPhone");
	By signButtonBy = By.xpath("//div[@class='form frmRegister']//button");

//Error message
	By nameErrorMsgBy = By.id("txtFirstname-error");
	By emailErrorMsgBy = By.id("txtEmail-error");
	By confirmEmailErrorMsgBy = By.id("txtCEmail-error");
	By passwordErrorMsgBy = By.id("txtPassword-error");
	By confirmPasswordErrorMsgBy = By.id("txtCPassword-error");
	By phoneNumberErrorMsgBy = By.id("txtPhone-error");

	@BeforeTest
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	@Test
	public void TC_01_Register_With_Empty_Data() {
		driver.findElement(By.xpath("//div[@class='form frmRegister']//button")).click();
		Assert.assertEquals(driver.findElement(nameErrorMsgBy).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(emailErrorMsgBy).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(confirmEmailErrorMsgBy).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(passwordErrorMsgBy).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(confirmPasswordErrorMsgBy).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(phoneNumberErrorMsgBy).getText(), "Vui lòng nhập số điện thoại.");

	}

	@Test
	public void TC_02_Register_With_invalid_Email() {

		driver.findElement(nameTextBoxBy).sendKeys("nga");
		driver.findElement(emailTextBoxBy).sendKeys("123.123");
		driver.findElement(confirmEmailTextBoxBy).sendKeys("123.123");
		driver.findElement(passwordTextBoxBy).sendKeys("1234567");
		driver.findElement(ConfirmPasswordTextBox).sendKeys("1234567");
		driver.findElement(phoneNumberTextBox).sendKeys("0905124421");
		driver.findElement(By.xpath("//div[@class='form frmRegister']")).click();
		// Assert.assertEquals(driver.findElement(emailErrorMsgBy).getText(),"Vui lòng
		// nhập email hợp lệ" );
		Assert.assertEquals(driver.findElement(emailErrorMsgBy).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(confirmEmailErrorMsgBy).getText(), "Email nhập lại không đúng");

	}

	@Test
	public void TC_03_Register_With_Incorrect_Confirm_Email() {
		driver.findElement(nameTextBoxBy).sendKeys("nga");
		driver.findElement(emailTextBoxBy).sendKeys("nhuynh@gmail.com");
		driver.findElement(confirmEmailTextBoxBy).sendKeys("huynh@gmail.com");
		driver.findElement(passwordTextBoxBy).sendKeys("1111111");
		driver.findElement(ConfirmPasswordTextBox).sendKeys("1111111");
		driver.findElement(phoneNumberTextBox).sendKeys("0905123456");
		driver.findElement(signButtonBy).click();
		Assert.assertEquals(driver.findElement(confirmEmailErrorMsgBy).getText(), "Email nhập lại không đúng");

	}

	@Test
	public void TC_04_Register_With_Password_Less_Than_6_Characters() {
		driver.findElement(nameTextBoxBy).sendKeys("nga");
		driver.findElement(emailTextBoxBy).sendKeys("nhuynh@gmail.com");
		driver.findElement(confirmEmailTextBoxBy).sendKeys("nhuynh@gmail.com");
		driver.findElement(passwordTextBoxBy).sendKeys("1234");
		driver.findElement(ConfirmPasswordTextBox).sendKeys("1234");
		driver.findElement(phoneNumberTextBox).sendKeys("0905123456");
		driver.findElement(signButtonBy).click();
		Assert.assertEquals(driver.findElement(passwordErrorMsgBy).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(confirmPasswordErrorMsgBy).getText(),
				"Mật khẩu phải có ít nhất 6 ký tự");

	}

	@Test
	public void TC_05_Register_With_Incorrect_Confirm_Password() {

		driver.findElement(nameTextBoxBy).sendKeys("nga");
		driver.findElement(emailTextBoxBy).sendKeys("nga@gmail.com");
		driver.findElement(confirmEmailTextBoxBy).sendKeys("nga@gmail.com");
		driver.findElement(passwordTextBoxBy).sendKeys("111111");
		driver.findElement(ConfirmPasswordTextBox).sendKeys("222222");
		driver.findElement(phoneNumberTextBox).sendKeys("0905123456");
		driver.findElement(signButtonBy).click();
		Assert.assertEquals(driver.findElement(confirmPasswordErrorMsgBy).getText(), "Mật khẩu bạn nhập không khớp");

	}

	@Test
	public void TC_06_Register_With_Invalid_Phone_Number() {
		driver.findElement(emailTextBoxBy).sendKeys("nga@gmail.com");
		driver.findElement(confirmEmailTextBoxBy).sendKeys("nga@gmail.com");
		driver.findElement(passwordTextBoxBy).sendKeys("111111");
		driver.findElement(ConfirmPasswordTextBox).sendKeys("111111");
		driver.findElement(phoneNumberTextBox).sendKeys("11111111");
		driver.findElement(signButtonBy).click();
		Assert.assertEquals(driver.findElement(phoneNumberErrorMsgBy).getText(),
				"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
