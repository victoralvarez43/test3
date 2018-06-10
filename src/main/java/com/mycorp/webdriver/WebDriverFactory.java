package com.mycorp.webdriver;

import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.BrowserManager;

/**
 * Interface that must be implemented by all the specific factories of browsers.
 *  
 * @author victor
 *
 * @param <T>
 */
public interface WebDriverFactory<T extends WebDriver> {

	/**
	 * Create new element.
	 * 
	 * @return
	 */
	public T newElement();
	
	/**
	 * Get Browser Manager.
	 * 
	 * @return
	 */
	public BrowserManager getBrowserManager();
}
