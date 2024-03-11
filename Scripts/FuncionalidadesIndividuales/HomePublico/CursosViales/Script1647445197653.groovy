import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.model.FailureHandling

import co.com.quipux.simit2.homePublico.wat.runnerCursosViales as runner


/*Tomamos la dirección actual del proyecto*/

CucumberKW.runWithCucumberRunner(runner.class, FailureHandling.CONTINUE_ON_FAILURE)

