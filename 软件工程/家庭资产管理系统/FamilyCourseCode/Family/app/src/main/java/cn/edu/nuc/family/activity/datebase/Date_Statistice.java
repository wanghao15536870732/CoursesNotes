package cn.edu.nuc.family.activity.datebase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Date_Statistice extends SQLiteOpenHelper {

	public Date_Statistice(Context context) {
		super(context, "STATISTICS", null, 1);
		// TODO 自动生成的方法存根
	}

	//统计表
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE STATISTICS ("
				+"_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " STATISTICS_NO VARCHAR(20) DEFAULT NULL,"
				+ " STATISTICS_INCOME DOUBLE (50) DEFAULT NULL,"
				+ " STATISTICS_OUTCOME DOUBLE(50) DEFAULT NULL)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自动生成的方法存根
	}
}
