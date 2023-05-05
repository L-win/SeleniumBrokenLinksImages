package tests;

import org.openqa.selenium.*;
import java.util.Iterator;
import java.util.List;


public class BrokenLinks {

	public static void findLinks(WebDriver driver){

		String url = "";

		try {

			driver.get("https://www.google.com");
			driver.manage().window().setPosition(new Point(500, 0));
			driver.manage().window().setSize(new Dimension(800, 900));

			List<WebElement> allLinks = driver.findElements(By.tagName("a"));
			Iterator<WebElement> iterator = allLinks.iterator();

			int links = allLinks.size();
			int currentLink = 0;
			System.out.println("Found "+links);

			while (iterator.hasNext()) {
				currentLink += 1;
//				url = iterator.next().getText();
				url = iterator.next().getAttribute("href");
				System.out.println("Link " + currentLink + "/" + links);
				System.out.println(url);
			}

			Thread.sleep(3000);

		} catch(Exception e) {

			System.out.println("[ERROR]" + e.getMessage());

		} finally {

			System.out.println("[TEST STOP]");
			driver.quit();

		}

	}

	public static void findOneLink(WebDriver driver){
		driver.get("https://www.google.com");
		List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		System.out.println(allLinks.get(0).getAttribute("href"));
		System.out.println(allLinks.get(1).getAttribute("href"));
		System.out.println(allLinks.get(3).getAttribute("href"));
	}

}
