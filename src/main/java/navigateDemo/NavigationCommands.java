package navigateDemo;

import java.io.File;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
 
import navigateDemo.DriverSetup;
public class NavigationCommands {
	
	public static void wait(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static Properties  p=new Properties();
	
	
	public static void main(String[] args) throws Exception {
		FileInputStream f= new FileInputStream(".//src//main//resources//config.properties");
		p.load(f);
		
		NavigationCommands obj = new NavigationCommands();
		DriverSetup cfg=new DriverSetup();
		WebDriver driver = DriverSetup.getDriver();
	    driver.manage().window().maximize();
 
        
//      Enter the value in search or Edit box "Orange HRM demo” and Press enter.
		WebElement search = driver.findElement(By.name("q"));
		search.sendKeys("Orange HRM demo");
		search.submit();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.orangehrm.com/");
		
//		Click on Back arrow button and verify if the Google Page is appeared.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		driver.navigate().to("https://google.com");
		System.out.println("SuccessFully Navigated to Google Page");
		
//		Click on Forward arrow button and verify if it is redirected to the results page.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		driver.navigate().back();
		System.out.println("SuccessFully Navigated to OrangeHrm Page");
		
//      Click on "Contact Sales"
        driver.findElement(By.linkText("Contact Sales")).click();
 
//      Fill all the fields on the current webpage.
        driver.findElement(By.name("FullName")).sendKeys(p.getProperty("name"));
        driver.findElement(By.name("Contact")).sendKeys(p.getProperty("contact"));
        driver.findElement(By.name("Email")).sendKeys(p.getProperty("email"));
 
        Select countryDropdown = new Select(driver.findElement(By.name("Country")));
        countryDropdown.selectByVisibleText(p.getProperty("country"));
 
        //System.out.println(employees);
        Select employeeDropdown = new Select(driver.findElement(By.name("NoOfEmployees")));
        employeeDropdown.selectByVisibleText(p.getProperty("employee"));
 
 
        driver.findElement(By.name("JobTitle")).sendKeys(p.getProperty("jobTitle"));
        System.out.println("Please complete the CAPTCHA manually within 30 seconds...");
        wait(30000);
        
//      Click Contact sales Button
		driver.findElement(By.name("action_submitForm")).click();
		wait(4000);
 
//      Take screenshot after submission
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(screenshot, new File("screenshot.png"));
        FileHandler.copy(screenshot, new File("C://Users//2425277//Downloads//screenshot.png"));
        
//      Enter any message in the “your message” text box.
        driver.findElement(By.id("Form_getForm_Comment")).sendKeys(
            "We are looking for an HRMS solution to manage employee records and leave tracking.");
        WebElement submitButton= driver.findElement(By.name("action_submitForm"));    
        submitButton.click();
        System.out.println("Button clicked successfully");
        wait(3000);
        
//      Close the current tab and verify if it is navigating to previous tab.
        String currentTab=driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://example.com");
        driver.close();
        driver.switchTo().window(currentTab);
 
//      Validate you're back
        String currentURL = driver.getCurrentUrl();
        if (currentURL.contains("orangehrm")) {
            System.out.println("Successfully navigated back to the original tab.");
            
        } else {
            System.out.println("Failed to return to the original tab.");
            
        }
        System.out.println("hello world");
 
//      Close browser
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        driver.quit();
        
        
        System.out.println("Browser Closed");
        
        
        
        
    }
 
}