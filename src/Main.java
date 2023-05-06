import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import tests.BrokenLinks;
import tests.BrokenImages;

public class Main {

	private static WebDriver driver;
	private static final String PATH_CHROME = "drivers\\chromedriver-113.exe";
	private static final String PATH_FIREFOX = "drivers\\geckodriver-0.33.0.exe";
	private static final String PATH_EDGE = "drivers\\msedgedriver-112.exe";

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", PATH_CHROME);
		System.setProperty("webdriver.edge.driver", PATH_EDGE);
		System.setProperty("webdriver.firefox.driver", PATH_FIREFOX);

		String host = "https://en.wikipedia.org/wiki/Main_Page";

		driver = new ChromeDriver();
		driver.manage().window().setPosition(new Point(500, 0));
		driver.manage().window().setSize(new Dimension(800, 600));

		// BrokenLinks.findAllLinks(driver,host);
		// BrokenLinks.verifyAllLinks(driver);
		BrokenImages.gatherImageLinks(driver,host);
	}
}