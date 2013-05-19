/**
 * 
 */
package me.liushuaikobe.simpledaoimpl.util;

/**
 * @author liushuai
 * 
 */
public class Util {
	/**
	 * get the getters' name of given field
	 * 
	 * @param FieldName
	 * @return
	 */
	public static String getSettersName(String FieldName) {
		return "set" + String.valueOf(FieldName.charAt(0)).toUpperCase()
				+ FieldName.substring(1);
	}

	/**
	 * get the getters' name of given field
	 * 
	 * @param FieldName
	 * @return
	 */
	public static String getGettersName(String FieldName) {
		return "get" + String.valueOf(FieldName.charAt(0)).toUpperCase()
				+ FieldName.substring(1);
	}
}
