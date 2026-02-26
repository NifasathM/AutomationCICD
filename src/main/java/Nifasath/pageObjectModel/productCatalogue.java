package Nifasath.pageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import Nifasath.AbstractComponents.AbstractComponent;

public class productCatalogue extends AbstractComponent {
	
	WebDriver driver;

	public productCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//List<WebElement> products = driver.findElements (By.cssSelector (".mb-3")) ;
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	/*@FindBy(css=".ng-animating")
	WebElement spinner;*/
	
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	//By invisibleToastMessage = By.cssSelector(".ng-animating");
	By spinner = By.cssSelector(".ng-animating");
	// class ng-tns-c4-2 toast-message ng-star-inserted
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) 
	{
		
		 waitForElementToAppear(productsBy); // REQUIRED
		 
		    return products.stream()
		        .filter(product -> product
		            .findElement(By.cssSelector("b"))
		            .getText()
		            .trim()
		            .equalsIgnoreCase(productName.trim())) //
		        .findFirst()
		        .orElse(null);
		}

		
		/* WebElement prod =products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals (productName)).findFirst().orElse(null);
		
		return prod;*/
		
		/*WebElement prod = products.stream()
		        .filter(product -> product
		            .findElement(By.cssSelector("b"))
		            .getText()
		            .trim()
		            .equalsIgnoreCase(productName.trim()))
		        .findFirst()
		        .orElse(null);

		    Assert.assertNotNull(prod, "Product not found: " + productName);
		    return prod;*/
	
	public void addProductToCart(String productName)
	{
		/*WebElement prod = getProductByName(productName);
		waitForElementToDisappear(spinner);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(toastMessage);
		//waitForElementToDisappear(spinner);*/
		
		waitForElementToDisappear(spinner);
		WebElement prod = getProductByName(productName);
		WebElement addBtn=prod.findElement(addToCart);

	    // 🔹 Scroll only if not visible
	     //   scrollIntoElement(addBtn);

	    // 🔹 Wait + Click
	    waitForElementToBeClickable(addBtn);
	    addBtn.click();

	    // 🔹 Wait for animation to finish
	    //waitForElementToDisappear(spinner);
	    waitForElementToAppear(toastMessage);//
	    waitForElementToDisappear(spinner); //
	}

		//waitForElementToBeClickable(addBtn);
		//addBtn.click();
	   // waitForElementToAppear(toastMessage);
	  //  waitForElementToDisappear(toastMessage);
		
		
		
	}
	
	
	
	
/* List<WebElement> products = driver.findElements (By.cssSelector (".mb-3")) ;
		WebElement prod =products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals (productName) ).findFirst().orElse(null);
		prod.findElement (By.cssSelector(".card-body button:last-of-type")).click();
 * 
 */


