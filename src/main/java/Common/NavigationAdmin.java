package Common;
import PageObjects.Login.AdminPage;
import PageObjects.Login.HomePage;
import PageObjects.Login.Login;
import PageObjects.LoginAdmin.LoginAdmin;
import PageObjects.Orders.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationAdmin {
    public static void navigationAdmin (WebDriver driver) {
        HomePage homePage = new HomePage();
        homePage.open();
        WaitTime.sleep(2000);
        Login loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);
        WaitTime.sleep(2000);
        AdminPage adminPage = new AdminPage();
        adminPage.openAdmin();
        WaitTime.sleep(1000);
        OrderPage orderPage = new OrderPage();
        orderPage.openOrder();
        WaitTime.sleep(2000);
    }

    public static void navigationOrderPage (WebDriver driver){
        LoginAdmin loginAdmin = new LoginAdmin();
        loginAdmin.openLoginAdmin();
        Login loginPage = new Login();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);
        WaitTime.sleep(5000);
        Constant.WEBDRIVER.findElement(By.xpath("//a[@href='/admin/order']")).click();
        WaitTime.sleep(2000);
    }


//    public static void navigationAdmin (WebDriver driver) {
//        HomePage homePage = new HomePage();
//        homePage.open();
//        WaitTime.sleep(2000);
//        Login loginPage = homePage.gotoLoginPage();
//        loginPage.login(Constant.USERNAME, Constant.PASSWORD);
//        WaitTime.sleep(2000);
//        AdminPage adminPage = new AdminPage();
//        adminPage.openAdmin();
//        WaitTime.sleep(1000);
//        OrderPage orderPage = new OrderPage();
//        orderPage.openOrder();
//        WaitTime.sleep(2000);
//    }
}
