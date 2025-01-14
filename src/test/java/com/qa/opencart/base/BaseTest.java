package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchPage;
import com.qa.opencart.pages.SearchPage;

public class BaseTest {

	WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	protected LoginPage lp;
	protected AccountsPage accPage;
	protected SearchPage searchPage;
	protected ProductInfoPage productInfoPage;
	protected SoftAssert softAssert;

	@BeforeTest
	public void setUp() {
		df = new DriverFactory();
		prop = df.getProperties();
		driver = df.initDriver(prop);
		lp = new LoginPage(driver);
		softAssert = new SoftAssert();
	}

	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
