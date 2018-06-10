package com.mycorp.webdriver;

import org.openqa.selenium.phantomjs.PhantomJSDriver;

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

}
