import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import tests.BrokenLinks;

public class Main {

	private static WebDriver driver;
	private static final String PATH_CHROME = "drivers\\chromedriver-113.exe";
	private static final String PATH_FIREFOX = "drivers\\geckodriver-0.33.0.exe";
	private static final String PATH_EDGE = "drivers\\msedgedriver-112.exe";

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", PATH_CHROME);
		System.setProperty("webdriver.edge.driver", PATH_EDGE);
		System.setProperty("webdriver.firefox.driver", PATH_FIREFOX);

		System.out.println("[START TEST]");

		driver = new ChromeDriver();

		BrokenLinks.findLinks(driver);
//		BrokenLinks.findOneLink(driver);

	}
}