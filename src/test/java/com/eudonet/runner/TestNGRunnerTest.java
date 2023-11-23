package com.eudonet.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;
import static io.cucumber.testng.CucumberOptions.SnippetType.CAMELCASE;

/**
 * The Test Runner File uses the @RunWith() Annotation from JUnit for executing
 * tests.
 */


/**
 * The @CucumberOptions Annotation is used to define the location of feature
 * files, step definitions, reporting integration.
 */

@CucumberOptions(
		 features = "src/test/resources/features",
        plugin = {"pretty", "summary", "html:target/cucumber-report/cucumber.html",
                                       "json:target/cucumber-report/cucumber.json"},
        monochrome = true,
        tags = ("@customers"),
        glue = {"com.eudonet.stepsdefinition", "com.eudonet.hooks", "com.eudonet.customtypes"},
        snippets = CAMELCASE
)
public class TestNGRunnerTest extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
