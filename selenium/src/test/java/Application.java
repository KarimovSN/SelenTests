import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Application {

    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;
    private ItemPage itemPage;
    private BasketPage basketPage;


    public Application() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        itemPage = new ItemPage(driver);
        basketPage = new BasketPage(driver);
    }

    public void quit() {
        driver.quit();
    }


    public void addProductMainPage(int count) {
        mainPage.open();
        mainPage.elementClickableWaiter(mainPage.boxMostPopular);
        for (int i = 1; i <= count; i++) {
            mainPage.boxMostPopular.click();
            itemPage.elementClickableWaiter(mainPage.quantity);
            itemPage.elementClickableWaiter(mainPage.addCartButton);
            try {
                itemPage.duckSizeSelector.click();
            } catch (NoSuchElementException e) {
                itemPage.addCartButton.click();
                itemPage.cartCountChangeWaiter(i);
                itemPage.goToMainPage.click();
            }
        }
        mainPage.elementClickableWaiter(mainPage.cart);
        mainPage.cart.click();
    }

    public void footerChecker(int count) {
        for (int i = 1; i < count; i++) {

            String beforePrice = basketPage.price.getAttribute("innerText").substring(1);
            float beforePayment = Float.parseFloat(beforePrice);
            basketPage.productSelect.click();
            basketPage.removeButton.click();

            String afterPrice = basketPage.price.getAttribute("innerText").substring(1);
            float afterPayment = beforePayment - Float.parseFloat(afterPrice);
            float total = beforePayment - afterPayment;
            basketPage.totalCostChecker(total);
        }
        basketPage.removeButton.click();
        itemPage.elementClickableWaiter(basketPage.backButton);
    }

}
