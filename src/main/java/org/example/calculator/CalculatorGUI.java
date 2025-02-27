package org.example.calculator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.io.IOException;



public class CalculatorGUI extends Application {
    Calculator calc;
    Stage window;
    VBox mainContainer;
    Label lblDisplayScreen;
    VBox buttonContainer;
    String numberInput;
    boolean isEnteringNumber;
    public void initializeUI(){
        numberInput = "0"; //This keeps track of the input string. This will be parsed into a double and passed to the calculator during calculation
        isEnteringNumber = false; //when you start inputing a number, this is set to true, thus appending the next input to the input string.
        // when you press any button that is not part of the number,it becomes false. Therefore, the next input number will
        //replace the current input string.
        initializeDisplay();
//        initializeInputBox();
        initializeButtons();
        initializeWindow();
    }


    //Initializing UI Components

    public void initializeDisplay(){

        lblDisplayScreen = new Label(numberInput);
        lblDisplayScreen.setBackground(new Background(new BackgroundFill(Color.web("#E0E0E0"), CornerRadii.EMPTY, Insets.EMPTY)));
        lblDisplayScreen.setFont(new Font("Calibri Light", 40));
        lblDisplayScreen.setMaxWidth(1920);
        lblDisplayScreen.setMinHeight(100);
        lblDisplayScreen.setAlignment(Pos.BOTTOM_RIGHT);
        lblDisplayScreen.setTextAlignment(TextAlignment.RIGHT);
    }

    public Button initializeNumber(String number){
        Button button = new Button(number);
        button = formatButton(button);
        button.setOnAction(event -> handleNumberInput(number));
        return button;
    }
    public void initializeButtons(){
        buttonContainer = new VBox();
        HBox row1 = new HBox();
        HBox row2 = new HBox();
        HBox row3 = new HBox();
        HBox row4 = new HBox();
        HBox row5 = new HBox();

        Button btnPlus =  initializeOperator("+");
        Button btnMinus = initializeOperator("-");
        Button btnMult = initializeOperator("x");
        Button btnDiv = initializeOperator("/");

        Button btn1 = initializeNumber("1");
        Button btn2 = initializeNumber("2");
        Button btn3 = initializeNumber("3");
        Button btn4 = initializeNumber("4");
        Button btn5 = initializeNumber("5");
        Button btn6 = initializeNumber("6");
        Button btn7 = initializeNumber("7");
        Button btn8 = initializeNumber("8");
        Button btn9 = initializeNumber("9");
        Button btn0 = initializeNumber("0");
        Button btnPoint = initializeNumber(".");

        Button btnNegate = new Button("-");
        btnNegate = formatButton(btnNegate);
        btnNegate.setOnAction(event -> btnNegateOnClick());

        Button btnCalculate = new Button("=");
        btnCalculate = formatButton(btnCalculate);
        btnCalculate.setOnAction(event ->  btnCalculateOnClick());

        Button btnReset = new Button("C");
        btnReset = formatButton(btnReset);
        btnReset.setOnAction(event -> btnResetOnClick());

        Button btnBackspace = new Button("<-");
        btnBackspace = formatButton(btnBackspace);
        btnBackspace.setOnAction(event -> btnBackspaceOnClick());

        row1.getChildren().addAll(btnReset, btnBackspace);
//        row1.setSpacing(1);
        row2.getChildren().addAll(btn1,btn2,btn3,btnPlus);
//        row2.setSpacing(1);
        row3.getChildren().addAll(btn4,btn5,btn6,btnMinus);
//        row3.setSpacing(1);
        row4.getChildren().addAll(btn7,btn8,btn9, btnMult);
//        row4.setSpacing(1);
        row5.getChildren().addAll(btnNegate,btn0,btnPoint, btnDiv);
//        row5.setSpacing(1);

        buttonContainer.getChildren().addAll(row1, row2, row3, row4, row5,btnCalculate);
//        buttonContainer.setSpacing(1);

    }

    public Button formatButton( Button button){
        button.setFont(new Font("Calibri Light", 24));
        HBox.setHgrow(button, Priority.ALWAYS);
        button.setMaxHeight(1080);
        button.setMaxWidth(1920);
        button.minHeight(50);
        return button;
    }

    public Button initializeOperator(String operator){
        Button button = new Button(operator);
        button.setOnAction(event -> btnOperatorOnClick(operator));
        return formatButton(button);
    }



    public void initializeWindow(){
        window = new Stage();
        window.setTitle("Calculator");

        mainContainer = new VBox();
        mainContainer.setPadding(new Insets(2));
        mainContainer.getChildren().addAll(lblDisplayScreen,buttonContainer);

        Scene scene = new Scene(mainContainer, 325,420);

        window.setScene(scene);
        window.setMinWidth(325);
        window.setMinHeight(420);
    }

    //Handling number input
    public void handleNumberInput(String number){
        if (isEnteringNumber){

            numberInput = numberInput + number;
        }
        else {
            if (number.equals(".")){
                numberInput = "0.";
            }
            else{
                numberInput = number;
            }
        }
        isEnteringNumber = true;
        lblDisplayScreen.setText(calc.getExpression() + "\n" + numberInput);
    }

    //Handling button Clicks
    public void btnOperatorOnClick(String operator){
        isEnteringNumber = false;
        try{
            if (calc.getOperator().isEmpty() ){
                calc.setOperator(operator);
                calc.setNum1(Double.parseDouble(numberInput));
                calc.setExpression(calc.formatDouble(calc.getNum1()) + " " + operator +" ");
                lblDisplayScreen.setText(calc.getExpression() + "\n="+ calc.formatDouble(calc.getNum1()));
                calc.setAnswer(calc.getNum1());
//                calc.setNum2(calc.getAnswer());
            }
            else{
                calc.setNum2(Double.parseDouble(numberInput));
                calc.calculate();
                calc.setExpression(calc.formatDouble(calc.getAnswer()) + " " + operator +" ");
                lblDisplayScreen.setText(calc.getExpression() + "\n= " + calc.formatDouble(calc.getAnswer()));
                calc.setOperator(operator);
                calc.setNum1(calc.getAnswer());
                numberInput = String.valueOf(calc.getAnswer());
            }

        } catch (Exception e) {
            lblDisplayScreen.setText("Invalid Expression");
        }
    }

    public void btnCalculateOnClick(){
        isEnteringNumber = false;
        try{
            if (calc.getOperator().isEmpty() ){
                calc.setNum1(Double.parseDouble(numberInput));
                calc.setAnswer(calc.getNum1());
                calc.setExpression(calc.formatDouble(calc.getAnswer()));
                lblDisplayScreen.setText(calc.getExpression() + "\n="+ calc.formatDouble(calc.getAnswer()));
            }
            else{
                calc.setNum2(Double.parseDouble(numberInput));
                calc.calculate();
                calc.setExpression(calc.getExpression()+ calc.formatDouble(calc.getNum2()));
            }
            lblDisplayScreen.setText(calc.getExpression() + "\n= " + calc.formatDouble(calc.getAnswer()));


        } catch (Exception e) {

            lblDisplayScreen.setText("Invalid Expression");
        }
        numberInput = calc.formatDouble(calc.getAnswer());
//        txtInput.requestFocus();
    }

    public void btnNegateOnClick(){
        if (numberInput.charAt(0) == '-'){
            numberInput = numberInput.substring(1);
        }
        else {
            numberInput = "-" + numberInput;
        }

        lblDisplayScreen.setText(calc.getExpression() + "\n" + numberInput);
    }

    public void btnResetOnClick(){
        calc.reset();
        numberInput = "0";
//        enteringInput = false;
        lblDisplayScreen.setText("0");

//        txtInput.setText("0");
    }

    public void btnBackspaceOnClick(){
        if (numberInput.length() == 1){
            numberInput = "0";
            isEnteringNumber = false;
        }
        else{
//            input = removeLast(input);
            numberInput = numberInput.substring(0, numberInput.length()-1);
        }

        lblDisplayScreen.setText(calc.getExpression() + "\n" + numberInput);

    }

    @Override
    public void start(Stage window) throws IOException {
        calc = new Calculator();

        initializeUI();

        this.window.show();
    }

    public static void main(String[] args) {
        launch();
    }
}