package pl.edu.pw.fizyka.pojava.W¹grodzkiWrzesinskiMichelson;

import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

import solution2.DrawSinusWave;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

import solution2.DrawSinusWave;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class MainFrame extends JFrame {

	DrawablePanel panelMid;	
	private String pattern = "";
	private JFileChooser fileChooser;
	public Color lightColor = Color.black;
	

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	
	public MainFrame() {
		setTitle("Interferometr Michelsona");
		setLayout(new BorderLayout());
		setSize(800, 500);

		// Gorny panel
		JPanel panelTop = new JPanel();
		panelTop.setLayout(new FlowLayout());
		this.add(panelTop, BorderLayout.PAGE_START);

		// Lewy panel
		JPanel panelLeft = new JPanel();
		panelLeft.setLayout(new GridLayout(8, 1));
		JButton rullerButton = new JButton("Dodaj linijke");
		rullerButton.setBounds(0, 50, 100, 50);
		panelLeft.add(rullerButton);
		JMenuBar jmb = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		JMenuItem loadMenuItem = new JMenuItem("Pobierz dane z pliku");
		JMenuItem saveMenuItem = new JMenuItem("Zapisz informacje o symulacji");
		setJMenuBar(jmb);
		jmb.add(menu);
		menu.add(loadMenuItem);
		menu.add(saveMenuItem);
		this.add(panelLeft, BorderLayout.LINE_START);

		// Centralny panel
		panelMid = new DrawablePanel(this);
		panelMid.setLayout(new FlowLayout());
		panelMid.setBackground(Color.pink);
		this.add(panelMid, BorderLayout.CENTER);
		panelMid.setAddedMirrorY(0);

		// Prawy panel
		JPanel panelRight = new JPanel();
		panelRight.setLayout(new GridLayout(8, 1));
		JButton Button7 = new JButton("Wybierz kolor");
		Button7.setBounds(0, 100, 100, 50);
		Button7.addActionListener(new paintComponent());
		panelRight.add(Button7);
		final JSlider slider = new JSlider(JSlider.VERTICAL, 0, 75, 0);
		slider.setValue(75);
		slider.setMajorTickSpacing(1);
		
		
		//Akcje menu
		
		//Odczyt danej z pliku
		ActionListener loadColor = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new java.io.File("."));
				fileChooser.setDialogTitle("Wybierz skad chcesz wczytac informacje o interferencji");
				int result = fileChooser.showDialog(null, "Wybierz");
				if (JFileChooser.APPROVE_OPTION == result) {
					System.out.println("Wybrano plik: " + fileChooser.getSelectedFile());
				} else {
					System.out.println("Nie wybrano pliku");
					return;
				}
				InputStreamReader isr;
				String text = "";
				try {
					isr = new InputStreamReader(
						new FileInputStream(fileChooser.getSelectedFile().getAbsolutePath()),
					    Charset.forName("UTF-8").newDecoder()
					);
					int ch = isr.read();
					while(ch != -1) {
						text += (char)ch;
						ch = isr.read();				     
					}		
				} catch (FileNotFoundException e1) {
					System.out.println("Nie znaleziono pliku");
				} catch (IOException e1) {
					System.out.println("Blad odczytu pliku");
				}
				
				int length = 650 - Integer.parseInt(text);
				
				if(length > 320 && length < 440)
					length = 320;
				
				if(length < 0 || length > 320) {
					System.out.println("Zla wartosc dlugosci fali");
					return;
				}
            	int R = Math.min(Math.max(Math.abs((int)(length*4.75)-755)-255, 0), 255);
            	int G = Math.min(Math.max(511-Math.abs((int)(length*4.75)-512), 0), 255);
            	int B = Math.min(Math.max(511-Math.abs((int)(length*4.75)-1024), 0), 255);
            	
            	lightColor = new Color(R,G,B);
            	panelMid.repaint();

			}
		};
		loadMenuItem.addActionListener(loadColor);
		
		
			
		//Zapis informacji odnoœnie interferencji
	
		ActionListener fileSave = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new java.io.File("."));
				fileChooser.setDialogTitle("Wybierz gdzie chcesz zapisaæ informacje o interferencji");
				int result = fileChooser.showDialog(null, "Wybierz");
				if (JFileChooser.APPROVE_OPTION == result) {
					System.out.println("Wybrano plik: " + fileChooser.getSelectedFile());
				} else {
					System.out.println("Nie wybrano pliku");
					return;
				}

				writeDictation(createStatement(75-slider.getValue()), fileChooser.getSelectedFile().toString());
			}
		};
		saveMenuItem.addActionListener(fileSave);
		

		
		// Akcja suwaka
		ChangeListener setSliderValue = new ChangeListener() {
			
			public void stateChanged(ChangeEvent arg0) {
				int value =75-slider.getValue();				
				panelMid.setAddedMirrorY(value);
				repaint();
			}			
		};
		slider.addChangeListener(setSliderValue);

		panelRight.add(slider);
		this.add(panelRight, BorderLayout.LINE_END);

		// Dolny panel
		JPanel panelBot = new JPanel();
		panelBot.setLayout(new BorderLayout());
		JButton Button8 = new JButton("ON/OFF");
		panelBot.add(Button8, BorderLayout.LINE_END);
		this.add(panelBot, BorderLayout.PAGE_END);
		
	}
	
	//Metody zwi¹zane z mainem
	
	//Metoda informuj¹ca o aktualnym stanie
	String createStatement(int value) 
	{		
		String results;			
		if (value==0 || value==45)
			results="Wygaszenie";
		else if(value==21 || value==66)
			results="Wzmocnienie";
		else 
			results="Stan pomiedzy wzmocnieniem a wygaszeniem";			
		return results;
	}
	
	//Metoda do zapisu informacji o interferencji
	void writeDictation(String results, String fileName) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(fileName);
			fw.write(results);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.exit(1);
		}
		try {
			fw.close();
		} catch (IOException e) {
			System.out.println("BLAD PRZY ZAMYKANIU PLIKU!");
			System.exit(3);
		}
	}
	
	
	
	

	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public class paintComponent implements ActionListener {
		
		public void actionPerformed(ActionEvent arg0) {
			Color newColor = JColorChooser.showDialog(null, "Wybierz kolor", Color.white);
			panelMid.setBackground(newColor);
		}	
	}

	
}