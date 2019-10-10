package com.adactin.stepdefinition;

import com.adactin.baseclass.BaseClass;

import cucumber.api.java.en.Given;

public class StepDefinition extends BaseClass {
	@Given("^Navigate to the link$")
	public void navigate_to_the_link() throws Throwable {
		getUrl("https://adactin.com/HotelApp/");
	}
}
