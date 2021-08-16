package com.onemount.dungtran.features;

import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.openqa.selenium.By;
import static com.onemount.dungtran.common.Assert.*;

import com.onemount.dungtran.common.WebDriverTest;
import com.onemount.dungtran.models.User;
import com.onemount.dungtran.utils.RandomUtils;

public class LoginTest extends WebDriverTest {

	String appUrl = "https://id.zing.vn/";
	String xpathLogin = "//div[@id='%s']/descendant::div[@class='tipcontent']";
	String expectBlankText = "Bạn cần nhập thông tin này";
	public LoginTest() {
		getUrl(appUrl);
	}

	private void login(User user) throws InterruptedException {

		element(By.id("login_account")).sendKeys(user.getUsername());
		element(By.id("login_pwd")).sendKeys(user.getPassword());
		findElement(By.className("zidsignin_btn")).click();
		TimeUnit.SECONDS.sleep(3);
	}

	@Test
	public void runTestLogin() throws InterruptedException {

		checkTitle();
		loginBlankUsername();
		loginBlankPassword();
		loginInvalidPassword();
		loginWrongPassword();
		loginSuccess();
		System.out.println("End");
		driver.quit();
	}

	private void checkTitle() throws InterruptedException {
		String textTitle = "Zing ID - Zing Passport - Tài khoản Zing của VNG";
		String message = "Check title page: ";
		assertEquals(textTitle, driver.getTitle(), message);
	}

	private void loginSuccess() throws InterruptedException {
		String infosettingUrl = "id.zing.vn/v2/infosetting";
		String message = "Login Success: ";
		User user = new User("dung.ttt99", "Dung12345@");
		login(user);
		assertContains(driver.getCurrentUrl(), infosettingUrl, message);

	}

	private void loginBlankUsername() throws InterruptedException {
		String message = "Login Blank Username: ";
		findElement(By.className("zidsignin_btn")).click();
		assertEquals(getText(xpathLogin, "login_account_error"), expectBlankText, message);
	}

	private void loginBlankPassword() throws InterruptedException {
		String message = "Login Blank Password: ";
		User user = new User(RandomUtils.randomText(8));
		login(user);
		assertEquals(getText(xpathLogin, "login_pwd_error"), expectBlankText, message);
	}

	private void loginInvalidPassword() throws InterruptedException {
		String expectText = "Vui lòng nhập mật khẩu dài 6-32 ký tự, có ký tự chữ số, chữ hoa và chữ thường";
		String message = "Login Invalid Password: ";
		User user = new User("", RandomUtils.randomText(4));
		login(user);
		String actualText = getText(xpathLogin, "login_pwd_error");
		assertEquals(actualText, expectText, message);
	}

	private void loginWrongPassword() throws InterruptedException {
		String expectText = "Tài khoản hoặc mật khẩu không đúng";
		String message = "Login Wrong Password: ";
		login(new User());
		assertEquals(getText(xpathLogin, "login_error"), expectText, message);
	}

}
