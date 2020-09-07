package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class ArticlePageObject extends MainPageObject {

    private static final String
        TITLE = "org.wikipedia:id/view_page_title_text",
        FOOTER_ELEMENT = "//*[@text='View page in browser']",
        OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[@text='Add to reading list']",
        ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
        MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
        MY_LIST_OK_BUTTON = "//*[@text='OK']",
        CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
        NAME_OF_EXISTING_FOLDER_TMP = "//*[@text='{FOLDER_NAME}']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getExistingFolderXpathByName(String name_of_folder) {
        return NAME_OF_EXISTING_FOLDER_TMP.replace("{FOLDER_NAME}", name_of_folder);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.id(TITLE), "Can not find article title on page", 15);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT), "Can not find the end of article", 20);
    }

    public void addArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Can not find button to open article options",
                5
        );


        this.waitForOptionsMenuToRender(
                By.xpath("//*[@text='Change language']"),
                By.xpath("//*[@text='Share link']"),
                By.xpath("//*[@text='Add to reading list']"),
                By.xpath("//*[@text='Find in page']"),
                By.xpath("//*[@text='Similar pages']"),
                By.xpath("//*[@text='Font and theme']"),
                "Can not find all options in the menu"
        );

        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Can not find option to add article to reading list",
                5
        );
        this.waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY),
                "Can not find 'Got it' tip overlay",
                5
        );
        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),
                "Can not find input to set name of articles folder",
                5
        );

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Can not put text into articles folder input",
                5
        );
        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Can not press OK button",
                5
        );
    }

    public void addArticleToExistingMyList(String name_of_folder) {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Can not find button to open article options",
                5
        );

        this.waitForOptionsMenuToRender(
                By.xpath("//*[@text='Change language']"),
                By.xpath("//*[@text='Share link']"),
                By.xpath("//*[@text='Add to reading list']"),
                By.xpath("//*[@text='Find in page']"),
                By.xpath("//*[@text='Font and theme']"),
                "Can not find all options in the menu"
        );

        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Can not find option to add article to reading list",
                5
        );

        String folder_name_xpath = getExistingFolderXpathByName(name_of_folder);

        this.waitForElementAndClick(
                By.xpath(folder_name_xpath),
                "Can not find created folder",
                5
        );

    }


    public void closeArticle() {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Can not find close button, can not find X link",
                5
        );
    }

}
