import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends Page {

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public MainPage open() {
        driver.get("https://localhost/litecart/en/");
        return this;
    }


    @FindBy(xpath = "//div[@id = 'box-most-popular']//li[1]")
    public WebElement boxMostPopular;

    @FindBy(xpath = "//span[@class= 'quantity']")
    public WebElement quantity;

    @FindBy(xpath = "//button[@name= 'add_cart_product']")
    public WebElement addCartButton;

    @FindBy(id = "cart")
    public WebElement cart;

    public void elementClickableWaiter(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
