package cn.edu.nuc.family.activity.datebase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Date_Notepad extends SQLiteOpenHelper {

	public Date_Notepad(Context context) {
		super(context, "NOTEPAD", null, 1);
		// TODO 自动生成的构造函数存根
	}

	//记事表
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE NOTEPAD ("
				+"_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " NOTEPAD_NO VARCHAR(50) DEFAULT NULL,"
				+ " NOTEPAD_NAME VARCHAR(50) DEFAULT NULL,"
				+ " NOTEPAD_DATE DATE(12) DEFAULT NULL,"
				+ " NOTEPAD_NOT VARCHAR(100) DEFAULT NULL)");



	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自动生成的构造函数存根

	}
}
