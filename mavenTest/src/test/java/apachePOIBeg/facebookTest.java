package apachePOIBeg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class facebookTest {

WebDriver driver;
	
	@Parameters("mybrowser")
	@BeforeMethod
	public void launchBrowser(String mybrowser) throws Exception
	{
	//running on IE Chrom Browser
		if (mybrowser.equalsIgnoreCase("Chrome")) {
			 System.setProperty("webdriver.chrome.driver","C:\\Eclipse\\web drivers\\chromedriver\\chromedriver.exe");
			 driver = new ChromeDriver();
			 driver.manage().window().maximize();
			 driver.get("https://www.facebook.com/");
			
			 Thread.sleep(5000);
		}
		//running on IE FireFox
		else if(mybrowser.equalsIgnoreCase("FireFox"))
		{
			
			System.setProperty("webdriver.gecko.driver","gecko\\geckodriver.exe");
			 driver = new FirefoxDriver();
			 driver.manage().window().maximize();
			 driver.get("https://www.facebook.com/");
			 Thread.sleep(3000);
		}
		//running on IE browser
		else if(mybrowser.equalsIgnoreCase("IExplorer"))
		{
			
			System.setProperty("webdriver.ie.driver","C:\\Eclipse\\web drivers\\IEDriverServer11.exe"); 
			driver = new InternetExplorerDriver();
			 driver.manage().window().maximize();
			 driver.get("https://www.facebook.com/");
			 Thread.sleep(3000);
		}
		
	}
	
	@Test(priority=1)
	public void happyScenario() throws Exception
	{
		String expectedResult="Invalid username or password";
		String actualResult="";
		File source =new File("C:\\Eclipse\\Data.xls");
	    FileInputStream input= new FileInputStream(source);
	    HSSFWorkbook workbook = new HSSFWorkbook(input);
	    
		// Load sheet- Here we are loading first sheet 
		HSSFSheet sheet = workbook.getSheetAt(1);
		int totalrow = sheet.getLastRowNum();
		
	
	    for (int i = 1; i <= sheet.getLastRowNum()+1; i++) {
	    	
		driver.getCurrentUrl();
		HSSFCell cell = sheet.getRow(i).getCell(0);
		cell.setCellType(CellType.STRING);
		driver.findElement(By.id("email")).sendKeys(cell.getStringCellValue());
		Thread.sleep(5000);

		//cell = sheet.getRow(i).getCell(1);
		//cell.setCellType(CellType.STRING);
		//driver.findElement(By.id("pass")).sendKeys(cell.getStringCellValue());
		//Thread.sleep(5000);

		expectedResult = sheet.getRow(i).getCell(2).toString();
		driver.findElement(By.id("loginbutton")).click();
		Thread.sleep(5000);

		WebElement error = driver.findElement(By.xpath("//div[text()='Invalid username or password']"));
		Thread.sleep(5000);

		actualResult = error.getText();
		Thread.sleep(5000);

		// Specify the message needs to be written.

		if (expectedResult.contentEquals(actualResult)) {
			String pass = "PASS";
			sheet.getRow(i).createCell(3).setCellValue(actualResult);

			sheet.getRow(i).createCell(4).setCellValue(pass);
			System.out.println("Test Pass !!!");

		} else {
			String fail = "FAIL";
			sheet.getRow(i).createCell(3).setCellValue(actualResult);

			sheet.getRow(i).createCell(4).setCellValue(fail);

			System.out.println("Test Fail");
		}

		input.close();
		FileOutputStream fileOutput = new FileOutputStream(source);

		// finally write content
		workbook.write(fileOutput);

		// close the file
		fileOutput.close();
		driver.close();
	   
	    }
	
		
	}
	
	
	
	
}
