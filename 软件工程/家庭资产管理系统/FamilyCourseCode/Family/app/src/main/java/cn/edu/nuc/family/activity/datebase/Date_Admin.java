package cn.edu.nuc.family.activity.datebase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Date_Admin extends SQLiteOpenHelper {

	public Date_Admin(Context context) {
		super(context, "ADMIN", null, 1);
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE ADMIN ("
				+"_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "ADMIN_NO VARCHAR(20) DEFAULT NULL,"
				+ "ADMIN_PAS VARCHAR(20) DEFAULT NULL)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自动生成的方法存根
	}
}
