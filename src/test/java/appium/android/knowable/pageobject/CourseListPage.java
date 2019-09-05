package appium.android.knowable.pageobject;

import java.time.Duration;
import java.util.List;

import org.apache.tools.ant.taskdefs.Touch;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import appium.android.knowable.utils.NotOnRightPageException;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class CourseListPage extends BasePage {

	@AndroidFindBy(id = "course_list_info_menu_button")
	AndroidElement hambugerButton;

	// @AndroidFindBy(uiAutomator = "new
	// UiSelector().resourceId(\"fyi.knowable.android:id/title\")")
	@AndroidFindBy(id = "title")
	AndroidElement pageTitle;

	@FindAll(@FindBy(how = How.ID, using = "headline_title"))
	List<AndroidElement> headlines;

	@FindAll(@FindBy(how = How.ID, using = "cardlist_item"))
	List<AndroidElement> course_item;

	TouchAction action;

	public CourseListPage(AndroidDriver<AndroidElement> driver) throws NotOnRightPageException {
		super(driver);

		// This wont throw exception if element not found, until we use element
		PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);

		action = new TouchAction(androidDriver);

		if (!isOnTheRightPage())
			throw new NotOnRightPageException("Not on course list page");
		
	}

	@Override
	public boolean isOnTheRightPage() {
		try {
			if (pageTitle.getText().toLowerCase().equals("courses")) {
				return true;
			} else {
				return false;
			}
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return false;
		}
	}

	public void clickMenu() {
		hambugerButton.click();
	}

	public void scrollDown() {
		if (getNumberOfCourse() == 0)
			throw new IllegalStateException("There is no course in the list");
		int screenWidth = androidDriver.manage().window().getSize().width;
		int screenHeight = androidDriver.manage().window().getSize().height;

		int startX = screenWidth / 2;
		int startY = screenHeight - 100;

		int endY = 200;

		PointOption<?> startPoint = PointOption.point(startX, startY);
		PointOption<?> endPoint = PointOption.point(endY, startX);

		action.press(startPoint).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(endPoint).release()
				.perform();
	}

	public void scrollUp() {
		if (getNumberOfCourse() == 0)
			throw new IllegalStateException("There is no course in the list");

		System.out.println("Scrolling Up");
		int screenWidth = androidDriver.manage().window().getSize().width;
		int screenHeight = androidDriver.manage().window().getSize().height;

		int startX = screenWidth / 2;
		int startY = 500;

		int endY = screenHeight - 100;

		PointOption<?> startPoint = PointOption.point(startX, startY);
		PointOption<?> endPoint = PointOption.point(startX, endY);

		action.press(startPoint).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(endPoint).release()
				.perform();
	}

	public CourseDetailPage openCourseDetails(int index) throws NotOnRightPageException {
		if (getNumberOfCourse() == 0) {
			throw new IllegalStateException("There is no course in the list");
		}

		course_item.get(0).click();

		return new CourseDetailPage(androidDriver);
	}

	/**
	 * If logged in with user who has purchased for any course My course list should
	 * be show on top Course List
	 * 
	 * @return
	 */
	public boolean isMyCoursePresent() {
		try {
			return headlines.get(0).getText().toLowerCase().equals("my courses");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public int getNumberOfCourse() {
		return course_item.size();
	}
}
