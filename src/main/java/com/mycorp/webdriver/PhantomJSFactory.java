package com.mycorp.webdriver;

import org.openqa.selenium.phantomjs.PhantomJSDriver;

import io.github.bonigarcia.wdm.BrowserManager;
import io.github.bonigarcia.wdm.PhantomJsDriverManager;

/**
 * Factory to create PhantomJS browser driver.
 * 
 * @author victor
 *
 */
public class PhantomJSFactory implements WebDriverFactory<PhantomJSDriver>{

	@Override
	public PhantomJSDriver newElement() {
		return new PhantomJSDriver();
	}

	@Override
	public BrowserManager getBrowserManager() {
		return PhantomJsDriverManager.getInstance();
	}

}
