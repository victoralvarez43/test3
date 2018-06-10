package com.mycorp.webdriver;

import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.BrowserManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;

/**
 * Factory to create Internet Explorer browser driver.
 * 
 * @author victor
 *
 */
public class InternetExplorerFactory implements WebDriverFactory<InternetExplorerDriver>{

	@Override
	public InternetExplorerDriver newElement() {
		return new InternetExplorerDriver();
	}

	@Override
	public BrowserManager getBrowserManager() {
		return InternetExplorerDriverManager.getInstance();
	}

}
