package cn.edu.nuc.family.activity.activity;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import cn.edu.nuc.family.R;
import cn.edu.nuc.family.activity.datebase.Date_User;
import cn.edu.nuc.family.activity.base.ChangeUserInformation;

public class HomeMemberManagerActivity extends ListActivity {
	private Button button1;
	private Button button2;
	Date_User date_user = new Date_User(this);
	public static String Userno;
	private SimpleCursorAdapter adapter;
	private OnItemClickListener listsetOnItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			
			SQLiteDatabase listWrite = date_user.getWritableDatabase();
			Cursor c= adapter.getCursor();
			c.moveToPosition(position);

			int itemId = c.getInt(c.getColumnIndex("_id"));
			Userno = c.getString(c.getColumnIndex("USER_NO"));
			Intent intent=new Intent();
			intent.setClass(HomeMemberManagerActivity.this, ChangeUserInformation.class);
			startActivity(intent);
			
		}
	};
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activitu_home_manager);
		
		OnItemLongClickListener ListViewItemLongClickListener = new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
				new AlertDialog.Builder(HomeMemberManagerActivity.this)
						.setTitle("提醒")
						.setMessage("您确定要删除此用户吗？")
						.setNegativeButton("取消", null)
						.setPositiveButton("确定", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO 自动生成的方法存根
								SQLiteDatabase userWrite = date_user.getWritableDatabase();
								Cursor c= adapter.getCursor();
								c.moveToPosition(position);
								int itemId = c.getInt(c.getColumnIndex("_id"));
								userWrite.delete("USER", "_id=?", new String[]{itemId+""});
								Toast.makeText(HomeMemberManagerActivity.this, "删除成功！", Toast.LENGTH_SHORT).show();
								refreshListView();
							}
						}).show();
				return true;
			}
		};

		//删除用户
		getListView().setOnItemLongClickListener(ListViewItemLongClickListener);
		//修改用户
		getListView().setOnItemClickListener(listsetOnItemClickListener );
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(HomeMemberManagerActivity.this, AddUserActivity.class);
				startActivity(intent);

			}
		});
		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				refreshListView();
			}
		});
		SQLiteDatabase userRead = date_user.getReadableDatabase();
		Cursor c = userRead.query("USER", null, null, null, null, null, null);
		adapter = new SimpleCursorAdapter(this, R.layout.user_list_cell, c, new String[]{"USER_NAME","USER_NO"}, new int[]{R.id.username,R.id.userno});
		setListAdapter(adapter);
	}

	private void refreshListView(){
		Date_User date_user = new Date_User(this);
		SQLiteDatabase userRead = date_user.getReadableDatabase();
		Cursor c = userRead.query("USER", null, null, null, null, null, null);
		adapter.changeCursor(c);
	}
}
