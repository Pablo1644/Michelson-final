package pl.edu.pw.fizyka.pojava.W¹grodzkiWrzesinskiMichelson;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class SineDraw {
	float a = 1,b = 0;
	public void Update(float x1, float x2, float y1, float y2) {
		a = (y1 - y2) / (x1 - x2);
		b = y1 - a * x1;
	}
	public double f(double x) {
        return Math.sin(x*Math.PI/180);
    }
	
	public void LeftHorizontalDraw (Graphics2D g2, Point2D.Double begginPoint, Point2D.Double endPoint, 
			double amplitude, double frequency, Color color, boolean reverse)
	{
		g2.setColor(color);
		g2.setStroke(new BasicStroke(2));
		double x = begginPoint.getX();
		double endX = endPoint.getX();
		double begY = begginPoint.getY();
		Point2D.Double point = new Point2D.Double();
		Line2D.Double line = new Line2D.Double();
		double y;
		
		while (x < endX)
		{
			y=amplitude*f(x*frequency);
			if(reverse) y*= -1;
			y += begY;
			if((int)y >= (int)(x*a + b))
				break;
			point.setLocation(x, y);
			line.setLine(point,point);
			g2.draw(line);
			x=x+0.5;
		}	
	}
	

	public void RightHorizontalDraw (Graphics2D g2, Point2D.Double begginPoint, Point2D.Double endPoint, double amplitude, double frequency, Color color, boolean reverse)
	{
		g2.setColor(color);
		g2.setStroke(new BasicStroke(2));
		double x = begginPoint.getX();
		double endX = endPoint.getX();
		double begY = begginPoint.getY();
		Point2D.Double point = new Point2D.Double();
		Line2D.Double line = new Line2D.Double();
		double y;
		
		while (x > endX)
		{
			y=amplitude*f(x*frequency);
			if(reverse) y*= -1;
			y += begY;
			if((int)y <= (int)(x*a + b))
				break;
			point.setLocation(x, y);
			line.setLine(point,point);
			g2.draw(line);
			x=x-0.5;
		}		
	}
	
	public void TopVerticalDraw (Graphics2D g2, Point2D.Double begginPoint, Point2D.Double endPoint,
			double amplitude, double frequency, Color color,double phase, boolean reverse)
	{
		g2.setColor(color);
		g2.setStroke(new BasicStroke(2));
		double y = begginPoint.getY();
		double endY = endPoint.getY();
		double begX = begginPoint.getX();
		Point2D.Double point = new Point2D.Double();
		Line2D.Double line = new Line2D.Double();
		double x;
		
		while (y < endY)
		{
			x=amplitude*f((y-begginPoint.getY()+phase)*frequency);
			if(reverse) x*= -1;
			x += begX;
			if((int)y >= (int)(x*a + b))
				break;
			point.setLocation(x, y);
			line.setLine(point,point);
			g2.draw(line);
			y=y+0.5;
		}		
	}
	
	public void BotVerticalDraw (Graphics2D g2, Point2D.Double begginPoint, Point2D.Double endPoint,
			double amplitude, double frequency, Color color,double phase, boolean reverse)
	{
		g2.setColor(color);
		g2.setStroke(new BasicStroke(2));
		double y = endPoint.getY();
		double endY = begginPoint.getY();
		double begX = begginPoint.getX();
		Point2D.Double point = new Point2D.Double();
		Line2D.Double line = new Line2D.Double();
		double x;
		
		while (y > endY)
		{
			x=amplitude*f((y-endPoint.getY()+phase)*frequency);
			if(reverse) y*= -1;
			x += begX;
			if((int)y <= (int)(x*a + b))
				break;
			point.setLocation(x, y);
			line.setLine(point,point);
			g2.draw(line);
			y-=0.5;
		}		
	}

	

	public void reverseHorizontalDraw (Graphics2D g2, Point2D.Double begginPoint, Point2D.Double endPoint, double amplitude, double frequency, Color color)
	{
		g2.setColor(color);
		g2.setStroke(new BasicStroke(2));
		double x = begginPoint.getX();
		double endX = endPoint.getX();
		double begY = begginPoint.getY();
		Point2D.Double point = new Point2D.Double();
		Line2D.Double line = new Line2D.Double();
		double y;
		
		while (x > endX)
		{
			y=-amplitude*f(x*frequency)+begY;
			point.setLocation(x, y);
			line.setLine(point,point);
			g2.draw(line);
			x=x-0.5;
		}		
	}
	
	public void verticalDraw (Graphics2D g2, Point2D.Double begginPoint, Point2D.Double endPoint,
			double amplitude, double frequency, Color color,double phase)
	{
		g2.setColor(color);
		g2.setStroke(new BasicStroke(2));
		double y = begginPoint.getY();
		double endY = endPoint.getY();
		double begX = begginPoint.getX();
		Point2D.Double point = new Point2D.Double();
		Line2D.Double line = new Line2D.Double();
		double x;
		
		while (y < endY)
		{
			x=amplitude*f((y-begginPoint.getY()+phase)*frequency)+begX;
			point.setLocation(x, y);
			line.setLine(point,point);
			g2.draw(line);
			y=y+0.5;
		}		
	}
	
	public void reverseVerticalDraw (Graphics2D g2, Point2D.Double begginPoint, Point2D.Double endPoint, double amplitude, double frequency, Color color)
	{
		g2.setColor(color);
		g2.setStroke(new BasicStroke(2));
		double y = begginPoint.getY();
		double endY = endPoint.getY();
		double begX = begginPoint.getX();
		Point2D.Double point = new Point2D.Double();
		Line2D.Double line = new Line2D.Double();
		double x;
		
		while (y > endY)
		{
			x=amplitude*f(y*frequency)+begX;
			point.setLocation(x, y);
			line.setLine(point,point);
			g2.draw(line);
			y=y-0.5;
		}		
	}
}
