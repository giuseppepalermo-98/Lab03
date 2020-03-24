package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {

	List<String> dizionario;
	
	public Dictionary() {
		dizionario= new LinkedList<String>();
	}
	
	
	public void loadDicotionary(String language) {
		FileReader fr;
		BufferedReader br;
		String nomeFile=language;
		
		try {
			fr = new FileReader(nomeFile);
			br = new BufferedReader(fr);
			String word;
			
			while( ( word =br.readLine()) !=null) {
				//String array[] =word.split("\n");
				
				//for(String s: array) {
				
				    word=word.toLowerCase();
					dizionario.add(word);
				//}
			}
			br.close();
			fr.close();
			
		}catch(IOException e){
			System.out.println("Errore nella lettura del file!");
		}
	}
	
	public List<String> acquisiscoTesto(String temp){
		List<String>inputTextList=new LinkedList<String>();
		
    	temp=temp.replaceAll("[.,\\/#?!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
    	temp=temp.toLowerCase();
        String array[]=temp.split(" ");
        System.out.println(temp);
    	for(String s:array) {
    		inputTextList.add(s);
    	}
    	
    	return inputTextList;
	}
	
	
	
	
	public List<RichWord> spellCheckText (List<String> inputTextList){
		List<RichWord> risult=new LinkedList<RichWord>();
		for(String s: inputTextList) {
			if(this.dizionario.contains(s)) {
				RichWord rw=new RichWord(true, s);
				risult.add(rw);
			}
			
			else {
				RichWord rw=new RichWord(false, s);
				risult.add(rw);
			}
		}
		return risult;
	}
	
}
