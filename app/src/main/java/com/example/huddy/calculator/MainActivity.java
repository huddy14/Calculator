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

    static final String RESULT = "result";
    static final String OPERATION = "operation";
    static final String BOOL_ISOPER = "isOper";
    static final String BOOL_ISZEROFIRST = "isZeroFirst";
    static final String BOOL_ISDOT = "isDot";
    static final String BOOL_CANENTER = "canEnter";
    static final String BOOL_EQUEALSCLICKED = "equealsClicked";
    private Button one,two,three,four,five,six,seven,eight,nine,zero,btnClear,btnAdd,btnSub,btnDiv,btnMult,btnEquals,btnDot;
    private TextView tvOperation,tvResult;
    private boolean isOper = false;
    private boolean isZeroFirst = true;
    private boolean isDot = false;
    private boolean canEnter = true;
    private boolean EquealsClicked = false;
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
            isZeroFirst = savedInstanceState.getBoolean(BOOL_ISZEROFIRST);
            isDot = savedInstanceState.getBoolean(BOOL_ISDOT);
            canEnter = savedInstanceState.getBoolean(BOOL_CANENTER);
            isOper = savedInstanceState.getBoolean(BOOL_ISOPER);
            EquealsClicked = savedInstanceState.getBoolean(BOOL_EQUEALSCLICKED);
        }




    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putString(RESULT,result);
        savedInstanceState.putString(OPERATION,operation);
        savedInstanceState.putBoolean(BOOL_CANENTER, canEnter);
        savedInstanceState.putBoolean(BOOL_ISDOT, isDot);
        savedInstanceState.putBoolean(BOOL_ISOPER, isOper);
        savedInstanceState.putBoolean(BOOL_ISZEROFIRST, isZeroFirst);
        savedInstanceState.putBoolean(BOOL_EQUEALSCLICKED,EquealsClicked);
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
        if(tvOperation.getText().toString()=="0")tvOperation.setText("");
        int btnId = v.getId();
        switch(v.getId())
        {
            case R.id.button0:
                //String s = tvOperation.getText().toString();
                if(isOper && canEnter)
                {
                    operation = tvOperation.getText() + "0";
                    tvOperation.setText(operation);
                    isOper = false;
                    isZeroFirst = true;
                    canEnter = false;
                }
                else if((!isZeroFirst || isDot) && canEnter) {
                    operation = tvOperation.getText() + "0";
                    tvOperation.setText(operation);
                    isOper = false;
                }
                //if(isOper)isZeroFirst=true;

                break;

            case R.id.button1:
                if(canEnter) {
                    numberClick('1');
                }
                break;

            case R.id.button2:
                if(canEnter) {
                    numberClick('2');
                }
                break;
            case R.id.button3:
                if(canEnter) {
                    numberClick('3');
                }
                break;
            case R.id.button4:
                if(canEnter) {
                    numberClick('4');
                }
                break;
            case R.id.button5:
                if(canEnter) {
                    numberClick('5');
                }
                break;
            case R.id.button6:
                if(canEnter) {
                    numberClick('6');
                }
                break;
            case R.id.button7:
                if(canEnter) {
                    numberClick('7');
                }
                break;
            case R.id.button8:
                if(canEnter) {
                    numberClick('8');
                }
                break;
            case R.id.button9:
                if(canEnter) {
                    numberClick('9');
                }
                break;
            //operation buttons
            case R.id.buttonAdd:
                if(EquealsClicked)onEquealsClick();
                if(!isOper)
                {
                    operationClick('+');
                }
                break;
            case R.id.buttonSubstract:
                if(EquealsClicked)onEquealsClick();
                if(!isOper) {
                    operationClick('-');
                }
                break;
            case R.id.buttonMultiiply:
                if(EquealsClicked)onEquealsClick();
                if(!isOper) {
                    operationClick('*');
                }
                break;
            case R.id.buttonDevide:
                if(EquealsClicked)onEquealsClick();
                if(!isOper) {
                    operationClick('/');
                }
                break;
            case R.id.buttonEquals:
                if(!isOper) {
                    double res = Calculator.evalPostfix(Calculator.infixToPostfix(tvOperation.getText().toString()));
                    tvResult.setText("" + res);
                    result = ""+res;
                    canEnter = false;isZeroFirst = false;
                    EquealsClicked = true;
                }
                break;
            case R.id.buttonClear:
                clear_tvOperation();
                clear_tvResult();
                result = "";
                operation ="";
                isDot = false;
                canEnter = true;
                break;
            case R.id.buttonDot:

                if(!isDot) {
                    tvOperation.setText(tvOperation.getText() + ".");
                    isDot = true;
                    isZeroFirst = false;
                    canEnter = true;
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

    private void operationClick(char s)
    {
        operation = tvOperation.getText() + " "+s+" ";
        tvOperation.setText(operation);
        isOper = true;
        isDot = false;
        canEnter = true;
        isZeroFirst = false;

    }

    private void numberClick(char s)
    {
        operation = tvOperation.getText()+""+ s;
        tvOperation.setText(operation);
        isOper = false;
        isZeroFirst = false;
    }

    private void onEquealsClick()
    {
        tvResult.setText("");
        tvOperation.setText(result);
        isOper = false;
        isZeroFirst = true;
        isDot = false;
        canEnter = true;
        EquealsClicked = false;

    }
}
