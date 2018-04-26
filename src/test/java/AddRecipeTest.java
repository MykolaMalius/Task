import businessobjects.AddRecipeBO;
import driver.SingletonDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.AddRecipePage;
import pages.HomePage;
import ru.yandex.qatools.allure.annotations.TestCaseId;

import static org.testng.Assert.assertEquals;
import static testdata.TestConstants.*;

/**
 * Created by M.Malyus on 4/26/2018.
 */
public class AddRecipeTest {
    private HomePage homePage;

    @TestCaseId("1")
    @Test
    public void checkIfRecipeCanBeSaved() throws Exception {
        AddRecipeBO addRecipeBO = new AddRecipeBO();
        homePage = addRecipeBO.createRecipe(TITLE,DESCRIPTION, INGREDIENTS,PHOTO_URL,TAG);
        homePage.findRecipe(TITLE);
        assertEquals(homePage.getSearchableRecipeName(),TITLE,
                "Recipe is not created when all fields are with valid value");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        SingletonDriver.quit();
    }
}
