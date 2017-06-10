package com.pack;

import java.lang.Thread;
import org.junit.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;

public class FutureAdvisorAutomation {
	public static void main(String...args) {
		WebDriver driver = new ChromeDriver();

		executeGoogleSearch(driver, "futureadvisor.com");
		waitForElementByCss(driver, "div#res div#ires");
		assertPageTitle(driver, "futureadvisor.com - Google Search");

		clickFirstGoogleResult(driver);
		waitForElementByCss(driver, "nav.primary-nav");
		assertPageTitle(driver, "Online Financial Advisor & Investing Advice | FutureAdvisor");

		navigateToRetirementPage(driver);

		WebElement pageText = waitForElementByXpath(driver, "//*[contains(.,'create a plan')]");
		assertPageTitle(driver, "Retirement Planning | Future Advisor");
		pageText.sendKeys(Keys.CONTROL, Keys.END);
		sleep(500);

		waitForElementAndClick(driver, "div.container>ul.user-control>li:nth-of-type(1)>a");

		WebElement emailInput = waitForElementByCss(driver, "input[data-ux='emailInput']");
		assertPageTitle(driver, "FutureAdvisor | FutureAdvisor");
		emailInput.click();
		emailInput.sendKeys("invalid@example.com");

		WebElement passwordInput = waitForElementByCss(driver, "input[data-ux='passwordInput']");
		passwordInput.click();
		passwordInput.sendKeys("nope");

		waitForElementAndClick(driver, "button.btn-submit");
		WebElement errorBar = waitForElementByCss(driver, "div.xulu-messages-container>div.error");
		Assert.assertEquals("Invalid email/password", errorBar.getText());

		driver.quit();
	}

	private static WebElement waitForElementByCss(WebDriver driver, String selector) {
		WebDriverWait wait = new WebDriverWait(driver, 2000);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selector)));
		return element;
	}

	private static WebElement waitForElementByXpath(WebDriver driver, String selector) {
		WebDriverWait wait = new WebDriverWait(driver, 2000);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(selector)));
		return element;
	}

	private static void waitForElementAndClick(WebDriver driver, String selector) {
		WebDriverWait wait = new WebDriverWait(driver, 2000);
		WebElement clickable = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(selector)));
		clickable.click();
	}

	private static void assertPageTitle(WebDriver driver, String match) {
		String pageTitle = driver.getTitle();
		Assert.assertEquals(match, pageTitle);
	}

	private static void executeGoogleSearch(WebDriver driver, String searchTerm) {
		driver.navigate().to("http://google.com");

		WebElement searchBox = driver.findElement(By.cssSelector("div#searchform input[title='Search']"));
		searchBox.click();

		searchBox.sendKeys(searchTerm + Keys.RETURN);
	}

	private static void clickFirstGoogleResult(WebDriver driver) {
		WebElement firstResult = driver.findElement(By.cssSelector("div#res div #ires div#rso>div:nth-of-type(1) a"));
		firstResult.click();
	}

	public static void navigateToRetirementPage(WebDriver driver) {
		Actions actions = new Actions(driver);
		WebElement whatWeDo = driver.findElement(By.cssSelector("nav.primary-nav>ul>li.has-drop:nth-of-type(1)"));

		actions.moveToElement(whatWeDo).perform();
		WebElement retirementLink = driver.findElement(By.cssSelector("nav.primary-nav>ul>li.has-drop:nth-of-type(1)>ul>li:nth-of-type(1)>a"));
		retirementLink.click();
	}

	public static void sleep(Integer mills) {
		try{
		    Thread.sleep(mills);
		}catch(InterruptedException e){
		    System.out.println("sleep was interrupted");
		}
	}
}
