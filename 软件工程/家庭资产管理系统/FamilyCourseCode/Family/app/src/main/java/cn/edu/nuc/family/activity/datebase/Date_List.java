package cn.edu.nuc.family.activity.datebase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Date_List extends SQLiteOpenHelper{

	public Date_List(Context context) {
		super(context, "LIST", null, 1);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE LIST ("
				+"_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " LIST_NO VARCHAR(20) DEFAULT NULL,"
				+ " LIST_DATE DATE(12) DEFAULT NULL,"
				+ " LIST_TYPE VARCHAR(20) DEFAULT NULL,"
				+ " LIST_NAME VARCHAR(20) DEFAULT NULL,"
				+ " LIST_XUHAO VARCHAR(20) DEFAULT NULL,"
				+ " LIST_MONEY DOUBLE(50) DEFAULT NULL)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自动生成的方法存根
	}
}
