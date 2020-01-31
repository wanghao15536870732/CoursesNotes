package cn.edu.nuc.family.activity.datebase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Date_User extends SQLiteOpenHelper {

	public Date_User(Context context) {
		super(context, "USER", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE USER("
				+"_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "USER_NO VARCHAR(20) DEFAULT NULL,"
				+ "USER_NAME VARCHAR(20) DEFAULT NULL,"
				+ "USER_PASSWORD VARCHAR(20) DEFAULT NULL,"
				+ "USER_POSE VARCHAR(10) DEFAULT NULL,"
				+ "USER_BANK VARCHAR(20) DEFAULT NULL,"
				+ "USER_FINAN VARCHAR(20) DEFAULT NULL)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自动生成的方法存根
	}
}
