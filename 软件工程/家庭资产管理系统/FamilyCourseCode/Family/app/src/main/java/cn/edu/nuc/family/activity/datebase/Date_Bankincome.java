package cn.edu.nuc.family.activity.datebase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Date_Bankincome extends SQLiteOpenHelper {

	public Date_Bankincome(Context context) {
		super(context, "BANKINCOME", null, 1);
		// TODO 自动生成的构造函数存根
	}

	//银行储蓄存入表
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE BANKINCOME ("
				+"_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "BANKINCOME_NO VARCHAR(20) DEFAULT NULL,"
				+ " BANKINCOME_NAMENO VARCHAR(20) DEFAULT NULL,"
				+ " BANKINCOME_NAME VARCHAR(20) DEFAULT NULL,"
				+ " BANKINCOME_MONEY DOUBLE(50) DEFAULT NULL,"
				+ " BANKINCOME_FREE DOUBLE(50) DEFAULT NULL,"
				+ " BANKINCOME_DATE DATE(12) DEFAULT NULL,"
				+ " BANKINCOME_TYPE VARCHAR(20) DEFAULT NULL,"
				+ " BANKINCOME_BNAME VARCHAR(20) DEFAULT NULL)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自动生成的方法存根
	}
}
