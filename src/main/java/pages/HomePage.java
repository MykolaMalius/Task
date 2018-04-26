package pages;

import driver.SingletonDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

/**
 * Created by M.Malyus on 4/26/2018.
 */
public class HomePage extends Page {
    private WebDriver driver = SingletonDriver.getInstance();
    private final Wait wait = new WebDriverWait(driver,5,1000);
    final static Logger LOG = Logger.getLogger(HomePage.class);

    @FindBy(xpath = "//i[@class='plus icon']")
    private WebElement addRecipeButton;

    @FindBy(xpath = "//div[@class='right menu']//input")
    private WebElement filterField;

    @FindAll(@FindBy(xpath = "//a[@class='header']"))
    private List<WebElement> listOfRecipeNames;

    @FindBy(xpath = "//div[1]/div/div[2]/a")
    private WebElement firstRecipeButton;

    public HomePage() throws Exception {
    }

    @Step
    public AddRecipePage clickOnAddRecipeButton(){
        addRecipeButton.click();
        return PageFactory.initElements(driver,AddRecipePage.class);
    }
    @Step
    public void findRecipe(String recipe){
        filterField.sendKeys(recipe);
    }
    @Step
    public String getSearchableRecipeName() throws InterruptedException {
        if(listOfRecipeNames.size()==0){
            return "There are no such results";
        }
        return listOfRecipeNames.get(0).getText();
    }
    @Step
    public EditPage clickOnFirstRecipeButton(){
        firstRecipeButton.click();
        return PageFactory.initElements(driver,EditPage.class);
    }

}
