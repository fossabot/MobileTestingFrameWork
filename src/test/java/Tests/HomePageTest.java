package Tests;

import Listeners.TestListener;
import Pages.LandingPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;


@Listeners(TestListener.class)
public class HomePageTest {

    protected static AndroidDriver<MobileElement> driver = null;


    @Test(priority = 1)
    @Story("SECOND TEST SUITE 1")
    @Description("Application will be rest and user will select tourist mode")
    public  void Verify_user_can_use_tourist_mode() throws IOException {
        LandingPage page = new LandingPage(driver);
        page.sign_as_Tourist();
    }

    @Test(priority = 2)
    @Story("SECOND TEST SUITE")
    @Description("Application will be rest and user will select local mode")
    public  void Verify_user_can_use_local_mode() {
        LandingPage page = new LandingPage(driver);
        page.sign_as_Local();
    }

}
