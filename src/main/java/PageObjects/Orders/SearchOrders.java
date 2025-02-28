package PageObjects.Orders;
import Common.Constant;
import PageObjects.GeneralPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
public class SearchOrders extends GeneralPage {
    private final By _sltSearch = By.xpath("(//select[@class='px-3 py-2 border-gray-100 rounded-md mr-2 w-[200px]'])[1]");
    private final By _txtSearchOrders =
            By.xpath("//input[@placeholder='Search orders...']");
    public WebElement getsltSearch() { return
            Constant.WEBDRIVER.findElement(_sltSearch);}
    public WebElement gettxtSearchOrders() { return
            Constant.WEBDRIVER.findElement(_txtSearchOrders);}
    public void SearchOrders (String sltSearch, String inputSearch ){
        try {
            new Select(this.getsltSearch()).selectByVisibleText(sltSearch);
            Thread.sleep(1000);
            this.gettxtSearchOrders().sendKeys(inputSearch);
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}