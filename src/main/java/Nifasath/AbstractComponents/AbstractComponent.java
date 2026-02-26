package Nifasath.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Nifasath.pageObjectModel.OrderPage;
import Nifasath.pageObjectModel.cartPage;

public class AbstractComponent {
	WebDriver driver;
	WebDriverWait wait;
	WebElement element;
	
	public AbstractComponent(WebDriver driver) {
		
		this.driver = driver;
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
		
		// fix headless header overlap
	    ((JavascriptExecutor) driver).executeScript("document.body.style.zoom='90%'");
		
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartButton;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement OrderHeader;
	
	
	

	public void waitForElementToAppear(By findBy)
	{
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	
	public void waitForWebElementToAppear(WebElement findBy)
	{
		
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	
/*	public void waitForElementToDisappear(WebElement notfindBy)
	{
		wait.until(ExpectedConditions.invisibilityOf(notfindBy));
	}*/
	
	//instead we used
	public void waitForElementToDisappear(By locator)
	{
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	public void waitForElementToBeClickable(WebElement element)
	{
	    wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	// headless 
	public void scrollToElement(WebElement element)
	{
	    ((JavascriptExecutor) driver)
	        .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
	}
	//headless 
	/*public void jsClick(WebElement element)
	{
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", element);
	}*/
	public void jsClick(WebElement element)
	{
	    wait.until(ExpectedConditions.visibilityOf(element));

	    // Scroll slightly above element to avoid sticky header overlap
	    ((JavascriptExecutor) driver).executeScript(
	            "var rect = arguments[0].getBoundingClientRect();" +
	            "window.scrollBy(0, rect.top - 150);", element);

	    wait.until(ExpectedConditions.elementToBeClickable(element));

	    ((JavascriptExecutor) driver)
	            .executeScript("arguments[0].click();", element);
	}
	public void waitForElementsToBeMoreThan(By locator, int count)
	{
	    wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, count));
	}


	
	
	public cartPage goToCart()
	{
		//waitForElementToDisappear(By.cssSelector(".ng-animating"));
		 // remove invisible toast overlay
	    ((JavascriptExecutor) driver)
	        .executeScript("document.getElementById('toast-container')?.remove();");
		
		/*((JavascriptExecutor) driver)
        .executeScript("window.scrollTo(0, 0);"); */
	    
		//waitForElementToBeClickable(cartButton);
		//cartButton.click();
	    jsClick(cartButton);
	 // wait until Angular navigates to cart page
	    wait.until(ExpectedConditions.urlContains("cart"));//--
		waitForElementToAppear(By.cssSelector(".cartSection h3")); 
	    cartPage cp = new cartPage(driver);
		return cp;
	}
	
	public OrderPage goToOrder() {
		
		waitForElementToBeClickable(OrderHeader);
		//OrderHeader.click();
		jsClick(OrderHeader);
		OrderPage op = new OrderPage (driver);
		return op;
	}
	
	public void scrollIntoElement(WebElement element)
	{
	 ((JavascriptExecutor) driver)
     .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
	 
	}
	
	public void scrollToTopForce()
	{
	    ((JavascriptExecutor) driver)
	        .executeScript("document.documentElement.scrollTop = 0;");
	

}
}
