package gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.geom.*;
  
//Extends JPanel class  
public class scale_coordinate extends JPanel{  

	private static final long serialVersionUID = 1L;
	//initialize coordinates  
    int margin = 0;
    int oldW = getWidth();
    int oldH = getHeight();
    
    public scale_coordinate()
    {
    	setBorder(new LineBorder(Color.BLACK,2,true));
    	setBackground(Color.WHITE);
    }

    public int getW()
    {
    	return getWidth();
    }
    public int getH()
    {
    	return getHeight();
    }
    
    @Override
    protected void paintComponent(Graphics grf){  
        //create instance of the Graphics to use its methods  
        super.paintComponent(grf);  
        Graphics2D graph = (Graphics2D)grf;  
          
        //Sets the value of a single preference for the rendering algorithms.  
        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  
          
        // get width and height  
        int width = getWidth();  
        int height = getHeight();  
    	int counter;
    	int i;
    	
        //scale on the -ve y-axis
        int y_corneg=(height/2)+10;
        counter=0;
        i=-5;
        do {
        	graph.setColor(Color.green);
    		graph.draw(new Line2D.Double(margin,y_corneg,width -margin,y_corneg));
        	y_corneg+=10;
        	counter++;
        	if(counter==5) {
        		graph.setColor(Color.black);
        		counter = 0;
            	grf.setFont(new Font("Serif", Font.BOLD, 10));
          	  	grf.drawString(String.valueOf(i),(width/2)-20,y_corneg-7);
          	  	i-=5;
        	}
        }
        while(y_corneg<=height-margin);
        
        //scale on the +ve y-axis
        int y_corpos=(height/2)-10;
        counter=0;
        i=5;
        do {
        	graph.setColor(Color.green);
    		graph.draw(new Line2D.Double(margin,y_corpos,width -margin,y_corpos));
        	y_corpos-=10;
        	counter++;
        	if(counter==5) {
        		counter = 0;
        		graph.setColor(Color.black);
            	grf.setFont(new Font("Serif", Font.BOLD, 10));
          	  	grf.drawString(String.valueOf(i),(width/2)-20,y_corpos+15);
          	  	i+=5;
        	}	
        }
        while(y_corpos>=margin);
        
        //scale on the +ve x-axis
        int x_corposi=(width/2)+10;
        counter=0;
        i=5;
        
        do {
        	graph.setColor(Color.green);
    		graph.draw(new Line2D.Double(x_corposi,margin,x_corposi,height-margin));
        	x_corposi+=10;
        	counter++;
        	if(counter==5) {
            	  counter=0;
            	  graph.setColor(Color.black); 
            	  grf.setFont(new Font("Serif", Font.BOLD, 10));
            	  grf.drawString(String.valueOf(i),x_corposi-13,(height/2)+5+10);
            	  i+=5;           	 
        	}
        }
        while(x_corposi<=width-margin);
        
        //scale on the -ve x-axis
        int x_corneg=(width/2)-10;
        counter=0;
        i=-5;
        do {
        	graph.setColor(Color.green);
    		graph.draw(new Line2D.Double(x_corneg,margin,x_corneg,height-margin));
        	x_corneg-=10;
        	counter++;
        	if(counter==5) {
        		counter = 0;
        		graph.setColor(Color.black);
            	grf.setFont(new Font("Serif", Font.BOLD, 10));
          	  	grf.drawString(String.valueOf(i),x_corneg+3,(height/2)+5+10);
          	  	i-=5;
        	}
        }
        while(x_corneg>=margin);
        
        //arrow for x-axis
        graph.setColor(Color.black);
        graph.draw(new Line2D.Double(width-margin,height/2,width-margin-10,(height/2)-10));
        graph.draw(new Line2D.Double(width-margin,height/2,width-margin-10,(height/2)+10));
        // draw graph  
        graph.draw(new Line2D.Double(width/2, margin, width/2, height-margin));  
        graph.draw(new Line2D.Double(margin, height/2, width-margin, height/2));
        String a="x";//display x-axis
        grf.setFont(new Font("Serif", Font.BOLD, 10));
        grf.drawString(a,width-margin+5,height/2);
        
        //arrow for y-axis
        graph.draw(new Line2D.Double(width/2,margin,(width/2)-10,margin+10));
        graph.draw(new Line2D.Double(width/2,margin,(width/2)+10,margin+10));
        String b="y";
        grf.setFont(new Font("Serif", Font.BOLD, 10));
        grf.drawString(b,width/2,margin-10);
    }         
}  