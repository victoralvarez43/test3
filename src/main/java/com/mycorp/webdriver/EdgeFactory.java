package com.mycorp.webdriver;

import org.openqa.selenium.edge.EdgeDriver;

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

}
