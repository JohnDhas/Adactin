package com.adactin.runner;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.adactin.baseclass.BaseClass;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
@RunWith(Cucumber.class)
@CucumberOptions(
//		plugin= {"pretty",
//				"html:src\\test\\resource\\com\\cucumber\\reports",
//				"json:src\\test\\resource\\com\\cucumber\\reports\\cucumberproject.json",
//				"junit:src\\test\\resource\\com\\cucumber\\reports\\cucumberproject.xml"
//				},
		features = "src\\test\\java\\com\\adactin\\feature", 
		glue = "com.adactin.stepdefinition",
		monochrome=true,
		dryRun = false,
		strict=true
		)
public class TestRunner {
	public static WebDriver driver;

	@BeforeClass
	public static void driverInitialization() throws Exception {
		driver = BaseClass.browserLaunch("chrome");
	}
}
