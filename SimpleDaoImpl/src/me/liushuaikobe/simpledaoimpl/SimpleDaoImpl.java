/**
 * 
 */
package me.liushuaikobe.simpledaoimpl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;

import me.liushuaikobe.simpledaoimpl.db.SimpleDBManager;
import me.liushuaikobe.simpledaoimpl.db.SimpleDBManagerFactory;
import me.liushuaikobe.simpledaoimpl.util.Util;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * 
 * @author liushuai
 * 
 * @param <T>
 *            ʵ������
 * @param <K>
 *            ��������
 */
public abstract class SimpleDaoImpl<T, K> {
	private String PK_name;
	private String TABLE_name;
	private Context mContext;
	private SimpleDBManager dbManager;
	private SQLiteDatabase db;

	/**
	 * ������
	 * 
	 * @param PK_name
	 *            ��������
	 */
	public SimpleDaoImpl(Context mContext) {
		this.PK_name = getPKName();
		this.TABLE_name = getTableName();
		this.mContext = mContext;
		this.dbManager = getDbManager();
		this.db = getDb();
	}

	/**
	 * ���ӻ��߸��£����entity������Ϊ�գ���ִ�����Ӳ��������������Ϊ�գ���ִ�и��²���
	 * 
	 * @param entity
	 */
	public void save(T entity) {
		Class<?> clazz = entity.getClass();
		try {
			Field[] fields = clazz.getDeclaredFields();
			db.beginTransaction();
			ContentValues values = new ContentValues();
			Method getter = null;
			String fieldName = null;
			for (Field field : fields) {
				fieldName = field.getName();
				if (fieldName.equals(PK_name)) { // ignore the given pk value
					continue;
				}
				getter = clazz.getMethod(Util.getGettersName(fieldName));
				values.put(field.getName(), getter.invoke(entity).toString());
			}
			db.insert(TABLE_name, null, values);
			db.setTransactionSuccessful();
			db.endTransaction();
		} catch (NoSuchMethodException e) {
			Log.e(entity.getClass().getName(), "The entity's PK is" + PK_name
					+ ", but it doesn't provide getters for PK.");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����ʵ�壬������в�����entity������Ӧ��ʵ�壬������в����µ�����
	 * 
	 * @param entity
	 */
	public void update(T entity) {
		Class<?> clazz = entity.getClass();
		try {
			Field[] fields = clazz.getDeclaredFields();
			db.beginTransaction();
			ContentValues values = new ContentValues();
			Method getter = null;
			String fieldName = null;
			for (Field field : fields) {
				fieldName = field.getName();
				getter = clazz.getMethod(Util.getGettersName(fieldName));
				values.put(field.getName(), getter.invoke(entity).toString());
			}
			db.replace(TABLE_name, null, values);
			db.setTransactionSuccessful();
			db.endTransaction();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ͨ���������ң������ҵ���entity,����Ҳ�������null
	 * 
	 * @param pk
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T find(K pk) {
		Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		T entity = null;
		try {
			entity = entityClass.newInstance();
			Cursor c = db.rawQuery("SELECT * FROM " + TABLE_name + " where "
					+ PK_name + " = ?", new String[] { pk.toString() });
			Method setter = null;
			String setterName = null;
			if (c.moveToNext()) {
				for (int i = 0; i < c.getColumnCount(); i++) {
					String columnName = c.getColumnName(i);
					setterName = Util.getSettersName(columnName);
					Class<?> fieldType = entityClass.getDeclaredField(
							columnName).getType();
					setter = entityClass.getMethod(setterName, fieldType);
					if (fieldType.getName().equals(String.class.getName())) { // String
						setter.invoke(entity, c.getString(i));
					} else if (fieldType.getName().equals( // Integer
							Integer.class.getName())) {
						setter.invoke(entity, c.getInt(i));
					} else if (fieldType.getName().equals(
							BigInteger.class.getName())) { // BigInteger
						setter.invoke(entity, new BigInteger(c.getString(i)));
					} else if (fieldType.getName().equals(Long.class.getName())) { // Long
						setter.invoke(entity, c.getLong(i));
					} else if (fieldType.getName() // Short
							.equals(Short.class.getName())) {
						setter.invoke(entity, c.getShort(i));
					} else if (fieldType.getName().equals( // Double
							Double.class.getName())) {
						setter.invoke(entity, c.getDouble(i));
					} else if (fieldType.getName()
							.equals(Float.class.getName())) { // Float
						setter.invoke(entity, c.getFloat(i));
					} else if (fieldType.getName().equals( // byte[]
							byte[].class.getName())) {
						setter.invoke(entity, c.getBlob(i));
					} else if (fieldType.getName().equals(
							Timestamp.class.getName())) { // TimeStamp
						setter.invoke(entity, Timestamp.valueOf(c.getString(i)));
					} else if (fieldType.getName().equals(
							java.sql.Date.class.getName())) { // java.sql.date
						setter.invoke(entity,
								java.sql.Date.valueOf(c.getString(i)));
					} else if (fieldType.getName().equals(Time.class.getName())) { // java.sql.Time
						setter.invoke(entity,
								java.sql.Time.valueOf(c.getString(i)));
					}
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return entity;
	}

	/**
	 * ɾ��pk��Ӧ��Ԫ��
	 * 
	 * @param pk
	 */
	public int delete(K pk) {
		db.beginTransaction();
		int l = db.delete(TABLE_name, PK_name + " = ?",
				new String[] { pk.toString() });
		db.setTransactionSuccessful();
		db.endTransaction();
		return l;
	}

	/**
	 * ���ر��м�¼����
	 * 
	 * @return
	 */
	public int getEntitiesCount() {
		Cursor c = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_name, null);
		int count = 0;
		if (c.moveToNext()) {
			count = c.getInt(0);
		}
		return count;
	}

	public SimpleDBManager getDbManager() {
		if (dbManager == null) {
			dbManager = SimpleDBManagerFactory.getInstance(mContext);
		}
		return dbManager;
	}

	public SQLiteDatabase getDb() {
		if (db == null) {
			db = dbManager.getDb();
		}
		return db;
	}

	public abstract String getTableName();

	public abstract String getPKName();
}
