package co.com.quipux.simit2.homePublico.wat

import org.junit.runner.RunWith

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber


@RunWith(Cucumber.class)
@CucumberOptions(plugin=["pretty" ,
	"html:reportecucumber/cucumber.html" ,
	"json:reportecucumber/cucumber.json",
	"junit:reportecucumber/cucumber.xml"],
features = ["Include/features/FuncionalidadesIndividuales/HomePublico/EstadoCuenta.feature"],
glue = [""],
dryRun = false,
tags = ["@EstadoCuenta"],
monochrome=false,
strict=true
)

public class runnerEstadoCuenta {
}
