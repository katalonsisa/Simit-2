package co.com.quipux.simit2.backOffice.wsat.cargasTransito

import org.junit.runner.RunWith

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber


@RunWith(Cucumber.class)
@CucumberOptions(plugin=[],
features = ["Include/features/FuncionalidadesIndividuales/BackOffice/WebService/CargasTransito/CargaComparendos.feature"],
glue = [""],
dryRun = false,
tags = [""],
monochrome=false,
strict=true
)


public class RunnerCargaArchivos {
}
