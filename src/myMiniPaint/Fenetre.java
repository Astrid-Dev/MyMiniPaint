package myMiniPaint;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class Fenetre extends JFrame{

	private JMenuBar menuBar = new JMenuBar();
	private JMenu fichier = new JMenu("Fichier"),
			edition = new JMenu("Edition"),
			aPropos = new JMenu("À Propos");
	
	private JMenuItem nouveau = new JMenuItem("Nouveau",  new ImageIcon("images\\barreMenu\\nouveau.jpg")),
					ouvrir = new JMenuItem("Ouvrir",  new ImageIcon("images\\barreMenu\\ouvrir.jpg")),
					enregistrerSous = new JMenuItem("Enregistrer Sous",  new ImageIcon("images\\barreMenu\\enregistrerSous.jpg")),
					effacer = new JMenuItem("Effacer"),
					fermer = new JMenuItem("Fermer"),
					pointeur = new JMenuItem("Pointeur"),
					fond = new JMenuItem("Fond"),
					propos = new JMenuItem("?"),
					ajoutForme = new JMenuItem("Ajouter une forme");
					
	private static JMenuItem enregistrer = new JMenuItem("Enregistrer",  new ImageIcon("images\\barreMenu\\enregistrer.png"));
	
	private JToolBar toolBar = new JToolBar();
	
	private JButton newFile = new JButton(new ImageIcon("images\\barreOutils\\nouveau.jpg")),
			square = new JButton(new ImageIcon("images\\barreOutils\\carre.png")),
			circle = new JButton(new ImageIcon("images\\barreOutils\\rond.png")),
			green = new JButton(new ImageIcon("images\\barreOutils\\vert.png")),
			red = new JButton(new ImageIcon("images\\barreOutils\\rouge.png")),
			blue = new JButton(new ImageIcon("images\\barreOutils\\bleu.png")),
			gomme = new JButton(new ImageIcon("images\\barreOutils\\gomme.jpg")),
			choixCouleur = new JButton(new ImageIcon("images\\barreOutils\\choixCouleurs.jpg")),
			traits = new JButton(new ImageIcon("images\\barreOutils\\traitSimple.png")),
			traitc = new JButton(new ImageIcon("images\\barreOutils\\traitContinu.png")),
			ajForme = new JButton(new ImageIcon("images\\barreOutils\\forme.jpg"));
	
	private static JButton saveFile = new JButton(new ImageIcon("images\\barreOutils\\enregistrer.png")),
			avancer = new JButton(new ImageIcon("images\\barreOutils\\avance.jpg")),
			retour = new JButton(new ImageIcon("images\\barreOutils\\retour.jpg"));
			
	private static JMenuItem back = new JMenuItem("Retour"),
					forward = new JMenuItem("Avancer");
	
	
	private DrawPanel pan;
	
	private String fichierCourant = "Sans nom";
	
	private static boolean dejaEnregistre = false, mmFichier = false;
	private String dossierCourant;
	
	Choice choix = new Choice();
	
	
	public Fenetre()
	{
		this.setTitle("My Mini Paint");
		this.setSize(800,  600);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				new FermerListener().actionPerformed(new ActionEvent("",  1,  ""));;
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		this.setLocationRelativeTo(null);
		try {
			File f = new File("AncienChemin.sauvegarde");
			if(f.exists()) {
				FileInputStream fis = new FileInputStream(f);
				dossierCourant = "";
				byte[] buf = new byte[8];
				while(fis.read(buf) >= 0)
				{
					for(byte bit : buf) {
						dossierCourant += (char)bit;
					}
					buf = new byte[8];
				}
				
				fis.close();
			}else {
				dossierCourant = "";
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		initMenuBar();
		initToolBar();
		toolBarListener();
		setEnregistrement(true);
		
		Image icon = Toolkit.getDefaultToolkit().getImage("images\\logo\\logo1.jpg");
		this.setIconImage(icon);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		}catch(InstantiationException e ) {}
		catch(ClassNotFoundException e) {}
		catch(UnsupportedLookAndFeelException e) {}
		catch(IllegalAccessException e) {}
		
		pan = new DrawPanel();
		this.getContentPane().add(pan, BorderLayout.CENTER);
		this.setVisible(true);
	}
			
	
	private void initToolBar()
	{
		Color couleurFond = new Color(0,  255,  255);
		Dimension dim = new Dimension(40, 40);
		toolBar.add(retour);
		retour.setBackground(couleurFond);
		retour.setToolTipText("Précédent");
		toolBar.add(avancer);
		avancer.setBackground(couleurFond);
		avancer.setToolTipText("Suivant");
		toolBar.addSeparator(dim);
		toolBar.add(newFile);
		newFile.setBackground(couleurFond);
		newFile.setToolTipText("Nouveau fichier");
		toolBar.add(saveFile);
		saveFile.setBackground(couleurFond);
		saveFile.setToolTipText("Enregistrer");
		toolBar.addSeparator(dim);
		toolBar.add(square);
		square.setToolTipText("Pointeur carré");
		square.setBackground(couleurFond);
		toolBar.add(circle);
		circle.setToolTipText("Pointeur rond");
		circle.setBackground(couleurFond);
		traitc.setBackground(couleurFond);
		traits.setBackground(couleurFond);
		toolBar.add(traits);
		toolBar.add(traitc);
		ajForme.setBackground(couleurFond);
		toolBar.add(ajForme);
		ajForme.setToolTipText("Ajouter une forme");
		traitc.setToolTipText("<html>" + "Traceur de <br>" + " traits continus" + "</html");
		traits.setToolTipText("<html>" + "Traceur de <br>" + " traits simples" + "</html");
		toolBar.addSeparator(dim);
		toolBar.add(green);
		green.setToolTipText("<html>" + "Pointeur de <br>couleur" + " verte" + "</html");
		green.setBackground(couleurFond);
		toolBar.add(red);
		red.setToolTipText("<html>" + "Pointeur de <br>couleur" + " rouge" + "</html");
		red.setBackground(couleurFond);
		toolBar.add(blue);
		blue.setBackground(couleurFond);
		blue.setToolTipText("<html>" + "Pointeur de <br>couleur" + " bleue" + "</html");
		toolBar.add(choixCouleur);
		choixCouleur.setBackground(couleurFond);
		choixCouleur.setToolTipText("Autres couleurs");
		toolBar.addSeparator(dim);
		toolBar.add(gomme);
		gomme.setBackground(couleurFond);
		gomme.setToolTipText("Gomme");
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(this.getWidth(), toolBar.getHeight()));
		p.setBackground(couleurFond);
		toolBar.add(p);
		
		aPropos.add(propos);
		
		toolBar.setEnabled(false);
		toolBar.setBackground(couleurFond);
		setRetour(false);
		setAvancer(false);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
	}
	
	private void initMenuBar()
	{
		menuBar.add(fichier);
		menuBar.add(edition);
		menuBar.add(aPropos);
		
		fichier.add(nouveau);
		fichier.add(ouvrir);
		fichier.addSeparator();
		fichier.add(enregistrer);
		fichier.add(enregistrerSous);
		fichier.addSeparator();
		fichier.add(effacer);
		fichier.addSeparator();
		fichier.add(fermer);
		
		edition.add(pointeur);
		edition.addSeparator();
		edition.add(ajoutForme);
		edition.addSeparator();
		edition.add(fond);
		edition.addSeparator();
		edition.add(back);
		edition.add(forward);
		
		fichier.setMnemonic('F');
		edition.setMnemonic('E');
		aPropos.setMnemonic('P');
		nouveau.setMnemonic('N');
		ouvrir.setMnemonic('O');
		enregistrer.setMnemonic('E');
		enregistrerSous.setMnemonic('r');
		
		
		nouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		ouvrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
		enregistrer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		enregistrerSous.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
		effacer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK));
		fermer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
		back.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
		forward.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK));
		
		this.setJMenuBar(menuBar);
		
	}
	
	private void toolBarListener()
	{
		square.addActionListener(new FormListener());
		circle.addActionListener(new FormListener());
		
		green.addActionListener(new ColorListener());
		red.addActionListener(new ColorListener());
		blue.addActionListener(new ColorListener());
		choixCouleur.addActionListener(new ChooseColor());
		
		gomme.addActionListener(new GommeListener());
		
		ouvrir.addActionListener(new OuvrirListener());
		
		enregistrer.addActionListener(new EnregistrerListener());
		saveFile.addActionListener(new EnregistrerListener());
		enregistrerSous.addActionListener(new EnregistrerSousListener());
		
		effacer.addActionListener(new EffacerListener());
		
		fermer.addActionListener(new FermerListener());
		
		nouveau.addActionListener(new NouveauListener());
		newFile.addActionListener(new NouveauListener());
		
		retour.addActionListener(new RetourListener());
		avancer.addActionListener(new AvancerListener());
		
		fond.addActionListener(new BackGroundListener());
		
		pointeur.addActionListener(new PointeurListener());
		traits.addActionListener(new Form2Listener());
		traitc.addActionListener(new Form2Listener());
		
		ajoutForme.addActionListener(new AjoutFormeListener());
		ajForme.addActionListener(new AjoutFormeListener());
		
		propos.addActionListener(new AProposListener());
		
	}
	
	class AProposListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			new APropos(null, "My Mini Paint", true);
		}
		
	}
	class FormListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			pan.setGomme(false);
			
			if(arg0.getSource() == square)
			{
				pan.setPointerType("CARRE");
			}
			else if(arg0.getSource() == circle)
			{
				pan.setPointerType("ROND");
			}
		}
		
	}
	
	class Form2Listener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if((JButton)arg0.getSource() == traits) {
				pan.setPointerType("Traits simples");
			}
			else if((JButton)arg0.getSource() == traitc){
				pan.setPointerType("Traits continus");
			}
		}
		
	}
	
	class ColorListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			pan.setGomme(false);
			
			if(arg0.getSource() == green) {
				pan.setPointerColor(Color.GREEN);
			}
			else if(arg0.getSource() == red) {
				pan.setPointerColor(Color.red);
			}
			else if(arg0.getSource() == blue) {
				pan.setPointerColor(Color.BLUE);
			}
			else if(arg0.getSource() == choixCouleur) {
				
				new ChooseColor();
				
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
			pan.setGomme(false);
			Color init = Color.black;
			Color couleur = JColorChooser.showDialog(this,  "Choisissez une couleur de pointeur",  init);
			
			pan.setPointerColor(couleur);
		}
		
	}
	
	class GommeListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			pan.setGomme(true);
		}
		
	}
	
	class OuvrirListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			JFileChooser choose = new JFileChooser(FileSystemView.getFileSystemView().getParentDirectory(new File(dossierCourant)));
			choose.setDialogTitle("Sélectionner un fichier ");
			if(fichierCourant.equals("Sans nom")) {
				
			}
			else {
				choose.setSelectedFile(new File(fichierCourant.split(".paint")[0] + ".paint"));
			}
			
			choose.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filtre = new FileNameExtensionFilter("Images paint", "paint");
			choose.addChoosableFileFilter(filtre);
			
			
			int res = choose.showOpenDialog(null);
			if(res == JFileChooser.APPROVE_OPTION) {
				
				if(dejaEnregistre) {
					Ouvrir ouvrir = new Ouvrir(choose.getSelectedFile().toString());
					pan.setPoints(ouvrir.getPoints());
					pan.setBackgroungColor(ouvrir.getColor());
					pan.setPreiousBackgroungColor(ouvrir.getColor());
					pan.repaint();
					setEnregistrement(true);
					fichierCourant = choose.getSelectedFile().getName();
					dossierCourant = choose.getSelectedFile().toString() + ".paint";
					mmFichier = true;
				}
				else {
					String[] choix = {
							"Enregistrer",
							"Ne pas enregistrer",
							"Annuler"
					};
					JOptionPane jop = new JOptionPane();
					int rang = jop.showOptionDialog(null, "Voulez-vous enregistrer les modifications\napportées à " + fichierCourant, "My Mini Paint", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choix, choix[2]);
				
					if(rang == 0) {
						new EnregistrerListener().actionPerformed(new ActionEvent("",  0,  ""));
						Ouvrir ouvrir = new Ouvrir(choose.getSelectedFile().toString());
						pan.setPoints(ouvrir.getPoints());
						pan.setBackgroungColor(ouvrir.getColor());
						pan.setPreiousBackgroungColor(ouvrir.getColor());
						pan.repaint();
						setEnregistrement(true);
						fichierCourant = choose.getSelectedFile().getName();
						dossierCourant = choose.getSelectedFile().toString();
						mmFichier = true;
					}
					else if(rang == 1) {
						Ouvrir ouvrir = new Ouvrir(choose.getSelectedFile().toString());
						pan.setPoints(ouvrir.getPoints());
						pan.setBackgroungColor(ouvrir.getColor());
						pan.setPreiousBackgroungColor(ouvrir.getColor());
						pan.repaint();
						setEnregistrement(true);
						fichierCourant = choose.getSelectedFile().getName();
						dossierCourant = choose.getSelectedFile().toString() + ".paint";
						mmFichier = true;
					}
				}
			}
		}
		
	}
	
	class EnregistrerListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(!mmFichier)
			{
				JFileChooser choose = new JFileChooser(FileSystemView.getFileSystemView().getParentDirectory(new File(dossierCourant.toString())));
				choose.setDialogTitle("Choisissez un repertoire ");
				choose.setFileSelectionMode(JFileChooser.FILES_ONLY);
				choose.setSelectedFile(new File(fichierCourant));
				choose.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter filtre = new FileNameExtensionFilter("Images paint (.paint)", "paint");
				choose.addChoosableFileFilter(filtre);
				
				int res = choose.showSaveDialog(null);
				if(res == JFileChooser.APPROVE_OPTION)
				{
					String s = choose.getSelectedFile().toString().split(".paint")[0];
					new Enregistrer(s,  pan.getPoints(), pan.getBackgroundColor());
					dossierCourant = s + ".paint";
					fichierCourant = choose.getSelectedFile().getName();
					setEnregistrement(true);
					mmFichier = true;
				}
				
			}
			else {
				new Enregistrer(dossierCourant.split(".paint")[0],  pan.getPoints(), pan.getBackgroundColor());
				setEnregistrement(true);
				mmFichier = true;
			}
		}
		
	}
	
	class EnregistrerSousListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			JFileChooser choose = new JFileChooser(FileSystemView.getFileSystemView().getParentDirectory(new File(dossierCourant)));
			choose.setSelectedFile(new File(fichierCourant.split(".paint")[0]));
			choose.setDialogTitle("Choisissez un repertoire ");
			choose.setFileSelectionMode(JFileChooser.FILES_ONLY);
			choose.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filtre = new FileNameExtensionFilter("Images paint (.paint)", "paint");
			choose.addChoosableFileFilter(filtre);
			
			int res = choose.showSaveDialog(null);
			if(res == JFileChooser.APPROVE_OPTION)
			{
				String s = choose.getSelectedFile().toString();
				new Enregistrer(s,  pan.getPoints(), pan.getBackgroundColor());
				fichierCourant = choose.getSelectedFile().getName();
				dossierCourant = s + ".paint";
				setEnregistrement(true);
				mmFichier = true;
			}
		}
		
	}
	
	class EffacerListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			pan.effacer();
			if(fichierCourant.equals("Sans nom")) {
				mmFichier = false;
			}
			else {
				mmFichier = true;
			}
		}
		
	}
	
	class FermerListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String[] choix = {
					"Enregistrer",
					"Ne pas enregistrer",
					"Annuler"
			};
			try {
				File f = new File("AncienChemin.sauvegarde");
				if(f.exists()) {
					f.delete();
					f = new File("AncienChemin.sauvegarde");
				}
				
				FileOutputStream fos = new FileOutputStream(f);
				for(int i = 0; i < dossierCourant.length(); i++)
				{
					fos.write((int)dossierCourant.charAt(i));
				}
				fos.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
			if(dejaEnregistre) {dispose();}
			else {
				JOptionPane jop = new JOptionPane();
				int rang = jop.showOptionDialog(null, "Voulez-vous enregistrer les modifications\napportées à " + fichierCourant.split(".paint")[0], "My Mini Paint", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choix, choix[2]);
			
				if(rang == 0) {
					new EnregistrerListener().actionPerformed(new ActionEvent("",  0,  ""));;
					dispose();
				}
				else if(rang == 1) {
					dispose();
				}
			}
		}
		
	}
	
	class NouveauListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(dejaEnregistre) {
				pan.effacer();
				fichierCourant = "Sans nom";
				mmFichier = false;
			}
			else {
				String[] choix = {
						"Enregistrer",
						"Ne pas enregistrer",
						"Annuler"
				};
				JOptionPane jop = new JOptionPane();
				int rang = jop.showOptionDialog(null, "Voulez-vous enregistrer les modifications\napportées à " + fichierCourant, "My Mini Paint", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choix, choix[2]);
			
				if(rang == 0) {
					new EnregistrerListener().actionPerformed(new ActionEvent("",  0,  ""));;
					pan.effacer();
					fichierCourant = "Sans nom";
					mmFichier = false;
				}
				else if(rang == 1) {
					pan.effacer();
					fichierCourant = "Sans nom";
					mmFichier = false;
				}
			}
			setRetour(false);
			setEnregistrement(true);
		}
		
	}
	
	class RetourListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			pan.retour();
		}
		
	}
	
	class AvancerListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			pan.avancer();
		}
		
	}
	
	
	
	class BackGroundListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			pan.setGomme(false);
			final JColorChooser colorChooser = new JColorChooser();
			final JDialog dialog = JColorChooser.createDialog(null,  "Change button background", true, colorChooser, new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					pan.setPreiousBackgroungColor(pan.getBackgroundColor());
					pan.setBackgroungColor(colorChooser.getColor());
					for(Point p : pan.getPoints()) {
						if(p.getEstGomme()) {
							p.setColor(colorChooser.getColor());
						}
					}
					pan.getFonds().add(new FondCouleur(pan.getPreviousBackgroundColor(),  pan.getBackgroundColor(),  pan.getPoints().size()));
					pan.repaint();
					setAvancer(false);
					setRetour(true);
					setEnregistrement(false);
				}
			}, new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
				}
			}); 
			dialog.setVisible(true);
			
		}
		
	}
	
	class PointeurListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			ModifierPointeur modif = new ModifierPointeur(null, "My Mini Paint", true, pan.getPointerType(), pan.getPointerSize(), pan.getPointerColor());
			if(modif.aEnvoyerInf()) {
				pan.setPointerType(modif.getInfos().getType());
				pan.setPointerSize(modif.getInfos().getPinceauSize());
				pan.setPointerColor(modif.getInfos().getPointeurColor());
				pan.setGomme(false);
			}
		}
		
	}
	
	
	public static void setEnregistrement(boolean b)
	{
		dejaEnregistre = b;
		enregistrer.setEnabled(!b);
		saveFile.setEnabled(!b);
	}

	public static void setRetour(boolean b)
	{
		retour.setEnabled(b);
		back.setEnabled(b);
		
	}
	
	public static void setAvancer(boolean b) {
		avancer.setEnabled(b);
		forward.setEnabled(b);
	}
	
	class AjoutFormeListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			boolean b1 = pan.getACocheContour(),
					b2 = pan.getAcocheRemplissage();
			
			int cont = pan.getTailleContour();
			
			String t = pan.getPointerType();
			
			Color c = pan.getCouleurContour(),
				c2 = pan.getCouleurRempli();
			
			if(c == null) {
				c = Color.black;
			}
			
			if(c2 == null)
			{
				c2 = Color.black;
			}
			
			AjoutForme a = new AjoutForme(null, "My Mini Paint", true, t, b1, b2, cont, c, c2);
			if(a.aEnvoyeInfos()) {
				pan.setACocheContour(a.aCocheContour());
				pan.setACocheRemplissage(a.aCocheRemplissage());
				pan.setPointerType(a.getInfos().getTypeForme());
				if(a.aCocheContour()) {
					pan.setTailleContour(a.getInfos().getTailleContour());
					pan.setCouleurContour(a.getInfos().getCouleurContour());
				}
				else
				{
					pan.setTailleContour(0);
					pan.setCouleurContour(null);
				}
				
				if(a.aCocheRemplissage()) {
					pan.setCouleurRempli(a.getInfos().getCouleurRempli());
				}
				else
				{
					pan.setCouleurRempli(null);
				}
			}
		}
		
	}
	
	
}
