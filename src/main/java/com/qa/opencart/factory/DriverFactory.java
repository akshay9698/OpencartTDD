package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.aspectj.util.FileUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser").toLowerCase().trim();
		System.out.println("Browser name is: " + browserName);

		if (browserName.equalsIgnoreCase("chrome")) {
			// driver = new ChromeDriver();
			tlDriver.set(new ChromeDriver());
		} else if (browserName.equalsIgnoreCase("edge")) {
			// driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver());
		} else if (browserName.equalsIgnoreCase("firefox")) {
			// driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver());
		} else {
			System.out.println("Please enter correct browser name: " + browserName);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	public Properties getProperties() {
		prop = new Properties();
		try {
			FileInputStream file = new FileInputStream("src\\test\\resources\\config\\config.properties");
			prop.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
	}

	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtil.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
