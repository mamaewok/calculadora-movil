package com.example.calculadoramovil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class PortadaActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.portada);
		
		 Thread th = new Thread(){
	            public void run(){
	                try{
	                    sleep(3000);
	                }
	                catch(Exception e){
	                    e.printStackTrace();
	                }
	                finally{
	                    onPause();
	                    startActivity(new Intent(
	                    		"com.example.calculadoramovil.CalculadoraActivity"));
	                }
	            }
	        };
	        th.start();
	        
	 }
    @Override
    public void onPause(){
        super.onPause();
        finish();
        }
    }