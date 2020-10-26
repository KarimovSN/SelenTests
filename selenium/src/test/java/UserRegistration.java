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

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;


public class UserRegistration {


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
    public void userReg() {

        driver.get("http://localhost/litecart/en/");
        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        String email = randomGenerator();

        driver.findElement(By.xpath("//a[text() ='Create Account']")).click();
        driver.findElement(By.xpath("//select[@name = 'country_code']/option[text() = 'United States']")).click();

        driver.findElement(By.xpath("//input[@name = 'firstname']")).sendKeys("SomeName");
        driver.findElement(By.xpath("//input[@name = 'lastname']")).sendKeys("SomeLastName");
        driver.findElement(By.xpath("//input[@name = 'address1']")).sendKeys("SomeAdress");
        driver.findElement(By.xpath("//input[@name = 'postcode']")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@name = 'city']")).sendKeys("Alabama");
        driver.findElement(By.xpath("//input[@name = 'email']")).sendKeys(email + "@gmail.com");
        driver.findElement(By.xpath("//input[@name = 'phone']")).sendKeys("+1" + randomGenerator());
        driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys("qazwsx");
        driver.findElement(By.xpath("//input[@name = 'confirmed_password']")).sendKeys("qazwsx");
        driver.findElement(By.xpath("//button[@name = 'create_account']")).click();
        driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys("qazwsx");
        driver.findElement(By.xpath("//input[@name = 'confirmed_password']")).sendKeys("qazwsx");
        driver.findElement(By.xpath("//button[@name = 'create_account']")).click();
        driver.findElement(By.xpath("//a[text() ='Logout'][1]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()= 'Login']")));
        driver.findElement(By.xpath("//a[text()= 'Login']")).click();
        driver.findElement(By.xpath("//input[@name = 'email']")).sendKeys(email + "@gmail.com");
        driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys("qazwsx");
        driver.findElement(By.xpath("//button[@name = 'login']")).click();
        driver.findElement(By.xpath("//a[text() ='Logout'][1]")).click();
    }

    public String randomGenerator() {
        Long curentTime = System.nanoTime();
        String email = String.valueOf(curentTime);
        return email;
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}







