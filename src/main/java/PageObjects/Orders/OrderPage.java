package PageObjects.Orders;

import Common.Constant;
import PageObjects.GeneralPage;
import org.openqa.selenium.WebElement;

public class OrderPage extends GeneralPage {
    public OrderPage openOrder()
    {
        Constant.WEBDRIVER.navigate().to(Constant.Order_URL);
        return this;
    }
}
