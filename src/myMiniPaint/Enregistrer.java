package myMiniPaint;

import java.awt.Color;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Enregistrer {

	private ArrayList<Point> points = new ArrayList<Point>();
	long nbre;
	String nom;
	Color background;
	
	public Enregistrer(String nom, ArrayList<Point> p, Color c)
	{
		this.nom = nom + ".paint";
		points = p;
		this.nbre = p.size();
		this.background = c;
		save();
	}
	
	private void save()
	{
		ObjectOutputStream oos;
		
		try {
			File file = new File(nom);
			if(file.exists()) {
				file.delete();
				file = new File(nom);
			}
			oos = new ObjectOutputStream(
					new DataOutputStream(
							new FileOutputStream(
									file)));
			oos.writeLong(nbre);
			for(Point p : points)
			{
				oos.writeObject(p);
			}
			
			oos.writeObject(background);
			oos.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
