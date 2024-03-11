import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.model.FailureHandling as FailureHandling

import co.com.quipux.simit2.backOffice.wsat.cargasTransito.RunnerCargaArchivos as runner

CucumberKW.runWithCucumberRunner(runner.class, FailureHandling.CONTINUE_ON_FAILURE)