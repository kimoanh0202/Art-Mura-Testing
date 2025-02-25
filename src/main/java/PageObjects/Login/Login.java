package PageObjects.Login;

import Common.Constant;
import PageObjects.GeneralPage;
import PageObjects.LoginAdmin.LoginAdmin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Login extends GeneralPage {
    //locator
    private final By _txtUsername = By.xpath("//input[@name='username']");
    private final By _txtPassword = By.xpath("//input[@name='password']");
    private final By _btnLogin = By.xpath("//button[@type='submit']");

    //element
    public WebElement getTxtUsername() { return Constant.WEBDRIVER.findElement(_txtUsername);}
    public WebElement getTxtPassword() { return Constant.WEBDRIVER.findElement(_txtPassword);}
    public WebElement getBtnLogin() { return Constant.WEBDRIVER.findElement(_btnLogin);}

    //method
    public LoginAdmin login (String username, String password)
    {
        this.getTxtUsername().sendKeys(username);
        this.getTxtPassword().sendKeys(password);
        this.getBtnLogin().click();
        return new LoginAdmin();
    }

}

