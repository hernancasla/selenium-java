package test;

import org.joda.time.DateTime;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Embajada {
	public static long getTimeToSleep() {
		DateTime now = new DateTime();
		DateTime after = (now).withHourOfDay(19).withMinuteOfHour(59).withSecondOfMinute(58).withMillisOfSecond(0);
//		DateTime after = (now).withHourOfDay(20).withMinuteOfHour(25).withSecondOfMinute(30).withMillisOfSecond(750);

		DateTime diff = after.minus(now.getMillis());
		System.out.println("milisegundos: " + diff.getMillis());
		int delta = (int) (Math.random() * 500);
		return diff.getMillis() + delta;
	}

	public static void main(String[] args) {
		// declaration and instantiation of objects/variables
		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
		WebDriver driver = new ChromeDriver();
		// comment the above 2 lines and uncomment below 2 lines to use Chrome
		String baseUrl = "https://pastel.diplomatie.gouv.fr/rdvinternet/html-4.02.00/frameset/frameset.html?lcid=1&sgid=74&suid=14";

		driver.get(baseUrl);	

		// get the actual value of the title
		// actualTitle = driver.getTitle();

		/*
		 * compare the actual title of the page with the expected one and print the
		 * result as "Passed" or "Failed"
		 */
		// if (actualTitle.contentEquals(expectedTitle)){

		driver.switchTo().frame("BODY_WIN");

		WebDriverWait wait10 = new WebDriverWait(driver, 10);

		wait10.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MENU_WIN"));

		wait10.until(ExpectedConditions.visibilityOfElementLocated(By.id("item2_0_0")));

		// ((JavascriptExecutor) driver).executeAsyncScript(
		// "javascript:parent.parent.ComposantMenuFrameset.SelectItem2Menu1(0,0,false)");
		driver.findElement(By.id("item2_0_0")).click();

		driver.switchTo().defaultContent();

		driver.switchTo().frame("BODY_WIN");

		wait10.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("CONTENU_WIN"));

		wait10.until(ExpectedConditions.elementToBeClickable(By.id("ccg")));

		driver.findElement(By.cssSelector("input[id=ccg]")).click();

		try {
			long timeToSleep = getTimeToSleep();
			System.out.println("time to sleep " + timeToSleep);
			Thread.sleep(timeToSleep);
//			Thread.sleep(5000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Click en primer siguiente: " + new DateTime());

		driver.findElement(By.id("boutonSuivant")).click();

//		wait10.until(ExpectedConditions.alertIsPresent());
//		Alert alert = driver.switchTo().alert();
//		alert.accept();

		driver.switchTo().defaultContent();

		driver.switchTo().frame("BODY_WIN");

		wait10.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("CONTENU_WIN"));

		int childNumber = (int) Math.floor(Math.random() * 3) + 1;

		String childSelector = "//*[@id=\"compTableau_tbody\"]/tr[" + childNumber + "]/td[1]/a";

//		childSelector = "//*[@id=\"Tbody_Calendrier\"]/tr[9]/td[9]";

		System.out.println("Antes del segundo siguiente: " + new DateTime());

		wait10.until(ExpectedConditions.elementToBeClickable(By.xpath(childSelector))).click();

		// driver.findElement(By.xpath(childSelector)).click();

		// wait10.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#compTableau_tbody")));

		System.out.println("Click en segundo siguiente: " + new DateTime());

		// wait10.until(ExpectedConditions.(By.id("boutonSuivant")));

//		driver.findElement(By.id("boutonSuivant")).click();
//		driver.findElement(By.id("boutonPrecedent")).click();

		((JavascriptExecutor) driver).executeScript("javascript: tabElementForm[0].suivant()");

		System.out.println("Click en el tercer siguiente: " + new DateTime());

		// driver.close();

	}
}
