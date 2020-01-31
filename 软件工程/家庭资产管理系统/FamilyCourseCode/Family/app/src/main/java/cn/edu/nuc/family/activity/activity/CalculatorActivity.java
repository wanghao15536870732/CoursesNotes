package cn.edu.nuc.family.activity.activity;


import java.util.ArrayList;
import java.util.List;



import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.edu.nuc.family.R;
import cn.edu.nuc.family.activity.base.Item;
import cn.edu.nuc.family.activity.base.Types;

public class CalculatorActivity extends Fragment implements OnClickListener {
	private TextView tvScreen;
	private List<Item> items = new ArrayList<Item>();

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_calculator, container,false);
		tvScreen = (TextView)view.findViewById(R.id.tvScreen);
		view.findViewById(R.id.btn0).setOnClickListener(this);
		view.findViewById(R.id.btn1).setOnClickListener(this);
		view.findViewById(R.id.btn2).setOnClickListener(this);
		view.findViewById(R.id.btn3).setOnClickListener(this);
		view.findViewById(R.id.btn4).setOnClickListener(this);
		view.findViewById(R.id.btn5).setOnClickListener(this);
		view.findViewById(R.id.btn6).setOnClickListener(this);
		view.findViewById(R.id.btn7).setOnClickListener(this);
		view.findViewById(R.id.btn8).setOnClickListener(this);
		view.findViewById(R.id.btn9).setOnClickListener(this);
		view.findViewById(R.id.btnAdd).setOnClickListener(this);
		view.findViewById(R.id.btnSub).setOnClickListener(this);
		view.findViewById(R.id.btnClear).setOnClickListener(this);
		view.findViewById(R.id.btnResult).setOnClickListener(this);
		view.findViewById(R.id.btnX).setOnClickListener(this);
		view.findViewById(R.id.btnDiv).setOnClickListener(this);
		return view;
	}
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn0:
			tvScreen.append("0");
			break;
		case R.id.btn1:
			tvScreen.append("1");
			break;
		case R.id.btn2:
			tvScreen.append("2");
			break;
		case R.id.btn3:
			tvScreen.append("3");
			break;
		case R.id.btn4:
			tvScreen.append("4");
			break;
		case R.id.btn5:
			tvScreen.append("5");
			break;
		case R.id.btn6:
			tvScreen.append("6");
			break;
		case R.id.btn7:
			tvScreen.append("7");
			break;
		case R.id.btn8:
			tvScreen.append("8");
			break;
		case R.id.btn9:
			tvScreen.append("9");
			break;
		case R.id.btnAdd:
			items.add(new Item(Double.parseDouble(tvScreen.getText().toString()), Types.NUM));
			checkAndCompute();
			items.add(new Item(0,Types.Add));
			tvScreen.append("+");
			break;
		case R.id.btnSub:
			items.add(new Item(Double.parseDouble(tvScreen.getText().toString()),Types.NUM));
			checkAndCompute();
			items.add(new Item(0,Types.Sub));
			tvScreen.setText("");
			break;
		case R.id.btnX:
			items.add(new Item(Double.parseDouble(tvScreen.getText().toString()),Types.NUM));
			checkAndCompute();
			items.add(new Item(0,Types.X));
			tvScreen.setText("");
			break;
		case R.id.btnDiv:
			items.add(new Item(Double.parseDouble(tvScreen.getText().toString()),Types.NUM));
			checkAndCompute();
			items.add(new Item(0,Types.Div));
			tvScreen.setText("");
			break;
		case R.id.btnClear:
			tvScreen.setText("");
			items.clear();
			break;
		case R.id.btnResult:
			items.add(new Item(Double.parseDouble(tvScreen.getText().toString()),Types.NUM));
			checkAndCompute();
			tvScreen.setText(items.get(0).value+"");
			items.clear();
			break;
		}		
	}

	public void checkAndCompute(){
		if(items.size()>=3) {
			double a = items.get(0).value;
			double b = items.get(2).value;
			int opt = items.get(1).type;
			items.clear();
			switch (opt) {
				case Types.Add:
					items.add(new Item(a + b, Types.NUM));
					break;
				case Types.Sub:
					items.add(new Item(a - b, Types.NUM));
					break;
				case Types.X:
					items.add(new Item(a * b, Types.NUM));
					break;
				case Types.Div:
					items.add(new Item(a / b, Types.NUM));
					break;
			}
		}
	}
}

	