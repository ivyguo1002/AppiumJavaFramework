package utils;

import com.google.common.io.Files;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class Base {

    public static Properties loadProperties(){
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(new File("resources/test.properties")));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return props;
    }

    public static AppiumDriverLocalService startAppiumServer() {
        //Stop appium server if it's runnning
        try {
            Runtime.getRuntime().exec("taskkill /im /f node.exe");
            Thread.sleep(3000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(InterruptedException e){
            e.printStackTrace();
        }

        //start appium service
        AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
        service.start();

        if (service == null || !service.isRunning()) {
            throw new AppiumServerHasNotBeenStartedLocallyException(
                    "An appium server node is not started!");
        }

        return service;
    }

    public static void startEmulator() throws IOException, InterruptedException
    {
        Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\resources\\startEmulator.bat");
        Thread.sleep(6000);
    }

    public static AndroidDriver<AndroidElement> capabilities(String device) throws MalformedURLException {
        AndroidDriver<AndroidElement> driver;
        DesiredCapabilities capabilities = new DesiredCapabilities();

//        if (device.contains("emulator")) {
//            try {
//                startEmulator();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        File appDir = new File("resources");
        File app = new File(appDir, "ApiDemos-debug.apk");
        //capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "demo2");

        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        //capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "20");
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        return driver;
    }

    public static String getScreenshot(String testName, WebDriver driver){
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = System.getProperty("user.dir") + "\\reports\\screenshots\\" + testName + ".png";

        try{
            Files.move(screenshot, new File(screenshotPath));
        }catch(IOException e){
            e.printStackTrace();
        }

        return screenshotPath;
    }



}
