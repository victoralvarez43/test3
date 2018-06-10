package com.mycorp;

import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.mycorp.webdriver.NoneDriver;

import io.github.bonigarcia.wdm.BrowserManager;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import io.github.bonigarcia.wdm.OperaDriverManager;
import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import io.github.bonigarcia.wdm.VoidDriverManager;


public enum BrowserManagerEnum {

	CHROME( "chrome",() -> new ChromeDriver(), ChromeDriverManager.getInstance().version( "2.24" ) ),
    FIREFOX( "firefox", () -> new FirefoxDriver(), FirefoxDriverManager.getInstance() ),
    EDGE( "edge", () -> new EdgeDriver(), EdgeDriverManager.getInstance() ),
    IE( "ie", () -> new InternetExplorerDriver(), InternetExplorerDriverManager.getInstance() ),
    MARIONETTE( "marionette", () -> new FirefoxDriver(), FirefoxDriverManager.getInstance() ),
    OPERA( "opera", () -> new OperaDriver(), OperaDriverManager.getInstance() ),
    PHANTOMJS( "phantomjs", () -> new PhantomJSDriver(), PhantomJsDriverManager.getInstance() ),
    NONE( "test", () -> new NoneDriver(), VoidDriverManager.getInstance().version( "1" ) );

	// Browser Name
	private final String browserName;
	
	// Browser Manager
    private final BrowserManager browserManager;
    
    // Supplier to construct webDriver
    private final Supplier<WebDriver> webDriverSupplier;
    
    /**
     * Construct Browser Enum with:
     * 
     * 	- Name
     *  - Supplier WebDriver impl
     *  - BroserManager impl
     *  
     * @param browserName
     * @param webDriverSupplier
     * @param browserManager
     */
    private BrowserManagerEnum( final String browserName, final Supplier<WebDriver> webDriverSupplier, final BrowserManager browserManager ) {
        this.browserName = browserName;
        this.browserManager = browserManager;
        this.webDriverSupplier = webDriverSupplier;
    }

    /**
     * Return Browser Enum represented by its name.
     * 
     * @param browserName
     * @return
     */
    public static BrowserManagerEnum of( final String browserName ) {
        final String lBrowserName = StringUtils.lowerCase( browserName );
        for( final BrowserManagerEnum browser : BrowserManagerEnum.values() ) {
            if( browser.browserName.equals( lBrowserName ) ) {
                return browser;
            }
        }
        return NONE;
    }

    /**
     * Get Browser Manager.
     * 
     * @return
     */
    public BrowserManager getBrowserManager() {
    	return this.browserManager;
    }

    /**
     * Get Browser Manager by version.
     * 
     * @param version
     * @return
     */
    public BrowserManager getBrowserManager( final String version ) {
        return getBrowserManager().version( version );
    }

    /**
     * Get Driver.
     * 
     * @return
     */
    public WebDriver getDriver() {
        return this.webDriverSupplier.get();
    }

}
