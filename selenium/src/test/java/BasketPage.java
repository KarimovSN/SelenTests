import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasketPage extends Page {

    public BasketPage (WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public BasketPage open() {
        driver.get("https://localhost/litecart/en/checkout");
        return this;
    }


    @FindBy(xpath="//tr[@class = 'footer']//td[2]/strong")
    public WebElement price;

    @FindBy(xpath="//ul[@class= 'shortcuts']//li")
    public WebElement productSelect;

    @FindBy(xpath="//p/button[@value= 'Remove'][1]")
    public WebElement removeButton;

    @FindBy(xpath="//a[text() = '<< Back']")
    public WebElement backButton;


    public void  totalCostChecker(Float total){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@class = 'footer']//td[2]/strong[text() = '$" + total + "0']")));
    }

}
