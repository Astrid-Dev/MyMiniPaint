package myMiniPaint;

import java.awt.Color;

public class FondCouleur {

	private Color couleurAvant;
	private Color couleurApres;
	private int indice;
	
	public FondCouleur(Color c1, Color c2, int in) {
		this.couleurAvant = c1;
		this.couleurApres = c2;
		this.indice = in;
	}
	
	public int getIndice() {
		return this.indice;
	}
	
	public Color getCouleurAvant() {
		return this.couleurAvant;
	}
	
	public Color getCouleurApres() {
		return this.couleurApres;
	}
	
	public void seIndice(int in) {
		this.indice = in;
	}
	
	public void setFondAvant(Color c) {
		this.couleurAvant = c;
	}
	
	public void setFondApres(Color c) {
		this.couleurApres = c;
	}
}
