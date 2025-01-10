package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	  private WebDriver driver;
	    private ElementUtil eleUtil;

	    Map<String, String> productInfoMap;

	    private By productHeader = By.tagName("h1");
	    private By productImgs = By.cssSelector("ul.thumbnails img");
	    private By productMetaData = By.xpath("//div[@class='col-sm-4']//ul[1]//li");
	    private By productPriceData = By.xpath("//div[@class='col-sm-4']//ul[2]//li");
	    private By quantity = By.id("input-quantity");
	    private By addToCartBtn = By.id("button-cart");
	    private By cartSuccessMessg = By.cssSelector("div.alert.alert-success");


	    public ProductInfoPage(WebDriver driver) {
	        this.driver = driver;
	        eleUtil = new ElementUtil(driver);

	    }

	    public String getProductHeaderValue() {
	        String productHeaderValue = eleUtil.doElementGetText(productHeader);
	        System.out.println("product header: " + productHeaderValue);
	        return productHeaderValue;
	    }

	    public int getProductImagesCount() {
	        int imagesCount = eleUtil.waitForElementsVisible(productImgs, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
	        System.out.println("product images count is: " + imagesCount);
	        return imagesCount;
	    }

	    public void enterQuantity(int qty) {
	        System.out.println("Product Quantity: " + qty);
	        eleUtil.doSendKeys(quantity, String.valueOf(qty));
	    }

	    public String addProductToCart() {
	        eleUtil.doClick(addToCartBtn);
	        String successMsg = eleUtil.waitForElementVisible(cartSuccessMessg, AppConstants.DEFAULT_MEDIUM_TIME_OUT).getText();
	        StringBuilder sb = new StringBuilder(successMsg);
	        String msg = sb.substring(0, successMsg.length() - 1).replace("\n", "").toString();

	        System.out.println("Cart Success Mesg: " + msg);
	        return msg;
	    }

	    public Map<String, String> getProductInfo() {
	        // productInfoMap = new HashMap<String, String>();
	        //  productInfoMap = new TreeMap<String, String>();
	        productInfoMap = new LinkedHashMap<String, String>();
	        productInfoMap.put("product header", getProductHeaderValue());
	        getProductMetaData();
	        getProductPriceData();
	        System.out.println(productInfoMap);
	        return productInfoMap;
	    }

	    private void getProductMetaData() {
	        List<WebElement> elements = eleUtil.getElements(productMetaData);
	        for (WebElement e : elements) {
	            String meta = e.getText();
	            String[] metaInfo = meta.split(":");
	            String key = metaInfo[0].trim();
	            String value = metaInfo[1].trim();
	            productInfoMap.put(key, value);
	        }
	    }

	    private void getProductPriceData() {
	        List<WebElement> pElements = eleUtil.getElements(productPriceData);
	        String price = pElements.get(0).getText();
	        String exTax = pElements.get(1).getText();
	        String exTaxValue = exTax.split(":")[1].trim();
	        productInfoMap.put("product price", price);
	        productInfoMap.put(exTax, exTaxValue);
	    }

}
