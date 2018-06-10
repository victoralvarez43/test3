package com.mycorp;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;

import com.mycorp.webdriver.*;

import io.github.bonigarcia.wdm.BrowserManager;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import io.github.bonigarcia.wdm.OperaDriverManager;
import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import io.github.bonigarcia.wdm.VoidDriverManager;


public enum BrowserManagerEnum {

	CHROME( "chrome", new ChromeFactory(), ChromeDriverManager.getInstance().version( "2.24" ) ),
    FIREFOX( "firefox", new FirefoxFactory(), FirefoxDriverManager.getInstance() ),
    EDGE( "edge", new EdgeFactory(), EdgeDriverManager.getInstance() ),
    IE( "ie", new InternetExplorerFactory(), InternetExplorerDriverManager.getInstance() ),
    MARIONETTE( "marionette", new FirefoxFactory(), FirefoxDriverManager.getInstance() ),
    OPERA( "opera", new OperaFactory(), OperaDriverManager.getInstance() ),
    PHANTOMJS( "phantomjs", new PhantomJSFactory(), PhantomJsDriverManager.getInstance() ),
    NONE( "test", new NoneFactory(), VoidDriverManager.getInstance().version( "1" ) );

	// Browser Name
	private final String browserName;
	
	// Browser Manager
    private final BrowserManager browserManager;
    
    // WebDriverFactory
    private final WebDriverFactory<?> webDriverFactory;
    
    /**
     * Construct Browser Enum with:
     * 
     * 	- Name
     *  - WebDriverFactory
     *  - BroserManager impl
     *  
     * @param browserName
     * @param CreateConcreteDriver
     * @param browserManager
     */
    private BrowserManagerEnum( final String browserName, final WebDriverFactory<?> webDriverFactory, final BrowserManager browserManager ) {
        this.browserName = browserName;
        this.browserManager = browserManager;
        this.webDriverFactory = webDriverFactory;
    }

    /**
     * Return Browser Enum represented by its browser name.
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
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */
    public WebDriver getDriver() throws InstantiationException, IllegalAccessException {
        return this.webDriverFactory.newElement();
    }

}
