/**
 * 
 */
package me.liushuaikobe.simpledaoimpl.db;

import me.liushuaikobe.simpledaoimpl.db.SimpleDBHelper.OnCreateDo;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author liushuai
 * 
 */
public class SimpleDBManager {
	private SimpleDBHelper dbHelper;
	private SQLiteDatabase db;

	public SimpleDBManager(Context context, String dbName, int version,
			OnCreateDo onCreateDo) {
		dbHelper = new SimpleDBHelper(context, dbName, null, version,
				onCreateDo);
		db = dbHelper.getWritableDatabase();
	}

	public SQLiteDatabase getDb() {
		return db;
	}

	public void closeDb() {
		if (db != null) {
			db.close();
		}
	}
}
