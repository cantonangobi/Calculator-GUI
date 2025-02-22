package org.example.calculator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;



public class HelloApplication extends Application {
    Calculator calc;
    Stage window;
    Scene scene;
    VBox layout;
    Label lblOutput;
    TextField txtInput;
    Button btnPlus, btnMinus, btnMult, btnDiv, btnCalculate, btnReset;

    public void initializeUI(){
        initializeOutputBox();
        initializeInputBox();
        initializeButtons();
        initializeWindow();
    }

    public void initializeOutputBox(){
        lblOutput = new Label("0");
        lblOutput.setBackground(new Background(new BackgroundFill(Color.web("#E0E0E0"), CornerRadii.EMPTY, Insets.EMPTY)));
        lblOutput.setFont(new Font("Calibri", 20));
        lblOutput.setMaxWidth(250);
    }

    public void initializeInputBox(){
        txtInput = new TextField("");
    }


    public void initializeButtons(){



        btnPlus =  initializeOperator("+");
        btnMinus = initializeOperator("-");
        btnMult = initializeOperator("x");
        btnDiv = initializeOperator("/");

        btnCalculate = new Button("=");
        btnCalculate.setMaxWidth(250);
        btnCalculate.setOnAction(event -> {
            try{
                calc.setNum2(Double.parseDouble(txtInput.getText()));
                calc.calculate();
                double answer = calc.getAnswer();
                lblOutput.setText(calc.getExpression() + calc.formatDouble(calc.getNum2()) + " = " + calc.formatDouble(answer));

            } catch (Exception e) {

                lblOutput.setText("Invalid Expression");
            }

            txtInput.setText("");
            txtInput.requestFocus();
        });

        btnReset = new Button("RESET");
        btnReset.setMaxWidth(250);
        btnReset.setOnAction(event -> {
            calc.reset();
            lblOutput.setText("0");
            txtInput.setText("0");
        });
    }

    public void initializeWindow(){
        window = new Stage();
        window.setTitle("Calculator");

        layout = new VBox(10);
        layout.setPadding(new Insets(2));
        layout.getChildren().addAll(lblOutput,txtInput,btnReset,btnPlus,btnMinus,btnMult,btnDiv,btnCalculate);

        scene = new Scene(layout, 250,250);

        window.setScene(scene);
        window.setMaxWidth(250);
        window.setMaxHeight(320);
        window.setMinWidth(250);
        window.setMinHeight(320);
    }

    public Button initializeOperator(String operator){
        Button button = new Button(operator);
        button.setMaxWidth(250);
        button.setOnAction(event -> {
            try{
                calc.setOperator(operator);
                calc.setNum1(Double.parseDouble(txtInput.getText()));
                calc.setExpression(calc.formatDouble(calc.getNum1()) + " " + operator +" ");
                lblOutput.setText(calc.getExpression());
                txtInput.setText("");
                txtInput.requestFocus();
            } catch (Exception e) {
                lblOutput.setText("Invalid Expression");
            }

        });
        return button;
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