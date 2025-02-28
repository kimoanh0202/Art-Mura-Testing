package PageObjects.Orders;

import Common.Constant;
import PageObjects.GeneralPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UpdateOrders extends GeneralPage {
    private HashMap<String, By> setMap;

    public UpdateOrders() {
        setMap = new HashMap<>();
        setMap.put("btnOrdername", By.xpath("//span[@class='check']"));
        setMap.put("txtOrdername", By.xpath("(//input[@name='name'])[1]"));
        setMap.put("sltUser", By.xpath("//select[@name='user id']"));
        setMap.put("sltPayment", By.xpath("//select[@name='payment_method']"));
        setMap.put("addProducts", By.xpath("(//button[contains(@type,'button')])[12]"));
        setMap.put("addCoupons", By.xpath("(//button[contains(@type,'button')])[24]"));
        setMap.put("clearProducts", By.xpath("(//button[contains(@type,'button')])[13]"));
        setMap.put("clearCoupons", By.xpath("(//button[contains(@type,'button')])[25]"));
        setMap.put("btnSubmit", By.xpath("//button[@type='submit']"));
        setMap.put("btnCancel", By.xpath("//button[@type='button']"));
    }

    public WebElement getElementBySetType(String type) {
        return Constant.WEBDRIVER.findElement(setMap.get(type));
    }

    public HashMap<String, String> updateOrder(OrderFormUpdate builder) {
        return OrderForm(builder);
    }

    public HashMap<String, String> OrderForm(OrderFormUpdate builder) {
        try {
            HashMap<String, String> orderData = new HashMap<>();

            if (builder.clickOrdername) {
                this.getElementBySetType("btnOrdername").click();
                this.getElementBySetType("txtOrdername").clear();
                this.getElementBySetType("txtOrdername").sendKeys(builder.ordername);
                Thread.sleep(1000);
            } else {
                this.getElementBySetType("txtOrdername").clear();
                this.getElementBySetType("txtOrdername").sendKeys(builder.ordername);
                Thread.sleep(1000);
            }

            Select sltUser = new Select(this.getElementBySetType("sltUser"));
            if (builder.customer != null && !builder.customer.isEmpty()) {
                sltUser.selectByVisibleText(builder.customer);
            }
            orderData.put("UserName", sltUser.getFirstSelectedOption().getText());
            Thread.sleep(1000);

            Select sltPayment = new Select(this.getElementBySetType("sltPayment"));
            if (builder.paymentMethod != null && !builder.paymentMethod.isEmpty()) {
                sltPayment.selectByVisibleText(builder.paymentMethod);
            }
            orderData.put("PaymentMethod", sltPayment.getFirstSelectedOption().getText());
            Thread.sleep(1000);

            if (builder.clearProducts) {
                this.getElementBySetType("clearProducts").click();
                Thread.sleep(1000);
            }

            if (builder.addProducts) {
                for (int i = 0; i < builder.addCount; i++) {
                    this.getElementBySetType("addProducts").click();
                    Thread.sleep(1000);
                }
            }

            this.getElementBySetType("btnSubmit").click();
            Thread.sleep(1000);
            return orderData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static class OrderFormUpdate {
        private boolean clickOrdername;
        private String ordername;
        private String customer;
        private String paymentMethod;
        public int addCount;
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
    }
}
