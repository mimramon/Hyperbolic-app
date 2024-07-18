/*the main control panel*/

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

class ControlCanvas extends DoubleBufferedCanvas implements MouseListener, MouseMotionListener
{
    DocumentCanvas D;
    PlotCanvas P1, P2, P3;
    PlotControlCanvas C1, C2, C3;
    RSphere S1, S2;
    Font font;
    double s, p, TRUNCATE;

    HorizontalSlider parameter, truncate, density;
    ListenSquare plot, erase, clear, money, plink, tlink, t_reset;
    ListenSquare[] COL = new ListenSquare[40];
    ListenSquare[] mode = new ListenSquare[6];
    int ERASE, DENSITY, P_LINK, T_LINK, MODE;
    Color[] color = new Color[4];

    ControlCanvas(DocumentCanvas D, PlotCanvas P1, PlotCanvas P2, PlotCanvas P3, PlotControlCanvas C1, PlotControlCanvas C2, PlotControlCanvas C3)
    {
        this.D = D;
        this.P1 = P1;
        this.P2 = P2;
        this.P3 = P3;
        this.C1 = C1;
        this.C2 = C2;
        this.C3 = C3;
        P_LINK = 1;
        T_LINK = 1;
        MODE = 1;
        color[3] = new Color(255, 50, 200);
        color[1] = new Color(255, 80, 220);
        color[0] = new Color(235, 0, 145);
        color[2] = new Color(255, 0, 180);
        addMouseListener(this);
        addMouseMotionListener(this);
        setBackground(Color.black);
        font = new Font("Helvetican", Font.PLAIN, 11);

        /*plot selector*/

        for (int i = 1; i <= 4; ++i)
        {
            mode[i] = new ListenSquare(257 + 15 * i, 0, 15, 17);
        }



        /*color selector*/
        for (int i = 0; i <= 6; ++i)
        {
            for (int j = 0; j <= 4; ++j)
            {
                COL[7 * j + i + 1] = new ListenSquare(272 + 12 * j, 120 + 12 * i, 12, 12);
                COL[7 * j + i + 1].C = Color.white;
            }
        }
        COL[0] = new ListenSquare(272, 102, 60, 102);
        COL[0].C = Color.white;
        COL[7].C = new Color(100, 255, 255);
        COL[14].C = new Color(0, 255, 255);
        COL[21].C = new Color(0, 200, 255);
        COL[28].C = new Color(0, 150, 200);
        COL[35].C = new Color(0, 100, 150);

        COL[6].C = new Color(255, 255, 100);
        COL[13].C = new Color(255, 255, 0);
        COL[20].C = new Color(255, 180, 0);
        COL[27].C = new Color(220, 140, 0);
        COL[34].C = new Color(160, 80, 0);

        COL[5].C = new Color(255, 180, 255);
        COL[12].C = new Color(255, 50, 255);
        COL[19].C = new Color(200, 0, 200);
        COL[26].C = new Color(150, 0, 150);
        COL[33].C = new Color(100, 0, 100);

        COL[4].C = new Color(180, 255, 180);
        COL[11].C = new Color(100, 255, 100);
        COL[18].C = new Color(0, 240, 0);
        COL[25].C = new Color(0, 180, 0);
        COL[32].C = new Color(0, 100, 0);

        COL[3].C = new Color(255, 170, 170);
        COL[10].C = new Color(255, 130, 130);
        COL[17].C = new Color(255, 40, 40);
        COL[24].C = new Color(200, 0, 0);
        COL[31].C = new Color(120, 0, 0);

        COL[2].C = new Color(170, 170, 255);
        COL[9].C = new Color(100, 100, 255);
        COL[16].C = new Color(40, 40, 255);
        COL[23].C = new Color(0, 0, 200);
        COL[30].C = new Color(0, 0, 120);

        COL[1].C = new Color(255, 255, 255);
        COL[8].C = new Color(200, 200, 200);
        COL[15].C = new Color(150, 150, 150);
        COL[22].C = new Color(80, 80, 80);
        COL[29].C = new Color(0, 0, 0);

        /*controls*/

        plot = new ListenSquare(272, 17, 60, 17);
        clear = new ListenSquare(272, 51, 60, 17);
        money = new ListenSquare(272, 68, 60, 34);
        erase = new ListenSquare(272, 34, 60, 17);
        parameter = new HorizontalSlider(17, 204, 589, 17, 333, color[2]);
        s = Math.sqrt(35.0);
        p = 2;
        density = new HorizontalSlider(332, 0, 273, 17, 382, color[2]);
        truncate = new HorizontalSlider(17, 0, 238, 17, 254, color[2]);
        TRUNCATE = 1;
        t_reset = new ListenSquare(256, 0, 16, 17);
        tlink = new ListenSquare(0, 0, 17, 17);
        plink = new ListenSquare(0, 204, 17, 17);
        DENSITY = 100;
        erase.C = color[0];
        /*object selectors*/
        S1 = new RSphere(0, 19, 270, 224);
        S2 = new RSphere(334, 19, 270, 224);
    }


    public void paint(Graphics gfx)
    {
        Graphics2D g = (Graphics2D) gfx;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.setFont(font);
        parameter.render(g);
        density.render(g);
        truncate.render(g);
        t_reset.render(g, color[0]);
        money.render(g, color[0]);
        S1.render(g);
        S2.render(g);
        plot.render(g, color[0]);
        clear.render(g, color[0]);
        erase.render(g, erase.C);
        plink.render(g, color[0]);
        tlink.render(g, color[0]);

        for (int i = 1; i <= 4; ++i) mode[i].render(g, color[0]);
        mode[MODE].render(g, color[1]);
        for (int i = 0; i <= 35; ++i) COL[i].render(g, COL[i].C);
        labelButtons(g, Color.black);

        /*slider markings*/
        Complex z = new Complex();
        g.setColor(Color.black);
        g.drawLine(333, 216, 333, 208);
        z.doubleRender(s, g, 185, 217);
        g.setColor(new Color(120, 0, 70));
        double ss = s / Math.sqrt(35.0);
        z.doubleRender(ss, g, 335, 217);
        double sss = s / Math.sqrt(125.0 / 3.0);
        z.doubleRender(sss, g, 535, 217);
        g.setColor(Color.black);
        g.drawString(Integer.toString(DENSITY), 575, 14);
        z.doubleRender(TRUNCATE, g, 185, 14);
    }

    void labelButtons(Graphics g, Color C)
    {
        g.setColor(C);
        font = new Font("Helvetica", Font.PLAIN, 11);
        g.setFont(font);

        if (P_LINK == 0) g.drawString("set parameter", 24, 217);
        if (P_LINK == 1) g.drawString("set parameter and replot", 24, 217);
        if (T_LINK == 0) g.drawString("truncate arcs", 24, 12);
        if (T_LINK == 1) g.drawString("truncate arcs and replot", 24, 12);
        g.drawString("set plot density", 340, 12);
        g.drawString("clear", 277, 64);
        g.drawString("plot", 277, 30);
        g.drawString("1", 277, 13);
        g.drawString("2", 292, 13);
        g.drawString("4", 307, 13);
        g.drawString("8", 322, 13);
        g.drawString("erase", 277, 47);
        g.drawString("show me", 277, 81);
        g.drawString("the money", 276, 98);
        g.setColor(S1.contrast(COL[0].C));
        g.drawString("color", 277, 116);
    }


    double getParameter()
    {
        double[] t = new double[5];
        t[0] = Math.sqrt(125.0 / 3.0);
        t[1] = Math.sqrt(35.0);
        if (parameter.POS > 333)
        {
            t[2] = (parameter.POS - 333.0) / (parameter.w - 333.0);
            t[3] = (1.0 - t[2]) * t[1] + t[2] * t[0];
            if (t[3] > t[0] - .00001) t[3] = t[0] - .00001;
        }
        if (parameter.POS <= 333)
        {
            t[2] = (parameter.POS - 19.0) / 314.0;
            t[3] = t[2] * t[1];
            if (t[3] < 0.0) t[3] = 0.0;
        }
        return (t[3]);
    }

    double getTruncate()
    {
        double ss = 0.0;
        ss = truncate.POS;
        ss = (ss - truncate.x) / truncate.w;
        if (ss > .99) ss = 1.0;
        return (ss);
    }

    void doPlot()
    {
        double t = getTruncate();
        D.MESSAGE = 50;
        D.ACTIVE = 1;
        D.repaint();
        repaint();

        P1.TRUNCATE = TRUNCATE;
        P2.TRUNCATE = TRUNCATE;
        P3.TRUNCATE = TRUNCATE;
        int m = 0;
        m = (int) (Math.pow(2, MODE - 1));
        P1.MULTIPLICITY = m;
        P2.MULTIPLICITY = m;
        P3.MULTIPLICITY = m;
        P1.DENSITY = DENSITY;
        P2.DENSITY = DENSITY;
        P3.DENSITY = DENSITY;

        for (int i = 7; i <= 18; ++i)
        {
            P1.S1.S[i] = S1.S[i];
            P1.S2.S[i] = S2.S[i];
            P2.S1.S[i] = S1.S[i];
            P2.S2.S[i] = S2.S[i];
            P3.S1.S[i] = S1.S[i];
            P3.S2.S[i] = S2.S[i];

        }
        for (int i = 1; i <= 6; ++i)
        {
            for (int j = 1; j <= 40; ++j)
            {
                P1.S1.A[i][j] = S1.A[i][j];
                P1.S2.A[i][j] = S2.A[i][j];
                P2.S1.A[i][j] = S1.A[i][j];
                P2.S2.A[i][j] = S2.A[i][j];
                P3.S1.A[i][j] = S1.A[i][j];
                P3.S2.A[i][j] = S2.A[i][j];
            }
        }

        P1.S1.setStructure(s, p);
        P2.S1 = P1.S1;
        P3.S1 = P1.S1;
        P3.repaint();
        P1.repaint();
        P2.repaint();
    }


    public void mousePressed(MouseEvent e)
    {
        Point p = pinpoint(e);

        if (density.inside(p) == 1) density.ACTIVE = 1;
        if (parameter.inside(p) == 1)
        {
            parameter.ACTIVE = 1;
            if (P_LINK > 01)
            {
                D.MESSAGE = 50;
                D.repaint();
            }
        }

        if (truncate.inside(p) == 1)
        {
            truncate.ACTIVE = 1;
            if (T_LINK > 1)
            {
                D.MESSAGE = 50;
                D.repaint();
            }
        }
        if (t_reset.inside(p) == 1)
        {
            truncate.POS = 254;
            TRUNCATE = 1.0;
            if (T_LINK > 1)
            {
                D.MESSAGE = 50;
                D.repaint();
            }
        }

    }

    public void mouseReleased(MouseEvent e)
    {

        Point p = pinpoint(e);
        parameter.configure(p);
        truncate.configure(p);
        density.configure(p);
        if (parameter.ACTIVE == 1) s = getParameter();
        if (truncate.ACTIVE == 1) TRUNCATE = getTruncate();
        if ((parameter.ACTIVE == 1) && (P_LINK == 1)) doPlot();
        if ((truncate.ACTIVE == 1) && (T_LINK == 1)) doPlot();
        if (t_reset.inside(p) == 1) doPlot();

        parameter.ACTIVE = 0;
        truncate.ACTIVE = 0;
        density.ACTIVE = 0;
    }


    public void mouseEntered(MouseEvent e)
    {
        D.MESSAGE = 1;
        if (D.ACTIVE == 1) D.repaint();
    }

    public void mouseExited(MouseEvent e)
    {
        D.MESSAGE = -1;
        if (D.ACTIVE == 1) D.repaint();
    }

    public void mouseMoved(MouseEvent e)
    {
        Point p = pinpoint(e);
        D.MESSAGE = 1;
        if (density.inside(p) == 1) D.MESSAGE = 9;
        if (parameter.inside(p) == 1) D.MESSAGE = 7;
        if (truncate.inside(p) == 1) D.MESSAGE = 12;
        if (t_reset.inside(p) == 1) D.MESSAGE = 71;
        if (plink.inside(p) == 1) D.MESSAGE = 27;
        if (tlink.inside(p) == 1) D.MESSAGE = 26;
        if (COL[0].inside(p) == 1) D.MESSAGE = 8;
        if (erase.inside(p) == 1) D.MESSAGE = 10;
        if (plot.inside(p) == 1) D.MESSAGE = 11;
        if (clear.inside(p) == 1) D.MESSAGE = 13;
        if (money.inside(p) == 1) D.MESSAGE = 14;
        for (int i = 1; i <= 4; ++i)
        {
            if (mode[i].inside(p) == 1) D.MESSAGE = 70;
        }
        int test = 0;
        test = S1.MESSAGE(p);
        if (test > 0) D.MESSAGE = 14 + test;
        test = S2.MESSAGE(p);
        if (test > 0) D.MESSAGE = 27 + test;
        if ((D.ACTIVE == 1) && (D.MESSAGE != D.history)) D.repaint();
    }


    public void mouseClicked(MouseEvent e)
    {
        Point p = pinpoint(e);
        int test = 0;
        for (int i = 1; i <= 35; ++i)
        {
            if (COL[i].inside(p) == 1) test = i;
        }
        COL[0].C = COL[test].C;

        if (plink.inside(p) == 1) P_LINK = 1 - P_LINK;
        if (tlink.inside(p) == 1) T_LINK = 1 - T_LINK;


        for (int i = 1; i <= 4; ++i)
        {
            if (mode[i].inside(p) == 1) MODE = i;
        }

        if (ERASE == 0)
        {
            S1.select(p, COL[0].C, DENSITY);
            S2.select(p, COL[0].C, DENSITY);
        }

        if (ERASE == 1)
        {
            S1.erase(p);
            S2.erase(p);
        }


        if (erase.inside(p) == 1)
        {
            ERASE = 1 - ERASE;
            erase.C = color[ERASE];
        }

        if (clear.inside(p) == 1)
        {
            S1.clear();
            S2.clear();
            doPlot();
        }

        if (money.inside(p) == 1)
        {
            TRUNCATE = 1;
            S1.clear();
            S2.clear();
            Color c1 = COL[16].C;
            Color c2 = COL[23].C;
            Color c3 = COL[2].C;
            Color c4 = COL[9].C;
            Color c5 = COL[24].C;
            Color c6 = COL[31].C;
            Color c7 = COL[3].C;
            Color c8 = COL[17].C;
            P1.ACTIVE = 1;
            P2.ACTIVE = 0;
            P3.ACTIVE = 0;
            P1.MODE = 1;
            P_LINK = 1;
            T_LINK = 1;
            MODE = 2;
            S1.showMoney(DENSITY, c1, c2, c3, c4);
            S2.showMoney(DENSITY, c5, c6, c7, c8);
            truncate.POS = 254;
            D.MESSAGE = 50;
            D.repaint();
            C1.repaint();
            C2.repaint();
            C3.repaint();
            doPlot();
        }

        if (plot.inside(p) == 1) doPlot();


        DENSITY = (int) (2.0 * (density.POS - density.x));
        repaint();
    }

    public void mouseDragged(MouseEvent e)
    {
        Point p = pinpoint(e);

        if (ERASE == 0)
        {
            S1.select2(p, COL[0].C, DENSITY);
            S2.select2(p, COL[0].C, DENSITY);
        }

        if (ERASE == 1)
        {
            S1.erase2(p);
            S2.erase2(p);
        }
        repaint();
    }
}
