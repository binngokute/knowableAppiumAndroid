package appium.android.knowable.pageobject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import appium.android.knowable.utils.NotOnRightPageException;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBySet;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SettingsPage extends BasePage{
	
	private CourseListPage courseListPage;
	
	//@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"fyi.knowable.android:id/info_menu_item_button\")")
	//@AndroidFindBy(id = "info_menu_item_button")
	public List<AndroidElement> menuItems;
	
	public SettingsPage(AndroidDriver<AndroidElement> driver) throws NotOnRightPageException {
		// TODO Auto-generated constructor stub
		super(driver);
		
		courseListPage = new CourseListPage(driver);
		
		courseListPage.clickMenu();
		
		//PageFactory.initElements(new AppiumFieldDecorator(androidDriver), SettingsPage.class); // Not working for list of element
		menuItems = driver.findElements(By.id("info_menu_item_button"));
	}
	
	public void clickMenu(String menuName) {
		for (AndroidElement menu : menuItems) {
			String name = menu.getText().toLowerCase();
			if (name.contains(menuName) || menuName.contains(name)) {
				menu.click();
				break;
			}
		}
	}
	
	public boolean isWebViewPresence() {
		try {
		   androidDriver.findElement(By.className("android.webkit.WebView"));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	@Override
	public boolean isOnTheRightPage() {
		// TODO Auto-generated method stub
		return menuItems.size() == 6;
	}
}