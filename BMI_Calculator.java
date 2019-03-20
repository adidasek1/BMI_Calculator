//BMI_Calculator
//@Adam Tomaszewski, Politechnika Koszalińska, Koszalin 2019

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BMI_Calculator {

	private JFrame frmKalkulatorBmi;
	private JTextField imieField;
	private JTextField wiekField;
	private JTextField wzrostField;
	private JTextField wagaField;
	
	String height, weight, name, pregnant, age;
	double heightD, weightD, ageD, BMI;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BMI_Calculator window = new BMI_Calculator();
					window.frmKalkulatorBmi.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Tworzenie aplikacji
	 */
	public BMI_Calculator() {
		initialize();
	}

	/**
	 * Inicjalizacja zawartości ramki
	 */
	private void initialize() {
		frmKalkulatorBmi = new JFrame();
		frmKalkulatorBmi.setTitle("BMI calculator");
		frmKalkulatorBmi.setBounds(100, 100, 450, 300);
		frmKalkulatorBmi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmKalkulatorBmi.getContentPane().setLayout(null);
		
		imieField = new JTextField();
		imieField.setBounds(52, 48, 116, 22);
		frmKalkulatorBmi.getContentPane().add(imieField);
		imieField.setColumns(10);
		
		wiekField = new JTextField();
		wiekField.setBounds(236, 48, 116, 22);
		frmKalkulatorBmi.getContentPane().add(wiekField);
		wiekField.setColumns(10);
		
		wzrostField = new JTextField();
		wzrostField.setBounds(52, 103, 116, 22);
		frmKalkulatorBmi.getContentPane().add(wzrostField);
		wzrostField.setColumns(10);
		
		wagaField = new JTextField();
		wagaField.setBounds(236, 103, 116, 22);
		frmKalkulatorBmi.getContentPane().add(wagaField);
		wagaField.setColumns(10);
		
		JLabel lblKalkulatorBmi = new JLabel("BMI calculator");
		lblKalkulatorBmi.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblKalkulatorBmi.setBounds(144, 0, 132, 35);
		frmKalkulatorBmi.getContentPane().add(lblKalkulatorBmi);
		
		JLabel imie = new JLabel("Name");
		imie.setFont(new Font("Tahoma", Font.PLAIN, 15));
		imie.setBounds(92, 32, 56, 16);
		frmKalkulatorBmi.getContentPane().add(imie);
		
		JLabel wiek = new JLabel("Age");
		wiek.setFont(new Font("Tahoma", Font.PLAIN, 15));
		wiek.setBounds(277, 32, 56, 16);
		frmKalkulatorBmi.getContentPane().add(wiek);
		
		JLabel wzrost = new JLabel("Height");
		wzrost.setFont(new Font("Tahoma", Font.PLAIN, 15));
		wzrost.setBounds(81, 83, 56, 16);
		frmKalkulatorBmi.getContentPane().add(wzrost);
		
		JLabel waga = new JLabel("Weight");
		waga.setFont(new Font("Tahoma", Font.PLAIN, 15));
		waga.setBounds(277, 83, 56, 16);
		frmKalkulatorBmi.getContentPane().add(waga);
		
		JTextPane textPaneWynik = new JTextPane();
		textPaneWynik.setBackground(SystemColor.inactiveCaptionBorder);
		textPaneWynik.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textPaneWynik.setBounds(12, 167, 408, 73);
		frmKalkulatorBmi.getContentPane().add(textPaneWynik);
		
		JLabel plec = new JLabel("Sex :");
		plec.setFont(new Font("Tahoma", Font.PLAIN, 15));
		plec.setBounds(12, 138, 42, 16);
		frmKalkulatorBmi.getContentPane().add(plec);
		
		JRadioButton plecMan = new JRadioButton("Man");
		plecMan.setBounds(62, 134, 89, 25);
		frmKalkulatorBmi.getContentPane().add(plecMan);
		
		JRadioButton plecWoman = new JRadioButton("Woman");
		plecWoman.setBounds(175, 134, 71, 25);
		frmKalkulatorBmi.getContentPane().add(plecWoman);
		
		JCheckBox ciaza = new JCheckBox("pregnant");
		ciaza.setEnabled(false);
		ciaza.setBounds(259, 134, 62, 25);
		frmKalkulatorBmi.getContentPane().add(ciaza);
		
		JButton przyciskOblicz = new JButton("Calculate");
		przyciskOblicz.setForeground(Color.BLACK);
		przyciskOblicz.setBackground(Color.LIGHT_GRAY);
		przyciskOblicz.setBounds(323, 134, 97, 25);
		frmKalkulatorBmi.getContentPane().add(przyciskOblicz);
		
		JLabel lblCm = new JLabel("cm");
		lblCm.setBounds(179, 106, 56, 16);
		frmKalkulatorBmi.getContentPane().add(lblCm);
		
		JLabel lblKg = new JLabel("kg");
		lblKg.setBounds(364, 106, 56, 16);
		frmKalkulatorBmi.getContentPane().add(lblKg);
		
		SimpleAttributeSet attribs = new SimpleAttributeSet();
		StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_LEFT);
		textPaneWynik.setParagraphAttributes(attribs, true);
		textPaneWynik.setText( "Hello ! Provide your data to calculate the BMI (Body Mass Index).");
		
		/* Po zaznaczeniu płci "Woman" pojawia się dodatkowa opcja do zaznaczenia: "pregnant"
		 * oraz nie będzie można zaznaczyć opcji "Man" 
		 */
		
		plecWoman.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				boolean radio = plecWoman.isSelected();
		        if (radio == true) {	
		         ciaza.setEnabled(true);
		         plecMan.setEnabled(false);}
		        else {
		        	ciaza.setEnabled(false);
			        plecMan.setEnabled(true);
		        }
		     }
		});
		
		// Po zaznaczeniu płci "Man" nie będzie można zaznaczyć opcji "Woman" oraz "pregnant"
		 
		plecMan.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				boolean radio = plecMan.isSelected();
		        if (radio == true) {	
		         ciaza.setEnabled(false);
		         plecWoman.setEnabled(false);}
		        else{
		        	ciaza.setEnabled(false);
			        plecWoman.setEnabled(true);
		        	}
		     }
		});
		
		
		// Fragment kodu zaczerpnięty od kolegi Sebastiana Matyszewskiego
		// Nasłuchiwanie przycisku "Calculate" a po naciśnięciu obliczenie BMI
				przyciskOblicz.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ciaza.setEnabled(false);
						boolean radio = plecMan.isSelected();
						
						//pobranie odpowiedzi od użytkownika
						age = wiekField.getText();
						weight = wagaField.getText();
						height = wzrostField.getText();
						pregnant = ciaza.getText();
						name = imieField.getText();
						boolean pregnant2 = ciaza.isSelected();
					    
					    
					    try {
					    	// Zamiana string na double
					    	heightD = Double.parseDouble(height)/100;
					        weightD = Double.parseDouble(weight);
					        ageD = Double.parseDouble(age);
					        BMI = weightD / (heightD*heightD);
					    
					    
				//	    finally {
					    	//Współczynnik BMI dla mężczyzn
					    	if (ageD >=14 & ageD <=80) {
					    	if (radio == true ) {

					        if (BMI < 16.5) {
					        	textPaneWynik.setBackground(Color.red);
					        	textPaneWynik.setText( "Hello " + name + "\nYour BMI is: " + BMI +"\nYOU ARE MALNOURISHED! NECESSARILY consult your doctor!");
					        
					        } else if (BMI >= 16.5 && BMI <18.5) {
					        	textPaneWynik.setBackground(Color.yellow);
					        	textPaneWynik.setText( "Hello " + name + "\nYour BMI is: " + BMI + "\nYou are underweight! You must eat a lot more !");
					   
					        } else if (BMI >= 18.5 && BMI <25) {
					        	textPaneWynik.setBackground(Color.green);
					        	textPaneWynik.setText( "Hello " + name + "\nYour BMI is: " + BMI +"\nNormal weight! Bravo! Keep it up !");
					        	
					        } else if (BMI >= 25 && BMI <30) {
					        	textPaneWynik.setBackground(Color.orange);
					        	textPaneWynik.setText( "Hello " + name + "\nYour BMI is: " + BMI + "\nYou are overweight! Do not eat in the evenings !");
					        	
					        } else if (BMI >= 30 && BMI <35) {
					        	textPaneWynik.setBackground(Color.red);
					        	textPaneWynik.setText( "Hello " + name + "\nYour BMI is: " + BMI + "\nYou have obesity class I ! Leave sweets and stop eating fast food !");
					        	
					        } else if (BMI >= 35 && BMI <40) {
					        	textPaneWynik.setBackground(Color.red);
					        	textPaneWynik.setText( "Hello " + name + "\nYour BMI is: " + BMI + "\nYou have obesity class II (clinical obesity)! You have to go on a strict diet !");
					         
					        } else if (BMI >40) {
					        	textPaneWynik.setBackground(Color.red);
					        	textPaneWynik.setText( "Hello " + name + "\nYour BMI is: " + BMI + "\nYou have obesity class III (EXTREME obesity) !! NECESSARILY to consult a doctor !");
					        }
					    	
					    }else {
					    	//Współczynnik BMI dla Kobiet
					    	if(pregnant2 == false) {
					    		
					    		 if (BMI < 16.5) {
							        	textPaneWynik.setBackground(Color.red);
							        	textPaneWynik.setText( "Hello " + name + "\nYour BMI is: " + BMI +"\nYOU ARE MALNOURISHED! NECESSARILY consult your doctor!");
							        
							        } else if (BMI >= 16.5 && BMI <18.5) {
							        	textPaneWynik.setBackground(Color.yellow);
							        	textPaneWynik.setText( "Hello " + name + "\nYour BMI is: " + BMI + "\nYou are underweight! You must eat a lot more !");
							   
							        } else if (BMI >= 18.5 && BMI <25) {
							        	textPaneWynik.setBackground(Color.green);
							        	textPaneWynik.setText( "Hello " + name + "\nYour BMI is: " + BMI +"\nNormal weight! Bravo! Keep it up !");
							        	
							        } else if (BMI >= 25 && BMI <30) {
							        	textPaneWynik.setBackground(Color.orange);
							        	textPaneWynik.setText( "Hello " + name + "\nYour BMI is: " + BMI + "\nYou are overweight! Do not eat in the evenings !");
							        	
							        } else if (BMI >= 30 && BMI <35) {
							        	textPaneWynik.setBackground(Color.red);
							        	textPaneWynik.setText( "Hello " + name + "\nYour BMI is: " + BMI + "\nYou have obesity class I ! Leave sweets and stop eating fast food !");
							        	
							        } else if (BMI >= 35 && BMI <40) {
							        	textPaneWynik.setBackground(Color.red);
							        	textPaneWynik.setText( "Hello " + name + "\nYour BMI is: " + BMI + "\nYou have obesity class II (clinical obesity)! You have to go on a strict diet !");
							         
							        } else if (BMI >40) {
							        	textPaneWynik.setBackground(Color.red);
							        	textPaneWynik.setText( "Hello " + name + "\nYour BMI is: " + BMI + "\nYou have obesity class III (EXTREME obesity) !! NECESSARILY to consult a doctor !");
							        }
					    		 }else {
					    		textPaneWynik.setText( "The BMI index is not recommended for pregnant women.");
					    		
					    	}
					    	
					    }
					}
					    	else if (ageD>0 && ageD<14) {
					    		textPaneWynik.setText( "For children, check the percentile grid !");
								textPaneWynik.setBackground(Color.YELLOW);
					    	}
					    	
					    	else if (ageD>80 && ageD<=150) {
					    		textPaneWynik.setBackground(Color.YELLOW);
					    		textPaneWynik.setText( "The use of BMI for seniors is not recommended !");
							}
					    	
					    	else  if ( (ageD <= 0) ||(ageD > 150) || (weightD < 0) || (heightD < 0) ){
					    		textPaneWynik.setBackground(Color.ORANGE);
					    		textPaneWynik.setText( "Enter the correct data!");
						}
					    
					    	
					  } // koniec try
					    
					
				//	} 
				    catch(Exception ex){
				    	textPaneWynik.setBackground(Color.RED);
				    	textPaneWynik.setText( "Enter correct data!");
				    } // koniec catch
					
					}// koniec public void actionPerformed(ActionEvent e)
				}); // koniec przyciskOblicz.addActionListener(new ActionListener 
		
	}
}
