package appium.android.knowable.pageobject;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public abstract class BasePage {
	
	AndroidDriver<AndroidElement> androidDriver;
	
	public BasePage() {
		// TODO Auto-generated constructor stub
	}
	
	public BasePage(AndroidDriver<AndroidElement> driver) {
		this.androidDriver = driver;
	}
	
	public abstract boolean isOnTheRightPage();
	
}
