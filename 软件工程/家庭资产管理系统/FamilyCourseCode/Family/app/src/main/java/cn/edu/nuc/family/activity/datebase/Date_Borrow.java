package cn.edu.nuc.family.activity.datebase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Date_Borrow extends SQLiteOpenHelper {

	public Date_Borrow(Context context) {
		super(context, "BORROW", null, 1);
	}

	//钱财借出表
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE BORROW("
				+"_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " BORROW_NO VARCHAR(20) DEFAULT NULL,"
				+ " BORROW_NAMEE VARCHAR(20) DEFAULT NULL,"
				+ " BORROW_NAMEF VARCHAR(20) DEFAULT NULL,"
				+ " BORROW_PNO VARCHAR (50) DEFAULT NULL,"
				+ " BORROW_MONE DOUBLE(50) DEFAULT NULL,"
				+ " BORROW_DATE DATE(12) DEFAULT NULL)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自动生成的方法存根
	}
}
