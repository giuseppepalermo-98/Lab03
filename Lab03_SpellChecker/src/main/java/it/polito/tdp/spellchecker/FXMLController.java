/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;


import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Dictionary myDizionario;
	private ObservableList <String> list= FXCollections.observableArrayList("English", "Italian");
			
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextArea txtTesto;

    @FXML
    private Button btnSpellChech;

    @FXML
    private TextArea txtResut;

    @FXML
    private Label txtErrori;

    @FXML
    private Button btnClear;

    @FXML
    private Label txtTime;

    @FXML
    void doClearText(ActionEvent event) {

    	this.txtResut.clear();
    	this.txtTesto.clear();
    	//this.txtErrori.setAccessibleRoleDescription("");
    	//this.txtTime.setAccessibleRoleDescription("");
    	this.txtErrori.setText("");
    	this.txtTime.setText("");
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	double start = System.nanoTime();
    	this.txtResut.clear();
    	String lingua=choiceBox.getValue();
    	
    	if(lingua.equals("English"))
    		this.myDizionario.loadDicotionary("src/main/resources/English.txt");
    	else
    		this.myDizionario.loadDicotionary("src/main/resources/Italian.txt");
    	
    	List<RichWord>listaErrori=new LinkedList<RichWord>();
    	List<String>inputTextList=new LinkedList<String>();
    	String temp;
    	int errori=0;
    	
    	temp=this.txtTesto.getText();
    	inputTextList.addAll(this.myDizionario.acquisiscoTesto(temp));
    	
    	listaErrori.addAll(this.myDizionario.spellCheckText(inputTextList));
    	
    	
    	for(RichWord rw: listaErrori) {
    		if(!rw.isCorretta()) {
    			errori++;
    			this.txtResut.appendText(rw.getParola()+"\n");
    		}
    	}
    	double end = System.nanoTime();
    	this.txtErrori.setText("The text contains "+errori+" errors");
    	this.txtTime.setText("Spell Check completed in "+(end-start)+" seconds");
    }

    @FXML
    void initialize() {
    	assert choiceBox != null : "fx:id=\"choiceBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTesto != null : "fx:id=\"txtTesto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpellChech != null : "fx:id=\"btnSpellChech\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResut != null : "fx:id=\"txtResut\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrori != null : "fx:id=\"txtErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTime != null : "fx:id=\"txtTime\" was not injected: check your FXML file 'Scene.fxml'.";

        choiceBox.setItems(list);
        choiceBox.setValue("Italian");
        
    }

	public void setModel(Dictionary model) {
		this.myDizionario=model;
	}
}


