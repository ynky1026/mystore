package com.zj.lock.service;

import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.zj.easyandroid.annotation.Receiver;
import com.zj.easyandroid.service.EasyAndroidService;
import com.zj.lock.view.LockActivity;

public class LockService extends EasyAndroidService {

	/**
	 * TAG
	 */
	private static final String TAG = "LockService";

	@Override
	public void onStart(Intent intent, int startId) {
		Log.d(TAG, "service start");
		super.onStart(intent, startId);
	}

	@Receiver(action = Intent.ACTION_SCREEN_ON)
	private void recieve(Context context, Intent intent) {
		try {
			Intent it = new Intent(this, LockActivity.class);
			it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(it);

		} catch (Throwable e) {
			Log.e(TAG, "recieve error", e);
		}
	}
}
