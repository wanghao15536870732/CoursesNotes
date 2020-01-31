package cn.edu.nuc.family.activity.datebase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Date_Bankoutcome extends SQLiteOpenHelper {

	public Date_Bankoutcome(Context context) {
		super(context, "BANKOUTCOME", null, 1);
		// TODO 自动生成的构造函数存根
	}

	// 银行储蓄取出表
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE BANKOUTCOME ("
				+"_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "BANKOUTCOME_NO VARCHAR(20) DEFAULT NULL,"
				+ " BANKOUTCOME_NAMENO VARCHAR(20) DEFAULT NULL,"
				+ " BANKOUTCOME_NAME VARCHAR(20) DEFAULT NULL,"
				+ " BANKOUTCOME_MONEY DOUBLE(50) DEFAULT NULL,"
				+ " BANKOUTCOME_FREE DOUBLE(50) DEFAULT NULL,"
				+ " BANKOUTCOME_DATE DATE(12) DEFAULT NULL,"
				+ " BANKOUTCOME_TYPE VARCHAR(20) DEFAULT NULL,"
				+ " BANKOUTCOME_BNAME VARCHAR(20) DEFAULT NULL)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自动生成的方法存根
	}
}
