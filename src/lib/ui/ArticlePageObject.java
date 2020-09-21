package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
        TITLE = "id:org.wikipedia:id/view_page_title_text",
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
        OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']",
        ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
        MY_LIST_OK_BUTTON = "xpath://*[@text='OK']",
        CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
        NAME_OF_EXISTING_FOLDER_TMP = "xpath://*[@text='{FOLDER_NAME}']";

/*
        CHANGE_LANG_BUTTON = "//*[@text='Change language']",
        SHARE_LINK_BUTTON = "//*[@text='Share link']",
        ADD_TO_READING_LIST_BUTTON = "//*[@text='Add to reading list']",
        FIND_IN_PAGE_BUTTON = "//*[@text='Find in page']",
        SIMILAR_PAGES_BUTTON = "//*[@text='Similar pages']",
        FONT_AND_THEME_BUTTON = "//*[@text='Font and theme']";
*/

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }


    private static String getExistingFolderXpathByName(String name_of_folder) {
        return NAME_OF_EXISTING_FOLDER_TMP.replace("{FOLDER_NAME}", name_of_folder);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(
                TITLE,
                "Can not find article title on page", 15
        );
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "Can not find the end of article", 20
        );
    }

    public void addArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Can not find button to open article options",
                5
        );


/*
        this.waitForOptionsMenuToRender(
                CHANGE_LANG_BUTTON,
                SHARE_LINK_BUTTON,
                ADD_TO_READING_LIST_BUTTON,
                FIND_IN_PAGE_BUTTON,
                SIMILAR_PAGES_BUTTON,
                FONT_AND_THEME_BUTTON,
                "Can not find all options in the menu"
        );
*/


        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Can not find option to add article to reading list",
                5
        );
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Can not find 'Got it' tip overlay",
                5
        );
        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Can not find input to set name of articles folder",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Can not put text into articles folder input",
                5
        );
        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Can not press OK button",
                5
        );
    }

    public void addArticleToExistingMyList(String name_of_folder) {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Can not find button to open article options",
                5
        );

/*        this.waitForOptionsMenuToRender(
                By.xpath("//*[@text='Change language']"),
                By.xpath("//*[@text='Share link']"),
                By.xpath("//*[@text='Add to reading list']"),
                By.xpath("//*[@text='Find in page']"),
                By.xpath("//*[@text='Font and theme']"),
                "Can not find all options in the menu"
        );*/

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Can not find option to add article to reading list",
                5
        );

        String folder_name_xpath = getExistingFolderXpathByName(name_of_folder);

        this.waitForElementAndClick(
                folder_name_xpath,
                "Can not find created folder",
                5
        );

    }


    public void closeArticle() {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Can not find close button, can not find X link",
                5
        );
    }

    public void checkElementTitle() {
        this.assertElementPresent(
                TITLE,
                "Element title not found"
                );
    }

}
