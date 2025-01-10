package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {
	@BeforeClass
	public void productInfoSetup() {
		accPage = lp.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@DataProvider
	public Object[][] getImageData() {
		return new Object[][] { { "macbook", "MacBook Pro", 4 }, { "iMac", "iMac", 3 },
				{ "Apple", "Apple Cinema 30\"", 6 } };
	}

	@Test(dataProvider = "getImageData")
	public void productImageCountTest(String searchKey, String ProductName, int imgCount) {
		searchPage = accPage.performSearch(searchKey);
		productInfoPage = searchPage.selectProduct(ProductName);
		int actImagesCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(actImagesCount, imgCount);
	}

	@DataProvider
	public Object[][] getProductMetaAndPriceData() {
		return new Object[][] { { "macbook", "MacBook Pro", "Apple", "Product 18", "In Stock", "$2,000.00" },
				{ "iMac", "iMac", "Apple", "Product 14", "Out Of Stock", "$122.00" } };
	}

	@Test(dataProvider = "getProductMetaAndPriceData")
	public void productInfoTest(String searchKey, String ProductName, String brand, String productCode,
			String availability, String productPrice) {
		searchPage = accPage.performSearch(searchKey);
		productInfoPage = searchPage.selectProduct(ProductName);
		Map<String, String> actProductInfo = productInfoPage.getProductInfo();
		softAssert.assertEquals(actProductInfo.get("Brand"), brand);
		softAssert.assertEquals(actProductInfo.get("Product Code"), productCode);
		softAssert.assertEquals(actProductInfo.get("Availability"), availability);
		softAssert.assertEquals(actProductInfo.get("product price"), productPrice);
		softAssert.assertAll();
	}
}
