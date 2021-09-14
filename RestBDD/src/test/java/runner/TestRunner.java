package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/features", glue = "steps", tags= "@post", dryRun = false, monochrome = true, plugin = {
		"pretty", "html:target/cucumber-reports/cucumber-pretty",
		"json:target/cucumber-reports/CucumberTestReport.json",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})

public class TestRunner extends AbstractTestNGCucumberTests {

}
