package TestBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class shoppingAutomationTestBase  extends CommonConstants{
	
	/**
	 * Map to hold the Customer login credentials along with the Market place information
	 */
	protected final HashMap<String, Set<String>> customerInfo = new HashMap<String, Set<String>>() {{ 
		put("testautomation.kyt@gmail.com;Amazon@123", new HashSet<>(Arrays.asList("IN")));
	}};

	/**
	 * getHardCodedCustomer Method - Parses the customer credentials depending on the Market place/ Specified locale.
	 * Returns - Array consisting of Customer Mail Id, Customer Password
	 */
	public Object[][] getHardCodedCustomer(Map<String, Set<String>> hardCodedCustomer, 
			String targetLocale) throws Exception
	{
		ArrayList<Object[]> customers = new ArrayList<>();
		
		for(Map.Entry<String, Set<String>> entry:hardCodedCustomer.entrySet())
		{
			if(entry.getValue().contains(targetLocale)) {
				customers.add(new Object[]{
					entry.getKey().split(";")[0],
					entry.getKey().split(";")[1]
				});
			}
		}
		return customers.toArray(new Object[0][0]);
	}
	
	/**
	 * driverWaitEvent Method - Wait till the element is visible to proceed automating the workflow
	 * Parameters- Locator of the element to be visible
	 * Returns - None
	 * Exceptions - Element Not Found - If the specified element is not visible within the Explicit Wait Time window = 3ms.
	 */
	public void driverWaitEvent(String locator)
	{
		WebDriverWait waitEvent = new WebDriverWait(driver,3);
		
		try {
			waitEvent.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
		} catch(Exception e)
		{
			System.out.println("Element not found");
		}
	}
	
	/**
	 * searchProduct Method - Searching for the required Product and choosing a specific brand
	 * Returns - void
	 */
	public void searchProduct(String choiceOfProduct) throws InterruptedException
	{
		Actions selectAction = new Actions(driver);
		
		//Search for the Product of Choice
		driverWaitEvent("//input[@id='twotabsearchtextbox']");
		selectAction.moveToElement(driver.findElement(By.cssSelector("input#twotabsearchtextbox"))).click().sendKeys(choiceOfProduct).sendKeys(Keys.ENTER).build().perform();
		
		//Confining search to the brand of choice
		driverWaitEvent("//li[@aria-label='Sony']/span/a/div/label/i");
		selectAction.moveToElement(driver.findElement(By.xpath("//li[@aria-label='Sony']/span/a/div/label/i"))).click().build()
				.perform();
	}
	
	/**
	 * selectPriceRange Method - Selection of Product pricing range
	 * Parameters - Lower price limit, Upper price limit
	 * Returns - void
	 */
	public void selectPriceRange(String lowerPriceLimit, String upperPriceLimit)
	{
		//Entering the lower price limit
		driverWaitEvent("//input[@id='low-price']");
		driver.findElement(By.cssSelector("input#low-price")).sendKeys(lowerPriceLimit);
		
		//Entering the higher price limit
		driverWaitEvent("//input[@id='high-price']");
		driver.findElement(By.cssSelector("input#high-price")).sendKeys(upperPriceLimit);
		
		//Applying the price range filter
		driver.findElement(By.cssSelector("input.a-button-input")).click();
	}
	
	/**
	 * featureSelect Method - Selection of Product key features
	 * Parameters - Feature of choice
	 * Returns - void
	 */
	public void featureSelect(String chosenFeature)
	{
		//Constructing the locator based on the choice of feature - E.g. Noise Cancelling
		String featureChoice = "//li[@aria-label='"+chosenFeature+"']/span/a/div/label/i";
				
		//Selecting the required feature 
		driverWaitEvent(featureChoice);
		driver.findElement(By.xpath(featureChoice)).click();
	}
	
	/**
	 * productSort Method - Sorting of Product based on discount and feature-sort
	 * Parameters - Discount range to be selected, Selection of search result sorting
	 * Returns - void
	 */
	public void productSort(String discountSelected, String Sorting)
	{
		String discountLocator = "//span[text()='" + discountSelected + "']";
		String sortLocator = "//a[@id='"+Sorting+"']";
		
		//Clicking on the discount range
		driverWaitEvent("//ul[@aria-labelledby='p_n_pct-off-with-tax-title']/li[1]/span/a");
		driver.findElement(By.xpath(discountLocator)).click();
		
		//Clicking on the drop-down menu for sorting the product
		driverWaitEvent("//span[@class='a-button a-button-dropdown a-button-small']");
		driver.findElement(By.xpath("//span[@class='a-button a-button-dropdown a-button-small']")).click();
		
		//Clicking on the sort selection in drop-down menu
		driverWaitEvent(sortLocator);
		driver.findElement(By.xpath(sortLocator)).click();
	}
	
	/**
	 * clickProduct Method - Selecting the Second Product from the search list, switching to the Product detail page
	 * Parameters - None
	 * Returns - void
	 */
	public void clickProduct() throws InterruptedException
	{
		//Clicking on the desired product
		driverWaitEvent("//*[@id='search']/div[1]/div[2]/div/span[3]/div[2]/div[3]/div/span/div/div/div[2]/div[2]/div/div[1]/div/div/div[1]/h2");
		driver.findElement(By.xpath("//*[@id='search']/div[1]/div[2]/div/span[3]/div[2]/div[3]/div/span/div/div/div[2]/div[2]/div/div[1]/div/div/div[1]/h2/a")).click();
		
		//Switching to the Product detail page
		Set<String> windowHandler = driver.getWindowHandles();
		Iterator<String> windowIterator =  windowHandler.iterator();
		windowIterator.next();
		driver.switchTo().window(windowIterator.next());
	}
	
	/**
	 * proceedToCheckout Method - In the Detail Page - Adding the selected item to Cart and Proceeding to buy
	 * Parameters - None
	 * Returns - void
	 */
	public void proceedToCheckout()
	{
		//Clicking on "Add to Cart" Button
		driverWaitEvent("//span[@id='hlb-ptc-btn']");
		driver.findElement(By.cssSelector("input#add-to-cart-button")).click();
		
		//Clicking on "Proceed to Buy(1 item)" button
		driverWaitEvent("//span[@id='hlb-ptc-btn']");
		driver.findElement(By.xpath("//span[@id='hlb-ptc-btn']")).click();

	}
	
	/**
	 * signIn Method - Signing-in to complete the checkout flow
	 * Parameters - Customer account mail Id, Customer account Password
	 * Returns - void
	 */
	public void signIn(String mailID, String password) throws InterruptedException
	{
		//Entering the e-mail ID
		driverWaitEvent("//input[@type='email']");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(mailID);
		driver.findElement(By.cssSelector("span#continue")).click();
		
		//Entering the password
		driverWaitEvent("//input[@type='password']");
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(password);
		driver.findElement(By.cssSelector("input[id='signInSubmit']")).click();
	}
	
	/**
	 * selectAddressAndPaymentMethod Method - In the ShipOptionSelectionPage, selection of the Shipping address and continuing to Checkout Page
	 * Parameters - void
	 * Returns - void
	 */
	public void selectAddressAndPaymentMethod()
	{
		//Clicking on "Deliver to this address" Button
		driverWaitEvent("//div[@class='a-spacing-base address-book']/div/div[2]/span");
		driver.findElement(By.xpath("//div[@class='a-spacing-base address-book']/div/div[2]/span")).click();
		
		//Clicking on "Continue" in ShipOptionSelectionPage - Choose your delivery options
		driverWaitEvent("//form[@id='shippingOptionFormId']/div[1]/div[2]/div/span[1]/span");
		driver.findElement(By.xpath("//form[@id='shippingOptionFormId']/div[1]/div[2]/div/span[1]/span")).click();
		
		/* Selection of the payment method for successful checkout process
		 * The automation stops here - 
		 *  1. Usage of the sample(invalid) payment card.
		 *  2. Hence, option to enter CVV to have successful transaction is disabled.
		 */ 
		driverWaitEvent("//span[@class='pmts-instrument-selector']/div[1]/label/input");
		driver.findElement(By.xpath("//span[@class='pmts-instrument-selector']/div[1]/label/input")).click();	
	}
}
