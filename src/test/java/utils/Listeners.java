package utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseTests implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String path = Base.getScreenshot(result.getName(),driver);
        try {
            test.fail(result.getThrowable());
            test.fail("screenshots").addScreenCaptureFromPath(path);
            //test.fail("details",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }
}
