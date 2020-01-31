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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.nuc.family.R;
import cn.edu.nuc.family.activity.activity.InputChangeActivity;
import cn.edu.nuc.family.activity.activity.InputInformationActivity;
import cn.edu.nuc.family.activity.activity.PaymentsManagementActivity;
import cn.edu.nuc.family.activity.datebase.Date_DailyIncome;
import cn.edu.nuc.family.activity.datebase.Date_DailyOutcome;
import cn.edu.nuc.family.activity.datebase.Date_List;
import cn.edu.nuc.family.activity.util.SnackbarUtil;

public class DailyInComeChange extends Activity {
	Calendar c = Calendar.getInstance();  
	private TextView riqi;
	private Button queding;
	private Button quxiao;
	private Button button1;
	private EditText editText1;
	private EditText editText2;
	private EditText editText3;
	private RadioButton radioButton1;
	private RadioButton radioButton2;
	Date_DailyIncome date_Dailyincome = new Date_DailyIncome(this);
	Date_DailyOutcome date_Dailyoutcome = new Date_DailyOutcome(this);
	Date_List date_list = new Date_List(this);
	private RelativeLayout mLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_daily_change);
		riqi = findViewById(R.id.riqi);
		queding = findViewById(R.id.queding);
		quxiao = findViewById(R.id.quxiao);
		button1 = findViewById(R.id.button1);
		editText1 = findViewById(R.id.editText1);
		editText2 = findViewById(R.id.editText2);
		editText3 = findViewById(R.id.editText3);
		radioButton1 = findViewById(R.id. radioButton1);
		radioButton2 = findViewById(R.id. radioButton2);
		mLayout = findViewById(R.id.daily_change);
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new DatePickerDialog(DailyInComeChange.this, new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						riqi.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
					}
				}, c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH)).show();

			}
		});

		if(InputChangeActivity.tableName.equals("日常收入：")){
			ContentValues cv = new ContentValues();
			SQLiteDatabase UserRead = date_Dailyincome.getReadableDatabase();
			Cursor c = UserRead.query("DAILYINCOME", null, "DAILYINCOME_PEOPLE=? and DAILYINCOME_NO=?", new String[]{PaymentsManagementActivity.no, InputChangeActivity.number} , null, null, null);
			while(c.moveToNext()){

				editText1.setText(c.getString(1));
				editText2.setText(c.getString(2));
				editText3.setText(c.getString(4));
				riqi.setText(c.getString(5));
			}
		}else{
			ContentValues cv = new ContentValues();
			SQLiteDatabase UserRead = date_Dailyoutcome.getReadableDatabase();
			Cursor c = UserRead.query("DAILYOUTCOME", null, "DAILYOUTCOME_PEOPLE=? and DAILYOUTCOME_NO=?", new String[]{PaymentsManagementActivity.no, InputChangeActivity.number} , null, null, null);
			while(c.moveToNext()){
				editText1.setText(c.getString(1));
				editText2.setText(c.getString(2));
				editText3.setText(c.getString(4));
				riqi.setText(c.getString(5));
				
			}
		}

		quxiao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(DailyInComeChange.this, InputInformationActivity.class);
				startActivity(intent);
			}
		});

		queding.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(radioButton1.isChecked()){
					if(editText1.getText().toString().equals("")||
							editText2.getText().toString().equals("")||
							editText3.getText().toString().equals("")){
						Toast.makeText(DailyInComeChange.this, "请输入完整信息", Toast.LENGTH_SHORT).show();
					} else{
						SQLiteDatabase UserWrite = date_Dailyincome.getWritableDatabase();
						SQLiteDatabase List = date_list.getWritableDatabase();
						//					SQLiteDatabase UserRead = date_Dailyincome.getReadableDatabase();
						ContentValues cv = new ContentValues();
						ContentValues cv2 = new ContentValues();
						cv.put("DAILYINCOME_NO",editText1.getText().toString());
						cv.put("DAILYINCOME_NAME",editText2.getText().toString());
						cv.put("DAILYINCOME_PEOPLE", PaymentsManagementActivity.no.toString());
						cv.put("DAILYINCOME_NUMBE",editText3.getText().toString());
						cv.put("DAILYINCOME_DATE",riqi.getText().toString());

						cv2.put("LIST_NO", PaymentsManagementActivity.no.toString());
						cv2.put("LIST_DATE", riqi.getText().toString());
						cv2.put("LIST_TYPE", "日常收入：");
						cv2.put("LIST_NAME", editText2.getText().toString());
						cv2.put("LIST_MONEY", editText3.getText().toString());
						cv2.put("LIST_XUHAO", editText1.getText().toString());

						List.update("LIST", cv2, "LIST_NO=? and LIST_XUHAO=? and LIST_TYPE=?",new String[]{PaymentsManagementActivity.no, InputChangeActivity.number,"�ճ����룺"});
						UserWrite.update("DAILYINCOME", cv, "DAILYINCOME_PEOPLE=? and DAILYINCOME_NO=?", new String[]{PaymentsManagementActivity.no, InputChangeActivity.number} );
						UserWrite.close();
						SnackbarUtil.makeSnackBar(mLayout,"修改成功!");
						editText1.setText("");
						editText2.setText("");
						editText3.setText("");
					}
				}
				if(radioButton2.isChecked()){
					if(editText1.getText().toString().equals("")||
							editText2.getText().toString().equals("")||
							editText3.getText().toString().equals("")){
						SnackbarUtil.makeSnackBar(mLayout,"请输入完整信息!");
					}
					else{
						SQLiteDatabase UserWrite = date_Dailyoutcome.getWritableDatabase();
						SQLiteDatabase List = date_list.getWritableDatabase();
						ContentValues cv = new ContentValues();
						ContentValues cv2 = new ContentValues();
						cv.put("DAILYOUTCOME_NO",editText1.getText().toString());
						cv.put("DAILYOUTCOME_NAME",editText2.getText().toString());
						cv.put("DAILYOUTCOME_PEOPLE", PaymentsManagementActivity.no.toString());
						cv.put("DAILYOUTCOME_NUMBE",editText3.getText().toString());
						cv.put("DAILYOUTCOME_DATE",riqi.getText().toString());
						cv2.put("LIST_NO", PaymentsManagementActivity.no.toString());
						cv2.put("LIST_DATE", riqi.getText().toString());
						cv2.put("LIST_TYPE", "日常支出：");
						cv2.put("LIST_NAME", editText2.getText().toString());
						cv2.put("LIST_MONEY", editText3.getText().toString());
						cv2.put("LIST_XUHAO", editText1.getText().toString());
						List.update("LIST", cv2, "LIST_NO=? and LIST_XUHAO=? and LIST_TYPE=?",new String[]{PaymentsManagementActivity.no, InputChangeActivity.number,"�ճ�֧����"});
						UserWrite.update("DAILYOUTCOME", cv, "DAILYOUTCOME_PEOPLE=? and DAILYOUTCOME_NO=?", new String[]{PaymentsManagementActivity.no, InputChangeActivity.number} );
						UserWrite.close();
						SnackbarUtil.makeSnackBar(mLayout,"修改成功!");
						editText1.setText("");
						editText2.setText("");
						editText3.setText("");
					}
				}
			}
		});
	}
}
