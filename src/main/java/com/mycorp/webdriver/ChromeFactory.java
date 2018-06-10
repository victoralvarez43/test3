package com.mycorp.webdriver;

import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.BrowserManager;
import io.github.bonigarcia.wdm.ChromeDriverManager;

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

	@Override
	public BrowserManager getBrowserManager() {
		return ChromeDriverManager.getInstance().version( "2.24" );
	}

}
