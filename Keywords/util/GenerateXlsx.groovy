package util

import java.lang.reflect.Field

import org.apache.poi.ss.usermodel.BorderStyle
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.FillPatternType
import org.apache.poi.ss.usermodel.Font
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook

public class GenerateXlsx {



	/**
	 * Apartir de un objeto llena las celdas con los valores del objecto 
	 * @param fields </br> 
	 * @param row
	 * @param obj
	 * @return null
	 */
	private static fieldsObjectToXlsx(List<Field> fields, Row row, Object obj) {
		for(Field field: fields) {
			if (field.isAnnotationPresent(XlsColumn.class)) {
				XlsColumn xlsColumn = field.getAnnotation(XlsColumn.class)
				Cell cell = row.createCell(xlsColumn.columnNumber())
				Font bodyFont = row.getSheet().getWorkbook().createFont()
				bodyFont.setFontName("Arial")
				bodyFont.setFontHeightInPoints((short) 10)

				CellStyle bodyCellStyle = row.getSheet().getWorkbook().createCellStyle()
				bodyCellStyle.setFont(bodyFont)
				bodyCellStyle.setBorderBottom(BorderStyle.HAIR)
				bodyCellStyle.setBorderLeft(BorderStyle.HAIR)
				bodyCellStyle.setBorderRight(BorderStyle.HAIR)
				bodyCellStyle.setBorderTop(BorderStyle.HAIR)

				if (obj."${field.getName()}" != null) {
					cell.setCellValue(obj."${field.getName()}".toString())
					cell.getRow().getSheet().autoSizeColumn(cell.getColumnIndex())
					//cell.getRow().getSheet().autoSizeColumn(xlsColumn.columnNumber())
					cell.setCellStyle(bodyCellStyle)
				}
			}
		}
	}

	/**
	 * Genera un documento de tipo xlsx (excel) en le cual se almacenaran los datos en el documento 
	 * @param path </br> ruta donde se almacenara 
	 * @param fileName </br> nombre con el cual se almacenara, este sera tambien el nombre de la hoja donde se almacenara la informacion 
	 * @param listObj </br> lista de objetos con la que se construira el documento
	 * @return null 
	 */
	public static generarXlsx(String path, String fileName, List<Object> listObj) {
		if (listObj == null || listObj.size() == 0) {
			return
		}

		Workbook workbook = new XSSFWorkbook()
		Sheet sheet = workbook.createSheet(fileName)

		Font headerFont = workbook.createFont()
		headerFont.setBold(true)
		headerFont.setFontName("Arial")
		headerFont.setFontHeightInPoints((short) 10)
		headerFont.setColor(IndexedColors.WHITE.getIndex())


		CellStyle headerCellStyle = workbook.createCellStyle()
		headerCellStyle.setFont(headerFont)
		headerCellStyle.setFillBackgroundColor(IndexedColors.BLUE.getIndex())
		headerCellStyle.setFillPattern(FillPatternType.ALT_BARS)
		headerCellStyle.setBorderBottom(BorderStyle.HAIR)
		headerCellStyle.setBorderLeft(BorderStyle.HAIR)
		headerCellStyle.setBorderRight(BorderStyle.HAIR)
		headerCellStyle.setBorderTop(BorderStyle.HAIR)


		List<Field> fields = listObj.get(0).getClass().getDeclaredFields()

		//Creating column headers with text
		Row headerRow = sheet.createRow(0);
		for (Field field: fields) {
			if (field.isAnnotationPresent(XlsColumn.class)) {
				XlsColumn xlsColumn = field.getAnnotation(XlsColumn.class)
				Cell cell = headerRow.createCell(xlsColumn.columnNumber())
				cell.setCellValue(xlsColumn.headerText())
				cell.setCellStyle(headerCellStyle);
			}
		}

		for (int i = 0; i < listObj.size(); i++) {
			Row row = sheet.createRow(i + 1);
			fieldsObjectToXlsx(fields, row, listObj.get(i))
		}

		FileOutputStream fileOut = new FileOutputStream(path + fileName + '.xlsx');
		workbook.write(fileOut);
		fileOut.close();
	}

	/**
	 * Metodo para generar una lista de objectos mapeados con XlsColumn 
	 * @param path <br> ruta del documento donde esta almacenada la informacion
	 * @param clazz <br> clase del objecto 
	 * @return lista con la informacion del xlsx mapeada 
	 */
	public static <T> List<T> getObjectFromXlsx(String path, Class<T> clazz) {
		List<T> objectList = new ArrayList<T>()
		try {
			FileInputStream  file = new FileInputStream(new File(path))
			Workbook workbook = new XSSFWorkbook(file)
			Sheet sheet = workbook.getSheetAt(0)

			List<Field> fields = clazz.getDeclaredFields()
			for(int i = sheet.getFirstRowNum()+1 ; i <= sheet.getLastRowNum(); i++) {
				T newObject = clazz.getDeclaredConstructor().newInstance()
				int count = 0
				for (field in fields) {
					if (field.isAnnotationPresent(XlsColumn.class)) {
						Row row = sheet.getRow(i);
						XlsColumn xlsColumn = field.getAnnotation(XlsColumn.class)
						newObject."${field.getName()}" = row.getCell(xlsColumn.columnNumber())
						count++
					}
				}

				if (count > 0) {
					objectList.add(newObject)
				}
			}
		} catch (Exception e) {
			e.printStackTrace()
		}
		return objectList
	}
}
