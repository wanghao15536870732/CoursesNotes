package cn.edu.nuc.family.activity.base;

import java.util.Calendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.nuc.family.R;
import cn.edu.nuc.family.activity.activity.InputChangeActivity;
import cn.edu.nuc.family.activity.activity.InputInformationActivity;
import cn.edu.nuc.family.activity.activity.PaymentsManagementActivity;
import cn.edu.nuc.family.activity.datebase.Date_Borrow;
import cn.edu.nuc.family.activity.datebase.Date_List;
import cn.edu.nuc.family.activity.datebase.Date_Repay;

public class Xiugai4 extends Activity {
	
	Date_Borrow date_borrow = new Date_Borrow(this);
	Date_Repay date_repay = new Date_Repay(this);
	private Button button1;
	private Button button2;
	private Button riqi;
	private TextView textView1;
	private EditText editText1;
	private EditText editText2;
	private EditText editText3;
	private EditText editText4;
	private RadioButton jiechu;
	private RadioButton huankuan;
	Calendar c = Calendar.getInstance(); 
	Date_List date_list = new Date_List(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xiugai4);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		riqi = (Button) findViewById(R.id.riqi);
		textView1 = (TextView) findViewById(R.id.textView1);
		editText1 = (EditText) findViewById(R.id.editText1);
		editText2 = (EditText) findViewById(R.id.editText2);
		editText3 = (EditText) findViewById(R.id.editText3);
		editText4 = (EditText) findViewById(R.id.editText4);
		jiechu = (RadioButton) findViewById(R.id.jiechu);
		huankuan = (RadioButton) findViewById(R.id.huankuan);
		
		textView1.setText(c.get(Calendar.YEAR)+"-"+c.get(Calendar.MONTH)+1+"-"+c.get(Calendar.DAY_OF_MONTH));
		riqi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new DatePickerDialog(Xiugai4.this, new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						textView1.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
					}
				}, c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH)).show();

			}
		});
		
		

		if(InputChangeActivity.tableName.equals("Ǯ�ƽ���ÿ��ˣ�")){
			ContentValues cv = new ContentValues();
			SQLiteDatabase UserRead = date_borrow.getReadableDatabase();
			Cursor c = UserRead.query("BORROW", null, "BORROW_NAMEF=? and BORROW_NO=?", new String[]{PaymentsManagementActivity.no, InputChangeActivity.number} , null, null, null);
			while(c.moveToNext()){

				editText1.setText(c.getString(1));
				editText2.setText(c.getString(2));
				editText3.setText(c.getString(4));
				editText4.setText(c.getString(5));
				textView1.setText(c.getString(6));
			}
		}else{
			ContentValues cv = new ContentValues();
			SQLiteDatabase UserRead = date_repay.getReadableDatabase();
			Cursor c = UserRead.query("REPAY", null, "REPAY_NAMEF=? and REPAY_NO=?", new String[]{PaymentsManagementActivity.no, InputChangeActivity.number} , null, null, null);
			while(c.moveToNext()){
				editText1.setText(c.getString(1));
				editText2.setText(c.getString(2));
				editText3.setText(c.getString(4));
				editText4.setText(c.getString(5));
				textView1.setText(c.getString(6));

			}
		}

		
		
		
		
		
		
		
		
		
		
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(Xiugai4.this, InputInformationActivity.class);
				startActivity(intent);
			}
		});
		
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(jiechu.isChecked()){
					if(editText1.getText().toString().equals("")||
							editText2.getText().toString().equals("")||
							editText3.getText().toString().equals("")||
							editText4.getText().toString().equals("")
							){
						Toast.makeText(Xiugai4.this, "������������Ϣ", Toast.LENGTH_SHORT).show();;
					}
					else{
					
					
					SQLiteDatabase UserWrite = date_borrow.getWritableDatabase();
					SQLiteDatabase List = date_list.getWritableDatabase();
//					SQLiteDatabase UserRead = date_borrow.getReadableDatabase();
					ContentValues cv = new ContentValues();
					ContentValues cv2 = new ContentValues();
					cv.put("BORROW_NO",editText1.getText().toString());
					cv.put("BORROW_NAMEE",editText2.getText().toString());
					cv.put("BORROW_NAMEF", PaymentsManagementActivity.no.toString());
					cv.put("BORROW_PNO",editText3.getText().toString());
					cv.put("BORROW_MONE",editText4.getText().toString());
					cv.put("BORROW_DATE",textView1.getText().toString());
					
					cv2.put("LIST_NO", PaymentsManagementActivity.no.toString());
					cv2.put("LIST_DATE", textView1.getText().toString());
					cv2.put("LIST_TYPE", "Ǯ�ƽ���ÿ��ˣ�");
					cv2.put("LIST_NAME", editText2.getText().toString());
					cv2.put("LIST_MONEY", editText4.getText().toString());
					cv2.put("LIST_XUHAO", editText1.getText().toString());
					
					List.update("LIST", cv2,"LIST_NO=? and LIST_XUHAO=? and LIST_TYPE=?",new String[]{PaymentsManagementActivity.no, InputChangeActivity.number,"Ǯ�ƽ���ÿ��ˣ�"});
					UserWrite.update("BORROW", cv,"BORROW_NAMEF=? and BORROW_NO=?", new String[]{PaymentsManagementActivity.no, InputChangeActivity.number});
					UserWrite.close();
					Toast.makeText(Xiugai4.this,"�޸ĳɹ�", Toast.LENGTH_SHORT).show();
					
					editText1.setText("");
					editText2.setText("");
					editText3.setText("");
					editText4.setText("");
					
					
//					Cursor c = UserRead.query("BORROW", null, null, null , null, null, null);//qweqwe
//					while(c.moveToNext()){
//						String NO = c.getString(c.getColumnIndex("BORROW_NO"));
//						String USER_NAME = c.getString(c.getColumnIndex("BORROW_NAMEE"));
//						String USER_PASSWORD = c.getString(c.getColumnIndex("BORROW_NAMEF"));
//						String USER_POSE = c.getString(c.getColumnIndex("BORROW_PNO"));
//						String USER_FINAN = c.getString(c.getColumnIndex("BORROW_MONE"));
//						System.out.println(NO+" "+USER_NAME+" "+USER_PASSWORD+" "+USER_POSE+" "+USER_FINAN+" ");
//					}
					
					
					}
				}
				if(huankuan.isChecked()){
					if(editText1.getText().toString().equals("")||
							editText2.getText().toString().equals("")||
							editText3.getText().toString().equals("")||
							editText4.getText().toString().equals("")
							){
						Toast.makeText(Xiugai4.this, "������������Ϣ", Toast.LENGTH_SHORT).show();;
					}
					else{
					
					SQLiteDatabase UserWrite = date_repay.getWritableDatabase();
					SQLiteDatabase List = date_list.getWritableDatabase();
//					SQLiteDatabase UserRead = date_repay.getReadableDatabase();
					ContentValues cv = new ContentValues();
					ContentValues cv2 = new ContentValues();
					cv.put("REPAY_NO",editText1.getText().toString());
					cv.put("REPAY_NAMEE",editText2.getText().toString());
					cv.put("REPAY_NAMEF", PaymentsManagementActivity.no.toString());
					cv.put("REPAY_PNO",editText3.getText().toString());
					cv.put("REPAY_MONE",editText4.getText().toString());
					cv.put("REPAY_DATE",textView1.getText().toString());
					
					cv2.put("LIST_NO", PaymentsManagementActivity.no.toString());
					cv2.put("LIST_DATE", textView1.getText().toString());
					cv2.put("LIST_TYPE", "Ǯ�ƹ黹�����ˣ�");
					cv2.put("LIST_NAME", editText2.getText().toString());
					cv2.put("LIST_MONEY", editText4.getText().toString());
					cv2.put("LIST_XUHAO", editText1.getText().toString());
					
					List.update("LIST", cv2,"LIST_NO=? and LIST_XUHAO=? and LIST_TYPE=?",new String[]{PaymentsManagementActivity.no, InputChangeActivity.number,"Ǯ�ƹ黹�����ˣ�"});
					UserWrite.update("REPAY", cv, "REPAY_NAMEF=? and REPAY_NO=?", new String[]{PaymentsManagementActivity.no, InputChangeActivity.number});
					UserWrite.close();
					Toast.makeText(Xiugai4.this,"�޸ĳɹ�", Toast.LENGTH_SHORT).show();
					
					editText1.setText("");
					editText2.setText("");
					editText3.setText("");
					editText4.setText("");
//					
//					Cursor c = UserRead.query("REPAY", null, null, null , null, null, null);//qweqwe
//					while(c.moveToNext()){
//						String NO = c.getString(c.getColumnIndex("REPAY_NO"));
//						String USER_NAME = c.getString(c.getColumnIndex("REPAY_NAMEE"));
//						String USER_PASSWORD = c.getString(c.getColumnIndex("REPAY_NAMEF"));
//						String USER_POSE = c.getString(c.getColumnIndex("REPAY_PNO"));
//						String USER_FINAN = c.getString(c.getColumnIndex("REPAY_MONE"));
//						System.out.println(NO+" "+USER_NAME+" "+USER_PASSWORD+" "+USER_POSE+" "+USER_FINAN+" ");
//					}
//					
					}
				}
			}
		});

	}

}
