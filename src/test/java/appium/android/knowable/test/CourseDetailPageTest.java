package appium.android.knowable.test;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.net.MalformedURLException;

import org.openqa.selenium.NoSuchElementException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import appium.android.knowable.pageobject.CourseDetailPage;
import appium.android.knowable.pageobject.CourseListPage;
import appium.android.knowable.pageobject.FullPlayerPage;
import appium.android.knowable.utils.NotOnRightPageException;

public class CourseDetailPageTest extends BaseTest {

	CourseDetailPage courseDetailsPage;

	@BeforeMethod
	public void setUp() throws NotOnRightPageException, MalformedURLException {
		super.setupAppium();

		CourseListPage courseList = new CourseListPage(driver);

		courseDetailsPage = courseList.openCourseDetails(0);
	}

	@Test
	public void test_allTabsIsPrent() { 
		courseDetailsPage.scrollToTabLayout();
		assertEquals(courseDetailsPage.getNumberOfTabs(), 3);
	}

	@Test
	public void test_defaultSelectedTab() {
		courseDetailsPage.scrollToTabLayout();
		assertTrue(
				"Default selected tab should be 'Summary' but was: "
						+ courseDetailsPage.getSelecteTab().getAttribute("contentDescription"),
				courseDetailsPage.isTabSelected(0) == true);
	}

	@Test
	public void test_selectTab() {
		courseDetailsPage.scrollToTabLayout();
		
		courseDetailsPage.selectTab(1);
		assertTrue(courseDetailsPage.isTabSelected(1) == true);

		courseDetailsPage.selectTab(2);
		assertTrue(courseDetailsPage.isTabSelected(2));

		courseDetailsPage.selectTab(0);
		assertTrue(courseDetailsPage.isTabSelected(0));
	}

	@Test
	public void test_swipeToNextTab() {
		courseDetailsPage.scrollToTabLayout();
		
		courseDetailsPage.swipeToNextTab();
		assertEquals(courseDetailsPage.isTabSelected(1), true);

		courseDetailsPage.swipeToNextTab();
		assertEquals(courseDetailsPage.isTabSelected(2), true);
	}

	@Test
	public void test_swipeBack() {
		courseDetailsPage.scrollToTabLayout();
		
		courseDetailsPage.selectTab(2);

		courseDetailsPage.swipeBack();
		assertEquals(courseDetailsPage.isTabSelected(1), true);

		courseDetailsPage.swipeBack();
		assertEquals(courseDetailsPage.isTabSelected(0), true);
	}

	@Test
	public void test_startLessonShouldShowFullPlayer() throws NotOnRightPageException {
		FullPlayerPage player = courseDetailsPage.startLession();
		assertEquals(player.playerControl.isDisplayed(), true);
	}

	@Test(dependsOnMethods = {"test_startLessonShouldShowFullPlayer"})
	public void test_backFromFullPlayerShouldShowMiniPlayer() throws NotOnRightPageException {

		try {
			courseDetailsPage.miniPlayer.isDisplayed();
			fail("Mini player shouldn't be show on Course Details when there is no Lesson played");
		} catch (NoSuchElementException e) {
			// TODO: handle exception
		}

		FullPlayerPage player = courseDetailsPage.startLession();
		player.minimizePlayer();

		assertEquals(courseDetailsPage.miniPlayer.isDisplayed(), true);
	}

	@Test
	public void test_clickOnMiniPlayerResumeFullPlayer() throws NotOnRightPageException {
		FullPlayerPage player = courseDetailsPage.startLession();
		
		player.minimizePlayer();
		
		courseDetailsPage.clickOnMiniPlayer();
		
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {
		super.afterTest(result);
		driver.closeApp();
	}
}
