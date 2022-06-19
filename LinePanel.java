package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LinePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	int x_1,x_2,y_1,y_2;
	int w = 973;
	int h = 442;
	int sts = 0;
	BresenhamLine temp;
	DDA ddatemp;
	JPanel drawingpanel;
	static ArrayList<BresenhamLine> blarr = new ArrayList<BresenhamLine>();
	static ArrayList<DDA> ddarr = new ArrayList<DDA>();

	
	public LinePanel(JPanel dp)
	{
		drawingpanel = dp;
		x_1 = x_2 = y_1 = y_2 = 0;
		JTextField xinput1 = new JTextField(5);
		JTextField yinput1 = new JTextField(5);
		JTextField xinput2 = new JTextField(5);
		JTextField yinput2 = new JTextField(5);
		JLabel x1 = new JLabel("x1:");
		JLabel x2 = new JLabel("x2:");
		JLabel y1 = new JLabel("y1:");
		JLabel y2 = new JLabel("y2:");
		JLabel tline = new JLabel("Thickness");
		JTextField lineThickness = new JTextField(5);
		JComboBox<String>lineStroke = new JComboBox<String>();
		JButton dda = new JButton("DDA");
		JButton bl = new JButton("Bresenham Line");
		
		lineStroke.addItem("Normal");
		lineStroke.addItem("Dashed");
		lineStroke.addItem("Dotted");
		
		dda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strokeTemp = lineStroke.getItemAt(lineStroke.getSelectedIndex());
				int t = Integer.parseInt(lineThickness.getText());

				x_1 = (int)(Double.parseDouble(xinput1.getText()) * 10);
				x_1 = x_1 + (((scale_coordinate) drawingpanel).getW()/2);
				y_1 = (int)(Double.parseDouble(yinput1.getText()) * 10);
				y_1 =(((scale_coordinate) drawingpanel).getH()/2) - y_1;

				x_2 = (int)(Double.parseDouble(xinput2.getText()) * 10);
				x_2 = x_2 + (((scale_coordinate) drawingpanel).getW()/2);
				y_2 = (int)(Double.parseDouble(yinput2.getText()) * 10);
				y_2 =(((scale_coordinate) drawingpanel).getH()/2) - y_2;
				
				ddatemp = new DDA(x_1,y_1,x_2,y_2,t,Color.black,strokeTemp,drawingpanel);
				ddarr.add(ddatemp);
				testclass.addrecord("DDALine");
				x_1 = x_2 = y_1 = y_2 = 0;
				repaint();
			}
		});
		
		
		bl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strokeTemp = lineStroke.getItemAt(lineStroke.getSelectedIndex());
				int t = Integer.parseInt(lineThickness.getText());
				x_1 = (int)(Double.parseDouble(xinput1.getText()) * 10);
				x_1 = x_1 + (((scale_coordinate) drawingpanel).getW()/2);
				y_1 = (int)(Double.parseDouble(yinput1.getText()) * 10);
				y_1 =(((scale_coordinate) drawingpanel).getH()/2) - y_1;

				x_2 = (int)(Double.parseDouble(xinput2.getText()) * 10);
				x_2 = x_2 + (((scale_coordinate) drawingpanel).getW()/2);
				y_2 = (int)(Double.parseDouble(yinput2.getText()) * 10);
				y_2 =(((scale_coordinate) drawingpanel).getH()/2) - y_2;
				
				temp = new BresenhamLine(x_1,y_1,x_2,y_2,t,strokeTemp,dp);
				blarr.add(temp);
				testclass.addrecord("BlLine");
				x_1 = x_2 = y_1 = y_2 = 0;
				repaint();
				}
		});
		 
		drawingpanel.addMouseListener(new MouseAdapter() { 
			@Override
		     public void mouseClicked(MouseEvent e) {
		    	//stores coordinates of current point 
		    	 if(sts == 0)
		    	 {
		 			double x = e.getX() - (((scale_coordinate) drawingpanel).getW()/2);
					double y = (((scale_coordinate) drawingpanel).getH()/2) - e.getY();
					xinput1.setText(Double.toString(x/10));
					yinput1.setText(Double.toString(y/10));
		    		sts = 1;	
		    	 }
		    	 else
		    	 {
		 			double x = e.getX() - (((scale_coordinate) drawingpanel).getW()/2);
					double y = (((scale_coordinate) drawingpanel).getH()/2) - e.getY();
					xinput2.setText(Double.toString(x/10));
					yinput2.setText(Double.toString(y/10));
		    		 sts = 0;
		    	 }
		     }
		 });

		add(x1);
		add(xinput1);
		add(y1);		
		add(yinput1);	
		add(x2);
		add(xinput2);
		add(y2);
		add(yinput2);
		add(tline);
		add(lineThickness);
		add(lineStroke);
		add(bl);
		add(dda);
	}
	
	public void repaintLineBresen(int w, int h, int i)
	{
		BresenhamLine temp = blarr.get(i);
		BresenhamLine tempdraw = new BresenhamLine(temp.x0+w,temp.y0+h,temp.x1+w,temp.y1+h,temp.thickness,temp.lineType,drawingpanel);
		blarr.set(i, tempdraw);
		repaint();
	}
	
	public void repaintLineDDA(int w, int h, int i)
	{
		DDA temp = ddarr.get(i);
		DDA tempdraw = new DDA(temp.x1+w,temp.y1+h,temp.x2+w,temp.y2+h,temp.thickness,Color.black,temp.lineType,drawingpanel);
		ddarr.set(i, tempdraw);
		repaint();
	}
	
	public static void clear()
	{
		blarr.clear();
		ddarr.clear();
	}
}