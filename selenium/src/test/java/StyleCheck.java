import org.checkerframework.checker.units.qual.C;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class StyleCheck {


    private WebDriver driver;
    private WebDriverWait wait;
    private WebDriverWait fullWait;


    @Before
    public void start() {
    }

    @Test
    public void styleCheck() {
        int browsers = 3;
        int count = 1;

        for (int i = 0; i < browsers; i++) {
            if (count == 1) {
                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                wait = new WebDriverWait(driver, 10);
            } else if (count == 2) {
                driver = new InternetExplorerDriver();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                wait = new WebDriverWait(driver, 10);
            } else {
                driver = new FirefoxDriver();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                wait = new WebDriverWait(driver, 10);
            }
            driver.get("http://localhost/litecart/en/");


            // Main page
            WebElement nameMainPage = driver.findElement(By.xpath("//div[@id= 'box-campaigns']//div[@class= 'name']"));
            WebElement regularPriceMainPage = driver.findElement(By.xpath("//div[@id= 'box-campaigns']//s[@class = 'regular-price']"));
            WebElement campaignPriceMainPage = driver.findElement(By.xpath("//div[@id= 'box-campaigns']//strong[@class = 'campaign-price']"));

            List<String> mainPageProducts = new ArrayList<>();
            String mainName = nameMainPage.getText();
            String regPriceMainPageText = regularPriceMainPage.getText();
            String campPriceMainPageText = campaignPriceMainPage.getText();
            String campPriceColorMainPage = campaignPriceMainPage.getCssValue("color");
            mainPageProducts.add(mainName);
            mainPageProducts.add(regPriceMainPageText);
            mainPageProducts.add(campPriceMainPageText);
            mainPageProducts.add(campPriceColorMainPage);


            String regPriceStyleMainPage = regularPriceMainPage.getCssValue("text-decoration");
            String regPriceColorMainPage = regularPriceMainPage.getCssValue("color");
            String campPriceFontSizeMainPage = campaignPriceMainPage.getCssValue("font-size");
            int campPriceFontWeightMainPage = Integer.parseInt(campaignPriceMainPage.getCssValue("font-weight"));

            assertTrue(regPriceStyleMainPage.contains("line-through"));
            assertTrue(regPriceColorMainPage.contains("119, 119, 119"));
            assertTrue(campPriceColorMainPage.contains("204, 0, 0"));
            assertEquals(campPriceFontSizeMainPage, "18px");
            assertTrue(campPriceFontWeightMainPage >= 700);


            // itemPage
            driver.findElement(By.xpath("//div[@id = 'box-campaigns']//a[1]")).click();


            WebElement nameItemPage = driver.findElement(By.xpath("//h1[@itemprop = 'name']"));
            WebElement regularPriceItemPage = driver.findElement(By.xpath("//s[@class = 'regular-price']"));
            WebElement campaignPriceItemPage = driver.findElement(By.xpath("//strong[@class = 'campaign-price']"));

            List<String> itemPageProducts = new ArrayList<>();
            String itemName = nameItemPage.getText();
            String regPriceItemPage = regularPriceItemPage.getText();
            String campPriceItemPage = campaignPriceItemPage.getText();
            String campPriceColorItemPage = campaignPriceItemPage.getCssValue("color");
            itemPageProducts.add(itemName);
            itemPageProducts.add(regPriceItemPage);
            itemPageProducts.add(campPriceItemPage);
            itemPageProducts.add(campPriceColorItemPage);


            String regPriceStyleItemPage = regularPriceItemPage.getCssValue("text-decoration");
            String regPriceColorItemPage = regularPriceItemPage.getCssValue("color");
            String campPriceFontSizeItemPage = campaignPriceItemPage.getCssValue("font-size");
            int campPriceWeightItemPage = Integer.parseInt(campaignPriceItemPage.getCssValue("font-weight"));

            assertTrue(regPriceStyleItemPage.contains("line-through"));
            assertTrue(regPriceColorItemPage.contains("102, 102, 102"));
            assertTrue(campPriceColorItemPage.contains("204, 0, 0"));
            assertEquals(campPriceFontSizeItemPage, "22px");
            assertTrue(campPriceWeightItemPage >= 700);

            textAssertion(mainPageProducts, itemPageProducts);

            count = count + 1;
        }
    }

    public void textAssertion(List mainPage, List itemPage) {
        Collections.sort(mainPage);
        Collections.sort(itemPage);
        assertEquals(mainPage, itemPage);
        driver.quit();
        driver = null;
    }

    @After
    public void stop() {
    }
}







