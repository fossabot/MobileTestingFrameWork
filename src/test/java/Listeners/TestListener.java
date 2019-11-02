package Listeners;

import Configuration.BaseSetup;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener extends BaseSetup implements ITestListener {


    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    //Screenshot attachments for Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(AndroidDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    //Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog (String message) {
        return message;
    }

    //HTML attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

//    //Screenshot attachment with renaming property
//    @Attachment(value = "Page screenshot", type = "image/png")
//    private static File screenshot (AndroidDriver driver) throws IOException  {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
//        Date date = new Date();
//        String fileName = sdf.format(date);
//        File SrcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(SrcFile, new File(System.getProperty("user.dir")+"//allure-results//"+fileName+".png"));
//        System.out.println("Screenshot is captured");
//        return SrcFile;
//
//    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("I am in onStart method " + iTestContext.getName());
        iTestContext.setAttribute("AndroidDriver", this.driver);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("I am in onFinish method " + iTestContext.getName());

    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("I am in onTestStart method " +  getTestMethodName(iTestResult) + " start");

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("I am in onTestSuccess method " +  getTestMethodName(iTestResult) + " succeed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("I am in onTestFailure method " +  getTestMethodName(iTestResult) + " failed");

        //Allure ScreenShotRobot and SaveTestLog
        if (driver != null) {
            saveScreenshotPNG(driver);
            System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
        }

        //Save a log on allure.
        saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("I am in onTestSkipped method "+  getTestMethodName(iTestResult) + " skipped");

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }



//    private String testResult;
//    private Logger log = LogManager.getLogger(getClass().getName());
//
//
//    @Attachment(value = "{0}", type = "text/plain")
//    private static String saveTextLog(String message) {
//        return message;
//    }
//

//

//
//    private String getTestResult() {
//        return testResult;
//    }
//
//    private void setTestResult(String testResult) {
//        this.testResult = testResult;
//    }
//
//
//
//
//
//    @Override
//    public void onTestStart(ITestResult iTestResult) {
//        ITestResult.startTest(iTestResult.getMethod().getMethodName(), "");
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult iTestResult) {}
//
//    @Override
//    public void onTestFailure(ITestResult iTestResult)  {
//        if (driver != null) {
//            System.out.println("Screenshot captured for test case");
//                saveScreenshotPNG(driver);
//            System.out.println(iTestResult.getMethod().getMethodName() + " failed!");
//            saveTextLog("failed and screenshot taken!");
//        }
//
//    }
//
//
//
//    @Override
//    public void onTestSkipped(ITestResult iTestResult) {
//        setTestResult("SKIPPED");
//
//    }
//
//    @Override
//    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
//        iTestResult.getMethod().getConstructorOrMethod().getName();
//
//    }
//
//    @Override
//    public void onStart(ITestContext iTestContext) {}
//
//    @Override
//    public void onFinish(ITestContext iTestContext) {}

}




