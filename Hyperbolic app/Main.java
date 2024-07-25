import javax.swing.*;
import java.awt.*;

/*This applet gives an interactive
  proof of the Goldman-Parker Conjecture.
  Programmed by Rich Schwartz*/

/*overall applet layout*/


public class Main extends JFrame
{
    public static void main(String[] args)
    {
        Main f = new Main();
        f.init();
    }

    PlotCanvas P1, P2, P3;
    PlotControlCanvas C1, C2, C3;
    ControlCanvas C;
    TextCanvas T;
    DocumentCanvas D;

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


        JPanel canvasControls = new JPanel();
        canvasControls.setSize(1920, 17);
        canvasControls.setLayout(new GridLayout(1, 3, 20, 0));
        canvasControls.add(C3);
        canvasControls.add(C1);
        canvasControls.add(C2);

        JPanel canvasPanel = new JPanel(new GridLayout(1, 3));
        canvasPanel.add(P3);
        canvasPanel.add(P1);
        canvasPanel.add(P2);

        JPanel controlNText = new JPanel(new GridLayout());
        controlNText.add(C);
        controlNText.add(D);

        test.add(canvasControls);
        test.add(canvasPanel);
        test.add(controlNText);
        test.add(T);
    }
}
