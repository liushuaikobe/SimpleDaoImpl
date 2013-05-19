package me.liushuaikobe.simpledaoimplexample;

import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Date;

import me.liushuaikobe.simpledaoimpl.db.SimpleDBHelper.OnCreateDo;
import me.liushuaikobe.simpledaoimpl.db.SimpleDBManagerFactory;
import me.liushuaikobe.simpledaoimplexample.bl.Test_table_1DAO;
import me.liushuaikobe.simpledaoimplexample.entity.Test_table_1;
import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Context mContext = MainActivity.this;
	private Button btnUpdate;
	private Test_table_1 test_table_1_1;
	private Test_table_1 test_table_1_2;
	private Test_table_1 test_table_1_3;
	private Test_table_1DAO test_table_1DAO;

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
		test_table_1DAO = new Test_table_1DAO(mContext);

		test_table_1_1 = new Test_table_1();
		test_table_1_1.setTest_character("liushuaikobe");
		test_table_1_1.setTest_text("zouliping");
		test_table_1_1.setTest_double(3.1415926535);
		test_table_1_1.setTest_timestamp(new Timestamp(System
				.currentTimeMillis()));
		test_table_1_1.setTest_integer(19910607);
		test_table_1_1.setTest_biginteger(new BigInteger("1234567890"));
		test_table_1_1.setTest_date(new Date(System.currentTimeMillis()));
		test_table_1_1.setTest_time(new Time(System.currentTimeMillis()));

		test_table_1DAO.save(test_table_1_1);
		Log.e("fuck", test_table_1_1.getTest_pk() + "");

		test_table_1_2 = new Test_table_1();
		test_table_1_2.setTest_character("liushuaikobe--1");
		test_table_1_2.setTest_text("zouliping--1");
		test_table_1_2.setTest_double(3.1415926535);
		test_table_1_2.setTest_timestamp(new Timestamp(System
				.currentTimeMillis()));
		test_table_1_2.setTest_integer(19930607);
		test_table_1_2
				.setTest_biginteger(new BigInteger("1234567890123456789"));
		test_table_1_2.setTest_date(new Date(System.currentTimeMillis()));
		test_table_1_2.setTest_time(new Time(System.currentTimeMillis()));
		test_table_1DAO.save(test_table_1_2);

		btnUpdate = (Button) findViewById(R.id.update);
		btnUpdate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				test_table_1_3 = test_table_1DAO.find(1);
				Log.e("3-->pk", test_table_1_3.getTest_pk() + " ");
				test_table_1_3.setTest_character("modify successfully");
				test_table_1_3.setTest_biginteger(new BigInteger("9876543210"));
				test_table_1DAO.update(test_table_1_3);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
