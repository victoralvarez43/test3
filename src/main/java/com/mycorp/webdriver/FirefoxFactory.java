package com.mycorp.webdriver;

import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Factory to create Firefox browser driver.
 * 
 * @author victor
 *
 */
public class FirefoxFactory implements WebDriverFactory<FirefoxDriver>{

	@Override
	public FirefoxDriver newElement() {
		return new FirefoxDriver();
	}

}
