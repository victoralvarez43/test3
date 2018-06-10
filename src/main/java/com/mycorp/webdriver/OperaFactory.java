package com.mycorp.webdriver;

import org.openqa.selenium.opera.OperaDriver;

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

}
