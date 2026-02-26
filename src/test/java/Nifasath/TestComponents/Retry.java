package Nifasath.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


public class Retry implements IRetryAnalyzer{
	
	int count=0;
	int maxTry=1;// give how many times i have to run the flaky test
	@Override
	public boolean retry(ITestResult result) {

		if(count<maxTry) {
			count++;
			return true;//it returns true so the test will re-run 
		}
		return false;//it returns false so the test will stop the re-run 
	}

}
