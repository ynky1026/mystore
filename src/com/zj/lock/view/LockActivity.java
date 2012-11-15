package com.zj.lock.view;

import java.util.Timer;
import java.util.TimerTask;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.zj.easyandroid.activities.EasyAndroidActivity;
import com.zj.easyandroid.annotation.Listener;
import com.zj.easyandroid.core.Enum.Event;
import com.zj.lock.R;
import com.zj.lock.common.ConstValue;

public class LockActivity extends EasyAndroidActivity {

	/**
	 * TAG
	 */
	private static final String TAG = "LockActivity";

	private EditText num1;

	private EditText num2;

	private EditText num3;

	private EditText num4;

	private String psw;

	/**
	 * SharedPreferences对象
	 */
	private SharedPreferences sp;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.lock);
			init();
		} catch (Exception e) {
			Log.e(TAG, "onCreate error", e);
		}
	}

	/**
	 * 初始化
	 */
	private void init() {
		num1 = getViewById(R.id.num1);
		num2 = getViewById(R.id.num2);
		num3 = getViewById(R.id.num3);
		num4 = getViewById(R.id.num4);

		sp = getSharedPreferences(ConstValue.SP_NAME, 0);
		psw = sp.getString(ConstValue.SP_PASSWORD, "");
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 打开小键盘
		Timer timer = new Timer();

		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
				imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

			}
		}, 800);
	}

	@Listener(id = R.id.num1, event = Event.TEXTCHANGE)
	private void num1TextChange() {
		num2.setEnabled(true);
		num2.requestFocus();
	}

	@Listener(id = R.id.num2, event = Event.TEXTCHANGE)
	private void num2TextChange() {
		num3.setEnabled(true);
		num3.requestFocus();
	}

	@Listener(id = R.id.num3, event = Event.TEXTCHANGE)
	private void num3TextChange() {
		num4.setEnabled(true);
		num4.requestFocus();
	}

	@Listener(id = R.id.num4, event = Event.TEXTCHANGE)
	private void num4TextChange() {

	}

}
