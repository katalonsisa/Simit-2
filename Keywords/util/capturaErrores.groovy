package util
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration

import cucumber.api.Scenario
import cucumber.api.java.Before


class capturaErrores {
	private static Scenario scenario;

	@Before
	public void beforetest(Scenario scenario){
		this.scenario = scenario

		HashMap<Object, String> chromePrefs = new HashMap<Object, String>();
		chromePrefs.put("download.default_directory", RunConfiguration.getProjectDir() + "/Reports/")
		RunConfiguration.setWebDriverPreferencesProperty("prefs", chromePrefs)
	}


	public static void capturarErrores(Exception Ex, int tipoError) {
		switch(tipoError){
			case 1:
				scenario.write("No se pudo realizar la conexión con la base de datos")
				break;

			case 2:
				String mensaje;
				int resultado;
				mensaje = Ex.message
				resultado = mensaje.indexOf("Size")
				if(resultado!=-1){
					mensaje= "Error durante la ejecución del webdriver, no se encontraron elementos"
				}
				if(Ex.getCause()!=null){
					mensaje = Ex.getCause().getMessage()
					resultado = mensaje.indexOf("not found")
					if(resultado!=-1){
						String[] parts = mensaje.split("xpath:");
						parts = parts[1].split("'")
						mensaje=Ex.message+"\n"
						mensaje+= "No se pudo encontrar el objeto por medio de este xpath "+parts[0]
					}else{
						mensaje = Ex.message
					}
				}
				scenario.write("\nDurante la ejecución del escenario se presentaron los siguientes errores: \n \n"+mensaje+"\n ------------------------------")
				break;

			default:
				break;
		}
	}
}