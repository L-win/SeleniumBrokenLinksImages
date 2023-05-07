package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BrokenImages{

	public static ArrayList<String> imageLinks = new ArrayList<>();

	public static void findImageLinks(WebDriver driver, String host){
		System.out.println("Gathering image links..");
		try{
			driver.get(host);
			List<WebElement> images = driver.findElements(By.tagName("img"));
			System.out.println("Found "+images.size()+" image links.");
			int n = images.size();
			for (WebElement img: images){
//				System.out.println("["+n--+"]");
//				System.out.println(img.getAttribute("src"));
				if (isNotBroken(driver,img)){
					System.out.println("["+n--+"]"+"[Displayed]"+" "+img.getAttribute("src"));
				} else{
					System.out.println("["+n--+"]"+"[Not displayed]"+" "+img.getAttribute("src"));
				}

			}
			Thread.sleep(4000);
		} catch(Exception e){
			System.out.println("[ERROR]\n" + e.getMessage());
		} finally{
			driver.close();
			driver.quit();
		}
	}

	private static boolean isValidLink(WebDriver driver, String link){
		boolean result = false;
		try {
			URL url = new URL(link);
			HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
			httpURLConnect.setConnectTimeout(5000);
			httpURLConnect.connect();
			if(httpURLConnect.getResponseCode()>=400) {
				result = false;
			} else{
				result = true;
			}
		} catch (Exception e) {

		}
		return result;
	}

	private static boolean isNotBroken(WebDriver driver, WebElement image){
		boolean result = false;
		try {
			boolean imageDisplayed = (Boolean) ((JavascriptExecutor) driver).executeScript("return (typeof arguments[0].naturalWidth !=\"undefined\" && arguments[0].naturalWidth > 0);", image);
			if (imageDisplayed) {
				result = true;
			} else {
				result = false;
			}
		} catch (Exception e) {
			System.out.println("[ERROR]"+e.getMessage());
		}
		return result;
	}
}