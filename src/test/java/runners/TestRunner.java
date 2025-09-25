package runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectPackages("features")
@ConfigurationParameter(key = "cucumber.glue", value = "stepDefinitions")
@ConfigurationParameter(key = "cucumber.plugin", value = "pretty,json:target/Reports/CucumberJsonReport.json")
//@ConfigurationParameter(key = "cucumber.filter.tags", value = "@repoFeature")

public class TestRunner {
}
