package com.onemount.dungtran.common;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverTest {
	protected static WebDriver driver;

	public void getUrl(String url) {
		String userDir = System.getProperty("user.dir");
		Path path = Paths.get(userDir).getParent();
		String chromedriverPath = path.toString().concat("\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chromedriverPath);
		driver = new ChromeDriver();
		driver.get(url);
	}

	public WebElement findElement(By by) {
		return driver.findElement(by);
	}

	public WebElement element(By by) {
		WebElement element = driver.findElement(by);
		element.clear();
		return element;
	}

	public String getText(String xpath, String nameElement) {
		By by = By.xpath(String.format(xpath, nameElement));
		return findElement(by).getText();
	}

}