/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiclassification;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import java.io.FileInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author J392018
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button btnPlay;
    @FXML
    private Button btnYes;
    @FXML
    private Button btnNo;
    @FXML
    private Text questionTxt;
    @FXML
    private Pane startPane;
    @FXML
    private Pane guessPane;
    @FXML
    private Pane resultPane;
    @FXML
    private Text thxTxt;
    @FXML
    private Pane giveupPane;
    @FXML
    private TextField newAnimalTxt;
    @FXML
    private Button btnOk;
    @FXML
    private Pane addQuestionPane;
    @FXML
    private TextField newQuestionTxt;
    @FXML
    private Button nBtnYes;
    @FXML
    private Button nBtnNo;
    @FXML
    private Pane replayPane;
    @FXML
    private Button btnReplay;
    @FXML
    private Button btnEnd;
    
    BTree animalTree = new BTree();
    Node root;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }

    public void stageController(String stage)
    {
        switch(stage)
        {
            case "start":
                resetStage();
                
                startPane.setVisible(true);
                startPane.setDisable(false);
                return;
            case "guessing":
                resetStage();
                
                guessPane.setVisible(true);
                guessPane.setDisable(false);
                return;
            case "giveup":
                resetStage();
                
                giveupPane.setVisible(true);
                giveupPane.setDisable(false);
                return;
            case "add":
                resetStage();
                
                addQuestionPane.setVisible(true);
                addQuestionPane.setDisable(false);
                return;
            case "result":
                resetStage();
                
                resultPane.setVisible(true);
                resultPane.setDisable(false);
                replayPane.setVisible(true);
                replayPane.setDisable(false);
                return;
            default:
                return;
        }
    }
    public void resetStage()
    {
        startPane.setVisible(false);
        startPane.setDisable(true);
        guessPane.setVisible(false);
        guessPane.setDisable(true);
        resultPane.setVisible(false);
        resultPane.setDisable(true);
        giveupPane.setVisible(false);
        giveupPane.setDisable(true);
        addQuestionPane.setVisible(false);
        addQuestionPane.setDisable(true);
        replayPane.setVisible(false);
        replayPane.setDisable(true);
    }
    
    public void readData()
    {
        try
        {
            FileInputStream inputFile = new FileInputStream("savedata.dat");
            ObjectInputStream inputObj = new ObjectInputStream(inputFile);
            root = (Node) inputObj.readObject();
            inputObj.close();
            inputFile.close();
        } 
        catch (IOException i)
        {
            //todo
            return;
        }
        catch (ClassNotFoundException c)
        {
            //todo
            return;
        }
    }
}
