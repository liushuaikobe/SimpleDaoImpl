package me.liushuaikobe.simpledaoimpl.util;

import android.util.Log;

public class LogUtil {
	public static void output_d(String tag, String msg) {
		Log.d(tag, msg);
	}

	public static void output_e(String tag, String msg) {
		Log.e(tag, msg);
	}

	public static void output_i(String tag, String msg) {
		Log.i(tag, msg);
	}
}
