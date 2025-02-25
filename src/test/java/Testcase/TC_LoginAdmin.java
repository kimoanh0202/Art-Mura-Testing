package Testcase;

import Common.Constant;
import Common.WaitTime;
import PageObjects.Login.Login;
import PageObjects.LoginAdmin.LoginAdmin;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC_LoginAdmin {
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");
        System.setProperty("webdriver.chrome.driver", "browserDrivers/chromedriver.exe");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }


    @AfterMethod
    public void cleanup() {
        System.out.println("Post-Condition");
        if (Constant.WEBDRIVER != null) {
            Constant.WEBDRIVER.quit();
        }
    }

    @Test
    public void TC01(){
        System.out.println("TC01-User login successfully with username and password valid");
        LoginAdmin loginAdmin = new LoginAdmin();
        loginAdmin.openLoginAdmin();
        WaitTime.sleep(2000);
        Login loginPage =new Login();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);
        WaitTime.sleep(5000);
        String actualResult1 = Constant.WEBDRIVER.findElement(By.xpath("//div[@role='alert']//div[2]")).getText();
        Assert.assertEquals(actualResult1, "Login Success", "Message does not match the expected result");
    }

    @Test
    public void TC02(){
        System.out.println("TC02-User login unsuccessfully with username valid and password invalid");
        LoginAdmin loginAdmin = new LoginAdmin();
        loginAdmin.openLoginAdmin();
        WaitTime.sleep(2000);
        Login loginPage =new Login();
        loginPage.login(Constant.USERNAME, "Oanh123");
        WaitTime.sleep(5000);
        String actualResult1 = Constant.WEBDRIVER.findElement(By.xpath("//div[@role='alert']//div[2]")).getText();
        Assert.assertEquals(actualResult1, "validation.login.credential_wrong", "Message does not match the expected result");
    }

    @Test
    public void TC03(){
        System.out.println("TC03-User login unsuccessfully with username invalid and password valid");
        LoginAdmin loginAdmin = new LoginAdmin();
        loginAdmin.openLoginAdmin();
        WaitTime.sleep(2000);
        Login loginPage =new Login();
        loginPage.login("adminnew", Constant.PASSWORD);
        WaitTime.sleep(5000);
        String actualResult1 = Constant.WEBDRIVER.findElement(By.xpath("//div[@role='alert']//div[2]")).getText();
        Assert.assertEquals(actualResult1, "validation.login.credential_wrong", "Message does not match the expected result");
    }
}
