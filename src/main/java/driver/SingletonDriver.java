package driver;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.Page;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import static driver.DriverConstants.*;

public class SingletonDriver {
    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<WebDriver>();


    private SingletonDriver() {
    }

    public static WebDriver getInstance() throws Exception {
        if (webDriverThreadLocal.get() != null) {
            return webDriverThreadLocal.get();
        }
        if (BROWSER.equalsIgnoreCase(CHROME)) {
            setChromeDriver();
            WebDriver instance = createDriverInstance();
            webDriverThreadLocal.set(instance);
        } else if (BROWSER.equalsIgnoreCase(FIREFOX)) {
            setFirefox();
            WebDriver instance = createDriverInstance();
            webDriverThreadLocal.set(instance);
        } else if (BROWSER.equalsIgnoreCase(INTERNET_EXPLORE)) {
            setIeDriver();
            WebDriver instance = createDriverInstance();
            webDriverThreadLocal.set(instance);
        } else if (BROWSER.equalsIgnoreCase(SAFARI)) {
            setSafariBrowser();
        }
        webDriverThreadLocal.get().get(Page.WEB_URL);
        return webDriverThreadLocal.get();
    }

    public static void setChromeDriver() throws Exception {
        String osName = System.getProperty("os.name").toLowerCase();
        StringBuffer chromeBinaryPath = new StringBuffer(
                "src/main/resources/");

        if (osName.startsWith("win")) {
            chromeBinaryPath.append("drivers/chrome-win/chromedriver.exe");
        } else if (osName.startsWith("lin")) {
            chromeBinaryPath.append("drivers/chrome-lin/chromedriver");
        } else if (osName.startsWith("mac")) {
            chromeBinaryPath.append("drivers/chrome-mac/chromedriver");
        } else
            throw new Exception("Your OS is invalid for webdriver tests");

        System.setProperty("webdriver.chrome.driver",
                chromeBinaryPath.toString());

    }

    /*IE driver only for windows*/
    public static void setIeDriver() throws Exception {
        String osName = System.getProperty("os.name").toLowerCase();
        StringBuffer ieBinaryPath = new StringBuffer(
                "src/main/resources/");

        if (osName.startsWith("win")) {
            ieBinaryPath.append("ie-win/IEDriverServer.exe");
        } else
            throw new Exception("Your OS is invalid for webdriver tests");

        System.setProperty("webdriver.ie.driver",
                ieBinaryPath.toString());
    }

    /**
     * Sets the gecko driver path for specific OS.
     *
     * @throws Exception the exception
     */
    public static void setFirefox() throws Exception {
        String osName = System.getProperty("os.name").toLowerCase();
        StringBuffer firefoxBinaryPath = new StringBuffer("src/main/resources/");

        if (osName.startsWith("win")) {
            firefoxBinaryPath.append("gecko-win/geckodriver.exe");
        } else if (osName.startsWith("lin")) {
            firefoxBinaryPath.append("drivers/gecko-lin/geckodriver");
        } else if (osName.startsWith("mac")) {
            firefoxBinaryPath.append("drivers/gecko-mac/geckodriver");
        } else
            throw new Exception("Your OS is invalid for webdriver tests");
        System.setProperty("webdriver.gecko.driver", firefoxBinaryPath.toString());
    }

    public static WebDriver createDriverInstance() {
        WebDriver instance = null;
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
        if (BROWSER.equalsIgnoreCase(CHROME)) {
            instance = new ChromeDriver(dc);
        } else if (BROWSER.equalsIgnoreCase(FIREFOX)) {
            instance = new FirefoxDriver();
        } else if (BROWSER.equalsIgnoreCase(INTERNET_EXPLORE)) {
            instance = new InternetExplorerDriver();
        }
        instance.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        instance.manage().window().maximize();
        // instance.manage().deleteAllCookies();
        return instance;
    }

    public static void setSafariBrowser() throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("browser", "Safari");
        dc.setCapability("browser_version", "11.0");
        dc.setCapability("browser_version", "63.0");
        dc.setCapability("browserstack.selenium_version", "3.5.2");
        dc.setCapability("os", "OS X");
        dc.setCapability("os_version", "High Sierra");
        dc.setCapability("resolution", "1920x1080");
        dc.setCapability("browserstack.safari.enablePopups", "true");
        dc.setCapability("project", "com.IDL");
//				dc.setCapability("browserstack.local","true");
//				dc.setCapability("browserstack.debug","true");

        webDriverThreadLocal.set(new RemoteWebDriver(new URL(DriverConstants.URL), dc));

    }

    public static void quit() {
        try {
            webDriverThreadLocal.get().quit();
        } finally {
            webDriverThreadLocal.remove();
        }
    }

    public static void killDriver(){
        try{
            webDriverThreadLocal.get().close();
            Runtime.getRuntime().exec("taskkill /im chromedriver.exe /f");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}