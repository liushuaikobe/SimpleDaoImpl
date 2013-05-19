/**
 * 
 */
package me.liushuaikobe.simpledaoimpl.db;

import me.liushuaikobe.simpledaoimpl.db.SimpleDBHelper.OnCreateDo;
import android.content.Context;

/**
 * @author liushuai
 * 
 */
public class SimpleDBManagerFactory {
	private static SimpleDBManager dbManager;

	private static String dbName;
	private static int version = 1;
	private static OnCreateDo onCreateDo;

	public static SimpleDBManager getInstance(Context aContext) {
		if (dbManager == null) {
			if (dbName == null || dbName.equals("")) {
				// TODO Å×³öÒì³£
			}
			dbManager = new SimpleDBManager(aContext, dbName, version,
					onCreateDo);
		}
		return dbManager;
	}

	public static String getDbName() {
		return dbName;
	}

	public static void setDbName(String dbName) {
		SimpleDBManagerFactory.dbName = dbName;
	}

	public static int getVersion() {
		return version;
	}

	public static void setVersion(int version) {
		SimpleDBManagerFactory.version = version;
	}

	public static OnCreateDo getOnCreateDo() {
		return onCreateDo;
	}

	public static void setOnCreateDo(OnCreateDo onCreateDo) {
		SimpleDBManagerFactory.onCreateDo = onCreateDo;
	}
}
