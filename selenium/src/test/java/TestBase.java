import org.junit.After;
import org.junit.Before;

public class TestBase {

    public static ThreadLocal<Application> tlapp= new ThreadLocal<>();
    public Application app;

    @Before
    public void start() {
        if (tlapp.get() != null) {
            app = tlapp.get();
            return;
        }

       app = new Application();
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    app.quit();
                    app = null;
                }));
    }

    @After
    public void stop() {
//        driver.quit();
//        driver = null;
    }


}
