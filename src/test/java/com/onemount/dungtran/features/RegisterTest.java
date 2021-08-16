package com.onemount.dungtran.features;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import static com.onemount.dungtran.common.Assert.*;
import com.onemount.dungtran.common.WebDriverTest;
import com.onemount.dungtran.models.Member;
import com.onemount.dungtran.utils.RandomUtils;

public class RegisterTest extends WebDriverTest {

	String appUrl = "https://secure.vietnamworks.com/register/vi?client_id=3";
	String xpathRegister = "//input[@id='%s']/following-sibling::span[@class='invalid-feedback']";
	Member memberRegister = new Member();

	public RegisterTest() {
		getUrl(appUrl);

	}

	private void createAnAccount(Member member) throws InterruptedException {
		element(By.id("firstname")).sendKeys(member.getFirstname());
		element(By.id("lastname")).sendKeys(member.getLastname());
		element(By.id("username")).sendKeys(member.getEmail());
		element(By.id("password")).sendKeys(member.getPassword());
		findElement(By.id("button-register")).click();
		TimeUnit.SECONDS.sleep(3);
	}

	@Test
	public void runTestRegister() throws InterruptedException {
		openFileWriter("register", false);
		registerBlankFirstname();
		registerBlankLastname();
		registerBlankEmail();
		registerInValidEmail();
		registerBlankPassword();
		registerShortPassword();
		registerInvalidPassword();
		registerSuccess();
		closeFileWriter();
		driver.quit();
		System.out.println("\n END!");

	}

	private void registerBlankFirstname() throws InterruptedException {
		String expectText = "Vui lòng nhập Tên.";
		String message = "Register Blank First name: ";
		findElement(By.id("button-register")).click();
		assertEquals(getText(xpathRegister, "firstname"), expectText, message);
	}

	private void registerBlankLastname() throws InterruptedException {
		String expectText = "Vui lòng nhập Họ.";
		String message = "Register Blank Last name: ";
		Member member = new Member(RandomUtils.randomText(8));
		createAnAccount(member);
		assertEquals(getText(xpathRegister, "lastname"), expectText, message);

	}

	private void registerBlankEmail() throws InterruptedException {
		String expectText = "Vui lòng nhập Email";
		String message = "Register Blank Email: ";
		Member member = new Member(RandomUtils.randomText(8), RandomUtils.randomText(8));
		createAnAccount(member);
		assertEquals(getText(xpathRegister, "username"), expectText, message);

	}

	private void registerBlankPassword() throws InterruptedException {
		String expectText = "Vui lòng nhập Mật Khẩu";
		String message = "Register Blank Password: ";
		memberRegister.setPassword("");
		createAnAccount(memberRegister);
		assertEquals(getText(xpathRegister, "password"), expectText, message);

	}

	private void registerInValidEmail() throws InterruptedException {
		String expectText = "Email không hợp lệ.";
		String message = "Register with Invalid Email: ";
		memberRegister.setEmail(RandomUtils.randomText(8));
		createAnAccount(memberRegister);
		assertEquals(getText(xpathRegister, "username"), expectText, message);
	}

	private void registerInvalidPassword() throws InterruptedException {
		String expectText = "Mật Khẩu không hợp lệ";
		String message = "Register with Invalid Password: ";
		memberRegister.setPassword(RandomUtils.randomText(8));
		createAnAccount(memberRegister);
		assertEquals(getText(xpathRegister, "password"), expectText, message);
	}

	private void registerShortPassword() throws InterruptedException {
		String expectText = "Mật Khẩu phải có ít nhất 6 ký tự.";
		String message = "Register with Short Password: ";
		memberRegister.setPassword(RandomUtils.randomText(5));
		createAnAccount(memberRegister);
		assertEquals(getText(xpathRegister, "password"), expectText, message);
	}

	private void registerSuccess() throws InterruptedException {
		String urlConfirmRegister = "https://secure.vietnamworks.com/register/vi/confirm?client_id=3";
		String message = "Register Success: ";
		Member member = new Member();
		createAnAccount(member);
		assertContains(driver.getCurrentUrl(), urlConfirmRegister, message);
	}

}
