package com.mycorp.webdriver;

import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Factory to create Chrome browser driver.
 * 
 * @author victor
 *
 */
public class ChromeFactory implements WebDriverFactory<ChromeDriver> {

	@Override
	public ChromeDriver newElement() {
		return new ChromeDriver();
	}

}
