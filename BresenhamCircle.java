package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class BresenhamCircle extends JPanel implements MouseListener {
	
	private static final long serialVersionUID = 1L;
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtR;
	private JPanel drawingpanel;
	int x;
	int y;
	int r;
	int w = 973;
	int h = 442;

	ArrayList<Integer> x_prev_circle = new ArrayList<Integer>();
	ArrayList<Integer> y_prev_circle = new ArrayList<Integer>();
	ArrayList<Integer> r_prev_circle = new ArrayList<Integer>();

	
	public BresenhamCircle(JPanel dp) {
		
		drawingpanel = dp;
		/*
		x = ((scale_coordinate) drawingpanel).getW()/2;
		y = ((scale_coordinate) drawingpanel).getH()/2;
		//toolpanel.setBounds(5,0,getWidth() - 10,30);*/
		
		JLabel lblx = new JLabel("X - axis:");
		add(lblx);
		
		txtX = new JTextField();
		add(txtX);
		txtX.setColumns(5);
		
		JLabel lbly = new JLabel("Y - axis:");
		add(lbly);
		
		txtY = new JTextField();
		add(txtY);
		txtY.setColumns(5);
		
		JLabel lblr = new JLabel("Radius :");
		add(lblr);
		
		txtR = new JTextField();
		add(txtR);
		txtR.setColumns(5);
		
		JButton btn = new JButton("Bresenham's Circle");
		btn.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				double dx = Double.parseDouble(txtX.getText()) * 10;
				x = (int)dx + (((scale_coordinate) drawingpanel).getW()/2);
				double dy = Double.parseDouble(txtY.getText()) * 10;
				y =(((scale_coordinate) drawingpanel).getH()/2) - (int)dy;
				r = Integer.parseInt(txtR.getText());
				Graphics g = drawingpanel.getGraphics();
				g.setColor(Color.BLACK);
				testclass.addrecord("circle");
				circle(x,y,r,g);
				addArr();
			}			
		});
		add(btn);
		// button 2 is clear
		JButton btn2 = new JButton("Clear");
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				x_prev_circle.clear();
				y_prev_circle.clear();
				r_prev_circle.clear();
				testclass.clearRecord();
				PolygonPanel.clear();
				LinePanel.clear();
				drawingpanel.repaint();
				
			}
		});
		add(btn2);

		drawingpanel.addMouseListener(this);
	}
	
		public void circle(int xc, int yc, int r, Graphics g){
		   int x,y,p;
		   x=0;
		   y=r;
		   fill(g,x,y,xc,yc);
		   p=1-r;
		   while(x<y){
			   x=x+1;
		    
			   if(p<0){
				   p=p+2*x+1;
		    }
		    
		    else{
		     y=y-1;
		     p=p+2*x+1-2*y;
		    }
		    
		    fill(g,x,y,xc,yc);
		   }
		  }
		
		 public void fill(Graphics g,int x,int y,int xc,int yc)
		  {
		   g.fillOval(xc+x,yc+y,5,5);
		   g.fillOval(xc+x,yc-y,5,5);
		   g.fillOval(xc-x,yc+y,5,5);
		   g.fillOval(xc-x,yc-y,5,5);
		   g.fillOval(xc+y,yc+x,5,5);
		   g.fillOval(xc+y,yc-x,5,5);
		   g.fillOval(xc-y,yc+x,5,5);
		   g.fillOval(xc-y,yc-x,5,5);
		  }
		
		 
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(SwingUtilities.isLeftMouseButton(e)) {
			double x = e.getX() - (((scale_coordinate) drawingpanel).getW()/2);
			double y = (((scale_coordinate) drawingpanel).getH()/2) - e.getY();
			txtX.setText(Double.toString(x/10));
			txtY.setText(Double.toString(y/10));
		}
		
	}

	public void repaintCircle(int w, int h,int i)
	{
			x_prev_circle.set(i, x_prev_circle.get(i) + w);
			y_prev_circle.set(i, y_prev_circle.get(i) + h);
			circle(x_prev_circle.get(i),y_prev_circle.get(i),r_prev_circle.get(i),drawingpanel.getGraphics());
	}
	
	public void addArr()
	{
		x_prev_circle.add(x);
		y_prev_circle.add(y);
		r_prev_circle.add(r);
		System.out.println(x_prev_circle.size());
		x = y = r = 0;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}