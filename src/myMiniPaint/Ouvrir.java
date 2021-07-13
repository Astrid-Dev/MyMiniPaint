package myMiniPaint;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Ouvrir {

	private ArrayList<Point> points = new ArrayList<Point>();
	private String nom;
	private Color c;
	
	public Ouvrir(String nom)
	{
		this.nom = nom;
		
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(
					new DataInputStream(
							new FileInputStream(
									new File(this.nom))));
			
			long nbre = ois.readLong();
			
			for(long i = 0; i < nbre; i++)
			{
				try {
					points.add((Point)ois.readObject());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				c = (Color)ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public ArrayList<Point> getPoints() {
		return points;
	}
	
	public Color getColor() {
		return c;
	}
}
