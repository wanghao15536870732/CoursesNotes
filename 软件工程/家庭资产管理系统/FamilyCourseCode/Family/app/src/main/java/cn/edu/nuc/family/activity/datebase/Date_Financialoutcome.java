package cn.edu.nuc.family.activity.datebase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Date_Financialoutcome extends SQLiteOpenHelper{

	public Date_Financialoutcome(Context context) {
		super(context, "FINANCIALOUTCOME", null, 1);
		// TODO 自动生成的构造函数存根
	}

	//理财产品支出表
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE FINANCIALOUTCOME ("
				+"_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " FINANCIALOUTCOME_NO VARCHAR(20) DEFAULT NULL,"
				+ " FINANCIALOUTCOME_NA VARCHAR(20) DEFAULT NULL,"
				+ " FINANCIALOUTCOME_IN DOUBLE(50) DEFAULT NULL,"
				+ " FINANCIALOUTCOME_P VARCHAR(20) DEFAULT NULL,"
				+ " FINANCIALOUTCOME_DAT DATE(12) DEFAULT NULL)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自动生成的构造函数存根
	}
}
