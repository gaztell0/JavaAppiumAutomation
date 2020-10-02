package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class IOSMyLIstsPageObject extends MyListsPageObject {
    static {
        ARTICLE_BY_TITLE_TMP = "xpath://XCUIElementTypeLink[contains(@name,'{TITLE}')]";
        CLOSE_SYNC_WINDOW = "id:Close";
    }
    public IOSMyLIstsPageObject(AppiumDriver driver) {
        super(driver);
    }
}
