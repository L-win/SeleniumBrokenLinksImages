package tests;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.JavascriptExecutor;


public class Test {

	private static WebDriver driver;

	private static final String PATH_CHROME = "drivers\\chromedriver-113.exe";
	private static final String PATH_FIREFOX = "drivers\\geckodriver-0.33.0.exe";
	private static final String PATH_EDGE = "drivers\\msedgedriver-112.exe";


	public static void main(String[] args){

		System.setProperty("webdriver.chrome.driver", PATH_CHROME);
		testOne();

	}

	public static void testOne(){

		driver = new ChromeDriver();

		try {
			driver.get("https://www.google.com");
//			driver.manage().window().maximize();
			driver.manage().window().setPosition(new Point(-10, 0));
			driver.manage().window().setSize(new Dimension(800, 900));
//			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//			JavascriptExecutor js = (JavascriptExecutor) driver;
//			js.executeScript("window.scrollBy(0,600)");
			Thread.sleep(3000);
		} catch(Exception e) {
			System.out.println("[E] Error: " + e);
		} finally {
			System.out.println("[TEST STOP]");
			driver.quit();
		}

	}


}
