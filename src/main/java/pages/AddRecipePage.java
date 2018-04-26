package pages;

import driver.SingletonDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
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
public class AddRecipePage extends Page {
    private WebDriver driver = SingletonDriver.getInstance();
    private final Wait wait = new WebDriverWait(driver,5,1000);

    @FindBy(xpath = "//input[@name='title']")
    private WebElement titleField;

    @FindBy(xpath = "//textarea[@name='description']")
    private WebElement descriptionField;

    @FindBy(xpath = "//textarea[@name='ingredients']")
    private WebElement ingredientsField;

    @FindBy(xpath = "//textarea[@name='directions']")
    private WebElement directionsField;

    @FindBy(xpath = "//input[@name='photo']")
    private WebElement photoField;

    @FindBy(xpath = "//div[6]/div/input")
    private WebElement tagInput;

    @FindBy(xpath = "//div[6]/div/div[2]")
    private WebElement addTagButton;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement okayButton;

    public AddRecipePage() throws Exception {
    }

    @Step
    public void writeTitle(String title){
        wait.until(ExpectedConditions.visibilityOf(titleField));
        titleField.sendKeys(title);
    }
    @Step
    public void writeDescription(String description){
        descriptionField.sendKeys(description);
    }
    @Step
    public void writeIngredients(String ingredients){
        ingredientsField.sendKeys(ingredients);
    }
    @Step
    public void writeDirections(String directions){
        directionsField.sendKeys(directions);
    }
    @Step
    public void writePhotoURL(String URL){
        photoField.sendKeys(URL);
    }
    @Step
    public void writeTag(String tag){
       tagInput.sendKeys(tag);
       JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;
       javascriptExecutor.executeScript("arguments[0].click();",addTagButton);
    }
    @Step
    public void clickOnOkayButton(){
        okayButton.click();
    }
}
