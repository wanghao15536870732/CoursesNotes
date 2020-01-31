package cn.edu.nuc.family.activity.datebase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Date_DailyIncome extends SQLiteOpenHelper {

	public Date_DailyIncome(Context context) {
		super(context, "DAILYINCOME", null, 1);
	}

	//日常收入表
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE DAILYINCOME ("
				+"_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " DAILYINCOME_NO VARCHAR(20) DEFAULT NULL,"
				+ " DAILYINCOME_NAME VARCHAR(20) DEFAULT NULL,"
				+ " DAILYINCOME_PEOPLE VARCHAR(16) DEFAULT NULL,"
				+ " DAILYINCOME_NUMBE DOUBLE(50) DEFAULT NULL,"
				+ " DAILYINCOME_DATE DATE(12) DEFAULT NULL)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
