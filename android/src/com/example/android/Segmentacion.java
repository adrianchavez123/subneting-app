package com.example.android;

import java.util.ArrayList;
import java.util.StringTokenizer;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Segmentacion extends Activity {

	Button ok;
	EditText etIp, etMascara;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_segmentacion);

		ok = (Button) findViewById(R.id.btnOk);
		etIp = (EditText) findViewById(R.id.etIp);
		etMascara = (EditText) findViewById(R.id.etMascara);

		ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				String vIp = etIp.getText().toString();
				String vMascar = etMascara.getText().toString();
				boolean continuar = false;
				try {

					int valorMascara = Integer.parseInt(vMascar);

					if (valorMascara <= 32) {

						continuar = true;
					}

					if (continuar) {

						int punto = 0;
						for (int i = 0; i < vIp.length(); i++) {

							if (vIp.charAt(i) == '.') {
								punto++;
							}
						}
						if (punto == 3) {

							StringTokenizer tok = new StringTokenizer(vIp, ".");
							ArrayList<Integer> red = new ArrayList<Integer>();
							int i = 0;
							while (tok.hasMoreElements()) {

								red.add(Integer.parseInt(tok.nextToken()));

								i++;
							}

							if (red.size() == 4) {
								for (i = 0; i < red.size(); i++) {

									if (red.get(i)<0 ||  red.get(i) >= 256) {

										continuar = false;
									}

								}
							}else{
								
								continuar = false;
							}
						} else {
							continuar = false;
						}
					}

					if (continuar) {

						Intent in = new Intent("com.example.android.LISTA");

						Bundle b = new Bundle();

						b.putString("IP", vIp);
						b.putString("MASCARA", vMascar);

						in.putExtras(b);
						startActivity(in);

					} else {

						Dialog d = new Dialog(Segmentacion.this);
						d.setTitle("Error");
						TextView tv = new TextView(Segmentacion.this);
						tv.setText("Algun dato fue ingresado Incorrectamente");
						d.setContentView(tv);
						d.show();
					}

				} catch (Exception e) {

					Dialog d = new Dialog(Segmentacion.this);
					d.setTitle("Error");
					TextView tv = new TextView(Segmentacion.this);
					tv.setText("Algun dato fue ingresado Incorrectamente");
					d.setContentView(tv);
					d.show();

				}

			}
		});

	}

}
