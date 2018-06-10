package com.mycorp;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;

import com.mycorp.webdriver.*;

import io.github.bonigarcia.wdm.BrowserManager;

public enum BrowserManagerEnum {

	CHROME( "chrome", new ChromeFactory() ),
    FIREFOX( "firefox", new FirefoxFactory() ),
    EDGE( "edge", new EdgeFactory() ),
    IE( "ie", new InternetExplorerFactory() ),
    MARIONETTE( "marionette", new FirefoxFactory() ),
    OPERA( "opera", new OperaFactory() ),
    PHANTOMJS( "phantomjs", new PhantomJSFactory() ),
    NONE( "test", new NoneFactory() );

	// Browser Name
	private final String browserName;
	
    // WebDriverFactory
    private final WebDriverFactory<?> webDriverFactory;
    
    /**
     * Construct Browser Enum with:
     * 
     * 	- Name
     *  - WebDriverFactory
     *  
     * @param browserName
     * @param CreateConcreteDriver
     * @param browserManager
     */
    private BrowserManagerEnum( final String browserName, final WebDriverFactory<?> webDriverFactory ) {
        this.browserName = browserName;
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
    	return this.webDriverFactory.getBrowserManager();
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
