import javax.swing.*;
import java.awt.*;

/*This applet gives an interactive
  proof of the Goldman-Parker Conjecture.
  Programmed by Rich Schwartz*/

public class Main extends JFrame
{
    public static void main(String[] args)
    {
        Main m = new Main();
        m.setSize(1920, 1080);
        m.init();

        m.setVisible(true);
        m.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    JPanel plotPanel, controlPanel, textPanel, documentPanel, buttonPanel;
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

        controlPanel = new JPanel();
        controlPanel.setSize(909, 17);
        controlPanel.add(C3, BorderLayout.EAST);
        controlPanel.add(C1, BorderLayout.WEST);
        controlPanel.add(C2, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.NORTH);

        plotPanel = new JPanel();
        plotPanel.setSize(909, 270);
        plotPanel.add(P3);
        plotPanel.add(P1);
        plotPanel.add(P2);
        add(plotPanel,BorderLayout.CENTER);

        textPanel = new JPanel();
        textPanel.setSize(915, 209);
        textPanel.add(T);
        add(textPanel, BorderLayout.SOUTH);

        documentPanel = new JPanel();
        documentPanel.setSize(303, 222);
        documentPanel.add(D);
        add(documentPanel, BorderLayout.SOUTH);

        buttonPanel = new JPanel();
        buttonPanel.setSize(607, 222);
        buttonPanel.add(C);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}