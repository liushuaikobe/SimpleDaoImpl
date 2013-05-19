/**
 * 
 */
package me.liushuaikobe.simpledaoimplexample.bl;

import android.content.Context;
import me.liushuaikobe.simpledaoimpl.SimpleDaoImpl;
import me.liushuaikobe.simpledaoimplexample.entity.Test_table_1;

/**
 * @author liushuai
 * 
 */
public class Test_table_1DAO extends SimpleDaoImpl<Test_table_1, Integer> {

	public Test_table_1DAO(Context mContext) {
		super(mContext);
	}

	@Override
	public String getPKName() {
		return "test_pk";
	}

	@Override
	public String getTableName() {
		return "test_table_1";
	}

}
