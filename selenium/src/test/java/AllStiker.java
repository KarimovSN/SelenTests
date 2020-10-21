import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;


public class AllStiker {


    private WebDriver driver;
    private WebDriverWait wait;
    private WebDriverWait fullWait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    @Test
    public void allStiker() {

        driver.get("http://localhost/litecart/en/");

        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        sticKCheck("box-most-popular");
        sticKCheck("box-campaigns");
        sticKCheck("box-latest-products");
    }

    public void sticKCheck(String boxName) {
        List<WebElement> prod = driver.findElements(By.xpath("//div[@id = '" + boxName + "']//li"));
        for (int i = 1; i <= prod.size(); i++) {
            List<WebElement> stikCount = driver.findElements(By.xpath("//div[@id = '" + boxName + "']//li[" + i + "][contains(@class, 'product')]//div[contains(@class, 'sticker')]"));
            assertTrue(stikCount.size() == 1);
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}







