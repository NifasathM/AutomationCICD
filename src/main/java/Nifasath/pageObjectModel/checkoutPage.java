package Nifasath.pageObjectModel;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Nifasath.AbstractComponents.AbstractComponent;

public class checkoutPage extends AbstractComponent{
	WebDriver driver;

	public checkoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(xpath="(//button[contains(@class, 'ta-item')]) [2]")
	WebElement selectCountry;
	
	By results = By.cssSelector(".ta-results");
	
	public void SelectCountry(String countryName)
	{
		// important for headless Angular
	    country.click();        // focus event
	    country.clear();        // reset state
		Actions a = new Actions (driver);
		a.sendKeys(country, countryName). build().perform();
		// wait until suggestions actually load (not just container)
		waitForElementToAppear(results);
		selectCountry.click();
		
	}
	public orderConfirmation submitOrder() {
		submit.click();
		orderConfirmation oc = new orderConfirmation(driver);
		return oc;
	}
	

}
