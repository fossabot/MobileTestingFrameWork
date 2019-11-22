package Configuration;


import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
//import org.openqa.grid.common.exception.GridException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseSetup  {

    public static AndroidDriver<MobileElement> driver = null;
    protected AppiumDriverLocalService service;
    public WebDriverWait wait;
    private ThreadLocalDriver threadLocalDriver = new ThreadLocalDriver();


    @Parameters({"deviceName","platformVersion","udid","URL_","Sport","deviceId","sysPort","bootStrap","wdaPort","path"})
    @BeforeMethod(alwaysRun = true)
    public void setUp (String deviceName, String platformVersion, String udid, String URL_, String Sport, String deviceId, String sysPort, String bootStrap, String wdaPort, String path) throws Exception {


        System.out.println("Driver Is Initiated");
        DesiredCapabilities dc = new DesiredCapabilities();

        // Mobile setup
        dc.setCapability("deviceId", deviceId);
//        dc.setCapability("isHeadless",true);
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
//        dc.setCapability(MobileCapabilityType.UDID, udid);
        dc.setCapability("platformName", "Android");
        dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
//        dc.setCapability(AndroidMobileCapabilityType.AVD,deviceName);
        dc.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, sysPort); //systemPort for UiAtuomator2 from 8200 to 8299

//        dc.setCapability("appium:unlockType", "password");
//        dc.setCapability("appium:unlockKey", "@MR0122882435aba");
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiAutomator2"); // Make the test fail after first tear down "BROWSER_TIMEOUT"
        dc.setCapability("appium:uiautomator2ServerInstallTimeout", 8000);


        // Application setup
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.sarajevo.food.dictionary");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.sfdmobile.MainActivity");
//        dc.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, "com.sfdmobile.MainActivity");

        dc.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS,"true");
        dc.setCapability(MobileCapabilityType.CLEAR_SYSTEM_FILES, true);


        // Driver configuration
//        int port = Integer.parseInt(Sport); //need when use function .using
//        service = AppiumDriverLocalService
//                .buildService(new AppiumServiceBuilder()
//                        .usingDriverExecutable(new File("/usr/bin/node"))
////                        .usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
//                        .withAppiumJS(new File("/home/linuxbrew/.linuxbrew/lib/node_modules/appium/build/lib/main.js"))
//                        //.withAppiumJS(new File("C:\\Users\\amrka\\AppData\\Local\\Programs\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
//                        .withArgument(Arg.ADDRESS,URL_)
//                        .withArgument(Arg.PORT,Sport)
//                        .withArgument(Arg.CALLBACKPORT,Sport)
////                        .withArgument(Arg.WDALOCALPORT,wdaPort)
//                        .withArgument(Arg.BootstrapPort,bootStrap) //from 100 to 200
////                    .withArgument(Arg.NODECONFIG,path)  //uncomment this when use parallel test with grid
//                        .withArgument(Arg.SESSIONOVERRIDE));
//        service.start();

//        // uncomment the code below while using selenium grid
//        try {
//            waitForAppiumNodeToComeUp();
//        } catch (InterruptedException e) {
//            throw new GridException(e.getMessage(), e);
//        }

//                Thread.sleep(5000); // Needed for appium server to wait for selenium grid to register the node


//        driver = new AndroidDriver (new URL("http://localhost:4723/wd/hub"), dc); //uncomment this line when you use selenium grid
        driver = new AndroidDriver (new URL("http://"+URL_+":"+Sport+"/wd/hub"), dc); //uncomment this part when use parallel test without grid
//
//        threadLocalDriver.setTLDriver(new AndroidDriver<MobileElement>(new URL("http://"+URL_+":"+Sport+"/wd/hub"),dc)); //for thread safety uncomment this part when u use parallel test without grid
//        threadLocalDriver.setTLDriver(new AndroidDriver<MobileElement>(new URL("http://localhost:4444/wd/hub"),dc)); //for thread safety with selenium grid
//        driver = threadLocalDriver.getTLDriver(); // for thread safety
//        wait = new WebDriverWait(driver, 10); // for thread safety
    }


    //grid will wait for appium server, uncomment this part when use selenium grid
    private void waitForAppiumNodeToComeUp()
            throws InterruptedException {
        boolean flag = true;
        while (flag) {
            if (service.isRunning()) {
                flag = false;
            }
            TimeUnit.SECONDS.sleep(5);
        }
    }




// uncomment this when use selenium grid
//    @AfterMethod
//    public void tearDown() throws InterruptedException {
//        if (driver != null)
////            driver.quit();     //will lead to unexpected behaviour during teardown, some test will fail
//            service.stop();
//        System.out.println("service is stopped");
//
//    }

    @AfterSuite
    public void cleanUp() throws InterruptedException {
        if (driver != null)
            driver.quit();
//        service.stop();
//        driver.quit();
        System.out.println("Driver quit");

    }

//    @AfterClass   // used with thread safety.
//    public synchronized void teardown()  {
//        if (driver != null)
//            service.stop();
//    }
}


