package TNQ;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;

public class AddCart {

	public static void main(String[] args) throws InterruptedException {
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

		try {
			driver.findElementByXPath("//a[contains(text(),'Sign in')]").click();
			System.out.println("we are currently landed in " + driver.getTitle());
		} catch (NoSuchElementException e) {
			System.out.println("No such element found ");
		} catch (WebDriverException e) {
			System.out.println("web driver exception ");
		}

		try {

			WebDriverWait emailwait = new WebDriverWait(driver, 10);
			WebElement emailElement = driver.findElementById("email");
			emailwait.until(ExpectedConditions.visibilityOf(emailElement));
			emailElement.sendKeys("sathishsady@gmail.com");

			WebDriverWait passwait = new WebDriverWait(driver, 10);
			WebElement passwordElement = driver.findElementById("passwd");
			passwait.until(ExpectedConditions.visibilityOf(passwordElement));
			passwordElement.sendKeys("Gunadharan");

			WebDriverWait submitwait = new WebDriverWait(driver, 10);
			WebElement submitElement = driver.findElementById("SubmitLogin");
			submitwait.until(ExpectedConditions.elementToBeClickable(submitElement));
			submitElement.click();

			System.out.println("email id and password entered successfully also submit button clicked");
		} catch (NoSuchElementException e) {
			System.out.println("No such element found ");
		} catch (WebDriverException e) {
			System.out.println("web driver exception ");
		}


		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElementByXPath("//a[text()='Women']")).perform();
		Thread.sleep(300);
		driver.findElementByXPath("(//a[text()='Summer Dresses'])[1]").click();
		driver.findElementByXPath("//*[@class='icon-th-list']").click();
		
		Thread.sleep(300);
		WebElement firstResult = driver.findElementByXPath("(//*[@class='product-name'])[3]");
		firstResult.click();
		
/*		
  		if (firstResult.isDisplayed() == true) {
			firstResult.click();
		}
*/
		Thread.sleep(300);
		driver.findElementByXPath("//i[@class='icon-plus']").click();
		driver.findElementByXPath("//i[@class='icon-plus']").click();

		Thread.sleep(300);
		WebElement sizeElement = driver.findElementById("group_1");
		Select sizedd = new Select(sizeElement);
		sizedd.selectByVisibleText("M");
		
		driver.findElementByXPath("(//a[@class='color_pick'])[1]").click();
		driver.findElementByXPath("//span[text()='Add to cart']").click();
		driver.findElementByXPath("//span[@title='Continue shopping']//i").click();
		driver.findElementByXPath("(//a[@title='Summer Dresses'])[last()]").click();
		driver.findElementByXPath("(//a[@title='Add to cart'])[2]").click();
		driver.findElementByXPath("//span[@title='Continue shopping']//i").click();
		driver.findElementByXPath("(//a[@title='Add to cart'])[3]").click();
		driver.findElementByXPath("//a[@title='Proceed to checkout']/span").click();

		Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save();
		Thread.sleep(300);
		driver.findElementByClassName("logout").click();

	}

}
