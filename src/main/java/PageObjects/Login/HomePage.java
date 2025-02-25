package PageObjects.Login;

import Common.Constant;
import PageObjects.GeneralPage;

public class HomePage extends GeneralPage {
    public HomePage open()
    {
        Constant.WEBDRIVER.navigate().to(Constant.EC_URL);
        return this;
    }
}
