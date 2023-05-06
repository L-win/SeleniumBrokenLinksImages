package tests;

import org.openqa.selenium.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BrokenLinks {

	public static ArrayList<String> allLinks = new ArrayList<>();

	public static void findAllLinks(WebDriver driver, String host){
		System.out.println("Gathering links...");
		try{
			driver.get(host);
			List<WebElement> allLinksTemp = driver.findElements(By.tagName("a"));
			System.out.println("Found " + allLinksTemp.size() + " links.");
			for (WebElement el: allLinksTemp){
				allLinks.add(el.getAttribute("href"));
			}
			Thread.sleep(3000);
		}
		catch(Exception e){
			System.out.println("[ERROR]\n" + e.getMessage());
		}
		finally{
			driver.close();
			driver.quit();
		}
	}

	public static void verifyAllLinks(WebDriver driver){
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
				}
				else{
					System.out.println(con.getResponseMessage() + " " + link);
				}
				Thread.sleep(1000);
			}
		}
		catch(Exception e){
			System.out.println("[ERROR] Unknown lik: " + e.getMessage());
			e.printStackTrace();
		}
		finally{
			if (con != null) con.disconnect();
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
		HttpURLConnection con = null;
		try{
			URL url = new URL(link);
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
			driver.quit();
			System.out.println("[TEST STOP]");
		}
	}

}