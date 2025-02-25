package Testcase;

import Common.Constant;
import Common.WaitTime;
import PageObjects.Login.AdminPage;
import PageObjects.Login.HomePage;
import PageObjects.Login.Login;
import PageObjects.Products.AddProduct;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class TC_Products {
    private boolean isClassRun;

    @BeforeClass
    public void beforeClass() {
        System.out.println("Pre-condition");
        isClassRun = true;
        System.setProperty("webdriver.chrome.driver", "browserDrivers/chromedriver.exe");
        if (Constant.WEBDRIVER == null) {
            Constant.WEBDRIVER = new ChromeDriver();
        }

        Constant.WEBDRIVER.manage().window().maximize();
    }
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");
        isClassRun = false;
        System.setProperty("webdriver.chrome.driver", "browserDrivers/chromedriver.exe");
        if (Constant.WEBDRIVER == null) {
            Constant.WEBDRIVER = new ChromeDriver();
        }

        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void cleanup() {
        System.out.println("Post-Condition");
//        if (Constant.WEBDRIVER != null) {
//            Constant.WEBDRIVER.quit();
//        }
    }

    @Test
    public void TC01() {
        System.out.println("TC01-User login successfully with username and password valid");
        HomePage homePage = new HomePage();
        homePage.open();
        Login loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);
        WaitTime.sleep(5000);
    }

    @Test
    public void TC02() {
        System.out.println("TC02-Verify access to AdminPage and navigation to ProductsPage successfully");
        if (!isClassRun) {
            HomePage homePage = new HomePage();
            homePage.open();
            Login loginPage = homePage.gotoLoginPage();
            loginPage.login(Constant.USERNAME, Constant.PASSWORD);
            WaitTime.sleep(5000);
        }
        AdminPage adminPage = new AdminPage();
        adminPage.openAdmin();
        Constant.WEBDRIVER.findElement(By.xpath("//a[@href='/admin/product']")).click();
        WaitTime.sleep(5000);
    }

    @Test
    public void TC03(){
        System.out.println("TC03-");
        if (!isClassRun) {
            HomePage homePage = new HomePage();
            homePage.open();
            WaitTime.sleep(2000);
            Login loginPage = homePage.gotoLoginPage();
            loginPage.login(Constant.USERNAME, Constant.PASSWORD);
            WaitTime.sleep(5000);
            AdminPage adminPage = new AdminPage();
            adminPage.openAdmin();
            WaitTime.sleep(2000);
        }

        Constant.WEBDRIVER.findElement(By.xpath("//a[@href='/admin/product']")).click();
        WaitTime.sleep(5000);
        Constant.WEBDRIVER.findElement(By.xpath("(//button[contains(@type,'button')])[1]")).click();
        WaitTime.sleep(5000);
        AddProduct addProduct = new AddProduct();
        String imgPath = "C:\\Users\\tt-ki\\Downloads\\image.png";
        addProduct.ProductForm("Dress","Fashion","20","500","Active","Not On Sale", null, null, "This is a dress");
        WaitTime.sleep(4000);
//        String alertText = Constant.WEBDRIVER.findElement(By.xpath("(//div[@role='alert'])/div[2]")).getText();
//        Assert.assertEquals(alertText, "Save Data Success", "Alert text is not as expected");
    }
}
