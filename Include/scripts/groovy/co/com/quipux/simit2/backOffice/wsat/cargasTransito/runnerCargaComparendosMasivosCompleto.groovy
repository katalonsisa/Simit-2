package co.com.quipux.simit2.backOffice.wsat.cargasTransito

import org.junit.runner.RunWith

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber


@RunWith(Cucumber.class)
@CucumberOptions(plugin=["pretty" ,
	"html:reportecucumber/cucumber.html" ,
	"json:reportecucumber/cucumber.json",
	"junit:reportecucumber/cucumber.xml"],
features = ["Include/features/FuncionalidadesCompletas/cargasComparendos/cargaComparendosMasivoCompleto.feature"],
glue = [""],
dryRun = false,
tags = ["@CargasComparendosCompleto"],
monochrome=false,
strict=true
)
public class runnerCargaComparendosMasivosCompleto {
}
