package com.example.android;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Resultado extends Activity {

	TableLayout tablas;
	TextView res;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resultado);

		res = (TextView) findViewById(R.id.tvRes);
		tablas = (TableLayout) findViewById(R.id.Tab);

		Bundle bb = getIntent().getExtras();

		long a[] = bb.getLongArray("ACANTIDAD");
		ArrayList<String> b = bb.getStringArrayList("AINICIO");
		ArrayList<String> c = bb.getStringArrayList("AFIN");
		int d[] = bb.getIntArray("AMASCARA");
		
		res.setText("RESULTADO");

		for (int i = 0; i < a.length; i++) {

			TableRow t1 = new TableRow(Resultado.this);
			TextView txt = new TextView(Resultado.this);
			TextView txt2 = new TextView(Resultado.this);
			TextView txt3 = new TextView(Resultado.this);
			TextView txt4 = new TextView(Resultado.this);

			Random r = new Random();
			txt.setTextColor(Color.rgb(r.nextInt(255), r.nextInt(255),
					r.nextInt(255)));
			txt2.setTextColor(Color.rgb(r.nextInt(255), r.nextInt(255),
					r.nextInt(255)));
			txt3.setTextColor(Color.rgb(r.nextInt(255), r.nextInt(255),
					r.nextInt(255)));
			txt4.setTextColor(Color.rgb(r.nextInt(255), r.nextInt(255),
					r.nextInt(255)));
			
			
			txt.setText(String.valueOf(a[i]));
			txt2.setText(b.get(i));
			txt3.setText(" "+c.get(i));
			txt4.setText("    "+(d[i]));
			
			txt.setTextSize(25);
			txt2.setTextSize(25);
			txt3.setTextSize(25);
			txt4.setTextSize(25);
			
			t1.addView(txt);
			t1.addView(txt2);
			t1.addView(txt3);
			t1.addView(txt4);

			//
			tablas.addView(t1);
		}

		
	}

}
