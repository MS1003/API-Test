package org.example;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features={"src\\test\\java\\featurePackage\\FeatureTestFile.feature"} //path for feature file
        ,glue =  "stepDef"   //Package name for Step definition class.

)

public class TestRunner {
}





