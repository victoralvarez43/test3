package com.mycorp.webdriver;

import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.BrowserManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;

/**
 * Factory to create Edge browser driver.
 * 
 * @author victor
 *
 */
public class EdgeFactory implements WebDriverFactory<EdgeDriver> {

	@Override
	public EdgeDriver newElement() {
		return new EdgeDriver();
	}

	@Override
	public BrowserManager getBrowserManager() {
		return EdgeDriverManager.getInstance();
	}

}
