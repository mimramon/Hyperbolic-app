import javax.swing.*;
import java.applet.Applet;
import java.awt.event.*;
import java.awt.*;

/*This applet gives an interactive
  proof of the Goldman-Parker Conjecture.
  Programmed by Rich Schwartz*/

/*overall applet layout*/


public class Main extends Applet
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("applet viewer");
        frame.setSize(1920, 1080);
        Applet applet = new Main();
        frame.add(applet);
        applet.init();
        applet.start();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    PlotCanvas P1, P2, P3;
    PlotControlCanvas C1, C2, C3;
    ControlCanvas C;
    TextCanvas T;
    DocumentCanvas D;

    public void init()
    {

        setBackground(Color.black);

        D = new DocumentCanvas();
        P1 = new PlotCanvas(1, D);
        P2 = new PlotCanvas(2, D);
        P3 = new PlotCanvas(3, D);
        C1 = new PlotControlCanvas(P1, D);
        C2 = new PlotControlCanvas(P2, D);
        C3 = new PlotControlCanvas(P3, D);
        C = new ControlCanvas(D, P1, P2, P3, C1, C2, C3);
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


        add(C3);
        add(C1);
        add(C2);

        add(P3);
        add(P1);
        add(P2);

        add(D);
        add(C);
        add(T);
    }
}
