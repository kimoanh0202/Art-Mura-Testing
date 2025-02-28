package PageObjects.Orders;
import Common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class AddNewOrders {
    private HashMap<String, By> setMap; //khai bao bien
    public AddNewOrders(){
        setMap = new HashMap<>(); //khoi tao
        setMap.put("btnOrdername", By.xpath("//span[@class='check']"));
        setMap.put("txtOrdername", By.xpath("(//input[@name='name'])[1]"));
        setMap.put("sltUser", By.xpath("//select[@name='user_id']"));
        setMap.put("sltPayment",
                By.xpath("//select[@name='payment_method']"));
        setMap.put("addProducts",
                By.xpath("(//button[contains(@type,'button')])[12]"));
        setMap.put("addCoupons", By.id("orderPopupAddCoupon"));
        setMap.put("clearProducts",
                By.xpath("(//button[contains(@type,'button')])[13]"));
        setMap.put("clearCoupons",
                By.xpath("(//button[contains(@type,'button')])[25]"));
        setMap.put("btnSubmit", By.xpath("//button[@type='submit']"));
        setMap.put("btnCancel", By.xpath("//button[@type='button']"));
    }
    public WebElement getElementBySetType(String key) {
        try {
            return Constant.WEBDRIVER.findElement(setMap.get(key));
        } catch (Exception ex) {
            return null;
        }
    }
    public HashMap<String, String> OrderForm (boolean clickOrdername, String
                                                      ordername, String customer, String paymentMethod, boolean addProducts,
                                              boolean addCoupons, boolean clearProducts, boolean clearCoupons, int
                                                      productCount){
        try {
            HashMap<String, String> orderData = new HashMap<>();
            if (clickOrdername) {this.getElementBySetType("btnOrdername").click();
                Thread.sleep(1000);
                this.getElementBySetType("txtOrdername").sendKeys(ordername);
                Thread.sleep(1000);
            }
            Select sltUser = new Select(this.getElementBySetType("sltUser"));
            sltUser.selectByVisibleText(customer);
            String userName = sltUser.getFirstSelectedOption().getText();
            orderData.put("UserName", userName);
            Thread.sleep(1000);
            Select sltPayment = new
                    Select(this.getElementBySetType("sltPayment"));
            sltPayment.selectByVisibleText(paymentMethod);
            String PaymentMethod =
                    sltPayment.getFirstSelectedOption().getText();
            orderData.put("PaymentMethod", PaymentMethod);
            Thread.sleep(1000);
            List<String> ListProduct = new ArrayList<String>();
            float totalPrice = 0;
            if (addProducts) {
                for (int i = 0; i < productCount; i++) {
                    this.getElementBySetType("addProducts").click();
                    Thread.sleep(1000);
                    String ProductName = new
                            Select(Constant.WEBDRIVER.findElement(By.xpath("(//select[@id='product" + i +
                            "'])[1]"))).getFirstSelectedOption().getText();
                    String ProductQuantity =
                            Constant.WEBDRIVER.findElement(By.xpath("(//input[@id='quantityProduct" + i +
                                    "'])[1]")).getAttribute("value");
                    String price =
                            Constant.WEBDRIVER.findElement(By.xpath("//*[@id=\"price" + i +
                                    "\"]/b")).getText();
                    totalPrice +=
                            Float.parseFloat(price.replace("¥","").replace(" ",""));
                    ListProduct.add(ProductName + " x " + ProductQuantity);
                }
            }
            orderData.put("totalPrice", String.format("%.1f", totalPrice));
            String orderProductData = "";
            for (int i=0; i<ListProduct.size();i++) {
                orderProductData += ListProduct.get(i);
                if (i < ListProduct.size() - 1) {
                    orderProductData += "\n";
                }
            }
            orderData.put("ListProduct", orderProductData);
            scrollToCoupon();
            if (addCoupons) {
                WebElement addCouponButton =
                        this.getElementBySetType("addCoupons");
                if (addCouponButton!=null) {
                    addCouponButton.click();
                } else {
                    System.out.println("No coupon");
                }
            }
            Thread.sleep(1000);
            if (clearProducts) {
                this.getElementBySetType("clearProducts").click();}
            Thread.sleep(1000);
            if (clearCoupons) {
                this.getElementBySetType("clearCoupons").click();}
            Thread.sleep(1000);
            scrollToSubmit();
            orderData.put("OrderName",
                    Constant.WEBDRIVER.findElement(By.xpath("(//input[@id='order_name'])[1]")).getAttribute("value"));
            String actualPrice =
                    Constant.WEBDRIVER.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div/section/div[2]/div/div[2]/div[2]/div/div[2]/form/div[5]/div/div")).getText();
                            actualPrice = String.format("%.1f",
                                    Float.parseFloat(actualPrice.replace("¥","").replace(" ","")));
            orderData.put("actualPrice", actualPrice);
            Thread.sleep(1000);
            this.getElementBySetType("btnSubmit").click();
            Thread.sleep(1000);
            return orderData;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void scrollToSubmit() {
        WebElement popup =
                Constant.WEBDRIVER.findElement(By.xpath("(//div[@class='flex-1 p-6 flex flexcol justify-center undefined'])[1]"));
        WebElement elementInPopup =
                popup.findElement(By.xpath("//button[@id='orderPopupSubmit']"));
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("arguments[0].scrollIntoView(true);",
                elementInPopup);
    }
    public void scrollToCoupon() {
        try {
            WebElement popup =
                    Constant.WEBDRIVER.findElement(By.xpath("(//div[@class='flex-1 p-6 flex flexcol justify-center undefined'])[1]"));
            WebElement elementInPopup =
                    popup.findElement(By.xpath("//button[@id='orderPopupAddCoupon']"));
            JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
            js.executeScript("arguments[0].scrollIntoView(true);",
                    elementInPopup);
        } catch (Exception ex){
        }
    }
    }