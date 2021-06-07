package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/features",  glue = "steps", dryRun = false, monochrome = true, plugin = { "pretty",
				"html:target/cucumber-reports/cucumber-pretty",
				"json:target/cucumber-reports/CucumberTestReport.json" })

public class TestRunner extends AbstractTestNGCucumberTests {

}
