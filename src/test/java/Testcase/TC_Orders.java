package Testcase;

import Common.Constant;
import Common.NavigationAdmin;
import Common.WaitTime;
import PageObjects.Orders.AddNewOrders;
import PageObjects.Orders.SearchID;
import PageObjects.Orders.SearchOrders;
import PageObjects.Orders.UpdateOrders;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC_Orders {
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");
        System.setProperty("webdriver.chrome.driver", "browserDrivers/chromedriver.exe");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }


//    @AfterMethod
//    public void cleanup() {
//        System.out.println("Post-Condition");
//        if (Constant.WEBDRIVER != null) {
//            Constant.WEBDRIVER.quit();
//        }
//    }


    @Test
    public void TC01(){
        System.out.println("TC01-Verify system add new order successfully with [Manual Input] OFF toggle button");
        NavigationAdmin.navigationOrderPage(Constant.WEBDRIVER);
        Constant.WEBDRIVER.findElement(By.xpath("(//button[contains(@type,'button')])[1]")).click();
        WaitTime.sleep(2000);
        AddNewOrders addNewOrder = new AddNewOrders();
        addNewOrder.OrderForm(false, null, "thaolinh", "Credit Card", true, true, false, false);
        WaitTime.sleep(2000);
        String alertText = Constant.WEBDRIVER.findElement(By.xpath("(//div[@role='alert'])/div[2]")).getText();
        Assert.assertEquals(alertText, "Save Data Success", "Alert text is not as expected");
    }

    @Test
    public void TC02(){
        System.out.println("TC02-Verify system add new order successfully with [Manual Input] ON toggle button");
        NavigationAdmin.navigationOrderPage(Constant.WEBDRIVER);
        Constant.WEBDRIVER.findElement(By.xpath("(//button[contains(@type,'button')])[1]")).click();
        WaitTime.sleep(2000);
        AddNewOrders addNewOrder = new AddNewOrders();
        addNewOrder.OrderForm(true, "This is product not buy", "thaolinh", "Credit Card", true, true, false, false);
        WaitTime.sleep(2000);
        String alertText = Constant.WEBDRIVER.findElement(By.xpath("(//div[@role='alert'])/div[2]")).getText();
        Assert.assertEquals(alertText, "Save Data Success", "Alert text is not as expected");
    }

    @Test
    public void TC03(){
        System.out.println("TC03-Verify system add new order failed when no product are added to the Product List");
        NavigationAdmin.navigationOrderPage(Constant.WEBDRIVER);
        Constant.WEBDRIVER.findElement(By.xpath("(//button[contains(@type,'button')])[1]")).click();
        WaitTime.sleep(2000);
        AddNewOrders addNewOrder = new AddNewOrders();
        addNewOrder.OrderForm(true, "Not add products here", "thaolinh", "Credit Card", false, false, false, false);
        WaitTime.sleep(2000);
        String alertText = Constant.WEBDRIVER.findElement(By.xpath("(//div[@role='alert'])/div[2]")).getText();
        Assert.assertEquals(alertText, "Please add at least 1 product for this order!", "Alert text is not as expected");
    }

    @Test
    public void TC04(){
        System.out.println("TC04-Verify that in the case of [Manual input] when input exist ORDER NAME an error is reported");
        NavigationAdmin.navigationOrderPage(Constant.WEBDRIVER);
        Constant.WEBDRIVER.findElement(By.xpath("(//button[contains(@type,'button')])[1]")).click();
        WaitTime.sleep(2000);
        AddNewOrders addNewOrder = new AddNewOrders();
        addNewOrder.OrderForm(true, "Oanh", "thaolinh", "Credit Card", true, false, false, false);
        WaitTime.sleep(2000);
        String alertText = Constant.WEBDRIVER.findElement(By.xpath("(//div[@role='alert'])/div[2]")).getText();
        Assert.assertEquals(alertText, "Order with name Oanh already exists", "Alert text is not as expected");
    }

    @Test
    public void TC05() {
        System.out.println("TC05-Verify system successfully DELETE product when click Confirm button in Remove mode");
        NavigationAdmin.navigationOrderPage(Constant.WEBDRIVER);
        SearchID searchID = new SearchID();
        searchID.searchIDOrders("btn-delete-order-23");
        String productXpath = String.format("//button[@id='%s']", "btn-delete-order-23");
        Constant.WEBDRIVER.findElement(By.xpath(productXpath)).click();
        Constant.WEBDRIVER.findElement(By.xpath("//button[normalize-space()='Confirm']")).click();
        WaitTime.sleep(2000);
        String alertText = Constant.WEBDRIVER.findElement(By.xpath("//div[@role='alert']//div[2]")).getText();
        Assert.assertEquals(alertText, "Delete Data Success", "Alert text is not as expected");

    }

    @Test
    public void TC06() {
        System.out.println("TC06-Verify system successfully DELETE product when click Confirm button in Revert mode");
        NavigationAdmin.navigationOrderPage(Constant.WEBDRIVER);
        SearchID searchID = new SearchID();
        searchID.searchIDOrders("btn-delete-order-19");
        String productXpath = String.format("//button[@id='%s']", "btn-delete-order-19");
        Constant.WEBDRIVER.findElement(By.xpath(productXpath)).click();
        WaitTime.sleep(2000);
        Constant.WEBDRIVER.findElement(By.xpath("(//span[@class='check'])[1]")).click();
        WaitTime.sleep(2000);
        Constant.WEBDRIVER.findElement(By.xpath("//button[normalize-space()='Confirm']")).click();
        WaitTime.sleep(2000);
        String alertText = Constant.WEBDRIVER.findElement(By.xpath("//div[@role='alert']//div[2]")).getText();
        Assert.assertEquals(alertText, "Delete Data Success", "Alert text is not as expected");
    }

    @Test
    public void TC07(){
        System.out.println("TC07-Verify that the SEARCH function when search for a value that EXIST");
        NavigationAdmin.navigationOrderPage(Constant.WEBDRIVER);
        SearchOrders searchOrders = new SearchOrders();
        searchOrders.SearchOrders("User's Name","vananh");
        WaitTime.sleep(3000);
        String alertText = Constant.WEBDRIVER.findElement(By.xpath("//tbody//td[2]")).getText();
        Assert.assertEquals(alertText, "vananh", "Alert text is not as expected");
    }

    @Test
    public void TC08(){
        System.out.println("TC008-Verify that the SEARCH function when search for a value that DON'T EXIST");
        NavigationAdmin.navigationOrderPage(Constant.WEBDRIVER);
        SearchOrders searchOrders = new SearchOrders();
        searchOrders.SearchOrders("User's Name","Not Exist");
        WaitTime.sleep(3000);
        String alertText = Constant.WEBDRIVER.findElement(By.xpath("(//td[@class='text-center text-2xl text-gray-500 font-medium pt-6 pb-6'])[1]")).getText();
        Assert.assertEquals(alertText, "There is no order yet!", "Alert text is not as expected");}

    @Test
    public void TC09() {
        System.out.println("TC09'>?.-Verify system successfully UPDATE product");
        NavigationAdmin.navigationOrderPage(Constant.WEBDRIVER);
        SearchID searchID = new SearchID();
        searchID.searchIDOrders("btn-edit-order-119");
        String productXpath = String.format("//button[@id='%s']", "btn-edit-order-119");
        Constant.WEBDRIVER.findElement(By.xpath(productXpath)).click();
        WaitTime.sleep(5000);

        UpdateOrders updateOrders = new UpdateOrders();
        UpdateOrders.OrderFormUpdate builder = new UpdateOrders.OrderFormUpdate();
        builder.setClickOrdername(false)
                .setOrdername("BO KHÙNG QUÁ")  // Set the new order name
                .setPaymentMethod("Credit Card")
                .setAddProducts(true);
        updateOrders.Update(builder);
        WaitTime.sleep(2000);

        String alertText = Constant.WEBDRIVER.findElement(By.xpath("//div[@role='alert']//div[2]")).getText();
        Assert.assertEquals(alertText, "Update Data Success", "Alert text is not as expected");
    }

}

