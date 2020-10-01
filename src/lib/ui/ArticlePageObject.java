package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import lib.Platform;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
        TITLE,
        FOOTER_ELEMENT,
        OPTIONS_BUTTON,
        OPTIONS_ADD_TO_MY_LIST_BUTTON,
        ADD_TO_MY_LIST_OVERLAY,
        MY_LIST_NAME_INPUT,
        MY_LIST_OK_BUTTON,
        CLOSE_ARTICLE_BUTTON,
        NAME_OF_EXISTING_FOLDER_TMP;

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
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else return title_element.getAttribute("name");
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
