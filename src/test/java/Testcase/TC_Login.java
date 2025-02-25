package Testcase;

import Common.Constant;
import Common.WaitTime;
import PageObjects.Login.HomePage;
import PageObjects.Login.Login;
import PageObjects.LoginAdmin.LoginAdmin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC_Login {
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
        HomePage homePage = new HomePage();
        homePage.open();
        Login loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);
        WaitTime.sleep(5000);
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, 10);
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']//div[2]")));
        String actualResult1 = successMessage.getText();
        Assert.assertEquals(actualResult1, "Login Success", "Message does not match the expected result");
    }

    @Test
    public void TC02(){
        System.out.println("TC02-User login unsuccessfully with username valid and password invalid");
        HomePage homePage = new HomePage();
        homePage.open();
        Login loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, "Oanh123");
        WaitTime.sleep(5000);
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, 10);
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']//div[2]")));
        String actualResult1 = successMessage.getText();
        Assert.assertEquals(actualResult1, "validation.login.credential_wrong", "Message does not match the expected result");
    }

    @Test
    public void TC03(){
        System.out.println("TC03-User login unsuccessfully with username invalid and password valid");
        HomePage homePage = new HomePage();
        homePage.open();
        Login loginPage = homePage.gotoLoginPage();
        loginPage.login(null, "Oanh123");
        WaitTime.sleep(5000);
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, 10);
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']//div[2]")));
        String actualResult1 = successMessage.getText();
        Assert.assertEquals(actualResult1, "validation.login.credential_wrong", "Message does not match the expected result");
    }


}
