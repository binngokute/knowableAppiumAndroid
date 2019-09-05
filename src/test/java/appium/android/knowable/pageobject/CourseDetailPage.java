package appium.android.knowable.pageobject;

import java.sql.Driver;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import appium.android.knowable.utils.NotOnRightPageException;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class CourseDetailPage extends BasePage {
	
	@AndroidFindBy(id = "course_background_image_view")
	AndroidElement course_bg;
	
	@FindAll(@FindBy(how = How.CLASS_NAME, using = "androidx.appcompat.app.ActionBar$Tab"))
	List<AndroidElement> tabs;
	
	@AndroidFindBy(id = "course_detail_tab_layout")
	//@AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector()).scrollIntoView("
     //       + "new UiSelector().resourceId(\"" + "course_detail_tab_layout" + "\"));")
	AndroidElement tabs_layout;
	
	@AndroidFindBy(id = "course_detail_action_button")
	AndroidElement startLessonBtn;

	FullPlayerPage fullPlayer;
	
	@AndroidFindBy(id = "home_mini_player_layout")
	public AndroidElement miniPlayer;
	
	TouchAction touch;
	
	public CourseDetailPage(AndroidDriver<AndroidElement> driver) throws NotOnRightPageException {
		// TODO Auto-generated constructor stub
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
		
		if (!isOnTheRightPage())
			throw new NotOnRightPageException("Not on course detail page since course background image not showing");
		
		touch = new TouchAction(androidDriver);
	}
	
	public boolean isOnTheRightPage() {
		return course_bg.isDisplayed();
	}
	
	public void scrollToTabLayout() {
		
		String text = "new UiScrollable(new UiSelector().resourceId(\"course_detail_tab_layout\")).scrollForward()";
		
		try {		
		androidDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
                        + "new UiSelector().resourceId(\"" + "course_detail_tab_layout" + "\"));"));
	
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage()); // We just need to be able to scroll to the layout
		}
	}
	
	public int getNumberOfTabs() {
		return tabs.size();
	}
	
	public void selectTab(int tabIndex) {
		tabs.get(tabIndex).click();
	}
	
	public void swipeToNextTab() {
		
		Dimension screenSize = androidDriver.manage().window().getSize();
		
		int width = screenSize.width;
		
		int height = screenSize.height;

		
		PointOption<?> startPoint =  PointOption.point(width - 50, height / 2);
		PointOption<?> endPoint = PointOption.point(10, height / 2);
		
		touch.press(startPoint).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(endPoint).release().perform();
	}
	
	public void swipeBack() {
		
	    Dimension screenSize = androidDriver.manage().window().getSize();
	    
	    int width = screenSize.getWidth();
	    
	    int height = screenSize.getHeight();

		PointOption<?> startPoint =  PointOption.point(10, height / 2);  // don't make it starts from (0, xx) since it won't touch correctly 
		PointOption<?> endPoint = PointOption.point(width - 50, height / 2);
		
		touch.press(startPoint).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3))).moveTo(endPoint).release().perform();
	}
	
	
	public FullPlayerPage startLession() throws NotOnRightPageException {
		startLessonBtn.click();
		System.out.println("Button clicked");
		return new FullPlayerPage(androidDriver);
	}
	
	public void joinWaitList() {
		
	}
	
	public boolean isTabSelected(int tabIndex) {
		return tabs.get(tabIndex).getAttribute("selected").equals("true");
	}
	
	public AndroidElement getSelecteTab() {
		
		for (AndroidElement tab : tabs) {
			if (tab.getAttribute("selected").equals("true")) {
				return tab;
			}
		}
		
		return null;
	}
	
	public FullPlayerPage clickOnMiniPlayer() throws NotOnRightPageException {
		miniPlayer.click();
		return new FullPlayerPage(androidDriver);
	}
}
