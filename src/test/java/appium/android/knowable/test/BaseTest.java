package appium.android.knowable.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import appium.android.knowable.pageobject.AuthenticationPage;
import appium.android.knowable.pageobject.CourseListPage;
import appium.android.knowable.pageobject.ExplorePage;
import appium.android.knowable.pageobject.SettingsPage;
import appium.android.knowable.utils.Constains;
import appium.android.knowable.utils.NotOnRightPageException;
import appium.android.knowable.utils.ScreenshotUtil;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

/**
 * Unit test for simple App.
 */
public class BaseTest {
	public static URL url;
	public static DesiredCapabilities capabilities;
	public static AndroidDriver<AndroidElement> driver;
	
	
	public void setupAppium() throws MalformedURLException, NotOnRightPageException {

		url = new URL(Constains.appium_server);

		capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Constains.deviceName);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Constains.platform);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, Constains.osVersion);
		
		//capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		
		capabilities.setCapability(MobileCapabilityType.APP, Constains.apk_path);
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, Constains.package_name);
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, Constains.main_activity);
		capabilities.setCapability(MobileCapabilityType.NO_RESET, true);

		driver = new AndroidDriver<AndroidElement>(url, capabilities);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		ExplorePage explorePage = new ExplorePage(driver);
		
		if (explorePage.isOnTheRightPage()) {
			explorePage.clickExplore();
		}

	}

	public void closeApp() throws InterruptedException {
		// driver.removeApp(Constains.package_name);
		driver.closeApp();
	}
	
	public void afterTest(ITestResult result) throws Exception {
		ScreenshotUtil.takeScreenshot(driver, result.getMethod().getMethodName());
	}
	
	public void loginWithUser(String email, String password) throws NotOnRightPageException {
		
		SettingsPage settingsPage = new SettingsPage(driver);
		settingsPage.clickMenu("log in");

		AuthenticationPage authenticationPage = new AuthenticationPage(driver);
		
		authenticationPage.doLogin(email, password);
	}
}