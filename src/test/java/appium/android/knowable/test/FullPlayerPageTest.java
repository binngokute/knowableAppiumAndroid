package appium.android.knowable.test;

import static org.junit.Assert.assertNotEquals;

import java.net.MalformedURLException;
import java.util.Set;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import appium.android.knowable.pageobject.CourseListPage;
import appium.android.knowable.pageobject.FullPlayerPage;
import appium.android.knowable.utils.NotOnRightPageException;

public class FullPlayerPageTest extends BaseTest {
	
	FullPlayerPage fullPlayer;

	public FullPlayerPageTest() {
		// TODO Auto-generated constructor stub
	}
	
	@BeforeMethod
	public void setup() throws MalformedURLException, NotOnRightPageException {
		
		super.setupAppium();
		
		fullPlayer = new CourseListPage(driver).openCourseDetails(0).startLession();
	}
	
	@Test
	public void test_switchContext() {
		Set<String> context = driver.getContextHandles();
		
		for (String string : context) {
			System.out.println(string);
		}
	}
	
	
	@Test
	public void test_skipForwardBackward_audioPaused() {
		
		// Pause player
		fullPlayer.clickPlayButton();
		
		String currentTime = fullPlayer.getCurrentProgess();
		System.out.println("currentTime: " + currentTime);

		fullPlayer.clickSkipForward();
		
		String timeAfterForward = fullPlayer.getCurrentProgess();
		System.out.println("timeAfterForward: " + timeAfterForward);
		
		assertNotEquals(currentTime, timeAfterForward);
				
		fullPlayer.clickSkipBackward();
		
		String timeAfterBackward = fullPlayer.getCurrentProgess();
		System.out.println("timeAfterBackward: " + timeAfterBackward);
		
		assertNotEquals(timeAfterForward, timeAfterBackward);
	
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {
		super.afterTest(result);
		driver.closeApp();
	}
}
