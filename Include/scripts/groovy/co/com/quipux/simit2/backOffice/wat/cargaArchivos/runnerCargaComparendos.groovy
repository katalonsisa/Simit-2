package co.com.quipux.simit2.backOffice.wat.cargaArchivos

import org.junit.runner.RunWith

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber


@RunWith(Cucumber.class)
@CucumberOptions(plugin=["pretty" ,
	"html:reportecucumber/cucumber.html" ,
	"json:reportecucumber/cucumber.json",
	"junit:reportecucumber/cucumber.xml"],
features = ["Include/features/FuncionalidadesIndividuales/BackOffice/WebService/CargasTransito/CargaComparendos.feature"],
glue = [""],
dryRun = false,
tags = ["@CargaArchivos"],
monochrome=false,
strict=true
)
public class runnerCargaComparendos {
}
