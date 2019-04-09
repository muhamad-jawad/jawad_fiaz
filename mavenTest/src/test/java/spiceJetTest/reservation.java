package spiceJetTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import spiceJetJava.spiceJetJava;

public class reservation {
	spiceJetJava spice = new spiceJetJava();
	WebDriver driver = null;
	String from="";
	String to="";
	String currency="";
	String expectedResult="";
	String actualResult="";
	String Pass="pass";
	String fail="fail";



	@Parameters("mybrowser")
	@BeforeMethod
	public void launchBrowser(String mybrowser) throws Exception
	{
		//running on IE Chrom Browser
		if (mybrowser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver","C:\\Eclipse\\web drivers\\chromedriver\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://www.spicejet.com/");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(5000);
			
		}
		//running on IE FireFox
		else if(mybrowser.equalsIgnoreCase("FireFox"))
		{

			System.setProperty("webdriver.gecko.driver","gecko\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get("https://www.spicejet.com/");
			Thread.sleep(3000);
		}
		//running on IE browser
		else if(mybrowser.equalsIgnoreCase("IExplorer"))
		{

			System.setProperty("webdriver.ie.driver","C:\\Eclipse\\web drivers\\IEDriverServer11.exe"); 
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			driver.get("https://www.spicejet.com/");
			Thread.sleep(3000);
		}

	}

	@Test(priority=1,enabled=true)
	public void happyScenario() throws Exception
	{

		spice.setExcelFileSheet("reservation");
		getExcelData(1);
		sendAllData();
		expectedResult=spice.getCellData(1, 4);
		System.out.println(getCurrentDay());
		actualResult=driver.findElement(By.xpath("//div[ @class='trip-detrails']")).getText();
		System.out.println(actualResult);

		assertEquals(actualResult, expectedResult);
		spice.setCellData(actualResult, 1, 5);
		spice.setCellData(Pass, 1, 6);

	}
	
	@Test(priority=2,enabled=true)
	public void arrivalEmpty() throws Exception
	{

		spice.setExcelFileSheet("reservation");
		getExcelData(2);
		
		sendAllData();
		expectedResult=spice.getCellData(2, 4);
		actualResult=driver.findElement(By.xpath("//div[@id='view-destination-station']")).getText();
		System.out.println(actualResult);

		assertEquals(actualResult, expectedResult);
		spice.setCellData(actualResult, 2, 5);
		spice.setCellData(Pass, 2, 6);

	}
	

	@Test(priority=3,enabled=true)
	public void adult_and_child_flight_seat() throws Exception
	{

		spice.setExcelFileSheet("reservation");
		getExcelData(3);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@id='divpaxinfo']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[@id='hrefIncChd']")).click();
		sendAllData();
		expectedResult=spice.getCellData(3, 4);
		actualResult=driver.findElement(By.xpath("//span[@id='spanpaxinfo']")).getText();
		System.out.println(actualResult);

		assertEquals(actualResult, expectedResult);
		if (actualResult.contentEquals(expectedResult)) {
			spice.setCellData(actualResult, 3, 5);
			spice.setCellData(Pass, 3, 6);
		}
		else
		{
			spice.setCellData(actualResult, 3, 5);
			spice.setCellData(fail, 3, 6);
		}
		
		
	}
	@Test(priority=4,enabled=true)
	public void adult_child_infant_flight_seat() throws Exception
	{

		spice.setExcelFileSheet("reservation");
		getExcelData(4);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@id='divpaxinfo']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[@id='hrefIncInf']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[@id='hrefIncChd']")).click();
		Thread.sleep(5000);
		sendAllData();
		expectedResult=spice.getCellData(4, 4);
		actualResult=driver.findElement(By.xpath("//span[@id='spanpaxinfo']")).getText();
		System.out.println(actualResult);

		assertEquals(actualResult, expectedResult);
		if (actualResult.contentEquals(expectedResult)) {
			spice.setCellData(actualResult, 4, 5);
			spice.setCellData(Pass, 4, 6);
		}
		else
		{
			spice.setCellData(actualResult, 4, 5);
			spice.setCellData(fail, 4, 6);
		}

	}
	
	@Test(priority=5,enabled=true)
	public void currency_INR() throws Exception
	{

		spice.setExcelFileSheet("reservation");
		getExcelData(5);
		sendAllData();
		expectedResult=spice.getCellData(5,4);
		actualResult=driver.findElement(By.xpath("//div[@class='trip-currency']")).getText();
		System.out.println(actualResult);

		assertEquals(actualResult, expectedResult);
		if (actualResult.contentEquals(expectedResult)) {
			spice.setCellData(actualResult, 5, 5);
			spice.setCellData(Pass, 5, 6);
		}
		else
		{
			spice.setCellData(actualResult, 5, 5);
			spice.setCellData(fail, 5, 6);
		}

	}
	
	@Test(priority=6,enabled=true)
	public void currency_USD() throws Exception
	{

		spice.setExcelFileSheet("reservation");
		getExcelData(6);
		sendAllData();
		expectedResult=spice.getCellData(6,4);
		actualResult=driver.findElement(By.xpath("//div[@class='trip-currency']")).getText();
		System.out.println(actualResult);

		assertEquals(actualResult, expectedResult);
		if (actualResult.contentEquals(expectedResult)) {
			spice.setCellData(actualResult, 6, 5);
			spice.setCellData(Pass, 6, 6);
		}
		else
		{
			spice.setCellData(actualResult, 6, 5);
			spice.setCellData(fail, 6, 6);
		}

	}
	
	
	@Test(priority=7,enabled=true)
	public void currency_AED() throws Exception
	{

		spice.setExcelFileSheet("reservation");
		getExcelData(7);
		sendAllData();
		expectedResult=spice.getCellData(7,4);
		actualResult=driver.findElement(By.xpath("//div[@class='trip-currency']")).getText();
		System.out.println(actualResult);

		assertEquals(actualResult, expectedResult);
		if (actualResult.contentEquals(expectedResult)) {
			spice.setCellData(actualResult, 7, 5);
			spice.setCellData(Pass, 7, 6);
		}
		else
		{
			spice.setCellData(actualResult, 7, 5);
			spice.setCellData(fail, 7, 6);
		}

	}
	
	
	
	public void getExcelData(int i) throws Exception
	{ 
		from=spice.getCellData(i, 0);
		to=spice.getCellData(i, 1);
		currency=spice.getCellData(i, 2);

	}

	public void sendAllData() throws Exception
	{  
		String today=getCurrentDay();
		WebElement dropDown =driver.findElement(By.xpath("//div[@id='marketCityPair_1']//div/input[@value='Departure City']"));
		dropDown.click();         
		Thread.sleep(5000);

		WebElement mylist=driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_originStation1_CTNR']/table[@id='citydropdown']/tbody/tr[2]/td[@class='mapbg']"));	
		List<WebElement> departOptions = mylist.findElements(By.tagName("li"));
		Thread.sleep(5000);

		selectCity(departOptions, from);

		WebElement dropDown1= driver.findElement(By.xpath("//input[@id='ctl00_mainContent_ddl_destinationStation1_CTXT']"));		
		dropDown1.click();
		Thread.sleep(5000);
		
		WebElement mylist1=driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR']/table[@id='citydropdown']/tbody/tr[2]/td[@class='mapbg']"));
		List<WebElement> arrivalOptions = mylist1.findElements(By.tagName("li"));
		Thread.sleep(5000);
		selectCity(arrivalOptions, to);
		
		driver.findElement(By.xpath("//input[@id='ctl00_mainContent_view_date1']")).click();
		
		WebElement SelectDate= driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div[1]/table/tbody"));
		Thread.sleep(5000);
		
		List<WebElement> columns = SelectDate.findElements(By.tagName("td"));
		selectDate(columns, today);

		WebElement currencyInput=driver.findElement(By.xpath("//div[@class='currency-dropdown']/select"));
		currencyInput.click();
		currencyInput.sendKeys(currency);
		Thread.sleep(3000);

		driver.findElement(By.xpath("//div[@class='padding_none row1 text-align-center search-button']/span/input[1]")).click();
		Thread.sleep(5000);
	}

	
	@AfterMethod
	public void teardown() {
		driver.close();
	}

	private String getCurrentDay (){
		//Create a Calendar Object
		//Create a Calendar Object
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

		//Get Current Day as a number
		int todayInt = calendar.get(Calendar.DAY_OF_MONTH + 1);
		System.out.println("Today Int: " + todayInt +"\n");

		//Integer to String Conversion
		String todayStr = Integer.toString(todayInt);
		System.out.println("Today Str: " + todayStr + "\n");

		return todayStr;
	}

	public void selectCity(List<WebElement> list, String value) 
	{
		for (WebElement option : list)
		{
			if (option.getText().equals(value) )
			{

				option.click(); 
				System.out.println(value);
				break;
			}
		}

	}

	public void selectDate(List<WebElement> list, String value)
	{
		for (WebElement cell: list) {

			//Select Today's Date
			if (cell.getText().equals(value)) {
				cell.click();
				break;
			}
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}



}