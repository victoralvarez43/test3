package com.mycorp.webdriver;

import org.openqa.selenium.opera.OperaDriver;

import io.github.bonigarcia.wdm.BrowserManager;
import io.github.bonigarcia.wdm.OperaDriverManager;

/**
 * Factory to create Opera browser driver.
 * 
 * @author victor
 *
 */
public class OperaFactory implements WebDriverFactory<OperaDriver>{

	@Override
	public OperaDriver newElement() {
		return new OperaDriver();
	}

	@Override
	public BrowserManager getBrowserManager() {
		return OperaDriverManager.getInstance();
	}

}
