package cn.edu.nuc.family.activity.base;

import java.util.Calendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
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
import cn.edu.nuc.family.activity.activity.InputInformationActivity;
import cn.edu.nuc.family.activity.activity.PaymentsManagementActivity;
import cn.edu.nuc.family.activity.datebase.Date_Financialincome;
import cn.edu.nuc.family.activity.datebase.Date_Financialoutcome;
import cn.edu.nuc.family.activity.datebase.Date_List;

public class Luru3 extends Activity {
	
	Date_Financialincome date_Financialincome = new Date_Financialincome(this);
	Date_Financialoutcome date_Financialoutcome = new Date_Financialoutcome(this);
	private TextView textView1;
	private Button button1;
	private Button queding;
	private Button quxiao;
	private EditText editText1;
	private EditText editText2;
	private EditText editText3;
	private RadioButton shouru;
	private RadioButton zhichu;
	Calendar c = Calendar.getInstance(); 
	Date_List date_list = new Date_List(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.luru3);
		textView1 = (TextView) findViewById(R.id.textView1);
		button1 = (Button) findViewById(R.id.button1);
		queding = (Button) findViewById(R.id.queding);
		quxiao = (Button) findViewById(R.id.quxiao);
		editText1 = (EditText) findViewById(R.id.editText1);
		editText2 = (EditText) findViewById(R.id.editText2);
		editText3 = (EditText) findViewById(R.id.editText3);
		shouru = (RadioButton) findViewById(R.id.shouru);
		zhichu = (RadioButton) findViewById(R.id.zhichu);
		
		
		textView1.setText(c.get(Calendar.YEAR)+"-"+c.get(Calendar.MONTH)+1+"-"+c.get(Calendar.DAY_OF_MONTH));
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new DatePickerDialog(Luru3.this, new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						textView1.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
					}
				}, c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH)).show();

			}
		});
		quxiao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(Luru3.this, InputInformationActivity.class);
				startActivity(intent);
			}
		});
		
		queding.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(shouru.isChecked()){
					if(editText1.getText().toString().equals("")||
							editText2.getText().toString().equals("")||
							editText3.getText().toString().equals("")
							){
						Toast.makeText(Luru3.this, "������������Ϣ", Toast.LENGTH_SHORT).show();;
					}
					else{
					
					
					
					SQLiteDatabase UserWrite = date_Financialincome.getWritableDatabase();
					SQLiteDatabase List = date_list.getWritableDatabase();
//					SQLiteDatabase UserRead = date_Financialincome.getReadableDatabase();
					ContentValues cv = new ContentValues();
					ContentValues cv2 = new ContentValues();
					cv.put("FINANCIALINCOME_NO",editText1.getText().toString());
					cv.put("FINANCIALINCOME_NA",editText2.getText().toString());
					cv.put("FINANCIALINCOME_IN",editText3.getText().toString());
					cv.put("FINANCIALINCOME_P", PaymentsManagementActivity.no.toString());
					cv.put("FINANCIALINCOME_DAT",textView1.getText().toString());
					
					cv2.put("LIST_NO", PaymentsManagementActivity.no.toString());
					cv2.put("LIST_DATE", textView1.getText().toString());
					cv2.put("LIST_TYPE", "������룺");
					cv2.put("LIST_NAME", editText2.getText().toString());
					cv2.put("LIST_MONEY", editText3.getText().toString());
					cv2.put("LIST_XUHAO", editText1.getText().toString());
					
					List.insert("LIST", null, cv2);
					UserWrite.insert("FINANCIALINCOME", null, cv);
					UserWrite.close();
					Toast.makeText(Luru3.this,"��ӳɹ�", Toast.LENGTH_SHORT).show();
					editText1.setText("");
					editText2.setText("");
					editText3.setText("");
//					
//					Cursor c = UserRead.query("FINANCIALINCOME", null, null, null , null, null, null);//qweqwe
//					while(c.moveToNext()){
//						String NO = c.getString(c.getColumnIndex("FINANCIALINCOME_NO"));
//						String USER_NAME = c.getString(c.getColumnIndex("FINANCIALINCOME_NA"));
//						String USER_PASSWORD = c.getString(c.getColumnIndex("FINANCIALINCOME_DAT"));
//						String USER_POSE = c.getString(c.getColumnIndex("FINANCIALINCOME_IN"));
//						String USER_FINAN = c.getString(c.getColumnIndex("FINANCIALINCOME_P"));
//						System.out.println(NO+" "+USER_NAME+" "+USER_PASSWORD+" "+USER_POSE+" "+USER_FINAN+" ");
//					}
					}
					
					
				}
				if(zhichu.isChecked()){
					if(editText1.getText().toString().equals("")||
							editText2.getText().toString().equals("")||
							editText3.getText().toString().equals("")
							){
						Toast.makeText(Luru3.this, "������������Ϣ", Toast.LENGTH_SHORT).show();;
					}
					else{
					
					
					SQLiteDatabase UserWrite = date_Financialoutcome.getWritableDatabase();
					SQLiteDatabase List = date_list.getWritableDatabase();
//					SQLiteDatabase UserRead = date_Financialoutcome.getReadableDatabase();
					ContentValues cv = new ContentValues();
					ContentValues cv2 = new ContentValues();
					cv.put("FINANCIALOUTCOME_NO",editText1.getText().toString());
					cv.put("FINANCIALOUTCOME_NA",editText2.getText().toString());
					cv.put("FINANCIALOUTCOME_IN",editText3.getText().toString());
					cv.put("FINANCIALOUTCOME_P", PaymentsManagementActivity.no.toString());
					cv.put("FINANCIALOUTCOME_DAT",textView1.getText().toString());
					
					cv2.put("LIST_NO", PaymentsManagementActivity.no.toString());
					cv2.put("LIST_DATE", textView1.getText().toString());
					cv2.put("LIST_TYPE", "���֧����");
					cv2.put("LIST_NAME", editText2.getText().toString());
					cv2.put("LIST_MONEY", editText3.getText().toString());
					cv2.put("LIST_XUHAO", editText1.getText().toString());
					
					List.insert("LIST", null, cv2);
					UserWrite.insert("FINANCIALOUTCOME", null, cv);
					UserWrite.close();
					Toast.makeText(Luru3.this,"��ӳɹ�", Toast.LENGTH_SHORT).show();
					
					editText1.setText("");
					editText2.setText("");
					editText3.setText("");
//					Cursor c = UserRead.query("FINANCIALOUTCOME", null, null, null , null, null, null);//qweqwe
//					while(c.moveToNext()){
//						String NO = c.getString(c.getColumnIndex("FINANCIALOUTCOME_NO"));
//						String USER_NAME = c.getString(c.getColumnIndex("FINANCIALOUTCOME_NA"));
//						String USER_PASSWORD = c.getString(c.getColumnIndex("FINANCIALCOME_DAT"));
//						String USER_POSE = c.getString(c.getColumnIndex("FINANCIALOUTCOME_IN"));
//						String USER_FINAN = c.getString(c.getColumnIndex("FINANCIALOUTCOME_P"));
//						System.out.println(NO+" "+USER_NAME+" "+USER_PASSWORD+" "+USER_POSE+" "+USER_FINAN+" ");
//					}
					
					
					}
				}
				
			}
		});

	}

}
