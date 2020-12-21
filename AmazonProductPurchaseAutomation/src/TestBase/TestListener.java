package TestBase;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * onFinish Method - Method executed after the finish of each test method.
	 * Parameters - Test context
	 * Returns - void
	 */
	@Override
	public void onFinish(ITestContext context) {
		// On finish of the test
		System.out.println("The result of the test is written to index.html file");
		System.out.print("To view the result, open the report file in web browser");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
}
