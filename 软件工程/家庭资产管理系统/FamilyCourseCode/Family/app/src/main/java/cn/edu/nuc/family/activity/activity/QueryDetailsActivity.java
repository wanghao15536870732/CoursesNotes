package cn.edu.nuc.family.activity.activity;


import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.nuc.family.R;
import cn.edu.nuc.family.activity.datebase.Date_List;
import cn.edu.nuc.family.activity.util.SnackbarUtil;

public class QueryDetailsActivity extends ListActivity {
	private Button button1;
	private Button search;
	private EditText editText1;
	private TextView textView1;
	private TextView shouru;
	private TextView zhichu;
	SimpleCursorAdapter adapter,adapter1;
	private RadioButton shijian;
	private RadioButton jine;
	Date_List date_list = new Date_List(this);
	Calendar c = Calendar.getInstance();
	private ImageView back;
	private LinearLayout mLayout;

	@SuppressLint("SetTextI18n")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_query_detail);
		button1 = findViewById(R.id.button1);
		search = findViewById(R.id.sousuo);
		textView1 = findViewById(R.id.textView1);
		editText1 = findViewById(R.id.editText1);
		shijian = findViewById(R.id.shijian);
		jine = findViewById(R.id.jine);
		shouru = findViewById(R.id.shouru);
		zhichu = findViewById(R.id.zhichu);
		back = findViewById(R.id.query_back);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		textView1.setText(c.get(Calendar.YEAR)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.DAY_OF_MONTH));
		mLayout = findViewById(R.id.query_main);
		//先全部显示出来
		SQLiteDatabase dailyincome = date_list.getReadableDatabase();
		Cursor c1 = dailyincome.query("LIST", null, "LIST_NO=?", new String[]{PaymentsManagementActivity.no}, null, null, null);
		adapter = new SimpleCursorAdapter(this, R.layout.richangshouru_list_cell, c1, new String[]{"LIST_DATE","LIST_NAME","LIST_MONEY","LIST_TYPE","LIST_XUHAO","LIST_NO"}, new int[]{R.id.riqi,R.id.mingcheng,R.id.jine,R.id.leibie,R.id.xuhao});
		setListAdapter(adapter);

		//shouru zhichu
		double shouru1 = 0;
		double zhichu1 = 0;
		SQLiteDatabase listRead = date_list.getReadableDatabase();
		Cursor c2 = listRead.query("LIST", null, null, null, null, null, null);
		while(c2.moveToNext()){
			if(c2.getString(3).equals("日常收入：")||c2.getString(3).equals("银行存入：")||c2.getString(3).equals("理财收入：")||c2.getString(3).equals("钱财归还还款人：")){
				shouru1 = shouru1+Double.parseDouble(c2.getString(6));
			}else{
				zhichu1 = zhichu1+Double.parseDouble(c2.getString(6));
			}
		}
		shouru.setText("总收入："+shouru1);
		zhichu.setText("总支出："+zhichu1);
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new DatePickerDialog(QueryDetailsActivity.this,
						new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						textView1.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
						if(shijian.isChecked()){
							DateToTime();
						}
						if(jine.isChecked()){
							DateMoney();
						}
					}
				}, c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
		search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(shijian.isChecked()){
					SearchTime();
				}
				if(jine.isChecked()){
					SearchMoney();
				}
			}
		});
	}

	private void SearchTime(){
		Date_List date_list2= new Date_List(this);
		SQLiteDatabase date_list2Read = date_list2.getReadableDatabase();
		String txt = editText1.getText().toString();
		Pattern p = Pattern.compile("[0-9]*");
		Matcher m = p.matcher(txt);
		if(editText1.getText().toString().equals("")){
			SnackbarUtil.makeSnackBar(mLayout,"查询条件不能为空");
			Cursor c = date_list2Read.query("LIST", null,  "LIST_NO=? and LIST_DATE<=?",
					new String[]{PaymentsManagementActivity.no,textView1.getText().toString()}, null, null, "LIST_DATE");
			adapter.changeCursor(c);
			sz(c);
		}else if(m.matches()){
			Cursor c = date_list2Read.query("LIST", null,
					"LIST_NO=? and LIST_DATE<=? and LIST_XUHAO=? ",
					new String[]{PaymentsManagementActivity.no,
							textView1.getText().toString(),
							editText1.getText().toString()}, null, null, "LIST_DATE");
			if(c.getCount()==0){
				Toast.makeText(QueryDetailsActivity.this, "无", Toast.LENGTH_SHORT).show();
			}else{
				adapter.changeCursor(c);
				sz(c);
			}
		}else if(editText1.getText().toString().equals("理财收入")){
			Cursor c = date_list2Read.query("LIST", null,
					"LIST_NO=? and LIST_DATE<=? and LIST_TYPE=? ", new String[]{
							PaymentsManagementActivity.no,textView1.getText().toString(), "理财收入："
					}, null, null, "LIST_DATE");
			adapter.changeCursor(c);sz(c);
		}else if(editText1.getText().toString().equals("理财支出")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_NO=? and LIST_DATE<=? and LIST_TYPE=? ",
					new String[]{PaymentsManagementActivity.no,textView1.getText().toString(),"理财支出："}, null,
					null, "LIST_DATE");
			adapter.changeCursor(c);sz(c);
		}else if(editText1.getText().toString().equals("日常收入")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_NO=? and LIST_DATE<=? and LIST_TYPE=? ",
					new String[]{PaymentsManagementActivity.no,textView1.getText().toString(),"日常收入："}, null,
					null, "LIST_DATE");
			adapter.changeCursor(c);sz(c);
		}else if(editText1.getText().toString().equals("日常支出")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_NO=? and LIST_DATE<=? and LIST_TYPE=? ",
					new String[]{PaymentsManagementActivity.no,textView1.getText().toString(),"日常支出："}, null,
					null, "LIST_DATE");
			adapter.changeCursor(c);sz(c);
		}else if(editText1.getText().toString().equals("银行存入")){
			Cursor c = date_list2Read.query("LIST", null, "LIST_NO=? and LIST_DATE<=? and LIST_TYPE=? ",
					new String[]{PaymentsManagementActivity.no, textView1.getText().toString(),"银行存入："}, null,
					null, "LIST_DATE");
			adapter.changeCursor(c);sz(c);
		}else if(editText1.getText().toString().equals("银行取出")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_NO=? and LIST_DATE<=? and LIST_TYPE=? ",
					new String[]{PaymentsManagementActivity.no,textView1.getText().toString(),"银行取出："}, null,
					null, "LIST_DATE");
			adapter.changeCursor(c);sz(c);
		}else if(editText1.getText().toString().equals("借钱")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_NO=? and LIST_DATE<=? and LIST_TYPE=? ",
					new String[]{PaymentsManagementActivity.no,textView1.getText().toString(),"钱财借出得款人："}, null,
					null, "LIST_DATE");
			adapter.changeCursor(c);sz(c);
		}else if(editText1.getText().toString().equals("还钱")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_NO=? and LIST_DATE<=? and LIST_TYPE=? ",
					new String[]{PaymentsManagementActivity.no,textView1.getText().toString(),"钱财归还还款人："}, null,
					null, "LIST_DATE");
			adapter.changeCursor(c);sz(c);
		}else{
			Cursor c = date_list2Read.query("LIST", null,  "LIST_NO=? and LIST_DATE<=? and LIST_NAME=? ",
					new String[]{PaymentsManagementActivity.no,textView1.getText().toString(),editText1.getText().toString()},
					null, null, "LIST_DATE");
			if(c.getCount()==0){
				Toast.makeText(QueryDetailsActivity.this, "无", Toast.LENGTH_SHORT).show();
			}else{
				adapter.changeCursor(c);
				sz(c);
			}
		}
	}

	private void SearchMoney(){
		Date_List date_list2= new Date_List(this);
		SQLiteDatabase date_list2Read = date_list2.getReadableDatabase();
		String txt = editText1.getText().toString();
		Pattern p = Pattern.compile("[0-9]*");
		Matcher m = p.matcher(txt);
		if(editText1.getText().toString().equals("")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_NO=? and LIST_DATE<=?", new String[]{PaymentsManagementActivity.no,textView1.getText().toString()}, null, null, "LIST_MONEY");
			adapter.changeCursor(c);
			sz(c);
		}else if(m.matches()){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_NO=? and LIST_DATE<=? and LIST_XUHAO=? ", new String[]{PaymentsManagementActivity.no,textView1.getText().toString(),editText1.getText().toString()}, null, null, "LIST_MONEY");
			if(c.getCount()==0){
				Toast.makeText(QueryDetailsActivity.this, "无", Toast.LENGTH_SHORT).show();
			}else{

				adapter.changeCursor(c);
				sz(c);
			}
		}else if(editText1.getText().toString().equals("理财收入")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_NO=? and LIST_DATE<=? and LIST_TYPE=? ",
					new String[]{PaymentsManagementActivity.no,textView1.getText().toString(),"理财收入："},
					null, null, "LIST_MONEY");
			adapter.changeCursor(c);sz(c);
		}else if(editText1.getText().toString().equals("理财支出")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_NO=? and LIST_DATE<=? and LIST_TYPE=? ",
					new String[]{PaymentsManagementActivity.no,textView1.getText().toString(),"理财支出："},
					null, null, "LIST_MONEY");

			adapter.changeCursor(c);sz(c);
		}else if(editText1.getText().toString().equals("日常收入")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_NO=? and LIST_DATE<=? and LIST_TYPE=? ",
					new String[]{PaymentsManagementActivity.no,textView1.getText().toString(),"日常收入："},
					null, null, "LIST_MONEY");
			adapter.changeCursor(c);sz(c);
		}else if(editText1.getText().toString().equals("日常支出")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_NO=? and LIST_DATE<=? and LIST_TYPE=? ",
					new String[]{PaymentsManagementActivity.no,textView1.getText().toString(),"日常支出："},
					null, null, "LIST_MONEY");
			adapter.changeCursor(c);sz(c);
		}else if(editText1.getText().toString().equals("银行存入")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_NO=? and LIST_DATE<=? and LIST_TYPE=? ",
					new String[]{PaymentsManagementActivity.no,textView1.getText().toString(),"银行存入："}, null, null, "LIST_MONEY");
			adapter.changeCursor(c);sz(c);
		}else if(editText1.getText().toString().equals("银行取出")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_NO=? and LIST_DATE<=? and LIST_TYPE=? ",
					new String[]{PaymentsManagementActivity.no,textView1.getText().toString(),"银行取出："},
					null, null, "LIST_MONEY");
			adapter.changeCursor(c);sz(c);
		}else if(editText1.getText().toString().equals("借钱")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_NO=? and LIST_DATE<=? and LIST_TYPE=? ",
					new String[]{PaymentsManagementActivity.no,textView1.getText().toString(),"钱财借出得款人："},
					null, null, "LIST_MONEY");
			adapter.changeCursor(c);sz(c);
		}else if(editText1.getText().toString().equals("还钱")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_NO=? and LIST_DATE<=? and LIST_TYPE=? ",
					new String[]{PaymentsManagementActivity.no,textView1.getText().toString(),"钱财归还还款人："},
					null, null, "LIST_MONEY");
			adapter.changeCursor(c);sz(c);
		}else{
			Cursor c = date_list2Read.query("LIST", null,  "LIST_NO=? and LIST_DATE<=? and LIST_NAME=? ",
					new String[]{PaymentsManagementActivity.no,textView1.getText().toString(),editText1.getText().toString()},
					null, null, "LIST_MONEY");
			if(c.getCount()==0){
				Toast.makeText(QueryDetailsActivity.this, "无", Toast.LENGTH_SHORT).show();
			}else{

				adapter.changeCursor(c);
				sz(c);
			}
		}
		SnackbarUtil.makeSnackBar(mLayout,"查询成功！");
	}

	@SuppressLint("SetTextI18n")
	public void sz(Cursor c2){
		double income1 = 0;
		double payment1 = 0;
		c2.moveToFirst();
		if(c2.getString(3).equals("日常收入：")||c2.getString(3).equals("银行存入：")||c2.getString(3).equals("理财收入：")||c2.getString(3).equals("钱财归还还款人：")){
			income1 = income1+Double.parseDouble(c2.getString(6));
		}else{
			payment1 = payment1+Double.parseDouble(c2.getString(6));
		}
		while(c2.moveToNext()){
			if(c2.getString(3).equals("日常收入：")||c2.getString(3).equals("银行存入：")||c2.getString(3).equals("理财收入：")||c2.getString(3).equals("钱财归还还款人：")){
				income1 = income1+Double.parseDouble(c2.getString(6));
			}else{
				payment1 = payment1+Double.parseDouble(c2.getString(6));
			}
		}
		System.out.println("\n");
		shouru.setText("总收入：" + income1);
		zhichu.setText("总支出：" + payment1);
	}


	private void DateToTime(){
		Date_List date_list2= new Date_List(this);
		SQLiteDatabase date_list2Read = date_list2.getReadableDatabase();
		Cursor c = date_list2Read.query("LIST", null,  "LIST_NO=? and LIST_DATE<=?", new String[]{PaymentsManagementActivity.no,textView1.getText().toString()}, null, null, "LIST_DATE");
		adapter.changeCursor(c);
	}

	private void DateMoney(){
		Date_List date_list2= new Date_List(this);
		SQLiteDatabase date_list2Read = date_list2.getReadableDatabase();
		Cursor c = date_list2Read.query("LIST", null,  "LIST_NO=? and LIST_DATE<=?", new String[]{PaymentsManagementActivity.no,textView1.getText().toString()}, null, null, "LIST_MONEY");
		adapter.changeCursor(c);
	}

}
