package mavenProject.mavenTest;

import java.sql.Driver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class democlass {
	ChromeDriver driver = null;
	@BeforeMethod
	public void launchBrowser() throws Exception
	{
		System.setProperty("webdriver.chrome.driver","C:\\Eclipse\\web drivers\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
		Thread.sleep(5000);
	}

	@Test
	public void show() {
		System.out.println("Hello Fiaz");
	}

	@AfterMethod
	public void tearDown() throws Exception
	{
		driver.close();
	}



}
