package preference;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.Preference;
import utils.BaseTests;

public class PreferenceDependancyTests  extends BaseTests{
    @Test(dataProvider = "wifi")
    public void testWifiSetting(String wifi){
        HomePage homePage = new HomePage(driver);
        homePage.goToPreferencePage();
        Preference preference = new Preference(driver);
        preference.goToPreferenceDependancies();

        preference.inputWifi(wifi);
        //Assert.assertEquals(true, false);
    }

    @Test (enabled = false)
    public void testWifiSetting2() {
        Assert.assertEquals(true, true);
    }

    @Test(dataProvider = "wifi1", dataProviderClass = TestData.class)
    public void testData(String wifi){
        Assert.assertEquals(wifi, "hello");
    }

    @Test(dataProvider = "preference-1", dataProviderClass = TestData.class)
    public void testsample(String name, String password, String result){
        System.out.println(name);
        System.out.println(password);
        System.out.println(result);

    }

    @DataProvider(name = "wifi")
    public Object[][] getData(){
        Object[][] data = new Object[2][1];
        data[0][0] = "hello";
        data[1][0] = "hi";
        return data;
    }

}
