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
import cn.edu.nuc.family.activity.datebase.Date_Bankincome;
import cn.edu.nuc.family.activity.datebase.Date_Bankoutcome;
import cn.edu.nuc.family.activity.datebase.Date_List;

public class Xiugai2 extends Activity {

	Date_Bankincome date_bankincome = new Date_Bankincome(this);
	Date_Bankoutcome date_bankoutcome = new Date_Bankoutcome(this);
	Date_List date_list = new Date_List(this);
	Calendar c = Calendar.getInstance(); 
	private TextView riqi;
	private Button button1;
	private Button queding;
	private Button quxiao;
	private EditText editText1;
	private EditText editText2;
	private EditText editText3;
	private EditText editText4;
	private EditText editText5;
	private EditText editText6;
	private RadioButton radio0;
	private RadioButton radio1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xiugai2);

		button1 = (Button) findViewById(R.id.button1);
		riqi = (TextView) findViewById(R.id.riqi);
		queding = (Button) findViewById(R.id.queding);
		quxiao = (Button) findViewById(R.id.quxiao);
		editText1 = (EditText) findViewById(R.id.editText1);
		editText2 = (EditText) findViewById(R.id.editText2);
		editText3 = (EditText) findViewById(R.id.editText3);
		editText4 = (EditText) findViewById(R.id.editText4);
		editText5 = (EditText) findViewById(R.id.editText5);
		editText6 = (EditText) findViewById(R.id.editText6);
		radio0 = (RadioButton) findViewById(R.id.radio0);
		radio1 = (RadioButton) findViewById(R.id.radio1);
		
		
		
		riqi.setText(c.get(Calendar.YEAR)+"-"+c.get(Calendar.MONTH)+1+"-"+c.get(Calendar.DAY_OF_MONTH));
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new DatePickerDialog(Xiugai2.this, new DatePickerDialog.OnDateSetListener() {
					
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						riqi.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
					}
				}, c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH)).show();
				
			}
		});

		

		if(InputChangeActivity.tableName.equals("���д��룺")){
			ContentValues cv = new ContentValues();
			SQLiteDatabase UserRead = date_bankincome.getReadableDatabase();
			Cursor c = UserRead.query("BANKINCOME", null, "BANKINCOME_NAME=? and BANKINCOME_NO=?", new String[]{PaymentsManagementActivity.no, InputChangeActivity.number} , null, null, null);
			while(c.moveToNext()){

				editText1.setText(c.getString(1));
				editText2.setText(c.getString(2));
				editText3.setText(c.getString(4));
				editText4.setText(c.getString(5));
				editText5.setText(c.getString(7));
				editText6.setText(c.getString(8));
				riqi.setText(c.getString(6));
			}
		}else{
			ContentValues cv = new ContentValues();
			SQLiteDatabase UserRead = date_bankoutcome.getReadableDatabase();
			Cursor c = UserRead.query("BANKOUTCOME", null, "BANKOUTCOME_NAME=? and BANKOUTCOME_NO=?", new String[]{PaymentsManagementActivity.no, InputChangeActivity.number} , null, null, null);
			while(c.moveToNext()){
				editText1.setText(c.getString(1));
				editText2.setText(c.getString(2));
				editText3.setText(c.getString(4));
				editText4.setText(c.getString(5));
				editText5.setText(c.getString(7));
				editText6.setText(c.getString(8));
				riqi.setText(c.getString(6));

			}
		}





		quxiao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(Xiugai2.this, InputInformationActivity.class);
				startActivity(intent);
			}
		});

		queding.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(radio0.isChecked()){
					if(editText1.getText().toString().equals("")||
							editText2.getText().toString().equals("")||
							editText3.getText().toString().equals("")||
							editText4.getText().toString().equals("")||
							editText5.getText().toString().equals("")||
							editText6.getText().toString().equals("")){
						Toast.makeText(Xiugai2.this, "������������Ϣ", Toast.LENGTH_SHORT).show();;
					}
					else{
					
					
					
					SQLiteDatabase UserWrite = date_bankincome.getWritableDatabase();
					SQLiteDatabase List = date_list.getWritableDatabase();
					//					SQLiteDatabase UserRead = date_bankincome.getReadableDatabase();
					ContentValues cv = new ContentValues();
					ContentValues cv2 = new ContentValues();
					cv.put("BANKINCOME_NO",editText1.getText().toString());
					cv.put("BANKINCOME_NAMENO",editText2.getText().toString());
					cv.put("BANKINCOME_NAME", PaymentsManagementActivity.no.toString());
					cv.put("BANKINCOME_MONEY",editText3.getText().toString());
					cv.put("BANKINCOME_FREE",editText4.getText().toString());
					cv.put("BANKINCOME_DATE",riqi.getText().toString());
					cv.put("BANKINCOME_TYPE",editText5.getText().toString());
					cv.put("BANKINCOME_BNAME",editText6.getText().toString());

					cv2.put("LIST_NO", PaymentsManagementActivity.no.toString());
					cv2.put("LIST_DATE", riqi.getText().toString());
					cv2.put("LIST_TYPE", "���д��룺");
					cv2.put("LIST_NAME", editText5.getText().toString());
					cv2.put("LIST_MONEY", editText3.getText().toString());
					cv2.put("LIST_XUHAO", editText1.getText().toString());

					List.update("LIST", cv2,"LIST_NO=? and LIST_XUHAO=? and LIST_TYPE=?",new String[]{PaymentsManagementActivity.no, InputChangeActivity.number,"���д��룺"});
					UserWrite.update("BANKINCOME", cv,"BANKINCOME_NAME=? and BANKINCOME_NO=?", new String[]{PaymentsManagementActivity.no, InputChangeActivity.number});
					UserWrite.close();
					Toast.makeText(Xiugai2.this,"�޸ĳɹ�", Toast.LENGTH_SHORT).show();
					
					editText1.setText("");
					editText2.setText("");
					editText3.setText("");
					editText4.setText("");
					editText5.setText("");
					editText6.setText("");

					//					Cursor c = UserRead.query("BANKINCOME", null, null, null , null, null, null);//qweqwe
					//					while(c.moveToNext()){
					//						
					//						String NO = c.getString(c.getColumnIndex("BANKINCOME_NO"));
					//						String USER_NAME = c.getString(c.getColumnIndex("BANKINCOME_NAMENO"));
					//						String USER_PASSWORD = c.getString(c.getColumnIndex("BANKINCOME_NAME"));
					//						String USER_POSE = c.getString(c.getColumnIndex("BANKINCOME_MONEY"));
					//						String USER_FINAN = c.getString(c.getColumnIndex("BANKINCOME_FREE"));
					//						System.out.println(NO+" "+USER_NAME+" "+USER_PASSWORD+" "+USER_POSE+" "+USER_FINAN+" ");
					//					}


				}}
				if(radio1.isChecked()){
					if(editText1.getText().toString().equals("")||
							editText2.getText().toString().equals("")||
							editText3.getText().toString().equals("")||
							editText4.getText().toString().equals("")||
							editText5.getText().toString().equals("")||
							editText6.getText().toString().equals("")){
						Toast.makeText(Xiugai2.this, "������������Ϣ", Toast.LENGTH_SHORT).show();;
					}
					else{
					
					
					SQLiteDatabase UserWrite = date_bankoutcome.getWritableDatabase();
					SQLiteDatabase List = date_list.getWritableDatabase();
					//					SQLiteDatabase UserRead = date_bankoutcome.getReadableDatabase();
					ContentValues cv = new ContentValues();
					ContentValues cv2 = new ContentValues();
					cv.put("BANKOUTCOME_NO",editText1.getText().toString());
					cv.put("BANKOUTCOME_NAMENO",editText2.getText().toString());
					cv.put("BANKOUTCOME_NAME", PaymentsManagementActivity.no.toString());
					cv.put("BANKOUTCOME_MONEY",editText3.getText().toString());
					cv.put("BANKOUTCOME_FREE",editText4.getText().toString());
					cv.put("BANKOUTCOME_DATE",riqi.getText().toString());
					cv.put("BANKOUTCOME_TYPE",editText5.getText().toString());
					cv.put("BANKOUTCOME_BNAME",editText6.getText().toString());

					cv2.put("LIST_NO", PaymentsManagementActivity.no.toString());
					cv2.put("LIST_DATE", riqi.getText().toString());
					cv2.put("LIST_TYPE", "����ȡ����");
					cv2.put("LIST_NAME", editText5.getText().toString());
					cv2.put("LIST_MONEY", editText3.getText().toString());
					cv2.put("LIST_XUHAO", editText1.getText().toString());

					List.update("LIST", cv2,"LIST_NO=? and LIST_XUHAO=? and LIST_TYPE=?",new String[]{PaymentsManagementActivity.no, InputChangeActivity.number,"����ȡ����"});
					UserWrite.update("BANKOUTCOME", cv,"BANKOUTCOME_NAME=? and BANKOUTCOME_NO=?", new String[]{PaymentsManagementActivity.no, InputChangeActivity.number});
					UserWrite.close();
					Toast.makeText(Xiugai2.this,"�޸ĳɹ�", Toast.LENGTH_SHORT).show();
					editText1.setText("");
					editText2.setText("");
					editText3.setText("");
					editText4.setText("");
					editText5.setText("");
					editText6.setText("");


					//					Cursor c = UserRead.query("BANKOUTCOME", null, null, null , null, null, null);//qweqwe
					//					while(c.moveToNext()){
					//						
					//						String NO = c.getString(c.getColumnIndex("BANKOUTCOME_NO"));
					//						String USER_NAME = c.getString(c.getColumnIndex("BANKOUTCOME_NAMENO"));
					//						String USER_PASSWORD = c.getString(c.getColumnIndex("BANKOUTCOME_NAME"));
					//						String USER_POSE = c.getString(c.getColumnIndex("BANKOUTCOME_MONEY"));
					//						String USER_FINAN = c.getString(c.getColumnIndex("BANKOUTCOME_FREE"));
					//						System.out.println(NO+" "+USER_NAME+" "+USER_PASSWORD+" "+USER_POSE+" "+USER_FINAN+" ");
					//					}


				}

			}}
		});


	}

}
