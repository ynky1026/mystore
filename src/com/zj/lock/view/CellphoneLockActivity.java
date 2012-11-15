package com.zj.lock.view;

import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.zj.easyandroid.activities.EasyAndroidActivity;
import com.zj.easyandroid.annotation.Listener;
import com.zj.easyandroid.core.Enum.*;
import com.zj.lock.R;
import com.zj.lock.common.ConstValue;
import com.zj.lock.service.LockService;

public class CellphoneLockActivity extends EasyAndroidActivity {

	/**
	 * TAG
	 */
	private static final String TAG = "CellphoneLockActivity";

	/**
	 * SharedPreferences����
	 */
	private SharedPreferences sp;

	/**
	 * email��ַ�����
	 */
	private EditText email;

	/**
	 * �ֻ����������
	 */
	private EditText phonenum;

	/**
	 * ���������
	 */
	private EditText pw;
	
	private KeyguardManager mKeyguardManager;
	
	private KeyguardLock mKeyguardLock;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.main);
			init();
		} catch (Throwable e) {
			Log.e(TAG, "onCreate error", e);
		}
	}

	/**
	 * ��ʼ������
	 */
	private void init() {
		email = getViewById(R.id.email);
		phonenum = getViewById(R.id.phonenum);
		pw = getViewById(R.id.password);

		sp = getSharedPreferences(ConstValue.SP_NAME, 0);
	}

	@Listener(id = R.id.save, event = Event.CLICK)
	private void save(View v) {
		try {
			String address = email.getText().toString();
			// if (!CommonUtil.isEmail(address)) {
			// Toast.makeText(this, "�����ʽ���Ϸ���", Toast.LENGTH_LONG).show();
			// return;
			// }

			String phonenum = this.phonenum.getText().toString();

			String password = pw.getText().toString();

			sp.edit().putString(ConstValue.SP_EMAIL, address);
			sp.edit().putString(ConstValue.SP_PASSWORD, password);
			sp.edit().putString(ConstValue.SP_PHONENUM, phonenum);
			sp.edit().commit();

			mKeyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
			mKeyguardLock = mKeyguardManager
					.newKeyguardLock("Activity1");
			mKeyguardLock.disableKeyguard();

			Intent it = new Intent(this, LockService.class);
			startService(it);
		} catch (Throwable e) {
			Log.e(TAG, "save failed", e);
		}
	}

	@Listener(id = R.id.exit, event = Event.CLICK)
	private void exit() {
		try {
			Intent it = new Intent(this, LockService.class);
			stopService(it);
			mKeyguardLock.reenableKeyguard();
		} catch (Exception e) {
			Log.e(TAG, "exit failed", e);
		}
	}
}