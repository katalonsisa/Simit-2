package util

import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.ResultSetMetaData
import java.sql.Statement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import oracle.jdbc.driver.OracleConnection as Connection

public class ConnectionDataBase {

	private static Connection connection = null;
	/**
	 * Abre y retorna una connection a la base de datos
	 * @param dataFile absolute file path
	 * @return una instancia de java.sql.Connection
	 */
	@Keyword
	public boolean checkDB(String url, String dbname, String port, String username, String password){
		try {
			connectDB( url,  dbname,  port,  username,  password)
			closeDatabaseConnection()
			return true
		}
		catch (Exception e) {
			WebUI.comment(e.message)
			return false
		}
	}
	//Establishing a connection to the DataBase
	@Keyword
	public connectDB(String url, String dbname, String port, String username, String password){
		TimeZone timeZone = TimeZone.getTimeZone("America/Bogota");
		TimeZone.setDefault(timeZone);

		//Load driver class for your specific database type
		String conn = "jdbc:oracle:thin:@//" + url + ":" + port + "/" + dbname
		println(conn + "/" + username)
		Class.forName("oracle.jdbc.OracleDriver");
		//String connectionString = "jdbc:sqlite:" + dataFile
		if(connection != null && !connection.isClosed()){
			connection.close()
		}
		connection = DriverManager.getConnection(conn, username, password)
		return connection
	}

	/**
	 * Realiza una conexion a base de datos dados una ruta de un properties 
	 * @param modulo
	 * @return
	 */
	@Keyword
	public connectDBModul(String modulo){
		return connectDB(PropertiesManager.getStringSetting(modulo, "setting.DB.host"), PropertiesManager.getStringSetting(modulo, "setting.DB.name"),PropertiesManager.getStringSetting(modulo, "setting.DB.port") ,PropertiesManager.getStringSetting(modulo, "setting.DB.user"),PropertiesManager.getStringSetting(modulo, "setting.DB.password"))
	}

	//	/**
	//	 * Realiza una conexion a base de datos con los datos dados en el perfil
	//	 * @return
	//	 */
	//	@Keyword
	//	public connectDBProfile(){
	//		return connectDB(GlobalVariable.BDHost, GlobalVariable.BDName,GlobalVariable.BDPort,GlobalVariable.BDUser,GlobalVariable.DBPassword)
	//	}


	/**
	 * Realiza una conexion a base de datos con el usuario dado
	 * @param modulo - usuario para la conexion a la base de datos 
	 * @return
	 */
	@Keyword
	public connectDBProfileUser(String modulo){
		return connectDB(GlobalVariable.getAt(GlobalVariable.getAt(modulo).getAt("DB").toString()).getAt("Host"), GlobalVariable.getAt(GlobalVariable.getAt(modulo).getAt("DB").toString()).getAt("Name"),GlobalVariable.getAt(GlobalVariable.getAt(modulo).getAt("DB").toString()).getAt("Port"),GlobalVariable.getAt(modulo).getAt("user"),GlobalVariable.getAt(modulo).getAt("password"))
	}

	/**
	 * Ejecutamos un SQL query en la Base de Datos
	 * @param queryString SQL query string
	 * @return a reference to returned data collection, an instance of java.sql.ResultSet
	 */
	//Executing the constructed Query and Saving results in resultset
	@Keyword
	def executeQuery(String queryString) {
		Statement stm = connection.createStatement()
		ResultSet rs = stm.executeQuery(queryString)
		ResultSetMetaData metadata = rs.getMetaData()
		int columnCount = metadata.getColumnCount()
		List<List<String>> rowList = new LinkedList<List<String>>()
		while (rs.next())
		{
			List<String> row = new LinkedList<>()
			for(int i = 1; i <=columnCount; i++) {
				Object value = rs.getObject(i)
				row.add(value)
			}
			rowList.add(row)
		}
		for(List<String> row: rowList) {
			for(String data: row) { System.out.print(data + " ") }
			System.out.println()
		}
		return rowList
	}

	//Closing the connection
	@Keyword
	def closeDatabaseConnection() {
		if(connection != null && !connection.isClosed()){
			connection.close()
		}
		connection = null
	}
	/**
	 * Execute non-query (usually INSERT/UPDATE/DELETE/COUNT/SUM...) on database
	 * @param queryString a SQL statement
	 * @return single value result of SQL statement
	 */
	@Keyword
	def execute(String queryString) {
		Statement stm = connection.createStatement()
		boolean result = stm.execute(queryString)
		return result
	}
}
