package com.mycorp.webdriver;

import org.openqa.selenium.WebDriver;

public class CreateConcreteDriver<T extends WebDriver> {

	// declare the class instance
	private Class<T> tClass;

	public CreateConcreteDriver(Class<T> tClass) {
		this.tClass = tClass;
	}

	/**
	 * Create new concrete WebDriver element.
	 * 
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public T newElement() throws InstantiationException, IllegalAccessException {
		return tClass.newInstance();
	}

}
