package cn.edu.nuc.family.activity.activity;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.edu.nuc.family.R;
import cn.edu.nuc.family.activity.datebase.Date_Admin;

public class ChangeAdminActivity extends Activity{
	private EditText editText1;
	private EditText editText2;
	private EditText editText3;
	private Button button1;
	Date_Admin date_admin = new Date_Admin(this);
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_admin);
		editText1 = (EditText) findViewById(R.id.editText1);
		editText2 = (EditText) findViewById(R.id.editText2);
		editText3 = (EditText) findViewById(R.id.editText3);
		button1 = (Button) findViewById(R.id.button1);

		SQLiteDatabase UserRead = date_admin.getReadableDatabase();
		Cursor c = UserRead.query("ADMIN", null, null, null, null, null, null);

		c.moveToFirst();
		final String adminpassword = c.getString(2);

		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!editText1.getText().toString().equals(adminpassword)) {
					Toast.makeText(ChangeAdminActivity.this, "原密码错误！",
							Toast.LENGTH_SHORT).show();
				} else {
					if (!editText2.getText().toString().equals(editText3.getText().toString())) {
						Toast.makeText(ChangeAdminActivity.this, "两次密码不一致！",
								Toast.LENGTH_SHORT).show();
					} else {
						changeAdminPassword(editText2.getText().toString());
					}
				}
			}
		});
	}
	public void changeAdminPassword(String newPassword){
		SQLiteDatabase UserWrite = date_admin.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("ADMIN_NO", "admin");
		cv.put("ADMIN_PAS", newPassword);
		UserWrite.update("ADMIN", cv, "_id=1", null);
		Toast.makeText(ChangeAdminActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent();
		intent.setClass(ChangeAdminActivity.this, ChangeAdminActivity.class);
		startActivity(intent);
	}
}
