package appium.android.knowable.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import appium.android.knowable.utils.NotOnRightPageException;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FullPlayerPage extends BasePage{
	
	@AndroidFindBy(className = "android.webkit.WebView") // chapter_description_web_view
	AndroidElement lessonWebView;
	
	@AndroidFindBy(id = "chapter_playback_player_play_pause_button")
	AndroidElement playBtn;
	
	@AndroidFindBy(id = "chapter_playback_player_seekbar")
	AndroidElement seekbar;
	
	@AndroidFindBy(id = "chapter_playback_player_skip_forward_button")
	AndroidElement skipForward;
	
	@AndroidFindBy(id = "chapter_playback_player_skip_backward_button")
	AndroidElement skipBackward;
	
	@AndroidFindBy(id = "chapter_playback_player_speed_button")
	AndroidElement speed;
	
	@AndroidFindBy(id = "chapter_playback_player_options")
	AndroidElement share;
	
	@AndroidFindBy(id = "chapter_playback_down_arrow_button")
	AndroidElement minimizePlayer;
	
	@AndroidFindBy(id = "chapter_playback_player_background_card_view")
	public AndroidElement playerControl;
	
	@AndroidFindBy(id = "chapter_playback_player_end_time_text_view")
	public AndroidElement endTime;
	
	@AndroidFindBy(id = "chapter_playback_player_start_time_text_view")
	public AndroidElement currentTime;
	
	public FullPlayerPage(AndroidDriver<AndroidElement> driver) throws NotOnRightPageException {
		// TODO Auto-generated constructor stub
		super(driver);
		
		PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
	
		if (!isOnTheRightPage())
			throw new NotOnRightPageException("Not on FullPlayer Page");
		
	} 
	
	/**
	 * Dont know why if using @AndroidFindBy(id = "chapter_description_web_view") it throws NoSuchElementException
	 * Maybe due to some javaScript on the WebView
	 * also use Thread.sleep will also cause IOException
	 */
	@Override
	public boolean isOnTheRightPage() {
		
		//WebDriverWait wait = new WebDriverWait(androidDriver, 10);
		
		//lessonWebView = (AndroidElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.webkit.WebView")));
		
		return lessonWebView.isDisplayed();
	}

	public void clickPlayButton() {
		playBtn.click();
	}
	
	public void clickSkipForward() {
		skipForward.click();
	}

	public void clickSkipBackward() {
		skipBackward.click();
	}
	
	public String getCurrentProgess() {
		return currentTime.getText();
	}
	
	public void minimizePlayer() {
		minimizePlayer.click();
	}
}
