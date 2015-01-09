package interestratecalc;

import java.text.DecimalFormat;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * A Simple Interest Calculator. 
 * This is a JavaFX Desktop Application
 * Created on : Nov 2, 2014
 */
public class InterestRateCalc extends Application {
    
    
    /**
     * This method validates the string inputs passed in.
     * @param pText the principal input in string format
     * @param rText the rate input in string format
     * @param tpText time period input in string format
     * @return an error message if any of the inputs are not valid
     *          else it returns an empty message
     */
    private String validateInput(String pText, String rText, String tpText){
        String errorMessage = "";
        try{
            if (pText.isEmpty() || rText.isEmpty() || tpText.isEmpty()){
                errorMessage = "All fields are required.";
                return errorMessage;
            }
            
            double pDouble = Double.parseDouble(pText);
            double rDouble = Double.parseDouble(rText);
            double tpDouble = Double.parseDouble(tpText); 
            
            if (pDouble <= 0 || rDouble <= 0 || tpDouble <= 0){
                errorMessage = "Input should be greater than 0.";
                return errorMessage;
            }
            
            if (rDouble > 100){
                errorMessage = "Rate should not be more than 100.";
                return errorMessage;
            }
            
        }catch (NumberFormatException e){
            errorMessage = "Invalid Input, should be a number.";
        }
        return errorMessage;
    }
    
    /**
     * This method uses a grid layout format to generate the 
     * content of the UI.
     * @return a GridPane
     */
    private GridPane generateContent(){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text scenetitle = new Text("Simple Interest Calculator");
        scenetitle.setId("title-text");
        //scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        
        Text errorText = new Text();
        errorText.setId("error-display");
        grid.add(errorText, 0, 1, 2, 1);
        
        Label principal = new Label("Principal:");
        grid.add(principal, 0, 2);

        TextField principalTextField = new TextField();
        grid.add(principalTextField, 1, 2);
        
        Label rate = new Label("Rate:");
        grid.add(rate, 0, 3);

        TextField rateTextField = new TextField();
        grid.add(rateTextField, 1, 3);
        
        Text rateDescription = new Text("%");
        grid.add(rateDescription, 2, 3);
        
        Label period = new Label("Time Period:");
        grid.add(period, 0, 4);

        TextField periodTextField = new TextField();
        grid.add(periodTextField, 1, 4);
        
        Text periodDescription = new Text("years");
        grid.add(periodDescription, 2, 4);
        
        Button btn = new Button("Calculate");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 6);
        
        final Text resultdisplay = new Text();
        resultdisplay.setId("result-display");
        grid.add(resultdisplay, 1, 8);
        
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                String pText = principalTextField.getText();
                String rText = rateTextField.getText();
                String tpText = periodTextField.getText();
                
                String errorMessage = validateInput(pText, rText, tpText);
                
                if (errorMessage.equals("")){
                    double pDouble = Double.parseDouble(pText);
                    double rDouble = Double.parseDouble(rText);
                    double tpDouble = Double.parseDouble(tpText);                
                    double interest = (pDouble * rDouble * tpDouble)/100;
                    
                    DecimalFormat df = new DecimalFormat("#.00");
                    //resultdisplay.setFill(Color.DARKGREEN);
                    errorText.setText("");                    
                    resultdisplay.setText("Interest = "+df.format(interest));
                }else{
                    //errorText.setFill(Color.FIREBRICK);
                    resultdisplay.setText("");
                    errorText.setText(errorMessage);
                    
                }
                
                
            }
        });
        
        return grid;
    }
    
    
    /**
     * This method is the entry point of the application
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Interest Calculator");
        
        GridPane grid = generateContent();       
           
        Scene scene = new Scene(grid, 350, 300);
        scene.getStylesheets().add
        (InterestRateCalc.class.getResource("Style.css").toExternalForm());
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * This method is not required when the JAR file
     * for the application is created with the JavaFX Packager tool.
     * However it is necessary when running the application
     * through an IDE (such as NetBeans, Eclipse, etc).
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
