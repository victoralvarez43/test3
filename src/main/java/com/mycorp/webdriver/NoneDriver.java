package com.mycorp.webdriver;

import java.util.Map;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.Response;

/**
 * WebDriver for mock browser.
 * 
 * @author victor
 *
 */
public class NoneDriver extends RemoteWebDriver {

	public NoneDriver() {
		super(new DesiredCapabilities(BrowserType.MOCK, "mock-version", Platform.ANY));
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see RemoteWebDriver#execute(String, Map)
	 */
	@Override
	protected Response execute(final String driverCommand, final Map<String, ?> parameters) {
		return new Response();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see RemoteWebDriver#startSession(Capabilities, Capabilities)
	 */
	@Override
	protected void startSession(final Capabilities desiredCapabilities, final Capabilities requiredCapabilities) {
		setSessionId("mock");
	}

}
