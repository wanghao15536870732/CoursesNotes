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
import cn.edu.nuc.family.activity.datebase.Date_Borrow;
import cn.edu.nuc.family.activity.datebase.Date_List;
import cn.edu.nuc.family.activity.datebase.Date_Repay;

public class LoanInputActivity extends Activity {

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
	private RadioButton loaned;
	private RadioButton returned;
	Calendar c = Calendar.getInstance(); 
	Date_List date_list = new Date_List(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loan_input);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		riqi = (Button) findViewById(R.id.riqi);
		textView1 = (TextView) findViewById(R.id.textView1);
		editText1 = (EditText) findViewById(R.id.editText1);
		editText2 = (EditText) findViewById(R.id.editText2);
		editText3 = (EditText) findViewById(R.id.editText3);
		editText4 = (EditText) findViewById(R.id.editText4);
		loaned = (RadioButton) findViewById(R.id.jiechu);
		returned = (RadioButton) findViewById(R.id.huankuan);

		textView1.setText(c.get(Calendar.YEAR)+"-"+c.get(Calendar.MONTH)+1+"-"+c.get(Calendar.DAY_OF_MONTH));
		riqi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new DatePickerDialog(LoanInputActivity.this, new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						textView1.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
					}
				}, c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH)).show();

			}
		});
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
//				Intent intent=new Intent();
//				intent.setClass(LoanInputActivity.this, InputInformationActivity.class);
//				startActivity(intent);
			}
		});

		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(loaned.isChecked()){
					if(editText1.getText().toString().equals("")|| editText2.getText().toString().equals("")||
							editText3.getText().toString().equals("")|| editText4.getText().toString().equals("")){
						Toast.makeText(LoanInputActivity.this, "请输入完整信息", Toast.LENGTH_SHORT).show();
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
						cv2.put("LIST_TYPE", "理财收入：");
						cv2.put("LIST_NAME", editText2.getText().toString());
						cv2.put("LIST_MONEY", editText4.getText().toString());
						cv2.put("LIST_XUHAO", editText1.getText().toString());
						List.insert("LIST", null, cv2);
						UserWrite.insert("BORROW", null, cv);
						UserWrite.close();
						Toast.makeText(LoanInputActivity.this,"添加成功", Toast.LENGTH_SHORT).show();
						editText1.setText("");
						editText2.setText("");
						editText3.setText("");
						editText4.setText("");
					}
				}
				if(returned.isChecked()){
						if(editText1.getText().toString().equals("")|| editText2.getText().toString().equals("")||
								editText3.getText().toString().equals("")|| editText4.getText().toString().equals("")){
							Toast.makeText(LoanInputActivity.this, "请输入完整信息", Toast.LENGTH_SHORT).show();
						}
						else{

							SQLiteDatabase UserWrite = date_repay.getWritableDatabase();
							SQLiteDatabase List = date_list.getWritableDatabase();
							//					SQLiteDatabase UserRead = date_repay.getReadableDatabase();
							ContentValues cv = new ContentValues();
							ContentValues cv2 = new ContentValues();
							cv.put("REPAY_NO",editText1.getText().toString());
							cv.put("REPAY_NAMEE",editText2.getText().toString());
							cv.put("REPAY_NAMEF", PaymentsManagementActivity.no);
							cv.put("REPAY_PNO",editText3.getText().toString());
							cv.put("REPAY_MONE",editText4.getText().toString());
							cv.put("REPAY_DATE",textView1.getText().toString());

							cv2.put("LIST_NO", PaymentsManagementActivity.no);
							cv2.put("LIST_DATE", textView1.getText().toString());
							cv2.put("LIST_TYPE", "理财支出：");
							cv2.put("LIST_NAME", editText2.getText().toString());
							cv2.put("LIST_MONEY", editText4.getText().toString());
							cv2.put("LIST_XUHAO", editText1.getText().toString());

							List.insert("LIST", null, cv2);
							UserWrite.insert("REPAY", null, cv);
							UserWrite.close();
							Toast.makeText(LoanInputActivity.this,"添加成功", Toast.LENGTH_SHORT).show();
							editText1.setText("");
							editText2.setText("");
							editText3.setText("");
							editText4.setText("");
						}
						finish();
				}
			}
		});
	}
}
