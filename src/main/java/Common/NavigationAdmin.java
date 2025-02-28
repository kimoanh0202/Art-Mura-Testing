package Common;
import PageObjects.Login.Login;
import PageObjects.LoginAdmin.LoginAdmin;
import org.openqa.selenium.By;
public class NavigationAdmin {
    public static void loginAdmin() {
        LoginAdmin loginAdmin = new LoginAdmin();
        loginAdmin.openLoginAdmin();
        Login loginPage = new Login();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);
        WaitTime.sleep(5000);
    }
    public static void navigationOrderPage (){

        Constant.WEBDRIVER.findElement(By.xpath("//a[@href='/admin/order']")).click()
        ;
        WaitTime.sleep(2000);
    }
    public static void navigationProductPage (){

        Constant.WEBDRIVER.findElement(By.xpath("//a[@href='/admin/product']")).click
                ();
        WaitTime.sleep(2000);
    }
}