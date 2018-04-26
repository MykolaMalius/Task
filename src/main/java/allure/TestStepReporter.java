package allure;

import org.testng.Reporter;

/**
 * Test Step Reporter for reporting test steps based on Reporter.log of Test Ng.
 */
public class TestStepReporter {

	/**
	 * Reports the detailed test stepsproperties with stepsproperties summary and details
	 *
	 * @param stepText the test stepsproperties summary text
	 * @param stepOutput the stepsproperties output text
	 */
	public static void report(String stepText, String stepDetails) {
		Reporter.log("<b>" + stepText + "</b>" + "&nbsp" + stepDetails + "<p>", true);
	}

	/**
	 * Reports the detailed test stepsproperties with stepsproperties summary and details, - separates the output with new lines
	 *
	 * @param stepText the test stepsproperties summary text
	 * @param stepOutput the stepsproperties output text
	 */
	public static void reportln(String stepText, String stepDetails) {
		Reporter.log("<p>" + "<b>" + stepText + "</b>" + "&nbsp" + stepDetails + "<p>", true);
	}
	
	/**
	 * Reports the general test stepsproperties with stepsproperties summary
	 *
	 * @param stepText the test stepsproperties text
	 */
	public static void report(String stepText) {
		Reporter.log("<b>" + stepText + "</b>" + "<p>", true);
	}
	
	/**
	 * Reports the general test stepsproperties with stepsproperties summary - separates the output with new lines
	 *
	 * @param stepText the test stepsproperties text
	 */
	public static void reportln(String stepText) {
		Reporter.log("<p>" + "<b>" + stepText + "</b>" + "&nbsp" + "<p>", true);
	}

}
