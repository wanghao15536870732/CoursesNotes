package cn.edu.nuc.family.activity.activity;

import java.util.Calendar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import cn.edu.nuc.family.R;
import cn.edu.nuc.family.activity.datebase.Date_List;
import de.hdodenhof.circleimageview.CircleImageView;

public class PaymentsManagementActivity extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener {

	Date_List date_list = new Date_List(this);

	static double income = 0;
	static double payment = 0;
	private DrawerLayout mDrawerLayout;
	public static String no = "";
	Calendar c = Calendar.getInstance();
	//切换头像
	private CircleImageView headImageView;


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_paymentsmanager);
		TextView text_income = findViewById(R.id.text_input_income);
		TextView text_payment = findViewById(R.id.text_input_payment);
		TextView greeting = findViewById(R.id.text_greeting);
		Intent intent = getIntent();
		no = intent.getStringExtra("NO");
		Intent intent2 = new Intent();
		intent2.putExtra("no", no);

		income = 0;
		payment = 0;
		SQLiteDatabase listRead = date_list.getReadableDatabase();
		Cursor c2 = listRead.query("LIST", null, "LIST_NO=?",
				new String[]{PaymentsManagementActivity.no}, null, null, null);
		while(c2.moveToNext()){
			if(c2.getString(3).equals("日常收入：")||c2.getString(3).equals("银行存入：")||c2.getString(3).equals("理财收入：")||c2.getString(3).equals("钱财归还还款人：")){
				income = income + Double.parseDouble(c2.getString(6));
			}else{
				payment = payment + Double.parseDouble(c2.getString(6));
			}
		}
		text_income.setText(String.valueOf(income));
		text_payment.setText(String.valueOf(payment));

		if(income > payment){
			greeting.setText("您的收入大于支出，加油，不错哦！");
			greeting.setTextColor(0xFF7CCD7C);
		}else if(income < payment){
			greeting.setText("您的收入小于支出，您花销太大了，再这样就要吃土了！");
			greeting.setTextColor(0xFFCD0000);
		}else{
			greeting.setText("您的收入等于支出，再努努力，就能奔小康了！");
			greeting.setTextColor(0xFF7D9EC0);
		}

		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		//获取圆形View的实例
		headImageView = findViewById(R.id.icon_image);
		mDrawerLayout = findViewById(R.id.drawer_layout);
		NavigationView navView = findViewById(R.id.nav_view);

		//调用getSupportActionBar()方法让导航按钮显示出来
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null){
			//调用actionBar的setDisplayHomeAsUpEnabled()方法让导航按钮显示出开
			actionBar.setDisplayHomeAsUpEnabled(true);
			//再设置一个导航按钮图标,默认是返回键,设置为菜单的样式
			actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
		}
		navView.setNavigationItemSelectedListener(this);
	}

	public boolean onCreateOptionsMenu(Menu menu){

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		switch (item.getItemId()){
			case android.R.id.home:
				mDrawerLayout.openDrawer(GravityCompat.START);
				break;
		}
		return true;
	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
		Fragment calculatorFragment = new CalculatorActivity();
		FragmentManager fm = getFragmentManager();
		if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)){
			mDrawerLayout.closeDrawers();
		}
		switch (menuItem.getItemId()){
			case R.id.nav_main:
				mDrawerLayout.closeDrawers();
				break;
			case R.id.nav_input:
				Intent intent=new Intent();
				intent.setClass(PaymentsManagementActivity.this, InputChangeActivity.class);
				startActivity(intent);
				mDrawerLayout.closeDrawers();
				break;
			case R.id.nav_search:
				Intent intent2=new Intent();
				intent2.setClass(PaymentsManagementActivity.this, QueryDetailsActivity.class);
				startActivity(intent2);
				break;
			case R.id.nav_notebook:
				Intent intent3=new Intent();
				intent3.setClass(PaymentsManagementActivity.this, NoteBookActivity.class);
				startActivity(intent3);
				break;
			case R.id.nav_calculator:
				fm.beginTransaction().replace(R.id.content_frame, calculatorFragment).commit();
				break;
			default:
				break;
		}
		return true;
	}
}


