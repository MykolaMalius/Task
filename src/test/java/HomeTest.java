import driver.SingletonDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import ru.yandex.qatools.allure.annotations.TestCaseId;

import static org.testng.Assert.assertEquals;
import static testdata.TestConstants.RECIPE_NAME;

/**
 * Created by M.Malyus on 4/26/2018.
 */
public class HomeTest {
    private HomePage homePage;

    @TestCaseId("4")
    @Test
    public void checkIfFilterFieldFindByName() throws Exception {
        homePage = new HomePage();
        homePage.findRecipe(RECIPE_NAME);
        assertEquals(homePage.getSearchableRecipeName(), RECIPE_NAME,
                "Searchable recipe name is not valid");

    }
    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        SingletonDriver.quit();
    }
}
