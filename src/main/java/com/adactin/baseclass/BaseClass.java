package com.adactin.baseclass;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class BaseClass {
	public static WebDriver driver;

	// Launch Browser
	public static WebDriver browserLaunch(String browserName) throws Exception {
		try {
			if (browserName.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver",
						"C:\\Users\\User\\eclipse-workspace\\Cucumber\\src\\test\\resource\\com\\cucumber\\drivers\\chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("Internet Explorer")) {
				System.setProperty("webdriver.ie.driver", "");
				driver = new InternetExplorerDriver();
			} else if (browserName.equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver", "");
				driver = new FirefoxDriver();
			} else {
				throw new Exception("Not a valid browser name");
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return driver;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	// Get URL
	public static void getUrl(String url) throws Exception {
		try {
			driver.get(url);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Url is not present");
		}
	}

	// Mouse hover
	public static void mouseOver(WebElement element) throws Exception {
		try {
			waitForVisibilityOfElement(element);
			Actions ac = new Actions(driver);
			if (elementIsDisplayed(element) && elementIsEnabled(element)) {
				ac.moveToElement(element).build().perform();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	// Current Page URL
	public static String getCurrentPageUrl() throws Exception {
		String currentUrl = null;
		try {
			currentUrl = driver.getCurrentUrl();
			System.out.println(currentUrl);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return currentUrl;
	}

	// Page Title
	public static String getPageTitle() throws Exception {
		String title = null;
		try {
			title = driver.getTitle();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return title;
	}

	// Scroll into view
	public static void scroll(WebElement element) throws Exception {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", element);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	// sendKeys
	public static void sendKey(WebElement element, String userValue) throws Exception {
		try {
			waitForVisibilityOfElement(element);
			boolean elementIsDisplayed = elementIsDisplayed(element);
			boolean elementIsEnabled = elementIsEnabled(element);
			if (elementIsDisplayed && elementIsEnabled) {
				element.clear();
				element.sendKeys(userValue);
			}
			String actual = getAttributeValue(element);
			Assert.assertEquals(userValue, actual);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	// Dropdown
	public static void dropdown(WebElement element, String selectMethod, String i) throws Exception {
		try {
			Select s = new Select(element);
			if (selectMethod.equals("index"))
				s.selectByIndex(Integer.parseInt(i));
			else if (selectMethod.equals("value"))
				s.selectByValue(i);
			else if (selectMethod.equals("text"))
				s.selectByVisibleText(i);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	// Click on the WebElement
	public static void clickOnWebElement(WebElement element) throws Exception {
		try {
			waitForVisibilityOfElement(element);
			if (elementIsDisplayed(element)) {
				element.click();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	// Navigate To
	public static void navigateTo(String url) throws Exception {
		try {
			driver.navigate().to(url);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	// Navigate Back
	public static void navigateBack() throws Exception {
		try {
			driver.navigate().back();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	// Navigate Forward

	public static void navigateForward() throws Exception {
		try {
			driver.navigate().forward();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	// Navigate Refresh
	public static void refresh() throws Exception {
		try {
			driver.navigate().refresh();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	// getText form the WebElemet
	public static String getTextFromWebElement(WebElement element) throws Exception {
		try {
			String text = element.getText();
			return text;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	// Get the value from the input tag
	public static String getAttributeValue(WebElement element) throws Exception {
		try {
			String attribute = element.getAttribute("value");
			return attribute;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	// Radio Button Click
	public static void radioButton(WebElement element) throws Exception {
		try {
			element.click();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	// Get Options from Dropdown
	public static void getoptions(WebElement element) throws Exception {
		try {
			Select y = new Select(element);
			List<WebElement> options = y.getOptions();
			for (WebElement option : options) {
				System.out.println(option.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	// first selected option
	public static void firstSelectedOp(WebElement element) throws Exception {
		try {
			Select y = new Select(element);
			WebElement firstSelectedOption = y.getFirstSelectedOption();
			System.out.println(firstSelectedOption.getText());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	// screen shot
	public static void screenshot() throws Exception {
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File screenshotAs = ts.getScreenshotAs(OutputType.FILE);
			File saveFile = new File("C:\\Users\\User\\eclipse-workspace\\Cucumber\\Screenshot\\first.png");
			Files.copy(screenshotAs, saveFile);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	// Window Handles for 2 Windows
	public static void windowHandles(WebElement element) throws Exception {
		try {
			String childid = driver.getWindowHandle();
			element.click();
			Set<String> parentid = driver.getWindowHandles();
			String y = null;
			for (String d : parentid) {
				if (!d.equals(childid)) {
					y = d;
				}
			}
			driver.switchTo().window(y);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	// Alerts - Ok or Cancel
	public static void alertAcceptDismiss(WebElement element, String var) throws Exception {
		try {
			element.click();
			Alert alert = driver.switchTo().alert();
			if (var == "accept") {
				alert.accept();
				driver.switchTo().defaultContent();
			} else {
				alert.dismiss();
				driver.switchTo().defaultContent();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	// Alert SendKeys
	public static void alertSendKeys(WebElement element, String value) throws Exception {
		try {
			element.click();
			Alert alert = driver.switchTo().alert();
			alert.sendKeys(value);
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	// close
	public static void closee() throws Exception {
		try {
			driver.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	// quit
	public static void quitt() throws Exception {
		try {
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	// double click
	public static void doubleClickByMouse(WebElement element) throws Exception {
		try {
			Actions ac1 = new Actions(driver);
			ac1.doubleClick(element).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	// click
	public static void clickByMouse(WebElement element) throws Exception {
		try {
			Actions ac2 = new Actions(driver);
			ac2.click(element).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	// right click
	public static void rightclick(WebElement element) throws Exception {
		try {
			Actions ac3 = new Actions(driver);
			ac3.contextClick(element).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	// send keys using actions
	public static void sendkeysActions(WebElement element, String value) throws Exception {
		try {
			Actions ac4 = new Actions(driver);
			ac4.sendKeys(element, value).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	// robot
	public static void robotKeyboardEvent(WebElement element, String value) throws Exception {
		try {
			Actions ac = new Actions(driver);
			ac.contextClick(element).build().perform();
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyRelease(KeyEvent.VK_DOWN);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}

	}

	// drag and drop
	public void dragdrop(WebElement element1, WebElement element2) throws Exception {
		try {
			Actions ac = new Actions(driver);
			ac.dragAndDrop(element1, element2).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	// i-Frames

	public void iframes(WebElement elements) throws Exception {
		try {
			driver.switchTo().frame(elements);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}

	}

	// Explicit Wait
	public static void waitForVisibilityOfElement(WebElement element) throws Exception {
		try {
			WebDriverWait wb = new WebDriverWait(driver, 50);
			wb.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	// Check Element is displayed
	public static boolean elementIsDisplayed(WebElement element) throws Exception {
		try {
			boolean displayed = element.isDisplayed();
			return displayed;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	// Check Element is enabled
	public static boolean elementIsEnabled(WebElement element) throws Exception {
		try {
			boolean enabled = element.isEnabled();
			return enabled;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	// isSelected
		public boolean elementisselected(WebElement element) throws Exception {
			boolean selected = false;
			try {
				selected = element.isSelected();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception();
			}
			return selected;
		}
}