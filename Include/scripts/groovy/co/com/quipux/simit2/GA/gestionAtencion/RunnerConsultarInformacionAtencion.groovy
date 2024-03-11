package co.com.quipux.simit2.GA.gestionAtencion

import org.junit.runner.RunWith

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber

@RunWith(Cucumber.class)
@CucumberOptions(
features = ["Include/features/FuncionalidadesIndividuales/GA/Gestion de la atencion/Consultar informacion de la atencion.feature"],
glue = [""],
dryRun = false,
tags = [""],
monochrome=false,
strict=true
)

public class RunnerConsultarInformacionAtencion {
}

