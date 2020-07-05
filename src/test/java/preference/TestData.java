package preference;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;

import java.io.IOException;

import static utils.ExcelDataReader.getExcelData;

public class TestData {
    @DataProvider(name = "wifi1")
    public Object[][] getData(){
        Object[][] data = new Object[2][1];
        data[0][0] = "hello";
        data[1][0] = "hi";
        return data;
    }

    @DataProvider(name = "wifi2")
    public Object[][] getwifiData(){
        return new Object[][]{
                {"hello"},
                {"hi"}
        };

    }
    @DataProvider(name = "preference-1")
    public Object[][] getLoginData() throws IOException, InvalidFormatException {

         return getExcelData("preference", "1");
    }

}
