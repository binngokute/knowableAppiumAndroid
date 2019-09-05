package appium.android.knowable.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.MalformedURLException;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import appium.android.knowable.pageobject.CourseDetailPage;
import appium.android.knowable.pageobject.CourseListPage;
import appium.android.knowable.utils.Constains;
import appium.android.knowable.utils.NotOnRightPageException;

public class CourseListPageTest extends BaseTest {

	CourseListPage courseListPage;
	
	@BeforeSuite(groups = {"not_logged_in"})
	public void setUp_notLoggedIn(ITestContext context) throws MalformedURLException, NotOnRightPageException {
		
		System.out.println("Running Suite: " + context.getSuite().getName());
		super.setupAppium();
		
		courseListPage = new CourseListPage(driver);
	}

	@BeforeSuite(groups = {"logged_in"})
	public void setUp_loggedIn(ITestContext context) throws MalformedURLException, NotOnRightPageException {

		System.out.println("Running Suite: " + context.getSuite().getName());
		
		super.setupAppium();
		
		super.loginWithUser(Constains.purchased_email, Constains.purchased_password);
	
	    courseListPage = new CourseListPage(driver);
	}
	
	@Test(groups = {"not_logged_in"})
	public void testScrollCourseList() {
		courseListPage.scrollDown();
		courseListPage.scrollUp();
	}
	
	@Test(groups = {"logged_in"})
	public void testHeadLine() {
		courseListPage.scrollUp();
		assertTrue("My Courses not showing on course list OR hidden OR user hasn't purchased any course", courseListPage.isMyCoursePresent());
	}
	
	@Test(groups = {"not_logged_in"})
	public void testOpenCourseDetail() {
		try {
		courseListPage.openCourseDetails(0);
		} catch (NotOnRightPageException e) {
			// TODO: handle exception
			fail(e.getMessage());
		} finally {
			driver.navigate().back();
		}
		
	}
	
	@AfterMethod(groups = {"logged_in", "not_logged_in"})
	public void tearDown(ITestResult result) throws Exception {
		super.afterTest(result);
	}
	
	@AfterSuite(groups = {"logged_in"})
	public void doLogout() {
		System.out.println("Aftter Suite");
		// Logout to make sure it doesn't affect not_logged_in test
		// Cannot call this method after driver is initialled because this will also end current session.
		driver.resetApp();
	}
}
