package cn.edu.nuc.family.activity.datebase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

//日常支出表
public class Date_DailyOutcome extends SQLiteOpenHelper {

	public Date_DailyOutcome(Context context) {
		super(context, "DAILYOUTCOME", null, 1);
	}

	//日常支出表
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE DAILYOUTCOME ("
				+"_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " DAILYOUTCOME_NO VARCHAR(20) DEFAULT NULL,"
				+ " DAILYOUTCOME_NAME VARCHAR(20) DEFAULT NULL,"
				+ " DAILYOUTCOME_PEOPLE VARCHAR(16) DEFAULT NULL,"
				+ " DAILYOUTCOME_NUMBE DOUBLE(50) DEFAULT NULL,"
				+ " DAILYOUTCOME_DATE DATE(12) DEFAULT NULL)");


	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自动生成的方法存根
	}
}
