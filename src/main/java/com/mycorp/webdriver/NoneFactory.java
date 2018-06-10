package com.mycorp.webdriver;

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

}
