package PageObjects.Orders;

import Common.Constant;
import Common.WaitTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class SearchID {
    public boolean searchIDOrders(String IDXpath){
        String productXpath = String.format("//button[@id='%s']", IDXpath);
        List<WebElement> orderID = Constant.WEBDRIVER.findElements(By.xpath(productXpath));
        List<WebElement> nextpagebtn = Constant.WEBDRIVER.findElements(By.xpath("(//a[normalize-space()='>'])[1]"));

        while (orderID.isEmpty() && !nextpagebtn.isEmpty()) {
            nextpagebtn.get(0).click();
            WaitTime.sleep(2000);
            orderID = Constant.WEBDRIVER.findElements(By.xpath(productXpath));
            nextpagebtn = Constant.WEBDRIVER.findElements(By.xpath("(//a[normalize-space()='>'])[1]"));
        }
        if (!orderID.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
