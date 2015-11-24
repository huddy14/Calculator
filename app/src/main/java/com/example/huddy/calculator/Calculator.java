package com.example.huddy.calculator;

import java.text.DecimalFormat;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Created by huddy on 11/18/15.
 */
public class Calculator {


    static int precedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 5;
            case '*':
            case '/':
                return 10;
            default:
                return 0;
        }
    }

    public static String infixToPostfix(String infix) {
        StringTokenizer tokenizer = new StringTokenizer(infix);
        String postfix = "";
        Stack opStack = new Stack();

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            char c = token.charAt(0);
            if (Character.isDigit(c)) {
                postfix += (token + " ");
            }
            else {
                while (!opStack.empty()) {
                    char top = (Character) opStack.peek();
                    if (precedence(top) >= precedence(c)) {
                        postfix += (top + " ");
                        opStack.pop();
                    }
                    else {
                        break;
                    }
                }

                opStack.push(Character.valueOf(c));
            }
        }

        while (!opStack.empty()) {
            char top = (Character) opStack.pop();
            postfix += (top + " ");
        }

        return postfix;
    }

    public static double evalPostfix(String postfix) {
        StringTokenizer tokenizer = new StringTokenizer(postfix);
        Stack valStack = new Stack();

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            char c = token.charAt(0);
            if (Character.isDigit(c)) {
                valStack.push(new Double(token));
            }
            else {
                double rightVal = (Double) valStack.pop();
                double leftVal = (Double) valStack.pop();
                double rslt = 0.0;

                switch (c) {
                    case '+': rslt = leftVal + rightVal; break;
                    case '-': rslt = leftVal - rightVal; break;
                    case '*': rslt = leftVal * rightVal; break;
                    case '/': rslt = leftVal / rightVal; break;
                    default:
                        rslt = 0.0;
                        break;
                }

                valStack.push(rslt);
            }
        }

        double rslt = (Double) valStack.pop();


        return rslt;
    }


    public String Round(double number)
    {
        DecimalFormat dF = new DecimalFormat("#.############");
        String result = dF.format(number);
        if(result.length()>15)result = result.substring(0,14);
        return dF.format(number);
    }
}
