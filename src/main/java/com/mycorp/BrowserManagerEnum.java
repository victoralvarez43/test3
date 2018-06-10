package com.mycorp;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.mycorp.webdriver.CreateConcreteDriver;
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

	CHROME( "chrome", new CreateConcreteDriver<ChromeDriver>(ChromeDriver.class), ChromeDriverManager.getInstance().version( "2.24" ) ),
    FIREFOX( "firefox", new CreateConcreteDriver<FirefoxDriver>(FirefoxDriver.class), FirefoxDriverManager.getInstance() ),
    EDGE( "edge", new CreateConcreteDriver<EdgeDriver>(EdgeDriver.class), EdgeDriverManager.getInstance() ),
    IE( "ie", new CreateConcreteDriver<InternetExplorerDriver>(InternetExplorerDriver.class), InternetExplorerDriverManager.getInstance() ),
    MARIONETTE( "marionette", new CreateConcreteDriver<FirefoxDriver>(FirefoxDriver.class), FirefoxDriverManager.getInstance() ),
    OPERA( "opera", new CreateConcreteDriver<OperaDriver>(OperaDriver.class), OperaDriverManager.getInstance() ),
    PHANTOMJS( "phantomjs", new CreateConcreteDriver<PhantomJSDriver>(PhantomJSDriver.class), PhantomJsDriverManager.getInstance() ),
    NONE( "test", new CreateConcreteDriver<NoneDriver>(NoneDriver.class), VoidDriverManager.getInstance().version( "1" ) );

	// Browser Name
	private final String browserName;
	
	// Browser Manager
    private final BrowserManager browserManager;
    
    // Concrete Driver by reflection
    private final CreateConcreteDriver<?> createConcreteDriver;
    
    /**
     * Construct Browser Enum with:
     * 
     * 	- Name
     *  - CreateConcreteDriver
     *  - BroserManager impl
     *  
     * @param browserName
     * @param CreateConcreteDriver
     * @param browserManager
     */
    private BrowserManagerEnum( final String browserName, final CreateConcreteDriver<?> createConcreteDriver, final BrowserManager browserManager ) {
        this.browserName = browserName;
        this.browserManager = browserManager;
        this.createConcreteDriver = createConcreteDriver;
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
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */
    public WebDriver getDriver() throws InstantiationException, IllegalAccessException {
        return this.createConcreteDriver.newElement();
    }

}
