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
    Button btnPlus, btnMinus, btnMult, btnDiv, btnCalculate;

    public void initializeOutputBox(){
        lblOutput = new Label("_");
        lblOutput.setBackground(new Background(new BackgroundFill(Color.web("#E0E0E0"), CornerRadii.EMPTY, Insets.EMPTY)));
        lblOutput.setFont(new Font("Calibri", 20));
        lblOutput.setMaxWidth(250);
    }

    public void initializeInputBox(){
        txtInput = new TextField("0");
    }


    public void initializeButtons(){
        calc = new Calculator();
        btnPlus = new Button("+");
        btnPlus.setMaxWidth(250);
        btnPlus.setOnAction(event -> {
            handleInput("+");
        });
        btnMinus = new Button("-");
        btnMinus.setMaxWidth(250);
        btnMinus.setOnAction(event -> {
            handleInput("-");
        });
        btnMult = new Button("x");
        btnMult.setMaxWidth(250);
        btnMult.setOnAction(event -> {
            handleInput("x");
        });
        btnDiv = new Button("/");
        btnDiv.setMaxWidth(250);
        btnDiv.setOnAction(event -> {
            handleInput("/");

        });
        btnCalculate = new Button("=");
        btnCalculate.setMaxWidth(250);
        btnCalculate.setOnAction(event -> {
            calc.setNum2(Double.parseDouble(txtInput.getText()));
            calc.calculate();
            double answer = calc.getAnswer();

            lblOutput.setText(calc.getExpression() + calc.formatDouble(calc.getNum2()) + " = " + calc.formatDouble(answer));
        });
    }

    public void initializeWindow(){
        window = new Stage();
        window.setTitle("Calculator");

        layout = new VBox(10);
        layout.setPadding(new Insets(2));
        layout.getChildren().addAll(lblOutput,txtInput,btnPlus,btnMinus,btnMult,btnDiv,btnCalculate);

        scene = new Scene(layout, 250,250);

        window.setScene(scene);
        window.setMaxWidth(250);
        window.setMaxHeight(287);
        window.setMinWidth(250);
        window.setMinHeight(287);
    }

    public void handleInput(String operator){
        calc.setOperator(operator);
        calc.setNum1(Double.parseDouble(txtInput.getText()));
        calc.setExpression(calc.formatDouble(calc.getNum1()) + " " + operator +" ");
        lblOutput.setText(calc.getExpression());
    }

    @Override
    public void start(Stage window) throws IOException {

        initializeOutputBox();
        initializeInputBox();
        initializeButtons();
        initializeWindow();

        this.window.show();
    }

    public static void main(String[] args) {
        launch();
    }
}