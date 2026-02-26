package Nifasath.pageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Nifasath.AbstractComponents.AbstractComponent;


public class cartPage extends AbstractComponent {
	
	WebDriver driver;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement button;

	public cartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	public Boolean cartProductList(String productName)
	{
		
		Boolean match = cartProducts.stream().anyMatch(cartProduct-> 
		cartProduct.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	
	public checkoutPage Checkout()
	{
		button.click();
		checkoutPage cop = new checkoutPage(driver);
		return cop;
	}



	



	
	

}

