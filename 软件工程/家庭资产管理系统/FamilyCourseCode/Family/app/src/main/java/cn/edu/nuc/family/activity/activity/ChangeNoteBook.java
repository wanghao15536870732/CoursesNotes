package cn.edu.nuc.family.activity.activity;

import java.util.Calendar;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.edu.nuc.family.R;
import cn.edu.nuc.family.activity.datebase.Date_Notepad;

public class ChangeNoteBook extends Activity {
	private Button button1;
	private EditText editText1;
	private EditText editText2;
	private String date;
	Calendar c = Calendar.getInstance(); 
	Date_Notepad date_notepad = new Date_Notepad(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_note);
		
		button1 = (Button) findViewById(R.id.button1);
		editText1 = (EditText) findViewById(R.id.editText1);
		editText2 = (EditText) findViewById(R.id.editText2);
		
		editText1.setText(NoteBookActivity.tittle);
		editText2.setText(NoteBookActivity.content);
		
		
		date = (c.get(Calendar.YEAR)+"-"+c.get(Calendar.MONTH)+1+"-"+c.get(Calendar.DAY_OF_MONTH));
		
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SQLiteDatabase UserWrite = date_notepad.getWritableDatabase();
				ContentValues cv = new ContentValues();
				
				cv.put("NOTEPAD_NAME",editText1.getText().toString());
				cv.put("NOTEPAD_NOT",editText2.getText().toString());
				cv.put("NOTEPAD_DATE",date);
				cv.put("NOTEPAD_NO", PaymentsManagementActivity.no);

				UserWrite.update("NOTEPAD", cv, "NOTEPAD_NO=?",new String[]{PaymentsManagementActivity.no});
				UserWrite.close();
				editText1.setText("");
				editText2.setText("");
				Toast.makeText(ChangeNoteBook.this,"修改成功", Toast.LENGTH_SHORT).show();
				finish();
			}
		});
	}
}
