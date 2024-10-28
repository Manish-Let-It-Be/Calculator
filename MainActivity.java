package com.example.calcmanish;

import android.os.Build;

import android.os.Bundle;

import android.view.View;

import android.widget.TextView;

import android.os.VibrationEffect;

import android.os.Vibrator;

import android.widget.Toast;

import androidx.activity.EdgeToEdge;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.content.ContextCompat;

import androidx.core.graphics.Insets;

import androidx.core.view.ViewCompat;

import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;

import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

TextView resultTv,solutionTv;

MaterialButton buttonC,buttonOpenBracket,buttonClosedBracket;

MaterialButton buttonDivide,buttonMultiply,buttonPlus,buttonMinus,buttonEqualto;

MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;

MaterialButton buttonDot,buttonAC;

private Vibrator vibrator;

@Override

protected void onCreate(Bundle savedInstanceState) {

super.onCreate(savedInstanceState);

setContentView(R.layout.activity_main);

getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.status_bar_color));

resultTv=findViewById(R.id.result_tv);

solutionTv=findViewById(R.id.solution_tv);

vibrator = (Vibrator) getSystemService(MainActivity.VIBRATOR_SERVICE);

assignId(buttonC,R.id.button_c);

assignId(buttonAC,R.id.button_ac);

assignId(buttonOpenBracket,R.id.button_openbracket);

assignId(buttonClosedBracket,R.id.button_closebracket);

assignId(buttonDivide,R.id.button_divide);

assignId(buttonMultiply,R.id.button_multiply);

assignId(buttonPlus,R.id.button_plus);

assignId(buttonMinus,R.id.button_minus);

assignId(buttonEqualto,R.id.button_equalto);

assignId(button0,R.id.button_0);

assignId(button1,R.id.button_1);

assignId(button2,R.id.button_2);

assignId(button3,R.id.button_3);

assignId(button4,R.id.button_4);

assignId(button5,R.id.button_5);

assignId(button6,R.id.button_6);

assignId(button7,R.id.button_7);

assignId(button8,R.id.button_8);

assignId(button9,R.id.button_9);

assignId(buttonDot,R.id.button_dot);

}

void assignId(MaterialButton btn,int id){

btn=findViewById(id);

btn.setOnClickListener(this);

}

@Override

public void onClick(View view) {

MaterialButton button=(MaterialButton) view;

String buttonText = button.getText().toString();

String dataToCalculate=solutionTv.getText().toString();

if (vibrator != null && vibrator.hasVibrator()) {

// Vibrate for 50 milliseconds

if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));

}

}

// Handle button click action based on view's id

if (view.getId() == R.id.button_1) {

// Handle button 1 click action

Toast.makeText(this, "Button 1 Clicked", Toast.LENGTH_SHORT).show();

}

if(buttonText.equals("AC")){

solutionTv.setText("");

resultTv.setText("0");

return;

}

if(buttonText.equals("=")){

solutionTv.setText(resultTv.getText());

return;

}

if (buttonText.equals("C")) {

if (dataToCalculate.length() > 0) {

dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);

}

} else {

dataToCalculate = dataToCalculate + buttonText;

}

solutionTv.setText(dataToCalculate);

String finalResult=getResult(dataToCalculate);

if(!finalResult.equals("Err")){

resultTv.setText(finalResult);

}

}

String getResult(String data){

try {

Context context=Context.enter();

context.setOptimizationLevel(-1);

Scriptable scriptable=context.initStandardObjects();

String finalResult= context.evaluateString(scriptable,data,"Javascript",1,null).toString();

if(finalResult.endsWith(".0")){

finalResult=finalResult.replace(".0","");

}

return finalResult;

}catch (Exception e){

return "Err";

}

}

private class VIBRATOR_SERVICE {

}

}