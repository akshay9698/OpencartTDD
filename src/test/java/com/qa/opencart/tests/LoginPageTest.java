package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = lp.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE_VALUE);
	}

	@Test(priority = 2)
	public void loginPageUrlTest() {
		String url = lp.getLoginPageUrl();
		Assert.assertTrue(url.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
	}

	@Test(priority = 3)
	public void forgotLinkExistTest() {
		Assert.assertTrue(lp.isForgotPwdLinkExist());
	}

	@Test(priority = 4)
	public void loginTest() {
		lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
}
