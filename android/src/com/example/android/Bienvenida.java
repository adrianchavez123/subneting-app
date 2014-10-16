package com.example.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Bienvenida extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bienvenida);
		
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(4000);					
				} catch (InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent openStartingPoint = new Intent("com.example.android.SEGMENTACION");
					startActivity(openStartingPoint);
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
