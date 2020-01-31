package cn.edu.nuc.family.activity.activity;

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
import cn.edu.nuc.family.activity.datebase.Date_DailyIncome;
import cn.edu.nuc.family.activity.datebase.Date_DailyOutcome;
import cn.edu.nuc.family.activity.datebase.Date_List;

public class GeneralInputActivity extends Activity {
	Calendar calendar = Calendar.getInstance();
	private TextView date;
	private Button sureBtn;
	private Button cancelBtn;
	private Button chooseBtn;
	private EditText editText1;
	private EditText editText2;
	private EditText editText3;
	private RadioButton radioButton1;
	private RadioButton radioButton2;
	Date_DailyIncome date_Dailyincome = new Date_DailyIncome(this);
	Date_DailyOutcome date_Dailyoutcome = new Date_DailyOutcome(this);
	Date_List date_list = new Date_List(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_general_input);
		date = findViewById(R.id.date);
		sureBtn = findViewById(R.id.sureBtn);
		cancelBtn = findViewById(R.id.cancelBtn);
		chooseBtn = findViewById(R.id.choose_date);
		editText1 = findViewById(R.id.general_editText1);
		editText2 = findViewById(R.id.general_editText2);
		editText3 = findViewById(R.id.general_editText3);
		radioButton1= findViewById(R.id.radioButton1);
		radioButton2= findViewById(R.id.radioButton2);
		date.setText(calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+1+"-"+calendar.get(Calendar.DAY_OF_MONTH));
		chooseBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new DatePickerDialog(
						GeneralInputActivity.this, new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker view, int year,
										  int monthOfYear, int dayOfMonth) {
						date.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
					}
				}, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1,
						calendar.get(Calendar.DAY_OF_MONTH)).show();

			}
		});
		cancelBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(GeneralInputActivity.this, InputInformationActivity.class);
				startActivity(intent);
			}
		});

		sureBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(radioButton1.isChecked()){
					if(editText1.getText().toString().equals("")||
							editText2.getText().toString().equals("")||
							editText3.getText().toString().equals("")){
						Toast.makeText(GeneralInputActivity.this, "请输入完整信息", Toast.LENGTH_SHORT).show();;
						System.out.println("qweqwe");
					} else{
						SQLiteDatabase UserWrite = date_Dailyincome.getWritableDatabase();
						SQLiteDatabase List = date_list.getWritableDatabase();
						//SQLiteDatabase UserRead = date_Dailyincome.getReadableDatabase();
						ContentValues cv = new ContentValues();
						ContentValues cv2 = new ContentValues();
						cv.put("DAILYINCOME_NO",editText1.getText().toString());
						cv.put("DAILYINCOME_NAME",editText2.getText().toString());
						cv.put("DAILYINCOME_PEOPLE", PaymentsManagementActivity.no.toString());
						cv.put("DAILYINCOME_NUMBE",editText3.getText().toString());
						cv.put("DAILYINCOME_DATE",date.getText().toString());

						cv2.put("LIST_NO", PaymentsManagementActivity.no.toString());
						cv2.put("LIST_DATE", date.getText().toString());
						cv2.put("LIST_TYPE", "日常收入：");
						cv2.put("LIST_NAME", editText2.getText().toString());
						cv2.put("LIST_MONEY", editText3.getText().toString());
						cv2.put("LIST_XUHAO", editText1.getText().toString());

						List.insert("LIST", null, cv2);
						UserWrite.insert("DAILYINCOME", null, cv);
						UserWrite.close();

						editText1.setText("");
						editText2.setText("");
						editText3.setText("");
						Toast.makeText(GeneralInputActivity.this,"添加成功", Toast.LENGTH_SHORT).show();
					}
				}
				if(radioButton2.isChecked()){
					if(editText1.getText().toString().equals("")||
							editText2.getText().toString().equals("")||
							editText3.getText().toString().equals("")){
						Toast.makeText(GeneralInputActivity.this, "请输入完整信息", Toast.LENGTH_SHORT).show();
					}else{
						SQLiteDatabase UserWrite = date_Dailyoutcome.getWritableDatabase();
						SQLiteDatabase List = date_list.getWritableDatabase();
						ContentValues cv = new ContentValues();
						ContentValues cv2 = new ContentValues();
						cv.put("DAILYOUTCOME_NO",editText1.getText().toString());
						cv.put("DAILYOUTCOME_NAME",editText2.getText().toString());
						cv.put("DAILYOUTCOME_PEOPLE", PaymentsManagementActivity.no.toString());
						cv.put("DAILYOUTCOME_NUMBE",editText3.getText().toString());
						cv.put("DAILYOUTCOME_DATE",date.getText().toString());

						cv2.put("LIST_NO", PaymentsManagementActivity.no.toString());
						cv2.put("LIST_DATE", date.getText().toString());
						cv2.put("LIST_TYPE", "日常支出：");
						cv2.put("LIST_NAME", editText2.getText().toString());
						cv2.put("LIST_MONEY", editText3.getText().toString());
						cv2.put("LIST_XUHAO", editText1.getText().toString());

						UserWrite.insert("DAILYOUTCOME", null, cv);
						List.insert("LIST", null, cv2);
						UserWrite.close();
						Toast.makeText(GeneralInputActivity.this,"添加成功", Toast.LENGTH_SHORT).show();

						editText1.setText("");
						editText2.setText("");
						editText3.setText("");
					}
				}
			}
		});
	}

	private void addIncome(String onNumber,String incomeName,String date,String type,String money,String noMember,String nameMember){
		SQLiteDatabase UserWrite = date_Dailyincome.getWritableDatabase();
		SQLiteDatabase List = date_list.getWritableDatabase();
		//SQLiteDatabase UserRead = date_Dailyincome.getReadableDatabase();
		ContentValues cv = new ContentValues();
		ContentValues cv2 = new ContentValues();
		cv.put("DAILYINCOME_NO",onNumber);
		cv.put("DAILYINCOME_NAME",incomeName);
		cv.put("DAILYINCOME_PEOPLE", noMember);
		cv.put("DAILYINCOME_NUMBE",money);
		cv.put("DAILYINCOME_DATE",date);

		cv2.put("LIST_NO", noMember);
		cv2.put("LIST_DATE", date);
		cv2.put("LIST_TYPE", type);
		cv2.put("LIST_NAME", incomeName);
		cv2.put("LIST_MONEY", money);
		cv2.put("LIST_XUHAO", onNumber);

		List.insert("LIST", null, cv2);
		UserWrite.insert("DAILYINCOME", null, cv);
		UserWrite.close();

		editText1.setText("");
		editText2.setText("");
		editText3.setText("");
		Toast.makeText(GeneralInputActivity.this,"添加成功", Toast.LENGTH_SHORT).show();
	}

}
