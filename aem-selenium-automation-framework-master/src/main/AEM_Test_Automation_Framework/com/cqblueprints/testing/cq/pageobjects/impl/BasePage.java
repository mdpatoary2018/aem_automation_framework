package com.cqblueprints.testing.cq.pageobjects.impl;

import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * Base page used as parent class for other PageObjects to inherit from.
 * Provides common functions that are used across different page types
 * @author MdShohid H Patoary
 */
public class BasePage {
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	/**
	 * Validate an element exists by a selenium selector
	 * 
	 * @param by selenium selector of the target element
	 */
	public void assertExists(By by) {
		WebElement el = null;
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			el = driver.findElement(by);
		} catch (Exception e) {
			
		}
		Assert.assertNotNull("Failed to find element: "+by.toString(), el);
	}

	public void assertNotExists(By by) {
		WebElement el = null;
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			el = driver.findElement(by);
		} catch (Exception e) {

		}
		Assert.assertNull("Element exists: "+by.toString(), el);
	}

    public void assertVisibility(By by, boolean visible) {
        WebElement el = null;
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            el = driver.findElement(by);
        } catch (Exception e) {

        }
        if (visible) {
			Assert.assertNotNull("Element should be visible but is not: " + by.toString(), el);
		} else {
			Assert.assertNull("Element should not be visible but is: " + by.toString(), el);
		}
    }
	
	/**
	 * Validate an element has the specified text by a selenium selector
	 * 
	 * @param by selenium selector of the target element
	 * @param text the expected text value
	 */
	public void assertText(By by, String text) {
		WebElement el = null;
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			el = driver.findElement(by);
		} catch (Exception e) {
			Assert.fail("Failed to find element "+by.toString());
		}
		Assert.assertNotNull("Text is not correct", el.getText().equals(text));
	}
	
	/**
	 * Validate a link created using the List component
	 * 
	 * @param s The link text to search for
	 */
	public void checkLinkByText(String s) {
		Assert.assertNotNull("Couldn't find link "+s, driver.findElement(By.xpath("//a/h4[text()='"+s+"']")));
	}
	
	/**
	 * Validate a link has the specified text by a selenium selector
	 * 
	 * @param link The link text to search for
	 */
	public void assertLinkText(String link) {
		Assert.assertNotNull("Couldn't find link "+link, driver.findElement(By.linkText(link)));
	}
	
	/**
	 * Switches to an iframe based on a selenium selector
	 * @param by The selector to use to locate the iframe
	 */
	public void switchToFrame(By by) {
		WebElement el = driver.findElement(by);
		driver.switchTo().frame(el);
	}
	
	/**
	 * Tests the width and height of a component
	 * 
	 * @param parsysName Parent parsys of the element
	 * @param width      The expected width
	 * @param height     The expected height
	 */
	public void checkWidthAndHeightOfComponent(String parsysName, String width, String height) {
		try {
		List<WebElement> dropTargets = driver.findElements(By.xpath("//*[@id='OverlayWrapper']/div/div")); //.findElements(By.xpath("//div[@class='cq-droptarget']"));
		for (WebElement el : dropTargets) {
			if (el.getAttribute("data-path") != null) {
				if (el.getAttribute("data-path").endsWith("/"+parsysName.toLowerCase().replace(" ", ""))) {
					WebElement childDiv = el.findElement(By.tagName("div"));
					Assert.assertTrue("Width doens't match "+width, childDiv.getAttribute("width").equals(width));
					Assert.assertTrue("Height doens't match "+height, childDiv.getAttribute("height").equals(height));
					break;
				}
			}
		}
		} catch (Exception e) {
			Assert.fail("Failed to find component section "+parsysName);
		}
	}
	
	/**
	 * Tests the breadcrumb of a page to validate it matches the content hierarchy
	 */
	public void validateBreadcrumbs() {
		List<WebElement> breadcrumbLinks = driver.findElements(By.xpath("//*[contains(@class,'breadcrumb')]//a"));
		String[] parentPages = driver.getCurrentUrl().split("/");
		int breadcrumbLinkCount = 0;
		List<String> missingLinks = new ArrayList<String>();
		for (int i = parentPages.length; i > 0; i--) {
			if ( i == parentPages.length) {
				continue;
			}
			for (WebElement link : breadcrumbLinks) {
				if (link.getText() == parentPages[i]) {
					breadcrumbLinkCount++;
					missingLinks.add(parentPages[i]);
					break;
				}
			}
		}
		String listString = "";

		for (String s : missingLinks)
		{
		    listString += s + "\t";
		}
		Assert.assertTrue("Breadcrumb missing links "+ listString, breadcrumbLinkCount == parentPages.length-2);
	}
	
	/**
	 * Waits for an element and clicks it.&nbsp;Found by a selenium selector
	 * 
	 * @param by The selenium selector of the element
	 */
	public void clickBy(By by) {
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
		driver.findElement(by).click();
	}
	
	/**
	 * Extracts text from an element
	 * @param by The selenium selector of the element to extract text from
	 * @return String value of the elements text
	 */
	public String getTextFromElement(By by) {
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
		return driver.findElement(by).getText();
	}
	
	/**
	 * Waits for and finds a list of elements by their selenium selector
	 * @param by Selenium selector of the elements
	 * @return List of all the found elements
	 */
	public List<WebElement> getElements(By by) {
		try {
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
		List<WebElement> retVal = driver.findElements(by);
		return retVal;
		} catch (Exception e) {
			return new ArrayList<WebElement>();
		}
	}
	
	/**
	 * Extracts text from an html input field
	 * @param by The selenium selector of the element to extract text from
	 * @return String value of the input fields text
	 */
	public String getTextFromInput(By by) {
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
		return driver.findElement(by).getAttribute("value");
	}
	
	/**
	 * Extracts text from an html input field
	 * @param ele The element to extract text from
	 * @return String value of the input fields text
	 **/
	public String getTextFromInput(WebElement ele) {
		wait.until(ExpectedConditions.visibilityOf(ele));
		return ele.getAttribute("value");
	}
}
