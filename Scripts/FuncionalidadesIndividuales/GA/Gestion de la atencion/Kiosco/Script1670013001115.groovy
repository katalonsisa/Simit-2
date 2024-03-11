import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.model.FailureHandling

import co.com.quipux.simit2.GA.gestionAtencion.RunnerKiosco as runner

//Ejecutamos el cucumber 
CucumberKW.runWithCucumberRunner(runner.class, FailureHandling.CONTINUE_ON_FAILURE)



