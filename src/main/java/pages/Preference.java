package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Preference {

    @AndroidFindBy(xpath="//android.widget.TextView[@text='3. Preference dependencies']")
    private WebElement dependencies;

    @AndroidFindBy(id = "android:id/checkbox")
    private WebElement wifiCheckbox;

    @AndroidFindBy(className = "android.widget.RelativeLayout")
    private List<WebElement> wifiMenus;

    @AndroidFindBy(className = "android.widget.EditText")
    private WebElement wifisetting;

    @AndroidFindBy(className = "android.widget.Button")
    private List<WebElement> buttons;


    public Preference(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    public void goToPreferenceDependancies() {
        dependencies.click();
    }

    public void inputWifi(String password){
        wifiCheckbox.click();
        wifiMenus.get(1).click();
        wifisetting.sendKeys(password);
        buttons.get(1).click();

    }


}
