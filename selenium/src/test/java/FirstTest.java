import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstTest {

    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start(){
        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFirstTest(){
        driver.get("http://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("speedtest");
        driver.findElement(By.name("btnI")).click();
    }


    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

}
