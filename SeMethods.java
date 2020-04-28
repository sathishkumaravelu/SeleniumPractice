package wdMethods;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.Select;


public class SeMethods implements WdMethods {
	public static RemoteWebDriver driver;
	int i = 1;
	Select DropDown;

	public void startApp(String browser, String url) {
		try {
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", "./drivers/geckoriver.exe");
				driver = new FirefoxDriver();
			}
			driver.manage().window().maximize();
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			System.out.println("The Browser " + browser + " is Launched Successfully");
			//reportStep("The Browser " + browser + " is Launched Successfully", "Pass");
		}catch (TimeoutException e) {

		}catch (UnreachableBrowserException e) {
			System.err.println("UnReachable Expection Occurred");
		}catch (WebDriverException e) {
			System.err.println("WebDriver Expection is Occurred");
			//throw new RuntimeException();

		} catch (Exception e) {
			//reportStep("The Browser " + browser + " is Not Launched Successfully", "Fail");
			System.err.println("Expection is Occurred");


		} finally {
			//takeSnap();
			System.out.println("launched");
		}
	}

	public WebElement locateElement(String locator, String locValue) {
		try {
			switch (locator) {
			case "id":
				return driver.findElementById(locValue);
			case "class":
				return driver.findElementByClassName(locValue);
			case "xpath":
				return driver.findElementByXPath(locValue);
			case "css":
				return driver.findElementByCssSelector(locValue);
			case "linktext":
				return driver.findElementByLinkText(locValue);
			case "Tagname":
				return driver.findElementByTagName(locValue);
			case "name":
				return driver.findElementByName(locValue);
			}
		} catch (NoSuchElementException e) {
			System.err.println("No Such WebElement Error Occured");
			//reportStep("No Such WebElement Error Occured", "Fail");
		}catch(StaleElementReferenceException e) {

		}catch (WebDriverException e) {
			System.err.println("No WebElement WebDriver Expection Occurred");
			//reportStep("No Such WebElement Error Occured", "Fail");

		}catch (Exception e) {
			System.err.println("Expection Occurred");
			//reportStep("No WebElement Elements not clickied", "Fail");
			//reportStep("No Such WebElement Error Occured", "Fail");
		}
		return null;

	}

	public WebElement locateElement(String locValue) {
		try {
			driver.findElementById(locValue);
		} catch (NoSuchElementException e) {
			System.err.println("No Such Element present Error Occured");
			//reportStep("No Such WebElement Error Occured", "Fail");
			throw new RuntimeException();
		}catch (WebDriverException e) {
			System.err.println("No Such Element WebDriver Expection Occurred");
			//reportStep("No Such WebElement Error Occured", "Fail");
		}catch (Exception e) {
			System.err.println("Expection Occurred");
			//reportStep("No Elements not clickied", "Fail");
		}
		return null;
	}

	public void type(WebElement ele, String data) {
		try {
			ele.sendKeys(data);
			//reportStep("The Data " + data + " is Entered Successfully", "Pass");
			System.out.println("The Data " + data + " is Entered Successfully");
			takeSnap();
		}catch (NullPointerException e) {
			System.err.println("NullPointer Exception  Occured");
			//reportStep("The Data " + data + " is not Entered Successfully", "Fail");
			throw new RuntimeException();
		}catch (WebDriverException e) {
			System.err.println("No Such String is present WebDriver Expection Occurred");
		}catch (Exception e) {
			System.err.println("No Such String is present WebDriver Expection Occurred");
			//reportStep("The Data " + data + " is not Entered Successfully", "Fail");
		}
	}

	public void click(WebElement ele) {
		try {
			ele.click();
			System.out.println("The Element " + ele + " is clicked Successfully");
			//reportStep("The Element " + ele + " is clicked Successfully", "Pass");
		} catch (NullPointerException e) {
			System.err.println("NullPointer Exception  Occured");
			//reportStep("The Element " + ele + " is Not  clicked Successfully", "Fail");
			throw new RuntimeException();
		}catch (WebDriverException e) {
			System.err.println("No Element is clicked in WebDriver Expection Occurred");
		}catch (Exception e) {
			System.err.println("No Element is clicked in WebDriver Expection Occurred");
		}
	}

	public String getText(WebElement ele) {


		String Text = null;
		try {
			Text = ele.getText();
			System.out.println("The values is" + Text);
			//reportStep("The values is" + Text, "Pass");
		}catch (NullPointerException e) {
			System.err.println("NullPointer Exception  Occured"); 
			throw new RuntimeException();
		}catch (Exception e) {

			//reportStep("The values is" + Text, "Fail");
		}
		return Text;

		// return ele.getText();

	}

	public String getAttribute(WebElement ele,String attribu) {


		String Text = null;
		try {
			Text = ele.getAttribute(attribu);
			System.out.println(Text + " Attribute is present");
			//reportStep(Text + " Attribute is present", "Pass");
		}catch (NullPointerException e) {
			System.err.println("NullPointer Exception  Occured"); 
			throw new RuntimeException();
		}catch (Exception e) {

			//reportStep(Text + " Attribute is Not present", "Fail");		
		}
		return Text;

		// return ele.getText();

	}

	public void selectDropDownUsingText(WebElement ele, String value) {

		try {
			DropDown = new Select(ele);
			DropDown.selectByVisibleText(value);
			System.out.println("The Dropdown " + value + " is clicked Successfully");
		} catch (NullPointerException e) {
			System.err.println("NullPointer Exception  Occured"); 
			throw new RuntimeException();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			takeSnap();
		}
	}

	public void selectDropDownUsingIndex(WebElement ele, int index) {

		try {
			DropDown = new Select(ele);
			DropDown.selectByIndex(index);
			System.out.println("The Dropdown " + index + " is clicked Successfully");
		} catch (NullPointerException e) {
			System.err.println("NullPointer Exception  Occured"); 
			throw new RuntimeException();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			takeSnap();
		}
	}
	public void selectDropDownUsingValue(WebElement ele, String value) {


		try {
			DropDown = new Select(ele);
			DropDown.selectByValue(value);
			System.out.println("The Dropdown " + value + " is clicked Successfully");
		} catch (NoSuchElementException e) {
			System.err.println("No Such Element Expection Occurred");
		}catch (TimeoutException e) {
			System.err.println("Time Expection Occurred");
		}
		finally
		{
			takeSnap();
		}

	}

	public boolean verifyTitle(String expectedTitle) {

		try {
			String CurrentTitle = driver.getTitle();

			if (CurrentTitle.equals(expectedTitle)) {
				System.out.println(CurrentTitle + "Is Matched With" + expectedTitle);
				return true;
			} else {
				System.out.println(CurrentTitle + "Is Not Matched With" + expectedTitle);

			}
		} catch (NoSuchElementException e) {
			System.err.println("No Such Element Error Occured");
			throw new RuntimeException();
		}catch (NullPointerException e) {
			System.err.println("WebDriver Expection Occurred");
		}catch (Exception e) {
			System.err.println("Expection Occurred");
		}
		return false;
	}

	public void verifyExactText(WebElement ele, String expectedText) {

		try {
			String First = ele.getText();

			if (First.equals(expectedText)) {
				System.out.println(First + "Is Matched With" + expectedText);

			} else {
				System.out.println(First + "Is Not Matched With" + expectedText);
			}
		} catch (NullPointerException e) {
			System.err.println("Null Pointer Expection Occurred");
			throw new RuntimeException();
		}catch(Exception e) {

		}
	}

	public void verifyPartialText(WebElement ele, String expectedText) {

		String First = ele.getText();

		if (First.contains(expectedText)) {
			System.out.println(First + " text is present in " +expectedText);
			//reportStep(First + " text is present in " +expectedText, "Pass"); 

		} else {
			System.out.println(First + " text is not present in "+expectedText);
			//reportStep(First + " text is not present in " +expectedText, "Fail"); 
		}
	}

	public void verifyExactAttribute(WebElement ele, String attribute, String value) {


		String exactAttribute = ele.getAttribute(attribute);
		if (exactAttribute.equals(value)) {
			System.out.println(exactAttribute + "Is Equals to" + attribute);
		} else {
			System.out.println(exactAttribute + "Is Not Equals to" + attribute);
		}
	}

	public void verifyPartialAttribute(WebElement ele, String attribute, String value) {

		String exactAttribute = ele.getAttribute(attribute);
		if (exactAttribute.equals(value)) {
			System.out.println(exactAttribute + "Is Equals to" + attribute);
		} else {
			System.out.println(exactAttribute + "Is Not Equals to" + attribute);
		}
	}

	public void verifySelected(WebElement ele) {

		ele.isSelected();

	}

	public void verifyDisplayed(WebElement ele) {

		ele.isDisplayed();
	}

	public void switchToWindow(int index) {

		try {
			Set<String> allWindows = driver.getWindowHandles();
			List<String> listWindows = new ArrayList<String>(allWindows);
			listWindows.addAll(allWindows);
			driver.switchTo().window(listWindows.get(index));
		} catch (NoSuchWindowException e) {
			System.err.println("No Window Present Error Occurrect");
			throw new RuntimeException();

		}catch (WebDriverException e) {
			System.err.println("WebDriver Expection is Occurred");
			throw new RuntimeException();

		}catch (Exception e) {
			System.err.println(" Expection is Occurred");
			throw new RuntimeException();
		}
	}
	public void switchToFrame(WebElement ele) {

		try {
			driver.switchTo().frame(ele);
		} catch (NoSuchFrameException e) {
			System.err.println("No Frame Expection is Occurred");
			throw new RuntimeException();
		} catch (NoSuchElementException e)
		{
			System.err.println("No Element Expection is Occurred");
		}catch (Exception e)
		{
			System.err.println("Expection is Occurred");
		}
	}

	public void acceptAlert() {

		try {
			Alert myAlert = driver.switchTo().alert();
			myAlert.accept();
		} catch (NoAlertPresentException e) {
			System.err.println("No Alert Present");
			throw new RuntimeException();
		}catch (UnhandledAlertException e) {
			System.err.println("UnHandel alert  is Occurred");
		}catch (Exception e)
		{
			System.err.println("Expection is Occurred");
		}
	}

	public void dismissAlert() {

		try {
			Alert myAlert = driver.switchTo().alert();
			myAlert.dismiss();
		} catch (NoAlertPresentException e) {
			System.err.println("No Alert Present");
			throw new RuntimeException();
		}catch (UnhandledAlertException e) {
			System.err.println("UnHandel alert  is Occurred");
		}catch (Exception e)
		{
			System.err.println("Expection is Occurred");
		}
	}

	public String getAlertText() {

		String Alert = null;
		try {

			Alert myAlert = driver.switchTo().alert();
			Alert = myAlert.getText();
		} catch (NoAlertPresentException e) {
			System.err.println("No Alert Present");
			throw new RuntimeException();
		}catch (UnhandledAlertException e) {
			System.err.println("UnHandel alert  is Occurred");
		}catch (Exception e)
		{
			System.err.println("Expection is Occurred");
		}

		return Alert;
	}


	public void takeSnap() {


		/*File src = driver.getScreenshotAs(OutputType.FILE);
		File desc = new File("./snaps/"+System.currentTimeMillis() + ".png");
		FileUtils.copyFile(src, desc);*/


		File src = driver.getScreenshotAs(OutputType.FILE);
		File desc = new File("./snaps/img" + i + ".png");
		try {
			FileUtils.copyFile(src, desc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		i++;
	}

	public void closeBrowser() {

		driver.close();

	}
	
	public void clear(WebElement ele) {
		
		ele.click();
	}

	public void closeAllBrowsers() {

		driver.quit();

	}

	public void Max() {

		driver.manage().window().maximize();

	}
	public void Thread() {

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public void MouseOver(WebElement we){
		Actions builder=new Actions(driver);
		builder.moveToElement(we).perform();
	}

	@Override
	public void DragAndDrop(WebElement we, WebElement we2) {
		// TODO Auto-generated method stub
		Actions builder = new Actions(driver);
		builder.dragAndDrop(we, we2).perform();
	}

	public void Draggable(WebElement we,int x, int y) {
		// TODO Auto-generated method stub
		Actions builder = new Actions(driver);
		builder.dragAndDropBy(we, x, y);
	}

	public void Selectable(WebElement we,WebElement we2) {
		// TODO Auto-generated method stub
		Actions builder = new Actions(driver);
		builder.clickAndHold(we).release(we2).perform();
	}
	public String getFirstSelectedOption(WebElement ele) {
		DropDown = new Select(ele);
		return DropDown.getFirstSelectedOption().getText();
	}
	public void scrolldown() {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,500)", "");
	}

	
}

