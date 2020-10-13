import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MultiLogin {

    private WebDriver chromedriver = new ChromeDriver();
    private WebDriver iedriver = new InternetExplorerDriver();
    private WebDriver ffdriver = new FirefoxDriver();
    private WebDriverWait wait;


    @Before
    public void start() {
        wait = new WebDriverWait(chromedriver, 10);
        wait = new WebDriverWait(iedriver, 10);
        wait = new WebDriverWait(ffdriver, 10);
    }

    @Test
    public void login() {
        chromedriver.get("http://localhost/litecart/admin/");
        chromedriver.findElement(By.name("username")).sendKeys("admin");
        chromedriver.findElement(By.name("password")).sendKeys("admin");
        chromedriver.findElement(By.name("login")).click();
        chromedriver.quit();

        iedriver.get("http://localhost/litecart/admin/");
        iedriver.findElement(By.name("username")).sendKeys("admin");
        iedriver.findElement(By.name("password")).sendKeys("admin");
        iedriver.findElement(By.name("login")).click();
        iedriver.quit();

        ffdriver.get("http://localhost/litecart/admin/");
        ffdriver.findElement(By.name("username")).sendKeys("admin");
        ffdriver.findElement(By.name("password")).sendKeys("admin");
        ffdriver.findElement(By.name("login")).click();
        ffdriver.quit();
    }


    @After
    public void stop() {
        chromedriver.quit();
        iedriver.quit();
        ffdriver.quit();
        chromedriver = null;
        iedriver = null;
        ffdriver = null;
    }
}





