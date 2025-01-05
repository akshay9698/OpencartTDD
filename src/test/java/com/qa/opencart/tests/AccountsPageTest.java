package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accountsPageSetup() {
		accPage = lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void accountsPageTitleTest() {
		String actTitle = accPage.getAccountsPageTitle();
		Assert.assertEquals(actTitle, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
	}

	@Test(priority = 2)
	public void accountsPageUrlTest() {
		String actUrl = accPage.getAccountsPageUrl();
		Assert.assertTrue(actUrl.contains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE));
	}

	@Test(priority = 3)
	public void logoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

	@Test(priority = 4)
	public void accountsPageHeadersCountTest() {
		Assert.assertEquals(accPage.getAccountsPageHeaderList().size(), AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
	}

	@Test(priority = 5)
	public void accountsPageHeaderTest() {
		List<String> actHeaderListValue = accPage.getAccountsPageHeaderList();
		System.out.println("Accounts page headers list: " + actHeaderListValue);
		Assert.assertEquals(actHeaderListValue, AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
	}

}
