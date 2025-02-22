package org.example.calculator;

public class Calculator {

    private double num1, num2, answer;
    public String operator, expression;



    public Calculator(){
        num1 = 0;
        num2 = 0;
        answer = 0;
        expression = "";
    }

    public double getNum1() {
        return num1;
    }

    public void setNum1(double num1) {
        this.num1 = num1;
    }

    public double getNum2() {
        return num2;
    }

    public void setNum2(double num2) {
        this.num2 = num2;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public double getAnswer() {
        return answer;
    }

    public void setAnswer(double answer) {
        this.answer = answer;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void reset(){
        num1 = 0;
        num2 = 0;
        answer = 0;
        expression = "";
        operator = "";
    }

    public void calculate(){
        if (operator.equals("+")) {
            answer = num1 + num2;
        }
        else if (operator.equals("-")) {
            answer = num1 - num2;
        }
        else if (operator.equals("x")) {
            answer = num1 * num2;
        }
        else if (operator.equals("/")) {
            answer = num1 / num2;
        }
    }

    public String formatDouble(double num){
        if ((num*10) % 10 == 0){
            return String.format("%.0f",num);
        }
        return String.valueOf(num);
    }



}
