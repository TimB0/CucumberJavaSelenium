package common.drivers;

import com.google.gson.JsonObject;
import common.config.PropertyLoader;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;

import java.io.File;
import java.util.Objects;

public class SingletonDriver {

    private SingletonDriver() {
    }

    private static final PropertyLoader PROPERTY_LOADER = PropertyLoader.getPropertyLoader();

    private static WebDriver driver;

    private static WebDriver getWebDriver(BrowserType type, boolean proxyEnabled) {
        if (driver == null) {
            Proxy proxy = null;
            if (proxyEnabled) {
                System.out.println("PROXY ENABLED!!");
                proxy = getProxySettings();
            }
            switch (type) {
                case FIREFOX: {
                    System.setProperty("webdriver.gecko.driver", "gecko/geckodriver_x64.exe");
                    FirefoxOptions options = new FirefoxOptions();
                    if (Objects.nonNull(proxy)) {
                        //Below line is for setting proxy
                        options.setProxy(proxy);
                    }
                    options.setAcceptInsecureCerts(true);
                    //for disabling JS - adding extension that blocks js - A WORKAROUND
                    FirefoxProfile profile = new FirefoxProfile();
                    profile.addExtension(new File("no-script/noscript.xpi"));
                    options.setProfile(profile);
                    driver = new FirefoxDriver(options);
                    break;
                }
                case CHROME: {
                    System.setProperty("webdriver.chrome.driver", "chrome/chromedriver.exe");
                    ChromeOptions co = new ChromeOptions();
                    //Proxy doesn't work for chrome, as it reads from IE settings
                    driver = new ChromeDriver(co);
                    break;
                }
            }
        }
        return driver;
    }

    /**
     * Creates, if not exists, else returne existing instance of WebDriver.
     * The browser is set in properties file.
     * proxy settings are also set in property file;
     * proxy only works with firefox;
     *
     * @return - Singleton instance of WebDriver
     */
    public static WebDriver getDriver() {
        String browserName = PROPERTY_LOADER.getProperty("browser", BrowserType.FIREFOX.toString());
        //assuming name is coming as correct
        BrowserType type = BrowserType.fromName(browserName);
        boolean proxyEnabled = Boolean.valueOf(PROPERTY_LOADER.getProperty("proxy.enabled", "FALSE"));
        return getWebDriver(type, proxyEnabled);
    }

    private static Proxy getProxySettings() {
        String proxyPath = PROPERTY_LOADER.getProperty("proxy.path", "localhost:11111");
        Proxy proxy = new Proxy();
        proxy.setProxyType(Proxy.ProxyType.MANUAL);
        proxy.setHttpProxy(proxyPath);
        proxy.setFtpProxy(proxyPath);
        proxy.setSslProxy(proxyPath);
        System.out.println("proxy - json = " + proxy.toJson());
        return proxy;
    }

    //you can do it through json as well
    private static JsonObject getJsonObjectForProxy() {
        JsonObject object = new JsonObject();
        object.addProperty("proxyType", "manual");
        object.addProperty("httpProxy", "localhost:11111");
        object.addProperty("ftpProxy", "localhost:11111");
        object.addProperty("sslProxy", "localhost:11111");
        return object;
    }

}
