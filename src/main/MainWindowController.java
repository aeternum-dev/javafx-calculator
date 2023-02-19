package main;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;


public class MainWindowController {
    @FXML private Pane titlePane;
    @FXML private ImageView btnClose;
    @FXML private ImageView btnMinimize;
    @FXML private Label calculatorOutput;

    private double x, y;
    private double num1 = 0;
    private String operator = "+";

    public void init(Stage stage) {
        titlePane.setOnMousePressed(mouseEvent -> {
            x= mouseEvent.getSceneX();
            y= mouseEvent.getSceneY();
        });
        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });

        btnClose.setOnMouseClicked(mouseEvent -> stage.close());
        btnMinimize.setOnMouseClicked((mouseEvent -> stage.setIconified(true)));
    }

    @FXML
    void onNumberClicked(MouseEvent event) {
        int value = Integer.parseInt(((Pane)event.getSource()).getId().replace("btn",""));
        calculatorOutput.setText(Double.parseDouble(calculatorOutput.getText())==0?String.valueOf((double)value)
                :String.valueOf(Double.parseDouble(calculatorOutput.getText())*10+value));
    }

    @FXML
    void onSymbolClicked(MouseEvent event) {
        String symbol = ((Pane)event.getSource()).getId().replace("btn","");

        if(symbol.equals("Equation")) {
            double num2 = Double.parseDouble(calculatorOutput.getText());
            switch (operator) {
                case  "+" -> calculatorOutput.setText((num1+num2) + "");
                case  "-" -> calculatorOutput.setText((num1-num2) + "");
                case  "*" -> calculatorOutput.setText((num1*num2) + "");
                case  "/" -> calculatorOutput.setText((num1/num2) + "");
            }
            operator = ".";
        }
        else if(symbol.equals("Clear")) {
            calculatorOutput.setText(String.valueOf(0.0));
            operator = ".";
        }
        else {
            switch (symbol) {
                case "Addition" -> operator = "+";
                case "Subtraction" -> operator = "-";
                case "Multiplication" -> operator = "*";
                case "Division" -> operator = "/";
            }
            num1 = Double.parseDouble(calculatorOutput.getText());
            calculatorOutput.setText(String.valueOf(0.0));

        }


    }

}
