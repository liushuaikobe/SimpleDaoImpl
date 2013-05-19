/**
 * 
 */
package me.liushuaikobe.simpledaoimpl.db;

import me.liushuaikobe.simpledaoimpl.util.LogUtil;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author liushuai
 * 
 */
public class SimpleDBHelper extends SQLiteOpenHelper {

	private OnCreateDo onCreateDo;

	public SimpleDBHelper(Context context, String name, CursorFactory factory,
			int version, OnCreateDo onCreateDo) {
		super(context, name, factory, version);
		this.onCreateDo = onCreateDo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite
	 * .SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		LogUtil.output_d("SimpleDBHelper", "db created...");
		onCreateDo.onDbCreatedDo(db);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite
	 * .SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public interface OnCreateDo {
		public void onDbCreatedDo(SQLiteDatabase db);
	}

}
