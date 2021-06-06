package appHooks;

import com.setup.factory.DriverFactory;
import com.setup.utils.ConfigReader;
import io.cucumber.java.Scenario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class AppHooks {
    private DriverFactory driverFactory;
    private ConfigReader configReader;
    private WebDriver driver;
    Properties prop;

    @BeforeEach
    public void getProperty(){
        configReader = new ConfigReader();
        prop = configReader.init_prop();
        System.out.println("Reading from configuration....");
        
    }
    @BeforeEach
    public void launchBrowser(){
        String browserName = prop.getProperty("browser");
        driverFactory = new DriverFactory();
        driverFactory.init_driver(browserName);
        System.out.println("driver object is initialised...");

    }
    @AfterEach
    public void quitBrowser(){
       driver.quit();

    }
    @AfterEach
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            // take screenshot:
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(sourcePath, "image/png", screenshotName);
            System.out.println("Test Failed. Taking ScreenShot......");
        }


    }

}
