package appium.android.knowable.test;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import appium.android.knowable.pageobject.ExplorePage;
import appium.android.knowable.pageobject.AuthenticationPage;
import appium.android.knowable.pageobject.SettingsPage;
import appium.android.knowable.utils.Constains;
import appium.android.knowable.utils.CustomActions;
import appium.android.knowable.utils.NotOnRightPageException;

public class SettingsPageTest extends BaseTest {

	SettingsPage settings;

	@BeforeSuite
	public void setup() throws MalformedURLException, InterruptedException, NotOnRightPageException {
		
		System.out.println("Called ONCE");
		
		super.setupAppium();

		ExplorePage explorePage = new ExplorePage(driver);

		if (explorePage.isOnTheRightPage())
			explorePage.clickExplore();

		settings = new SettingsPage(driver);
	}
	
	@Test
	public void test_clickLogin() {
		settings.clickMenu("log in");
		
		AuthenticationPage loginPage = new AuthenticationPage(driver);
		
		assertTrue(loginPage.isOnTheRightPageWithTitle("Log In"));
	}
	

	@Test
	public void test_clickSignup() throws InterruptedException {

		settings.clickMenu("create account");
		
		AuthenticationPage signupPage = new AuthenticationPage(driver);
		
		assertTrue(signupPage.isOnTheRightPageWithTitle("Create account"));
	}
	
	@Test
	public void test_clickHelp() {
		
		settings.clickMenu("help");
		
		assertTrue(settings.isWebViewPresence());
	}

	@Test
	public void test_clickFeedback() {
		
		settings.clickMenu("feedback");
		
		assertTrue(settings.isWebViewPresence());
	}
	
	@Test
	public void test_clickApplyToTeach() {
		
		settings.clickMenu("apply");
		
		assertTrue(settings.isWebViewPresence());
	}
	
	@Test
	public void test_clickJoinTeam() {
		
		settings.clickMenu("join our team");
		
		assertTrue(settings.isWebViewPresence());
	}
	
	@Test
	public void test_clickPrivacy() {
		
		settings.clickMenu("privacy");
		
		assertTrue(settings.isWebViewPresence());
	}
	
	@Test
	public void test_clickTerm() {
		
		settings.clickMenu("terms");
		
		assertTrue(settings.isWebViewPresence());
	}
	
	
	@AfterMethod
	public void afterTestCase(ITestResult result) throws Exception{
		super.afterTest(result);
		driver.navigate().back();
		
		// This is to make sure context resumes focus on Setting screen after opening other Activity
		CustomActions.pushToBackgroundThenResume(driver, Constains.package_name, Constains.settings_activity);
	}

	@AfterSuite
	public void closeApp() throws InterruptedException {
		super.closeApp();
	}
}
