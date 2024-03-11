package co.com.quipux.simit2.homePublico.wat

import org.junit.runner.RunWith

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber


@RunWith(Cucumber.class)
@CucumberOptions(plugin=["pretty" ,
	"html:reportecucumber/cucumber.html" ,
	"json:reportecucumber/cucumber.json",
	"junit:reportecucumber/cucumber.xml"],
features = ["Include/features/FuncionalidadesIndividuales/HomePublico/pazYSalvo.feature"],
glue = [""],
dryRun = false,
tags = ["@PazYSalvo"],
monochrome=false,
strict=true
)

public class runnerPazYSalvo {
}
