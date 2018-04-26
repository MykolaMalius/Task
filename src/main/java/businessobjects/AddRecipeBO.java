package businessobjects;

import driver.SingletonDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.AddRecipePage;
import pages.HomePage;

/**
 * Created by M.Malyus on 4/26/2018.
 */
public class AddRecipeBO {
    private WebDriver driver = SingletonDriver.getInstance();
    private HomePage homePage;
    private AddRecipePage addRecipePage;

    public AddRecipeBO() throws Exception {
    }

    public HomePage createRecipe(String title,String descr, String ingr, String photoURL,String tag) throws Exception {
        homePage = new HomePage();
        addRecipePage = homePage.clickOnAddRecipeButton();
        addRecipePage.writeTitle(title);
        addRecipePage.writeDescription(descr);
        addRecipePage.writeIngredients(ingr);
        addRecipePage.writePhotoURL("https://assets.epicurious.com/photos/58486ca5c57348020906be40/6:4/w_620%2Ch_413/salted-black-licorice-caramels-120716.jpg");
        addRecipePage.writeTag(tag);
        addRecipePage.clickOnOkayButton();
        return PageFactory.initElements(driver,HomePage.class);
    }
}
