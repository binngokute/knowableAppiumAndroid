package appium.android.knowable.utils;

import java.time.Duration;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class CustomActions {

	public static void pushToBackgroundThenResume(AndroidDriver<AndroidElement> driver, String packageName, String activityName) {
		/*
		 // after runAppInBackground, app will be relaunch (re-open MainActivity) NOT resume previous Activity, therefore we 
		 // have to use startActivity
		driver.runAppInBackground(Duration.ofSeconds(3));
		driver.findElement(By.id("course_list_info_menu_button")).click();
		*/
		
		driver.pressKey(new KeyEvent(AndroidKey.HOME));
		driver.startActivity(new Activity(packageName, Constains.settings_activity));
	}
	
	public static void scrollDown(AndroidDriver<AndroidElement> driver, PointOption startPoint, PointOption endPoint) {
		
		TouchAction touch = new TouchAction(driver);
		
		touch.press(startPoint).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(endPoint).release().perform();
	}
}
