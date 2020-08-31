package core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class TestBase {

   //public AndroidDriver<MobileElement> driver;
   AppiumDriver driver;


    //@BeforeSuite
    public void appiumAppTestSetup() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","e90c94cc");
        capabilities.setCapability("platformName","android");
        capabilities.setCapability("platformVersion","10");
        capabilities.setCapability("appPackage","com.rentalcars.handset");
        capabilities.setCapability("appActivity","com.rentalcars.handset.home.HomeActivity");
        driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Parameters({"deviceName","Version","URL"})
    @BeforeTest
    public void appiumTestSetupParallel(String deviceName,String Version,String URL) throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName",deviceName);
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("platformVersion",Version);
        capabilities.setCapability("appPackage","com.rentalcars.handset");
        capabilities.setCapability("appActivity","com.rentalcars.handset.home.HomeActivity");
        driver = new AppiumDriver(new URL(URL),capabilities);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }



    //@BeforeSuite
    public void appiumBrowserTestSetup() throws Exception{
        //chrome://inspect/devices#devices
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\WizdomQA\\Desktop\\drivers\\chromedriver_win32\\chromedriver.exe");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","emulator-5554");
        capabilities.setCapability("platformName","android");
        capabilities.setCapability("platformVersion","10");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get("https://www.amazon.co.uk/");
    }

//    @AfterMethod
//    public void reLaunchApp(){
//        driver.launchApp();
//    }
//
//    @AfterSuite
//    public void closeTest(){
//        driver.quit();
//    }

    public void clickElementFromListByText(String xpath, String text){
        List<MobileElement> results = driver.findElementsByXPath(xpath);
        for(MobileElement result : results){
            if(result.getText().equals(text.trim())){
                result.click();break;
            }
        }
    }

    public void sendText(String xpath, String text){
        driver.findElementByXPath(xpath).sendKeys(text);
    }

    public void clickElementByXpath(String xpath){
        driver.findElementByXPath(xpath).click();
    }

    public void clickElementById(String Id){
        driver.findElementById(Id).click();
    }

    public void waitForElement(String xpath,long timeout){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath(xpath)));
    }

    public String getInnerTextFromInputField(String xpath){
        return driver.findElementByXPath(xpath).getAttribute("text");
    }

    public String getTextFromElement(String xpath){
        return driver.findElementByXPath(xpath).getText();
    }

}
