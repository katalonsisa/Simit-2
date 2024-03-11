import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.model.FailureHandling

import co.com.quipux.simit2.homePublico.wat.runnerLiquidacion as runner
import util.Dates
import util.Direcciones as direcciones


def inicio = Dates.getTimeStampSpanish()

/*Tomamos la dirección actual del proyecto*/
def direccionActual = direcciones.getDireccionActual()

def direccionParaReporte = direcciones.getDireccionDobleSlashInvertido(direccionActual)

CucumberKW.runWithCucumberRunner(runner.class, FailureHandling.CONTINUE_ON_FAILURE)

//WebUI.openBrowser(direccionParaReporte + '\\reportecucumber\\cucumber.html\\index.html')
//
//WebUI.maximizeWindow()
//
//CustomKeywords.'util.Reportes.generarReporte'(direccionParaReporte + '\\\\reportecucumber', './reportecucumber/cucumber-statics/homepublico',
//	inicio, Dates.getTimeStampSpanish(), 'QUIPUX','home publico','liquidacion','3.0')
