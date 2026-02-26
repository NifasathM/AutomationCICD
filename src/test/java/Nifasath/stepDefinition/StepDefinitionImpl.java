package Nifasath.stepDefinition;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Nifasath.TestComponents.BaseTests;
import Nifasath.pageObjectModel.cartPage;
import Nifasath.pageObjectModel.checkoutPage;
import Nifasath.pageObjectModel.landingPage;
import Nifasath.pageObjectModel.orderConfirmation;
import Nifasath.pageObjectModel.productCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTests {
	public landingPage lp;
	public productCatalogue pc;
	public orderConfirmation oc;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws Exception
	{
		//code 
		lp= launchApplication();
		driver.manage().window().maximize();
	}
	@Given("^Logged with username (.+) and (.+)$")
	public void logged_in_username_and_password(String Username, String Password) {
		pc=lp.loginApplication(Username,Password);

	}
	//When  I add product <productName> to Cart
	
	@When("^I add product (.+) to Cart$")
	public void add_product_to_cart(String productName) {
		List<WebElement> products = pc.getProductList();
		pc.addProductToCart(productName);
	}
	// And Checkout product <productName> and submit the order
	@And("^Checkout product (.+) and submit the order$")
	public void checkProduct_and_submitOrder(String productName) {
		cartPage cp = pc.goToCart();
		boolean match = cp.cartProductList(productName);
		Assert.assertTrue (match);
		checkoutPage cop =cp.Checkout();
		cop.SelectCountry("india");
		oc =cop.submitOrder();
	}
	// Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage
	@Then("{string} message is displayed on ConfirmationPage")
	public void Confirmation_Page(String string) {
		String confirmMessage=oc.confirmationText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
		}
	@Then("{string} message is Displayed")
	public void some_message_is_displayed(String string) {
		Assert.assertEquals(string, lp.getErrorMessage());
		driver.close();
	}
	

}
// or use tidy gerkin if we can use this chrome pluggin but good practice is to write ourself 

/* checkoutPage cop =cp.Checkout();
cop.SelectCountry("india");
orderConfirmation oc =cop.submitOrder();
String confirmMessage=oc.confirmationText();
Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));*/