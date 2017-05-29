package com.basiccalc.mybasiccalculator;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnAdd,btnSubtract,btnDivide,btnMultiply;
    private TextView tvresult;
    private EditText etfirst, etsecond;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseStorage storbuck = FirebaseStorage.getInstance();

        init();
    }

    private void init(){
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnSubtract = (Button)findViewById(R.id.btnSubtract);
        btnDivide = (Button)findViewById(R.id.btnDivide);
        btnMultiply = (Button)findViewById(R.id.btnMultiply);
        etfirst = (EditText)findViewById(R.id.etFirstNumber);
        etsecond = (EditText)findViewById(R.id.etSecondNumber);

        //use if want to display result to page
        //tvresult = (TextView)findViewById(R.id.tvresult);

        btnAdd.setOnClickListener(this);
        btnSubtract.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);


    }

    @Override
    public void onClick(View view){
        String num1 = etfirst.getText().toString();
        String num2 = etsecond.getText().toString();
        switch(view.getId()){
            case R.id.btnAdd:
                int addition = Integer.parseInt(num1) + Integer.parseInt(num2);
                composeEmail("addition",String.valueOf(addition));
                //sendMessage(String.valueOf(addition));
                //tvresult.setText(String.valueOf(addition));
                break;
            case R.id.btnSubtract:
                int subtraction = Integer.parseInt(num1) - Integer.parseInt(num2);
                composeEmail("subtraction",String.valueOf(subtraction));
                //sendMessage(String.valueOf(subtraction));
                //tvresult.setText(String.valueOf(subtraction));
                break;
            case R.id.btnDivide:
                try{
                    int division = Integer.parseInt(num1) / Integer.parseInt(num2);
                    composeEmail("division",String.valueOf(division));
                    //sendMessage(String.valueOf(division));
                    //tvresult.setText(String.valueOf(division));
                }catch (Exception e){
                    composeEmail("UNDEFINED","The Universe is Gonna Explode");
                    //sendMessage(String.valueOf("CANT DIVIDE!!"));
                    //tvresult.setText("cannot Divide!");
                }
                break;
            case R.id.btnMultiply:
                int multiply = Integer.parseInt(num1) * Integer.parseInt(num2);
                composeEmail("multiply",String.valueOf(multiply));
                //sendMessage(String.valueOf(multiply));
                //tvresult.setText(String.valueOf(multiply));
                break;
        }
    }

    /** Toaster is called when user wants to toast the result */
    public void sendMessage(CharSequence text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        //chained toast methods for optimal toasting
        Toast.makeText(context, text, duration).show();
    }

    public void composeEmail(String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
