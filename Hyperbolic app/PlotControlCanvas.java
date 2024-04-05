/*This class provides the scaling options for the
plot windows, and also lets the user toggle the
coordinate systems and turn the plot windows
on and off.*/

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

class PlotControlCanvas extends DoubleBufferedCanvas implements MouseListener, MouseMotionListener
{
    ListenSquare active, reset, info, mode;
    DocumentCanvas D;
    PlotCanvas P;
    HorizontalSlider scale, aspect;
    Font font;
    Color color1, color2, color3, color4, color5;

    PlotControlCanvas(PlotCanvas P, DocumentCanvas D)
    {
        this.D = D;
        this.P = P;
        color1 = new Color(255, 50, 180);
        color2 = new Color(255, 0, 140);
        color3 = color2;
        color4 = color3;
        color5 = Color.black;


        addMouseListener(this);
        addMouseMotionListener(this);
        scale = new HorizontalSlider(168, 0, 105, 17, 220, color1);
        aspect = new HorizontalSlider(29, 0, 103, 17, 80, color1);
        active = new ListenSquare(273, 0, 29, 17);
        mode = new ListenSquare(131, 0, 37, 17);
        reset = new ListenSquare(0, 0, 29, 17);
        font = new Font("Helvetica", Font.PLAIN, 11);

    }


    public void paint(Graphics gfx)
    {
        Graphics2D g = (Graphics2D) gfx;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(new Font("Helvetica", Font.PLAIN, 10));

        scale.render(g);
        aspect.render(g);
        active.render(g, color2);
        mode.render(g, color3);
        reset.render(g, color4);
        g.setColor(color5);
        g.drawString("reset", 3, 13);
        g.drawString("aspect", 35, 13);
        g.drawString("scale", 176, 13);
        Complex z = new Complex();
        z.doubleRender2(P.SCALE, g, 220, 14);
        z.doubleRender2(P.XSCALE / P.YSCALE, g, 80, 14);
        if (P.ACTIVE == 0) g.drawString("off", 277, 13);
        if (P.ACTIVE == 1) g.drawString("on", 277, 13);
        if ((P.MODE == 0) && (P.TYPE == 1)) g.drawString("elev1", 135, 13);
        if ((P.MODE == 1) && (P.TYPE == 1)) g.drawString("elev2", 135, 13);
        if ((P.MODE == 0) && (P.TYPE == 2)) g.drawString("plan1", 135, 13);
        if ((P.MODE == 1) && (P.TYPE == 2)) g.drawString("plan2", 135, 13);
        if ((P.MODE == 0) && (P.TYPE == 3)) g.drawString("hyp1", 135, 13);
        if ((P.MODE == 1) && (P.TYPE == 3)) g.drawString("hyp2", 135, 13);
        g.drawLine(80, 14, 80, 17);
        g.drawLine(220, 14, 220, 17);
    }


    public void mousePressed(MouseEvent e)
    {
        Point p = pinpoint(e);

        if (scale.inside(p) == 1)
        {
            scale.ACTIVE = 1;
            scale.configure(p);
        }

        if (aspect.inside(p) == 1)
        {
            aspect.ACTIVE = 1;
            aspect.configure(p);
            D.MESSAGE = 50;
            D.repaint();
            repaint();
        }
    }

    public void mouseClicked(MouseEvent e)
    {
        Point p = pinpoint(e);

        if (active.inside(p) == 1)
        {
            P.ACTIVE = 1 - P.ACTIVE;
            D.MESSAGE = 50;
            D.ACTIVE = 1;
            D.repaint();
            repaint();
            P.repaint();
        }

        if (mode.inside(p) == 1)
        {
            P.MODE = 1 - P.MODE;
            repaint();
            D.MESSAGE = 50;
            D.ACTIVE = 1;
            D.repaint();
            P.repaint();
        }

        if (reset.inside(p) == 1)
        {
            P.SCALE = 1;
            P.XSCALE = 60.0;
            P.YSCALE = 60.0;
            scale.POS = 220;
            aspect.POS = 80;
            P.XORIGIN = 0;
            if (P.TYPE == 3) P.XORIGIN = -120;
            P.YORIGIN = 0;
            D.MESSAGE = 50;
            D.ACTIVE = 1;
            repaint();
            D.repaint();
            P.repaint();
        }
    }


    public void mouseReleased(MouseEvent e)
    {
        Point p = pinpoint(e);

        if (scale.ACTIVE == 1)
        {
            double d1 = scale.POS - 220;
            P.SCALE = 1.0 * Math.exp(d1 / 40.0);
            repaint();
        }

        if (aspect.ACTIVE == 1)
        {
            double d2 = aspect.POS - 80;
            double d3 = Math.exp(d2 / 40.0);
            P.XSCALE = P.YSCALE * d3;
            D.MESSAGE = 50;
            D.ACTIVE = 1;
            D.repaint();
            repaint();
            P.repaint();
        }

        scale.ACTIVE = 0;
        aspect.ACTIVE = 0;
    }


    public void mouseExited(MouseEvent e)
    {
    }

    public void mouseEntered(MouseEvent e)
    {
    }

    public void mouseDragged(MouseEvent e)
    {
    }

    public void mouseMoved(MouseEvent e)
    {
        Point p = pinpoint(e);
        if (aspect.inside(p) == 1) D.MESSAGE = 46;
        if (scale.inside(p) == 1) D.MESSAGE = 43;
        if (mode.inside(p) == 1) D.MESSAGE = 44;
        if (reset.inside(p) == 1) D.MESSAGE = 45;
        if (active.inside(p) == 1) D.MESSAGE = 47;
        if ((D.ACTIVE == 1) && (D.history != D.MESSAGE)) D.repaint();
    }
}