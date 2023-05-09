import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import tests.BrokenLinks;
import tests.BrokenImages;

public class Main {

	private static final String PATH_CHROME = "drivers\\chromedriver-113.exe";
	private static final String PATH_FIREFOX = "drivers\\geckodriver-0.33.0.exe";
	private static final String PATH_EDGE = "drivers\\msedgedriver-112.exe";

	@BeforeSuite
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", PATH_CHROME);
		System.setProperty("webdriver.edge.driver", PATH_EDGE);
		System.setProperty("webdriver.firefox.driver", PATH_FIREFOX);
	}

	@Test(priority = 1)
	private static void links(){
		WebDriver driver = new ChromeDriver();
		String host = "https://google.com";
		// driver.manage().window().setPosition(new Point(500, 0));
		// driver.manage().window().setSize(new Dimension(800, 600));
		BrokenLinks.findAllLinks(driver,host);
	}

	@Test(priority = 2)
	private static void	images(){
		WebDriver driver = new ChromeDriver();
		host = "https://en.wikipedia.org/wiki/Main_Page";
		BrokenImages.findImageLinks(driver,host);
	}

}