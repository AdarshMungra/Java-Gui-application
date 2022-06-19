package gui;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import javax.swing.JPanel;





public class BresenhamLine extends JPanel  {

	private static final long serialVersionUID = 1L;
	//the x and y values
	// stores current values of x and y coordinates 
		 int x0;
		 int y0;
		 int x1;
		 int y1;
			
	 int thickness;
	 String lineType; //solid ,dotted or dashed
	private Graphics2D ga;
	
	
	//set method for line type(solid, dashed, dotted
		public void setLineType(String s) {
			
			lineType = s;
			repaint();
		}
		
		//set method for line thickness
		public void setLineThickness(int t) {
			thickness = t;
			repaint();
		}
	
		
	//method Bresenham algorithm 
		//method to draw the line using Bresenham line algorithm
		public void plotLineLow(int x_0, int y_0, int x_1, int y_1) {
			
			//ga.setPaint();
		    ga.setStroke(new BasicStroke(thickness));
		    
			int dx, dy, p, x, y;
			int xTemp = x_0; 	//temporary x-coordinate (Used for dotted and dashed lines)
			
		    dx = x_1 - x_0;  
		    dy = y_1 - y_0;  
		    x = x_0;  
		    y = y_0;
		    
		    int yi = 1;			//y-axis increment
		    
		    if (dy < 0) {
	    		
	    		yi = -1;
	    		dy = -dy;
	    	}
		    	
	    	p = (2 * dy) - dx;
	    	
		    while(x <= x_1) {
		    	
		    	if (lineType == "Normal") {
		    		
		    		ga.drawLine(x, y, x, y); //Drawing one point
		    	}
		    	else if (lineType == "Dotted") {
		    		
		    		if (x == x_0) {
		    			
		    			ga.drawLine(x, y, x, y);
		    		}
		    		else if (x == (xTemp + (2 * thickness))){
		    			
		    			xTemp = x;
		    			ga.drawLine(x, y, x, y);
		    		}
		    	}
		    	else if (lineType == "Dashed") {
		    		
		    		if ((x >= xTemp) && ((x - xTemp) <= (4 * thickness))) {
		    			
		    			ga.drawLine(x, y, x, y);
		    		}
		    		else if ((x - xTemp) == ((4 * thickness) + 1)){
		    			
		    			xTemp = x + (2 * thickness);
		    		}
		    	}
		    	
		    	
		        if(p >= 0) {
		        	
		            y = y + yi;  
		            p = p + (2 * dy) - (2 * dx);  
		        }  
		        else {
		        	 
		            p = p + (2 * dy);
		        }  
		        x = x + 1;  
		    }
		}
		
		public void plotLineHigh(int x_0, int y_0, int x_1, int y_1) {
			
			//ga.setPaint(lineColor);
		    ga.setStroke(new BasicStroke(thickness));
		    
		    
			int dx, dy, p, x, y;
			int yTemp = y_0; 	//temporary y-coordinate (Used for dotted and dashed lines)
			
		    dx = x_1 - x_0;  
		    dy = y_1 - y_0;  
		    x = x_0;  
		    y = y_0;
		    
		    int xi = 1;			//x-axis increment
		    
		    if (dx < 0) {
	    		
	    		xi = -1;
	    		dx = -dx;
	    	}
		    	
	    	p = (2 * dx) - dy;
	    	
		    while(y <= y_1) {
		    	
		    	if (lineType == "Normal"){
		    		
		    		ga.drawLine(x, y, x, y); //Drawing one point
		    	}
		    	else if (lineType == "Dotted") {
		    		
		    		if (y == y_0) {
		    			
		    			ga.drawLine(x, y, x, y);
		    		}
		    		else if (y == (yTemp + (2 * thickness))){
		    			
		    			yTemp = y;
		    			ga.drawLine(x, y, x, y);
		    		}
		    	}
		    	else if (lineType == "Dashed") {
		    		
		    		if ((y >= yTemp) && ((y - yTemp) <= (4 * thickness))) {
		    			
		    			ga.drawLine(x, y, x, y);
		    		}
		    		else if ((y - yTemp) == ((4 * thickness) + 1)){
		    			
		    			yTemp = y + (2 * thickness);
		    		}
		    	}
		    	
		        if(p >= 0) {
		        	
		            x = x + xi;  
		            p = p + (2 * dx) - (2 * dy);  
		        }  
		        else {
		        	 
		            p = p + (2 * dx);
		        }  
		        y = y + 1;  
		    }
		}
		
		//Function to cater for all slope cases
		public void plotLine(int x_0, int y_0, int x_1, int y_1) {
			if ((Math.abs(y_1 - y_0)) < (Math.abs(x_1 - x_0))) {
				
				if (x_0 > x_1) {
					
					plotLineLow(x_1, y_1, x_0, y_0);
				}
				else {
					
					plotLineLow(x_0, y_0, x_1, y_1);
				}
			}
			else {
				
				if (y_0 > y_1) {
					
					plotLineHigh(x_1, y_1, x_0, y_0);
				}
				else {
					
					plotLineHigh(x_0, y_0, x_1, y_1);
				}
			}
		}
		
		public BresenhamLine(int x,int y, int x2, int y2,int thickness, String lineType, JPanel dp) {
			
			this.thickness = thickness;
			
			this.lineType = lineType;
			ga = (Graphics2D) dp.getGraphics();
			x0 = x;
			x1 = x2;
			y0 = y;
			y1 = y2;
			plotLine(x0,y0,x1,y1);
			repaint();
		}
}
		