package PageObjects;

import Common.Constant;
import PageObjects.Login.Login;
import PageObjects.Orders.AddNewOrders;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GeneralPage {
    //khai báo all xPath sẽ sử dụng, đặt tên cho nó
    //Locator: định vị các phần tử trên trang
    private final By tabLogin = By.xpath("//a[@href='/login']");
    private final By lbWelcomeMessage = By.xpath("//div[@class='mb-3']/p");
    private final By tabAdd = By.xpath("//section[contains(@class,'mb-6 flex items-center justify-between')]//button[contains(@type,'button')]");

    //Element: phương thức lấy phần tử ra
    protected WebElement getTabLogin() { return Constant.WEBDRIVER.findElement(tabLogin);}
    public WebElement getlbWelcomeMessage() { return Constant.WEBDRIVER.findElement(lbWelcomeMessage);}
    protected WebElement getTabAdd() { return Constant.WEBDRIVER.findElement(tabAdd);}

    //Method: phương thức xử lý
    public String WelcomeMessage()
    {
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, 10);
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(lbWelcomeMessage));
        return this.getlbWelcomeMessage().getText();
    }


    public Login gotoLoginPage()
    {
        this.getTabLogin().click();
        return new Login();
    }

}
