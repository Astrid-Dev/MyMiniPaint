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
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ModifierPointeur extends JDialog{

	private JRadioButton pinceau = new JRadioButton("Pinceau"),
			traceurForme = new JRadioButton("Traceur de traits");
	
	private JComboBox<String> typePinceau = new JComboBox<String>(), typeCrayon = new JComboBox<String>(),
			choixCouleur = new JComboBox<String>();
	
	private JLabel demandeType = new JLabel("Type de pinceau : "),
			demandeTaille = new JLabel("Entrez une taille : "),
			px = new JLabel("pixel(s)");
	
	private JLabel demandeCrayon = new JLabel("Propriété du traceur : " ),
			demandeCouleur = new JLabel("Choisir une couleur : ");
	
	private JRadioButton predefini = new JRadioButton("Prédéfinie"),
			autreCouleurs = new JRadioButton("Autres couleurs");
	
	private JLabel rouge = new JLabel("Rouge :"),
			vert = new JLabel("Vert     :"),
			bleu = new JLabel("Bleu     :");
	
	private JButton annuler = new JButton("Annuler"),
			appliquer = new JButton("Appliquer");
	
	private JFormattedTextField taillePinceau = new JFormattedTextField(NumberFormat.getIntegerInstance());
	
	private JTextField red = new JTextField(), green = new JTextField(), blue = new JTextField();
	
	private JButton panneauCouleur = new JButton("Panneau de couleurs");
	
	private ModifierPointeurInfos mpInfos;
	char bufferTaille, bufferRed, bufferGreen, bufferBlue;
	
	private String type = "";
	private int size;
	private Color CouleurPointeur = null;
	
	private boolean peutEnvoyerInfos = false;
	
	public ModifierPointeur(JFrame parent, String title, boolean modal, String type, int size, Color couleurPointeur)
	{
		super(parent, title, modal);
		this.type = type;
		this.size = size;
		this.CouleurPointeur = couleurPointeur;
		this.setSize(500,  510);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		initComponent();
		
		this.setVisible(true);
	}
	
	private void initComponent()
	{
		Font fontChoix = new Font("Arial", Font.BOLD, 12);
		ButtonGroup bg1 = new ButtonGroup();
		bg1.add(pinceau);
		pinceau.setFont(fontChoix);
		bg1.add(traceurForme);
		traceurForme.setFont(fontChoix);
		pinceau.setSelected(true);
		
		ButtonGroup bg2 = new ButtonGroup();
		bg2.add(predefini);
		predefini.setFont(fontChoix);
		bg2.add(autreCouleurs);
		autreCouleurs.setFont(fontChoix);
		predefini.setSelected(true);
		
		typePinceau.addItem("ROND");
		typePinceau.addItem("CARRE");
		typePinceau.setSelectedItem("ROND");
		
		typeCrayon.addItem("Traits simples");
		typeCrayon.addItem("Traits continus");
		typeCrayon.setSelectedItem("Traits simples");
		
		JPanel panTitre = new JPanel();
		panTitre.setPreferredSize(new Dimension(this.getWidth(), 40));
		
		JLabel titre = new JLabel("Redéfinition du pointeur");
		titre.setFont(new Font("Arial", Font.ITALIC, 20));
		titre.setPreferredSize(new Dimension(this.getWidth(), 25));
		titre.setHorizontalAlignment(JLabel.CENTER);
		panTitre.add(titre);
		
		this.getContentPane().add(panTitre, BorderLayout.NORTH);
		
		JPanel panMilieu = new JPanel();
		JPanel pan1 = new JPanel();
		pan1.add(pinceau);
		JPanel p2 = new JPanel();
		p2.setPreferredSize(new Dimension(400, 20));
		pan1.add(p2);
		JPanel p3 = new JPanel();
		p3.setPreferredSize(new Dimension(45, 30));
		pan1.add(p3);
		
		
		JPanel panTemp2 = new JPanel();
		panTemp2.add(taillePinceau);
		taillePinceau.setPreferredSize(new Dimension(40, 20));
		px.setPreferredSize(new Dimension(50,  20));
		panTemp2.add(px);
		
		pan1.add(demandeType);
		pan1.add(typePinceau);
		JPanel p1 = new JPanel();
		p1.setPreferredSize(new Dimension(260, 25));
		pan1.add(p1);
		
		JPanel panTemp7 = new JPanel();
		panTemp7.setBorder(BorderFactory.createTitledBorder("Taille du pointeur"));
		panTemp7.setPreferredSize(new Dimension(this.getWidth(), 65));
		
	
		JPanel p13 = new JPanel();
		p13.setPreferredSize(new Dimension(52, 30));
		panTemp7.add(p13);
		panTemp7.add(demandeTaille);
		panTemp7.add(panTemp2);
		JPanel p12 = new JPanel();
		p12.setPreferredSize(new Dimension(223, 30));
		panTemp7.add(p12);
		
		pan1.setBorder(BorderFactory.createTitledBorder("Type de pointeur"));
		JPanel panTemp3 = new JPanel();
		pan1.add(traceurForme);
		JPanel p4 = new JPanel();
		p4.setPreferredSize(new Dimension(344, 20));
		pan1.add(p4);
		panTemp3.add(demandeCrayon);
		panTemp3.add(typeCrayon);
		pan1.add(panTemp3);
		JPanel p5 = new JPanel();
		p5.setPreferredSize(new Dimension(153, 20));
		pan1.add(p5);
		pan1.setPreferredSize(new Dimension(this.getWidth(),  150));
		
		panMilieu.add(pan1);
		panMilieu.add(panTemp7);
		
		
		JPanel panTemp4 = new JPanel();
		panTemp4.add(predefini);
		JPanel p6 = new JPanel();
		p6.setPreferredSize(new Dimension(390, 20));
		panTemp4.add(p6);
		choixCouleur.addItem("Noir");
		choixCouleur.addItem("Bleu");
		choixCouleur.addItem("Rouge");
		choixCouleur.addItem("Vert");
		choixCouleur.addItem("Jaune");
		choixCouleur.addItem("Orange");
		JPanel p8 = new JPanel();
		p8.setPreferredSize(new Dimension(42, 20));
		panTemp4.add(p8);
		panTemp4.add(demandeCouleur);
		panTemp4.add(choixCouleur);
		JPanel p7 = new JPanel();
		p7.setPreferredSize(new Dimension(240, 20));
		panTemp4.add(p7);
		panTemp4.add(autreCouleurs);
		JPanel p9 = new JPanel();
		p9.setPreferredSize(new Dimension(355, 20));
		panTemp4.add(p9);
		
		JPanel p10 = new JPanel();
		p10.setPreferredSize(new Dimension(47, 20));
		panTemp4.add(p10);
		
		JPanel panTemp5 = new JPanel();
		
		GridLayout gl2 = new GridLayout(3, 1);
		
		panTemp5.setLayout(gl2);
		panTemp5.add(rouge);
		red.setPreferredSize(new Dimension(40, 20));
		panTemp5.add(red);
		panTemp5.add(vert);
		green.setPreferredSize(new Dimension(40, 20));
		panTemp5.add(green);
		panTemp5.add(bleu);
		blue.setPreferredSize(new Dimension(40, 20));
		panTemp5.add(blue);
		panTemp4.add(panTemp5);
		panTemp4.add(panneauCouleur);
		JPanel p11 = new JPanel();
		p11.setPreferredSize(new Dimension(195, 20));
		panTemp4.add(p11);
		panTemp4.setBorder(BorderFactory.createTitledBorder("Couleur du pointeur"));
		panTemp4.setPreferredSize(new Dimension(this.getWidth(),  180));
		
		panMilieu.add(panTemp4);
		
		this.getContentPane().add(panMilieu, BorderLayout.CENTER);
		
		JPanel panTemp6 = new JPanel();
		panTemp6.add(annuler);
		panTemp6.add(appliquer);
		
		this.getContentPane().add(panTemp6, BorderLayout.SOUTH);
		
		initialiseFenetre();
		statutPointeur();
		
		predefini.addActionListener(new RadioListener());
		autreCouleurs.addActionListener(new RadioListener());
		pinceau.addActionListener(new RadioListener());
		traceurForme.addActionListener(new RadioListener());
		taillePinceau.addKeyListener(new DemandeTailleListener());
		
		choixCouleur.addActionListener(new ChoixCouleurListener());
		
		green.addKeyListener(new DemandeCouleurListener());
		red.addKeyListener(new DemandeCouleurListener());
		blue.addKeyListener(new DemandeCouleurListener());
		
		panneauCouleur.addActionListener(new ChooseColor());
		annuler.addActionListener(new AnnulerListener());
		appliquer.addActionListener(new AppliquerListener());
	}
	
	public void statutPointeur()
	{
		if(!pinceau.isSelected())
		{
			demandeType.setEnabled(false);
			typePinceau.setEnabled(false);
			
			demandeCrayon.setEnabled(true);
			typeCrayon.setEnabled(true);
		}
		else {
			demandeType.setEnabled(true);
			typePinceau.setEnabled(true);
			
			demandeCrayon.setEnabled(false);
			typeCrayon.setEnabled(false);
		}
		
		if(predefini.isSelected())
		{
			demandeCouleur.setEnabled(true);
			choixCouleur.setEnabled(true);
			
			rouge.setEnabled(false);
			bleu.setEnabled(false);
			vert.setEnabled(false);
			green.setEnabled(false);
			blue.setEnabled(false);
			red.setEnabled(false);
			panneauCouleur.setEnabled(false);
		}
		else
		{
			demandeCouleur.setEnabled(false);
			choixCouleur.setEnabled(false);
			
			rouge.setEnabled(true);
			bleu.setEnabled(true);
			vert.setEnabled(true);
			green.setEnabled(true);
			blue.setEnabled(true);
			red.setEnabled(true);
			panneauCouleur.setEnabled(true);
		}
	}
	
	class RadioListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			statutPointeur();
		}
		
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
			if(taillePinceau.getText().length() == 1 && c == KeyEvent.VK_BACK_SPACE) {
				bufferTaille = taillePinceau.getText().charAt(0);
			}
			if( (((c < '0') || (c > '9')) || (taillePinceau.getText().length() >= 3)) && (c != KeyEvent.VK_BACK_SPACE)){
				arg0.consume();
			}
			else if((taillePinceau.getText().length() == 2) && (c >= '0') && (Integer.valueOf(taillePinceau.getText().substring(0,  2)) >= 25)) {
				arg0.consume();
				taillePinceau.setText("250");
			}
			else if(taillePinceau.getText().length() == 0 && c == '0') {
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
			if((JTextField)arg0.getSource() == green)
			{
				char c = arg0.getKeyChar();
				if(green.getText().length() == 1 && c == KeyEvent.VK_BACK_SPACE) {
					bufferGreen = green.getText().charAt(0);
				}
				if( (((c < '0') || (c > '9')) || (green.getText().length() >= 3)) && (c != KeyEvent.VK_BACK_SPACE)){
					arg0.consume();
				}
				
				else if((green.getText().length() == 2) && ((c != KeyEvent.VK_BACK_SPACE) && (Integer.valueOf(green.getText().substring(0,  2) + String.valueOf(c)) > 255))) {
					arg0.consume();
					green.setText("255");
				}
			}
			else if((JTextField)arg0.getSource() == blue)
			{
				char c = arg0.getKeyChar();
				if(blue.getText().length() == 1 && c == KeyEvent.VK_BACK_SPACE) {
					bufferBlue = blue.getText().charAt(0);
				}
				if( (((c < '0') || (c > '9')) || (blue.getText().length() >= 3)) && (c != KeyEvent.VK_BACK_SPACE)){
					arg0.consume();
				}
				
				else if((blue.getText().length() == 2) && ((c != KeyEvent.VK_BACK_SPACE) && (Integer.valueOf(blue.getText().substring(0,  2) + String.valueOf(c)) > 255))) {
					arg0.consume();
					blue.setText("255");
				}
			}
			else if((JTextField)arg0.getSource() == red)
			{
				char c = arg0.getKeyChar();
				if(red.getText().length() == 1 && c == KeyEvent.VK_BACK_SPACE) {
					bufferRed = red.getText().charAt(0);
				}
				if( (((c < '0') || (c > '9')) || (red.getText().length() >= 3)) && (c != KeyEvent.VK_BACK_SPACE)){
					arg0.consume();
				}
				
				else if((red.getText().length() == 2) && ((c != KeyEvent.VK_BACK_SPACE) && (Integer.valueOf(red.getText().substring(0,  2) + String.valueOf(c)) > 255))) {
					arg0.consume();
					red.setText("255");
				}
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
			Color init = CouleurPointeur;
			Color couleur = JColorChooser.showDialog(this,  "Choisissez une couleur de pointeur",  init);
			if(couleur == null) {
				couleur = init;
			}
			green.setText(String.valueOf(couleur.getGreen()));
			red.setText(String.valueOf(couleur.getRed()));
			blue.setText(String.valueOf(couleur.getBlue()));
		}
		
	}
	
	class ChoixCouleurListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(choixCouleur.getSelectedItem().equals("Noir")) {
				Color couleur = Color.BLACK;
				red.setText(String.valueOf(couleur.getRed()));
				green.setText(String.valueOf(couleur.getGreen()));
				blue.setText(String.valueOf(couleur.getBlue()));
			}
			else if(choixCouleur.getSelectedItem().equals("Rouge"))
			{
				Color couleur = Color.red;
				red.setText(String.valueOf(couleur.getRed()));
				green.setText(String.valueOf(couleur.getGreen()));
				blue.setText(String.valueOf(couleur.getBlue()));
			}
			else if(choixCouleur.getSelectedItem().equals("Vert"))
			{
				Color couleur = Color.green;
				red.setText(String.valueOf(couleur.getRed()));
				green.setText(String.valueOf(couleur.getGreen()));
				blue.setText(String.valueOf(couleur.getBlue()));
			}
			else if(choixCouleur.getSelectedItem().equals("Bleu"))
			{
				Color couleur = Color.BLUE;
				red.setText(String.valueOf(couleur.getRed()));
				green.setText(String.valueOf(couleur.getGreen()));
				blue.setText(String.valueOf(couleur.getBlue()));
			}
			else if(choixCouleur.getSelectedItem().equals("Orange"))
			{
				Color couleur = Color.ORANGE;
				red.setText(String.valueOf(couleur.getRed()));
				green.setText(String.valueOf(couleur.getGreen()));
				blue.setText(String.valueOf(couleur.getBlue()));
			}
			else if(choixCouleur.getSelectedItem().equals("Jaune"))
			{
				Color couleur = Color.yellow;
				red.setText(String.valueOf(couleur.getRed()));
				green.setText(String.valueOf(couleur.getGreen()));
				blue.setText(String.valueOf(couleur.getBlue()));
			}
		}
		
	}
	
	class AppliquerListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String type = "";
			int pinceauSize;
			Color pointeurColor = null;
			
			int couleurRouge, couleurVerte, couleurBleu;
			
			
			if(pinceau.isSelected())
			{
				if(typePinceau.getSelectedItem().equals("ROND")) {
					type = "ROND";
				}
				else {
					type = "CARRE";
				}
			}
			else {
				if(typeCrayon.getSelectedItem().equals("Traits simples")) {
					type = "Traits simples";
				}
				else {
					type = "Traits continus";
				}
			}
			
			if(taillePinceau.getText().equals("")) {
				pinceauSize = Integer.valueOf(String.valueOf(bufferTaille));
			}
			else {
				pinceauSize = Integer.valueOf(taillePinceau.getText());
			}
			
			if(predefini.isSelected()) {
				if(choixCouleur.getSelectedItem().equals("Noir")) {
					pointeurColor = Color.BLACK;
				}
				else if(choixCouleur.getSelectedItem().equals("Rouge"))
				{
					pointeurColor = Color.red;
				}
				else if(choixCouleur.getSelectedItem().equals("Vert"))
				{
					pointeurColor = Color.green;
				}
				else if(choixCouleur.getSelectedItem().equals("Bleu"))
				{
					pointeurColor = Color.BLUE;
				}
				else if(choixCouleur.getSelectedItem().equals("Orange"))
				{
					pointeurColor = Color.ORANGE;
				}
				else if(choixCouleur.getSelectedItem().equals("Jaune"))
				{
					pointeurColor = Color.yellow;
				}
			}
			else {
				if(red.getText().equals("")) {
					couleurRouge = Integer.valueOf(String.valueOf(String.valueOf(bufferRed)));
				}
				else {
					couleurRouge = Integer.valueOf(red.getText());
				}
				
				if(blue.getText().equals("")) {
					couleurBleu = Integer.valueOf(String.valueOf(String.valueOf(bufferBlue)));
				}
				else {
					couleurBleu = Integer.valueOf(blue.getText());
				}
				
				if(green.getText().equals("")) {
					couleurVerte = Integer.valueOf(String.valueOf(String.valueOf(bufferGreen)));
				}
				else {
					couleurVerte = Integer.valueOf(green.getText());
				}
				
				pointeurColor = new Color(couleurRouge, couleurVerte, couleurBleu);
			}
			
			if(pinceauSize == 1 && type.equals("ROND")) {
				JOptionPane jop = new JOptionPane();
				jop.showMessageDialog(null,  "Avec un type de pinceau ROND et une taille de 1 pixel,\nle pointeur sera presque invisible!\n"
						+ "Donc veuillez modifier l'un des champs.", "Pointeur invisible", JOptionPane.WARNING_MESSAGE);
			}
			else {
				peutEnvoyerInfos = true;
				mpInfos = new ModifierPointeurInfos(type,  pinceauSize,  pointeurColor);
				dispose();
			}
		}
		
	}
	
	
	private void initialiseFenetre() {
		if(type.equals("ROND") || type.equals("CARRE"))
		{
			pinceau.setSelected(true);
			if(type.equals("ROND")) {
				typePinceau.setSelectedItem("ROND");
			}
			else {
				typePinceau.setSelectedItem("CARRE");
			}
		}
		else {
			traceurForme.setSelected(true);
			if(type.equals("Traits simples")) {
				typeCrayon.setSelectedItem("Traits simples");
			}
			else {
				typeCrayon.setSelectedItem("Traits continus");
			}
		}
		taillePinceau.setText(String.valueOf(size));
		
		if(CouleurPointeur == Color.BLACK || CouleurPointeur == Color.RED || CouleurPointeur == Color.GREEN || CouleurPointeur == Color.BLUE || CouleurPointeur == Color.orange || CouleurPointeur == Color.yellow)
		{
			predefini.setSelected(true);
			if(CouleurPointeur == Color.BLACK) {
				choixCouleur.setSelectedItem("Noir");
				red.setText(String.valueOf(CouleurPointeur.getRed()));
				green.setText(String.valueOf(CouleurPointeur.getGreen()));
				blue.setText(String.valueOf(CouleurPointeur.getBlue()));
			}
			else if(CouleurPointeur == Color.RED)
			{
				choixCouleur.setSelectedItem("Rouge");
				red.setText(String.valueOf(CouleurPointeur.getRed()));
				green.setText(String.valueOf(CouleurPointeur.getGreen()));
				blue.setText(String.valueOf(CouleurPointeur.getBlue()));
			}
			else if(CouleurPointeur == Color.GREEN)
			{
				choixCouleur.setSelectedItem("Vert");
				red.setText(String.valueOf(CouleurPointeur.getRed()));
				green.setText(String.valueOf(CouleurPointeur.getGreen()));
				blue.setText(String.valueOf(CouleurPointeur.getBlue()));
			}
			else if( CouleurPointeur == Color.BLUE)
			{
				choixCouleur.setSelectedItem("Bleu");
				red.setText(String.valueOf(CouleurPointeur.getRed()));
				green.setText(String.valueOf(CouleurPointeur.getGreen()));
				blue.setText(String.valueOf(CouleurPointeur.getBlue()));
			}
			else if( CouleurPointeur == Color.orange)
			{
				choixCouleur.setSelectedItem("Orange");
				red.setText(String.valueOf(CouleurPointeur.getRed()));
				green.setText(String.valueOf(CouleurPointeur.getGreen()));
				blue.setText(String.valueOf(CouleurPointeur.getBlue()));
			}
			else if( CouleurPointeur == Color.yellow)
			{
				choixCouleur.setSelectedItem("Jaune");
				red.setText(String.valueOf(CouleurPointeur.getRed()));
				green.setText(String.valueOf(CouleurPointeur.getGreen()));
				blue.setText(String.valueOf(CouleurPointeur.getBlue()));
			}
		}
		else
		{
			autreCouleurs.setSelected(true);
			red.setText(String.valueOf(CouleurPointeur.getRed()));
			green.setText(String.valueOf(CouleurPointeur.getGreen()));
			blue.setText(String.valueOf(CouleurPointeur.getBlue()));
		}
	}
	
	public ModifierPointeurInfos getInfos() {
		return mpInfos;
	}
	
	public boolean aEnvoyerInf() {
		return peutEnvoyerInfos;
	}

}
