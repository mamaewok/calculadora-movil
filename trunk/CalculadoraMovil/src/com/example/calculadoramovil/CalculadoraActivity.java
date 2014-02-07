package com.example.calculadoramovil;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * 
 * @author Ainhoa S.Suárez
 *
 */
public class CalculadoraActivity extends Activity implements View.OnClickListener{
	
	Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0,btnPunto;
	ButtonOperacion	btnMas, btnMenos, btnMulti, btnDiv, btnC, btnIgual;
	EditText pantalla;
	boolean nuevaOperacion = true;
	String operacion = "0";
	double resultado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calculadora);
		
		pantalla = (EditText)findViewById(R.id.Pantalla);
		
		btn1 = (ButtonNumerico)findViewById(R.id.Btn1);
		btn2 = (ButtonNumerico)findViewById(R.id.Btn2);
		btn3 = (ButtonNumerico)findViewById(R.id.Btn3);
		btn4 = (ButtonNumerico)findViewById(R.id.Btn4);
		btn5 = (ButtonNumerico)findViewById(R.id.Btn5);
		btn6 = (ButtonNumerico)findViewById(R.id.Btn6);
		btn7 = (ButtonNumerico)findViewById(R.id.Btn7);
		btn8 = (ButtonNumerico)findViewById(R.id.Btn8);
		btn9 = (ButtonNumerico)findViewById(R.id.Btn9);
		btn0 = (ButtonNumerico)findViewById(R.id.Btn0);
		btnPunto = (ButtonNumerico)findViewById(R.id.BtnPunto);
		btnMas = (ButtonOperacion)findViewById(R.id.BtnMas);
		btnMenos = (ButtonOperacion)findViewById(R.id.BtnMenos);
		btnMulti = (ButtonOperacion)findViewById(R.id.BtnMulti);
		btnDiv = (ButtonOperacion)findViewById(R.id.BtnDiv);
		btnC = (ButtonOperacion)findViewById(R.id.BtnC);
		btnIgual = (ButtonOperacion)findViewById(R.id.BtnIgual);

		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
		btn6.setOnClickListener(this);
		btn7.setOnClickListener(this);
		btn8.setOnClickListener(this);
		btn9.setOnClickListener(this);
		btn0.setOnClickListener(this);
		btnMas.setOnClickListener(this);
		btnMenos.setOnClickListener(this);
		btnMulti.setOnClickListener(this);
		btnDiv.setOnClickListener(this);
		btnPunto.setOnClickListener(this);
		btnC.setOnClickListener(this);
		btnIgual.setOnClickListener(this);
		
	}
	
	/**
	 * Quita los dos últimos carácteres del texto que aparezca en la caja de
	 * texto
	 */
	public void formateoResultado(double resultado) { 
			DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
			simbolo.setDecimalSeparator('.');
			DecimalFormat df = new DecimalFormat("0.#", simbolo);
			pantalla.setText("" + (df.format(resultado)));
	}
	
	/**
	 * Gestiona la pulsacion de teclas numéricas
	 * 
	 * @param digito
	 */
		public void numeroPulsado(String digito) {
			if (pantalla.getText().equals("0") || nuevaOperacion){ 
				pantalla.setText(digito);
				if(digito.equals(".")){
				 pantalla.setText("0.");
			 }	
				} else {
					pantalla.setText(pantalla.getText() + digito);
					}
			nuevaOperacion = false;

		}
	/**
	 * Gestiona la pulsacion de teclas de operacion
	 * 
	 * @param oper
	 */
	public void operacionPulsado(String oper) {
		if (oper.equals("=")){
			try{
				calcularResultado();
			}catch(NumberFormatException nfe){
				pantalla.setText("ERROR");
			}
		}
				
		else if (oper.equals("C")) {
			resultado = 0;
			pantalla.setText("0");
			nuevaOperacion = true;
		} else {
			operacion = oper;
			if ((resultado > 0) && !nuevaOperacion) {
				calcularResultado();
			} else {
				String textPantalla = pantalla.getText().toString();
				resultado = new Double(textPantalla);
				formateoResultado(resultado);
			}
		}
		nuevaOperacion = true;
	}
	
	/**
	 * Calcula el resultado y lo saca por pantalla sacando un mensaje de error en el caso de ser resultado infinito
	 */
	public void calcularResultado() {
		String textPantalla = pantalla.getText().toString();
		
		if (operacion.equals("+")) {
			resultado += new Double(textPantalla);
			formateoResultado(resultado);
		} else if (operacion.equals("-")) {
			resultado -= new Double(textPantalla);
			formateoResultado(resultado);
		} else if (operacion.equals("x")) {
			resultado *= new Double(textPantalla);
			formateoResultado(resultado);
		} else if (operacion.equals("÷")) {
			resultado /= new Double(textPantalla);
			formateoResultado(resultado);
		} else {
			resultado = new Double(textPantalla);
		}
		if(Double.isInfinite(resultado)){
			pantalla.setText("ERROR");
		}
		else{
			formateoResultado(resultado);
			operacion = "";
		}
		
	}

	@Override
	public void onClick(View v) {
		Button botonPulsado = (Button)findViewById(v.getId());
		if(botonPulsado.getClass() == ButtonNumerico.class){
			numeroPulsado(botonPulsado.getText().toString());
		}
		else if(botonPulsado.getClass() == ButtonOperacion.class){
			operacionPulsado(botonPulsado.getText().toString());
		}
		
	}

	
}
