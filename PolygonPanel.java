package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Graphics;
import javax.swing.*;


public class PolygonPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	int side, point,x,y,fillsts = 0,fillstsLine = 0, sts = 0, w = 973, h = 442;
	
	Color color;
	DrawPoly temp;
	Graphics g;
	JPanel drawingpanel;
	
	static ArrayList<DrawPoly> dpArr = new ArrayList<DrawPoly>();
	ArrayList<Integer> xpoint = new ArrayList<Integer>();
	ArrayList<Integer> ypoint = new ArrayList<Integer>();

	public PolygonPanel(JPanel dp)
	{
		drawingpanel = dp;
		g = drawingpanel.getGraphics();
		x = w/2;
		y = h/2;
		renderFrame();
	}
	
	public void renderFrame()
	{
		JButton enter = new JButton("Enter");
		JButton chColor = new JButton("Choose Color");
		JButton drawIrre = new JButton("Draw Irregular");
		JButton fill = new JButton("Fill");
		JButton fillLine = new JButton("Change line Color");
		drawIrre.setBackground(Color.green);
		JLabel pointLabel = new JLabel("Enter Number of side:");
		JLabel sideSize = new JLabel("Enter size of polygon:");
		JTextField fieldPoint = new JTextField(3);
		JTextField fieldSide = new JTextField(3);
		
		enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sideS = fieldSide.getText();
				String pointS = fieldPoint.getText();
				side = Integer.parseInt(sideS);
				point = Integer.parseInt(pointS);
				addpoly();
			}
		});
		
		chColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {    
				Color initialcolor=Color.black;
				color = Color.black;
				color = JColorChooser.showDialog(new JFrame(),"Select a color",initialcolor);
				chColor.setBackground(color);
			} 
		});
		
		drawIrre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(sts == 0)
				{
					sts = 1;
					drawIrre.setBackground(Color.red);
				}
				else
				{
					addirregularpoly();
					drawIrre.setBackground(Color.green);
					sts = 0;
				}
			}
		});
		
		fill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				fillsts = 1;
				g = drawingpanel.getGraphics();
				paintPolygon(g);
				repaint();
			}
		});
		
		fillLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				fillstsLine = 1;
				g = drawingpanel.getGraphics();
				paintPolygon(g);
				repaint();
			}
		});
		
		drawingpanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(sts == 1) {
					int tempx = e.getX();
					int tempy = e.getY();
					xpoint.add(tempx);
					ypoint.add(tempy);
					return;
				}
				x = e.getX();
				y = e.getY();
			}
		});
		
		add(pointLabel);
		add(fieldPoint);
		add(sideSize);
		add(fieldSide);
		add(enter);
		add(chColor);
		add(fill);
		add(fillLine);
		add(drawIrre);
	}
	public void addpoly()
	{
		temp = new DrawPoly(y,x,point,side,color);
		temp.setColorFillLine(color);
		dpArr.add(temp);
		repaint();
		g = drawingpanel.getGraphics();
		testclass.addrecord("polygon");
		paintPolygon(g);
	}
	
	public void addirregularpoly() {
		side = xpoint.size();
		int[] arrx = xpoint.stream().mapToInt(Integer::intValue).toArray();
		int[] arry = ypoint.stream().mapToInt(Integer::intValue).toArray();
		temp = new DrawPoly(side, arrx,arry,color);
		temp.setColorFillLine(color);
		dpArr.add(temp);
		g = drawingpanel.getGraphics();
		testclass.addrecord("polygon");
		paintPolygon(g);
		xpoint.clear();
		ypoint.clear();
		repaint();
	}
	
	public void paintPolygon(Graphics g)
	{
		if(dpArr.get(dpArr.size() - 1) != null)
		{
			dpArr.get(dpArr.size() - 1).setColorfill(color);
			dpArr.get(dpArr.size() - 1).draw(g);
		}
		if(fillsts == 1)
		{
			fillsts = 0;
			dpArr.get(dpArr.size() - 1).setColorfill(color);
			dpArr.get(dpArr.size() - 1).fill(g);
			dpArr.get(dpArr.size() - 1).fillLine(g);
		}
		if(fillstsLine == 1)
		{
			fillstsLine = 0;
			dpArr.get(dpArr.size() - 1).setColorFillLine(color);
			dpArr.get(dpArr.size() - 1).fillLine(g);
		}
	}
	
	public void repaintPoly(int newW, int newH, int i)
	{
		DrawPoly dp = dpArr.get(i);	
		dp.p.translate(newW/2, newH/2);
		((DrawPoly)dp).draw(drawingpanel.getGraphics());
		if(dp.colorfill != null)
		{
			((DrawPoly)dp).fill(drawingpanel.getGraphics());
			((DrawPoly)dp).fillLine(drawingpanel.getGraphics());
		}
		dpArr.set(i, dp);
	}
	
	static public void clear()
	{
		dpArr.clear();
	}
	
}

 