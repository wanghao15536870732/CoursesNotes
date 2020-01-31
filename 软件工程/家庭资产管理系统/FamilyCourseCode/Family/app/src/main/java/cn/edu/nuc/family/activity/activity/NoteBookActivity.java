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

import cn.edu.nuc.family.R;
import cn.edu.nuc.family.activity.datebase.Date_Notepad;

public class NoteBookActivity extends ListActivity {
	public static String content;
	public static String tittle;
	private Button refreshBtn;
	private Button newBtn;
	private SimpleCursorAdapter adapter;
	Date_Notepad date_notepad = new Date_Notepad(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note_book);

		refreshBtn = (Button) findViewById(R.id.refreshNoteBtn);
		newBtn = (Button) findViewById(R.id.newNoteBtn);

		newBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(NoteBookActivity.this, NewNoteActivity.class);
				startActivity(intent);
			}
		});

		refreshBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				 refreshListView();
			}
		});
		SQLiteDatabase dailyincome = date_notepad.getReadableDatabase();
		Cursor c1 = dailyincome.query("NOTEPAD", null, "NOTEPAD_NO=?",
				new String[]{PaymentsManagementActivity.no}, null, null, null);
		adapter = new SimpleCursorAdapter(this, R.layout.note_list_cell, c1,
				new String[]{"NOTEPAD_DATE","NOTEPAD_NAME"}, new int[]{R.id.textView1,R.id.textView2});
		setListAdapter(adapter);
		//单击事件
		getListView().setOnItemClickListener(listOnItemClickListener);
		//长按事件
		getListView().setOnItemLongClickListener(listOnItemLongClickListener );
    }

	private OnItemClickListener listOnItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
								long id) {
			SQLiteDatabase listWrite = date_notepad.getWritableDatabase();
			Cursor c= adapter.getCursor();
			c.moveToPosition(position);

			int itemId = c.getInt(c.getColumnIndex("_id"));
			content = c.getString(c.getColumnIndex("NOTEPAD_NOT"));
			tittle= c.getString(c.getColumnIndex("NOTEPAD_NAME"));
			Intent intent=new Intent();
			intent.setClass(NoteBookActivity.this, ChangeNoteBook.class);
			startActivity(intent);

		}
	};

	private OnItemLongClickListener listOnItemLongClickListener = new OnItemLongClickListener() {

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
			new AlertDialog.Builder(NoteBookActivity.this)
					.setTitle("提醒")
					.setMessage("您确定要删除此信息吗？")
					.setNegativeButton("取消", null)
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO 自动生成的方法存根
							SQLiteDatabase userWrite = date_notepad.getWritableDatabase();
							Cursor c= adapter.getCursor();
							c.moveToPosition(position);
							int itemId = c.getInt(c.getColumnIndex("_id"));
							userWrite.delete("NOTEPAD", "_id=?", new String[]{itemId+""});
							refreshListView();
						}
					}).show();
			return true;
		}
	};

    private void refreshListView(){
		Date_Notepad date_user = new Date_Notepad(this);
		SQLiteDatabase userRead = date_user.getReadableDatabase();
		Cursor c = userRead.query("NOTEPAD", null, null,
				null, null, null, null);
		adapter.changeCursor(c);
	}
}
