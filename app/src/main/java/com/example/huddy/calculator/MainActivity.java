package com.example.huddy.calculator;
/*
TODO: make some tests why 0.2 * 3 isnt working,
TODO: make flags work as intendent, some input policy
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import static com.example.huddy.calculator.R.id.button1;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAdd;
    private Button btnSub;
    private Button btnDiv;
    private Button btnMult;
    private Button btnEquals;
    private Button btnDot;
    private Button one,two,three,four,five,six,seven,eight,nine,zero,btnClear;
    static final String RESULT = "result";
    static final String OPERATION = "operation";
    private TextView tvResult;
    private TextView tvOperation;
    private boolean isOper = false;
    private boolean isZeroFirst = true;
    private boolean isDot = false;
    private boolean canEnter = true;
    private String result,operation;
    int i = 0;

    private Calculator calc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        viewInitialization();


        if(savedInstanceState!=null)
        {
            tvResult.setText(savedInstanceState.getString(RESULT));
            tvOperation.setText(savedInstanceState.getString(OPERATION));
        }




    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putString(RESULT,result);
        savedInstanceState.putString(OPERATION,operation);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }
    /*
    initialization of view elements
    */
    private void viewInitialization()
    {
        //finding elements
        btnAdd = (Button)findViewById(R.id.buttonAdd);
        btnSub = (Button)findViewById(R.id.buttonSubstract);
        btnMult = (Button)findViewById(R.id.buttonMultiiply);
        btnDiv = (Button)findViewById(R.id.buttonDevide);
        btnEquals=(Button)findViewById(R.id.buttonEquals);
        btnDot = (Button)findViewById(R.id.buttonDot);
        tvOperation = (TextView)findViewById(R.id.textViewOperation);
        tvResult = (TextView)findViewById(R.id.textViewResult);
        one = (Button)findViewById(R.id.button1);
        two= (Button)findViewById(R.id.button2);
        three = (Button)findViewById(R.id.button3);
        four = (Button)findViewById(R.id.button4);
        five = (Button)findViewById(R.id.button5);
        six = (Button)findViewById(R.id.button6);
        seven = (Button)findViewById(R.id.button7);
        eight = (Button)findViewById(R.id.button8);
        nine = (Button)findViewById(R.id.button9);
        zero = (Button)findViewById(R.id.button0);
        btnClear = (Button)findViewById(R.id.buttonClear);


        //setting up listener for operation buttons
        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnMult.setOnClickListener(this);
        btnEquals.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnDot.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);


    }

    @Override
    public void onClick(View v)
    {
        if(tvOperation.getText()=="0")clear_tvOperation();
        int btnId = v.getId();
        switch(v.getId())
        {
            case R.id.button0:
                String s = tvOperation.getText().toString();
                if(!isZeroFirst || isDot) {
                    operation = tvOperation.getText() + "0";
                    tvOperation.setText(operation);
                }
                if(isOper)isZeroFirst=true;
                isOper = false;
                break;

            case R.id.button1:
                if(canEnter) {
                    operation = tvOperation.getText() + "1";
                    tvOperation.setText(operation);
                    isOper = false;
                }
                break;

            case R.id.button2:
                if(canEnter) {
                    operation = tvOperation.getText() + "2";
                    tvOperation.setText(operation);
                    isOper = false;
                }
                break;
            case R.id.button3:
                if(canEnter) {
                    operation = tvOperation.getText() + "3";
                    tvOperation.setText(operation);
                    isOper = false;
                }
                break;
            case R.id.button4:
                if(canEnter) {
                    operation = tvOperation.getText() + "4";
                    tvOperation.setText(operation);
                    isOper = false;
                }
                break;
            case R.id.button5:
                if(canEnter) {
                    operation = tvOperation.getText() + "5";
                    tvOperation.setText(operation);
                    isOper = false;
                }
                break;
            case R.id.button6:
                if(canEnter) {
                    operation = tvOperation.getText() + "6";
                    tvOperation.setText(operation);
                    isOper = false;
                }
                break;
            case R.id.button7:
                if(canEnter) {
                    operation = tvOperation.getText() + "7";
                    tvOperation.setText(operation);
                    isOper = false;
                }
                break;
            case R.id.button8:
                if(canEnter) {
                    operation = tvOperation.getText() + "8";
                    tvOperation.setText(operation);
                    isOper = false;
                }
                break;
            case R.id.button9:
                if(canEnter) {
                    operation = tvOperation.getText() + "9";
                    tvOperation.setText(operation);
                    isOper = false;
                }
                break;
            //operation buttons
            case R.id.buttonAdd:
                if(!isOper)
                {
                    tvOperation.setText(tvOperation.getText()+" + ");
                }
                break;
            case R.id.buttonSubstract:
                tvOperation.setText(tvOperation.getText()+" - ");
                isOper = true;
                isDot = false;
                break;
            case R.id.buttonMultiiply:
                tvOperation.setText(tvOperation.getText()+" * ");
                isOper = true;
                isDot = false;
                break;
            case R.id.buttonDevide:
                tvOperation.setText(tvOperation.getText()+" / ");
                isOper = true;
                isDot = false;
                break;
            case R.id.buttonEquals:
                if(!isOper) {
                    double res = Calculator.evalPostfix(Calculator.infixToPostfix(tvOperation.getText().toString()));
                    tvResult.setText("" + res);
                    result = ""+res;
                }
                break;
            case R.id.buttonClear:
                clear_tvOperation();
                clear_tvResult();
                isDot = false;
                canEnter = true;
                break;
            case R.id.buttonDot:
                if(!isDot && canEnter) {
                    tvOperation.setText(tvOperation.getText() + ".");
                    isDot = true;
                }
                break;

            default:break;

        }
    }
    private void clear_tvOperation()
    {
        tvOperation.setText("0");
    }
    private void clear_tvResult()
    {
        tvResult.setText("");
    }
}
