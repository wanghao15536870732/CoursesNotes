package cn.edu.nuc.family.activity.base;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.edu.nuc.family.R;
import cn.edu.nuc.family.activity.activity.HomeMemberManagerActivity;
import cn.edu.nuc.family.activity.datebase.Date_User;

public class ChangeUserInformation extends Activity {
	private EditText userno;
	private EditText username;
	private EditText userpassword;
	private EditText userpose;
	private EditText userbank;
	private EditText userfinan;
	private Button button1;
	private Button button2;
	Date_User date_user = new Date_User(this);
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change_user);
		userno = (EditText) findViewById(R.id.userno);
		username = (EditText) findViewById(R.id.username);
		userpassword = (EditText) findViewById(R.id.userpassword);
		userpose = (EditText) findViewById(R.id.userpose);
		userbank = (EditText) findViewById(R.id.userbank);
		userfinan = (EditText) findViewById(R.id.userfinan);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);

		ContentValues cv = new ContentValues();
		SQLiteDatabase UserRead = date_user.getReadableDatabase();
		Cursor c = UserRead.query("USER", null, "USER_NO=?", new String[]{HomeMemberManagerActivity.Userno} , null, null, null);
		while(c.moveToNext()){
			userno.setText(c.getString(1));
			username.setText(c.getString(2));
			userpassword.setText(c.getString(3));
			userpose.setText(c.getString(4));
			userbank.setText(c.getString(5));
			userfinan.setText(c.getString(6));
		}

		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(userno.getText().toString().equals("")||username.getText().toString().equals("")||userpassword.getText().toString().equals("")||userpose.getText().toString().equals("")){
					Toast.makeText(ChangeUserInformation.this,"����δ��ӵ���Ϣ", Toast.LENGTH_SHORT).show();
				}else{
					if(!panduan()){
						SQLiteDatabase UserWrite = date_user.getWritableDatabase();
						ContentValues cv = new ContentValues();
						cv.put("USER_NO",userno.getText().toString());
						cv.put("USER_NAME",username.getText().toString());
						cv.put("USER_PASSWORD",userpassword.getText().toString());
						cv.put("USER_POSE",userpose.getText().toString());
						cv.put("USER_FINAN",userbank.getText().toString());
						cv.put("USER_BANK",userfinan.getText().toString());
						UserWrite.update("USER", cv, "USER_NO=?", new String[]{HomeMemberManagerActivity.Userno});
						UserWrite.close();
						Toast.makeText(ChangeUserInformation.this,"�޸ĳɹ�", Toast.LENGTH_SHORT).show();
						userno.setText("");
						username.setText("");
						userpassword.setText("");
						userpose.setText("");
						userbank.setText("");
						userfinan.setText("");}else{
							Toast.makeText(ChangeUserInformation.this,"�û��Ѵ��ڣ�", Toast.LENGTH_SHORT).show();
						}
				}}
		});
	}
	public boolean panduan() {
		SQLiteDatabase UserWrite = date_user.getWritableDatabase();
		ContentValues cv = new ContentValues();
		SQLiteDatabase UserRead = date_user.getReadableDatabase();
		Cursor c = UserRead.query("USER", null, null, null , null, null, null);
		while(c.moveToNext()){
			String NO = c.getString(c.getColumnIndex("USER_NO"));
			if(userno.getText().toString().equals(NO)){
				return true;
			}
		}
		return false;
	}
}
