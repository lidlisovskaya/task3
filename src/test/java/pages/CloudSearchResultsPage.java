package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CloudSearchResultsPage extends BasePage {
    @FindBy(xpath = "//div[@class='gs-title']//b[contains(text(),'Google Cloud Pricing Calculator')]")
    private WebElement calculatorLink;

    public CloudSearchResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CloudCalculatorPage clickMatchingResult() {
        calculatorLink.click();
        return new CloudCalculatorPage(driver);
    }
}
