package com.example.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Lista extends Activity {

	EditText edit;
	Button agregar, calcular;
	ListView lista;

	public void onCreate(Bundle b) {
		super.onCreate(b);
		setContentView(R.layout.listasubredes);
		
		Bundle bu = getIntent().getExtras();
		
		final String ip = bu.getString("IP");
		final String mascara = bu.getString("MASCARA");
		
		
		edit = (EditText) findViewById(R.id.etSubRed);
		agregar = (Button) findViewById(R.id.btnAgregar);
		calcular = (Button) findViewById(R.id.btnCalcular);
		lista = (ListView) findViewById(R.id.lvlista);

		final ArrayList<String> alista = new ArrayList<String>();
		final ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, alista);
		lista.setAdapter(aa);
		

		agregar.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				alista.add(edit.getText().toString());
				aa.notifyDataSetChanged();
				edit.setText("");
			}
		});

		calcular.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
			
				try{
				Operacion o = new Operacion(alista,ip,Integer.parseInt(mascara));
				ArrayList<String> aBinCom = o.valorBinarioCompleto;
				ArrayList<String> aBinComFin = o.valorBinarioCompletoFin;
				long cant[] = o.ordenados;
				int mascara[] = o.masc;
				
				if(o.sigue){
				Intent intent = new Intent("com.example.android.RESULTADO");
				Bundle bund = new Bundle();
				
				bund.putLongArray("ACANTIDAD", cant);
				bund.putStringArrayList("AINICIO", aBinCom);
				bund.putStringArrayList("AFIN", aBinComFin);
				bund.putIntArray("AMASCARA",mascara);
				
				intent.putExtras(bund);
				
				startActivity(intent);
				
				}else{
					Toast t = Toast.makeText(Lista.this, "La cantidad de dispositivos es mayor a la disponible", Toast.LENGTH_LONG);
					t.show();
				}
				}catch(Exception e){
					Toast t = Toast.makeText(Lista.this, "Alguna cantidad se ingreso incorrectamente", Toast.LENGTH_LONG);
					t.show();
				}
			}
		});

	}

}
