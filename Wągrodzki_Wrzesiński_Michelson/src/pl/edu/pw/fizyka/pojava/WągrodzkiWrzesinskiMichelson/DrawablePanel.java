package pl.edu.pw.fizyka.pojava.WągrodzkiWrzesinskiMichelson;

import java.util.*;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import java.util.*;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class DrawablePanel extends JPanel {
	MainFrame parent;
	static int centerX = 300, centerY = 200;
	static int width = 400, height = 300;
	private SineDraw sD = new SineDraw();
	private int addedMirrorY = 0;

	DrawablePanel(MainFrame parent){
		this.parent = parent;
	}
	
	public void paint(Graphics polygon) {
		super.paintComponent(polygon);
		Graphics2D polygon2d = (Graphics2D) polygon;

		height = this.getHeight();
		width = this.getWidth();
		centerX = width / 2;
		centerY = height / 2;
		int mirrorX0 = centerX - width / 8;
		int mirrorX1 = centerX + width / 8;
		int mirrorY = centerY - 2 * height / 5 - 20;
		sD.Update(centerX - width / 8,  centerX + width / 8, centerY +height / 8, centerY - height / 8);
		// Ruchome Lustro
		polygon2d.drawLine(mirrorX0, mirrorY + getAddedMirrorY(), mirrorX1, mirrorY + getAddedMirrorY());

		// Ustawienie grubosci linii
		polygon2d.setStroke(new BasicStroke(1));

		// Ustawienie koloru elementow konstrukcyjnych
		polygon2d.setColor(Color.BLACK);

		// Linia Odniesienia
		polygon2d.drawLine(centerX + 2 * width / 5, centerY - height / 6, centerX + 2 * width / 5,
				centerY + height / 6);

		// Zrodlo swiatla
		polygon2d.setColor(Color.RED);
		polygon2d.fillRect(0, centerY - height / 4, width / 16, height / 2);

		// Soczewka przy zrodle
		polygon2d.setColor(Color.BLACK);
		polygon2d.drawRect(centerX - width / 8, centerY - height / 2, width / 4, centerY - height / 2);

		// Detektor
		polygon2d.setColor(Color.ORANGE);
		polygon2d.fillRect(centerX - width / 8, height - height / 8, width / 4, height / 8);

		// Soczewka przy detektorze
		polygon2d.setColor(Color.BLACK);
		polygon2d.setStroke(new BasicStroke(2.0f));
		polygon2d.drawLine(centerX - width / 40, height - height / 6, centerX + width / 40, height - height / 6);

		// Rysowanie zwierciadla,przez ktore przechodzi fala
		polygon2d.drawLine(width / 14, centerY + height / 30, width / 14, centerY - height / 30);
		polygon2d.drawLine(centerX - width / 8, centerY +height / 8, centerX + width / 8, centerY - height / 8);

		// Rysowanie fal sinusoidalnych

		// Fala ze zrodla
		sD.LeftHorizontalDraw(polygon2d, new Point2D.Double(width / 16, centerY),
				new Point2D.Double(centerX, centerY), 50, 4, parent.lightColor, false);

		// Fala po przejsciu przez zwierciadlo srodkowe
		sD.RightHorizontalDraw(polygon2d, new Point2D.Double(centerX + 2 * width / 5, centerY ),
				new Point2D.Double(centerX- width/8, centerY), 25, 4, Color.BLACK, false); //

		// Fala odbita od zwierciadła prawego
		sD.RightHorizontalDraw(polygon2d, new Point2D.Double(centerX + 2 * width / 5, centerY),
				new Point2D.Double(centerX - width/8, centerY), 25, 4, Color.red, true);

		// Fala odbita od zwierciadla środkowego
		sD.TopVerticalDraw(polygon2d, new Point2D.Double(centerX, mirrorY + getAddedMirrorY()),
				new Point2D.Double(centerX, centerY + height/8), 25, 4, Color.BLACK, 180 - getAddedMirrorY(), false);
		
		//180-get.....- faza i teraz trza by było dodać ify
		//Czyli jak np. 160-180-Wzmocnienie
		//160-20 -Nic nie zaszło
		//20-0 wygaszenia

		// Fala odbita od zwierciadła górnego
		sD.TopVerticalDraw(polygon2d, new Point2D.Double(centerX, mirrorY + getAddedMirrorY()),
				new Point2D.Double(centerX, centerY + height/8), 25, 4, Color.red, getAddedMirrorY(), false);

		// Fala po interferencji
		double modifiedAmplitude;
		double mirrorPosition=(double) getAddedMirrorY();
		mirrorPosition=mirrorPosition*4;
		modifiedAmplitude=25*(sD.f(mirrorPosition)+sD.f(180-mirrorPosition));
		sD.BotVerticalDraw(polygon2d, new Point2D.Double(centerX, centerY - height/8),
				new Point2D.Double(centerX, height - height / 6), modifiedAmplitude, 4, Color.WHITE, 0, false);

	}


	
	
	public int getAddedMirrorY() {
		return addedMirrorY;
	}



	public void setAddedMirrorY(int addedMirrorY) {
		this.addedMirrorY = addedMirrorY;
	}
}