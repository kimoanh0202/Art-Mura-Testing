package Testcase;

import Common.Constant;
import Common.WaitTime;
import PageObjects.Login.HomePage;
import PageObjects.Login.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class example {
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




////package Testcase;
////import Common.Constant;
////import Common.WaitTime;
////import PageObjects.Login.Login.AdminPage;
////import PageObjects.Login.Login.HomePage;
////import PageObjects.Login.Login;
////import PageObjects.Orders.AddNewOrder;
////import PageObjects.Orders.OrderPage;
////import org.openqa.selenium.By;
////import org.openqa.selenium.WebDriver;
////import org.openqa.selenium.WebElement;
////import org.openqa.selenium.chrome.ChromeDriver;
////import org.openqa.selenium.support.ui.ExpectedConditions;
////import org.openqa.selenium.support.ui.WebDriverWait;
////import org.testng.Assert;
////import org.testng.annotations.AfterMethod;
////import org.testng.annotations.BeforeMethod;
////import org.testng.annotations.Test;
////
////public class example {
////    WebDriver driver;
////
////
////    @BeforeMethod
////    public void setUp() {
////        // Đường dẫn tới driver của Chrome
////        System.setProperty("webdriver.chrome.driver", "browserDrivers/chromedriver.exe");
////        driver = new ChromeDriver();
////    }
////
////    @Test
////    public void LG01() {
////        System.out.println("LG01-Navigate to Login");
////        driver.get("http://192.168.3.220:3004/");
////        driver.manage().window().maximize();
////        driver.findElement(By.xpath("//a[@href='/login']")).click();
////        String actualResult = driver.findElement(By.xpath("//div[@class='mb-3']/p")).getText();
////        Assert.assertEquals(actualResult, "welcome back customer", "Actual welcome message is different from expected");
////    }
////
////
////    @Test
////    public void LG02() {
////        System.out.println("LG02-Login successfully");
////        driver.get("http://192.168.3.220:3004/");
////        driver.manage().window().maximize();
////        driver.findElement(By.xpath("//a[@href='/login']")).click();
////        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
////        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Hello123");
////        driver.findElement(By.xpath("//button[@type='submit']")).click();
////        WebDriverWait wait = new WebDriverWait(driver, 10);
////        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']//div[2]")));
////        String actualResult1 = successMessage.getText();
////        Assert.assertEquals(actualResult1, "Login Success", "Message does not match the expected result");
////    }
////
////    @Test
////    public void LG03() {
////        System.out.println("LG03-Login unsuccessfully when input username by UPPERCASE");
////        driver.get("http://192.168.3.220:3004/");
////        driver.manage().window().maximize();
////        driver.findElement(By.xpath("//a[@href='/login']")).click();
////        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("ADMIN");
////        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Hello123");
////        driver.findElement(By.xpath("//button[@type='submit']")).click();
////        WebDriverWait wait = new WebDriverWait(driver, 10);
////        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']//div[2]")));
////        String actualResult1 = successMessage.getText();
////        Assert.assertEquals(actualResult1, "Login Success", "Message does not match the expected result");
////    }
////
////    @Test
////    public void LG04() {
////        System.out.println("LG04-Login unsuccessfully when input the space at the beginning of username");
////        driver.get("http://192.168.3.220:3004/");
////        driver.manage().window().maximize();
////        driver.findElement(By.xpath("//a[@href='/login']")).click();
////        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(" admin");
////        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Hello123");
////        driver.findElement(By.xpath("//button[@type='submit']")).click();
////        WebDriverWait wait = new WebDriverWait(driver, 10);
////        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']//div[2]")));
////        String actualResult1 = successMessage.getText();
////        Assert.assertEquals(actualResult1, "validation.login.credential_wrong", "Message does not match the expected result");
////    }
////
////    @Test
////    public void LG05() {
////        System.out.println("LG05-Login unsuccessfully when input the space at the ending of username");
////        driver.get("http://192.168.3.220:3004/");
////        driver.manage().window().maximize();
////        driver.findElement(By.xpath("//a[@href='/login']")).click();
////        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin ");
////        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Hello123");
////        driver.findElement(By.xpath("//button[@type='submit']")).click();
////        WebDriverWait wait = new WebDriverWait(driver, 10);
////        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']//div[2]")));
////        String actualResult1 = successMessage.getText();
////        Assert.assertEquals(actualResult1, "validation.login.credential_wrong", "Message does not match the expected result");
////    }
////
////    @Test
////    public void LG06() {
////        System.out.println("LG06-Login unsuccessfully when input the space at the middle  of username");
////        driver.get("http://192.168.3.220:3004/");
////        driver.manage().window().maximize();
////        driver.findElement(By.xpath("//a[@href='/login']")).click();
////        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("ad min");
////        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Hello123");
////        driver.findElement(By.xpath("//button[@type='submit']")).click();
////        WebDriverWait wait = new WebDriverWait(driver, 10);
////        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']//div[2]")));
////        String actualResult1 = successMessage.getText();
////        Assert.assertEquals(actualResult1, "validation.login.credential_wrong", "Message does not match the expected result");
////    }
////
////    @AfterMethod
////    public void cleanup() {
////        if (driver != null) {
////            driver.close();
////        }
////        System.out.println("End");
////    }
////}
//
//
////////////////////////////////
//package Testcase;
//
//import Common.Constant;
//import Common.WaitTime;
//import PageObjects.Login.Login.AdminPage;
//import PageObjects.Login.Login.HomePage;
//import PageObjects.Login.Login;
//import PageObjects.Orders.AddNewOrder;
//import PageObjects.Orders.OrderPage;
//import org.openqa.selenium.By;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//public class example {
//
//    @BeforeMethod
//    public void beforeMethod() {
//        System.out.println("Pre-condition");
//        System.setProperty("webdriver.chrome.driver", "browserDrivers/chromedriver.exe");
//        Constant.WEBDRIVER = new ChromeDriver();
//        Constant.WEBDRIVER.manage().window().maximize();
//    }
//
//    @AfterMethod
//    public void cleanup() {
//        System.out.println("Post-Condition");
//        if (Constant.WEBDRIVER != null) {
//            Constant.WEBDRIVER.quit();
//        }
//    }
//
//    @Test
//    public void TC01() {
//        System.out.println("TC01-Verify that navigate to LoginPage");
//        HomePage homePage = new HomePage(); //tạo 1 đối tượng homepage
//        homePage.open();
//        Login loginPage = homePage.gotoLoginPage();
//        String actualResult = loginPage.WelcomeMessage();
//        Assert.assertEquals(actualResult, "welcome back customer", "Actual welcome message is different from expected");
//        WaitTime.sleep(5000);
//    }
//
//    @Test
//    public void TC02(){
//        System.out.println("TC02-User login successfully with username and password valid");
//        HomePage homePage = new HomePage(); //tạo 1 đối tượng homepage
//        homePage.open();
//        Login loginPage = homePage.gotoLoginPage();
//        loginPage.login(Constant.USERNAME, Constant.PASSWORD);
//        WaitTime.sleep(5000);
//    }
//
//    @Test
//    public void TC03(){
//        System.out.println("TC03-Verify that navigate to AdminPage");
//        HomePage homePage = new HomePage(); //tạo 1 đối tượng homepage
//        homePage.open();
//        Login loginPage = homePage.gotoLoginPage();
//        loginPage.login(Constant.USERNAME, Constant.PASSWORD);
//        WaitTime.sleep(5000);
//        AdminPage adminPage = new AdminPage();
//        adminPage.openAdmin();
//        WaitTime.sleep(5000);
//    }
//
//    @Test
//    public void TC04(){
//        System.out.println("TC04-Verify that navigate to OrderPage");
//        HomePage homePage = new HomePage(); //tạo 1 đối tượng homepage
//        homePage.open();
//        Login loginPage = homePage.gotoLoginPage();
//        loginPage.login(Constant.USERNAME, Constant.PASSWORD);
//        WaitTime.sleep(5000);
//        OrderPage orderPage = new OrderPage();
//        orderPage.openOrder();
//    }
//
//    @Test
//    public void TC05(){
//        System.out.println("TC05-Add new order successfully with [Manual Input] OFF toggle button");
//        HomePage homePage = new HomePage();
//        homePage.open();
//        Login loginPage = homePage.gotoLoginPage();
//        loginPage.login(Constant.USERNAME, Constant.PASSWORD);
//        WaitTime.sleep(5000);
//        OrderPage orderPage = new OrderPage();
//        orderPage.openOrder();
//        AddNewOrder addNewOrder = orderPage.gotoAddOrder();
//        addNewOrder.order("developer2","Credit Card",true, true, false, false);
//        WaitTime.sleep(5000);
////        String actualResult1 = Constant.WEBDRIVER.findElement(By.xpath("(//div[@class='Toastify'])[1]")).getText();
////        Assert.assertEquals(actualResult1, "Save Data Success", "Message does not match the expected result");
//    }
//}
//
//



