package com.mycorp.webdriver;

import io.github.bonigarcia.wdm.BrowserManager;
import io.github.bonigarcia.wdm.VoidDriverManager;

/**
 * Factory to create Test browser driver.
 * 
 * @author victor
 *
 */
public class NoneFactory implements WebDriverFactory<NoneDriver> {

	@Override
	public NoneDriver newElement() {
		return new NoneDriver();
	}

	@Override
	public BrowserManager getBrowserManager() {
		return VoidDriverManager.getInstance().version( "1" );
	}

}
