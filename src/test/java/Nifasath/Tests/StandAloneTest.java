package Nifasath.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import Nifasath.TestComponents.BaseTests;
import Nifasath.pageObjectModel.OrderPage;
import Nifasath.pageObjectModel.cartPage;
import Nifasath.pageObjectModel.checkoutPage;
import Nifasath.pageObjectModel.landingPage;
import Nifasath.pageObjectModel.orderConfirmation;
import Nifasath.pageObjectModel.productCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest extends BaseTests{

	//this is the submitOrder test which we reffered 
	// newwwwwwwww changessssssssssss
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData", groups={"Purchase"})
	public void StandAloneTest(HashMap<String, String> input) throws Exception, InterruptedException
	{
		
		productCatalogue pc=lp.loginApplication(input.get("email"),input.get("Password"));
		List<WebElement> products = pc.getProductList();
		pc.addProductToCart(input.get("productName"));
		cartPage cp = pc.goToCart();
		boolean match = cp.cartProductList(input.get("productName"));
		Assert.assertTrue (match);
		checkoutPage cop =cp.Checkout();
		cop.SelectCountry("india");
		orderConfirmation oc =cop.submitOrder();
		String confirmMessage=oc.confirmationText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		

	}
	
	/*To verify ZARA COAT 3 is displaying in orders page (its connected with the previous one
	 * because once its added then we gonna check if its added and visible on the orders page 
	 * so its interlinked right 
	*/ 
	@Test(dependsOnMethods= {"StandAloneTest"})
	public void OrderHistoryTest() {
		
		//ZARA COAT 3 is present in orderHistory page 
		//we will login then click on orderHistory and then we have to check
		String productName = "ZARA COAT 3";//
		productCatalogue pc=lp.loginApplication("nifasath004@gmail.com", "Nifasath@123");
		OrderPage op= pc.goToOrder();
		Assert.assertTrue(op.VerifyOrderPageList(productName));	
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		
		
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") +"\\src\\test\\java\\Nifasath\\Data\\PurchaseOrder.json");
		return new  Object[][] {{data.get(0)}, {data.get(1)}};
		
		
		
		
		
		
	}
	

}

//https://rahulshettyacademy.com/client/#/auth/login

/*@DataProvider
public Object[][] getData() {
	
	HashMap<String, String> map = new HashMap<String,String>();
	map.put("email", "nifasath004@gmail.com");
	map.put("Password","Nifasath@123");
	map.put("productName", "Automation 8");
	
	//for another set of values 
	HashMap<String, String> map1 = new HashMap<String,String>();
	map1.put("email", "nifariya@gmail.com");
	map1.put("Password","NifaRiya@123");
	map1.put("productName", "ADIDAS ORIGINAL");
	
	return new  Object[][] {{map}, {map1}};
	 * 
	 * 
	 */
	
	
	/*@DataProvider
public Object[][] getData() {
	return new  Object[][]{{"nifasath004@gmail.com","Nifasath@123","iphone 13 pro"}},--try later and scroll down the screen 
	add the item to cart then go up and once the cart header is visible click there 
	*/
