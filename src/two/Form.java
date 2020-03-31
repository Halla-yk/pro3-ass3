/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package two;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Abu Yasser
 */
public class Form extends Application {

    private TextField t;
    private PasswordField p;
    private Button reset;
    private Button exit;
    private Scene s2;
    private Scene s3;
    private Button add;
    private Button add2;
    private Button view;
    private Label alert;
    private Label welcome;
    private Label studentData;
    private ListView<Student> list;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        /////////////////scene 2///////////////////
        add = new Button("add");
        add.setStyle("-fx-background-color: #fc033d");
        add.setOnAction(event ->{primaryStage.setScene(s3);
        primaryStage.setTitle("Student Entry Page");});
        view = new Button("view");
        view.setStyle("-fx-background-color: #fc033d");
        VBox vbox2 = new VBox(10, add, view);
        vbox2.setAlignment(Pos.CENTER);
        vbox2.setStyle("-fx-background-color: #ffc5a6");
        ////////////////scene 3///////////////////
        list = new ListView<>();
        add2 = new Button("add");
        add2.setStyle("-fx-background-color: #fc033d");
        reset = new Button("reset");
        reset.setStyle("-fx-background-color: #fc033d");
        exit = new Button("exit");
        exit.setStyle("-fx-background-color: #fc033d");
        HBox buttons = new HBox(5, add2, reset, exit);
       // buttons.setAlignment(Pos.CENTER);
        studentData = new Label("Student Data");
        studentData.setStyle("-fx-font-size:23px ;");
        Text id = new Text("id:        ");
        TextField idT = new TextField();
        HBox h1 = new HBox(10, id, idT);
        Text name = new Text("Name: ");
        TextField nameT = new TextField();
        HBox h2 = new HBox(10, name, nameT);
        Text major = new Text("Major: ");
        TextField majorT = new TextField();
        HBox h3 = new HBox(10, major, majorT);
        Text grade = new Text("Grade:");
        TextField gradeT = new TextField();
        HBox h4 = new HBox(10, grade, gradeT);
        VBox v = new VBox(10, studentData, h1, h2, h3, h4, buttons);
     //   v.setStyle(STYLESHEET_MODENA);
      HBox all = new HBox(20,v,list);
      all.setStyle("-fx-background-color: #ffc5a6");
        all.setPadding(new Insets(20));
        v.setPadding(new Insets(20));
        list.setOnMouseClicked(new EventHandler<MouseEvent>() {

    @Override
    public void handle(MouseEvent click) {

        if (click.getClickCount() == 2) {
           idT.setText(Integer.toString(list.getSelectionModel().getSelectedItem().id));
           nameT.setText(list.getSelectionModel().getSelectedItem().name);
           majorT.setText(list.getSelectionModel().getSelectedItem().major);
           gradeT.setText(Double.toString(list.getSelectionModel().getSelectedItem().grade));
          
        }
    }
        });
        add2.setOnAction(event -> {
            if (event.getSource() == add2) {
                double gd = Double.parseDouble(gradeT.getText());
                int intId = Integer.parseInt(idT.getText());
                // if(!textFieldName.getText().equals(""))
                list.getItems().add(new Student(intId, nameT.getText(), majorT.getText(), gd));
                // textFieldName.setText("");
                list.getItems().setAll(
                        list.getItems().stream()
                                .sorted(new Comparator<Student>() {
                                    @Override
                                    public int compare(Student o1, Student o2) {
                                        return -o1.compareTo(o2);
                                    }
                                }
                                ).collect(Collectors.toList())
                );
            }
        });
        reset.setOnAction(event ->{if(event.getSource() == reset){
             //  textFieldName.setText(listViewNames.getSelectionModel().getSelectedItem());
                list.getItems().removeAll(list.getSelectionModel().getSelectedItem());
                double gd = Double.parseDouble(gradeT.getText());
                int intId = Integer.parseInt(idT.getText());
                // if(!textFieldName.getText().equals(""))
                list.getItems().add(new Student(intId, nameT.getText(), majorT.getText(), gd));
                // textFieldName.setText("");
                list.getItems().setAll(
                        list.getItems().stream()
                                .sorted(new Comparator<Student>() {
                                    @Override
                                    public int compare(Student o1, Student o2) {
                                        return -o1.compareTo(o2);
                                    }
                                }
                                ).collect(Collectors.toList())
                );
        }});
        exit.setOnAction(event -> {
        System.exit(0);});
        //////////////////////////////////
        s2 = new Scene(vbox2, 300, 250);
        
        s3 = new Scene(all, 600,300);
        // s1.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        //s2.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        
        primaryStage.setScene(s2);
        primaryStage.setTitle("option page");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
