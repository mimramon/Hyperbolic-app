import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*This applet gives an interactive
  proof of the Goldman-Parker Conjecture.
  Programmed by Rich Schwartz*/

/*overall applet layout*/


public class Main extends JFrame
{
	PlotCanvas P1, P2, P3;
    PlotControlCanvas C1, C2, C3;
    static ControlCanvas C;
    TextCanvas T;
    DocumentCanvas D;
	
	public static void main(String[] args)
    {
        Main f = new Main();
        f.init();
        log(f.C);
        
        int testVal = JOptionPane.showConfirmDialog(null, "Do you want to see testing mode?", "Test", JOptionPane.YES_NO_OPTION);
        if(testVal == JOptionPane.YES_OPTION)
        {
        	JFrame test = new JFrame();
            test.setSize(800, 450);
            test.setLayout(new GridLayout());
            test.setVisible(true);
            test.add(sParamSetter());
            test.add(pParamSetter());
            test.add(plotButton());
            test.add(clearButton());
            test.validate();
            test.pack();
        }
    }

    public void init()
    {
    	setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    	
        JPanel test = new JPanel();
        test.setLayout(new BoxLayout(test, BoxLayout.Y_AXIS));
        test.setBackground(Color.black);
        add(test);

        setBackground(Color.black);

        //Interactive text box
        D = new DocumentCanvas();

        //Canvases that are drawn to
        P1 = new PlotCanvas(1, D);
        P2 = new PlotCanvas(2, D);
        P3 = new PlotCanvas(3, D);

        //Canvas settings controllers
        C1 = new PlotControlCanvas(P1, D);
        C2 = new PlotControlCanvas(P2, D);
        C3 = new PlotControlCanvas(P3, D);

        //Controls what is drawn and colours
        C = new ControlCanvas(D, P1, P2, P3, C1, C2, C3);

        //Gives all the background information
        T = new TextCanvas(C, C1, C2, C3, D, P1, P2, P3);

        C1.setSize(303, 17);
        C2.setSize(303, 17);
        C3.setSize(303, 17);

        P1.setSize(303, 270);
        P2.setSize(303, 270);
        P3.setSize(303, 270);

        T.setSize(915, 209);
        C.setSize(607, 222);
        D.setSize(303, 222);

       JPanel canvasPanel = new JPanel(new GridLayout(1, 3));
        
        canvasPanel.add(plotPanel(P3, C3));
        canvasPanel.add(plotPanel(P1, C1));
        canvasPanel.add(plotPanel(P2, C2));

        JPanel controlNText = new JPanel(new GridLayout());
        controlNText.add(C);
        controlNText.add(D);

        test.add(canvasPanel);
        test.add(controlNText);
        test.add(T);
    }
    
    public static void log(ControlCanvas C)
    {
    	System.out.println("I0 Matrix det");
    	Complex.print(Matrix.determinant(Matrix.I0(C.s, C.p)));
    	System.out.println("\nI1 Matrix det");
    	Complex.print(Matrix.determinant(Matrix.I1(C.s, C.p)));
    	System.out.println("\nI2 Matrix det");
    	Complex.print(Matrix.determinant(Matrix.I2(C.s, C.p)));
    	
        System.out.println("\n");	
    	Matrix.print(Matrix.I0(C.s, C.p));
    	
    	System.out.println("I0 Matrix det normalised");
    	Complex.print(Matrix.determinant(Matrix.normaliseDeterminant(Matrix.I0(C.s, C.p))));
    	System.out.println("\nI1 Matrix det normalised");
    	Complex.print(Matrix.determinant(Matrix.normaliseDeterminant(Matrix.I1(C.s, C.p))));
    	System.out.println("\nI2 Matrix det normalised");
    	Complex.print(Matrix.determinant(Matrix.normaliseDeterminant(Matrix.I2(C.s, C.p))));
    	
    	System.out.println();
    	/*
    	Complex.print(Matrix.determinant(new Matrix(
    			new Vector(new Complex(1, 0), new Complex(2, 0), new Complex(3, 0)), 
    			new Vector(new Complex(4, 0), new Complex(5, 0), new Complex(7, 0)), 
    			new Vector(new Complex(8, 0), new Complex(10, 0), new Complex(13, 0)))));
    	*/
    }
    
    static JPanel sParamSetter()
    {
    	JSpinner valueSpinner;
    	JSpinner deltaSpinner;
    	
    	SpinnerNumberModel numberModel = new SpinnerNumberModel(Math.sqrt(125/3), 0, 10000000, 0.0000000000001);
    	valueSpinner = new JSpinner(numberModel);
        valueSpinner.setEditor(new JSpinner.NumberEditor(valueSpinner, "0.0000000000000"));
        valueSpinner.addChangeListener(new ChangeListener() 
        {
    			
        	@Override
    		public void stateChanged(ChangeEvent e) 
    		{
        		C.s = (double) valueSpinner.getValue();
    			System.out.println(valueSpinner.getValue());
    		}
    	});
        valueSpinner.setSize(200, 400);
        valueSpinner.setToolTipText("<html><p width=\"500\">" +"This is the s parameter selector. "
        		+ "The objects in the applet are parametrized by a number in the interval [0,sqrt(125/3)). "
        		+ "Click the buttons or type to select a value. "
        		+ "The picture is automatically replotted at the new parameter value. "
        		+ "The parameter value s is displayed in black. "
        		+ "For computational reasons any s > s0 = sqrt(125/3) - 0.00001 is reset  to s0."+"</p></html>");
       
        
        
        deltaSpinner = new JSpinner(new SpinnerNumberModel(13, 0, 13, 1));
    	deltaSpinner.setEditor(new JSpinner.NumberEditor(deltaSpinner, "00"));
    	
    	
    	deltaSpinner.addChangeListener(new ChangeListener() 
    	{
			
			@Override
			public void stateChanged(ChangeEvent e) 
			{
				double exponent = Math.pow(10,(int)deltaSpinner.getValue() - (int)deltaSpinner.getPreviousValue());
				numberModel.setStepSize(numberModel.getStepSize().doubleValue()*exponent);
			}
		});
		
    	deltaSpinner.setSize(200, 400);
    	
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLUE);
        panel.add(valueSpinner);
        panel.add(deltaSpinner);
        return panel;	
    }
    
    static JPanel pParamSetter()
    {
    	JSpinner spinner;
    	
    	spinner = new JSpinner(new SpinnerNumberModel(2, 0, 2, 0.0000000000001));
        spinner.setEditor(new JSpinner.NumberEditor(spinner, "0.0000000000000"));
        spinner.addChangeListener(new ChangeListener() 
        {
    			
        	@Override
    		public void stateChanged(ChangeEvent e) 
    		{
        		C.p = (double) spinner.getValue();
    			System.out.println(spinner.getValue());
    		}
    	});
        spinner.setSize(200, 400);
        spinner.setToolTipText("<html><p width=\"500\">" +"This is the p parameter selector. "
        		+ "The objects in the applet are parametrized by a number in the interval [1,inf). "
        		+ "Click the buttons or type to select a value. "
        		+ "The picture is automatically replotted at the new parameter value. "
        		+ "The parameter value p is displayed in black. "
        		+ "WARNING: Changing this parameter may cause the application to crash, it may need to be terminated."+"</p></html>");
        JPanel panel = new JPanel();
        panel.setBackground(Color.RED);
        panel.add(spinner);
        panel.validate();
        return panel;	
    }
    
    static JPanel plotButton()
    {
    	JPanel panel = new JPanel();
    	JButton button = new JButton("Plot");
    	button.addActionListener(new ActionListener() 
    	{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				C.doPlot();
			}
		});
    	button.setToolTipText("This is the plot button. When you push it, the selected objects are plotted in 3 coordinate systems: ELEV, PLAN, HYP.");
    	panel.add(button);
    	return panel;
    }
    
    static JPanel clearButton()
    {
    	JPanel panel = new JPanel();
    	JButton button = new JButton("Clear");
    	button.addActionListener(new ActionListener() 
    	{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				C.S1.clear();
	            C.S2.clear();
	            C.doPlot();
			}
		});
    	button.setToolTipText("This is the clear button."
        + "When you push this button the "
        + "object selectors are cleared.");
    	panel.add(button);
    	return panel;
    }
    
    static JPanel plotPanel(PlotCanvas p, PlotControlCanvas c)
    {
    	JPanel panel = new JPanel();
    	panel.add(c, BorderLayout.NORTH);
    	panel.add(p, BorderLayout.CENTER);
    	
    	return panel;
    }
}
