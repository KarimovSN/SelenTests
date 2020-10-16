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


public class AllAdmin {


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
    public void allAdmin() {

        List<WebElement> innerDocs = new ArrayList<>();
        List<WebElement> catalogList = new ArrayList<>();
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        catalogList = driver.findElements(By.xpath("//ul[@id = 'box-apps-menu']/li"));

        for (int i = 1; i <= catalogList.size(); i++) {
            driver.findElement(By.xpath("//ul[@id = 'box-apps-menu']/li[" + i + "]")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1/span[@class='fa-stack icon-wrapper']")));
            innerDocs = driver.findElements(By.xpath("//ul[@class='docs']/li"));
            innCheck(innerDocs);
        }
    }

    public void innCheck(List innerDocs) {
        for (int i = 1; i <= innerDocs.size(); i++) {
            driver.findElement(By.xpath("//ul[@class='docs']/li[" + i + "]")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1/span[@class='fa-stack icon-wrapper']")));
        }
    }

        @After
        public void stop () {
            driver.quit();
            driver = null;
        }
    }







