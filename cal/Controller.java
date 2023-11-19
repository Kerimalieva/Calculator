package com.example.cal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Controller {

    @FXML
    private TextField textField;

    @FXML
    private Text savedNumbers;

    private String firstNumber = "";
    private String currentNumber = "";
    private String calculationType;

    @FXML
    void addAction(ActionEvent event) {
        calculationSetup("+");
    }

    @FXML
    void minusAction(ActionEvent event) {
        calculationSetup("-");
    }

    @FXML
    void divideAction(ActionEvent event) {
        calculationSetup("/");
    }

    @FXML
    void multiplicationAction(ActionEvent event) {
        calculationSetup("*");
    }

    public void calculationSetup(String calculationType) {
        this.calculationType = calculationType;
        firstNumber = currentNumber;
        currentNumber = "";
        savedNumbers.setText(firstNumber + " " + calculationType);
    }

    @FXML
    void calculate(ActionEvent event) {
        if (firstNumber.isEmpty() || currentNumber.isEmpty()) {
            savedNumbers.setText("Error: Incomplete calculation");
            textField.setText("");
            return;
        }

        try {
            int firstNumberInt = Integer.parseInt(firstNumber);
            int secondNumberInt = Integer.parseInt(currentNumber);

            switch (calculationType) {
                case "+" -> performAndDisplayResult(firstNumberInt + secondNumberInt, "+");
                case "-" -> performAndDisplayResult(firstNumberInt - secondNumberInt, "-");
                case "/" -> {
                    if (secondNumberInt == 0) {
                        throw new DivisionByZeroException("Cannot divide by zero");
                    }
                    performAndDisplayResult(firstNumberInt / (double) secondNumberInt, "/");
                }
                case "*" -> performAndDisplayResult(firstNumberInt * secondNumberInt, "*");
            }
        } catch (NumberFormatException e) {
            savedNumbers.setText("Error: Invalid number format");
            textField.setText("");
        } catch (DivisionByZeroException e) {
            // Display an error message for division by zero
            savedNumbers.setText("Error: " + e.getMessage());
            textField.setText("");
        }
    }

    private void performAndDisplayResult(double result, String operator) {
        savedNumbers.setText(firstNumber + " " + operator + " " + currentNumber + " = " + result);
        textField.setText(String.valueOf(result));
    }


    @FXML
    void clearTextField(ActionEvent event) {
        currentNumber = "";
        textField.setText("");
        savedNumbers.setText("");
    }

    @FXML
    void button0Clicked(ActionEvent event) {
        if (!currentNumber.equals("")) {
            addNumber("0");
        }
    }

    @FXML
    void button1Clicked(ActionEvent event) {
        addNumber("1");
    }

    @FXML
    void button2Clicked(ActionEvent event) {
        addNumber("2");
    }

    @FXML
    void button3Clicked(ActionEvent event) {
        addNumber("3");
    }

    @FXML
    void button4Clicked(ActionEvent event) {
        addNumber("4");
    }

    @FXML
    void button5Clicked(ActionEvent event) {
        addNumber("5");
    }

    @FXML
    void button6Clicked(ActionEvent event) {
        addNumber("6");
    }

    @FXML
    void button7Clicked(ActionEvent event) {
        addNumber("7");
    }

    @FXML
    void button8Clicked(ActionEvent event) {
        addNumber("8");
    }

    @FXML
    void button9Clicked(ActionEvent event) {
        addNumber("9");
    }


    public void updateTextField() {
        textField.setText(currentNumber);
    }

    public void addNumber(String number) {
        currentNumber += number;
        updateTextField();
    }
}
