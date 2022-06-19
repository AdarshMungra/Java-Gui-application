package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JPanel;



public class DDA {
	 // stores current values of x and y coordinates 
		int x1;
	    int y1;
	    int x2;
	    int y2;
	    
		JPanel drawingpanel;
	    int thickness;
		private Color lineColor;
		String lineType;
		private Graphics2D g2D;
	    
		//set method for line type(solid, dashed, dotted
	    public void setLineType(String s) {
			lineType = s;
			//repaint();
		}
		
	    //set method for line thickness
		public void setLineThickness(int t) {
			thickness = t;
			//repaint();
		}
		
		//set method for line color
		public void setLineColor(Color c) {
			this.lineColor = c;
			//repaint();
		}
	    
		//This method will scale the line after it has been drawn.
	    public DDA(int x_1, int y_1,int x_2, int y_2, int thickness,Color lineColor, String lineType,JPanel dp){
	    	g2D = (Graphics2D) dp.getGraphics();
	    	this.x1 = x_1;
	    	this.y1 = y_1;
	    	this.x2 = x_2;
	    	this.y2 = y_2;
			this.thickness = thickness;
			//this.lineColor = lineColor;
			this.lineType = lineType;
			ddaLine(x1,y1,x2,y2);
	    }
		
	    //method to draw the line using dda line algorithm
		public void ddaLine(int x1, int y1,int x2, int y2) {
			
	        g2D.setPaint(lineColor); //use graphics to paint the line
	        g2D.setStroke(new BasicStroke(thickness));// increase thickness
	        
		    int dx = x2 - x1; //change in x 
			int dy = y2 - y1;// change in y
			int steps;
			
			//this if statement will set value for steps(length of which the line has to be drawn)
			if (Math.abs(dx) > Math.abs(dy)) {
				steps = Math.abs(dx);
			}
			else {
				steps = Math.abs(dy);
			}
			
			//Increments to get new value of x and y at each step
			float xInc = (float) dx / (float) steps;
			float yInc = (float) dy/ (float) steps;
			
		
		
			float x= x1, y=y1;
			
			//variables for dotted line which will store the x and y coordinates previously drawn
			float xTemp = x1;
			float yTemp = y1;
			
			//variable for dashed line which will store length of dash
			int length_of_dash = 0;
			
			
			
			//loop from initial coordinates until steps are completed
			for(int i=1;i<=steps;i++) {
				
				//condition if line type is solid
				if (lineType == "Normal") {
					x += xInc;
					y += yInc;
					g2D.drawLine((int)x,(int)y,(int)x,(int)y);
				}
				
				//condition if line type is dotted
				else if (lineType == "Dotted") {
					
					if (x == (xTemp + (2 * thickness))||x == (xTemp - (2 * thickness))||y == (yTemp + (2 * thickness))||y == (yTemp - (2 * thickness))){
		    			
						xTemp = x;
						yTemp = y;
		    			g2D.drawLine((int)x,(int) y,(int) x,(int) y); 
		 
		    		} 
					
	    			x += xInc;
	    			y += yInc;
				}
				
				//condition if line type is dotted
				else if (lineType == "Dashed") {
					
					//increment value of x and y to get next coordinates
					x += xInc;
	    			y += yInc;
					
	    			//check if length of dash required has not yet been drawn
					if(length_of_dash <  3*thickness) {
						
						
		    			length_of_dash += 1;//add 1 to length of dash
		    			
		    			g2D.drawLine((int)x,(int) y,(int) x,(int) y);// draw pixel on the screen
		    			
					}
					//if required length of dash is reached, then the condition below will subtract length of dash with one until it reaches 0
					//It will also ensure that there is a space(same length of dash) between each dash
					else if(length_of_dash == 3*thickness) {
						while(length_of_dash != 0) {
							
							//increment value of x and y to get next coordinates
							x += xInc;
			    			y += yInc;
			    			
			    			length_of_dash -= 1;//decrement length of dash by 1
			    			
			    			i++; //increment i 
						}
						
						
					}
						
				}
			}
		}

}