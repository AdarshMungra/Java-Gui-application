package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;


class DrawPoly {
	
	public Polygon p; 
	public Color color = null;
	public Color colorfill = null;
	
	public DrawPoly(int h, int w,int in, int s, Color c){
		int inputInt;
		int sideSize;
		int x,y;
		p  = new Polygon();
		this.color = c;
		inputInt = in;
		sideSize = s;
		x = w;
		y = h;
		
		double anglePhase = 0;
		double angle = 360/inputInt;
		double radius = sideSize/2;
		int coordx = (int) (x);
		int coordy = (int) ((y) - radius);
		
		angle = ((2*Math.PI)/360)*angle;


		p.addPoint(coordx,coordy);
		
		for(int i = 1; i< inputInt; i++)
		{
			anglePhase = angle *i;
			
			if(anglePhase >= 0 && anglePhase <= Math.PI/2)
			{
				coordx = (int)(radius *(Math.sin(anglePhase)));
				coordy = (int)(radius *(Math.sin((Math.PI/2) - anglePhase)));
				coordx = coordx + (x);
				coordy = (y) - coordy;
				p.addPoint(coordx,coordy);
			}
			else if(anglePhase > (Math.PI)/2 && anglePhase <= Math.PI)
			{
				anglePhase = Math.PI - anglePhase;
				coordx = (int)(radius *(Math.sin(anglePhase)));
				coordy = (int)(radius *(Math.sin((Math.PI/2) - anglePhase)));
				coordx+=(x);
				coordy+=(y);
				p.addPoint(coordx,coordy);
			}
			else if(anglePhase > Math.PI && anglePhase <= (3*Math.PI)/2)
			{
				anglePhase = anglePhase - Math.PI;
				coordx = (int)(radius *(Math.sin(anglePhase)));
				coordy = (int)(radius *(Math.sin((Math.PI/2) - anglePhase)));
				coordx = (x) - coordx;
				coordy+=(y);
				p.addPoint(coordx,coordy);
			}
			else
			{
				anglePhase = (2*Math.PI) - anglePhase;
				coordx = (int)(radius *(Math.sin(anglePhase)));
				coordy = (int)(radius *(Math.sin((Math.PI/2) - anglePhase)));
				coordx =(x) - coordx;
				coordy =(y) - coordy;
				p.addPoint(coordx,coordy);
			}
		}	
	}
	
	public void setColorfill(Color c)
	{
		colorfill = c;
	}
	
	public void setColorFillLine(Color c)
	{
		color = c;
	}
	
	public DrawPoly(int s, int[] arrx, int[] arry, Color c)
	{
		p = new Polygon(arrx,arry,s);
	 	this.color = c;
	}
	
	public void draw(Graphics g)
	{
		Graphics2D ga = (Graphics2D)g;
		ga.setColor(color);
		ga.setStroke(new BasicStroke(3));
		ga.drawPolygon(p);
		//ga.fillPolygon(p);
	}
	
	public void fill(Graphics g)
	{
		Graphics2D ga = (Graphics2D)g;
		ga.setColor(colorfill);
		ga.fillPolygon(p);
	}
	public void fillLine(Graphics g)
	{
		Graphics2D ga = (Graphics2D)g;
		ga.setColor(color);
		ga.setStroke(new BasicStroke(3));
		ga.drawPolygon(p);
		
	}
}