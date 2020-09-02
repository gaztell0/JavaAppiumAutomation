package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(By by, String error_message, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    public WebElement waitForElementAndClick(By by, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.clear();
        return element;
    }

    public void assertElementHasText(By by, String error_message, String expectedText) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        String text_element = element.getAttribute("text");
        Assert.assertEquals(
                error_message,
                expectedText,
                text_element
        );
    }

    public void assertElementHasWord(By by, String error_message, String word) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        String text_element = element.getAttribute("text");
        Assert.assertTrue(
                error_message,
                text_element.contains(word)
        );
    }

    public void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    public void swipeUpQuick() {
        swipeUp(200);
    }

    public void swipeUpToFindElement(By by, String error_message, int max_swipes) {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {

            if (already_swiped > max_swipes) {
                waitForElementPresent(by, "Can not find element by swiping up. \n" + error_message, 0);
                return;
            }

            swipeUpQuick();
            ++already_swiped;
        }
    }

    public void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(by, error_message, 10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(150)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

    public void waitForOptionsMenuToRender(By by1, By by2, By by3, By by4, By by5, By by6, String error_message) {
        waitForElementPresent(by1, error_message, 10);
        waitForElementPresent(by2, error_message, 10);
        waitForElementPresent(by3, error_message, 10);
        waitForElementPresent(by4, error_message, 10);
        waitForElementPresent(by5, error_message, 10);
        waitForElementPresent(by6, error_message, 10);
    }

    public void waitForOptionsMenuToRender(By by1, By by2, By by3, By by4, By by5, String error_message) {
        waitForElementPresent(by1, error_message, 10);
        waitForElementPresent(by2, error_message, 10);
        waitForElementPresent(by3, error_message, 10);
        waitForElementPresent(by4, error_message, 10);
        waitForElementPresent(by5, error_message, 10);
    }

    public int getAmountOfElements (By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    public String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    public void assertElementPresent(By by, String error_message) {
        List element = driver.findElements(by);
        if (element.size() > 0) {
            System.out.println("Element title is present");
        } else {
            System.out.println(error_message);
        }
    }

}
