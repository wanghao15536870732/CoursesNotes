package cn.edu.nuc.family.activity.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import cn.edu.nuc.family.R;
import cn.edu.nuc.family.activity.base.Luru3;

public class InputInformationActivity extends Activity {
	private Button btnGeneral;
	private Button btnBank;
	private Button btnProducts;
	private Button btnLoan;
	private ImageView back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_information_input);
		btnGeneral = findViewById(R.id.generalBtn);
		btnBank = findViewById(R.id.bankBtn);
		btnProducts = findViewById(R.id.productsBtn);
		btnLoan = findViewById(R.id.loanBtn);
		back = findViewById(R.id.information_back);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		btnGeneral.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(InputInformationActivity.this, GeneralInputActivity.class);
				startActivity(intent);
			}
		});

		btnBank.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(InputInformationActivity.this, BankInputActivity.class);
				startActivity(intent);
			}
		});

		btnProducts.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(InputInformationActivity.this, Luru3.class);
				startActivity(intent);
			}
		});

		btnLoan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(InputInformationActivity.this, LoanInputActivity.class);
				startActivity(intent);
			}
		});
	}
}
