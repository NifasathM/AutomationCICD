package Nifasath.pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Nifasath.AbstractComponents.AbstractComponent;


public class orderConfirmation extends AbstractComponent {
	WebDriver driver;

	public orderConfirmation(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css=".hero-primary")
	 WebElement CM;
	
	public String confirmationText() {
		
		waitForWebElementToAppear(CM);//
		String confirmMessage=CM.getText();
		return confirmMessage;
	}

}
