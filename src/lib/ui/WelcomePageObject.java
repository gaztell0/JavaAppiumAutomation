package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject {

    private static final String
    STEP_LEARN_MORE_LINK = "//XCUIElementTypeStaticText[@name='Learn more about Wikipedia']",
    STEP_NEW_WAYS_TO_EXPLORE_TEXT = "//XCUIElementTypeStaticText[@name='New ways to explore']",
    STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK = "//XCUIElementTypeStaticText[@name='Add or edit preferred languages']",
    STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "//XCUIElementTypeStaticText[@name='Learn more about data collected']",
    NEXT_BUTTON = "//XCUIElementTypeButton[@name='Next']",
    GET_STARTED_BUTTON = "//XCUIElementTypeButton[@name='Get started']";


    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink() {
        this.waitForElementPresent(By.xpath(STEP_LEARN_MORE_LINK), "Can not find 'Learn more about Wikipedia' link", 10);
    }

    public void waitForNewWaysToExploreText() {
        this.waitForElementPresent(By.xpath(STEP_NEW_WAYS_TO_EXPLORE_TEXT), "Can not find 'New ways to explore' text", 10);
    }

    public void waitForAddOrEditPreferredLangLink() {
        this.waitForElementPresent(By.xpath(STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK), "Can not find 'Add or edit preferred languages' link", 10);
    }

    public void waitForLearnMoreAboutDataCollectedLink() {
        this.waitForElementPresent(By.xpath(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK), "Can not find 'Learn more about data collected' link", 10);
    }

    public void clickNextButton() {
        this.waitForElementAndClick(By.xpath(NEXT_BUTTON), "Can not find and click 'Next' button", 10);
    }

    public void clickGetStartedButton() {
        this.waitForElementAndClick(By.xpath(GET_STARTED_BUTTON), "Can not find and click 'Get started' button", 10);
    }

}
