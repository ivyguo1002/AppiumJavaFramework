package preference;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTests;

public class AdvancedPreferenceTests {

    @Test(dataProvider = "preference-1", dataProviderClass = TestData.class)
    public void testsample(String name, String password, String result){
        System.out.println(name + password + result);
    }

}
