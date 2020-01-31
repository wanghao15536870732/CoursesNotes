package cn.edu.nuc.family.activity.datebase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Date_Repay extends SQLiteOpenHelper {

	public Date_Repay(Context context) {
		super(context, "REPAY", null, 1);
		// TODO 自动生成的构造函数存根
	}

	//钱财归还表
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE REPAY ("
				+"_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " REPAY_NO VARCHAR(20) DEFAULT NULL,"
				+ " REPAY_NAMEE VARCHAR(20) DEFAULT NULL,"
				+ " REPAY_NAMEF VARCHAR(20) DEFAULT NULL,"
				+ " REPAY_PNO VARCHAR (50) DEFAULT NULL,"
				+ " REPAY_MONE DOUBLE(50) DEFAULT NULL,"
				+ " REPAY_DATE DATE(12) DEFAULT NULL)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自动生成的方法存根

	}
}
