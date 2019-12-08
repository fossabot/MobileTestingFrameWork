package Configuration;


import MdfService.DeviceApi;
import MdfService.MDFService;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
//import org.openqa.grid.common.exception.GridException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseSetup  {

    public static AndroidDriver<MobileElement> driver = null;
    protected AppiumDriverLocalService service;
    public WebDriverWait wait;
    private ThreadLocalDriver threadLocalDriver = new ThreadLocalDriver();

    private DeviceApi deviceApi;


    private static final String MDF_SERVICE_URL = "http://localhost:7100";  // Change this URL
    private static final String ACCESS_TOKEN = "dee35b82018f4dd4852d8ecdbe34377fbb0d37d5beab480781b2dfe524b12523";  // Change this access token
    protected static String deviceSerial = "RF8M82EDL8Y";

//    @Factory(dataProvider = "mdfIntegration")
//    public BaseSetup(String deviceSerial) {
//        this.deviceSerial = deviceSerial;
//    }

//    @Factory(dataProvider = "mdfIntegration")
//    public BaseSetup() {
//        this.deviceSerial = deviceSerial;
//    }
//
//
//    @DataProvider(name = "mdfIntegration")
//    public Object[][] mdfIntegration() {
//        return new Object[][] {
//                {"RF8M82EDL8Y"}    // Change the device serial
//        };
//    }

    private void connectToStfDevice() throws MalformedURLException, URISyntaxException {
        MDFService mdfService = new MDFService(MDF_SERVICE_URL,ACCESS_TOKEN);
        this.deviceApi = new DeviceApi(mdfService);
        this.deviceApi.connectDevice(this.deviceSerial);
    }
    @Parameters({"URL_","Sport","bootStrap"})
    private void createAppiumService(String URL_, String Sport,String bootStrap) {
//        int port = Integer.parseInt(Sport); //need when use function .using
        service = AppiumDriverLocalService
                .buildService(new AppiumServiceBuilder()
                        .usingDriverExecutable(new File("/usr/bin/node"))
//                        .usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
                        .withAppiumJS(new File("/home/linuxbrew/.linuxbrew/lib/node_modules/appium/build/lib/main.js"))
                        //.withAppiumJS(new File("C:\\Users\\amrka\\AppData\\Local\\Programs\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
                        .withArgument(Arg.ADDRESS,URL_)
                        .withArgument(Arg.PORT,Sport)
                        .withArgument(Arg.CALLBACKPORT,Sport)
//                      .withArgument(Arg.WDALOCALPORT,wdaPort)
                        .withArgument(Arg.BootstrapPort,bootStrap) //from 100 to 200
//                      .withArgument(Arg.NODECONFIG,path)  //uncomment this when use parallel test with grid
                        .withArgument(Arg.SESSIONOVERRIDE));
        service.start();

    }




    @Parameters({"deviceName","platformVersion","udid","URL_","Sport","deviceId","sysPort","bootStrap","wdaPort","path"})
    @BeforeMethod(alwaysRun = true)
    public void setUp (String deviceName, String platformVersion, String udid, String URL_, String Sport, String deviceId, String sysPort, String bootStrap, String wdaPort, String path) throws Exception {


        System.out.println("Driver Is Initiated");
        DesiredCapabilities dc = new DesiredCapabilities();

        // Mobile setup
//        dc.setCapability("deviceId", deviceId);
//        dc.setCapability("isHeadless",true);
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        dc.setCapability(MobileCapabilityType.UDID, this.deviceSerial);
        dc.setCapability("platformName", "Android");
        dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
//        dc.setCapability(AndroidMobileCapabilityType.AVD,deviceName);
//        dc.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, sysPort); //systemPort for UiAtuomator2 from 8200 to 8299

        dc.setCapability("appium:unlockType", "password");
        dc.setCapability("appium:unlockKey", "@MR0122882435aba");
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiAutomator2"); // Make the test fail after first tear down "BROWSER_TIMEOUT".
        dc.setCapability("appium:uiautomator2ServerInstallTimeout", 8000);


        // Application setup
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.sarajevo.food.dictionary");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.sfdmobile.MainActivity");
//        dc.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, "com.sfdmobile.MainActivity");

        dc.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS,"true");
        dc.setCapability(MobileCapabilityType.CLEAR_SYSTEM_FILES, true);


        // Driver configuration

        connectToStfDevice();
        createAppiumService(URL_,Sport,bootStrap);
//        driver = new AndroidDriver(this.service.getUrl(), dc); //uncomment this part when use parallel test without grid

//        // uncomment the code below while using selenium grid
//        try {
//            waitForAppiumNodeToComeUp();
//        } catch (InterruptedException e) {
//            throw new GridException(e.getMessage(), e);
//        }

//                Thread.sleep(5000); // Needed for appium server to wait for selenium grid to register the node
//        driver = new AndroidDriver (new URL("http://localhost:4724/wd/hub"), dc); //uncomment this line when you use selenium grid
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
        if (driver != null){
            driver.quit();
            System.out.println("Driver quit");
            service.stop();
            this.deviceApi.releaseDevice(this.deviceSerial);
            System.out.println("Device Has been released");
    }
//        if (this.service.isRunning()) {
//            service.stop();
//            this.deviceApi.releaseDevice(this.deviceSerial);
//            System.out.println("Device Has been released");
//        }
    }

//    @AfterClass   // used with thread safety
//    public synchronized void teardown()  {
//        if (driver != null)
//            service.stop();
//    }



}


