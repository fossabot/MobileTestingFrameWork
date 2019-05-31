package Pages;

import Configuration.BaseSetup;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class LandingPage extends BaseSetup{


//    protected AndroidDriver driver;
    protected WebDriverWait wait;


    public LandingPage(AndroidDriver driver ) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Visit your favourite venue or discover a new place\")")
    private WebElement introText;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Next')]")
    private MobileElement nextButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Skip')]")
    private MobileElement skipButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Tourist\")")
    private  MobileElement touristMode;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Local\")")
    private  MobileElement localMode;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Get Started')]")
    private  MobileElement getStartedButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"PLACES NEARBY\")")
    private  MobileElement placesnearby;

    @AndroidFindBy(xpath = "//android.widget.EditText[contains(@text, 'Search')]")
    private  MobileElement searchField;

    @AndroidFindBy(className = "android.widget.ImageView")    //INDEX 2
    private List <MobileElement> ringIcon;

    @AndroidFindBy(className = "android.widget.ImageView")    //INDEX 0
    private List <MobileElement> burgerMenu;

    @AndroidFindBy(xpath = "//android.widget.EditText[contains(@text, 'Search')]")
    private  MobileElement notificationHeader;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc = \"Go back\")]")
    private  MobileElement goBack;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Settings')]")
    private MobileElement settings;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Tourist\")")
    private MobileElement veriTourist;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Tourist\")")
    private MobileElement veriLocal;

    @AndroidFindBy(className = "android.widget.ImageView")   //INDEX 8 first image of restaurant
    private List <WebElement> placesNearBy;



    @Step("Application will start and user will navigate as tourist")
    public void sign_as_Tourist () throws IOException {

        new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOf(introText));
        nextButton.click();
        nextButton.click();
        nextButton.click();
        touristMode.click();
        getStartedButton.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        burgerMenu.get(0).click();
        settings.click();
        Assert.assertEquals(veriTourist.getText(),"TourSist");
//        try{
//            Assert.assertEquals(veriTourist.getText(),"TouSrist");
//        }catch(AssertionError e){
//            System.out.println("Tourist mode is not selected");
//            screenshot();
//        }


    }

    @Step("Application will start and user will navigate as local")
    public void sign_as_Local () {

        new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOf(introText));
        nextButton.click();
        nextButton.click();
        nextButton.click();
        localMode.click();
        getStartedButton.click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        burgerMenu.get(0).click();
        settings.click();
        Assert.assertEquals(localMode.isDisplayed(),true);

    }

    @Step("Application will start and user will navigate as local")
    public void places_Near_by_are_displayed() {
        Assert.assertEquals(placesNearBy.get(8).isDisplayed(),true);
    }











}