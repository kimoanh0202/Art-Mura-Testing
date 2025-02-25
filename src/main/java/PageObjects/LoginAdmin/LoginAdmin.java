package PageObjects.LoginAdmin;

import Common.Constant;
import PageObjects.GeneralPage;

public class LoginAdmin extends GeneralPage {
    public LoginAdmin openLoginAdmin()
    {
        Constant.WEBDRIVER.navigate().to(Constant.LoginAdmin_URL);
        return this;
    }
}

