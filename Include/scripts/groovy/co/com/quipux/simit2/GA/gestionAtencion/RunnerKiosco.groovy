package co.com.quipux.simit2.GA.gestionAtencion

import org.junit.runner.RunWith

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber

@RunWith(Cucumber.class)
@CucumberOptions(
features = ["Include/features/FuncionalidadesIndividuales/GA/Gestion de la atencion/Kiosco.feature"],
glue = [""],
dryRun = false,
tags = ["@Kiosco"],
monochrome=false,
strict=true
)

public class RunnerKiosco {
}
