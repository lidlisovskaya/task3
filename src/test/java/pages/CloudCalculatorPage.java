package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CloudCalculatorPage extends BasePage {
    private final static String INNER_FRAME_NAME = "myFrame";
    private final static String NUMBERS_OF_INSTANCES_XPATH = "//input[@type='number' and @name='quantity']";
    private final static String TOTAL_PRICE_XPATH = "//h2/b[@class='ng-binding']";

    @FindBy(xpath = "//iframe[contains(@src,'https://cloud.google.com/frame/products/calculator')]")
    private WebElement outerFrame;

    @FindBy(xpath = "//div[@title='Compute Engine']")
    private WebElement computeEngineSection;

    @FindBy(xpath = NUMBERS_OF_INSTANCES_XPATH)
    private List<WebElement> numberOfInstancesSelector;

    @FindBy(xpath = "//md-select[contains(@ng-model,'computeServer') and contains(@aria-label,'Operating System')]")
    private WebElement softwareSelector;

    @FindBy(xpath = "//md-option[@value='free']")
    private WebElement selectFree;

    @FindBy(xpath = "//md-select[@placeholder='VM Class']")
    private WebElement provisioningModelSelector;

    @FindBy(xpath = "//md-option[@value='regular']")
    private WebElement selectRegular;

    @FindBy(xpath = "//md-select[@placeholder='Series']")
    private WebElement seriesSelector;

    @FindBy(xpath = "//md-option[@value='n1']")
    private WebElement selectN1;

    @FindBy(xpath = "//md-select[@placeholder='Instance type']")
    private WebElement machineTypeSelector;

    @FindBy(xpath = "//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8']")
    private WebElement selectStandard8;

    @FindBy(xpath = "//md-checkbox[@aria-label='Add GPUs']")
    private WebElement addGpusCheckbox;

    @FindBy(xpath = "//md-select[@placeholder='GPU type']")
    private WebElement gpuTypeSelector;

    @FindBy(xpath = "//md-option[@value='NVIDIA_TESLA_P100']")
    private WebElement selectNvidiaTeslaP100;

    @FindBy(xpath = "//md-select[@placeholder='Number of GPUs']")
    private WebElement numberOfGpusSelector;

    @FindBy(xpath = "//md-option[contains(@ng-repeat, 'supportedGpuNumbers') and @value='1']")
    private WebElement select1Gpu;

    @FindBy(xpath = "//md-select[@placeholder='Local SSD' and contains(@class, 'ng-pristine')]")
    private WebElement localSsdSelector;

    @FindBy(xpath = "//md-option[contains(@ng-repeat, 'dynamicSsd') and @value='2']")
    private WebElement select2x375Gb;

    @FindBy(xpath = "//md-select[@placeholder='Datacenter location' and @md-container-class='cpc-region-select']")
    private WebElement datacenterLocationSelector;

    @FindBy(xpath = "//md-option[@value='europe-west3' and contains(@ng-repeat,'computeServer')]")
    private WebElement selectFrankfurt;

    @FindBy(xpath = "//md-select[@placeholder='Committed usage']")
    private WebElement committedUsageSelector;

    @FindBy(xpath = "//div[@class='md-select-menu-container md-active md-clickable']//md-option[@value='1']")
    private WebElement select1Year;

    @FindBy(xpath = "//form[@name='ComputeEngineForm']//button[contains(text(),'Add to Estimate')]")
    private WebElement addToEstimate;

    @FindBy(xpath = TOTAL_PRICE_XPATH)
    private WebElement totalPrice;

    @FindBy(id = "Email Estimate")
    private WebElement emailEstimate;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailAddressContainer;

    @FindBy(xpath = "//button[contains(text(),'Send Email')]")
    private WebElement sendEmailButton;

    public CloudCalculatorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        switchToActiveFrame();
    }

    public CloudCalculatorPage chooseSection() {
        clickThis(computeEngineSection);
        return this;
    }

    public CloudCalculatorPage numberOfInstancesEnter() {
        numberOfInstancesSelector.get(0).sendKeys("4");
        return this;
    }

    public CloudCalculatorPage selectOS() {
        clickThis(softwareSelector);
        clickThis(selectFree);
        return this;
    }

    public CloudCalculatorPage provisioningModelSelection() {
        clickThis(provisioningModelSelector);
        clickThis(selectRegular);
        return this;
    }

    public CloudCalculatorPage seriesSelection() {
        clickThis(seriesSelector);
        clickThis(selectN1);
        return this;
    }

    public CloudCalculatorPage machineType() {
        clickThis(machineTypeSelector);
        clickThis(selectStandard8);
        return this;
    }

    public CloudCalculatorPage addGpuCheckboxclick() {
        clickThis(addGpusCheckbox);
        return this;
    }

    public CloudCalculatorPage addGpuType() {
        clickThis(gpuTypeSelector);
        clickThis(selectNvidiaTeslaP100);
        return this;
    }

    public CloudCalculatorPage numberOfGpu() {
        clickThis(numberOfGpusSelector);
        clickThis(select1Gpu);
        return this;
    }

    public CloudCalculatorPage selectSSD() {
        clickThis(localSsdSelector);
        clickThis(select2x375Gb);
        return this;
    }

    public CloudCalculatorPage selectDataCenterLocation() {
        clickThis(datacenterLocationSelector);
        clickThis(selectFrankfurt);
        return this;
    }

    public CloudCalculatorPage commitedUsage() {
        clickThis(committedUsageSelector);
        clickThis(select1Year);
        return this;
    }

    public String calculate() {
        clickThis(addToEstimate);
        waitTillElementIsPresent(By.xpath(TOTAL_PRICE_XPATH));
        return totalPrice.getText();
    }

    public void sendCalculatedInfoToEmail(String emailAddress) {
        clickThis(emailEstimate);
        emailAddressContainer.sendKeys(emailAddress);
        clickThis(sendEmailButton);
    }


    public CloudCalculatorPage switchToCalculatorPage() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        driver.switchTo().defaultContent();
        switchToActiveFrame();
        return this;
    }

    private void switchToActiveFrame() {
        driver.switchTo().frame(outerFrame.getAttribute("name"));
        driver.switchTo().frame(INNER_FRAME_NAME);
    }
}
