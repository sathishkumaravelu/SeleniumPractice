package testNGLearning;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LearningDependsOnMethod {

	WebDriver driver;

	@BeforeMethod
	public void launchApplication() {

		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		driver = new ChromeDriver(); // launch chrome
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://leaftaps.com/opentaps/control/main");
	}

	@Test
	public void LoginPage() {

		driver.findElement(By.name("USERNAME")).sendKeys("demosalesmanager");
		driver.findElement(By.name("PASSWORD")).sendKeys("crmsfa");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		//for failure condition
		//driver.findElement(By.xpath("//input[@value='Logi']")).click();

	}

	@Test(dependsOnMethods= "LoginPage")
	public void CRMSFAScreen() {

		String welcomeText = driver.findElement(By.id("form")).getText();
		System.out.println(welcomeText);
	}

	@AfterMethod
	public void logout() {
		//driver.findElement(By.xpath("//form[@id='logout']//input")).click();
		driver.close();
	}

}
