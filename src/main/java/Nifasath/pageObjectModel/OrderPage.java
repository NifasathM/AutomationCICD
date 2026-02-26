package Nifasath.pageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Nifasath.AbstractComponents.AbstractComponent;


public class OrderPage extends AbstractComponent {
	
	WebDriver driver;
	By ordersTableRows = By.cssSelector("tbody tr");
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> ProductNames;
	
	@FindBy(css=".totalRow button")
	WebElement button;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	public Boolean VerifyOrderPageList(String productName)
	{
		waitForElementToAppear(ordersTableRows);

		Boolean match = ProductNames.stream().anyMatch(cartProduct-> 
		cartProduct.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	
	/*public checkoutPage Checkout()
	{
		button.click();
		checkoutPage cop = new checkoutPage(driver);
		return cop;
	}*/



	



	
	

}

