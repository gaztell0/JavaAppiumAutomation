package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private final String
    name_of_folder = "Learning programming";

    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject.waitForTitleElement("Java (programming language)");
        String article_title = ArticlePageObject.getArticleTitle("Java (programming language)");

        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.closeArticle();
        } else {
            ArticlePageObject.closeArticle();
            SearchPageObject.clickCancelSearch();
        }

        NavigationUI.clickMyLists();

        if (Platform.getInstance().isIOS()) {
            MyListPageObject.clickCloseSyncWindow();
        }
        if (Platform.getInstance().isAndroid()) {
            MyListPageObject.openFolderByName(name_of_folder);
        }
        MyListPageObject.swipeByArticleToDelete(article_title);

    }

    @Test
    public void testSaveTwoArticlesToMyList() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject.waitForTitleElement("Java (programming language)");
        String article_title = ArticlePageObject.getArticleTitle("Java (programming language)");
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.closeArticle();
        } else {
            ArticlePageObject.closeArticle();
            SearchPageObject.clickCancelSearch();
        }

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Python");
        SearchPageObject.clickByArticleWithSubstring("General-purpose, high-level programming language");
        ArticlePageObject.waitForTitleElement("Python (programming language)");
        String article_title2 = ArticlePageObject.getArticleTitle("Python (programming language)");
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToExistingMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.closeArticle();
        } else {
            ArticlePageObject.closeArticle();
            SearchPageObject.clickCancelSearch();
        }

        NavigationUI.clickMyLists();

        if (Platform.getInstance().isIOS()) {
            MyListPageObject.clickCloseSyncWindow();
        }
        if (Platform.getInstance().isAndroid()) {
            MyListPageObject.openFolderByName(name_of_folder);
        }
        MyListPageObject.swipeByArticleToDelete(article_title);
        MyListPageObject.waitForArticleToAppearByTitle(article_title2);

/*        MyListPageObject.waitForArticleToAppearByTitle(article_title2);
        MyListPageObject.openArticleByName(article_title2);
        String title_inside = ArticlePageObject.getArticleTitle("Python (programming language)");
        assertEquals(
                "Article title has been changed",
                article_title2,
                title_inside
        );*/
    }

}
