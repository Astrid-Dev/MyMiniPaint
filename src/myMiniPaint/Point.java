package myMiniPaint;

import java.awt.Color;
import java.io.Serializable;

public class Point implements Serializable{

	private int x, x2;
	private int y, y2;
	private int size;
	private String type = "";
	private Color couleur = null;
	private boolean estGomme = false;
	private int tailleContour;
	private Color couleurContour, couleurRemplissage;
	
	public Point(int x, int y, int s, String t, Color c)
	{
		this.x = x;
		this.y = y;
		this.size = s;
		this.type = t;
		this.couleur = c;
		this.estGomme = false;
	}
	
	public Point(int x, int y, int s, String t, Color c, boolean b)
	{
		this.x = x;
		this.y = y;
		this.size = s;
		this.type = t;
		this.couleur = c;
		this.estGomme = true;
	}
	
	public Point(int x, int y, int x2, int y2, String t, Color c)
	{
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
		this.type = t;
		this.couleur = c;
		this.estGomme = false;
	}
	
	public Point(int x, int y, int x2, int y2, int cont, String t, Color c, Color c2)
	{
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
		this.type = t;
		this.couleurContour = c;
		this.couleurRemplissage = c2;
		this.tailleContour = cont;
	}
	
	public Point(int x, int y, int x2, int y2, int cont, String t, Color c, Color c2, boolean b)
	{
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
		this.type = t;
		this.couleurContour = c;
		this.couleurRemplissage = c2;
		this.tailleContour = cont;
		this.estGomme = b;
	}
	
	public boolean getEstGomme() {
		return estGomme;
	}
	
	public int getX() {
		return x;
	}
	
	public int getX2() {
		return x2;
	}
	
	public int getY() {
		return y;
	}
	
	public int getY2() {
		return y2;
	}
	
	public int getSize() {
		return size;
	}
	
	public String getType() {
		return type;
	}
	
	public Color getColor() {
		return couleur;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setX2(int x2) {
		this.x2 = x2;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setY2(int y2) {
		this.y2 = y2;
	}
	
	public void setSize(int s) {
		this.size = s;
	}
	
	public void setType(String t) {
		this.type = t;
	}
	
	public void setColor(Color c) {
		this.couleur = c;
	}
	
	public Color getCouleurContour() {
		return couleurContour;
	}
	
	public void setCouleurContour(Color c) {
		this.couleurContour = c;
	}
	
	public Color getCouleurRempli() {
		return couleurRemplissage;
	}
	
	public void setCouleurRempli(Color c) {
		this.couleurRemplissage = c;
	}
	
	public int getTailleContour() {
		return tailleContour;
	}
	
	public void setTailleContour(int t) {
		this.tailleContour = t;
	}
}
