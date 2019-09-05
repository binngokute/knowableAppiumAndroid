package appium.android.knowable.pageobject;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MiniPlayerPage extends BasePage {
	
	@AndroidFindBy(id = "home_mini_player_title_text_view")
	AndroidElement lessonTitle;  // id = home_mini_player_title_text_view
	
	@AndroidFindBy(id = "home_mini_player_play_pause_button")
	AndroidElement playbutton;  // id = home_mini_player_play_pause_button
	
	@AndroidFindBy(id = "home_mini_player_skip_backward_button")
	AndroidElement skipBackward; // id = home_mini_player_skip_backward_button
	
	@AndroidFindBy(id = "home_mini_player_layout")
	AndroidElement playerControl; // id = home_mini_player_layout

	public MiniPlayerPage() {
		// TODO Auto-generated constructor stub
	}

	public MiniPlayerPage(AndroidDriver<AndroidElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
	}

	@Override
	public boolean isOnTheRightPage() {
		// TODO Auto-generated method stub
		return playerControl.isDisplayed();
	}

}
