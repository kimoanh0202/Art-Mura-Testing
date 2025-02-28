package Testcase;

import Common.Constant;
import Common.NavigationAdmin;
import Common.WaitTime;
import PageObjects.Orders.AddNewOrders;
import PageObjects.Orders.SearchID;
import PageObjects.Orders.SearchOrders;
import PageObjects.Orders.UpdateOrders;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.HashMap;

public class TC_Orders {

    @BeforeTest
    public void beforeTest() {
        System.out.println("Pre-condition");
        System.setProperty("webdriver.chrome.driver", "browserDrivers/chromedriver.exe");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
        NavigationAdmin.loginAdmin();
        NavigationAdmin.navigationOrderPage();
    }

    @BeforeMethod
    public void beforeMethod() {
        Constant.WEBDRIVER.navigate().refresh();
        WaitTime.sleep(1000);
    }

    @AfterTest
    public void cleanup() {
        System.out.println("Post-Condition");
        if (Constant.WEBDRIVER != null) {
            Constant.WEBDRIVER.quit();
        }
    }

    @Test
    public void TC01() {
        System.out.println("TC01 - Verify that clicking [+] icon displays Add New Order popup");
        Constant.WEBDRIVER.findElement(By.xpath("(//button[contains(@type,'button')])[1]")).click();
        WaitTime.sleep(2000);
        String addNew = Constant.WEBDRIVER.findElement(By.xpath("(//h1[normalize-space()='Add New Order'])[1]")).getText();
        Assert.assertEquals(addNew, "Add New Order", "Actual result is not as expected");
        WaitTime.sleep(2000);
    }

    @Test
    public void TC02() {
        System.out.println("TC02 - Verify that clicking [X] icon closes the popup");
        Constant.WEBDRIVER.findElement(By.xpath("(//button[contains(@type,'button')])[1]")).click();
        WaitTime.sleep(2000);
        Constant.WEBDRIVER.findElement(By.xpath("(//button[contains(@type,'button')])[11]")).click();
        String closePopup = Constant.WEBDRIVER.findElement(By.xpath("(//h1[normalize-space()='Orders'])[1]")).getText();
        Assert.assertEquals(closePopup, "Orders", "Actual result is not as expected");
        WaitTime.sleep(2000);
    }

    @Test
    public void TC03() {
        System.out.println("TC03 - Verify that clicking [Cancel] button closes the popup");
        Constant.WEBDRIVER.findElement(By.xpath("(//button[contains(@type,'button')])[1]")).click();
        WaitTime.sleep(2000);
        Constant.WEBDRIVER.findElement(By.xpath("//button[@id='orderPopupCancel']")).click();
        String closePopup = Constant.WEBDRIVER.findElement(By.xpath("(//h1[normalize-space()='Orders'])[1]")).getText();
        Assert.assertEquals(closePopup, "Orders", "Actual result is not as expected");
        WaitTime.sleep(1000);
    }

    @Test
    public void TC04() {
        System.out.println("TC04 - Verify that Order Name field is blank in Manual Input mode");
        Constant.WEBDRIVER.findElement(By.xpath("(//button[contains(@type,'button')])[1]")).click();
        WaitTime.sleep(2000);
        Constant.WEBDRIVER.findElement(By.xpath("(//span[@class='check'])[1]")).click();
        String textBlank = Constant.WEBDRIVER.findElement(By.xpath("(//input[@id='order_name'])[1]")).getAttribute("value");
        Assert.assertEquals(textBlank, "", "Error: Field is not blank");
        WaitTime.sleep(1000);
    }

    @Test
    public void TC05() {
        System.out.println("TC05 - Verify that Order Name cannot exceed 255 characters in Manual Input mode");
        Constant.WEBDRIVER.findElement(By.xpath("(//button[contains(@type,'button')])[1]")).click();
        WaitTime.sleep(2000);

        AddNewOrders addNewOrder = new AddNewOrders();
        HashMap<String, String> data = addNewOrder.OrderForm(
                true,
                "a".repeat(256),
                "thaolinh",
                "Credit Card",
                true, false, false, false,
                1
        );

        WaitTime.sleep(2000);
        String alertText = Constant.WEBDRIVER.findElement(By.xpath("(//div[@role='alert'])/div[2]")).getText();
        Assert.assertEquals(alertText, "Order's name has to be less than 255 characters", "Alert text is not as expected");
    }
    @Test
    public void TC06() {
        System.out.println("TC06 - Verify Add New Order with auto-generated Order Name");
        Constant.WEBDRIVER.findElement(By.xpath("(//button[contains(@type,'button')])[1]")).click();
        WaitTime.sleep(2000);

        AddNewOrders addNewOrder = new AddNewOrders();
        HashMap<String, String> Data = addNewOrder.OrderForm(false, null, "admin", "Credit Card", true, true, false, false, 2);
        WaitTime.sleep(2000);

        String alertText = Constant.WEBDRIVER.findElement(By.xpath("(//div[@role='alert'])/div[2]")).getText();
        Assert.assertEquals(alertText, "Save Data Success", "Alert text is not as expected");

        Constant.WEBDRIVER.navigate().refresh();
        SearchOrders searchOrders = new SearchOrders();
        searchOrders.SearchOrders("Name", Data.get("OrderName"));
        WaitTime.sleep(3000);

        String orderName = Constant.WEBDRIVER.findElement(By.xpath("//tbody//td[1]")).getText();
        Assert.assertEquals(orderName, Data.get("OrderName"), "Order Name is not as expected");
    }

    @Test
    public void TC07() {
        System.out.println("TC07 - Verify Add New Order with manual input Order Name");
        Constant.WEBDRIVER.findElement(By.xpath("(//button[contains(@type,'button')])[1]")).click();
        WaitTime.sleep(2000);

        AddNewOrders addNewOrder = new AddNewOrders();
        HashMap<String, String> Data = addNewOrder.OrderForm(true, "Add New Order successfully", "thaolinh", "Credit Card", true, true, false, false, 3);
        WaitTime.sleep(2000);

        String alertText = Constant.WEBDRIVER.findElement(By.xpath("(//div[@role='alert'])/div[2]")).getText();
        Assert.assertEquals(alertText, "Save Data Success", "Alert text is not as expected");
    }

    @Test
    public void TC08() {
        System.out.println("TC08 - Verify system when adding order without products");
        Constant.WEBDRIVER.findElement(By.xpath("(//button[contains(@type,'button')])[1]")).click();
        WaitTime.sleep(2000);

        AddNewOrders addNewOrder = new AddNewOrders();
        addNewOrder.OrderForm(true, "Not add products here", "thaolinh", "Credit Card", false, false, false, false, 1);
        WaitTime.sleep(2000);

        String alertText = Constant.WEBDRIVER.findElement(By.xpath("(//div[@id='error_msg_bodyProduct'])[1]")).getText();
        Assert.assertEquals(alertText, "Please add at least 1 product for this order!", "Alert text is not as expected");
    }

    @Test
    public void TC09() {
        System.out.println("TC09 - Verify system Add New Order unsuccessfully when no product is added");
        Constant.WEBDRIVER.findElement(By.xpath("(//button[contains(@type,'button')])[1]")).click();
        WaitTime.sleep(2000);

        AddNewOrders addNewOrder = new AddNewOrders();
        addNewOrder.OrderForm(true, "Not add products here", "thaolinh", "Credit Card", false, false, false, false, 3);
        WaitTime.sleep(2000);

        String alertText = Constant.WEBDRIVER.findElement(By.xpath("(//div[@id='error_msg_bodyProduct'])[1]")).getText();
        Assert.assertEquals(alertText, "Please add at least 1 product for this order!", "Alert text is not as expected");
    }

    @Test
    public void TC10() {
        System.out.println("TC10 - Verify system when entering an existing Order Name");
        Constant.WEBDRIVER.findElement(By.xpath("(//button[contains(@type,'button')])[1]")).click();
        WaitTime.sleep(2000);

        AddNewOrders addNewOrder = new AddNewOrders();
        addNewOrder.OrderForm(true, "Oanh", "thaolinh", "Credit Card", true, false, false, false, 3);
        WaitTime.sleep(2000);

        String alertText = Constant.WEBDRIVER.findElement(By.xpath("(//div[@role='alert'])/div[2]")).getText();
        Assert.assertEquals(alertText, "Order with name Oanh already exists", "Alert text is not as expected");
    }

    @Test
    public void TC11() {
        System.out.println("TC11 - Verify system calculates total price correctly");
        Constant.WEBDRIVER.findElement(By.xpath("(//button[contains(@type,'button')])[1]")).click();
        WaitTime.sleep(2000);

        AddNewOrders addNewOrder = new AddNewOrders();
        HashMap<String, String> Data = addNewOrder.OrderForm(false, null, "thaolinh", "Credit Card", true, true, false, false, 5);
        WaitTime.sleep(2000);

        Assert.assertEquals(Data.get("actualPrice"), Data.get("totalPrice"), "Total Price is not as expected");
    }
    @Test
    public void TC12() {
        System.out.println("TC12-Verify the system that when clicking the [delete] icon, the Delete Order popup appears");

        SearchID searchID = new SearchID();
        searchID.searchIDOrders("btn-delete-order-37");
        String productXpath = String.format("//button[@id='%s']", "btn-delete-order-37");
        Constant.WEBDRIVER.findElement(By.xpath(productXpath)).click();
        WaitTime.sleep(1000);

        String alertText = Constant.WEBDRIVER.findElement(By.xpath("(//h1[normalize-space()='Please confirm this action!'])[1]")).getText();
        Assert.assertEquals(alertText, "Please confirm this action!", "Alert text is not as expected");
    }

    @Test
    public void TC13() {
        System.out.println("TC13-Verify the system that when clicking the [X] icon, the popup closes");

        SearchID searchID = new SearchID();
        searchID.searchIDOrders("btn-delete-order-37");
        String productXpath = String.format("//button[@id='%s']", "btn-delete-order-37");
        Constant.WEBDRIVER.findElement(By.xpath(productXpath)).click();
        WaitTime.sleep(1000);

        Constant.WEBDRIVER.findElement(By.xpath("(//button[contains(@type,'button')])[11]")).click();
        String closePopup = Constant.WEBDRIVER.findElement(By.xpath("(//h1[normalize-space()='Orders'])[1]")).getText();
        Assert.assertEquals(closePopup, "Orders", "Popup did not close as expected");
    }

    @Test
    public void TC14() {
        System.out.println("TC14-Verify the system that when clicking the [Cancel] button, the popup closes");

        SearchID searchID = new SearchID();
        searchID.searchIDOrders("btn-delete-order-37");
        String productXpath = String.format("//button[@id='%s']", "btn-delete-order-37");
        Constant.WEBDRIVER.findElement(By.xpath(productXpath)).click();
        WaitTime.sleep(1000);

        Constant.WEBDRIVER.findElement(By.xpath("(//button[normalize-space()='Cancel'])[1]")).click();
        String closePopup = Constant.WEBDRIVER.findElement(By.xpath("(//h1[normalize-space()='Orders'])[1]")).getText();
        Assert.assertEquals(closePopup, "Orders", "Popup did not close as expected");
    }

    @Test
    public void TC15() {
        System.out.println("TC15-Verify the system successfully deletes an order when clicking the Confirm button in Remove mode");

        SearchID searchID = new SearchID();
        searchID.searchIDOrders("btn-delete-order-41");
        String productXpath = String.format("//button[@id='%s']", "btn-delete-order-41");
        Constant.WEBDRIVER.findElement(By.xpath(productXpath)).click();
        WaitTime.sleep(2000);

        Constant.WEBDRIVER.findElement(By.xpath("//button[normalize-space()='Confirm']")).click();
        WaitTime.sleep(2000);

        String alertText = Constant.WEBDRIVER.findElement(By.xpath("//div[@role='alert']//div[2]")).getText();
        Assert.assertEquals(alertText, "Delete Data Success", "Alert text is not as expected");
    }

    @Test
    public void TC16() {
        System.out.println("TC16-Verify the system successfully deletes an order when clicking the Confirm button in Revert mode");

        SearchID searchID = new SearchID();
        searchID.searchIDOrders("btn-delete-order-163");
        String productXpath = String.format("//button[@id='%s']", "btn-delete-order-163");
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
    public void TC17() {
        System.out.println("TC17 - Verify the SEARCH system is successful when searching existing data that matches the field to be filtered");
        SearchOrders searchOrders = new SearchOrders();
        searchOrders.SearchOrders("User's Name", "vananh");
        WaitTime.sleep(3000);
        String alertText = Constant.WEBDRIVER.findElement(By.xpath("//tbody//td[2]")).getText();
        Assert.assertEquals(alertText, "vananh", "Alert text is not as expected");
        WaitTime.sleep(1000);
    }

    @Test
    public void TC18() {
        System.out.println("TC18 - Verify the SEARCH system is successful when input is in UPPERCASE");
        SearchOrders searchOrders = new SearchOrders();
        searchOrders.SearchOrders("User's Name", "THAOLINH");
        WaitTime.sleep(3000);
        String alertText = Constant.WEBDRIVER.findElement(By.xpath("//tbody//td[2]")).getText();
        Assert.assertEquals(alertText.toUpperCase(), "THAOLINH", "Alert text is not as expected");
        WaitTime.sleep(1000);
    }

    @Test
    public void TC19() {
        System.out.println("TC19 - Verify the SEARCH system is successful when input is in lowercase");
        SearchOrders searchOrders = new SearchOrders();
        searchOrders.SearchOrders("User's Name", "thanhvy");
        WaitTime.sleep(3000);
        String alertText = Constant.WEBDRIVER.findElement(By.xpath("//tbody//td[2]")).getText();
        Assert.assertEquals(alertText.toLowerCase(), "thanhvy", "Alert text is not as expected");
        WaitTime.sleep(1000);
    }

    @Test
    public void TC20() {
        System.out.println("TC20 - Verify the SEARCH system is unsuccessful when searching non-existing data");
        SearchOrders searchOrders = new SearchOrders();
        searchOrders.SearchOrders("User's Name", "Not Exist");
        WaitTime.sleep(3000);
        String alertText = Constant.WEBDRIVER.findElement(By.xpath("(//td[@class='text-center text-2xl text-gray-500 font-medium pt-6 pb-6'])[1]")).getText();
        Assert.assertEquals(alertText, "There is no order yet!", "Alert text is not as expected");
        WaitTime.sleep(1000);
    }

    @Test
    public void TC21() {
        System.out.println("TC21-Verify the system UPDATE successfully the product when the Status is SUCCESS");
        SearchID searchID = new SearchID();
        searchID.searchIDOrders("btn-edit-order-122");
        String productXpath = String.format("//button[@id='%s']", "btn-editorder-122");
        Constant.WEBDRIVER.findElement(By.xpath(productXpath)).click();
        WaitTime.sleep(5000);
        if (Constant.WEBDRIVER.findElement(By.xpath("//button[@id='btn-statusorder-122']")).getText().equals("Success")) {
            UpdateOrders updateOrders = new UpdateOrders();
            UpdateOrders.OrderFormUpdate builder = new UpdateOrders.OrderFormUpdate();
            builder.setClickOrdername(false)
                    .setOrdername("Update here");
            HashMap<String, String> Data = updateOrders.updateOrder(builder);
            System.out.println(Data);
            WaitTime.sleep(2000);
            String alertText = Constant.WEBDRIVER.findElement(By.xpath("//div[@role='alert']//div[2]")).getText();
            Assert.assertEquals(alertText, "Update Data Success", "Alert text is not as expected");
            WaitTime.sleep(2000);
            Constant.WEBDRIVER.navigate().refresh();
            SearchOrders searchOrders = new SearchOrders();
            searchOrders.SearchOrders("Name","Update here");
            WaitTime.sleep(2000);
            String orderName = Constant.WEBDRIVER.findElement(By.xpath("//tbody//td[1]")).getText();
            Assert.assertEquals(orderName,Data.get("OrderName"),"Order Name is not as expected");
            String customerName = Constant.WEBDRIVER.findElement(By.xpath("//tbody//td[2]")).getText();
            Assert.assertEquals(customerName,Data.get("UserName"),"User Name is not as expected");
            String productList = Constant.WEBDRIVER.findElement(By.xpath("//tbody//td[3]")).getText();
            Assert.assertEquals(productList,Data.get("ListProduct"),"Product List is not as expected");
            String paymentMethod = Constant.WEBDRIVER.findElement(By.xpath("//tbody//td[6]")).getText();
            Assert.assertEquals(paymentMethod.toLowerCase(),Data.get("PaymentMethod").toLowerCase(),"Payment Method is not as expected");
        }
    }
    @Test
    public void TC22() {
        System.out.println("TC22-Verify the system UPDATE unsuccessfully the product when the Status is FAILED");
        SearchID searchID = new SearchID();
        searchID.searchIDOrders("btn-edit-order-20");
        String productXpath = String.format("//button[@id='%s']", "btn-editorder-20");
        Constant.WEBDRIVER.findElement(By.xpath(productXpath)).click();
        WaitTime.sleep(5000);
        if (Constant.WEBDRIVER.findElement(By.xpath("//button[@id='btn-statusorder-20']")).getText()=="Failed"){
            String alertText = Constant.WEBDRIVER.findElement(By.xpath("//div[@role='alert']//div[2]")).getText();
            Assert.assertEquals(alertText, "Failed or Canceled order can't be updated!", "Alert text is not as expected");
        }
    }
    @Test
    public void TC23() {
        System.out.println("TC23-Verify the system UPDATE unsuccessfully the product when the Status is CANCELLED");
        SearchID searchID = new SearchID();
        searchID.searchIDOrders("btn-edit-order-20");
        String productXpath = String.format("//button[@id='%s']", "btn-editorder-20");
        Constant.WEBDRIVER.findElement(By.xpath(productXpath)).click();
        WaitTime.sleep(5000);
        if (Constant.WEBDRIVER.findElement(By.xpath("//button[@id='btn-statusorder-20']")).getText()=="Failed"){
            String alertText = Constant.WEBDRIVER.findElement(By.xpath("//div[@role='alert']//div[2]")).getText();
            Assert.assertEquals(alertText, "Failed or Canceled order can't be updated!", "Alert text is not as expected");
        }
    }
    @Test
    public void TC24() {
        System.out.println("TC24-Verify the system that when click icon [edit] appear Edit Order popup");
        SearchID searchID = new SearchID();
        searchID.searchIDOrders("btn-edit-order-122");
        String productXpath = String.format("//button[@id='%s']", "btn-editorder-122");
        Constant.WEBDRIVER.findElement(By.xpath(productXpath)).click();
        WaitTime.sleep(1000);
        String alertText = Constant.WEBDRIVER.findElement(By.xpath("(//h1[normalize-space()='Edit Order'])[1]")).getText();
        Assert.assertEquals(alertText, "Edit Order", "Alert text is not as expected");
        WaitTime.sleep(1000);
    }
    @Test
    public void TC25() {
        System.out.println("TC25-Verify the system that when click [X] icon will turn off the popup");
        SearchID searchID = new SearchID();
        searchID.searchIDOrders("btn-edit-order-122");
        String productXpath = String.format("//button[@id='%s']", "btn-editorder-122");Constant.WEBDRIVER.findElement(By.xpath(productXpath)).click();
        WaitTime.sleep(1000);

        Constant.WEBDRIVER.findElement(By.xpath("(//button[@type='button'])[11]")).click();
        String closepopup = Constant.WEBDRIVER.findElement(By.xpath("(//h1[normalizespace()='Orders'])[1]")).getText();
        Assert.assertEquals(closepopup,"Orders","Actual is not expect");
        WaitTime.sleep(1000);
    }
    @Test
    public void TC26() {
        System.out.println("TC26-Verify the system that when click [Cancel] button will turn off the popup");
        SearchID searchID = new SearchID();
        searchID.searchIDOrders("btn-edit-order-122");
        String productXpath = String.format("//button[@id='%s']", "btn-editorder-122");
        Constant.WEBDRIVER.findElement(By.xpath(productXpath)).click();
        WaitTime.sleep(1000);
        Constant.WEBDRIVER.findElement(By.xpath("(//button[normalizespace()='Cancel'])[1]")).click();
        String closepopup = Constant.WEBDRIVER.findElement(By.xpath("(//h1[normalizespace()='Orders'])[1]")).getText();
        Assert.assertEquals(closepopup,"Orders","Actual is not expect");
        WaitTime.sleep(1000);
    }
}