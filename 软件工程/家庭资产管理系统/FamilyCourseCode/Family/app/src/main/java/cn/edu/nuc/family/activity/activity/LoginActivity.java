package cn.edu.nuc.family.activity.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import cn.edu.nuc.family.R;
import cn.edu.nuc.family.activity.datebase.Date_Admin;
import cn.edu.nuc.family.activity.datebase.Date_User;
import cn.edu.nuc.family.activity.util.SnackbarUtil;

public class LoginActivity extends AppCompatActivity {
	private EditText username_edit;
	private EditText password_edit;
	private String password = "";
	Date_User date_user = new Date_User(this);
	Date_Admin date_admin = new Date_Admin(this);
	private LinearLayout mLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Button signInBtn = findViewById(R.id.signin_button);
		Button toastBtn = findViewById(R.id.tishi_button);
		username_edit = findViewById(R.id.username_edit);
		password_edit = findViewById(R.id.password_edit);
		mLayout = findViewById(R.id.login_main);
		SQLiteDatabase UserRead = date_admin.getReadableDatabase();
		SQLiteDatabase UserWrite = date_admin.getWritableDatabase();
		Cursor c = UserRead.query("ADMIN", null,
				null, null, null, null, null);
		if(c.getCount() == 0) {
			ContentValues cv = new ContentValues();
			cv.put("ADMIN_NO","admin");
			cv.put("ADMIN_PAS","admin");
			UserWrite.insert("ADMIN", null, cv);
			Toast.makeText(LoginActivity.this, "第一次", Toast.LENGTH_SHORT).show();
		}
		c.moveToFirst();
		final String adminName = c.getString(1);
		final String adminPassword = c.getString(2);

		signInBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if(username_edit.getText().equals("") || password_edit.getText().equals("")){
					SnackbarUtil.makeSnackBar(mLayout,"用户名和密码不能为空!");
				}
				else if(username_edit.getText().toString().equals(adminName)){
					if(password_edit.getText().toString().equals(adminPassword)){
						Intent intent=new Intent();
						intent.setClass(LoginActivity.this, ManagerActivity.class);
						startActivity(intent);
					}else{
						SnackbarUtil.makeSnackBar(mLayout,"管理员密码错误!");
					}
				}else{
					//SQLiteDatabase UserWrite = date_user.getWritableDatabase();
					ContentValues cv = new ContentValues();
					SQLiteDatabase UserRead = date_user.getReadableDatabase();
					int flag = 0;
					Cursor c = UserRead.query("USER", null, null, null , null, null, null);
					String number = "";
					while(c.moveToNext()){
						String NO = c.getString(c.getColumnIndex("USER_NO"));
						if(NO.equals(username_edit.getText().toString())){
							password = c.getString(3);
							flag = 1;
							number = NO;
							break;
						}
					}if(flag == 0){
						SnackbarUtil.makeSnackBar(mLayout,"该用户不存在!");
					}
					else if(password_edit.getText().toString().equals(password)){
						Intent intent=new Intent();
						intent.setClass(LoginActivity.this, PaymentsManagementActivity.class);

						//Bundle data = new Bundle();  
						//data.putString("TEXT", kk);
						intent.putExtra("NO",number);
						startActivity(intent);
					}else{
						SnackbarUtil.makeSnackBar(mLayout,"密码错误!");
					}
				}
			}
		});

		toastBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(LoginActivity.this,"初次使用请输出管理员账号:admin 密码:admin",
						Toast.LENGTH_SHORT).show();
			}
		});

	}
}
