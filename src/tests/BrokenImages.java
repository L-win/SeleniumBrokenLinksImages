package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BrokenImages{

	public static ArrayList<String> imageLinks = new ArrayList<>();

	public static void gatherImageLinks(WebDriver driver, String host){
		System.out.println("Gathering image links..");
		try{
			driver.get(host);
			List<WebElement> images = driver.findElements(By.tagName("img"));
			System.out.println("Found "+images.size()+" image links.");
			for (WebElement img: images){
				System.out.println(img.getAttribute("src"));
			}
			Thread.sleep(4000);
		} catch(Exception e){
			System.out.println("[ERROR]\n" + e.getMessage());
		} finally{
			driver.close();
			driver.quit();
		}
	}

	public static void verifyImages(WebDriver driver){

	}
}