package PageObjects.Orders;

import Common.Constant;
import PageObjects.GeneralPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class UpdateOrders extends GeneralPage {
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

    public WebElement getBtnOrdername() {
        return Constant.WEBDRIVER.findElement(_btnOrdername);
    }

    public WebElement getTxtOrdername() {
        return Constant.WEBDRIVER.findElement(_txtOrdername);
    }

    public WebElement getSltCustomer() {
        return Constant.WEBDRIVER.findElement(_sltUser);
    }

    public WebElement getSltPayment() {
        return Constant.WEBDRIVER.findElement(_sltPayment);
    }

    public WebElement getAddProducts() {
        return Constant.WEBDRIVER.findElement(_addProducts);
    }

    public WebElement getAddCoupons() {
        return Constant.WEBDRIVER.findElement(_addCoupons);
    }

    public WebElement getClearProducts() {
        return Constant.WEBDRIVER.findElement(_clearProducts);
    }

    public WebElement getClearCoupons() {
        return Constant.WEBDRIVER.findElement(_clearCoupons);
    }

    public WebElement getBtnSubmit() {
        return Constant.WEBDRIVER.findElement(_btnSubmit);
    }

    public WebElement getBtnCancel() {
        return Constant.WEBDRIVER.findElement(_btnCancel);
    }

    public void OrderForm(OrderFormUpdate builder) {
        try {
            //Nếu là true, click vào btnOrdername, sau đó clear và nhập ordername.
            //Nếu là false, chỉ clear và nhập ordername mà không click vào btnOrdername.
            if (builder.clickOrdername) {
                this.getBtnOrdername().click();
//                Thread.sleep(1000);
                this.getTxtOrdername().clear();
                this.getTxtOrdername().sendKeys(builder.ordername);
                Thread.sleep(1000);
            } else {
                this.getTxtOrdername().clear();
                this.getTxtOrdername().sendKeys(builder.ordername);
                Thread.sleep(1000);
            }

            if (builder.customer != null && !builder.customer.isEmpty()) {
                new Select(this.getSltCustomer()).selectByVisibleText(builder.customer);
                Thread.sleep(1000);
            }

            if (builder.paymentMethod != null && !builder.paymentMethod.isEmpty()) {
                new Select(this.getSltPayment()).selectByVisibleText(builder.paymentMethod);
                Thread.sleep(1000);
            }

            if (builder.addProducts) {
                this.getAddProducts().click();
                Thread.sleep(1000);
            }

            scrollToCoupon();

            if (builder.addCoupons) {
                this.getAddCoupons().click();
                Thread.sleep(1000);
            }

            if (builder.clearProducts) {
                this.getClearProducts().click();
                Thread.sleep(1000);
            }

            if (builder.clearCoupons) {
                this.getClearCoupons().click();
                Thread.sleep(1000);
            }

            scrollToSubmit();

            this.getBtnSubmit().click();
            Thread.sleep(1000);

        } catch (Exception e) {
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

    public static class OrderFormUpdate {
        private boolean clickOrdername;
        private String ordername;
        private String customer;
        private String paymentMethod;
        private boolean addProducts;
        private boolean addCoupons;
        private boolean clearProducts;
        private boolean clearCoupons;

        public OrderFormUpdate setClickOrdername(boolean clickOrdername) {
            this.clickOrdername = clickOrdername;
            return this;
        }

        public OrderFormUpdate setOrdername(String ordername) {
            this.ordername = ordername;
            return this;
        }

        public OrderFormUpdate setCustomer(String customer) {
            this.customer = customer;
            return this;
        }

        public OrderFormUpdate setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public OrderFormUpdate setAddProducts(boolean addProducts) {
            this.addProducts = addProducts;
            return this;
        }

        public OrderFormUpdate setAddCoupons(boolean addCoupons) {
            this.addCoupons = addCoupons;
            return this;
        }

        public OrderFormUpdate setClearProducts(boolean clearProducts) {
            this.clearProducts = clearProducts;
            return this;
        }

        public OrderFormUpdate setClearCoupons(boolean clearCoupons) {
            this.clearCoupons = clearCoupons;
            return this;
        }
    }

    public void Update(OrderFormUpdate builder) {
        try {
            this.OrderForm(builder);
            this.getBtnSubmit().click();
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



