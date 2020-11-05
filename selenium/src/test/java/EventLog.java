import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;


public class EventLog {


    private EventFiringWebDriver driver;
    private WebDriverWait wait;

    public static class MyListener extends AbstractWebDriverEventListener {
        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by + " found");
        }

        @Override
        public void beforeClickOn(WebElement element, WebDriver driver) {
            System.out.println(element);
        }

        @Override
        public void afterClickOn(WebElement element, WebDriver driver) {
            driver.manage().logs().get("browser").getAll();
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            System.out.println(throwable);
        }

    }

    @Before
    public void start() {
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.register(new MyListener());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

    }

    @Test
    public void browserLog() {

        List<WebElement> innerDocs = new ArrayList<>();
        List<WebElement> catalogList = new ArrayList<>();
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        driver.findElement(By.xpath("//span[text() = 'Catalog']")).click();
        driver.findElement(By.xpath("//a[text() = 'Rubber Ducks']")).click();
        clicker();
    }

    public void clicker() {
        List<WebElement> list = driver.findElements(By.xpath("//tr[@class = 'row']//td[3]/a"));
        for (int i = 1; i < list.size(); i++) {
            list = driver.findElements(By.xpath("//tr[@class = 'row']//td[3]/a"));
            list.get(i).click();
            logCheck();
            driver.findElement(By.xpath("//span[text() = 'Catalog']")).click();
            driver.findElement(By.xpath("//a[text() = 'Rubber Ducks']")).click();
        }
    }

    public void logCheck() {
        String log = String.valueOf(driver.manage().logs().get("browser").getAll());
        assertEquals(log, "[]");
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}







