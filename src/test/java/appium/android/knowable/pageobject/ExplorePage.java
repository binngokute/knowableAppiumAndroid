package appium.android.knowable.pageobject;

import org.openqa.selenium.NoSuchElementException ;

import org.openqa.selenium.By;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ExplorePage extends BasePage{

	AndroidElement exploreBtn;

	public ExplorePage(AndroidDriver<AndroidElement> driver) {
		super(driver);
	}

	@Override
	public boolean isOnTheRightPage() {
		boolean isExploreButtonPresence = true;
		try {
		 exploreBtn = androidDriver.findElement(By.id("start_exploring_button"));
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			isExploreButtonPresence = false;
		}
		return isExploreButtonPresence;
	}
	
	public void clickExplore() {
		exploreBtn.click();
	}
}
