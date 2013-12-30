package com.example.calculadoramovil;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.Button;

public class ButtonNumerico extends Button{

	public ButtonNumerico(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setTextColor(Color.BLACK);
	}
}
