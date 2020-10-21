import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class CountriesSort {


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
    public void sortCountries() {
        admLogin();
        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text() = 'Countries']")));
        WebElement countriesMenu = driver.findElement(By.xpath("//span[text() = 'Countries']"));
        countriesMenu.click();
        List<WebElement> countries = driver.findElements(By.xpath("//form[@name ='countries_form']//tr[@class = 'row']"));
        countryFiller(countries);
    }


    public void countryFiller(List list) {
        List<String> coutriesList = new ArrayList<>();
        for (int i = 2; i < list.size(); i++) {
            String countryName = driver.findElement(By.xpath("//form[@name ='countries_form']//tr[" + i + "][@class = 'row']//a[@href]")).getText();
            int val = Integer.parseInt(driver.findElement(By.xpath("//form[@name ='countries_form']//tr[" + i + "][@class = 'row']//a[@href]/../following-sibling::td[1]")).getText());
            coutriesList.add(countryName);
            if (val != 0) {
                driver.findElement(By.xpath("//form[@name ='countries_form']//tr[" + i + "][@class = 'row']//a[@href]")).click();
                List<WebElement> zones = driver.findElements(By.xpath("//table[@id = 'table-zones']//tr"));
                filler(zones);
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text() = 'Countries']")));
                driver.findElement(By.xpath("//span[text() = 'Countries']")).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1/span[@class='fa-stack icon-wrapper']")));
            }
        }
        sortAssertion(coutriesList);
    }

    public void filler(List list) {
        List<String> innerZones = new ArrayList<>();
        for (int i = 2; i < list.size(); i++) {
            String countryInZoneName = driver.findElement(By.xpath("//table[@id = 'table-zones']//tr[" + i + "]//td//input[contains(@name, 'name')]")).getAttribute("value");
            innerZones.add(countryInZoneName);
        }
        sortAssertion(innerZones);
    }

    public void sortAssertion(List naturalZones) {
        List<String> sortedZones = (List<String>) naturalZones.stream().collect(Collectors.toList());
        Collections.sort(sortedZones);
        assertEquals(naturalZones, sortedZones);
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







