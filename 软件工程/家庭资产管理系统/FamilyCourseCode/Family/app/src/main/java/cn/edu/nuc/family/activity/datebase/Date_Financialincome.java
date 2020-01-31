package cn.edu.nuc.family.activity.datebase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Date_Financialincome extends SQLiteOpenHelper {

	public Date_Financialincome(Context context) {
		super(context, "FINANCIALINCOME", null, 1);
		// TODO 自动生成的构造函数存根
	}

	//理财产品收入表
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE FINANCIALINCOME ("
				+"_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " FINANCIALINCOME_NO VARCHAR(20) DEFAULT NULL,"
				+ " FINANCIALINCOME_NA VARCHAR(20) DEFAULT NULL,"
				+ " FINANCIALINCOME_IN DOUBLE(50) DEFAULT NULL,"
				+ " FINANCIALINCOME_P VARCHAR(20) DEFAULT NULL,"
				+ " FINANCIALINCOME_DAT DATE(12) DEFAULT NULL)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自动生成的方法存根
	}
}
