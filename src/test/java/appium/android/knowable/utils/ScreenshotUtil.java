package appium.android.knowable.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;

public class ScreenshotUtil {
	public static String getCurrentDateTime() {
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy:HH.mm.ss");
		return formatter.format(currentDate.getTime());
	}

	public static void takeScreenshot(WebDriver driver, String testName) throws Exception {

		/*
		Dimension d = driver.manage().window().getSize();

		driver.manage().window().setSize(new Dimension(d.getWidth() / 2, d.getHeight() / 2));
		*/

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + testName + ".jpg";
				
		FileUtils.copyFile(screenshot, new File(screenshotPath));
		
		StringBuilder builder = new StringBuilder("<img src=file://");
		builder.append(screenshotPath);
		builder.append(" width='400' heigh='400' alt='Screenshot' />");
		
		//Reporter.log(builder.toString(), true);
	}
	
	public static void scrollAndTakeScreenshot(WebDriver driver, String testName) throws Exception {
		Screenshot fullScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(500)).takeScreenshot(driver);
		
		String projectDir = System.getProperty("user.dir");
		
		String filePath = projectDir + "/screenshots/" + testName + ".png";
		
		ImageIO.write(fullScreenshot.getImage(), "png", new File(filePath));
	}
	
	public static void takeScreenshotOfElement(WebDriver driver, WebElement element) throws IOException {
	   Screenshot fullScreenshot = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, element);
		
		String projectDir = System.getProperty("user.dir");
		
		String filePath = projectDir + "/screenshots/element" + System.currentTimeMillis() + ".png";
		
		ImageIO.write(fullScreenshot.getImage(), "png", new File(filePath));
	}
	
	
	// Take screen shot of list elements then merge them into a single image (handled by AShot)
	public static void takeScreenshotOfElements(WebDriver driver, ArrayList<WebElement> elements) throws IOException {
		   Screenshot fullScreenshot = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, elements);
			
			String projectDir = System.getProperty("user.dir");
			
			String filePath = projectDir + "/screenshots/element" + System.currentTimeMillis() + ".png";
			
			ImageIO.write(fullScreenshot.getImage(), "png", new File(filePath));
		}
}
