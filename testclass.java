package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class testclass extends JFrame{

	private static final long serialVersionUID = 1L;
	private static ArrayList<String> shapeRecord = new ArrayList<String>();
	private JPanel contentpane;
	LinePanel toolpanel1;
	BresenhamCircle toolpanel2;
	PolygonPanel toolpanel3;
	public scale_coordinate drawingpanel = new scale_coordinate();
	static int counter = 0;
	int w = 973;
	int h = 442;
	
	public testclass()
	{
		setTitle("Assignment");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000,600);
		contentpane = new JPanel();
		contentpane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentpane);
		toolpanel1 = new LinePanel(drawingpanel);
		toolpanel2 = new BresenhamCircle(drawingpanel);
		toolpanel3 = new PolygonPanel(drawingpanel);
		contentpane.setLayout(new GridBagLayout());
		GridBagConstraints gdl  = new GridBagConstraints();
	
		gdl.gridy = 0;
		gdl.gridx = 0;
		gdl.weighty = 0;
		gdl.weightx = 0;
		gdl.ipadx = getWidth();
		gdl.ipady =0;
		contentpane.add(toolpanel2,gdl);
		
		
		gdl.gridy = 1;
		gdl.gridx = 0;
		gdl.weighty = 0;
		gdl.weightx = 0;
		gdl.fill = GridBagConstraints.BOTH;
		gdl.ipadx = getWidth();
		gdl.ipady = 0;
		contentpane.add(toolpanel1, gdl);
		
		gdl.gridy = 2;
		gdl.gridx = 0;
		gdl.weighty = 0;
		gdl.weightx = 0;
		gdl.fill = GridBagConstraints.BOTH;
		gdl.ipadx = getWidth();
		gdl.ipady = 0;
		contentpane.add(toolpanel3, gdl);
		
		gdl.fill = GridBagConstraints.BOTH;
		gdl.gridy = 3;
		gdl.weighty = 1;
		gdl.weightx = 1;
		gdl.ipadx = getWidth() - 10;
		gdl.ipady = getHeight() - 10;

		contentpane.add(drawingpanel,gdl);
		
		drawingpanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int newW = drawingpanel.getWidth();
				int newH = drawingpanel.getHeight();
				int polySize = 0, circleSize = 0, blLineSize = 0, DDAlineSize = 0;
				for(int i = 0; i < shapeRecord.size(); i++)
				{
					switch (shapeRecord.get(i))
					{
						case "polygon":
						{
							toolpanel3.repaintPoly(newW - w, newH - h, polySize);
							polySize++;
							break;
						}
						case "circle":
						{
							toolpanel2.repaintCircle((newW - w)/2, (newH - h)/2, circleSize);
							circleSize++;
							break;
						}
						case "BlLine":
						{
							toolpanel1.repaintLineBresen((newW - w)/2, (newH - h)/2, blLineSize);
							blLineSize++;
							break;
						}
						case "DDALine":
						{
							toolpanel1.repaintLineDDA((newW - w)/2, (newH - h)/2, DDAlineSize);
							DDAlineSize++;
							break;
						}
					}
				}
				w = newW;
				h = newH;
			}
		});
	}
	
	static public void addrecord(String sr)
	{
		shapeRecord.add(sr);
		System.out.print(sr);
	}
	
	static public void main(String[] args)
	{
		testclass home = new testclass();
		home.setVisible(true);
	}
	
	static public void clearRecord()
	{
		shapeRecord.clear();
	}
}