package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static driver.SingletonDriver.getInstance;

/**
 * Created by M.Malyus on 1/25/2018.
 */
public abstract class Page {
    final static public String WEB_URL = "https://volodymyr-kushnir.github.io/recipes/#/";
    final static Logger LOG = Logger.getLogger(Page.class);

    public Page(WebDriver driver) throws Exception {
        WebDriver instance = getInstance();
        PageFactory.initElements((new DefaultElementLocatorFactory(instance)),this);
    }

    public Page() throws Exception {
        this(getInstance());
    }
    public boolean isElementPresent(WebElement element) {
        try {
            element.isEnabled();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void waitForTextPresent(WebElement webelement, String text) throws InterruptedException {
        int waitRetryDelayMs = 1;
        int timeOut = 10;
        boolean first = true;

        for (int milliSecond = 0; ; milliSecond += waitRetryDelayMs) {
            if (milliSecond > timeOut * 1000) {
                System.out.println("Timeout: Text '" + text + "' is not found ");
                break;
            }

            if (webelement.getText().contains(text)) {
                if (!first) System.out.println("Text is found: '" + text + "'");
                break;
            }

            if (first) System.out.println("Waiting for text is present: '" + text + "'");

            first = false;
            Thread.sleep(waitRetryDelayMs);
        }
    }


    public WebElement fluentWaitUntilElementBecomeClickable(WebElement webelement, String text) throws Exception {
        int waitRetryDelayMs = 500;
        int timeOut = 10000;

        Wait<WebDriver> wait = new FluentWait<WebDriver>(getInstance())
                .withTimeout(timeOut, TimeUnit.MILLISECONDS)
                .pollingEvery(waitRetryDelayMs, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(webelement));
            LOG.info("Element " + text + " found");
            return webelement;

        } catch (Exception e) {
            LOG.info("Element " + text + " NOT found");
            e.printStackTrace();
            return null;
        }
    }

    public WebElement waitUntilElementBecomeClickable(WebElement webelement, String text) throws Exception {
        int timeOutInSeconds = 10;

        WebDriverWait wait = new WebDriverWait(getInstance(), timeOutInSeconds);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(webelement));
            LOG.info("Element " + text + " found");
            return webelement;

        } catch (Exception e) {
            LOG.info("Element " + text + " NOT found");
            e.printStackTrace();
            return null;
        }
    }

    public boolean IsPageLoaded(WebElement element) throws Exception {
        int waitRetryDelayMs = 500;
        int timeOut = 10000;

        Wait<WebDriver> wait = new FluentWait<WebDriver>(getInstance())
                .withTimeout(timeOut, TimeUnit.MILLISECONDS)
                .pollingEvery(waitRetryDelayMs, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            LOG.info("Page is found");
            return true;

        } catch (Exception e) {
            LOG.info("Page is NOT found");
            e.printStackTrace();
            return false;
        }
    }

    public static boolean compareList(List ls1, List ls2) {
        return ls1.toString().contentEquals(ls2.toString()) ? true : false;
    }

    public void waitWhenAttributeChange(By locator, String attribute, String value) throws Exception {
        WebDriverWait wait = new WebDriverWait(getInstance(), 5);

        wait.until(new ExpectedCondition<Boolean>() {
            private By locator;
            private String attr;
            private String initialValue;

            private ExpectedCondition<Boolean> init(By locator, String attr, String initialValue ) {
                this.locator = locator;
                this.attr = attr;
                this.initialValue = initialValue;
                return this;
            }

            public Boolean apply(WebDriver driver) {
                WebElement button = driver.findElement(this.locator);
                String enabled = button.getAttribute(this.attr);
                if(enabled.equals(this.initialValue))
                    return false;
                else
                    return true;
            }
        }.init(locator, attribute, value));
    }


}
