package com.mycorp.webdriver;

import org.openqa.selenium.ie.InternetExplorerDriver;

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

}
