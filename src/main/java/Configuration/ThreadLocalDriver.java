package Configuration;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class ThreadLocalDriver  {


    private static ThreadLocal<AndroidDriver<MobileElement>> tlDriver = new ThreadLocal<>();

    //OB: Setting AndroidDriver to ThreadLocal driver
    public synchronized void setTLDriver(AndroidDriver driver) {
        tlDriver.set(driver);
    }

    public synchronized AndroidDriver getTLDriver() {
        return tlDriver.get();
    }
}

