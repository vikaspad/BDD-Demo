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
            plugin = {"pretty", "html:target/cucumber-reports.html"}
//            tags = "@Run"
    )


    public class TestRunner extends AbstractTestNGCucumberTests {

//    @Override
//    @DataProvider(parallel=false)
//    public Object[][] scenarios(){
//        return super.scenarios();
//    }

}
