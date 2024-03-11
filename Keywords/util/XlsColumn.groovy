package util

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Retention(RetentionPolicy.RUNTIME)
public @interface XlsColumn {
	/**
	 * @return El número de la columna donde se imprimirá el valor del campo
	 */
	int columnNumber() default 0

	/**
	 * @return Texto que se imprimirá en la fila número 1 de la columna especificada
	 */
	String headerText() default ""
}
