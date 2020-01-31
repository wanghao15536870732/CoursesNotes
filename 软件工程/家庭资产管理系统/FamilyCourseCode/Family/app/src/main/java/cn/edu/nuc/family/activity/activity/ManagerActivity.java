package cn.edu.nuc.family.activity.activity;

import java.io.FileOutputStream;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import cn.edu.nuc.family.R;
import cn.edu.nuc.family.activity.datebase.Date_List;

public class ManagerActivity extends Activity {
	private Button button5;
	private Date_List date_list = new Date_List(this);
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manager);
		Button button1 = (Button) findViewById(R.id.button1);
		Button button2 = (Button) findViewById(R.id.button2);
		Button button3 = (Button) findViewById(R.id.button3);
		Button button4 = (Button) findViewById(R.id.button4);
		
		button4.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent=new Intent();
                intent.setClass(ManagerActivity.this, ChangeAdminActivity.class);
                startActivity(intent);
			}
		});
		
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
                intent.setClass(ManagerActivity.this, HomeMemberManagerActivity.class);
                startActivity(intent);
			}
		});
		
		button3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
                intent.setClass(ManagerActivity.this, QueryByAdminActivity.class);
                startActivity(intent);
				
			}
		});
	
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SQLiteDatabase listRead = date_list.getReadableDatabase();
				Cursor c = listRead.query("LIST", null, null, null, null, null, null);


				// TODO 自动生成的方法存根
				try {
					// 输出流 第一个参数为文件名
					FileOutputStream out = new FileOutputStream(
							"/sdcard/xinxi.txt",false);
					// 或 mnt/sdcard/name.txt
					byte[] message = null;
					while(c.moveToNext()){
						String s ="用户ID：" +c.getString(1)+"日期："+c.getString(2)+"类型："+c.getString(3)+"名称："+c.getString(4)+"序号："+c.getString(5)+"金额："+c.getString(6)+"\n";
						s = s.replaceAll("\n", "\r\n");

						message = s.getBytes();
						out.write(message);
					}


//	                    String txt = button1.getText().toString();
//	                    // 换行
//	                    txt = txt.replaceAll("\n", "\r\n");
//	                    // 将内容转换为字节类型的数组
//	                    byte[] message = txt.getBytes();
					 	// 把字节输出
					 	// 关闭输出流
					out.close();
					Toast.makeText(ManagerActivity.this, "信息已保存至/sdcard/xinxi.txt", Toast.LENGTH_SHORT).show();
				}
				catch(Exception e) {
					Log.e("e", e.getMessage());
				}
	            
			}
		});
	}
	
	
	
	
}
