package myMiniPaint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class DrawPanel extends JPanel{

	
	private ArrayList<Point> points = new ArrayList<Point>(), points2= new ArrayList<Point>();
	private ArrayList<Object> doublurePoints = new ArrayList<Object>();
	
	private boolean peutEffacer = false, retourEffacer = false;
	private boolean peutGommer = false;
	
	private int pointerSize = 10;
	private Color pointerColor = Color.black;
	private String pointerType = "ROND";
	private Color backgroundColor = Color.WHITE, previousBackgroundColor = Color.WHITE;
	
	private final int WIPE_SIZE = 10;
	private int nbrePoints = 0;
	private ArrayList<Integer> nbreSauts = new ArrayList<Integer>();
	private ArrayList<Integer> nbreSauts2 = new ArrayList<Integer>();
	
	private boolean peutTracer = true, peutTracer2 = true, peutTracer3 = true;
	
	private boolean aCocheContour = false;
	private boolean aCocheRemplissage = false;
	
	private int tailleContour = 1;
	
	private Color couleurContour = Color.black,
			couleurRempli = Color.BLACK;
	
	private ArrayList<FondCouleur> fonds = new ArrayList<FondCouleur>();
	
	Point p = new Point(1,  1,  1,  "",  Color.BLACK);	
	
	
	public DrawPanel()
	{
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(nbrePoints >= 1) {
					nbreSauts.add(nbrePoints);
					if(backgroundColor != previousBackgroundColor) {
						fonds.add(new FondCouleur(previousBackgroundColor, backgroundColor,  points.size()-nbrePoints));
						previousBackgroundColor = backgroundColor;
					}
					
					nbrePoints = 0;
				}
				
				peutTracer = false;
				peutTracer3 = false;
				if(arg0.getButton() == MouseEvent.BUTTON1) {
					peutTracer2 = false;
				}
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Fenetre.setEnregistrement(false);
				if(arg0.getButton() == MouseEvent.BUTTON1)
				{
					if(!peutGommer) {
						if(pointerType.equals("Traits simples") || pointerType.equals("Traits continus") || pointerType.equals("Rectangle") || pointerType.equals("TriangleRect") || pointerType.equals("Losange") || pointerType.equals("Triangle") || pointerType.equals("Cercle") || pointerType.equals("RectangleRond")) {
							
						}
						else {
							points.add(new Point(arg0.getX()-pointerSize/2, arg0.getY()-pointerSize/2,  pointerSize,  pointerType,  pointerColor));
						}
					}
					else
					{
						points.add(new Point(arg0.getX(), arg0.getY(),  WIPE_SIZE, "CARRE",  backgroundColor, true));
					}
				}
				else if(arg0.getButton() == MouseEvent.BUTTON3)
				{
					if(!pointerType.equals("Traits simples") && !pointerType.equals("Traits continus") && !pointerType.equals("Rectangle")) {
						points.add(new Point(arg0.getX()-pointerSize/2, arg0.getY()-pointerSize/2,  pointerSize,  pointerType,  backgroundColor, true));
					}
					else if(pointerType.equals("Rectangle"))
					{
						points.add(new Point(arg0.getX()-pointerSize/2, arg0.getY()-pointerSize/2,arg0.getX()+pointerSize/2, arg0.getY()+pointerSize/2, 0, pointerType, null, backgroundColor));
					}
					else {
						points.add(new Point(arg0.getX()-pointerSize, arg0.getY()-pointerSize, arg0.getX()+pointerSize, arg0.getY()+pointerSize, pointerType, backgroundColor));
					}
				}
				if(doublurePoints.size() > 0) {
					doublurePoints.remove(doublurePoints.size() - 1);
					Fenetre.setAvancer(false);
				}
				if(doublurePoints.size() == 0)
				{
					Fenetre.setAvancer(false);
				}
				
				nbreSauts.add(1);
				if(backgroundColor != previousBackgroundColor) {
					fonds.add(new FondCouleur(previousBackgroundColor, backgroundColor,  points.size()-1));
					previousBackgroundColor = backgroundColor;
				}
				Fenetre.setRetour(true);
				repaint();
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Fenetre.setEnregistrement(false);
				if(SwingUtilities.isLeftMouseButton(arg0))
				{
					if(!peutGommer) {
						if(pointerType.equals("Traits simples")) {
							if(points.size() > 0 && points.get(points.size()-1).getType().equals("Traits simples") && peutTracer) {
								points.get(points.size()-1).setX2(arg0.getX());
								points.get(points.size()-1).setY2(arg0.getY());
								repaint();
							}
							else {
								points.add(new Point(arg0.getX(), arg0.getY(), arg0.getX()+2, arg0.getY()+2, pointerType, pointerColor));
								peutTracer = true;
								nbreSauts.add(1);
							}
							
						}
						else if(pointerType.equals("Traits continus")) {
							if(points.size() > 0 && points.get(points.size()-1).getType().equals("Traits continus") && !points.get(points.size()-1).getEstGomme()) {
								if(peutTracer2) {
									points.get(points.size()-1).setX2(arg0.getX());
									points.get(points.size()-1).setY2(arg0.getY());
									repaint();
								}
								else {
									points.add(new Point(points.get(points.size()-1).getX2(), points.get(points.size()-1).getY2(), arg0.getX(), arg0.getY(), pointerType, pointerColor));
									peutTracer2 = true;
									nbreSauts.add(1);
								}
							}
							else {
								points.add(new Point(arg0.getX(), arg0.getY(), arg0.getX()+2, arg0.getY()+2, pointerType, pointerColor));
								peutTracer2 = true;
								nbreSauts.add(1);
							}
						}
						else if(pointerType.equals("Rectangle") || pointerType.equals("TriangleRect") || pointerType.equals("Losange") || pointerType.equals("Triangle") || pointerType.equals("Cercle") || pointerType.equals("RectangleRond"))
						{
							if(points.size() > 0 && points.get(points.size()-1).getType().equals(pointerType) && !points.get(points.size()-1).getEstGomme()) {
								if(peutTracer3) {
									points.get(points.size()-1).setX2(arg0.getX());
									points.get(points.size()-1).setY2(arg0.getY());
									repaint();
								}
								else {
									points.add(new Point(arg0.getX(), arg0.getY(), arg0.getX()+2, arg0.getY()+2, tailleContour, pointerType, couleurContour, couleurRempli));
									peutTracer3 = true;
									nbreSauts.add(1);
								}
							}
							else {
								points.add(new Point(arg0.getX(), arg0.getY(), arg0.getX()+2, arg0.getY()+2, tailleContour, pointerType, couleurContour, couleurRempli));
								peutTracer3 = true;
								nbreSauts.add(1);
							}
						}
						else {
							points.add(new Point(arg0.getX()-pointerSize/2, arg0.getY()-pointerSize/2,  pointerSize,  pointerType,  pointerColor));
						}
					}
					else
					{
						points.add(new Point(arg0.getX(), arg0.getY(),  WIPE_SIZE, "CARRE",  backgroundColor, true));
					}
				}
				else if(SwingUtilities.isRightMouseButton(arg0))
				{
					if(!pointerType.equals("Traits simples") && !pointerType.equals("Traits continus") && !pointerType.equals("Rectangle") && !pointerType.equals("TriangleRect") && !pointerType.equals("Losange") && !pointerType.equals("Triangle") && !pointerType.equals("Cercle") && !pointerType.equals("RectangleRond")) {
						points.add(new Point(arg0.getX()-pointerSize/2, arg0.getY()-pointerSize/2,  pointerSize,  pointerType,  backgroundColor, true));
					}
					else if(pointerType.equals("Rectangle") || pointerType.equals("TriangleRect") || pointerType.equals("Losange") || pointerType.equals("Triangle") || pointerType.equals("Cercle") || pointerType.equals("RectangleRond"))
					{
						if(points.size() > 0 && points.get(points.size()-1).getType().equals(pointerType)) {
							if(peutTracer3) {
								points.get(points.size()-1).setX2(arg0.getX());
								points.get(points.size()-1).setY2(arg0.getY());
								repaint();
							}
							else {
								points.add(new Point(arg0.getX(), arg0.getY(), arg0.getX()+2, arg0.getY()+2, 0, pointerType, null, backgroundColor, true));
								peutTracer3 = true;
								nbreSauts.add(1);
							}
						}
						else {
							points.add(new Point(arg0.getX(), arg0.getY(), arg0.getX()+2, arg0.getY()+2, 0, pointerType, null, backgroundColor, true));
							peutTracer3 = true;
							nbreSauts.add(1);
						}
					}
					else {
						points.add(new Point(arg0.getX()-pointerSize, arg0.getY()-pointerSize, arg0.getX()+pointerSize, arg0.getY()+pointerSize, pointerType, backgroundColor));
					}
					
				}
				if(doublurePoints.size() > 0) {
					doublurePoints.remove(doublurePoints.size() - 1);
					Fenetre.setAvancer(false);
				}
				if(doublurePoints.size() == 0)
				{
					Fenetre.setAvancer(false);
				}
				if(!pointerType.equals("Traits simples") && !pointerType.equals("Traits continus") && !pointerType.equals("Rectangle") && !pointerType.equals("TriangleRect") && !pointerType.equals("Losange") && !pointerType.equals("Triangle") && !pointerType.equals("Cercle") && !pointerType.equals("RectangleRond"))
					++nbrePoints;
				else {
					if(points.size() == 0) {
						nbreSauts.add(1);
					}
					else if(points.size() > 0) {
						nbreSauts.remove(nbreSauts.size() - 1);
						nbreSauts.add(1);
					}
				}
				
				Fenetre.setRetour(true);
				repaint();
			}
		});
	}
	
	public void paintComponent(Graphics g)
	{
		g.setColor(backgroundColor);
		g.fillRect(0,  0,  this.getWidth(), this.getHeight());
		
		if(peutEffacer)
		{
			peutEffacer = false;
		}
		else
		{
			for(Point p : points)
			{
				if(p.getType().equals("CARRE"))
				{
					g.setColor(p.getColor());
					g.fillRect(p.getX(), p.getY(), p.getSize(),  p.getSize());
				}
				else if(p.getType().equals("ROND"))
				{
					g.setColor(p.getColor());
					g.fillOval(p.getX(), p.getY(), p.getSize(),  p.getSize());
				}
				else if(p.getType().equals("Traits simples") || p.getType().equals("Traits continus"))
				{
					g.setColor(p.getColor());
					int[] tabX = {p.getX(), p.getX(), p.getX2(), p.getX2(), p.getX(), p.getX()};
					int[] tabY = {p.getY()-pointerSize/2, p.getY()+pointerSize/2, p.getY2()+pointerSize/2, p.getY2()-pointerSize/2, p.getY()-pointerSize/2, p.getY()-pointerSize/2};
					if(p.getX() == p.getX2()) {
						g.fillRect(p.getX()-pointerSize/2, p.getY(), pointerSize, p.getY2()-p.getY());
					}
					else
						g.fillPolygon(tabX, tabY, 5);
				}
				else if(p.getType().equals("Rectangle"))
				{
					if(p.getTailleContour() != 0 && p.getCouleurRempli() != null)
					{
						int[] tabX = new int[4], tabY = new int[4], tabX2 = new int[4], tabY2 = new int[4];
						if(p.getX() <= p.getX2())
						{
							tabX[0] = p.getX();
							tabX[1] = p.getX2();
							tabX[2] = p.getX2();
							tabX[3] = p.getX();
							
							tabX2[0] = p.getX()+p.getTailleContour();
							tabX2[1] = p.getX2()-p.getTailleContour();
							tabX2[2] = p.getX2()-p.getTailleContour();
							tabX2[3] = p.getX()+p.getTailleContour();
						}
						else
						{
							tabX[0] = p.getX2();
							tabX[1] = p.getX();
							tabX[2] = p.getX();
							tabX[3] = p.getX2();
							
							tabX2[0] = p.getX2()+p.getTailleContour();
							tabX2[1] = p.getX()-p.getTailleContour();
							tabX2[2] = p.getX()-p.getTailleContour();
							tabX2[3] = p.getX2()+p.getTailleContour();
						}
						
						if(p.getY() <= p.getY2())
						{
							tabY[0] = p.getY();
							tabY[1] = p.getY();
							tabY[2] = p.getY2();
							tabY[3] = p.getY2();
							
							tabY2[0] = p.getY()+p.getTailleContour();
							tabY2[1] = p.getY()+p.getTailleContour();
							tabY2[2] = p.getY2()-p.getTailleContour();
							tabY2[3] = p.getY2()-p.getTailleContour();
						}
						else
						{
							tabY[0] = p.getY2();
							tabY[1] = p.getY2();
							tabY[2] = p.getY();
							tabY[3] = p.getY();
							
							tabY2[0] = p.getY2()+p.getTailleContour();
							tabY2[1] = p.getY2()+p.getTailleContour();
							tabY2[2] = p.getY()-p.getTailleContour();
							tabY2[3] = p.getY()-p.getTailleContour();
						}
						
						g.setColor(p.getCouleurContour());
						g.fillPolygon(tabX,  tabY,  4);
						
						g.setColor(p.getCouleurRempli());
						g.fillPolygon(tabX2,  tabY2,  4);
						

					}
					else {
						if(p.getCouleurRempli() != null) {
							g.setColor(p.getCouleurRempli());
							int[] tabX = {p.getX(), p.getX2(), p.getX2(), p.getX()};
							int[] tabY = {p.getY(), p.getY(), p.getY2(), p.getY2()};
							g.fillPolygon(tabX,  tabY,  4);
						}
						
						if(p.getTailleContour() != 0) {
							int[] tabX = new int[4], tabY = new int[4], tabX2 = new int[4], tabY2 = new int[4];
							if(p.getX() <= p.getX2())
							{
								tabX[0] = p.getX();
								tabX[1] = p.getX2();
								tabX[2] = p.getX2();
								tabX[3] = p.getX();
								
								tabX2[0] = p.getX()+p.getTailleContour();
								tabX2[1] = p.getX2()-p.getTailleContour();
								tabX2[2] = p.getX2()-p.getTailleContour();
								tabX2[3] = p.getX()+p.getTailleContour();
							}
							else
							{
								tabX[0] = p.getX2();
								tabX[1] = p.getX();
								tabX[2] = p.getX();
								tabX[3] = p.getX2();
								
								tabX2[0] = p.getX2()+p.getTailleContour();
								tabX2[1] = p.getX()-p.getTailleContour();
								tabX2[2] = p.getX()-p.getTailleContour();
								tabX2[3] = p.getX2()+p.getTailleContour();
							}
							
							if(p.getY() <= p.getY2())
							{
								tabY[0] = p.getY();
								tabY[1] = p.getY();
								tabY[2] = p.getY2();
								tabY[3] = p.getY2();
								
								tabY2[0] = p.getY()+p.getTailleContour();
								tabY2[1] = p.getY()+p.getTailleContour();
								tabY2[2] = p.getY2()-p.getTailleContour();
								tabY2[3] = p.getY2()-p.getTailleContour();
							}
							else
							{
								tabY[0] = p.getY2();
								tabY[1] = p.getY2();
								tabY[2] = p.getY();
								tabY[3] = p.getY();
								
								tabY2[0] = p.getY2()+p.getTailleContour();
								tabY2[1] = p.getY2()+p.getTailleContour();
								tabY2[2] = p.getY()-p.getTailleContour();
								tabY2[3] = p.getY()-p.getTailleContour();
							}
							
							g.setColor(p.getCouleurContour());
							g.fillPolygon(tabX,  tabY,  4);
							
							g.setColor(backgroundColor);
							g.fillPolygon(tabX2,  tabY2,  4);
						}
					}
				}
				else if(p.getType().equals("TriangleRect"))
				{
					if(p.getTailleContour() != 0 && p.getCouleurRempli() != null)
					{
						int[] tabX = new int[3], tabY = new int[3], tabX2 = new int[3], tabY2 = new int[3];
						if(p.getX() <= p.getX2())
						{
							tabX[0] = p.getX();
							tabX[1] = p.getX2();
							tabX[2] = p.getX();
							
							tabX2[0] = p.getX()+p.getTailleContour();
							tabX2[1] = p.getX2()-(p.getTailleContour()*2);
							tabX2[2] = p.getX()+p.getTailleContour();
						}
						else
						{
							tabX[0] = p.getX();
							tabX[1] = p.getX();
							tabX[2] = p.getX2();
							
							tabX2[0] = p.getX()-p.getTailleContour();
							tabX2[1] = p.getX()-(p.getTailleContour()*2);
							tabX2[2] = p.getX2()+p.getTailleContour();
						}
						
						if(p.getY() <= p.getY2())
						{
							tabY[0] = p.getY();
							tabY[1] = p.getY2();
							tabY[2] = p.getY2();
							
							tabY2[0] = p.getY()+(p.getTailleContour()*2);
							tabY2[1] = p.getY2()-p.getTailleContour();
							tabY2[2] = p.getY2()-p.getTailleContour();
						}
						else
						{
							tabY[0] = p.getY2();
							tabY[1] = p.getY();
							tabY[2] = p.getY();
							
							tabY2[0] = p.getY2()+(p.getTailleContour()*2);
							tabY2[1] = p.getY()-p.getTailleContour();
							tabY2[2] = p.getY()-p.getTailleContour();
						}
						
						g.setColor(p.getCouleurContour());
						g.fillPolygon(tabX,  tabY,  3);
						
						g.setColor(p.getCouleurRempli());
						g.fillPolygon(tabX2,  tabY2,  3);
						

					}
					else {
						if(p.getCouleurRempli() != null) {
							g.setColor(p.getCouleurRempli());
							int[] tabX = {p.getX(), p.getX2(), p.getX()};
							int[] tabY = {p.getY(), p.getY2(), p.getY2()};
							g.fillPolygon(tabX,  tabY,  3);
						}
						
						if(p.getTailleContour() != 0) {
							int[] tabX = new int[3], tabY = new int[3], tabX2 = new int[3], tabY2 = new int[3];
							if(p.getX() <= p.getX2())
							{
								tabX[0] = p.getX();
								tabX[1] = p.getX2();
								tabX[2] = p.getX();
								
								tabX2[0] = p.getX()+p.getTailleContour();
								tabX2[1] = p.getX2()-p.getTailleContour();
								tabX2[2] = p.getX()+p.getTailleContour();
							}
							else
							{
								tabX[0] = p.getX();
								tabX[1] = p.getX();
								tabX[2] = p.getX2();
								
								tabX2[0] = p.getX()-p.getTailleContour();
								tabX2[1] = p.getX()-p.getTailleContour();
								tabX2[2] = p.getX2()+p.getTailleContour();
							}
							
							if(p.getY() <= p.getY2())
							{
								tabY[0] = p.getY();
								tabY[1] = p.getY2();
								tabY[2] = p.getY2();
								
								tabY2[0] = p.getY()+p.getTailleContour();
								tabY2[1] = p.getY2()-p.getTailleContour();
								tabY2[2] = p.getY2()-p.getTailleContour();
							}
							else
							{
								tabY[0] = p.getY2();
								tabY[1] = p.getY();
								tabY[2] = p.getY();
								
								tabY2[0] = p.getY2()+p.getTailleContour();
								tabY2[1] = p.getY()-p.getTailleContour();
								tabY2[2] = p.getY()-p.getTailleContour();
							}
							
							g.setColor(p.getCouleurContour());
							g.fillPolygon(tabX,  tabY,  3);
							
							g.setColor(backgroundColor);
							g.fillPolygon(tabX2,  tabY2,  3);
							
						}
					}
				}
				else if(p.getType().equals("Losange"))
				{
					if(p.getTailleContour() != 0 && p.getCouleurRempli() != null)
					{
						int[] tabX = new int[4], tabY = new int[4], tabX2 = new int[4], tabY2 = new int[4];
						if(p.getX() <= p.getX2())
						{
							tabX[0] = (p.getX()+p.getX2())/2;
							tabX[1] = p.getX2();
							tabX[2] = (p.getX()+p.getX2())/2;
							tabX[3] = p.getX();
							
							tabX2[0] = (p.getX()+p.getX2())/2;
							tabX2[1] = p.getX2()-p.getTailleContour();
							tabX2[2] = (p.getX()+p.getX2())/2;
							tabX2[3] = p.getX()+p.getTailleContour();
						}
						else
						{
							tabX[0] = (p.getX()+p.getX2())/2;
							tabX[1] = p.getX();
							tabX[2] = (p.getX()+p.getX2())/2;
							tabX[3] = p.getX2();
							
							tabX2[0] = (p.getX()+p.getX2())/2;
							tabX2[1] = p.getX()-p.getTailleContour();
							tabX2[2] = (p.getX()+p.getX2())/2;
							tabX2[3] = p.getX2()+p.getTailleContour();
						}
						
						if(p.getY() <= p.getY2())
						{
							tabY[0] = p.getY();
							tabY[1] = (p.getY()+p.getY2())/2;
							tabY[2] = p.getY2();
							tabY[3] = (p.getY2()+p.getY())/2;
							
							tabY2[0] = p.getY()+p.getTailleContour();
							tabY2[1] = (p.getY()+p.getY2())/2;
							tabY2[2] = p.getY2()-p.getTailleContour();
							tabY2[3] = (p.getY()+p.getY2())/2;
						}
						else
						{
							tabY[0] = p.getY2();
							tabY[1] = (p.getY()+p.getY2())/2;
							tabY[2] = p.getY();
							tabY[3] = (p.getY()+p.getY2())/2;
							
							tabY2[0] = p.getY2()+p.getTailleContour();
							tabY2[1] = (p.getY()+p.getY2())/2;
							tabY2[2] = p.getY()-p.getTailleContour();
							tabY2[3] = (p.getY()+p.getY2())/2;
						}
						
						g.setColor(p.getCouleurContour());
						g.fillPolygon(tabX,  tabY,  4);
						
						g.setColor(p.getCouleurRempli());
						g.fillPolygon(tabX2,  tabY2,  4);
						

					}
					else {
						if(p.getCouleurRempli() != null) {
							g.setColor(p.getCouleurRempli());
							int[] tabX = {(p.getX()+p.getX2())/2, p.getX2(), (p.getX2()+p.getX())/2, p.getX()};
							int[] tabY = {p.getY(), (p.getY()+p.getY2())/2, p.getY2(), (p.getY2()+p.getY())/2};
							g.fillPolygon(tabX,  tabY,  4);
						}
						
						if(p.getTailleContour() != 0) {
							int[] tabX = new int[4], tabY = new int[4], tabX2 = new int[4], tabY2 = new int[4];
							if(p.getX() <= p.getX2())
							{
								tabX[0] = (p.getX()+p.getX2())/2;
								tabX[1] = p.getX2();
								tabX[2] = (p.getX()+p.getX2())/2;
								tabX[3] = p.getX();
								
								tabX2[0] = (p.getX()+p.getX2())/2;
								tabX2[1] = p.getX2()-p.getTailleContour();
								tabX2[2] = (p.getX()+p.getX2())/2;
								tabX2[3] = p.getX()+p.getTailleContour();
							}
							else
							{
								tabX[0] = (p.getX()+p.getX2())/2;
								tabX[1] = p.getX();
								tabX[2] = (p.getX()+p.getX2())/2;
								tabX[3] = p.getX2();
								
								tabX2[0] = (p.getX()+p.getX2())/2;
								tabX2[1] = p.getX()-p.getTailleContour();
								tabX2[2] = (p.getX()+p.getX2())/2;
								tabX2[3] = p.getX2()+p.getTailleContour();
							}
							
							if(p.getY() <= p.getY2())
							{
								tabY[0] = p.getY();
								tabY[1] = (p.getY()+p.getY2())/2;
								tabY[2] = p.getY2();
								tabY[3] = (p.getY2()+p.getY())/2;
								
								tabY2[0] = p.getY()+p.getTailleContour();
								tabY2[1] = (p.getY()+p.getY2())/2;
								tabY2[2] = p.getY2()-p.getTailleContour();
								tabY2[3] = (p.getY()+p.getY2())/2;
							}
							else
							{
								tabY[0] = p.getY2();
								tabY[1] = (p.getY()+p.getY2())/2;
								tabY[2] = p.getY();
								tabY[3] = (p.getY()+p.getY2())/2;
								
								tabY2[0] = p.getY2()+p.getTailleContour();
								tabY2[1] = (p.getY()+p.getY2())/2;
								tabY2[2] = p.getY()-p.getTailleContour();
								tabY2[3] = (p.getY()+p.getY2())/2;
							}
							
							g.setColor(p.getCouleurContour());
							g.fillPolygon(tabX,  tabY,  4);
							
							g.setColor(backgroundColor);
							g.fillPolygon(tabX2,  tabY2,  4);
						}
					}
				}
				else if(p.getType().equals("Triangle"))
				{
					if(p.getTailleContour() != 0 && p.getCouleurRempli() != null)
					{
						int[] tabX = new int[3], tabY = new int[3], tabX2 = new int[3], tabY2 = new int[3];
						if(p.getX() <= p.getX2())
						{
							tabX[0] = (p.getX()+p.getX2())/2;
							tabX[1] = p.getX2();
							tabX[2] = p.getX();
							
							tabX2[0] = (p.getX()+p.getX2())/2;
							tabX2[1] = p.getX2()-p.getTailleContour();
							tabX2[2] = p.getX()+p.getTailleContour();
						}
						else
						{
							tabX[0] = (p.getX()+p.getX2())/2;
							tabX[1] = p.getX();
							tabX[2] = p.getX2();
							
							tabX2[0] = (p.getX()+p.getX2())/2;
							tabX2[1] = p.getX()-p.getTailleContour();
							tabX2[2] = p.getX2()+p.getTailleContour();
						}
						
						if(p.getY() <= p.getY2())
						{
							tabY[0] = p.getY();
							tabY[1] = p.getY2();
							tabY[2] = p.getY2();
							
							tabY2[0] = p.getY()+p.getTailleContour();
							tabY2[1] = p.getY2()-p.getTailleContour();
							tabY2[2] = p.getY2()-p.getTailleContour();
						}
						else
						{
							tabY[0] = p.getY2();
							tabY[1] = p.getY();
							tabY[2] = p.getY();
							
							tabY2[0] = p.getY2()+p.getTailleContour();
							tabY2[1] = p.getY()-p.getTailleContour();
							tabY2[2] = p.getY()-p.getTailleContour();
						}
						
						g.setColor(p.getCouleurContour());
						g.fillPolygon(tabX,  tabY,  3);
						
						g.setColor(p.getCouleurRempli());
						g.fillPolygon(tabX2,  tabY2,  3);
						

					}
					else {
						if(p.getCouleurRempli() != null) {
							g.setColor(p.getCouleurRempli());
							int[] tabX = {(p.getX()+p.getX2())/2, p.getX2(), p.getX()};
							int[] tabY = {p.getY(), p.getY2(), p.getY2()};
							g.fillPolygon(tabX,  tabY,  3);
						}
						
						if(p.getTailleContour() != 0) {
							int[] tabX = new int[3], tabY = new int[3], tabX2 = new int[3], tabY2 = new int[3];
							if(p.getX() <= p.getX2())
							{
								tabX[0] = (p.getX()+p.getX2())/2;
								tabX[1] = p.getX2();
								tabX[2] = p.getX();
								
								tabX2[0] = (p.getX()+p.getX2())/2;
								tabX2[1] = p.getX2()-p.getTailleContour();
								tabX2[2] = p.getX()+p.getTailleContour();
							}
							else
							{
								tabX[0] = (p.getX()+p.getX2())/2;
								tabX[1] = p.getX();
								tabX[2] = p.getX2();
								
								tabX2[0] = (p.getX()+p.getX2())/2;
								tabX2[1] = p.getX()-p.getTailleContour();
								tabX2[2] = p.getX2()+p.getTailleContour();
							}
							
							if(p.getY() <= p.getY2())
							{
								tabY[0] = p.getY();
								tabY[1] = p.getY2();
								tabY[2] = p.getY2();
								
								tabY2[0] = p.getY()+p.getTailleContour();
								tabY2[1] = p.getY2()-p.getTailleContour();
								tabY2[2] = p.getY2()-p.getTailleContour();
							}
							else
							{
								tabY[0] = p.getY2();
								tabY[1] = p.getY();
								tabY[2] = p.getY();
								
								tabY2[0] = p.getY2()+p.getTailleContour();
								tabY2[1] = p.getY()-p.getTailleContour();
								tabY2[2] = p.getY()-p.getTailleContour();
							}
							
							g.setColor(p.getCouleurContour());
							g.fillPolygon(tabX,  tabY,  3);
							
							g.setColor(backgroundColor);
							g.fillPolygon(tabX2,  tabY2,  3);
							
						}
					}
				}
				else if(p.getType().equals("Cercle"))
				{
					if(p.getTailleContour() != 0 && p.getCouleurRempli() != null)
					{
						int[] tabX = new int[2], tabY = new int[2], tabX2 = new int[2], tabY2 = new int[2];
						if(p.getX() <= p.getX2())
						{
							tabX[0] = p.getX();
							tabX[1] = p.getX2()+p.getTailleContour();
							
							tabX2[0] = p.getX()+p.getTailleContour();
							tabX2[1] = p.getX2()-p.getTailleContour();
						}
						else
						{
							tabX[1] = p.getX()+p.getTailleContour();
							tabX[0] = p.getX2()-p.getTailleContour();
							
							tabX2[1] = p.getX()-p.getTailleContour();
							tabX2[0] = p.getX2();
						}
						
						tabY[0] = (p.getY()+p.getY2())/2;
						tabY[1] = (p.getY()+p.getY2())/2;
						
						tabY2[0] = (p.getY()+p.getY2())/2;
						tabY2[1] = (p.getY()+p.getY2())/2;
						
						g.setColor(p.getCouleurContour());
						g.fillOval(tabX[0],  tabY[0],  tabX[1], tabY[1]);
						
						g.setColor(p.getCouleurRempli());
						g.fillOval(tabX2[0],  tabY2[0],  tabX2[1], tabY2[1]);
						

					}
					else {
						if(p.getCouleurRempli() != null) {
							g.setColor(p.getCouleurRempli());
							int[] tabX = {p.getX(), p.getX2()};
							int[] tabY = {(p.getY()+p.getY2())/2, (p.getY()+p.getY2())/2};
							g.fillOval(tabX[0],  tabY[0],  tabX[1], tabY[1]);
						}
						
						if(p.getTailleContour() != 0) {
							int[] tabX = new int[2], tabY = new int[2], tabX2 = new int[2], tabY2 = new int[2];
							
							tabX[0] = p.getX();
							tabX[1] = p.getX2()+p.getTailleContour();
							
							tabX2[0] = p.getX()+p.getTailleContour();
							tabX2[1] = p.getX2()-p.getTailleContour();
							
							tabY[0] = (p.getY()+p.getY2())/2;
							tabY[1] = (p.getY()+p.getY2())/2;
							
							tabY2[0] = (p.getY()+p.getY2())/2;
							tabY2[1] = (p.getY()+p.getY2())/2;
							
							g.setColor(p.getCouleurContour());
							g.fillOval(tabX[0],  tabY[0],  tabX[1], tabY[1]);
							
							g.setColor(backgroundColor);
							g.fillOval(tabX2[0],  tabY2[0],  tabX2[1], tabY2[1]);
						}
					}
				}
				else if(p.getType().equals("RectangleRond"))
				{
					if(p.getTailleContour() != 0 && p.getCouleurRempli() != null)
					{
						int x, y, lon, lar;
						x = p.getX();
						y = p.getY();
						
						lon = p.getX2() - p.getX();
						lar = p.getY2()-p.getY();
						g.setColor(p.getCouleurContour());
						g.fillRoundRect(x,  y,  lon,  lar,  10,  10);
						
						g.setColor(p.getCouleurRempli());
						g.fillRoundRect(p.getX()+p.getTailleContour(),  p.getY()+p.getTailleContour(), lon - (p.getTailleContour()*2), lar - (p.getTailleContour()*2), 5, 5);

					}
					else {
						if(p.getCouleurRempli() != null) {
							g.setColor(p.getCouleurRempli());
							int x, y, lon, lar;
							x = p.getX();
							y = p.getY();
							
							lon = p.getX2() - p.getX();
							lar = p.getY2()-p.getY();
							g.setColor(p.getCouleurRempli());
							g.fillRoundRect(x,  y,  lon,  lar,  10,  10);
						}
						
						if(p.getTailleContour() != 0) {
							int x, y, lon, lar;
							x = p.getX();
							y = p.getY();
							
							lon = p.getX2() - p.getX();
							lar = p.getY2()-p.getY();
							g.setColor(p.getCouleurContour());
							g.fillRoundRect(x,  y,  lon,  lar,  10,  10);
							
							g.setColor(backgroundColor);
							g.fillRoundRect(p.getX()+p.getTailleContour(),  p.getY()+p.getTailleContour(), lon - (p.getTailleContour()*2), lar - (p.getTailleContour()*2), 5, 5);
						}
					}
					
				}
			}
		}
		
	}
	
	public void effacer()
	{
		peutEffacer = true;
		retourEffacer = true;
		points2 = new ArrayList<Point>();
		for (Point p : points) {
			points2.add(p);
		}
		points = new ArrayList<Point>();
		repaint();
	}
	
	public void setPointerType(String t)
	{
		this.pointerType = t;
	}
	public String getPointerType() {
		return pointerType;
	}
	
	public void setPointerSize(int s) {
		this.pointerSize = s;
	}
	public int getPointerSize() {
		return pointerSize;
	}
	
	public void setPointerColor(Color c)
	{
		this.pointerColor = c;
	}
	public Color getPointerColor() {
		return pointerColor;
	}
	
	public void setBackgroungColor(Color c) {
		this.backgroundColor = c;
	}
	
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	
	public Color getPreviousBackgroundColor() {
		return previousBackgroundColor;
	}
	
	public void setPreiousBackgroungColor(Color c) {
		this.previousBackgroundColor = c;
	}
	
	public int getTailleContour() {
		return tailleContour;
	}
	
	public void setTailleContour(int t) {
		this.tailleContour = t;
	}
	
	public Color getCouleurContour() {
		return couleurContour;
	}
	
	public void setCouleurContour(Color c) {
		this.couleurContour = c;
	}
	
	public Color getCouleurRempli() {
		return couleurRempli;
	}
	
	public void setCouleurRempli(Color c) {
		this.couleurRempli = c;
	}
	
	public boolean getACocheContour() {
		return aCocheContour;
	}
	
	public boolean getAcocheRemplissage() {
		return aCocheRemplissage;
	}
	
	public void setACocheContour(boolean b) {
		this.aCocheContour = b;
	}
	
	public void setACocheRemplissage(boolean b) {
		this.aCocheRemplissage = b;
	}
	
	public void setGomme(boolean b) {
		peutGommer = b;
	}
	
	public void setPoints(ArrayList<Point> p)
	{
		points = p;
	}
	
	public ArrayList<Point> getPoints(){
		return points;
	}
	
	public void retour() {
		if(fonds.size() > 0 || points.size() > 0) {
			
			if(fonds.size() > 0 && points.size() > 0) {
				if(fonds.get(fonds.size()-1).getIndice() >= points.size()) {
					previousBackgroundColor = backgroundColor;
					backgroundColor = fonds.get(fonds.size()-1).getCouleurAvant();
					doublurePoints.add(fonds.get(fonds.size()-1));
					fonds.remove(fonds.size()-1);
					repaint();
				}
				else {
					int saut = nbreSauts.get(nbreSauts.size() - 1);
					nbreSauts2.add(saut);
					for(int i = 0; i < saut; i++)
					{
						doublurePoints.add(points.get(points.size() - 1));
						points.remove(points.size() - 1);
					}
					repaint();
					nbreSauts.remove(nbreSauts.size() - 1);
				}
			}
			else if(fonds.size() > 0 && points.size() == 0) {
				previousBackgroundColor = backgroundColor;
				backgroundColor = fonds.get(fonds.size()-1).getCouleurAvant();
				doublurePoints.add(fonds.get(fonds.size()-1));
				fonds.remove(fonds.size()-1);
				repaint();
				
				if(fonds.size() == 0){
					Fenetre.setRetour(false);
				}
			}
			else if(fonds.size() == 0 && points.size() > 0) {
				int saut = nbreSauts.get(nbreSauts.size() - 1);
				nbreSauts2.add(saut);
				for(int i = 0; i < saut; i++)
				{
					doublurePoints.add(points.get(points.size() - 1));
					points.remove(points.size() - 1);
				}
				repaint();
				nbreSauts.remove(nbreSauts.size() - 1);
				
				if(points.size() == 0 || nbreSauts.size() == 0){
					Fenetre.setRetour(false);
				}
			}
		}
		else if(retourEffacer) {
			for(Point p : points2) {
				points.add(p);
			}
			retourEffacer = false;
			repaint();
		}
		
		if(fonds.size() == 0 && (points.size() == 0 || nbreSauts.size() == 0)){
			Fenetre.setRetour(false);
		}
		
		Fenetre.setAvancer(true);
		
	}
	
	public void avancer()
	{
		if(doublurePoints.get(doublurePoints.size() - 1).getClass() == p.getClass()) {
			int saut = nbreSauts2.get(nbreSauts2.size() - 1);
			nbreSauts.add(saut);
			for(int i = 0; i < saut; i++)
			{
				
				points.add((Point)doublurePoints.get(doublurePoints.size() - 1));
				doublurePoints.remove(doublurePoints.size() - 1);
			}
			repaint();
			nbreSauts2.remove(nbreSauts2.size() - 1);
		}
		else {
			backgroundColor = ((FondCouleur)doublurePoints.get(doublurePoints.size()-1)).getCouleurApres();
			fonds.add((FondCouleur)doublurePoints.get(doublurePoints.size()-1));
			doublurePoints.remove(doublurePoints.size() - 1);
			repaint();
		}
		
		
		if(doublurePoints.size() == 0)
		{
			Fenetre.setAvancer(false);
		}
		else {
			Fenetre.setAvancer(true);
		}
		
		Fenetre.setRetour(true);
		
	}
	
	public ArrayList<FondCouleur> getFonds(){
		return fonds;
	}
	
	
	
	
}
