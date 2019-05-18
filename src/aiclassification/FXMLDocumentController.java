/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiclassification;

import java.io.File;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
    String newAnimal;
    @FXML
    private Text askNQTxt;
    @FXML
    private Label askAnswerTxt;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        readData();
        stageController("start");
        
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
        newAnimalTxt.setText(null);
        newQuestionTxt.setText(null);
        
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
    
    public void defaultData()
    {
        root = new Node("Is it a mammal?");
        
        root.setYes(new Node("Monkey"));
        root.setNo(new Node("Chicken"));
        
    }
    
    public void readData()
    {
        try
        {
            File savedata = new File("savedata.dat");
            if(!savedata.exists())
            {
              try
              {
                  defaultData();
                  FileOutputStream outputFile = new FileOutputStream(savedata);
                  ObjectOutputStream outputObj = new ObjectOutputStream(outputFile);
                  outputObj.writeObject(root);
                  outputObj.close();
                  outputFile.close();
                  Alert alert = new Alert(AlertType.INFORMATION);
                  alert.setTitle("New savedata");
                  alert.setHeaderText(null);
                  alert.setContentText("savadata doesn't exist! Creating new savedata!");

                  alert.showAndWait();
                  FileInputStream inputFile = new FileInputStream(savedata);
                  ObjectInputStream inputObj = new ObjectInputStream(inputFile);
                  root = (Node) inputObj.readObject();
                  inputObj.close();
                  inputFile.close();
                  animalTree.setRoot(root);
                  animalTree.setCurrent(animalTree.getRoot());
              }
              catch (IOException i)
              {
                  Alert alert = new Alert(AlertType.ERROR);
                  alert.setTitle("Creating New Data Filed!");
                  alert.setHeaderText(null);
                  alert.setContentText("Filed to create savedata.dat!");
                  alert.showAndWait();
              }
            }
            FileInputStream inputFile = new FileInputStream(savedata);
            ObjectInputStream inputObj = new ObjectInputStream(inputFile);
            root = (Node) inputObj.readObject();
            inputObj.close();
            inputFile.close();
            animalTree.setRoot(root);
            animalTree.setCurrent(animalTree.getRoot());
        } 
        catch (IOException i)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Reading New Data Filed!");
            alert.setHeaderText(null);
            alert.setContentText("Filed to read savedata.dat!");
            alert.showAndWait();
            return;
        }
        catch (ClassNotFoundException c)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Class Not Found!");
            alert.setHeaderText(null);
            alert.setContentText("Class not found!");
            alert.showAndWait();
            return;
        }
        
    }
    
    public void saveData()
    {
        try
        {
            FileOutputStream outputFile = new FileOutputStream("savedata.dat");
            ObjectOutputStream outputObj = new ObjectOutputStream(outputFile);
            outputObj.writeObject(root);
            outputObj.close();
            outputFile.close();
        }
        catch (IOException i)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Saving Data Filed!");
            alert.setHeaderText(null);
            alert.setContentText("Filed to save savedata.dat!");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnHandler(ActionEvent event) 
    {
       Button btn = (Button) event.getSource();
        switch(btn.getId())
        {
            case "btnPlay":
                stageController("guessing");
                questionTxt.setText(animalTree.getCurrent().getData() + "  <( - w - \\)");
                return;
            case "btnYes":
                // if reaching the end of the node, ask "is it <animal> ?"
                if (animalTree.getCurrent().getYes() != null)
                {
                    animalTree.setCurrent(animalTree.getCurrent().getYes());
                    if (animalTree.getCurrent().getYes()==null && animalTree.getCurrent().getNo()==null)
                    {
                        questionTxt.setText("Is it "+animalTree.getCurrent().getData()+" ?  <( 0 w 0 )/");
                    } 
                    else
                    {
                        questionTxt.setText(animalTree.getCurrent().getData() + "  \\( - w - )/");
                    }
                }
                else
                {
                    stageController("result");
                    thxTxt.setText("I won! \\( > w < )/ The Answe is "+ animalTree.getCurrent().getData() + "!");
                }
                return;
            case "btnNo":
                if (animalTree.getCurrent().getNo() != null)
                {
                    animalTree.setCurrent(animalTree.getCurrent().getNo());
                    if (animalTree.getCurrent().getYes()==null && animalTree.getCurrent().getNo()==null)
                    {
                        questionTxt.setText("Is it "+animalTree.getCurrent().getData()+" ? ( > A < \")");
                    } 
                    else
                    {
                        questionTxt.setText(animalTree.getCurrent().getData()+ "  ( O A O )");
                    }
                }
                else
                {
                    stageController("giveup");   
                }
                return;
            case "btnOk":
                
                if (!newAnimalTxt.getText().isEmpty())
                {
                    newAnimal = newAnimalTxt.getText();
                    stageController("add");
                    askNQTxt.setText("What is the difference between "+newAnimal + " and " + animalTree.getCurrent().getData()+ "?");
                }
                else
                {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Please");
                    alert.setHeaderText(null);
                    alert.setContentText("Please tell me what the animal is in your mind!");
                }
                return;
            case "nBtnYes":
                if(!newQuestionTxt.getText().isEmpty())
                {
                    String temp = animalTree.getCurrent().getData();
                    animalTree.getCurrent().setData(newQuestionTxt.getText());
                    animalTree.getCurrent().setYes(new Node(newAnimal));
                    animalTree.getCurrent().setNo(new Node(temp));
                    saveData();
                    stageController("result");
                    thxTxt.setText("Now I remember! You won't beat me next time! \\( > A < )/");
                }
                else
                {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Question?");
                    alert.setHeaderText(null);
                    alert.setContentText("Please tell me what the question is!");
                }
                return;
            case "nBtnNo":
                if(!newQuestionTxt.getText().isEmpty())
                {
                    String temp = animalTree.getCurrent().getData();
                    animalTree.getCurrent().setData(newQuestionTxt.getText());
                    animalTree.getCurrent().setYes(new Node(temp));
                    animalTree.getCurrent().setNo(new Node(newAnimal));
                    saveData();
                    stageController("result");
                    thxTxt.setText("Now I remember! You won't beat me next time! \\( > A < )/");
                }
                else
                {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Question?");
                    alert.setHeaderText(null);
                    alert.setContentText("Please tell me what the question is!");
                }
                return;
            case "btnReplay":
                resetStage();
                animalTree.setCurrent(root);
                stageController("guessing");
                questionTxt.setText(animalTree.getCurrent().getData());
                return;
            case "btnEnd":
                System.exit(0);
                return;
                
        }
    }
    
}
