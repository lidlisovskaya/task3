package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CloudHomePage extends BasePage {
    private static final String HOMEPAGE_URL = "https://cloud.google.com/";
    final String INFO_SEARCH = "Google Cloud Platform Pricing Calculator";

    @FindBy(xpath = "//input[contains(@name,'q')]")
    public static WebElement searchInput;

    public CloudHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CloudHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public CloudSearchResultsPage searchForTerms() {
        clickThis(searchInput);
        searchInput.sendKeys(INFO_SEARCH + Keys.ENTER);
        return new CloudSearchResultsPage(driver);
    }
}
