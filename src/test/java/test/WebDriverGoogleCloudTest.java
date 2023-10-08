package test;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.CloudCalculatorPage;
import pages.CloudHomePage;
import pages.YopmailHomePage;


public class WebDriverGoogleCloudTest {
    private WebDriver driver;
    SoftAssertions softAssertions;

    @BeforeEach
    public void browserSetUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*",
                "--disable-blink-features=AutomationControlled", "--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        softAssertions = new SoftAssertions();
    }

    @Test
    public void isTotalPriceMatchingIsCalculated() {
        String googleCloudCalculatorWindow;
        String YopmailPageWindow;
        String totalMonthlyPriceFromEmail;
        CloudCalculatorPage cloudCalculatorPage = new CloudHomePage(driver)
                .openPage()
                .searchForTerms()
                .clickMatchingResult()
                .chooseSection()
                .numberOfInstancesEnter()
                .selectOS()
                .provisioningModelSelection()
                .seriesSelection()
                .machineType()
                .addGpuCheckboxclick()
                .addGpuType()
                .numberOfGpu()
                .selectSSD()
                .selectDataCenterLocation()
                .commitedUsage();

        String priceMessageFromCalculatorPage = cloudCalculatorPage.calculate();
        YopmailHomePage yopmailHomePage = new YopmailHomePage(driver)
                .openYopmailInNewTab();
        String emailAddress = yopmailHomePage.generateRandomEmailAddress();


        cloudCalculatorPage.switchToCalculatorPage()
                .sendCalculatedInfoToEmail(emailAddress);
        yopmailHomePage.switchToYopmailPage();
        String priceMessageFromEmail = yopmailHomePage.receiveEstimatedInfoFromGeneratedEmail();

        softAssertions.assertThat(priceMessageFromCalculatorPage.contains("USD"));
        softAssertions.assertThat(priceMessageFromCalculatorPage).isEqualTo(priceMessageFromEmail);


    }

    @AfterEach
    public void browserQuit() {
        driver.quit();
    }
}
