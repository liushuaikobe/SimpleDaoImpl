/**
 * 
 */
package me.liushuaikobe.simpledaoimplexample.entity;

import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author liushuai
 * 
 */
public class Test_table_1 {
	private int test_pk;
	private String test_character;
	private String test_text;
	private double test_double;
	private Timestamp test_timestamp;
	private Integer test_integer;
	private BigInteger test_biginteger;
	private Date test_date;
	private Time test_time;

	public int getTest_pk() {
		return test_pk;
	}

	public void setTest_pk(int test_pk) {
		this.test_pk = test_pk;
	}

	public String getTest_character() {
		return test_character;
	}

	public void setTest_character(String test_character) {
		this.test_character = test_character;
	}

	public String getTest_text() {
		return test_text;
	}

	public void setTest_text(String test_text) {
		this.test_text = test_text;
	}

	public double getTest_double() {
		return test_double;
	}

	public void setTest_double(double test_double) {
		this.test_double = test_double;
	}

	public Timestamp getTest_timestamp() {
		return test_timestamp;
	}

	public void setTest_timestamp(Timestamp test_timestamp) {
		this.test_timestamp = test_timestamp;
	}

	public Integer getTest_integer() {
		return test_integer;
	}

	public void setTest_integer(Integer test_integer) {
		this.test_integer = test_integer;
	}

	public BigInteger getTest_biginteger() {
		return test_biginteger;
	}

	public void setTest_biginteger(BigInteger test_biginteger) {
		this.test_biginteger = test_biginteger;
	}

	public Date getTest_date() {
		return test_date;
	}

	public void setTest_date(Date test_date) {
		this.test_date = test_date;
	}

	public Time getTest_time() {
		return test_time;
	}

	public void setTest_time(Time test_time) {
		this.test_time = test_time;
	}
}
