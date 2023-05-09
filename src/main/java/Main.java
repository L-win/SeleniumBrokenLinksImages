
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import tests.BrokenLinks;
import tests.BrokenImages;

public class Main {

	// @Test(priority = 1)
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
		String host = "https://en.wikipedia.org/wiki/Main_Page";
		BrokenImages.findImageLinks(driver,host);
	}

}