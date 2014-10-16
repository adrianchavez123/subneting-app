package com.example.android;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Operacion {

	public String resultado = "";
	public ArrayList<String> valorBinarioCompleto;
	public ArrayList<String> valorBinarioCompletoFin;
	private ArrayList<String> numero;
	int[] valMascara;
	int[] numBin;
	private String valIp;
	int valmascara;
	final int maxMascara = 32;

	String prim = "";
	String mascar[] = new String[4];
	String ip[] = new String[4];
	String ip2[] = new String[4];
	String a[] = new String[4];
	String red[] = new String[4];

	int m[] = new int[4];
	int p[] = new int[4];

	int primera[] = new int[4];
	public long ordenados[];
	public int masc[];
	long valAprox[];
	String valAprox2[];
	String valAprox3[];
	long totalSuma = 0;
	long mascara = 0;
	boolean sigue;

	Operacion(ArrayList<String> numero, String valIp, int valmascara) {

		this.numero = numero;
		this.valIp = valIp;
		this.valmascara = valmascara;

		 sigue = esMenor();
	}

	private boolean esMenor() {

		ordenados = new long[numero.size()];

		// long val = 0;
		for (int i = 0; i < numero.size(); i++) {

			// val += Integer.parseInt(numero.get(i));
			// ordenados[i] = Integer.parseInt(numero.get(i));
			ordenados[i] = Long.parseLong(numero.get(i));
		}

		//ordenar();
		Ordenador o = new Ordenador();
		o.ordenarQuicksort(ordenados);
		potenciaCercana();

		mascara = maxMascara - valmascara;

		mascara = (long) Math.pow(2, mascara);

		System.out.println("mascara " + mascara);

		for (int i = 0; i < valAprox.length; i++) {

			System.out.println(ordenados[i] + "   " + valAprox[i]);

			totalSuma += valAprox[i];
		}

		System.out.println("suma" + totalSuma);

		boolean continuar = numeroIpsPosibles();

		if (continuar) {

			System.out.println("continua");

			// and();
			andMascaraIp();
			
			return true;

		} else {
			System.out.println("No continua");
		}

		// /
		// if(mascara <= val);
		return false;
	}

	private void ordenar() {

		long pasadas, almacena;
		for (pasadas = 1; pasadas < ordenados.length; pasadas++) {

			for (int i = 0; i < ordenados.length - 1; i++) {
				/*
				 * compara los elementos adyacentes y los intercambia si el
				 * primer elementos es mayor que el segundo
				 */

				if (ordenados[i] < ordenados[i + 1]) {
					almacena = ordenados[i];
					ordenados[i] = ordenados[i + 1];
					ordenados[i + 1] = almacena;
					/* fin del if */
				}
			} /* fin del por interno */

		}/* fin del for externo */
	}

	private void potenciaCercana() {

		valAprox = new long[ordenados.length];
		valAprox2 = new String[ordenados.length];
		valAprox3 = new String[ordenados.length];
		valMascara = new int[ordenados.length];

		for (int i = 0; i < valAprox.length; i++) {

			int x = 0;
			while (true) {

				long potencia = (long) Math.pow(2, x);

				if (potencia >= ordenados[i] + 2) {

					valAprox[i] = potencia;
					valMascara[i] = x;
					break;
				}
				x++;
			}
		}

	}

	private boolean numeroIpsPosibles() {

		if (totalSuma <= mascara) {

			return true;
		}

		return false;
	}

	private void andMascaraIp() {

		StringTokenizer tok = new StringTokenizer(valIp, ".");
		int c = 0;
		while (tok.hasMoreTokens()) {
			String valor = tok.nextToken();
			red[c] = valor;

			c++;
		}

		for (int i = 0; i < red.length; i++) {
			a[i] = Integer.toBinaryString(Integer.parseInt(red[i]));
			// System.out.println(a[i]);
		}
		System.out.println("---------------------------------------------");

		String t = "";
		for (int i = 0; i < red.length; i++) {
			if (red[i].length() != 8) {
				String temp = "";
				for (int j = a[i].length(); j < 8; j++) {
					temp += "0";
				}
				a[i] = temp + a[i];
			}
		}

		for (int i = 0; i < a.length; i++) {

			System.out.print(a[i]);

			if (i < a.length - 1) {
				System.out.print(".");

			}
		}

		System.out.println();
		crearMascara();

		System.out.println("\n_____________________________________________");
		and();

		siguientes();
	}

	private void crearMascara() {

		String t = "";

		for (int i = 0; i < 32; i++) {
			if (i < valmascara) {
				if (i % 8 == 0 && i != 0) {
					t += ".";
				}
				t += "1";

			} else {
				if (i % 8 == 0 && i != 0) {
					t += ".";
				}
				t += "0";
			}
		}

		StringTokenizer to = new StringTokenizer(t, ".");
		int c = 0;
		while (to.hasMoreTokens()) {
			String valor = to.nextToken();
			mascar[c] = valor;

			c++;
		}

		for (int i = 0; i < mascar.length; i++) {

			System.out.print(mascar[i]);
			if (i < a.length - 1) {
				System.out.print(".");

			}
		}
	}

	private void and() {

		for (int i = 0; i < red.length; i++) {
			m[i] = Integer.parseInt(a[i], 2);
			p[i] = Integer.parseInt(mascar[i], 2);
			// System.out.print(m[i]+"-");
		}

		valorBinarioCompleto = new ArrayList<String>();
		for (int i = 0; i < red.length; i++) {
			primera[i] = m[i] & p[i];
			// System.out.print(Integer.toBinaryString(primera[i]));

			String temp = Integer.toBinaryString(primera[i]);

			if (temp.length() < 8) {
				String temp2 = "";
				for (int x = temp.length(); x < 8; x++) {
					temp2 += "0";
				}

				prim += temp2;
				prim += Integer.toBinaryString(primera[i]);
			} else {
				prim += Integer.toBinaryString(primera[i]);
			}
			if (i < a.length - 1) {
				// System.out.print(".");

			}

			// System.out.print(Integer.toBinaryString(ma[i]) +".");
		}

		System.out.println("\n" + prim + "->and");
		valorBinarioCompleto.add(prim);
	}

	private void siguientes() {

		binarios();

		System.out.println("prim:\n" + prim);
		// long x = Long.parseLong(prim,2);
		// int y = Integer.parseInt(valAprox2[0],2);

		for (int i = 0; i < valAprox2.length; i++) {

			BigInteger b = new BigInteger(prim, 2);
			System.out.println(valAprox2[i]
					+ "\n______________________________________\n");
			BigInteger r = b.add(new BigInteger(valAprox2[i], 2));

			prim = r.toString(2);
			System.out.println(prim);
			valorBinarioCompleto.add(prim);

		}

		System.out.println(valorBinarioCompleto.size());

		System.out.println("\n\n\n");
		for (int i = 0; i < valorBinarioCompleto.size(); i++) {

			System.out.println(valorBinarioCompleto.get(i));
		}

		valorBinarioCompletoFin = new ArrayList<String>();
		for (int i = 0; i < valorBinarioCompleto.size() - 1; i++) {

			BigInteger b = new BigInteger(valorBinarioCompleto.get(i), 2);
			BigInteger r = b.add(new BigInteger(valAprox3[i], 2));

			prim = r.toString(2);
			valorBinarioCompletoFin.add(prim);
		}

		System.out.println("\n\n\n");
		for (int i = 0; i < valorBinarioCompletoFin.size(); i++) {

			System.out.println(valorBinarioCompletoFin.get(i));
		}

		ordenarBinario();

	}

	private void binarios() {

		numBin = new int[valAprox.length];

		for (int i = 0; i < valAprox.length; i++) {

			valAprox2[i] = Long.toBinaryString(valAprox[i]);
			valAprox3[i] = Long.toBinaryString(valAprox[i] - 1);
		}

		System.out.println();
		for (int i = 0; i < valAprox.length; i++) {

			System.out.println(valAprox[i] + "\t\t" + valAprox2[i]);
		}
	}

	private void ordenarBinario() {

		System.out.println("\n\n\n");

		for (int i = 0; i < valorBinarioCompleto.size(); i++) {

			if (valorBinarioCompleto.get(i).length() == 32) {

				String temp = "";
				for (int j = 0; j < 32; j++) {

					if (j % 8 == 0 && j != 0) {
						temp += ".";
					}
					temp += valorBinarioCompleto.get(i).charAt(j);

				}
				valorBinarioCompleto.set(i, temp);
				System.out.println(valorBinarioCompleto.get(i));
			} else {

				String s = "";

				for (int j = valorBinarioCompleto.get(i).length(); j < 32; j++) {

					s += "0";
				}
				s += valorBinarioCompleto.get(i);

				valorBinarioCompleto.set(i, s);

				String temp = "";
				for (int j = 0; j < 32; j++) {

					if (j % 8 == 0 && j != 0) {
						temp += ".";
					}
					temp += valorBinarioCompleto.get(i).charAt(j);

				}
				valorBinarioCompleto.set(i, temp);
				System.out.println(valorBinarioCompleto.get(i));

			}
		}

		for (int i = 0; i < valorBinarioCompleto.size(); i++) {

			System.out.println("valor binario completo"
					+ valorBinarioCompleto.get(i));
		}

		System.out.println("\n\n\n");
		// for(int i = 0; i<valorBinarioCompletoFin.size();i++){
		//
		// String temp ="";
		// for(int j = 0; j<32; j++){
		//
		// if(j%8==0 && j!=0)
		// {
		// temp+=".";
		// }
		// temp+=valorBinarioCompletoFin.get(i).charAt(j);
		//
		// }
		// valorBinarioCompletoFin.set(i, temp);
		// System.out.println(valorBinarioCompletoFin.get(i));
		// }

		System.out.println("\n\n\n");

		for (int i = 0; i < valorBinarioCompletoFin.size(); i++) {

			if (valorBinarioCompletoFin.get(i).length() == 32) {

				String temp = "";
				for (int j = 0; j < 32; j++) {

					if (j % 8 == 0 && j != 0) {
						temp += ".";
					}
					temp += valorBinarioCompletoFin.get(i).charAt(j);

				}
				valorBinarioCompletoFin.set(i, temp);
				System.out.println(valorBinarioCompletoFin.get(i));
			} else {

				String s = "";

				for (int j = valorBinarioCompletoFin.get(i).length(); j < 32; j++) {

					s += "0";
				}
				s += valorBinarioCompletoFin.get(i);

				valorBinarioCompletoFin.set(i, s);

				String temp = "";
				for (int j = 0; j < 32; j++) {

					if (j % 8 == 0 && j != 0) {
						temp += ".";
					}
					temp += valorBinarioCompletoFin.get(i).charAt(j);

				}
				valorBinarioCompletoFin.set(i, temp);
				System.out.println(valorBinarioCompletoFin.get(i));

			}
		}

		for (int i = 0; i < valorBinarioCompletoFin.size(); i++) {

			System.out.println("valor binario completo fin"
					+ valorBinarioCompletoFin.get(i));
		}

		System.out.println("\n\n");
		separarOctetos();

	}

	private void separarOctetos() {

		StringTokenizer tok;

		for (int i = 0; i < valorBinarioCompleto.size(); i++) {

			tok = new StringTokenizer(valorBinarioCompleto.get(i), ".");
			int v = 0;
			int uno, dos, tres, cuatro;
			uno = dos = tres = cuatro = 0;
			while (tok.hasMoreElements()) {

				if (v == 0) {
					uno = Integer.parseInt(tok.nextToken(), 2);
				} else if (v == 1) {
					dos = Integer.parseInt(tok.nextToken(), 2);
				} else if (v == 2) {
					tres = Integer.parseInt(tok.nextToken(), 2);
				} else if (v == 3) {
					cuatro = Integer.parseInt(tok.nextToken(), 2);
				}

				v++;
			}
			System.out.println(uno + "." + dos + "." + tres + "." + cuatro);
			valorBinarioCompleto.set(i, uno + "." + dos + "." + tres + "."
					+ cuatro);
		}

		for (int i = 0; i < valorBinarioCompletoFin.size(); i++) {

			tok = new StringTokenizer(valorBinarioCompletoFin.get(i), ".");
			int v = 0;
			int uno, dos, tres, cuatro;
			uno = dos = tres = cuatro = 0;
			while (tok.hasMoreElements()) {

				if (v == 0) {
					uno = Integer.parseInt(tok.nextToken(), 2);
				} else if (v == 1) {
					dos = Integer.parseInt(tok.nextToken(), 2);
				} else if (v == 2) {
					tres = Integer.parseInt(tok.nextToken(), 2);
				} else if (v == 3) {
					cuatro = Integer.parseInt(tok.nextToken(), 2);
				}

				v++;
			}
			System.out.println(uno + "." + dos + "." + tres + "." + cuatro);
			valorBinarioCompletoFin.set(i, uno + "." + dos + "." + tres + "."
					+ cuatro);

		}

		resultado += "\tInicio\t\tFin\n";
		masc = new int[ordenados.length];
		for (int i = 0; i < valorBinarioCompletoFin.size(); i++) {

			masc[i] =(maxMascara - valMascara[i]);
			resultado += "(" + ordenados[i] + ") " + valorBinarioCompleto.get(i)
					+ "\t" + valorBinarioCompletoFin.get(i) + "\t/"
					+ masc[i]+"\n";
		}
	}

}
