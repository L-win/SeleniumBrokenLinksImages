package tests;

import org.openqa.selenium.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BrokenLinks {

	public static ArrayList<String> allLinks = new ArrayList<>();

	public static Boolean findAllLinks(WebDriver driver, String host){
		Boolean result = true;
		System.out.println("Gathering links...");
		try {
			driver.get(host);
			List<WebElement> allLinksTemp = driver.findElements(By.tagName("a"));
			System.out.println("Found " + allLinksTemp.size() + " links.");
			for (WebElement el: allLinksTemp) allLinks.add(el.getAttribute("href"));
			Thread.sleep(3000);
			// Return true or falls (assert)
			result = verifyAllLinks();
		} catch(Exception e) {
			System.out.println("[ERROR]\n" + e.getMessage());
		} finally {
			driver.close();
		}
		return result;
	}

	private static Boolean verifyAllLinks(){
		Boolean result = true;
		HttpURLConnection con = null;
		System.out.println("Verifying "+allLinks.size()+" links...");
		try {
			for (String link: allLinks){
				URL url = new URL(link);
				con = (HttpURLConnection) url.openConnection();
				con.setConnectTimeout(5000);
				con.connect();
				if (con.getResponseCode() >= 400){
					result = false;
					System.out.println(link + " - " + con.getResponseMessage() + "is a broken link");
					break;
				} else {
					System.out.println(con.getResponseMessage() + " " + link);
				}
				Thread.sleep(1000);
			}
		} catch(Exception e) {
			System.out.println("[ERROR] Unknown lik: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (con != null) con.disconnect();
		}
		return result;
	}

}