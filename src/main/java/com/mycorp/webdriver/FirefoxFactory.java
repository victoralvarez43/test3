package com.mycorp.webdriver;

import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.BrowserManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;

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

	@Override
	public BrowserManager getBrowserManager() {
		return FirefoxDriverManager.getInstance();
	}

}
