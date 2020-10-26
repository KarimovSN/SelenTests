import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class AddProduct {


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
    public void itemAdd() throws InterruptedException {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        Actions builder = new Actions(driver);

        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text() = 'Catalog']")));
        driver.findElement(By.xpath("//span[text() = 'Catalog']")).click();

        //General
        driver.findElement(By.xpath("//a[text() =' Add New Product']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@name = 'name[en]']")).sendKeys("Magic duck");
        driver.findElement(By.xpath("//input[@name = 'code']")).sendKeys("1234");
        driver.findElement(By.xpath("//td[text() = 'Unisex']/..//input[@type = 'checkbox']")).click();
        driver.findElement(By.xpath("//input[@type= 'radio'][1]")).click();
        driver.findElement(By.xpath("//input[@name = 'quantity']")).sendKeys("200");

        By fileInput = By.cssSelector("input[type=file]");
        driver.findElement(fileInput).sendKeys(new File("build/resources/test/duck.png").getAbsolutePath());


        //Information
        driver.findElement(By.xpath("//a[text() = 'Information']")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//select[@name = 'manufacturer_id']//option[text() = 'ACME Corp.']")).click();
        driver.findElement(By.xpath("//input[@name = 'short_description[en]']")).sendKeys("Elite duck");
        driver.findElement(By.xpath("//input[@name = 'head_title[en]']")).sendKeys("Elite duck");

        //Prices
        driver.findElement(By.xpath("//a[text() = 'Prices']")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//input[@name = 'purchase_price']")).sendKeys("25");
        driver.findElement(By.xpath("//select[@name = 'purchase_price_currency_code']//option[@value = 'USD']")).click();
        driver.findElement(By.xpath("//input[@name = 'prices[USD]']")).sendKeys("20");
        driver.findElement(By.xpath("//input[@name = 'prices[EUR]']")).sendKeys("16");
        driver.findElement(By.xpath("//button[@name = 'save']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text() = 'Catalog']")));
        driver.findElement(By.xpath("//span[text() = 'Catalog']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[text()= 'Magic duck']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name = 'delete']")));
        driver.findElement(By.xpath("//button[@name = 'delete']")).click();
        builder.sendKeys(Keys.RETURN).perform();
    }
        @After
        public void stop () {
            driver.quit();
            driver = null;
        }
    }






