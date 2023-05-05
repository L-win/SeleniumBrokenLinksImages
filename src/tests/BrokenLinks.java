package tests;

import org.openqa.selenium.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BrokenLinks {

	public static ArrayList<String> allLinks = new ArrayList<>();

	public static void findLinks(WebDriver driver){
		System.out.println("Gathering links...");
		try {
			String url = "";
			driver.get("https://www.google.com");
			driver.manage().window().setPosition(new Point(500, 0));
			driver.manage().window().setSize(new Dimension(800, 600));

			List<WebElement> allLinksTemp = driver.findElements(By.tagName("a"));
			Iterator<WebElement> iterator = allLinksTemp.iterator();

			System.out.println("Found " + allLinksTemp.size() + " links.");

			while (iterator.hasNext()) {
				url = iterator.next().getAttribute("href");
				allLinks.add(url);
			}

			Thread.sleep(3000);
		} catch(Exception e) {
			System.out.println("[ERROR]\n" + e.getMessage());
		} finally {
			driver.close();
			driver.quit();
		}

	}

	public static void findOneLink(WebDriver driver){
		driver.get("https://www.google.com");
		List<WebElement> allLinksTemp = driver.findElements(By.tagName("a"));
		System.out.println(allLinksTemp.get(0).getAttribute("href"));
		System.out.println(allLinksTemp.get(1).getAttribute("href"));
		System.out.println(allLinksTemp.get(3).getAttribute("href"));
	}

	public static void verifyOneLink(WebDriver driver,String link){
//		String link = "";
		HttpURLConnection con = null;
		try{
			URL url = new URL(link);
//			String link = "";
//			URL url = new URL(link);
			con = (HttpURLConnection) url.openConnection();

			con.setConnectTimeout(5000);
			con.connect();

			if (con.getResponseCode() >= 400){
				System.out.println(link + " - " + con.getResponseMessage() + "is a broken link");
			} else {
				System.out.println(link + " - " + con.getResponseMessage());
			}
			Thread.sleep(1000);
		} catch(Exception e){
			System.out.println("[ERROR] Unknown lik: " + e.getMessage());
			e.printStackTrace();
		} finally{
			if (con != null) con.disconnect();
//			driver.close();
			driver.quit();
			System.out.println("[TEST STOP]");
		}
	}

	public static void verifyLinks(WebDriver driver){
//		String link = "";
		HttpURLConnection con = null;
		System.out.println("Verifying "+allLinks.size()+" links...");
		try{
			for (String link: allLinks){
				URL url = new URL(link);
				con = (HttpURLConnection) url.openConnection();

				con.setConnectTimeout(5000);
				con.connect();

				if (con.getResponseCode() >= 400){
					System.out.println(link + " - " + con.getResponseMessage() + "is a broken link");
				} else {
					System.out.println(con.getResponseMessage() + " " + link);
				}
				Thread.sleep(2000);
			}

		} catch(Exception e){
			System.out.println("[ERROR] Unknown lik: " + e.getMessage());
			e.printStackTrace();
		} finally{
			if (con != null) con.disconnect();
			driver.quit();
			System.out.println("[TEST STOP]");
		}

	}
}