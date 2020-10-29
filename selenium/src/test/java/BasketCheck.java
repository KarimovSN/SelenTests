import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class BasketCheck {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        new WebDriverWait(driver, 10).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    @Test
    public void basketAdd() throws InterruptedException {
        driver.get("http://localhost/litecart/en/");
        new WebDriverWait(driver, 10).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        for (int i = 1; i <= 3; i++) {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id = 'box-most-popular']//li[1]")));
            driver.findElement(By.xpath("//div[@id = 'box-most-popular']//li[1]")).click();
            wait.until(d -> d.findElement(By.xpath("//span[@class= 'quantity']")));
            wait.until(d -> d.findElement(By.xpath("//button[@name= 'add_cart_product']")));

            try {
                driver.findElement(By.xpath("//select[@name= 'options[Size]']/option[@value = 'Small']")).click();
            } catch (NoSuchElementException e) {
                driver.findElement(By.xpath("//button[@name= 'add_cart_product']")).click();
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class,'quantity') and contains(text(),'" + i + "')]")));
                driver.findElement(By.xpath("//img[@title = 'My Store']")).click();
            }
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.id("cart")));
        driver.findElement(By.id("cart")).click();
        List<WebElement> ducks = driver.findElements(By.xpath("//ul[@class= 'shortcuts']//li"));
        footerChecker(ducks);
    }

    public void footerChecker(List ducks) {
        for (int i = 1; i < ducks.size(); i++) {
            String beforePrice = driver.findElement(By.xpath("//tr[@class = 'footer']//td[2]/strong")).getAttribute("innerText").substring(1);
            float beforePayment = Float.parseFloat(beforePrice);

            driver.findElement(By.xpath("//ul[@class= 'shortcuts']//li")).click();
            driver.findElement(By.xpath("//p/button[@value= 'Remove'][1]")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@class = 'footer']//td[2]/strong")));
            String afterPrice = driver.findElement(By.xpath("//tr[@class = 'footer']//td[2]/strong")).getAttribute("innerText").substring(1);
            float afterPayment = beforePayment - Float.parseFloat(afterPrice);
            float total = beforePayment - afterPayment;
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@class = 'footer']//td[2]/strong[text() = '$" + total + "0']")));
        }
        driver.findElement(By.xpath("//p/button[@value= 'Remove'][1]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text() = '<< Back']")));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}






