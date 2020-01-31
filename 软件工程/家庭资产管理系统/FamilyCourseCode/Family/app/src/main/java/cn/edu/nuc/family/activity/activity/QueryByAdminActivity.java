package cn.edu.nuc.family.activity.activity;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import android.widget.RadioButton;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.nuc.family.R;
import cn.edu.nuc.family.activity.datebase.Date_List;

public class QueryByAdminActivity extends ListActivity {
	private Button button1;
	private Button search;
	private EditText editText1;
	private TextView textView1;
	SimpleCursorAdapter adapter,adapter1;
	private RadioButton shijian;
	private RadioButton money;
	private TextView income;
	private TextView payment;

	Date_List date_list = new Date_List(this);
	Calendar c = Calendar.getInstance(); 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_query_admin);
		button1 = (Button) findViewById(R.id.button1);
		search = (Button) findViewById(R.id.sousuo);
		textView1 = (TextView) findViewById(R.id.textView1);
		editText1 = (EditText) findViewById(R.id.editText1);
		shijian = (RadioButton) findViewById(R.id.shijian);
		money = (RadioButton) findViewById(R.id.jine);
		income = (TextView) findViewById(R.id.shouru);
		payment = (TextView) findViewById(R.id.zhichu);
		textView1.setText(c.get(Calendar.YEAR)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.DAY_OF_MONTH));

		//先全部显示出来
		SQLiteDatabase dailyincome = date_list.getReadableDatabase();
		Cursor c1 = dailyincome.query("LIST", null, null , null, null, null, null);
		adapter = new SimpleCursorAdapter(this, R.layout.admin_cell, c1,
				new String[]{"LIST_DATE","LIST_NAME","LIST_MONEY","LIST_TYPE","LIST_XUHAO","LIST_NO"}, new int[]{R.id.riqi,R.id.mingcheng,R.id.jine,R.id.leibie,R.id.xuhao,R.id.textView2});
		setListAdapter(adapter);

		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new DatePickerDialog(QueryByAdminActivity.this, new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						textView1.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
						if(shijian.isChecked()){
							riqishijian();
						}
						if(money.isChecked()){
							riqijine();
						}
					}
				}, c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH)).show();
			}
		});

		search.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(shijian.isChecked()){
					sousuoshijian();
				}
				if(money.isChecked()){
					sousuojine();
				}
			}
		});

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
		income.setText("总收入："+shouru1);
		payment.setText("总支出："+zhichu1);
	}


	public void sz(Cursor c2){
		double shouru1 = 0;
		double zhichu1 = 0;
		c2.moveToFirst();
		if(c2.getString(3).equals("日常收入：")||c2.getString(3).equals("银行存入：")||c2.getString(3).equals("理财收入：")||c2.getString(3).equals("钱财归还还款人：")){
			shouru1 = shouru1+Double.parseDouble(c2.getString(6));
		}else{
			zhichu1 = zhichu1+Double.parseDouble(c2.getString(6));
		}
		while(c2.moveToNext()){
			if(c2.getString(3).equals("日常收入：")||c2.getString(3).equals("银行存入：")||c2.getString(3).equals("理财收入：")||c2.getString(3).equals("钱财归还还款人：")){
				shouru1 = shouru1+Double.parseDouble(c2.getString(6));
			}else{
				zhichu1 = zhichu1+Double.parseDouble(c2.getString(6));
			}
		}
		System.out.println("\n");
		income.setText("总收入："+shouru1);
		payment.setText("总支出："+zhichu1);
	}

	private void sousuoshijian(){

		Date_List date_list2= new Date_List(this);
		SQLiteDatabase date_list2Read = date_list2.getReadableDatabase();
		String txt = editText1.getText().toString();
		Pattern p = Pattern.compile("[0-9]*");
		Matcher m = p.matcher(txt);
		if(editText1.getText().toString().equals("")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_DATE<=?", new String[]{textView1.getText().toString()}, null, null, "LIST_DATE");
			adapter.changeCursor(c);
			sz(c);
		}else if(m.matches()){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_DATE<=? and LIST_NO=? ", new String[]{textView1.getText().toString(),editText1.getText().toString()}, null, null, "LIST_DATE");
			if(c.getCount()==0){
				Toast.makeText(QueryByAdminActivity.this, "无", Toast.LENGTH_SHORT).show();
			}else{
				adapter.changeCursor(c);
				sz(c);
			}
		}else if(editText1.getText().toString().equals("理财收入")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_DATE<=? and LIST_TYPE=? ", new String[]{textView1.getText().toString(),"理财收入："}, null, null, "LIST_DATE");
			adapter.changeCursor(c);
			sz(c);
		}else if(editText1.getText().toString().equals("理财支出")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_DATE<=? and LIST_TYPE=? ", new String[]{textView1.getText().toString(),"理财支出："}, null, null, "LIST_DATE");
			adapter.changeCursor(c);
			sz(c);
		}else if(editText1.getText().toString().equals("日常收入")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_DATE<=? and LIST_TYPE=? ", new String[]{textView1.getText().toString(),"日常收入："}, null, null, "LIST_DATE");
			adapter.changeCursor(c);
			sz(c);
		}else if(editText1.getText().toString().equals("日常支出")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_DATE<=? and LIST_TYPE=? ", new String[]{textView1.getText().toString(),"日常支出："}, null, null, "LIST_DATE");
			adapter.changeCursor(c);
			sz(c);
		}else if(editText1.getText().toString().equals("银行存入")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_DATE<=? and LIST_TYPE=? ", new String[]{textView1.getText().toString(),"银行存入："}, null, null, "LIST_DATE");
			adapter.changeCursor(c);
			sz(c);
		}else if(editText1.getText().toString().equals("银行取出")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_DATE<=? and LIST_TYPE=? ", new String[]{textView1.getText().toString(),"银行取出："}, null, null, "LIST_DATE");
			adapter.changeCursor(c);
			sz(c);
		}else if(editText1.getText().toString().equals("借钱")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_DATE<=? and LIST_TYPE=? ", new String[]{textView1.getText().toString(),"钱财借出得款人："}, null, null, "LIST_DATE");
			adapter.changeCursor(c);
			sz(c);
		}else if(editText1.getText().toString().equals("还钱")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_DATE<=? and LIST_TYPE=? ", new String[]{textView1.getText().toString(),"钱财归还还款人："}, null, null, "LIST_DATE");
			adapter.changeCursor(c);
			sz(c);
		}else{
			Cursor c = date_list2Read.query("LIST", null,  "LIST_DATE<=? and LIST_NAME=? ", new String[]{textView1.getText().toString(),editText1.getText().toString()}, null, null, "LIST_DATE");
			if(c.getCount()==0){
				Toast.makeText(QueryByAdminActivity.this, "无", Toast.LENGTH_SHORT).show();
			}else{
				adapter.changeCursor(c);
				sz(c);
			}
		}
	}

	private void sousuojine(){
		Date_List date_list2= new Date_List(this);
		SQLiteDatabase date_list2Read = date_list2.getReadableDatabase();
		String txt = editText1.getText().toString();
		Pattern p = Pattern.compile("[0-9]*");
		Matcher m = p.matcher(txt);
		if(editText1.getText().toString().equals("")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_DATE<=?", new String[]{textView1.getText().toString()}, null, null, "LIST_MONEY");
			adapter.changeCursor(c);
			sz(c);
		}else if(m.matches()){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_DATE<=? and LIST_NO=? ", new String[]{textView1.getText().toString(),editText1.getText().toString()}, null, null, "LIST_MONEY");
			if(c.getCount()==0){
				Toast.makeText(QueryByAdminActivity.this, "无", Toast.LENGTH_SHORT).show();
			}else{
				adapter.changeCursor(c);
				sz(c);
			}
		}else if(editText1.getText().toString().equals("理财收入")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_DATE<=? and LIST_TYPE=? ", new String[]{textView1.getText().toString(),"理财收入："}, null, null, "LIST_MONEY");
			adapter.changeCursor(c);
			sz(c);
		}else if(editText1.getText().toString().equals("理财支出")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_DATE<=? and LIST_TYPE=? ", new String[]{textView1.getText().toString(),"理财支出："}, null, null, "LIST_MONEY");
			adapter.changeCursor(c);
			sz(c);
		}else if(editText1.getText().toString().equals("日常收入")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_DATE<=? and LIST_TYPE=? ", new String[]{textView1.getText().toString(),"日常收入："}, null, null, "LIST_MONEY");
			adapter.changeCursor(c);sz(c);
		}else if(editText1.getText().toString().equals("日常支出")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_DATE<=? and LIST_TYPE=? ", new String[]{textView1.getText().toString(),"日常支出："}, null, null, "LIST_MONEY");
			adapter.changeCursor(c);sz(c);
		}else if(editText1.getText().toString().equals("银行存入")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_DATE<=? and LIST_TYPE=? ", new String[]{textView1.getText().toString(),"银行存入："}, null, null, "LIST_MONEY");
			adapter.changeCursor(c);sz(c);
		}else if(editText1.getText().toString().equals("银行取出")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_DATE<=? and LIST_TYPE=? ", new String[]{textView1.getText().toString(),"银行取出："}, null, null, "LIST_MONEY");
			adapter.changeCursor(c);sz(c);
		}else if(editText1.getText().toString().equals("借钱")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_DATE<=? and LIST_TYPE=? ", new String[]{textView1.getText().toString(),"钱财借出得款人："}, null, null, "LIST_MONEY");
			adapter.changeCursor(c);sz(c);
		}else if(editText1.getText().toString().equals("还钱")){
			Cursor c = date_list2Read.query("LIST", null,  "LIST_DATE<=? and LIST_TYPE=? ", new String[]{textView1.getText().toString(),"钱财归还还款人："}, null, null, "LIST_MONEY");
			adapter.changeCursor(c);sz(c);
		}else{
			Cursor c = date_list2Read.query("LIST", null,  "LIST_DATE<=? and LIST_NAME=? ", new String[]{textView1.getText().toString(),editText1.getText().toString()}, null, null, "LIST_MONEY");
			if(c.getCount()==0){
				Toast.makeText(QueryByAdminActivity.this, "无", Toast.LENGTH_SHORT).show();
			}else{

				adapter.changeCursor(c);
				sz(c);
			}
		}
	}

	private void riqishijian(){
		Date_List date_list2= new Date_List(this);
		SQLiteDatabase date_list2Read = date_list2.getReadableDatabase();
		Cursor c = date_list2Read.query("LIST", null,  "LIST_DATE<=?", new String[]{textView1.getText().toString()}, null, null, "LIST_DATE");
		adapter.changeCursor(c);
	}

	private void riqijine(){
		Date_List date_list2= new Date_List(this);
		SQLiteDatabase date_list2Read = date_list2.getReadableDatabase();
		Cursor c = date_list2Read.query("LIST", null,  "LIST_DATE<=?", new String[]{textView1.getText().toString()}, null, null, "LIST_MONEY");
		adapter.changeCursor(c);
	}

}


