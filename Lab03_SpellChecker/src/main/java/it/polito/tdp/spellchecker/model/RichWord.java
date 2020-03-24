package it.polito.tdp.spellchecker.model;


public class RichWord {
	
	String parola;
	boolean corretta;
	
	public RichWord() {
		parola="";
		corretta=false;
	}

	public RichWord(boolean b, String s) {
		this.parola=s;
		this.corretta=b;
	}

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public boolean isCorretta() {
		return corretta;
	}

	public void setCorretta(boolean corretta) {
		this.corretta = corretta;
	}
	
	
}
