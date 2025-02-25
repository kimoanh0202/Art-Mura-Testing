package PageObjects.Products;
import Common.Constant;
import PageObjects.GeneralPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddProduct extends GeneralPage {
    //1. XÁC ĐỊNH LOCATOR CỦA ITEM TRÊN WEBSITE
    private final By _txtProductname = By.xpath("//input[@name='name']");
    private final By _sltCategory = By.xpath("//select[@name='category_id']");
    private final By _txtQuantity = By.xpath("//input[@name='quantity']");
    private final By _txtPrice = By.xpath("//input[@name='price']");
    private final By _sltStatus = By.xpath("//select[@name='status']");
    private final By _sltSale = By.xpath("//select[@name='sale']");
    private final By _btnOpenFileDialog = By.xpath("//a[@type='button']"); // Cập nhật phần tử button để mở hộp thoại tải lên file
    private final By _fileInput = By.xpath("//input[@type='file']"); // Cập nhật phần tử input file
    private final By _txtSaleValue = By.xpath("//input[@name='saleValue']");
    private final By _txtDescription = By.xpath("//textarea[@name='description']");
    private final By _btnSubmit = By.xpath("//button[@type='submit']");
    private final By _btnCancel = By.xpath("(//button[normalize-space()='Cancel'])[1]");

    //2. PHƯƠNG THỨC ĐỂ TRẢ VỀ WEBELEMENT
    public WebElement gettxtProductname() { return Constant.WEBDRIVER.findElement(_txtProductname);}
    public WebElement getsltCategory() { return Constant.WEBDRIVER.findElement(_sltCategory);}
    public WebElement gettxtQuantity() { return Constant.WEBDRIVER.findElement(_txtQuantity);}
    public WebElement gettxtPrice() {return Constant.WEBDRIVER.findElement(_txtPrice);}
    public WebElement getsltStatus() { return Constant.WEBDRIVER.findElement(_sltStatus);}
    public WebElement getsltSale() { return Constant.WEBDRIVER.findElement(_sltSale);}
    public WebElement getBtnOpenFileDialog() { return Constant.WEBDRIVER.findElement(_btnOpenFileDialog);} // Cập nhật phương thức trả về phần tử button mở hộp thoại tải lên file
    public WebElement getFileInput() { return Constant.WEBDRIVER.findElement(_fileInput);} // Cập nhật phương thức trả về phần tử input file
    public WebElement gettxtSaleValue() { return Constant.WEBDRIVER.findElement(_txtSaleValue);}
    public WebElement gettxtDescription() { return Constant.WEBDRIVER.findElement(_txtDescription);}
    public WebElement getBtnSubmit() { return Constant.WEBDRIVER.findElement(_btnSubmit);}
    public WebElement getBtnCancel() { return Constant.WEBDRIVER.findElement(_btnCancel);}

    //3. PHƯƠNG THỨC ĐỂ THỰC HIỆN HÀNH ĐỘNG TRÊN WEBSITE
    public void ProductForm (String productname, String category, String quantity, String price, String status, String sale, String img, String salevalue, String description){
        try {
            this.gettxtProductname().sendKeys(productname);
            Thread.sleep(1000);

            new Select(this.getsltCategory()).selectByVisibleText(category);
            Thread.sleep(1000);

            this.gettxtQuantity().sendKeys(quantity);
            Thread.sleep(1000);

            this.gettxtPrice().sendKeys(price);
            Thread.sleep(1000);

            this.getsltStatus().sendKeys(status);
            Thread.sleep(1000);

            new Select(this.getsltSale()).selectByVisibleText(sale);
            Thread.sleep(1000);

            // Nhấn nút để mở hộp thoại tải lên file

            if (img != null && !img.isEmpty()) {
                this.getBtnOpenFileDialog().click();
                Thread.sleep(1000);
                this.getFileInput().sendKeys(img);
            }
            Thread.sleep(1000);

            if (salevalue != null && !salevalue.isEmpty()) {
                this.gettxtSaleValue().sendKeys(salevalue);
            }
            Thread.sleep(1000);

            this.gettxtDescription().sendKeys(description);
            Thread.sleep(1000);

            this.getBtnSubmit().click();
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
