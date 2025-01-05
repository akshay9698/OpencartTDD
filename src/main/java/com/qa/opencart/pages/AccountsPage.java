package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By logoutLink = By.linkText("Logout");
	private By accHeaders = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcn = By.cssSelector("#search button");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getAccountsPageTitle() {
		String title = eleUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT,
				AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
		System.out.println("Accounts page title is: " + title);
		return title;
	}

	public String getAccountsPageUrl() {
		String url = eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT,
				AppConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE);
		System.out.println("Accounts page url is: " + url);
		return url;
	}

	public boolean isLogoutLinkExist() {
		return eleUtil.waitForElementVisible(logoutLink, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}

	public List<String> getAccountsPageHeaderList() {
		List<WebElement> headers = eleUtil.waitForElementsVisible(accHeaders, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		List<String> accHeadersList = new ArrayList<String>();

		for (WebElement e : headers) {
			String accHeaders = e.getText();
			accHeadersList.add(accHeaders);
		}
		return accHeadersList;
	}

}
