package appium.android.knowable.pageobject;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.springframework.aop.ThrowsAdvice;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AuthenticationPage extends BasePage {

	@AndroidFindBy(id = "authenticate_confirm_email_action_button")
	AndroidElement loginButton;
	
	@AndroidFindBy(id = "authenticate_sign_up_email_edit_text")
	AndroidElement emailField;

	@AndroidFindBy(id = "authenticate_password_edit_text")
	AndroidElement passwordField;

	public AuthenticationPage(AndroidDriver<AndroidElement> driver) {
		super(driver);
		
		/*
		if (!isOnTheRightPage())
			throw new IllegalAccessException("Not on the Login Screen");
		*/
		PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
	}

	
	public boolean isOnTheRightPageWithTitle(String pageTitle) {

		try {
			loginButton = androidDriver.findElement(By.id("authenticate_confirm_email_action_button"));
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return false;
		}

		return loginButton.getText().toLowerCase().equals(pageTitle.toLowerCase());
	}


	@Override
	public boolean isOnTheRightPage() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public void doLogin(String email, String password) {
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		
		if (androidDriver.isKeyboardShown())
			androidDriver.hideKeyboard();
		
		loginButton.click();
	}
	

}
