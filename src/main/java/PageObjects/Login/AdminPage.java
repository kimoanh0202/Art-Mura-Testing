package PageObjects.Login;

import Common.Constant;
import PageObjects.GeneralPage;


public class AdminPage extends GeneralPage {
    public AdminPage openAdmin()
    {
        Constant.WEBDRIVER.navigate().to(Constant.Admin_URL);
        return this;
    }
}

