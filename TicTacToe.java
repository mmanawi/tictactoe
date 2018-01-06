package tictactoe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TicTacToe extends JFrame {

	private JPanel pfelder, pbutton;
	private JButton bEnde, bNeuesSpiel;
	private JLabel jl;
	private List<JButton> jbl;

	private String spieler = "X";
	private String sLbl;
	private int anzahlFelder = 9;
	
	// Konstruktor:
	public TicTacToe() {

		super("Tic Tac Toe");
		setSize(500, 600);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(6, 1));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initComponents();

		setVisible(true);
	}

	// Getter und Setter 
	public String getSpieler() {
		return spieler;
	}
	public void setSpieler(String spieler) {
		this.spieler = spieler;
	}

	// Initialiserung der Komponente
	public void initComponents() {

		Container c = getContentPane();
		c.setBackground(Color.blue);

		// Panel 1 für Spiel Buttons
		pfelder = new JPanel();
		pfelder.setLayout(new GridLayout(3, 3, 6, 6));
		c.add(pfelder, BorderLayout.CENTER);

		// Spiel-Buttons 1-9:
		jbl = new ArrayList<JButton>();

		// Neun Buttons für Spiel erzeugen und diese 
		// und diese gleichzeitig in der List<JButton> adden:

		for (int i = 0; i < anzahlFelder; i++) {
			jbl.add(new JButton());
		}

		// für ActionListener registrieren und in das entsprechende Panel adden:
		for (JButton jb : jbl) {
			jb.addActionListener(new MyActionListeners());
			pfelder.add(jb);
		}

		// panel 2 für weitere Buttons:
		pbutton = new JPanel();
		c.add(pbutton, BorderLayout.SOUTH);

		// Label:
		jl = new JLabel();
		sLbl = "SpielerIn (" + getSpieler() + ") ist dran!";
		jl.setText(sLbl);
		jl.setForeground(Color.blue);
		jl.setFont(jl.getFont().deriveFont(Font.BOLD , 15));
		pbutton.add(jl);

		// Button für neues Spiel:
		bNeuesSpiel = new JButton("Neues Spiel");
		bNeuesSpiel.setForeground(Color.blue);
		bNeuesSpiel.setFont(bNeuesSpiel.getFont().deriveFont(Font.BOLD , 12));
		bNeuesSpiel.addActionListener(new MyActionListeners());
		pbutton.add(bNeuesSpiel);

		// Button Ende
		bEnde = new JButton("Ende");
		bEnde.setForeground(Color.blue);
		bEnde.setFont(bEnde.getFont().deriveFont(Font.BOLD , 12));
		bEnde.addActionListener(new MyActionListeners());
		pbutton.add(bEnde);
	}

	// Eine innere Klasse, die das Interface ActionListener implementiert:
	private class MyActionListeners implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent eve) {

			// Button Neues Spiel:
			if (eve.getSource() == bNeuesSpiel) {

		       for(JButton jb : jbl){
		
			       jb.setText("");
			       jb.setEnabled(true);
		           jl.setText("SpielerIn (" + getSpieler() + ") ist dran!");
		       }
			}
			// Button Ende
			if (eve.getSource() == bEnde) {

				int me = javax.swing.JOptionPane.showConfirmDialog(null, "Möchten wirklich abbrechen?", "Frage:",
						 javax.swing.JOptionPane.YES_NO_OPTION);
				if (me == 1) {
					// DO NOTHING
				} else {
					System.exit(0);
				}
			}

		    Font schrift = getFont().deriveFont(Font.BOLD , 60);

			// Spiel-Buttons 1-9 in einer for each schleife
			for (JButton jb : jbl) {

			    jb.setFont(schrift);
				if (eve.getSource() == jb) {
				   jb.setText(getSpieler());
				   if (getSpieler() == "X") {
						setSpieler("O");
			   } else {
				  setSpieler("X");
			   }

                    jb.setEnabled(false);
		            jl.setText("SpielerIn (" + getSpieler() + ") ist dran!");

		            // Die Mehtode gewinner aufrufen (siehe unten):
					gewinner();
				}
			}
		}
	}
    
	public void gewinner(){
		
		// Zuerst prüfen, ob eine entsprechende Reihe gleichen Wert (X oder O) haben:
	    if((jbl.get(0).getText().length() > 0 && jbl.get(0).getText() == jbl.get(1).getText() &&  jbl.get(0).getText()  == jbl.get(2).getText()) ||
		   (jbl.get(0).getText().length() > 0 && jbl.get(0).getText() == jbl.get(3).getText() &&  jbl.get(0).getText()  == jbl.get(6).getText()) ||
		   (jbl.get(0).getText().length() > 0 && jbl.get(0).getText() == jbl.get(4).getText() &&  jbl.get(0).getText()  == jbl.get(8).getText()) ||
		   (jbl.get(1).getText().length() > 0 && jbl.get(1).getText() == jbl.get(4).getText() &&  jbl.get(1).getText()  == jbl.get(7).getText()) ||
		   (jbl.get(2).getText().length() > 0 && jbl.get(2).getText() == jbl.get(5).getText() &&  jbl.get(2).getText()  == jbl.get(8).getText()) ||
		   (jbl.get(2).getText().length() > 0 && jbl.get(2).getText() == jbl.get(4).getText() &&  jbl.get(2).getText()  == jbl.get(6).getText()) ||
		   (jbl.get(3).getText().length() > 0 && jbl.get(3).getText() == jbl.get(4).getText() &&  jbl.get(3).getText()  == jbl.get(5).getText()) ||
		   (jbl.get(6).getText().length() > 0 && jbl.get(6).getText() == jbl.get(7).getText() &&  jbl.get(6).getText()  == jbl.get(8).getText())) 
	    {
	    	
	    	   // Wenn ja, dann den Gewinner ermitteln und in einer Dialogbox verkünden:
		       for (JButton jb : jbl) {
		    	   jb.setEnabled(false);
		       }
			   if(getSpieler() == "O") setSpieler("X"); else setSpieler("O");
			   sLbl = "SpielerIn: (" + getSpieler() + ") hat gewonnen!"; 
		       jl.setText(sLbl);
		       JOptionPane.showMessageDialog(null, sLbl , "GewinnerIn:", JOptionPane.INFORMATION_MESSAGE);

		} else { 
			
			  //wenn nicht, zusätzlich prüfen, ob alle Felder voll sind:
		      boolean isVoll = true;

		      for(int i = 0; i<anzahlFelder; i++){
			      if(jbl.get(i).getText().length() == 0) isVoll = false;
		      }

		      if(isVoll == true){ 
			     sLbl = "Das Spiel ist unentschieden zu Ende!";
		         jl.setText(sLbl);
		         JOptionPane.showMessageDialog(null, sLbl , "Info", JOptionPane.WARNING_MESSAGE);
		      }
		}
	}
	public static void main(String[] args) {

		new TicTacToe();
	}
}