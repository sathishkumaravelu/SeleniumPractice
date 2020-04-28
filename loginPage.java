package cas_STFC_UAT;

import org.openqa.selenium.WebElement;

import wdMethods.SeMethods;

public class loginPage extends SeMethods{
	
	public static void main(String[] args)  {
		
		loginPage obj = new loginPage();
		obj.login();
	}
	
	public void login() {
	startApp("chrome","https://www.google.com/"); 
	WebElement searchElement = locateElement("xpath","//input[@type='text']");
	type(searchElement, "test");
	WebElement searchButtonElement = locateElement("xpath","(//input[@value='Google Search'])[2]");
	click(searchButtonElement);
	
}
}

