package myMiniPaint;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class APropos extends JDialog{

	
	public APropos(JFrame parent, String title, boolean modal)
	{
		super(parent, title, modal);
		
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setIconImage(null);
		initComponent();
		this.setVisible(true);
		/*JLabel lien = new JLabel();
		lien.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
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
				Desktop.getDesktop().browse(new URI("));
			}
		});*/
	}
	
	private void initComponent()
	{
		JLabel logo = new JLabel(new ImageIcon("images\\logo\\logo11.jpg"));
		JLabel texte1 = new JLabel("<html>My Mini Paint est comme son nom l'indique une mini application de dessin "
				+ "réalisée<br> par JOUNANG-NANA ASTRID et inspirée de<br>Microsoft Paint.</html>");
		
		JLabel texte2 = new JLabel("<html>Pour plus d'informations par rapport à ce produit ou <br>pour toute éventuelle proposition d'idéé d'amélioration, <br>veuillez le faire à l'une des adresses ci-dessous</html>");
		JLabel facebook = new JLabel(new ImageIcon("images\\logo\\facebook.jpg")),
				what = new JLabel(new ImageIcon("images\\logo\\what.jpg")),
				tel = new JLabel(new ImageIcon("images\\logo\\telephone.jpg")),
				mail = new JLabel(new ImageIcon("images\\logo\\mail.jpg")),
				twit = new JLabel(new ImageIcon("images\\logo\\twitter.jpg")),
				tele = new JLabel(new ImageIcon("images\\logo\\telegram.jpg"));
		
		JLabel text3 = new JLabel("Merci d'utiliser le logiciel");
		JLabel text4 = new JLabel("My Mini Paint", JLabel.CENTER);
		text4.setFont(new Font("Arial", Font.BOLD|Font.ITALIC, 12));
		this.getContentPane().setLayout(null);
		
		logo.setBounds(10, 10, 80, 80);
		this.getContentPane().add(logo);
		
		text4.setBounds(5,  95,  80,  20);
		this.getContentPane().add(text4);
		
		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(2,  3));
		pan.add(tel);
		pan.add(what);
		pan.add(mail);
		pan.add(facebook);
		pan.add(tele);
		pan.add(twit);
		
		pan.setBounds(90, 230, 200, 100);
		this.getContentPane().add(pan);
		
		texte1.setBounds(90, 30, 250,  70);
		this.getContentPane().add(texte1);
		
		texte2.setBounds(90,  140,  250,  70);
		this.getContentPane().add(texte2);
		
		text3.setBounds(250, 350, 200, 20);
		this.getContentPane().add(text3);
		
		tel.setToolTipText("+237 6 50 26 04 82");
		tel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		what.setCursor(new Cursor(Cursor.HAND_CURSOR));
		tele.setCursor(new Cursor(Cursor.HAND_CURSOR));
		facebook.setCursor(new Cursor(Cursor.HAND_CURSOR));
		twit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		mail.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		mail.setToolTipText("astridjounang@gmail.com");
		
		facebook.setToolTipText("astrid jounang");
		
		tele.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
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
				try {
					Desktop.getDesktop().browse(new URI("https://t.me/Astrid2x"));
				} catch (IOException | URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		tele.setToolTipText("Astrid Jounang(@Astrid2x)");
		what.setToolTipText("+237 6 50 26 04 82");
		what.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
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
				try {
					Desktop.getDesktop().browse(new URI("https://wa.me/+237650260482"));
				} catch (IOException | URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		twit.setToolTipText("@JounangA");
		twit.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
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
				try {
					Desktop.getDesktop().browse(new URI("https://mobile.twitter.com/JounangA?s=09"));
				} catch (IOException | URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		mail.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
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
				try {
					Desktop.getDesktop().browse(new URI("mailto:astridjounang@gmail.com"));
				} catch (IOException | URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
	}
}
