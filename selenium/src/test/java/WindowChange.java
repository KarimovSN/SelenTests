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

import static org.junit.Assert.assertEquals;


public class WindowChange {


    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    @Test
    public void changeWindow() {
        admLogin();
        clickWaiter("//span[text() = 'Countries']");
        clicker("//span[text() = 'Countries']");
        clickWaiter("//a[@class = 'button']/i");
        clicker("//a[@class = 'button']/i");

        List<WebElement> elementList = driver.findElements(By.xpath("//tr//a[contains(@href, '')]/i[@class = 'fa fa-external-link']"));
        for (int i = 1; i < elementList.size(); i++) {
            elementList = driver.findElements(By.xpath("//tr//a[contains(@href, '')]/i[@class = 'fa fa-external-link']"));
            elementList.get(i).click();
            switcher();
        }
    }

    public void switcher() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        fullWait();
        driver.close();
        driver.switchTo().window(tabs.get(0));
    }

    public void clickWaiter(String path) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(path)));
    }

    public void clicker(String elem) {
        driver.findElement(By.xpath(elem)).click();
    }

    public void fullWait() {
        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public void admLogin() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}







