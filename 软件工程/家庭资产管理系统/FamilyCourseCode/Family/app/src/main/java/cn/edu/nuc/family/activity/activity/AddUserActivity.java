package cn.edu.nuc.family.activity.activity;


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
import cn.edu.nuc.family.activity.datebase.Date_User;

public class AddUserActivity extends Activity {
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
		setContentView(R.layout.adduser);
		userno = findViewById(R.id.userno);
		username = findViewById(R.id.username);
		userpassword = findViewById(R.id.userpassword);
		userpose = findViewById(R.id.userpose);
		userbank = findViewById(R.id.userbank);
		userfinan = findViewById(R.id.userfinan);
		button1 = findViewById(R.id.button1);
		button2 = findViewById(R.id.button2);

		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(userno.getText().toString().equals("")||username.getText().toString().equals("")||userpassword.getText().toString().equals("")||userpose.getText().toString().equals("")){
					Toast.makeText(AddUserActivity.this,"您有未添加的信息", Toast.LENGTH_SHORT).show();
				}else{
					String userNo = userno.getText().toString();
					String userName = username.getText().toString();
					String userPassword = userpassword.getText().toString();
					String userPose = userpose.getText().toString();
					String userBank = userbank.getText().toString();
					String userFiNan = userfinan.getText().toString();
					if(!checkUser(userNo,date_user)){
						InsertUser(userNo,userName,userPassword, userPose,userBank, userFiNan);
						userno.setText("");
						username.setText("");
						userpassword.setText("");
						userpose.setText("");
						userbank.setText("");
						userfinan.setText("");
					}else{
						Toast.makeText(AddUserActivity.this,"用户已存在！", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
	}

	public void InsertUser(String userNo,String userName,String userPassword,
						   String userPose,String userBank,String userFiNan){
		if(!checkUser(userNo,date_user)){
			SQLiteDatabase UserWrite = date_user.getWritableDatabase();
			ContentValues cv = new ContentValues();
			cv.put("USER_NO",userNo);
			cv.put("USER_NAME",userName);
			cv.put("USER_PASSWORD",userPassword);
			cv.put("USER_POSE",userPose);
			cv.put("USER_BANK",userBank);
			cv.put("USER_FINAN",userFiNan);
			UserWrite.insert("USER", null, cv);
			UserWrite.close();
			Toast.makeText(AddUserActivity.this,"添加成功", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(AddUserActivity.this,"该用户已存在！", Toast.LENGTH_SHORT).show();
		}
	}

	public boolean checkUser(String userNo,Date_User date_user) {
		SQLiteDatabase UserRead = date_user.getReadableDatabase();
		Cursor c = UserRead.query("USER", null, null,
				null , null, null, null);
		while(c.moveToNext()){
			String NO = c.getString(c.getColumnIndex("USER_NO"));
			if(userNo.equals(NO)){
				return true;
			}
		}
		return false;
	}
}
