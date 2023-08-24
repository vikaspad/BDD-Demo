package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

    @RunWith(Cucumber.class)
    @CucumberOptions (
            features = {"features/"},
            glue = {"Stepdefinition"},
            plugin = {"pretty", "html:target/cucumber-report.html","json:target/cucumber.json"}
//            tags = "@Run"
    )


    public class TestRunner extends AbstractTestNGCucumberTests {
        
        //type in terminal: mvn clean test -Dcucumber.options="--tags @Run"

}
