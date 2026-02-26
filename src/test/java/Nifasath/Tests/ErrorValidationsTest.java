package Nifasath.Tests;

import org.testng.annotations.Test;



import org.testng.AssertJUnit;
import org.testng.IRetryAnalyzer;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import Nifasath.TestComponents.Retry;

import Nifasath.TestComponents.BaseTests;
import Nifasath.pageObjectModel.cartPage;
import Nifasath.pageObjectModel.checkoutPage;
import Nifasath.pageObjectModel.landingPage;
import Nifasath.pageObjectModel.orderConfirmation;
import Nifasath.pageObjectModel.productCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationsTest extends BaseTests{

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws Exception, InterruptedException
	{
		//String productName = "ADIDAS ORIGINAL";
		//Gave wrong email id and password to fail the test and to catch error 
		productCatalogue pc=lp.loginApplication("nifasath004@gmail.com", "Nifasath@12");
		Assert.assertEquals("Incorrect email or password.", lp.getErrorMessage());
		

	}
    
	@Test 
	public void ProductErrorValidation() throws  Exception, InterruptedException
	{
		String productName = "ADIDAS ORIGINAL";
		productCatalogue pc=lp.loginApplication("nifasath004@gmail.com", "Nifasath@123");
		List<WebElement> products = pc.getProductList();
		pc.addProductToCart(productName);
		cartPage cp = pc.goToCart();
		
		boolean match = cp.cartProductList("ADIDAS ORIGINALL");
		Assert.assertFalse(match);
	}
	
}
