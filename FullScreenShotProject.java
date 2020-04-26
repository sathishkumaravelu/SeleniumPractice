package TNQ;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;

public class FullScreenShotProject {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		// setting system property to insist where my chromdriver exe is available
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		// setting system property to which manages the chrome drive to enable in silent
		// mode
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		// creating options object to access the methods of ChromeOptions class
		ChromeOptions options = new ChromeOptions();
		// to set the chromedriver to disbale the browser notifications and to run in
		// maximized mode
		ChromeOptions browserArgument = options.addArguments("--disable-notificatins", "--start-maximized");
		// create a object 'driver' to the ChromeDriver class
		ChromeDriver driver = new ChromeDriver(browserArgument);
		// implicitly wait -Specifies the amount of time the driver should wait when
		// searching for an element if it isnot immediately present.
		// throws NoSuchElementException
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// to launch the application
		driver.get("http://automationpractice.com/index.php?controller=contact");
		
		Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save();
		Shutterbug.shootPage(driver).save();
		
	}

}
