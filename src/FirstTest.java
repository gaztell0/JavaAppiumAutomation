import lib.CoreTestCase;
import lib.ui.MainPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;

public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception {

        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testSearch() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can not find 'Search Wikipedia' input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Can not find search input",
                5
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Object-oriented programming language']"),
                "Can not find Object-oriented programming language topic searching by 'Java'",
                15
        );
    }

    @Test
    public void testCancelSearch() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can not find 'Search Wikipedia' input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Can not find X to cancel search",
                5
        );

        MainPageObject.waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present on the page",
                5
        );

    }

    @Test
    public void testClearSearch() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can not find 'Search Wikipedia' input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Can not find search input",
                5
        );

        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Can not find search field",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Can not find X to cancel search",
                5
        );

        MainPageObject.waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present on the page",
                5
        );

    }

    @Test
    public void testCompareArticleTitle() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can not find 'Search Wikipedia' input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Can not find search input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Object-oriented programming language']"),
                "Can not find 'Search Wikipedia' input",
                5
        );
       WebElement title_element = MainPageObject.waitForElementPresent(
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
        MainPageObject.assertElementHasText(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Search field doesn't contain expected text",
                "Search Wikipedia"
        );
    }

    @Test
    public void testSearchAndCancel() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can not find 'Search Wikipedia' input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Liverpool",
                "Can not find search input",
                5
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='City in Merseyside, England, United Kingdom']"),
                "Can not find 'City in Merseyside, England, United Kingdom' topic searching by 'Liverpool'",
                15
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Association football club in Liverpool, England']"),
                "Can not find 'Association football club in Liverpool, England' topic searching by 'Liverpool'",
                15
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Inter-city rivalry between two English football clubs']"),
                "Can not find 'Inter-city rivalry between two English football clubs' topic searching by 'Liverpool'",
                15
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Can not find X to cancel search",
                5
        );
        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/search_empty_message"),
                "Search results are still present on the page",
                5
        );

    }

    @Test
    public void testWordInSearchResults() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can not find 'Search Wikipedia' input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Liverpool",
                "Can not find search input",
                5
        );
        MainPageObject.assertElementHasWord(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Liverpool']"),
                "Search result do not contain word 'Liverpool'",
                "Liverpool"
        );
        MainPageObject.assertElementHasWord(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Liverpool F.C.']"),
                "Search result do not contain word 'Liverpool'",
                "Liverpool"
        );
        MainPageObject.assertElementHasWord(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Liverpool F.C.–Manchester United F.C. rivalry']"),
                "Search result do not contain word 'Liverpool'",
                "Liverpool"
        );
    }

    @Test
    public void testSwipeArticle() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can not find 'Search Wikipedia' input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Appium",
                "Can not find search input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Can not find 'Appium' in search",
                5
        );
        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can not find article title",
                15
        );

        MainPageObject.swipeUpToFindElement(
                By.xpath("//*[@text='View page in browser']"),
                "Can not find the end of the article",
                20
        );

    }

    @Test
    public void testSaveFirstArticleToMyList() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can not find 'Search Wikipedia' input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Can not find search input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Object-oriented programming language']"),
                "Can not find 'Search Wikipedia' input",
                5
        );
        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can not find article title",
                15
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Can not find button to open article options",
                5
        );
        MainPageObject.waitForOptionsMenuToRender(
                By.xpath("//*[@text='Change language']"),
                By.xpath("//*[@text='Share link']"),
                By.xpath("//*[@text='Add to reading list']"),
                By.xpath("//*[@text='Find in page']"),
                By.xpath("//*[@text='Similar pages']"),
                By.xpath("//*[@text='Font and theme']"),
                "Can not find all options in the menu"
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Can not find option to add article to reading list",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Can not find 'Got it' tip overlay",
                5
        );
        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Can not find input to set name of articles folder",
                5
        );

        String name_of_folder = "Learning programming";

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Can not put text into articles folder input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Can not press OK button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Can not find close button, can not find X link",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Can not find navigation button to My lists",
                5
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Can not delete saved article",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Can not find created folder",
                5
        );
        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Can not find saved article"
        );

        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Can not delete saved article",
                5
        );
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can not find 'Search Wikipedia' input",
                5
        );

        String search_line = "Linkin Park Diskography";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                search_line,
                "Can not find search input",
                5
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";

        MainPageObject.waitForElementPresent(
                By.xpath(search_result_locator),
                "Can not find anything by the request " + search_line,
                15
        );

        int amount_of_search_results = MainPageObject.getAmountOfElements(
                By.xpath(search_result_locator)
        );

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can not find 'Search Wikipedia' input",
                5
        );

        String search_line = "Java";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                search_line,
                "Can not find search input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Object-oriented programming language']"),
                "Can not find topic searching by " + search_line,
                15
        );

        String title_before_rotation = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Can not fimd title of article",
                15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Can not fimd title of article",
                15
        );

        Assert.assertEquals(
                "Article title has been changed after rotation",
                title_before_rotation,
                title_after_rotation
        );

        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_after_secong_rotation = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Can not fimd title of article",
                15
        );

        Assert.assertEquals(
                "Article title has been changed after rotation",
                title_before_rotation,
                title_after_secong_rotation
        );

    }

    @Test
    public void testCheckSearchArticleInBackground() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can not find 'Search Wikipedia' input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Can not find search input",
                5
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Object-oriented programming language']"),
                "Can not find 'Search Wikipedia' input",
                5
        );

        driver.runAppInBackground(2);

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Object-oriented programming language']"),
                "Can not find article after returning in background",
                5
        );
    }

    @Test
    public void testSaveTwoArticlesToMyList() {
        String first_query = "Java";
        String second_query = "Python";
        String name_of_folder = "Learning Appium Automation";

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can not find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                first_query,
                "Can not find search input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Object-oriented programming language']"),
                "Can not find topic searching by " + first_query,
                5
        );
        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can not find article title",
                15
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Can not find button to open article options",
                5
        );
        MainPageObject.waitForOptionsMenuToRender(
                By.xpath("//*[@text='Change language']"),
                By.xpath("//*[@text='Share link']"),
                By.xpath("//*[@text='Add to reading list']"),
                By.xpath("//*[@text='Find in page']"),
                By.xpath("//*[@text='Similar pages']"),
                By.xpath("//*[@text='Font and theme']"),
                "Can not find all options in the menu"
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Can not find option to add article to reading list",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Can not find 'Got it' tip overlay",
                5
        );
        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Can not find input to set name of articles folder",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Can not put text into articles folder input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Can not press OK button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Can not find close button, can not find X link",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can not find 'Search Wikipedia' input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                second_query,
                "Can not find search input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='General-purpose programming language']"),
                "Can not find topic searching by " + second_query,
                5
        );
        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can not find article title",
                15
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Can not find button to open article options",
                5
        );
        MainPageObject.waitForOptionsMenuToRender(
                By.xpath("//*[@text='Change language']"),
                By.xpath("//*[@text='Share link']"),
                By.xpath("//*[@text='Add to reading list']"),
                By.xpath("//*[@text='Find in page']"),
                By.xpath("//*[@text='Font and theme']"),
                "Can not find all options in the menu"
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Can not find option to add article to reading list",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Can not find created folder",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Can not find close button, can not find X link",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Can not find navigation button to My lists",
                5
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Can not find created folder",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Can not find created folder",
                5
        );
        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Can not find saved article"
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='Python (programming language)']"),
                "Can not find second saved article"
        );

        String title_in_folder = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/page_list_item_title"),
                "text",
                "Can not find title of article",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='general-purpose programming language']"),
                "Can not find second saved article",
                5
        );


        String title_inside = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Can not find title of article",
                15
        );

        Assert.assertEquals(
                "Article title has been changed",
                title_in_folder,
                title_inside
        );

    }

    @Test
    public void testArticleHasTitle() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can not find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Can not find search input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Object-oriented programming language']"),
                "Can not find topic searching by 'Java'",
                5
        );
        MainPageObject.assertElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Element title not found"
        );

    }

}
