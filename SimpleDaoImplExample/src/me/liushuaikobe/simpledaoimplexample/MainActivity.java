package me.liushuaikobe.simpledaoimplexample;

import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import me.liushuaikobe.simpledaoimpl.db.SimpleDBHelper.OnCreateDo;
import me.liushuaikobe.simpledaoimpl.db.SimpleDBManagerFactory;
import me.liushuaikobe.simpledaoimplexample.bl.Test_table_1DAO;
import me.liushuaikobe.simpledaoimplexample.entity.Test_table_1;
import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {
	private Context mContext = MainActivity.this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SimpleDBManagerFactory.setDbName("testdb");
		SimpleDBManagerFactory.setOnCreateDo(new OnCreateDo() {

			@Override
			public void onDbCreatedDo(SQLiteDatabase db) {
				db.execSQL("CREATE TABLE IF NOT EXISTS test_table_1 " + "("
						+ "test_pk integer PRIMARY KEY AUTOINCREMENT NOT NULL,"
						+ "test_character character varying(8) NOT NULL,"
						+ "test_text text(32),"
						+ "test_double DOUBLE(16,2) NULL DEFAULT 0,"
						+ "test_timestamp TIMESTAMP NULL DEFAULT NULL,"
						+ "test_integer integer NULL DEFAULT NULL,"
						+ "test_biginteger BIGINT(64) NULL DEFAULT NULL,"
						+ "test_date DATE NULL DEFAULT NULL,"
						+ "test_time TIME NULL DEFAULT NULL" + ")");
			}
		});
		Test_table_1DAO test_table_1DAO = new Test_table_1DAO(mContext);
		Test_table_1 test_table_1 = new Test_table_1();
		test_table_1.setTest_character("liushuaikobe");
		test_table_1.setTest_text("zouliping");
		test_table_1.setTest_double(3.1415926535);
		test_table_1
				.setTest_timestamp(new Timestamp(System.currentTimeMillis()));
		test_table_1.setTest_integer(19910607);
		test_table_1.setTest_biginteger(new BigInteger("1234567890"));
		test_table_1.setTest_date(new Date());
		test_table_1.setTest_time(new Time(System.currentTimeMillis()));
		test_table_1DAO.saveOrUpdate(test_table_1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
