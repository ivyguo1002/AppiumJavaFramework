package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import static utils.Base.*;
import static utils.ExtentReporter.getReports;

public class BaseTests {
    public static Properties props;
    protected static AndroidDriver<AndroidElement> driver;
    private static AppiumDriverLocalService service;
    public static ExtentReports extent;
    public static ExtentTest test;

    @BeforeSuite
    public void beforeSuite(){

        //service = startAppiumServer();

        //start reports
        extent = getReports();

    }

    @BeforeMethod
    public void beforeMethod(){
        props = loadProperties();
        String device = props.getProperty("device");

        try {
            driver = capabilities(device);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void afterMethod(){
        if (driver != null) {
            driver.quit();
        }

    }

    @AfterSuite
    public void afterSuite(){
//        if (service != null) {
//            service.stop();
//        }

        //flush reports
        extent.flush();
    }

}
