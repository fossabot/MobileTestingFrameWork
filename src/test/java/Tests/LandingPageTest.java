package Tests;


import Configuration.BaseSetup;
import Listeners.TestListener;
import Pages.LandingPage;
import io.qameta.allure.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;


@Listeners({TestListener.class})      //naming package Listeners make conflict with junit package, you will have to import TestListener manually
                                      // without this annotation attachments will not appear
@Epic("Landing page users features")
@Feature("Select between local and tourist user")
public class LandingPageTest extends BaseSetup {


    @Test (priority = 1 , description = "Verify user can use tourist mode")
    @TmsLink("700")
    @Issue("")
    @Story("As a user i want to be able to log in as tourist")
    @Description("Application will be rest and user will select tourist mode")
    public  void Verify_user_can_use_tourist_mode() throws IOException {
        LandingPage page = new LandingPage(driver);
        page.sign_as_Tourist();
    }
//
    @Test (priority = 2 , description = "Verify user can use local mode")
    @Story("As a user i want to be able to log in as tourist")
    @Description("Application will be rest and user will select local mode")
    public  void Verify_user_can_use_local_mode() {
        LandingPage page = new LandingPage(driver);
        page.sign_as_Local();
    }

    @Test(priority = 4 , description = "Verify places neary by your location is visible")
    @Story("As a user i want to see restaurant near by my location")
    @Description("After logging as local user will see on landing page places near by menu")
    public void Verify_Places_near_by_is_displayed () throws InterruptedException {
        LandingPage page = new LandingPage(driver);
        page.places_Near_by_are_displayed();
    }



}
