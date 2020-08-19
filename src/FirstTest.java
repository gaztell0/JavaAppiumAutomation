import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.1");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/taras/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @Test
    public void firstTest() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can not find 'Search Wikipedia' input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Can not find search input",
                5
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Object-oriented programming language']"),
                "Can not find Object-oriented programming language topic searching by 'Java'",
                15
        );
    }

    @Test
    public void testCancelSearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can not find 'Search Wikipedia' input",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Can not find X to cancel search",
                5
        );

        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present on the page",
                5
        );

    }

    @Test
    public void testClearSearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can not find 'Search Wikipedia' input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Can not find search input",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Can not find search field",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Can not find X to cancel search",
                5
        );

        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present on the page",
                5
        );

    }

    @Test
    public void testCompareArticleTitle() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can not find 'Search Wikipedia' input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Can not find search input",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Object-oriented programming language']"),
                "Can not find 'Search Wikipedia' input",
                5
        );
       WebElement title_element = waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can not find article title",
                15
        );

       String article_title = title_element.getAttribute("text");
        Assert.assertEquals(
                "We see unexpected title!",
                "Java (programming language)",
                article_title
        );
    }

    @Test
    public void testSearchFieldContainsText() {
        assertElementHasText(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Search field doesn't contain expected text",
                "Search Wikipedia"
        );
    }

    @Test
    public void testSearchAndCancel() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can not find 'Search Wikipedia' input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Liverpool",
                "Can not find search input",
                5
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='City in Merseyside, England, United Kingdom']"),
                "Can not find 'City in Merseyside, England, United Kingdom' topic searching by 'Liverpool'",
                15
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Association football club in Liverpool, England']"),
                "Can not find 'Association football club in Liverpool, England' topic searching by 'Liverpool'",
                15
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Inter-city rivalry between two English football clubs']"),
                "Can not find 'Inter-city rivalry between two English football clubs' topic searching by 'Liverpool'",
                15
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Can not find X to cancel search",
                5
        );
        waitForElementPresent(
                By.id("org.wikipedia:id/search_empty_message"),
                "Search results are still present on the page",
                5
        );

    }

    @Test
    public void testWordInSearchResults() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can not find 'Search Wikipedia' input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Liverpool",
                "Can not find search input",
                5
        );
        assertElementHasWord(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Liverpool']"),
                "Search result do not contain word 'Liverpool'",
                "Liverpool"
        );
        assertElementHasWord(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Liverpool F.C.']"),
                "Search result do not contain word 'Liverpool'",
                "Liverpool"
        );
        assertElementHasWord(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Liverpool F.C.–Manchester United F.C. rivalry']"),
                "Search result do not contain word 'Liverpool'",
                "Liverpool"
        );
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
         WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
         wait.withMessage(error_message + "\n");
         return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.clear();
        return element;
    }

    private void assertElementHasText(By by, String error_message, String expectedText) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        String text_element = element.getAttribute("text");
        Assert.assertEquals(
                error_message,
                expectedText,
                text_element
        );
    }

    private void assertElementHasWord(By by, String error_message, String word) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        String text_element = element.getAttribute("text");
        Assert.assertTrue(
                error_message,
                text_element.contains(word)
        );
    }



}
