import driver.SingletonDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.EditPage;
import pages.HomePage;
import ru.yandex.qatools.allure.annotations.TestCaseId;

import static org.testng.Assert.assertEquals;
import static testdata.TestConstants.NAME;

/**
 * Created by M.Malyus on 4/26/2018.
 */
public class EditTest {
    private HomePage homePage;
    private EditPage editPage;

    @TestCaseId("5")
    @Test
    public void checkIfAfterEditingRecipeNameChangesAreSaved() throws Exception {
        homePage = new HomePage();
        editPage = homePage.clickOnFirstRecipeButton();
        editPage.clickOnEditButton();
        editPage.writeRecipeName(NAME);
        editPage.clickOnSaveButton();
        assertEquals(editPage.getNameFieldValue(), NAME,
                "Recipe name is not saved after editing");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        SingletonDriver.quit();
    }
}
