package Nifasath.pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Nifasath.AbstractComponents.AbstractComponent;

public class landingPage extends AbstractComponent {
	
	WebDriver driver;

	public landingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement Password;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement ErrorText;
	//we add this becoz in headless mode some spinner is hiding the click n causes issue 
	By spinner = By.cssSelector(".ng-animating");


	
	public String getErrorMessage() {
		waitForWebElementToAppear(ErrorText);
		return ErrorText.getText();
	}
	
	//url
	public void goTo()
	{
		driver.get ("https://rahulshettyacademy.com/client");
	}
	
	//here the sendkeys, click and all the actions were done using action class 
	public productCatalogue loginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		Password.sendKeys(password);
		waitForElementToDisappear(spinner); //we add this for running in headless mode too
		 // Wait until button is clickable
	    waitForElementToBeClickable(submit); //this will wait till the spinner disappears 
	    //HEADLESS FIX — scroll button into view
	    ((JavascriptExecutor) driver)
	            .executeScript("arguments[0].scrollIntoView({block:'center'});", submit);
	    //Headless fix submit.click();instead we use 
	    jsClick(submit);
		productCatalogue pc = new productCatalogue(driver);
		return pc;
	}
	
	

}
