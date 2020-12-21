package TestBase;

import org.openqa.selenium.WebDriver;

public class CommonConstants {
	/**
	 * Holds parameters such as- 
	 * Driver Object declaration
	 * Market Place
	 * Product
	 * Product Feature
	 * Lower Price Range
	 * Upper Price Range
	 * Product Discount selection
	 * Search result Sorting
	 */
	
	protected static WebDriver driver;
 
	protected String marketPlace = "IN";
	protected String product = "Headphones";
	protected String productFeature = "Noise Cancelling";
	protected String lowPrice = "5000";
	protected String highPrice = "20000";
	protected String productDiscount = "10% Off or more";
	protected String sortSelect = "s-result-sort-select_2";

}
