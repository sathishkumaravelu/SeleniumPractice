/**
 * 
 */
package TNQ;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author sathish
 *
 */
public class ContactUsProject {

	public static void main(String[] args) throws InterruptedException, IOException {

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
		driver.get("http://automationpractice.com/index.php");

		WebElement pageLogoElement = driver.findElementById("header_logo");
		if (ExpectedConditions.visibilityOf(pageLogoElement) != null) {
			System.out.println("page successfully launched");
		} else {
			System.out.println("page not loaded");
		}

		WebElement contactUsElement = driver.findElementByXPath("//a[text()='Contact us']");
		String contactUsText = contactUsElement.getText();
		if (contactUsText.contains("Contact")) {
			contactUsElement.click();
			System.out.println("the menu " + contactUsText + "clicked successfully");
			System.out.println("we are landed in " + driver.getTitle());
		} else {
			System.out.println("button not available");
		}

		WebElement subjectHeaderElement = driver.findElementById("id_contact");

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(subjectHeaderElement));
		if (subjectHeaderElement.isDisplayed()) {
			Select subjectHeaderDropDown = new Select(subjectHeaderElement);
			subjectHeaderDropDown.selectByValue("1");
			System.out.println("webmaster values selected successfully");
		} else {
			System.out.println("value not clicked");
		}

		WebElement emailElement = driver.findElementById("email");
		if (emailElement.isEnabled()) {
			emailElement.sendKeys("sathishsady@gmail.com");
			System.out.println("email id is entered successfully");
		}

		WebElement fileUpload = driver.findElementById("fileUpload");
		fileUpload.sendKeys("C:\\Users\\sathish\\Desktop\\Image.png");

		driver.findElement(By.id("message")).sendKeys("test");
		driver.findElementByXPath("//button[@id='submitMessage']/span").click();
		// time to upload the file
		Thread.sleep(500);
		System.out.println("we are landed in " + driver.getTitle());

		// snap shot
		File source = driver.getScreenshotAs(OutputType.FILE);
		File desc = new File("./Snaps/IMG1.png");
		FileUtils.copyFile(source, desc);
	}

}
