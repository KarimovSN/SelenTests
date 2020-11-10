import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ItemPage extends Page {

    public ItemPage (WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//span[@class= 'quantity']")
    public WebElement quantity;

    @FindBy(xpath="//button[@name= 'add_cart_product']")
    public WebElement addCartButton;

    @FindBy(xpath="//select[@name= 'options[Size]']/option[@value = 'Small']")
    public WebElement duckSizeSelector;

    @FindBy(xpath="//img[@title = 'My Store']")
    public WebElement goToMainPage;

    public void elementClickableWaiter(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void cartCountChangeWaiter(int count){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class,'quantity') and contains(text(),'" + count + "')]")));
    }


}
