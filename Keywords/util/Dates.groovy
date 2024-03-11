package util

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class Dates {

	@Keyword
	public static String getFechaActual(int dia) {
		Date ahora = new Date()+dia;
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		return formateador.format(ahora);
	}

	@Keyword
	public static String getTimeStamp(String format) {
		Date ahora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat(format);
		return formateador.format(ahora);
	}

	@Keyword
	public static String getTimeStampSpanish() {
		Date ahora = new Date();
		Locale spanishLocale=new Locale("es", "ES");
		SimpleDateFormat formateador = new SimpleDateFormat("EEEE d 'de' MMMM 'de' yyyy HH:mm:ss",spanishLocale );
		//String dateInSpanish=ahora.format(DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy",spanishLocale));
		return formateador.format(ahora)
	}

	@Keyword
	public static String convertirHora(String arg) {
		// (ArrayList<String> args)
		//Input date in String format
		def hora = arg
		String input = this.getFechaActual(0)+' '+ hora + ':00'//"15/02/2014 22:22:12";
		//Date/time pattern of input date
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		//Date/time pattern of desired output date
		DateFormat outputformat = new SimpleDateFormat("hh:mm aa");//("yyyy-MM-dd hh:mm:ss aa");
		Date date = null;
		String output = null;
		//String razonSocial= args(0);
		try{
			//Conversion of input String to date
			date= df.parse(input);
			//old date format to new date format
			output = outputformat.format(date).toLowerCase();
			//System.out.println(output);
			WebUI.comment(output)
			//def  List a
			//a.add(razonSocial)
			//a.add(output)
			return output
		}catch(ParseException pe){
			pe.printStackTrace();
		}
	}

	@Keyword
	public static String fechaRandom(){

		String fecha, dia, mes;
		int diaRandom = Math.random()*31 + 1
		int mesRandom = Math.random()*12 + 1
		int año = Math.random()*(2020 - 1990 +1)+1990

		dia = diaRandom
		mes = mesRandom

		if(diaRandom < 10){

			dia = "0" + dia
		}

		if(mesRandom < 10){

			mes = "0" + mes

		}

		fecha = dia + "/" + mes + "/" + año
		return fecha;
	}
	@Keyword
	public static String dateRank(int rango){
		//formato para las fecchas "dd/MM/yyyy"
		SimpleDateFormat formar = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
		Calendar tiempo = Calendar.getInstance();
		//se busca una fecha con el rango ingresado
		tiempo.add(Calendar.DATE, rango);
		Date date = tiempo.getTime();
		return formar.format(date);

	}
	public static String fullDate(){
		String fechaCompleta = "";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
		Calendar calendar = Calendar.getInstance();
		fechaCompleta = simpleDateFormat.format(calendar.getTime());
		return fechaCompleta
	}
}
