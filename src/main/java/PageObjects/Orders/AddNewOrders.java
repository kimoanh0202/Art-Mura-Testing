package PageObjects.Orders;

import Common.Constant;
import PageObjects.GeneralPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddNewOrders extends GeneralPage {
    //1. XÁC ĐỊNH LOCATOR CỦA ITEM TRÊN WEBSITE
    private final By _btnOrdername = By.xpath("//span[@class='check']");
    private final By _txtOrdername = By.xpath("(//input[@name='name'])[1]");
    private final By _sltUser = By.xpath("//select[@name='user_id']");
    private final By _sltPayment = By.xpath("//select[@name='payment_method']");
    private final By _addProducts = By.xpath("(//button[contains(@type,'button')])[12]");
    private final By _addCoupons = By.xpath("(//button[contains(@type,'button')])[24]");
    private final By _clearProducts = By.xpath("(//button[contains(@type,'button')])[13]");
    private final By _clearCoupons = By.xpath("(//button[contains(@type,'button')])[25]");
    private final By _btnSubmit = By.xpath("//button[@type='submit']");
    private final By _btnCancel = By.xpath("//button[@type='button']");

    //2. PHƯƠNG THỨC ĐỂ TRẢ VỀ WEBELEMENT
    public WebElement getBtnOrdername() { return Constant.WEBDRIVER.findElement(_btnOrdername);}
    public WebElement getTxtOrdername() { return Constant.WEBDRIVER.findElement(_txtOrdername);}
    public WebElement getSltCustomer() { return Constant.WEBDRIVER.findElement(_sltUser);}
    public WebElement getSltPayment() { return Constant.WEBDRIVER.findElement(_sltPayment);}
    public WebElement getAddProducts() { return Constant.WEBDRIVER.findElement(_addProducts);}
    public WebElement getAddCoupons() {return Constant.WEBDRIVER.findElement(_addCoupons);}
    public WebElement getClearProducts() { return Constant.WEBDRIVER.findElement(_clearProducts);}
    public WebElement getClearCoupons() { return Constant.WEBDRIVER.findElement(_clearCoupons);}
    public WebElement getBtnSubmit() { return Constant.WEBDRIVER.findElement(_btnSubmit);}
    public WebElement getBtnCancel() { return Constant.WEBDRIVER.findElement(_btnCancel);}

    //3. PHƯƠNG THỨC ĐỂ THỰC HIỆN HÀNH ĐỘNG TRÊN WEBSITE
    public void OrderForm (boolean clickOrdername, String ordername, String customer, String paymentMethod, boolean addProducts, boolean addCoupons, boolean clearProducts, boolean clearCoupons){
        try {
            if (clickOrdername) {
                this.getBtnOrdername().click();
                Thread.sleep(1000);
                this.getTxtOrdername().sendKeys(ordername);
                Thread.sleep(1000);
            }

            new Select(this.getSltCustomer()).selectByVisibleText(customer);
            Thread.sleep(1000);

            new Select(this.getSltPayment()).selectByVisibleText(paymentMethod);
            Thread.sleep(1000);

            if (addProducts) {
                for (int i = 0; i < 3; i++) {
                    this.getAddProducts().click();
                    Thread.sleep(1000);
                }
            }

            scrollToCoupon();

            if (addCoupons) {  this.getAddCoupons().click();}
            Thread.sleep(1000);

            if (clearProducts) { this.getClearProducts().click();}
            Thread.sleep(1000);

            if (clearCoupons) { this.getClearCoupons().click();}
            Thread.sleep(1000);

            scrollToSubmit();

            this.getBtnSubmit().click();
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void scrollToSubmit() {
         WebElement popup = Constant.WEBDRIVER.findElement(By.xpath("(//div[@class='flex-1 p-6 flex flex-col justify-center undefined'])[1]"));
         WebElement elementInPopup = popup.findElement(By.xpath("//button[@id='orderPopupSubmit']"));
         JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
         js.executeScript("arguments[0].scrollIntoView(true);", elementInPopup);
    }
    public void scrollToCoupon() {
        WebElement popup = Constant.WEBDRIVER.findElement(By.xpath("(//div[@class='flex-1 p-6 flex flex-col justify-center undefined'])[1]"));
        WebElement elementInPopup = popup.findElement(By.xpath("//button[@id='orderPopupAddCoupon']"));
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("arguments[0].scrollIntoView(true);", elementInPopup);
    }
}



