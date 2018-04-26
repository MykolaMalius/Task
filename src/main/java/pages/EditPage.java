package pages;

import driver.SingletonDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by M.Malyus on 4/26/2018.
 */
public class EditPage extends Page {
    private WebDriver driver = SingletonDriver.getInstance();
    private final Wait wait = new WebDriverWait(driver,5,1000);

    @FindBy(xpath = "//div[@class='menu right']//a[2]")
    private WebElement editButton;

    @FindBy(xpath = "//input[@name='title']")
    private WebElement nameField;

    @FindBy(xpath = "//button[@class='ui basic fluid button']")
    private WebElement saveAsNewVersionButton;

    public EditPage() throws Exception {
    }

    @Step
    public void clickOnEditButton(){
        wait.until(ExpectedConditions.visibilityOf(editButton));
        editButton.click();
    }
    @Step
    public void writeRecipeName(String name){
        nameField.clear();
        nameField.sendKeys(name);
    }
    @Step
    public void clickOnSaveButton(){
        saveAsNewVersionButton.click();
    }
    @Step
    public String getNameFieldValue(){
        return nameField.getAttribute("value");
    }
}
