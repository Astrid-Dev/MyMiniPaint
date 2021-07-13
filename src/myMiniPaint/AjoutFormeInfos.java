package myMiniPaint;

import java.awt.Color;

public class AjoutFormeInfos {

	private int tailleContour = 0;
	
	private String typeForme = "";
	
	private Color couleurContour = null,
			couleurRempli = null;
	
	public AjoutFormeInfos()
	{
		
	}
	
	public AjoutFormeInfos(String t, int tai, Color c, Color c2) {
		this.typeForme = t;
		this.tailleContour = tai;
		this.couleurContour = c;
		this.couleurRempli = c2;
	}
	
	public int getTailleContour() {
		return this.tailleContour;
	}
	
	public String getTypeForme() {
		return this.typeForme;
	}
	
	public Color getCouleurContour() {
		return this.couleurContour;
	}
	
	public Color getCouleurRempli() {
		return this.couleurRempli;
	}
	
}
