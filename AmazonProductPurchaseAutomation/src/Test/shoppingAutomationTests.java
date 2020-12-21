package Test;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestBase.CommonConstants;
import TestBase.shoppingAutomationTestBase;

public class shoppingAutomationTests extends shoppingAutomationTestBase {

	/**
	 * Data Provider - To populate the test method with user credentials for every run
	 * Returns - Customer Account mail ID, Customer Account Password
	 */
	@DataProvider 
	public Object[][] dataProviderAutomation() throws Exception
	{
		//Populating the customer credentials
		return getHardCodedCustomer(customerInfo, marketPlace);
	}
	
	/**
	 * beforeTest Method - To instantiate a Web-driver object and load the web-site in the same. 
	 * Parameters (from the build file) - Browser choice, URL of the Web-site.
	 * Returns - void
	 */
	@Parameters ({"Browser", "URL"})
	@BeforeTest
	public void beforeTest( String browser, String url){
		
		//Choosing the instantiation of driver object based on the choice of browser.
		if(browser.equalsIgnoreCase("Chrome")){
			System.setProperty("webdriver.chrome.driver", "//Users//vrajee//Downloads//chromedriver");
			CommonConstants.driver = new ChromeDriver();
		} else if(browser.equalsIgnoreCase("Firefox")){
			System.setProperty("webdriver.gecko.driver", "//Users//vrajee//Downloads//geckodriver");
			CommonConstants.driver = new FirefoxDriver();
		} else if(browser.equalsIgnoreCase("Safari")){
			System.setProperty("webdriver.safari.driver", "//Users//vrajee//Downloads//safaridriver");
			CommonConstants.driver = new SafariDriver();
		} else if(browser.equalsIgnoreCase("InternetExplorer")){
			System.setProperty("webdriver.edge.driver", "//Users//vrajee//Downloads//edgedriver");
			CommonConstants.driver = new EdgeDriver();
		}
		
		//Maximizing the browser window for better UX
		driver.manage().window().maximize();
		
		//Loading the Web-site
		driver.get(url);
	}
	
	/**
	 * automateShoppingTest Test Method - Testing of end-to-end shopping workflow. 
	 * Parameters (from Data Provider) - Customer account mail Id, Customer account Password 
	 * Returns - void
	 */
	@Test (dataProvider="dataProviderAutomation")
	public void automateShoppingTest(String customerMailId, String customerPassword) throws InterruptedException
	{
		//Searching for the required Product and choosing a specific brand
		searchProduct(product);
		
		//Selection of Product pricing range
		selectPriceRange(lowPrice, highPrice);
		
		//Selection of Product key features
		featureSelect(productFeature);
		
		//Sorting of Product based on discount and feature-sort
		productSort(productDiscount, sortSelect);
		
		//Selecting the Product of choice
		clickProduct();
		
		//In the Detail Page - Adding the selected item to Cart and Proceeding to buy
		proceedToCheckout();
		
		//Signing-in to complete the checkout flow
		signIn(customerMailId,customerPassword);
		
		// ShipOptionSelectionPage - Selection of the Shipping address and continuing to Checkout Page
		selectAddressAndPaymentMethod();
		
	}

	/**
	 * afterTestMethod Method - Ending the Browser session and closing all windows opened. 
	 * Parameters - None
	 * Returns - void
	 */
	@AfterMethod
	public void afterTestMethod(){
		//Closing the browser session after the test execution
		driver.quit();
	}

	
}
