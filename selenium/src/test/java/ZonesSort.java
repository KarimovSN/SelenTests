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
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;


public class ZonesSort {


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
    public void sortZones() {
        admLogin();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text() = 'Geo Zones']")));
        driver.findElement(By.xpath("//span[text() = 'Geo Zones']")).click();
        List<WebElement> zones = driver.findElements(By.xpath("//form[@name ='geo_zones_form']//tr[@class = 'row']"));
        zoneFiller(zones);
    }


    public void zoneFiller(List list) {
        List<String> zones = new ArrayList<>();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//form[@name ='geo_zones_form']//tr[2][@class = 'row']//a[@href]")));
        for (int i = 2; i <= list.size() + 1; i++) {
            String zoneNumber = String.valueOf(driver.findElement(By.xpath("//tr[@class = 'row'][" + (i - 1) + "]//td[4]")).getText());
            new WebDriverWait(driver, 30).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            driver.findElement(By.xpath("//form[@name ='geo_zones_form']//tr[" + i + "][@class = 'row']//a[@href]")).click();
            List<WebElement> zoneCounter = driver.findElements(By.xpath("//td/select[@name = 'zones[" + zoneNumber + "][zone_code]']/option"));
            for (int x = 2; x <= zoneCounter.size(); x++) {
                String zone = String.valueOf(driver.findElement(By.xpath("//td/select[@name = 'zones[" + zoneNumber + "][zone_code]']/option[" + x + "]")).getText());
                zones.add(zone);
            }
            sortAssertion(zones);
            zones.clear();
        }
    }


    public void sortAssertion(List naturalZones) {
        List<String> sortedZones = (List<String>) naturalZones.stream().collect(Collectors.toList());
        Collections.sort(sortedZones);
        assertEquals(naturalZones, sortedZones);
        driver.findElement(By.xpath("//span[text() = 'Geo Zones']")).click();
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







