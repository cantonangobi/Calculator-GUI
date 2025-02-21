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

import static java.lang.Integer.MAX_VALUE;



public class HelloApplication extends Application {
    @Override
    public void start(Stage window) throws IOException {
        Calculator calc = new Calculator();
        window.setTitle("Calculator");

        Label lblOutput = new Label("_");
        lblOutput.setBackground(new Background(new BackgroundFill(Color.web("#E0E0E0"), CornerRadii.EMPTY, Insets.EMPTY)));
//        lblOutput
        lblOutput.setFont(new Font("Calibri", 20));
        lblOutput.setMaxWidth(250);
        TextField txtInput = new TextField("0");
        Button btnPlus = new Button("+");
        btnPlus.setMaxWidth(248);
        btnPlus.setOnAction(event -> {
            calc.setOperator("+");
            double num1 = Double.parseDouble(txtInput.getText());
            calc.setNum1(num1);
            String expression = String.valueOf(num1) + " + ";
            calc.setExpression(expression);
            lblOutput.setText(expression);

        });
        Button btnMinus = new Button("-");
        btnMinus.setMaxWidth(248);
        btnMinus.setOnAction(event -> {
            calc.setOperator("-");
            double num1 = Double.parseDouble(txtInput.getText());
            calc.setNum1(num1);
            String expression = String.valueOf(num1) + " - ";
            calc.setExpression(expression);
            lblOutput.setText(expression);
        });
        Button btnMult = new Button("x");
        btnMult.setMaxWidth(248);
        btnMult.setOnAction(event -> {
            calc.setOperator("x");
            double num1 = Double.parseDouble(txtInput.getText());
            calc.setNum1(num1);
            String expression = String.valueOf(num1) + " x ";
            calc.setExpression(expression);
            lblOutput.setText(expression);
        });
        Button btnDiv = new Button("/");
        btnDiv.setMaxWidth(248);
        btnDiv.setOnAction(event -> {
            calc.setOperator("/");
            double num1 = Double.parseDouble(txtInput.getText());
            calc.setNum1(num1);
            String expression = String.valueOf(num1) + " / ";
            calc.setExpression(expression);
            lblOutput.setText(expression);
        });
        Button btnCalculate = new Button("=");
        btnCalculate.setMaxWidth(250);
        btnCalculate.setOnAction(event -> {
            String text = txtInput.getText();
            double num2 = Double.parseDouble(txtInput.getText());
            calc.setNum2(num2);
            calc.calculate();
            String expression = calc.getExpression();
            double answer = calc.getAnswer();
            expression = expression + String.valueOf(num2) + " = " + String.valueOf(answer);
            lblOutput.setText(expression);
        });

        VBox box = new VBox(10);
//        box.setSpacing(5);
        box.setPadding(new Insets(2));

        box.getChildren().addAll(lblOutput,txtInput,btnPlus,btnMinus,btnMult,btnDiv,btnCalculate);

        Scene scene = new Scene(box, 250,250);


        window.setScene(scene);
        window.setMaxWidth(250);
        window.setMaxHeight(287);
        window.setMinWidth(250);
        window.setMinHeight(287);
        window.show();
    }

    public static void main(String[] args) {
        launch();
    }
}