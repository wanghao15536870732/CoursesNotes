package cn.edu.nuc.family.activity.activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import cn.edu.nuc.family.R;
import cn.edu.nuc.family.activity.datebase.Date_Bankincome;
import cn.edu.nuc.family.activity.datebase.Date_Bankoutcome;
import cn.edu.nuc.family.activity.datebase.Date_Borrow;
import cn.edu.nuc.family.activity.datebase.Date_DailyIncome;
import cn.edu.nuc.family.activity.datebase.Date_DailyOutcome;
import cn.edu.nuc.family.activity.datebase.Date_Financialincome;
import cn.edu.nuc.family.activity.datebase.Date_Financialoutcome;
import cn.edu.nuc.family.activity.datebase.Date_List;
import cn.edu.nuc.family.activity.datebase.Date_Repay;
import cn.edu.nuc.family.activity.base.DailyInComeChange;
import cn.edu.nuc.family.activity.base.Xiugai2;
import cn.edu.nuc.family.activity.base.Xiugai3;
import cn.edu.nuc.family.activity.base.Xiugai4;

public class InputChangeActivity extends ListActivity {

	Date_DailyIncome date_dailyincome = new Date_DailyIncome(this);
	Date_DailyOutcome date_dailyoutcome = new Date_DailyOutcome(this);
	Date_Bankincome date_bankincome = new Date_Bankincome(this);
	Date_Bankoutcome date_bankoutcome = new Date_Bankoutcome(this);
	Date_Financialincome date_financialincome = new Date_Financialincome(this);
	Date_Financialoutcome date_financialoutcome = new Date_Financialoutcome(this);
	Date_Borrow date_borrow = new Date_Borrow(this);
	Date_Repay date_repay = new Date_Repay(this);
	Date_List date_list = new Date_List(this);

	Cursor c;
	private SimpleCursorAdapter adapter;
	private Button addBtn;
	private Button refreshBtn;
	public static String number;
	public static String tableName;
	private ImageView back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_input_change);

		refreshBtn = findViewById(R.id.refreshBtn);

		refreshBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				refreshListView();
			}
		});

		addBtn = findViewById(R.id.addBtn);

		addBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				refreshListView();
				Intent intent = new Intent(InputChangeActivity.this, InputInformationActivity.class);
				startActivity(intent);
			}
		});

		back = findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		//单击事件
		getListView().setOnItemClickListener(listOnItemClickListener);
		//长按事件
		getListView().setOnItemLongClickListener(listOnItemLongClickListener);
		//日常收入支出
		SQLiteDatabase dailyincome = date_list.getReadableDatabase();
		c = dailyincome.query("LIST", null, "LIST_NO=?",
				new String[]{PaymentsManagementActivity.no}, null, null, null);
		adapter = new SimpleCursorAdapter(this, R.layout.richangshouru_list_cell, c,
				new String[]{"LIST_DATE", "LIST_NAME", "LIST_MONEY", "LIST_TYPE", "LIST_XUHAO"},
				new int[]{R.id.riqi, R.id.mingcheng, R.id.jine, R.id.leibie, R.id.xuhao});
		setListAdapter(adapter);
		//		SQLiteDatabase dailyoutcome = date_dailyoutcome.getReadableDatabase();
		//		c = dailyoutcome.query("DAILYOUTCOME", null, null, null, null, null, null);
		//		SimpleCursorAdapter adapter1 = new SimpleCursorAdapter(this,
		//		R.layout.richangzhichu_list_cell, c,
		//		new String[]{"DAILYOUTCOME_DATE","DAILYOUTCOME_NAME","DAILYOUTCOME_NUMBE"},
		//		new int[]{R.id.riqi,R.id.mingcheng,R.id.jine});
		//		setListAdapter(adapter);

	}

	private OnItemClickListener listOnItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			SQLiteDatabase listWrite = date_list.getWritableDatabase();
			Cursor c = adapter.getCursor();
			c.moveToPosition(position);

			int itemId = c.getInt(c.getColumnIndex("_id"));
			number = c.getString(c.getColumnIndex("LIST_XUHAO"));
			tableName = c.getString(c.getColumnIndex("LIST_TYPE"));

			if (tableName.equals("日常收入：")) {
				Intent intent = new Intent();
				intent.setClass(InputChangeActivity.this, DailyInComeChange.class);
				startActivity(intent);
			}

			if (tableName.equals("日常支出：")) {
				Intent intent = new Intent();
				intent.setClass(InputChangeActivity.this, DailyInComeChange.class);
				startActivity(intent);

			}

			if (tableName.equals("银行取出：")) {
				Intent intent = new Intent();
				intent.setClass(InputChangeActivity.this, Xiugai2.class);
				startActivity(intent);

			}

			if (tableName.equals("银行存入：")) {
				Intent intent = new Intent();
				intent.setClass(InputChangeActivity.this, Xiugai2.class);
				startActivity(intent);

			}

			if (tableName.equals("理财支出：")) {
				Intent intent = new Intent();
				intent.setClass(InputChangeActivity.this, Xiugai3.class);
				startActivity(intent);

			}

			if (tableName.equals("理财收入：")) {
				Intent intent = new Intent();
				intent.setClass(InputChangeActivity.this, Xiugai3.class);
				startActivity(intent);

			}

			if (tableName.equals("钱财借出得款人：")) {
				Intent intent = new Intent();
				intent.setClass(InputChangeActivity.this, Xiugai4.class);
				startActivity(intent);

			}

			if (tableName.equals("钱财归还还款人：")) {
				Intent intent = new Intent();
				intent.setClass(InputChangeActivity.this, Xiugai4.class);
				startActivity(intent);
			}
			refreshListView();
		}
	};

	private void showWarningDialog(Context mContext,String title,String message){

	}
	private OnItemLongClickListener listOnItemLongClickListener = new OnItemLongClickListener() {

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
									   final int position, long id) {

			new AlertDialog.Builder(InputChangeActivity.this)
					.setTitle("提醒").setMessage("您确定要删除此信息吗？")
					.setNegativeButton("取消", null)
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO 自动生成的方法存根
						SQLiteDatabase listWrite = date_list.getWritableDatabase();
						Cursor c = adapter.getCursor();
						c.moveToPosition(position);
	
						int itemId = c.getInt(c.getColumnIndex("_id"));
						String xuhao = c.getString(c.getColumnIndex("LIST_XUHAO"));
						String biaoming = c.getString(c.getColumnIndex("LIST_TYPE"));
						if (biaoming.equals("日常收入：")) {
							SQLiteDatabase userWrite = date_dailyincome.getWritableDatabase();
							userWrite.delete("DAILYINCOME",
									"DAILYINCOME_PEOPLE=? and DAILYINCOME_NO=?",
									new String[]{PaymentsManagementActivity.no, xuhao});
	
						}
						if (biaoming.equals("日常支出：")) {
							SQLiteDatabase userWrite = date_dailyoutcome.getWritableDatabase();
							userWrite.delete("DAILYOUTCOME",
									"DAILYOUTCOME_PEOPLE=? and DAILYOUTCOME_NO=?",
									new String[]{PaymentsManagementActivity.no, xuhao});
	
						}
						if (biaoming.equals("银行取出：")) {
							SQLiteDatabase userWrite = date_bankoutcome.getWritableDatabase();
							userWrite.delete("BANKOUTCOME",
									"BANKOUTCOME_NAME=? and BANKOUTCOME_NO=?",
									new String[]{PaymentsManagementActivity.no, xuhao});
	
						}
						if (biaoming.equals("银行存入：")) {
							SQLiteDatabase userWrite = date_bankincome.getWritableDatabase();
							userWrite.delete("BANKINCOME",
									"BANKINCOME_NAME=? and BANKINCOME_NO=?",
									new String[]{PaymentsManagementActivity.no, xuhao});
	
						}
						if (biaoming.equals("理财支出：")) {
							SQLiteDatabase userWrite = date_financialoutcome.getWritableDatabase();
							userWrite.delete("FINANCIALOUTCOME",
									"FINANCIALOUTCOME_NA=? and FINANCIALOUTCOME_NO=?",
									new String[]{PaymentsManagementActivity.no, xuhao});
	
	
						}
						if (biaoming.equals("理财收入：")) {
							SQLiteDatabase userWrite = date_financialincome.getWritableDatabase();
							userWrite.delete("FINANCIALINCOME",
									"FINANCIALINCOME_NA=? and FINANCIALINCOME_NO=?",
									new String[]{PaymentsManagementActivity.no, xuhao});
	
						}
						if (biaoming.equals("钱财借出得款人：")) {
							SQLiteDatabase userWrite = date_borrow.getWritableDatabase();
							userWrite.delete("BORROW", "BORROW_NAMEF=? and BORROW_NO=?",
									new String[]{PaymentsManagementActivity.no, xuhao});
	
						}
						if (biaoming.equals("钱财归还还款人：")) {
							SQLiteDatabase userWrite = date_repay.getWritableDatabase();
							userWrite.delete("REPAY", "REPAY_NAMEF=? and REPAY_NO=?",
									new String[]{PaymentsManagementActivity.no, xuhao});
						}
						listWrite.delete("LIST", "_id=?", new String[]{itemId + ""});
						Toast.makeText(InputChangeActivity.this, "删除成功！", Toast.LENGTH_SHORT).show();
						refreshListView();
					}
				}).show();
				return true;
		}
	};

	private void refreshListView() {
		Date_List date_list2 = new Date_List(this);
		SQLiteDatabase date_list2Read = date_list2.getReadableDatabase();
		Cursor c = date_list2Read.query("LIST", null,
				"LIST_NO=?", new String[]{PaymentsManagementActivity.no},
				null, null, null);
		adapter.changeCursor(c);
	}
}


