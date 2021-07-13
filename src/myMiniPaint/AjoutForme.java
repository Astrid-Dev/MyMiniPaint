package myMiniPaint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class AjoutForme extends JDialog {

	private JRadioButton jrLosange = new JRadioButton("Losange"),
			jrTriangle = new JRadioButton("Triangle"),
			jrTriangleRect = new JRadioButton("Triangle rectangle"),
			jrRectangle = new JRadioButton("Rectangle"),
			jrRectanglerRond = new JRadioButton("Rectangle arrondi"),
			jrCercle = new JRadioButton("Ellipse");
	
	
	
	
	private ImageIcon imgLosange = new ImageIcon("images\\barreOutils\\losange.png"),
			imgTriangle = new ImageIcon("images\\barreOutils\\triangle.png"),
			imgTriangleRect = new ImageIcon("images\\barreOutils\\triangleRect.png"),
			imgRectangle = new ImageIcon("images\\barreOutils\\rectangle.png"),
			imgRectanglerRond = new ImageIcon("images\\barreOutils\\rectangleRond.png"),
			imgCercle = new ImageIcon("images\\barreOutils\\cercle.png");
	
	private JButton valider = new JButton("valider"),
			annuler = new JButton("Annuler");
	
	private JCheckBox contour = new JCheckBox("Contour"),
			remplissage = new JCheckBox("Remplissage");
	
	private JTextField tailleContour = new JTextField(),
			rouge1 = new JTextField(),
			rouge2 = new JTextField(),
			vert1 = new JTextField(),
			vert2 = new JTextField(),
			bleu1 = new JTextField(),
			bleu2 = new JTextField();
	
	private JButton pallette = new JButton("Pan. de couleurs"), palette2 = new JButton("Pan. de couleurs");
	
	private JLabel lab1 = new JLabel("Epaisseur du contour : "),
			lab2 = new JLabel("Pixel(s)"),
			lab3 = new JLabel("Couleur du contour : "),
			lab4 = new JLabel("Rouge : "),
			lab5 = new JLabel("Vert : "),
			lab6 = new JLabel("Bleu : "),
			lab7 = new JLabel("Couleur du remplissage : "),
			lab8 = new JLabel("Rouge : "),
			lab9 = new JLabel("Vert : "),
			lab10 = new JLabel("Bleu : ");
	
	private AjoutFormeInfos infos = new AjoutFormeInfos();
	
	private char bufferRouge1, bufferVert1, bufferBleu1, bufferRouge2, bufferVert2, bufferBleu2, bufferTaille;
	
	private int tailleContours;
	private String typeForme = "";
	private Color couleurContour = null,
			couleurRempli = null;
	
	private boolean peutEnvoyerInfos = false;
	
	private boolean cocheContour, cocheRemp;
	private String type;
	private int taille;
	private Color couleurc, couleurR;
	
	public AjoutForme(JFrame parent, String title, boolean modal, String t, boolean b1, boolean b2, int cont, Color c, Color c2)
	{
		super(parent, title, modal);
		this.setSize(600,  500);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setIconImage(null);
		this.cocheContour = b1;
		this.cocheRemp = b2;
		this.taille = cont;
		this.type = t;
		this.couleurc = c;
		this.couleurR = c2;
		initComponent();
		this.setVisible(true);
	}
	
	private void initComponent()
	{
		this.getContentPane().setLayout(new BorderLayout());
		JPanel panHaut = new JPanel();
		panHaut.setPreferredSize(new Dimension(this.getWidth(),  40));
		JLabel titre = new JLabel("Ajout d'une forme", JLabel.CENTER);
		titre.setPreferredSize(new Dimension(this.getWidth(), 30));
		titre.setFont(new Font("Arial", Font.ITALIC, 20));
		panHaut.add(titre);
		
		JPanel panMilieu = new JPanel();

		JPanel panForme = new JPanel();
		panForme.setPreferredSize(new Dimension(this.getWidth(), 150));
		panForme.setBorder(BorderFactory.createTitledBorder("choisissez une forme"));
		panForme.setLayout(new GridLayout(2,  3));
		JPanel panLo = new JPanel(),
				panTri = new JPanel(),
				panTriR = new JPanel(),
				panRect = new JPanel(),
				panRectR = new JPanel(),
				panCer = new JPanel();
		panLo.add(jrLosange);
		panLo.add(new JLabel(imgLosange));
		
		panTri.add(jrTriangle);
		panTri.add(new JLabel(imgTriangle));
		
		panTriR.add(jrTriangleRect);
		panTriR.add(new JLabel(imgTriangleRect));
		
		panRect.add(jrRectangle);
		panRect.add(new JLabel(imgRectangle));
		
		panRectR.add(jrRectanglerRond);
		panRectR.add(new JLabel(imgRectanglerRond));
		
		panCer.add(jrCercle);
		panCer.add(new JLabel(imgCercle));
		
		panForme.add(panLo);
		panForme.add(panTri);
		panForme.add(panTriR);
		panForme.add(panRect);
		panForme.add(panRectR);
		panForme.add(panCer);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(jrCercle);
		bg.add(jrLosange);
		bg.add(jrRectangle);
		bg.add(jrRectanglerRond);
		bg.add(jrTriangle);
		bg.add(jrTriangleRect);
		jrLosange.setSelected(true);
		typeForme = "Losange";
		
		JPanel panPro = new JPanel();
		panPro.setPreferredSize(new Dimension(this.getWidth(),  240));
		panPro.setBorder(BorderFactory.createTitledBorder("Propriété de la forme"));
		panPro.setLayout(null);
		
		JPanel p = new JPanel();
		p.setBounds(300,  20,  1,  210);
		p.setBackground(Color.BLACK);
		panPro.add(p);
		
		contour.setFont(new Font("Times New Roman", Font.BOLD|Font.ITALIC, 14));
		remplissage.setFont(new Font("Times New Roman", Font.BOLD|Font.ITALIC, 14));
		
		contour.setBounds(30, 30, 80, 15);
		panPro.add(contour);
		
		remplissage.setBounds(330, 30,  100,  15);
		panPro.add(remplissage);
				
		lab1.setBounds(70,  60,  120,  15);
		panPro.add(lab1);
		
		tailleContour.setBounds(100,  80,  30,  20);
		panPro.add(tailleContour);
		
		lab2.setBounds(140,  82,  60,  15);
		panPro.add(lab2);
		 
		lab3.setBounds(70,  110,  120,  15);
		panPro.add(lab3);
		
		
		lab4.setBounds(100,  130,  50,  20);
		panPro.add(lab4);
		rouge1.setBounds(155,  130,  50,  20);
		panPro.add(rouge1);
		
		lab5.setBounds(100,  160,  50,  20);
		panPro.add(lab5);
		vert1.setBounds(155,  160,  50,  20);
		panPro.add(vert1);
		
		lab6.setBounds(100,  190,  50,  20);
		panPro.add(lab6);
		bleu1.setBounds(155,  190,  50,  20);
		panPro.add(bleu1);
		
		pallette.setBounds(100,  215,  120,  20);
		panPro.add(pallette);
		
		lab7.setBounds(370,  60,  125,  15);
		panPro.add(lab7);

			
		lab8.setBounds(400,  90,  50,  20);
		panPro.add(lab8);
		rouge2.setBounds(455,  90,  50,  20);
		panPro.add(rouge2);
		
		lab9.setBounds(400,  120,  50,  20);
		panPro.add(lab9);
		vert2.setBounds(455,  120,  50,  20);
		panPro.add(vert2);
		
		lab10.setBounds(400,  150,  50,  20);
		panPro.add(lab10);
		bleu2.setBounds(455,  150,  50,  20);
		panPro.add(bleu2);
			
		palette2.setBounds(400,  185,  120,  20);
		panPro.add(palette2);

		panMilieu.add(panForme);
		panMilieu.add(panPro);
		
		JPanel panBas = new JPanel();
		panBas.setPreferredSize(new Dimension(this.getWidth(),  30));
		panBas.add(annuler);
		panBas.add(valider);
		
		rouge1.setText("0");
		vert1.setText("0");
		bleu1.setText("0");
		
		rouge2.setText("0");
		vert2.setText("0");
		bleu2.setText("0");
		
		tailleContour.setText("1");
		
		contour.addActionListener(new selectionListener());
		remplissage.addActionListener(new selectionListener());
		
		rouge1.addKeyListener(new DemandeCouleurListener());
		vert1.addKeyListener(new DemandeCouleurListener());
		bleu1.addKeyListener(new DemandeCouleurListener());
		
		rouge2.addKeyListener(new DemandeCouleurListener());
		vert2.addKeyListener(new DemandeCouleurListener());
		bleu2.addKeyListener(new DemandeCouleurListener());
		
		tailleContour.addKeyListener(new DemandeTailleListener());
		
		jrCercle.addActionListener(new FormeListener());
		jrLosange.addActionListener(new FormeListener());
		jrRectangle.addActionListener(new FormeListener());
		jrRectanglerRond.addActionListener(new FormeListener());
		jrTriangle.addActionListener(new FormeListener());
		jrTriangleRect.addActionListener(new FormeListener());
		
		pallette.addActionListener(new ChooseColor());
		palette2.addActionListener(new ChooseColor());
		
		annuler.addActionListener(new AnnulerListener());
		valider.addActionListener(new ValiderListener());
		
		this.getContentPane().add(panHaut, BorderLayout.NORTH);
		this.getContentPane().add(panMilieu, BorderLayout.CENTER);
		this.getContentPane().add(panBas, BorderLayout.SOUTH);
		
		if(type.equals("Rectangle"))
		{
			jrRectangle.setSelected(true);
		}
		else if(type.equals("Losange"))
		{
			jrLosange.setSelected(true);
		}
		else if(type.equals("Triangle"))
		{
			jrTriangle.setSelected(true);
		}
		else if(type.equals("TriangleRect"))
		{
			jrTriangleRect.setSelected(true);
		}
		else if(type.equals("Cercle"))
		{
			jrCercle.setSelected(true);
		}
		else if(type.equals("RectangleRond"))
		{
			jrRectanglerRond.setSelected(true);
		}
		
		contour.setSelected(cocheContour);
		remplissage.setSelected(cocheRemp);
		tailleContour.setText(String.valueOf(taille));
		rouge1.setText(String.valueOf(couleurc.getRed()));
		vert1.setText(String.valueOf(couleurc.getGreen()));
		bleu1.setText(String.valueOf(couleurc.getBlue()));
		rouge2.setText(String.valueOf(couleurR.getRed()));
		vert2.setText(String.valueOf(couleurR.getGreen()));
		bleu2.setText(String.valueOf(couleurR.getBlue()));
		
		statutChoix();
	}
	
	class AnnulerListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			peutEnvoyerInfos = false;
			dispose();
		}
		
	}
	
	class ValiderListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(!contour.isSelected() && !remplissage.isSelected())
			{
				JOptionPane jop = new JOptionPane();
				jop.showMessageDialog(null,  "Veuillez cochez au moins une option entre \nle contour et le remplissage", "Informations insuffisantes", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				if(contour.isSelected())
				{
					if(tailleContour.getText().equals("")) {
						tailleContours = Integer.valueOf(String.valueOf(bufferTaille));
					}
					else {
						tailleContours = Integer.valueOf(tailleContour.getText());
					}
					int r, v, b;
					if(rouge1.getText().equals("")) {
						r = Integer.valueOf(String.valueOf(bufferRouge1));
					}
					else {
						r = Integer.valueOf(rouge1.getText());
					}
					
					if(vert1.getText().equals("")) {
						v = Integer.valueOf(String.valueOf(bufferVert1));
					}
					else {
						v = Integer.valueOf(vert1.getText());
					}
					
					if(bleu1.getText().equals("")) {
						b = Integer.valueOf(String.valueOf(bufferBleu1));
					}
					else {
						b = Integer.valueOf(bleu1.getText());
					}
					couleurContour = new Color(r, v, b);
				}
				else
				{
					tailleContours = 0;
					couleurContour = null;
				}
				
				if(remplissage.isSelected())
				{
					int r, v, b;
					if(rouge2.getText().equals("")) {
						r = Integer.valueOf(String.valueOf(bufferRouge2));
					}
					else {
						r = Integer.valueOf(rouge2.getText());
					}
					
					if(vert2.getText().equals("")) {
						v = Integer.valueOf(String.valueOf(bufferVert2));
					}
					else {
						v = Integer.valueOf(vert2.getText());
					}
					
					if(bleu2.getText().equals("")) {
						b = Integer.valueOf(String.valueOf(bufferBleu2));
					}
					else {
						b = Integer.valueOf(bleu2.getText());
					}
					couleurRempli = new Color(r, v, b);
				}
				else {
					couleurRempli = null;
				}
				
				infos = new AjoutFormeInfos(typeForme, tailleContours, couleurContour, couleurRempli);
				peutEnvoyerInfos = true;
				dispose();
			}
		}
		
	}
	
	class selectionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			statutChoix();
		}
		
	}
	
	class FormeListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if((JRadioButton)arg0.getSource() == jrLosange) {
				typeForme = "Losange";
			}
			else if((JRadioButton)arg0.getSource() == jrTriangle) {
				typeForme = "Triangle";
			}
			else if((JRadioButton)arg0.getSource() == jrTriangleRect) {
				typeForme = "TriangleRect";
			}
			else if((JRadioButton)arg0.getSource() == jrCercle) {
				typeForme = "Cercle";
			}
			else if((JRadioButton)arg0.getSource() == jrRectangle) {
				typeForme = "Rectangle";
			}
			else if((JRadioButton)arg0.getSource() == jrRectanglerRond) {
				typeForme = "RectangleRond";
			}
		}
		
	}
	
	class ChooseColor extends JDialog implements ActionListener
	{
		public ChooseColor() {
			this.setSize(300,  200);
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			this.setLocationRelativeTo(null);
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if((JButton)arg0.getSource() == pallette)
			{
				int r, v, b;
				if(rouge1.getText().equals("")) {
					r = Integer.valueOf(String.valueOf(bufferRouge1));
				}
				else {
					r = Integer.valueOf(rouge1.getText());
				}
				
				if(vert1.getText().equals("")) {
					v = Integer.valueOf(String.valueOf(bufferVert1));
				}
				else {
					v = Integer.valueOf(vert1.getText());
				}
				
				if(bleu1.getText().equals("")) {
					b = Integer.valueOf(String.valueOf(bufferBleu1));
				}
				else {
					b = Integer.valueOf(bleu1.getText());
				}
				Color init = new Color(r, v, b);
				Color couleur = JColorChooser.showDialog(this,  "Choisissez une couleur de pointeur",  init);
				if(couleur == null) {
					couleur = init;
				}
				vert1.setText(String.valueOf(couleur.getGreen()));
				rouge1.setText(String.valueOf(couleur.getRed()));
				bleu1.setText(String.valueOf(couleur.getBlue()));
			}
			else if((JButton)arg0.getSource() == palette2)
			{
				int r, v, b;
				if(rouge2.getText().equals("")) {
					r = Integer.valueOf(String.valueOf(bufferRouge2));
				}
				else {
					r = Integer.valueOf(rouge2.getText());
				}
				
				if(vert2.getText().equals("")) {
					v = Integer.valueOf(String.valueOf(bufferVert2));
				}
				else {
					v = Integer.valueOf(vert2.getText());
				}
				
				if(bleu2.getText().equals("")) {
					b = Integer.valueOf(String.valueOf(bufferBleu2));
				}
				else {
					b = Integer.valueOf(bleu2.getText());
				}
				Color init = new Color(r, v, b);
				Color couleur = JColorChooser.showDialog(this,  "Choisissez une couleur de pointeur",  init);
				if(couleur == null) {
					couleur = init;
				}
				vert2.setText(String.valueOf(couleur.getGreen()));
				rouge2.setText(String.valueOf(couleur.getRed()));
				bleu2.setText(String.valueOf(couleur.getBlue()));
			}
			
		}
		
	}
	
	class DemandeTailleListener implements KeyListener
	{ 

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			char c = arg0.getKeyChar();
			if(tailleContour.getText().length() == 1 && c == KeyEvent.VK_BACK_SPACE) {
				bufferTaille = tailleContour.getText().charAt(0);
			}
			if( (((c < '0') || (c > '9')) || (tailleContour.getText().length() >= 2)) && (c != KeyEvent.VK_BACK_SPACE)){
				arg0.consume();
			}
			else if((tailleContour.getText().length() == 1) && (c != KeyEvent.VK_BACK_SPACE)) {
				arg0.consume();
				tailleContour.setText("10");
			}
			else if(tailleContour.getText().length() == 0 && c == '0') {
				arg0.consume();
			}
				
		}
	}
	
	class DemandeCouleurListener implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			if((JTextField)arg0.getSource() == vert1)
			{
				char c = arg0.getKeyChar();
				if(vert1.getText().length() == 1 && c == KeyEvent.VK_BACK_SPACE) {
					bufferVert1 = vert1.getText().charAt(0);
				}
				if( (((c < '0') || (c > '9')) || (vert1.getText().length() >= 3)) && (c != KeyEvent.VK_BACK_SPACE)){
					arg0.consume();
				}
				else if(vert1.getText().equals("0") && c != KeyEvent.VK_BACK_SPACE) {
					arg0.consume();
					vert1.setText(String.valueOf(c));
				}
				else if((vert1.getText().length() == 2) && ((c != KeyEvent.VK_BACK_SPACE) && (Integer.valueOf(vert1.getText().substring(0,  2) + String.valueOf(c)) > 255))) {
					arg0.consume();
					vert1.setText("255");
				}
			}
			else if((JTextField)arg0.getSource() == bleu1)
			{
				char c = arg0.getKeyChar();
				if(bleu1.getText().length() == 1 && c == KeyEvent.VK_BACK_SPACE) {
					bufferBleu1 = bleu1.getText().charAt(0);
				}
				if( (((c < '0') || (c > '9')) || (bleu1.getText().length() >= 3)) && (c != KeyEvent.VK_BACK_SPACE)){
					arg0.consume();
				}
				else if(bleu1.getText().equals("0") && c != KeyEvent.VK_BACK_SPACE) {
					arg0.consume();
					bleu1.setText(String.valueOf(c));
				}
				else if((bleu1.getText().length() == 2) && ((c != KeyEvent.VK_BACK_SPACE) && (Integer.valueOf(bleu1.getText().substring(0,  2) + String.valueOf(c)) > 255))) {
					arg0.consume();
					bleu1.setText("255");
				}
			}
			else if((JTextField)arg0.getSource() == rouge1)
			{
				char c = arg0.getKeyChar();
				if(rouge1.getText().length() == 1 && c == KeyEvent.VK_BACK_SPACE) {
					bufferRouge1 = rouge1.getText().charAt(0);
				}
				if( (((c < '0') || (c > '9')) || (rouge1.getText().length() >= 3)) && (c != KeyEvent.VK_BACK_SPACE)){
					arg0.consume();
				}
				else if(rouge1.getText().equals("0") && c != KeyEvent.VK_BACK_SPACE) {
					arg0.consume();
					rouge1.setText(String.valueOf(c));
				}
				else if((rouge1.getText().length() == 2) && ((c != KeyEvent.VK_BACK_SPACE) && (Integer.valueOf(rouge1.getText().substring(0,  2) + String.valueOf(c)) > 255))) {
					arg0.consume();
					rouge1.setText("255");
				}
			}
			else if((JTextField)arg0.getSource() == vert2)
			{
				char c = arg0.getKeyChar();
				if(vert2.getText().length() == 1 && c == KeyEvent.VK_BACK_SPACE) {
					bufferVert2 = vert2.getText().charAt(0);
				}
				if( (((c < '0') || (c > '9')) || (vert2.getText().length() >= 3)) && (c != KeyEvent.VK_BACK_SPACE)){
					arg0.consume();
				}
				else if(vert2.getText().equals("0") && c != KeyEvent.VK_BACK_SPACE) {
					arg0.consume();
					vert2.setText(String.valueOf(c));
				}
				else if((vert2.getText().length() == 2) && ((c != KeyEvent.VK_BACK_SPACE) && (Integer.valueOf(vert2.getText().substring(0,  2) + String.valueOf(c)) > 255))) {
					arg0.consume();
					vert2.setText("255");
				}
			}
			else if((JTextField)arg0.getSource() == bleu2)
			{
				char c = arg0.getKeyChar();
				if(bleu2.getText().length() == 1 && c == KeyEvent.VK_BACK_SPACE) {
					bufferBleu2 = bleu2.getText().charAt(0);
				}
				if( (((c < '0') || (c > '9')) || (bleu2.getText().length() >= 3)) && (c != KeyEvent.VK_BACK_SPACE)){
					arg0.consume();
				}
				else if(bleu2.getText().equals("0") && c != KeyEvent.VK_BACK_SPACE) {
					arg0.consume();
					bleu2.setText(String.valueOf(c));
				}
				else if((bleu2.getText().length() == 2) && ((c != KeyEvent.VK_BACK_SPACE) && (Integer.valueOf(bleu2.getText().substring(0,  2) + String.valueOf(c)) > 255))) {
					arg0.consume();
					bleu2.setText("255");
				}
			}
			else if((JTextField)arg0.getSource() == rouge2)
			{
				char c = arg0.getKeyChar();
				if(rouge2.getText().length() == 1 && c == KeyEvent.VK_BACK_SPACE) {
					bufferRouge2 = rouge2.getText().charAt(0);
				}
				if( (((c < '0') || (c > '9')) || (rouge2.getText().length() >= 3)) && (c != KeyEvent.VK_BACK_SPACE)){
					arg0.consume();
				}
				else if(rouge2.getText().equals("0") && c != KeyEvent.VK_BACK_SPACE) {
					arg0.consume();
					rouge2.setText(String.valueOf(c));
				}
				else if((rouge2.getText().length() == 2) && ((c != KeyEvent.VK_BACK_SPACE) && (Integer.valueOf(rouge2.getText().substring(0,  2) + String.valueOf(c)) > 255))) {
					arg0.consume();
					rouge2.setText("255");
				}
			}
		}
		
	}
	
	private void statutChoix()
	{
		if(!contour.isSelected()) {
			lab1.setEnabled(false);
			lab2.setEnabled(false);
			lab3.setEnabled(false);
			lab4.setEnabled(false);
			lab5.setEnabled(false);
			lab6.setEnabled(false);
			pallette.setEnabled(false);
			tailleContour.setEnabled(false);
			rouge1.setEnabled(false);
			vert1.setEnabled(false);
			bleu1.setEnabled(false);
		}
		else {
			lab1.setEnabled(true);
			lab2.setEnabled(true);
			lab3.setEnabled(true);
			lab4.setEnabled(true);
			lab5.setEnabled(true);
			lab6.setEnabled(true);
			pallette.setEnabled(true);
			tailleContour.setEnabled(true);
			rouge1.setEnabled(true);
			vert1.setEnabled(true);
			bleu1.setEnabled(true);
		}
		
		if(!remplissage.isSelected()) {
			lab7.setEnabled(false);
			lab8.setEnabled(false);
			lab9.setEnabled(false);
			lab10.setEnabled(false);
			palette2.setEnabled(false);
			rouge2.setEnabled(false);
			vert2.setEnabled(false);
			bleu2.setEnabled(false);
		}
		else {
			lab7.setEnabled(true);
			lab8.setEnabled(true);
			lab9.setEnabled(true);
			lab10.setEnabled(true);
			palette2.setEnabled(true);
			rouge2.setEnabled(true);
			vert2.setEnabled(true);
			bleu2.setEnabled(true);
		}
	}
	
	public AjoutFormeInfos getInfos() {
		return infos;
	}
	
	public boolean aEnvoyeInfos() {
		return peutEnvoyerInfos;
	}
	
	public boolean aCocheContour() {
		return contour.isSelected();
	}
	
	public boolean aCocheRemplissage() {
		return remplissage.isSelected();
	}
}
