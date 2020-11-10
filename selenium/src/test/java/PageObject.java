import org.junit.Test;

public class PageObject extends TestBase{



    @Test
    public void basketAddPageObj()  { ;
        app.addProductMainPage(3);
        app.footerChecker(3);
    }

}
