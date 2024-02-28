import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class TextCanvas extends DoubleBufferedCanvas implements MouseListener
{
    ControlCanvas C;
    PlotControlCanvas C1, C2, C3;
    PlotCanvas P1, P2, P3;
    DocumentCanvas D;
    String[] St = new String[60];
    int MESSAGE;
    ListenSquare[] L = new ListenSquare[50];
    ListenSquare[] G = new ListenSquare[20];
    int[] history = new int[1000];
    int GALLERY;
    int depth = 0;
    Color COL, TCOL, BCOL, BTCOL;
    Font font;
    int FONT1, FONT2;
    Font[][] FONT = new Font[4][10];

    TextCanvas(ControlCanvas C, PlotControlCanvas C1, PlotControlCanvas C2, PlotControlCanvas C3, DocumentCanvas D, PlotCanvas P1, PlotCanvas P2, PlotCanvas P3)
    {
        addMouseListener(this);
        this.C = C;
        this.C1 = C1;
        this.C2 = C2;
        this.C3 = C3;
        this.D = D;
        this.P1 = P1;
        this.P2 = P2;
        this.P3 = P3;
        GALLERY = 0;
        history[0] = 0;

        COL = C.COL[23].C;
        TCOL = Color.white;
        BCOL = C.COL[35].C;
        BTCOL = Color.white;

        MESSAGE = 0;
        font = new Font("Helvetica", Font.PLAIN, 11);
        FONT1 = 1;
        FONT2 = 4;
        L[1] = new ListenSquare(150, 0, 75, 17); //restart
        L[2] = new ListenSquare(225, 0, 78, 17); //back
        L[3] = new ListenSquare(754, 191, 75, 17);  //notation
        L[4] = new ListenSquare(0, 0, 302, 17); //topic display
        /*context dependent*/
        L[5] = new ListenSquare(303, 0, 149, 17);
        L[6] = new ListenSquare(452, 0, 152, 17);
        L[7] = new ListenSquare(303, 191, 150, 17);
        L[8] = new ListenSquare(452, 191, 152, 17);
        L[9] = new ListenSquare(604, 0, 75, 17); //tutorial
        L[10] = new ListenSquare(829, 0, 83, 17); //coords
        L[11] = new ListenSquare(754, 0, 75, 17); //objects
        L[12] = new ListenSquare(150, 191, 75, 17); //doc on
        L[13] = new ListenSquare(829, 191, 83, 17);  //gallery
        L[14] = new ListenSquare(225, 191, 78, 17); //doc off
        L[15] = new ListenSquare(0, 191, 75, 17); //modify
        L[16] = new ListenSquare(679, 0, 75, 17); //math
        L[17] = new ListenSquare(604, 191, 75, 17); //conjecture
        L[18] = new ListenSquare(679, 191, 75, 17); //proof
        L[19] = new ListenSquare(75, 191, 75, 17); //checks

        for (int i = 1; i <= 19; ++i)
        {
            L[i].select = 1;
            L[i].current = 0;
        }
        setPage();
        St[42] = "back";
        St[41] = "restart";
        St[43] = "notation";
        St[44] = "";
        St[45] = "where in math?";
        St[46] = "acknowledgements";
        St[47] = "";
        St[48] = "";
        St[49] = "1.tutorial";
        St[51] = "4.coords";
        St[50] = "3.objects";
        St[52] = "doc:on";
        St[54] = "doc:off";
        St[53] = "gallery";
        St[55] = "modify";
        St[56] = "2.math";
        St[57] = "5.conjecture";
        St[58] = "6.proof";
        St[59] = "checks";
        L[1].future = 0;
        L[2].future = 0;
        L[3].future = 999;
        L[4].future = 200;
        L[5].future = 199;
        L[6].future = 400;
        L[7].future = 300;
        L[8].future = 0;
        L[9].future = 200;
        L[10].future = 270;
        L[11].future = 205;
        L[12].future = 0;
        L[13].future = 998;
        L[14].future = 0;
        L[15].future = 1001;
        L[16].future = 1000;
        L[17].future = 300;
        L[18].future = 900;
        L[19].future = 550;

        /*applet modification*/

        L[20] = new ListenSquare(5, 130, 145, 17);
        L[21] = new ListenSquare(307, 130, 96, 17);
        L[22] = new ListenSquare(403, 130, 96, 17);
        L[23] = new ListenSquare(499, 130, 96, 17);

        for (int i = 24; i <= 29; ++i)
        {
            L[i] = new ListenSquare(307 + 48 * i - 48 * 24, 147, 48, 17);
        }
        L[30] = new ListenSquare(307, 164, 144, 17);

        L[33] = new ListenSquare(612, 130, 15, 15);
        L[31] = new ListenSquare(629, 130, 16, 15);
        L[32] = new ListenSquare(647, 130, 16, 15);
        L[34] = new ListenSquare(612, 147, 15, 15);
        L[35] = new ListenSquare(629, 147, 34, 15);
        L[36] = new ListenSquare(612, 164, 51, 15);

        for (int i = 1; i <= 6; ++i)
        {
            FONT[1][i] = new Font("Helvetica", Font.PLAIN, 8 + i);
            FONT[2][i] = new Font("", Font.PLAIN, 8 + i);
            FONT[3][i] = new Font("Helvetica", Font.PLAIN, 8 + i);
        }

        G[1] = new ListenSquare(305, 20, 17, 17);
        G[2] = new ListenSquare(325, 20, 17, 17);
        G[3] = new ListenSquare(305, 40, 17, 17);
        G[4] = new ListenSquare(325, 40, 17, 17);
        G[5] = new ListenSquare(305, 60, 17, 17);
        G[6] = new ListenSquare(325, 60, 17, 17);
        G[7] = new ListenSquare(345, 60, 17, 17);
        G[8] = new ListenSquare(305, 80, 17, 17);
        G[9] = new ListenSquare(325, 80, 17, 17);
        G[10] = new ListenSquare(305, 100, 17, 17);

    }

    public void paint(Graphics gfx)
    {
        Graphics2D g = (Graphics2D) gfx;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(font);
        g.setColor(COL);
        g.fillRect(0, 0, 912, 220);
        g.setColor(TCOL);
        g.drawRect(303, 0, 301, 299);
        g.drawRect(604, 0, 308, 298);
        g.drawRect(0, 0, 303, 299);

        L[4].render4(g, COL, TCOL);
        for (int i = 1; i <= 3; ++i) L[i].render4(g, BCOL, BTCOL);
        for (int i = 5; i <= 8; ++i) L[i].render4(g, new Color(0, 130, 180), BTCOL);
        for (int i = 9; i <= 19; ++i) L[i].render4(g, BCOL, BTCOL);

        g.setColor(BTCOL);
        g.drawString(St[53], 837, 203); //gallery
        g.drawString(St[42], 235, 13); //back
        g.drawString(St[43], 762, 203);  //notation
        g.drawString(St[52], 160, 203);  //documentation on
        g.drawString(St[54], 235, 203);  //documentation off
        g.drawString(St[45], 308, 13);
        g.drawString(St[46], 460, 13);
        g.drawString(St[47], 308, 203);
        g.drawString(St[48], 460, 203);
        g.drawString(St[49], 612, 13); //tutorial
        g.drawString(St[50], 762, 13); //objects
        g.drawString(St[41], 160, 13); //start
        g.drawString(St[51], 837, 13); //coords
        g.drawString(St[56], 687, 13); //math
        g.drawString(St[57], 612, 203); //conj
        g.drawString(St[58], 687, 203); //proof
        g.drawString(St[55], 10, 203); //modify
        g.drawString(St[59], 85, 203); //checks

        g.setColor(TCOL);
        g.drawString(St[0], 10, 13);


        g.setColor(TCOL);
        for (int i = 1; i <= 12; ++i)
        {
            g.drawString(St[i], 5, 18 + 14 * i);
            g.drawString(St[i + 12], 307, 14 * i + 18);
            g.drawString(St[i + 24], 610, 14 * i + 18);
        }


        if (GALLERY == 1)
        {
            for (int i = 1; i <= 10; ++i)
            {
                G[i].render(g, new Color(150, 0, 255));
                g.setColor(Color.white);
                g.drawString(Integer.toString(i), G[i].x + 3, G[i].y + 13);
            }
        }

        if (MESSAGE == 1001)
        {
            for (int i = 20; i <= 36; ++i) L[i].render(g, new Color(255, 150, 0));
            for (int i = 31; i <= 36; ++i) L[i].render(g, new Color(255, 255, 0));
            if (P1.on == 0) L[31].render(g, new Color(255, 150, 0));
            if (P2.on == 0) L[32].render(g, new Color(255, 150, 0));
            if (P3.on == 0) L[33].render(g, new Color(255, 150, 0));
            if (D.on == 0) L[34].render(g, new Color(255, 150, 0));
            if (C.on == 0) L[35].render(g, new Color(255, 150, 0));
            if (this.on == 0) L[36].render(g, new Color(255, 150, 0));

            L[20 + FONT1].render(g, new Color(255, 255, 0));
            L[23 + FONT2].render(g, new Color(255, 255, 0));

            g.drawString("text window color", 10, 143);
            g.drawString("Helvetica", 312, 143);
            g.drawString("Plain", 407, 143);
            g.drawString("Helvetica", 502, 143);
            for (int i = 1; i <= 6; ++i)
            {
                g.drawString(Integer.toString(i + 8), 312 + 48 * i - 45, 160);
            }
            g.drawString("reset font", 317, 177);
        }


    }

    void changePage(int a, int b, int c)
    {

        if (a > 2)
        {
            ++depth;
            history[depth] = b;
        }
        if (a == 0) depth = 0;
        if ((depth > 0) && (a == 2)) --depth;
        L[2].future = history[depth];
        MESSAGE = c;
        for (int i = 1; i <= 19; ++i) L[i].current = c;
        setPage();
        repaint();
    }

    public void mousePressed(MouseEvent e)
    {
    }

    public void mouseReleased(MouseEvent e)
    {
    }

    public void mouseEntered(MouseEvent e)
    {
        D.MESSAGE = 3;
        if (D.ACTIVE == 1) D.repaint();
    }

    public void mouseExited(MouseEvent e)
    {
        D.MESSAGE = -1;
        if (D.ACTIVE == 1) D.repaint();
    }

    public void mouseClicked(MouseEvent e)
    {
        Point p = pinpoint(e);
        int test = -1;
        for (int i = 1; i <= 3; ++i)
        {
            if (L[i].select * L[i].inside(p) == 1) test = i;
        }

        for (int i = 5; i <= 19; ++i)
        {
            if (L[i].select * L[i].inside(p) == 1) test = i;
        }
        if (test == 12) test = -1;
        if (test == 14) test = -1;
        if (test > -1) changePage(test, L[test].current, L[test].future);

        if (L[12].inside(p) == 1)
        {
            D.ACTIVE = 1;
            D.repaint();
        }
        if (L[14].inside(p) == 1)
        {
            D.ACTIVE = 0;
            D.repaint();
        }
        repaint();

        if (GALLERY == 1)
        {
            test = 0;
            for (int i = 1; i <= 10; ++i)
            {
                if (G[i].inside(p) == 1) test = i;
            }
            if (test > 0) doGallery(test);
        }

        if (MESSAGE == 1001)
        {
            test = 0;
            for (int i = 20; i <= 36; ++i)
            {
                if (L[i].inside(p) == 1) test = i;
            }

            if (test == 20)
            {
                COL = C.COL[0].C;
                TCOL = C.S1.contrast(COL);
                D.COL = COL;
                D.TCOL = TCOL;
                repaint();
                D.repaint();
            }

            if (test == 21)
            {
                FONT1 = 1;
            }

            if (test == 22)
            {
                FONT1 = 2;
            }
            if (test == 23)
            {
                FONT1 = 3;
            }

            for (int i = 24; i <= 29; ++i)
            {
                if (test == i) FONT2 = i - 24 + 1;
            }
            if (test == 30)
            {
                FONT1 = 1;
                FONT2 = 4;
            }
            if ((test >= 21) && (test <= 30))
            {
                font = FONT[FONT1][FONT2];
                D.font = font;
                C1.font = font;
                C2.font = font;
                C3.font = font;
                D.repaint();
                C1.repaint();
                C2.repaint();
                C3.repaint();
                repaint();
            }

            if (test == 31) P1.on = 1 - P1.on;
            if (test == 32) P2.on = 1 - P2.on;
            if (test == 33) P3.on = 1 - P3.on;
            if (test == 34) D.on = 1 - D.on;
            if (test == 35) C.on = 1 - C.on;
            if (test == 36) this.on = 1 - this.on;
        }


    }

    void setScale(int a1, int a2, int a3)
    {
        C1.P.XSCALE = a1;
        C2.P.XSCALE = a2;
        C3.P.XSCALE = a3;
        C1.P.YSCALE = a1;
        C2.P.YSCALE = a2;
        C3.P.YSCALE = a3;
        C1.aspect.POS = 80;
        C2.aspect.POS = 80;
        C3.aspect.POS = 80;
    }

    void setOrigin(int a1, int b1, int a2, int b2, int a3, int b3)
    {
        C1.P.XORIGIN = a1;
        C1.P.YORIGIN = b1;
        C2.P.XORIGIN = a2;
        C2.P.YORIGIN = b2;
        C3.P.XORIGIN = a3;
        C3.P.YORIGIN = b3;
    }


    void doGallery(int k)
    {
        D.MESSAGE = 50;
        D.ACTIVE = 1;
        D.repaint();
        C.S1.clear();
        C.S2.clear();
        C1.P.ACTIVE = 1;
        C2.P.ACTIVE = 1;
        C3.P.ACTIVE = 1;

        if ((k == 1) || (k == 2))
        {

            C.parameter.POS = 150;
            C.truncate.POS = 254;
            C.s = C.getParameter();
            C.TRUNCATE = C.getTruncate();
            C.T_LINK = 1;
            C.P_LINK = 1;
            C.MODE = 3;
            C1.P.MODE = 1;
            C2.P.MODE = 1;
            C3.P.MODE = 1;
            C1.repaint();
            C2.repaint();
            C3.repaint();
            setScale(90, 60, 60);
            setOrigin(0, 80, 0, 30, -140, 50);

            for (int i = 5; i >= 6 - k; --i)
            {
                for (int j = 1; j <= 30; j = j + 5)
                {
                    C.S1.A[i][j].select = 1;
                    C.S1.A[i][j].C = C.COL[14].C;
                    C.S1.A[i][j].dens = 100;
                }

                for (int j = 2; j <= 30; j = j + 5)
                {
                    C.S1.A[i][j].select = 1;
                    C.S1.A[i][j].C = C.COL[6].C;
                    C.S1.A[i][j].dens = 100;
                }

                for (int j = 3; j <= 30; j = j + 5)
                {
                    C.S1.A[i][j].select = 1;
                    C.S1.A[i][j].C = C.COL[16].C;
                    C.S1.A[i][j].dens = 100;
                }

                for (int j = 4; j <= 30; j = j + 5)
                {
                    C.S1.A[i][j].select = 1;
                    C.S1.A[i][j].C = C.COL[17].C;
                    C.S1.A[i][j].dens = 100;
                }

                for (int j = 5; j <= 30; j = j + 5)
                {
                    C.S1.A[i][j].select = 1;
                    C.S1.A[i][j].C = C.COL[19].C;
                    C.S1.A[i][j].dens = 100;
                }
            }

            if (k == 2)
            {
                C.S1.S[10].select = 1;
                C.S1.S[10].C = C.COL[1].C;
                C.S1.S[10].dens = 100;
            }

            C.S1.S[9].select = 1;
            C.S1.S[9].C = C.COL[1].C;
            C.S1.S[9].dens = 100;

            C.doPlot();
        }

        if ((k == 3) || (k == 4))
        {

            C1.P.MODE = 0;
            C2.P.MODE = 0;
            C3.P.MODE = 0;
            C.S1.S[9].select = 1;
            C.S1.S[9].C = C.COL[24].C;
            C.S1.S[9].dens = 100;
            C1.repaint();
            C2.repaint();
            C3.repaint();
            setScale(60, 60, 60);
            setOrigin(0, 40, 0, 0, -140, 40);
            C.T_LINK = 1;
            C.P_LINK = 1;
            C.MODE = 3;
            C.parameter.POS = 300;
            C.truncate.POS = 254;
            C.s = C.getParameter();
            C.TRUNCATE = 1.0;


            for (int j = 1; j <= 30; j = j + 1)
            {
                C.S1.A[2][j].select = 1;
                if (k == 4)
                {
                    C.S1.A[2][j].C = C.COL[16].C;
                    if ((j > 19) && (j < 27)) C.S1.A[2][j].C = C.COL[6].C;
                }
                if (k == 3) C.S1.A[2][j].C = C.COL[17].C;
                C.S1.A[2][j].dens = 100;
            }
            C.doPlot();
        }


        if (k == 5)
        {
            C1.P.MODE = 1;
            C2.P.MODE = 1;
            C3.P.MODE = 1;
            C1.repaint();
            C2.repaint();
            C3.repaint();
            setScale(70, 80, 70);
            setOrigin(0, 40, 0, 40, -120, 40);
            C.T_LINK = 1;
            C.P_LINK = 1;
            C.MODE = 3;
            C.parameter.POS = 100;
            C.s = C.getParameter();
            C.truncate.POS = 254;
            C.TRUNCATE = 1.0;
            for (int j = 1; j <= 15; j = j + 1)
            {
                C.S1.A[5][j].select = 1;
                C.S1.A[5][j].dens = 100;
                C.S1.A[5][31 - j].dens = 100;
                C.S1.A[5][31 - j].select = 1;

                if (j <= 5)
                {
                    C.S1.A[5][j].C = C.COL[9].C;
                    C.S1.A[5][31 - j].C = C.COL[23].C;
                }
                if ((j > 5) && (j <= 10))
                {
                    C.S1.A[5][j].C = C.COL[10].C;
                    C.S1.A[5][31 - j].C = C.COL[24].C;
                }
                if ((j > 10) && (j <= 15))
                {
                    C.S1.A[5][j].C = C.COL[6].C;
                    C.S1.A[5][31 - j].C = C.COL[27].C;
                }

            }

            C.S1.S[9].select = 1;
            C.S1.S[9].C = C.COL[1].C;
            C.S1.S[9].dens = 100;

            C.S1.S[15].select = 1;
            C.S1.S[15].C = C.COL[1].C;
            C.S1.S[15].dens = 100;
            C.doPlot();
        }


        if ((k == 6) || (k == 7))
        {
            C1.P.MODE = 1;
            C2.P.MODE = 1;
            C3.P.MODE = 1;
            C.T_LINK = 1;
            C.P_LINK = 1;
            C.MODE = 3;
            C1.repaint();
            C2.repaint();
            C3.repaint();
            setScale(60, 60, 60);
            setOrigin(0, 0, 0, 0, -120, 0);
            if (k == 7)
            {
                setScale(300, 200, 200);
                setOrigin(0, 0, -100, 0, -240, 0);
            }

            C.parameter.POS = 100;
            C.truncate.POS = 254;
            C.s = C.getParameter();
            C.TRUNCATE = 1.0;
            for (int j = 1; j <= 30; j = j + 1)
            {
                C.S1.A[5][j].select = 1;
                C.S1.A[5][j].C = C.COL[16].C;
                C.S1.A[5][j].dens = 100;
                C.S2.A[5][j].select = 1;
                C.S2.A[5][j].C = C.COL[16].C;
                C.S2.A[5][j].dens = 100;
            }
            C.S1.S[9].select = 1;
            C.S1.S[9].C = C.COL[1].C;
            C.S1.S[9].dens = 100;
            C.S2.S[9].select = 1;
            C.S2.S[9].C = C.COL[1].C;
            C.S2.S[9].dens = 100;
            C.doPlot();
        }


        if ((k == 8) || (k == 9))
        {
            C1.P.MODE = 1;
            C2.P.MODE = 1;
            C3.P.MODE = 1;
            C1.repaint();
            C2.repaint();
            C3.repaint();
            setScale(60, 60, 60);
            setOrigin(0, -40, 10, -20, -120, -40);
            C.T_LINK = 1;
            C.P_LINK = 1;
            C.MODE = 3;
            C.parameter.POS = 500;
            if (k == 8) C.truncate.POS = 60;
            if (k == 9) C.truncate.POS = 254;
            C.s = C.getParameter();
            C.TRUNCATE = C.getTruncate();

            for (int j = 1; j <= 30; j = j + 1)
            {
                C.S2.A[2][j].select = 1;
                C.S2.A[2][j].C = C.COL[23].C;
                C.S2.A[2][j].dens = 100;
                C.S2.A[5][j].select = 1;
                C.S2.A[5][j].C = C.COL[2].C;
                C.S2.A[5][j].dens = 100;
            }
            C.S2.S[9].select = 1;
            C.S2.S[9].C = C.COL[13].C;
            C.S2.S[9].dens = 100;
            C.doPlot();
        }


        if (k == 10)
        {
            C1.P.MODE = 0;
            C2.P.MODE = 0;
            C3.P.MODE = 0;
            C1.repaint();
            C2.repaint();
            C3.repaint();
            setScale(80, 80, 80);
            setOrigin(0, 0, 0, 0, -150, 0);
            C.T_LINK = 1;
            C.P_LINK = 1;
            C.MODE = 2;
            C.parameter.POS = 400;
            C.truncate.POS = 254;
            C.s = C.getParameter();
            C.TRUNCATE = 1.0;
            for (int j = 1; j <= 30; j = j + 1)
            {
                C.S1.A[6][j].select = 1;
                C.S1.A[6][j].C = C.COL[16].C;
                C.S1.A[6][j].dens = 100;
                C.S2.A[6][j].select = 1;
                C.S2.A[6][j].C = C.COL[14].C;
                C.S2.A[6][j].dens = 100;
            }
            C.doPlot();
        }
    }


    void setPage()
    {
        for (int i = 0; i <= 39; ++i) St[i] = "";
        D.pix = 0;
        D.repaint();
        GALLERY = 0;
        if (MESSAGE == 0)
        {
            St[0] = "Introduction";
            St[1] = "I wrote this applet to illustrate my new proof";
            St[2] = "of the Goldman-Parker conjecture.  My original";
            St[3] = "proof was complicated, but the new proof is";
            St[4] = "simple enough to explain, fairly completely,";
            St[5] = "with an applet. The applet does not contain";
            St[6] = "a complete rigorous proof, but it does explain";
            St[7] = "all the essential ideas in the proof.  If you";
            St[8] = "want to see the proof in full, download my";
            St[9] = "paper 'A Better Proof of the G.-P. Conjecture'.";
            St[10] = "My idea is that the applet and the paper should";
            St[11] = "complement each other, with the applet giving";
            St[12] = "more intuition and the paper giving more rigor.";

            St[13] = "This text is divided into 6 parts:";
            St[14] = "1. tutorial: explains how to use the applet.";
            St[15] = "2. math: explains the mathematics.";
            St[16] = "3. objects: explains the objects you plot.";
            St[17] = "4. coords: explains the coordinate systems.";
            St[18] = "5. the conjecture: explains the conjecture.";
            St[19] = "6. the proof: explains the proof of the conjecture.";
            St[20] = "You can read these in any order, but the above";
            St[21] = "order is a good one.  Another approach:  after";
            St[22] = "learning how to use the applet, read the conjecture";
            St[23] = "and proof, clicking on the hypertext links to";
            St[24] = "learn about unfamiliar material as you go along.";

            St[25] = "This applet does not run equally well on all";
            St[26] = "browsers.  It the applet appears to be running";
            St[27] = "poorly you can try to improve things by";
            St[28] = "clicking on the 'modify' button and";
            St[29] = "then using the orange keys to change some";
            St[30] = "features of the applet.";

            St[32] = "I welcome your comments or suggestions about the";
            St[33] = "applet. (email: res@math.brown.edu).";

            St[35] = "What to do now?  I recommend that you click";
            St[36] = "the 'tutorial' key and take the tutorial.";


            St[45] = "where in math?";
            St[46] = "acknowledgements";
            St[47] = "";
            St[48] = "";
            L[5].future = 199;
            L[6].future = 400;
            L[7].future = 0;
            L[8].future = 0;
            depth = 0;
            history[0] = 0;
            history[1] = 0;
        }

        if (MESSAGE == 110)
        {
            St[0] = "Hermitian geometry";
            St[1] = "Summary:";
            St[2] = "The Hermitian geometry we have in mind";
            St[3] = "is the geometry of C^3 and CP^2";
            St[4] = "When C^3 is equipped with the standard form";
            St[5] = "of type (2,1). Equipped with this form";
            St[6] = "C^3 is usually denoted by C^(2,1).";
            St[7] = "The group of form preserving complex linear";
            St[8] = "transformations of C^(2,1) is called SU(2,1).";
            St[9] = "This group acts naturally on CP^2, and";
            St[10] = "the group action there is called PU(2,1).";
            St[11] = "To understand this stuff, you need to learn";
            St[12] = "about these topics:";

            St[13] = "1. The standard form of type (2,1).";
            St[15] = "2. What is CP^2.";
            St[17] = "3. What is PU(2,1)";
            St[19] = "4. element classification in PU(2,1).";
            St[20] = "a. elliptics";
            St[21] = "b. parabolics.";
            St[22] = "c. loxodromics.";
            St[23] = "d. C-reflections.";

            St[45] = "1. type (2,1) form";
            St[46] = "2. CP^2";
            St[47] = "PU(2,1)";
            St[48] = "element classification";
            L[5].future = 221;
            L[6].future = 781;
            L[7].future = 220;
            L[8].future = 111;
        }

        if (MESSAGE == 111)
        {
            St[0] = "element classification";
            St[1] = "The elements of PU(2,1) are classified into";
            St[2] = "3 types:";
            St[4] = "elliptic elements";
            St[5] = "parabolic elements";
            St[6] = "loxodromic elements";
            St[8] = "A C-reflection is a special kind of elliptic";
            St[9] = "element pertinent to our constructions.";

            St[45] = "elliptic elements.";
            St[46] = "parabolic elements";
            St[47] = "loxodromic elements";
            St[48] = "C-reflections";
            L[5].future = 360;
            L[6].future = 361;
            L[7].future = 362;
            L[8].future = 350;
        }

        if (MESSAGE == 120)
        {
            St[0] = "CR objects.";
            St[1] = "Our constructions use some special objects";
            St[2] = "in S^3:";
            St[4] = "C-circles and C-arcs.";
            St[5] = "R-circles and R-arcs.";
            St[6] = "The contact structure in S^3.";
            St[8] = "All these objects are respected by the action";
            St[9] = "of PU(2,1) on S^3.";

            St[45] = "C-circles and C-arcs";
            St[46] = "R-circles and R-arcs";
            St[47] = "contact structure";
            St[48] = "PU(2,1)";
            L[5].future = 210;
            L[6].future = 211;
            L[7].future = 770;
            L[8].future = 220;
        }


        if (MESSAGE == 150)
        {
            St[0] = "R-spheres";
            St[1] = "The basic idea of our proof is to construct";
            St[2] = "R-spheres S0,S1,S2 corresponding to each of";
            St[3] = "the generators of the triangle group";
            St[4] = "Actually we just need to consider S1 and S2.";
            St[6] = "An R-sphere is foliated by R-arcs, each of";
            St[7] = "which connects a point on one C-circle to a";
            St[8] = "point on another one, according to a certain";
            St[9] = "rule. Thus, to understand R-spheres you need";
            St[10] = "to understand R-circles, R-arcs";
            St[11] = "C-circles, and C-arcs";
            St[13] = "If you already understand these basic curves,";
            St[14] = "click on the S1 button to see the construction";
            St[15] = "of S1.  The other two R-spheres are constructed";
            St[16] = "in the same way, once the indices are permuted.";
            St[17] = "Click 'show me the money' to see S1 and S2";
            St[18] = "drawn in 3 coordinate systems.";

            St[19] = "I introduced R-spheres, as they appear here, in";
            St[20] = "my paper 'Degenerating the Complex Hyperbolic";
            St[21] = "Ideal Triangle Groups' (Acta #186, 2001)";
            St[45] = "C-circles and C-arcs";
            St[46] = "R-circles and R-arcs";
            St[47] = "triangle groups";
            St[48] = "S1";
            L[5].future = 210;
            L[6].future = 211;
            L[7].future = 280;
            L[8].future = 250;
        }

        if (MESSAGE == 199)
        {
            St[0] = "where in math?";
            St[1] = "This page is supposed to answer the";
            St[2] = "question, 'how does the G.P. conjecture";
            St[3] = "fit into the world of mathematics?'";
            St[4] = "Here is a list of topics, with each";
            St[5] = "one being a subfield of the previous one.";
            St[6] = "geometry and topology";
            St[7] = "infinite symmetry groups.";
            St[8] = "discrete subgroups of lie groups";
            St[9] = "isometry groups of the complex hyperbolic plane.";
            St[10] = "complex hyperbolic triangle groups.";
            St[11] = "complex hyperbolic C-reflection ideal triangle groups.";
            St[13] = "In short, the G.P. conjecture is the statement";
            St[14] = "which classifies complex hyperbolic C-reflection";
            St[15] = "ideal triangle groups up to discreteness.";
            St[16] = "The significance of the conjecture is that";
            St[17] = "these groups are some of the simplest";
            St[18] = "nontrivial discrete isometry groups of the";
            St[19] = "complex hyperbolic plane. Understanding";
            St[20] = "their discreteness is a foundational";
            St[21] = "step in understanding complex hyperbolic";
            St[22] = "discrete groups.";
            St[25] = "For me the main significance of the";
            St[26] = "conjecture is that it was a simply";
            St[27] = "stated and geometrically interesting problem";
            St[28] = "that I could actually solve.";

            St[45] = "";
            St[46] = "";
            St[47] = "";
            St[48] = "";

            L[5].future = 199;
            L[6].future = 199;
            L[7].future = 199;
            L[8].future = 199;

            D.ACTIVE = 1;
            D.repaint();
        }

        if (MESSAGE == 200)
        {
            St[0] = "tutorial part 1";
            St[1] = "This is a 13 step tutorial. Once you finish";
            St[2] = "this page of the tutorial, click on the";
            St[3] = "'tutorial, part 2' key to read the next page.";
            St[4] = "After that, click on 'tutorial, part 3'.";
            St[6] = "1. The DOCUMENTATION TEXT in the window";
            St[7] = "above this one documents the rest of the applet. ";
            St[8] = "Move the mouse over the other parts of the";
            St[9] = "applet and watch this text change. (Don't";
            St[10] = "(worry right now what the documentation ";
            St[11] = "text says.)  You can turn on/off the text";
            St[12] = "by clicking on it or by using the buttons";

            St[13] = "2. Move the mouse to multicolored cluster of";
            St[14] = "35 squares.  This is the COLOR SELECTOR.";
            St[15] = "Click on some of the 35 little squares and";
            St[16] = "watch the 'color' rectangle change color.";

            St[18] = "3. Move the mouse over into the long black bar";
            St[19] = "labelled C1.  This bar is part of the cluster";
            St[20] = "of rectangles, outlined in blue, called the";
            St[21] = "OBJECT SELECTOR 1.  Now click on the";
            St[22] = "C1 bar.  Notice that it changes into the";
            St[23] = "same color that you last selected with the color";
            St[24] = "selector.  Try to turn the C1 bar red.";

            St[25] = "4. Move the mouse to the magenta PLOT BUTTON";
            St[26] = "and press it. A red curve appears in the 3";
            St[27] = "coordinate windows.  You are seeing plots of";
            St[28] = "C1 in 3 coordinate systems. (Don't worry now";
            St[29] = "about what C1 is or what the coordinates are.)";

            St[31] = "5. Click the S12A button.";
            St[32] = "Now click the '1' button, then plot.";
            St[33] = "Now click the '2' button, then replot.";
            St[34] = "Plotting with the 'N' button produces";
            St[35] = "a picture with N times the detail as";
            St[36] = "plotting with the '1' button.";


            St[45] = "tutorial part 2";
            St[46] = "";
            St[47] = "";
            St[48] = "";

            L[5].future = 201;
            L[6].future = 200;
            L[7].future = 200;
            L[8].future = 200;

            D.ACTIVE = 1;
            D.repaint();
        }

        if (MESSAGE == 201)
        {
            St[0] = "tutorial part 2";


            St[1] = "6. Plot the objects C1,C2,E1,E2 all in";
            St[2] = "different colors.  C1 and E2 are part";
            St[3] = "C1, E2 are part of OBJECT SELECTOR 1,";
            St[4] = "the left cluster of black rectangles.";
            St[5] = "C2, E1 are part of OBJECT SELECTOR 2,";
            St[6] = "the right cluster of black rectangles.";
            St[7] = "Object selector N controls the plotting";
            St[8] = "of objects associated to a sphere SN.  The";
            St[9] = "objects E0,Q0,HQ0 are associated to";
            St[10] = "both S1 and S2.  Not all the objects";
            St[11] = "are actually subsets of S1 and S2.";

            St[13] = "7. Click the magenta SHOW ME THE MONEY";
            St[14] = "button. Wait for the plot. You are seeing";
            St[15] = "plots of the 2 spheres:";
            St[16] = "S1 is in red.";
            St[17] = "S2 is in blue.";

            St[19] = "8. Click the magenta TRUNCATE ARCS bar and";
            St[20] = "wait for the replot.  Repeat, clicking";
            St[21] = "different spots along the bar.";
            St[22] = "This bar controls the extent to which the";
            St[23] = "arcs of S21, etc. are drawn.";

            St[25] = "9. Click on the magenta ERASE BUTTON and note";
            St[26] = "that it turns black.  Click several times. You";
            St[27] = "are turning the erase button on/off. When";
            St[28] = "the erase button is highlighted you can de-select";
            St[29] = "objects.  Try de-selecting 3 thin blue";
            St[30] = "rectangles from object selector 1.";
            St[31] = "These should all be colored, if you've just";
            St[32] = "finished steps 7&8. That's the erase button.";

            St[45] = "tutorial part 3";
            St[46] = "";
            St[47] = "";
            St[48] = "";

            L[5].future = 202;
            L[6].future = 201;
            L[7].future = 201;
            L[8].future = 201;
        }

        if (MESSAGE == 202)
        {
            St[0] = "tutorial part 3";
            St[1] = "10. Erase everything, then plot C1.  Move";
            St[2] = "the mouse to the magenta 'select parameter' bar.";
            St[3] = "This is the PARAMETER SELECTOR.  The plotted";
            St[4] = "objects depend on a parameter which you change";
            St[5] = "by clicking on this bar.  Try plotting the";
            St[6] = "same object at different parameters.  See";
            St[7] = "the documentation for more details.";

            St[9] = "11. Move the mouse to the magenta PLOT DENSITY";
            St[10] = "SELECTOR. This bar lets you change the";
            St[11] = "plotting density.  Read the documentation. Try";
            St[12] = "plotting the same object at different densities.";

            St[13] = "12. Now let's learn the ELEV plot window.";
            St[14] = "(PLAN and HYP operate in the same way.)";
            St[15] = "(A) Erase everything, then plot C1 and C2.";
            St[16] = "(B) Click the blue 'aspect' bar.";
            St[17] = "(C) Click the blue 'reset' button.";
            St[18] = "(D) Click the blue 'on(off)' button. Repeat.";
            St[19] = "(E) Click the blue 'ELEV2(ELEV1) button'. Repeat.";
            St[20] = "(F) Move the white line in the blue 'scale'";
            St[21] = "bar to the left of center.  Click on the black part";
            St[22] = "of the plot window and watch the image rescale.";
            St[23] = "(G) Repeat (F), this time with the white line";
            St[24] = "to the right of the center.";

            St[25] = "13. The orange buttons at the bottom of the applet";
            St[26] = "change various features of the applet.  As an";
            St[27] = "exercise, use the color selector, the orange";
            St[28] = "buttons, and the instructions from the";
            St[29] = "documentation text to make this window yellow";
            St[30] = "with green text. (The left orange 'reset'";
            St[31] = "button returns you to the original color scheme.)";

            St[33] = "(final word) If you click a button and";
            St[34] = "there is no response, just wait.  If you";
            St[35] = "click repeatedly before waiting for a";
            St[36] = "response, you can jam the applet, you fool.";

            St[48] = "";
            St[46] = "";
            St[47] = "";
            St[45] = "";

            L[8].future = 202;
            L[6].future = 202;
            L[7].future = 202;
            L[5].future = 202;

        }

        if (MESSAGE == 205)
        {
            St[0] = "objects, part 1";
            St[1] = "This 12 step tutorial assumes you know how";
            St[2] = "to operate the applet. If you don't know how";
            St[3] = "to operate the applet, you should click on";
            St[4] = "the 'tutorial' button and read that.";
            St[5] = "Also, it is a good idea before reading this";
            St[6] = "section to take a glance at the notation.";
            St[7] = "You can access the notation at any time";
            St[8] = "using the 'notation' button.";
            St[9] = "Once you are done with this page, click";
            St[10] = "the 'objects, part 2' button to read the";
            St[11] = "next page.";

            St[13] = "1. C-circles:";
            St[14] = "If you don't know what C-circles are, click";
            St[15] = "on the link and read about them now.";
            St[16] = "The objects C1,C2,E0,E1,E2 are C-circles.";
            St[17] = "The images of C-circles in PLAN1 and HYP1.";
            St[18] = "are round circles (or points.)   Plot all";
            St[19] = "the C-circles and look in PLAN1 and HYP1";

            St[25] = "2. C-arcs:";
            St[26] = "Qj is an arc of Ej for j=0,1,2.";
            St[27] = "Plot Q0,Q1,Q2 using light red, blue, green";
            St[28] = "Plot E0,E1,E2 using dark red, blue, green.";
            St[29] = "Plot HQ0 in dark grey.";
            St[30] = "Move the parameter towards the right end";
            St[31] = "As you do this, watch Q0,Q1,Q2 shrink to points.";
            St[32] = "HQ0 is not a C-arc, but its closely related";
            St[33] = "to E0 and Q0.  Click the link below to read";
            St[34] = "more about HQ0.";

            St[45] = "C-circles and C-arcs";
            St[46] = "E0 and Q0";
            St[47] = "objects, part 2";
            St[48] = "";
            L[5].future = 210;
            L[6].future = 800;
            L[7].future = 206;
            L[8].future = 205;

        }

        if (MESSAGE == 206)
        {
            St[0] = "objects, part 2";
            St[1] = "3. R-circles:";
            St[2] = "If you don't know what an R-circle is, click";
            St[3] = "on the link and read about it now.";
            St[4] = "The figure above shows what we call a";
            St[5] = "'short-long-short combo' or 'SLSC'.";
            St[6] = "There are 120 SLSCs on the object selectors.";
            St[7] = "These are grouped into 4 groups of 30.";
            St[8] = "Each SLSC is an R-circle. The image";
            St[9] = "of an SLSC in PLAN1 is a lemniscate.";
            St[10] = "A lemniscate looks like a figure 8.";
            St[11] = "Plot an SLSC.  Now plot one from each";
            St[12] = "of the 4 groups. Vary the parameter; replot.";

            St[13] = "4. R-arcs";
            St[14] = "Each SLSC is segmented into 3-pieces.  Each";
            St[15] = "represents an R-arc.  Plot some more SLSCs,";
            St[16] = "using a different color for each of the segments,";
            St[17] = "as in the figure above. Look in PLAN1.";

            St[19] = "5. SLSCs labelled by S10A:";
            St[20] = "Consider the 15 SLSCs clustered around the";
            St[21] = "label S10A.  (Functionally, this label";
            St[22] = "only turns on the 'long part' of each SLSC.";
            St[23] = "We'll get to that later.)";
            St[24] = "Each SLSC in the S10A group intersects";
            St[25] = "E0-Q0 once, Q0 once, and C1 once.";
            St[26] = "Let X be an SLSC.  Plot X.";
            St[27] = "Note that the double point of PLAN1(X) is";
            St[28] = "is 0=PLAN1(E0).  This is a consequence of";
            St[29] = "the fact that X and E0 intersect twice.";
            St[30] = "Looking in HYP1 we get the more refined picture";
            St[31] = "that one intersection point lies in E0-Q0 and";
            St[32] = "one in Q0. We can also see that HYP1(X) is a";
            St[33] = "geodesic perpendicular to the geodesic which";
            St[34] = "connects the endpoints of HYP1(Q0).";

            St[45] = "R-circles and R-arcs";
            St[46] = "";
            St[47] = "objects, part 3";
            St[48] = "";

            L[5].future = 211;
            L[6].future = 206;
            L[7].future = 207;
            L[8].future = 206;

            D.pix = 1;
            D.ACTIVE = 0;
            D.repaint();
        }

        if (MESSAGE == 207)
        {
            St[0] = "objects, part 3";
            St[1] = "6. Amplifying Step 6:";
            St[2] = "For each point s on C1 there is (it turns out)";
            St[3] = "a unique R-circle X(s) with these properties:";
            St[4] = "A. s is contained in X(s).";
            St[5] = "B. X(s) intersects E0 twice.";
            St[6] = "C. HYP(X(s)) is a geodesic perpendicular to the";
            St[7] = "geodesic connecting the endpoints of HYP1(Q0).";
            St[8] = "My preprint gives a proof.  For 30 choices of";
            St[9] = "s, the R-circles X(s) are labelled by the SLSCs";
            St[10] = "associated to (E0,Q0;C1).";

            St[13] = "7. The long part:";
            St[14] = "Let X be an SLSC as in steps 6 and 7.  The";
            St[15] = "part of X represented by the middle (long)";
            St[16] = "rectangle is the R-arc which connects C1 to Q0";
            St[17] = "while avoiding E0-Q0. Plot a long part of X &";
            St[18] = "also plot C1 and Q0.  Look at the picture.";
            St[19] = "in PLAN1 and HYP.";

            St[21] = "8. S10";
            St[22] = "S10=S10B u. S10A.";
            St[23] = "Plot this object and see that it is";
            St[24] = "An embedded disk.  The arcs you see";
            St[25] = "are R-arcs, and they are controlled";
            St[26] = "by the long parts of the SLSCs associated.";
            St[27] = "to (E0,Q0;C1)";
            St[28] = "If you want to see the structure more";
            St[29] = "clearly, use the 'truncate arcs' bar";
            St[30] = "after plotting the picture.";
            St[31] = "to read more about S10 click the link.";

            St[45] = "S10";
            St[46] = "";
            St[47] = "objects, part 4";
            St[48] = "";

            L[5].future = 260;
            L[6].future = 207;
            L[7].future = 208;
            L[8].future = 207;

            D.pix = 1;
            D.ACTIVE = 0;
            D.repaint();
        }

        if (MESSAGE == 208)
        {
            St[0] = "objects, part 4";
            St[1] = "9. This brings us to S21.  This object";
            St[2] = "is the image of S10 under the map I1,";
            St[3] = "one of the generators of the triangle group.";
            St[4] = "Plot this object and look in PLAN1 or PLAN2.";
            St[5] = "You can see, more clearly than for S10,";
            St[6] = "that it is an embedded disk.";

            St[8] = "10. S1 = S10 u. S21.";
            St[9] = "These are two disks which meet only at C1.";
            St[10] = "(Thus S1 is an embedded sphere.  Compare Property 1)";
            St[11] = "You can see how the disks intersect by plotting";
            St[12] = "both and looking in PLAN2.";

            St[13] = "11. S2 = S20 u. S21.";
            St[14] = "S2 has the same description as S1, but with";
            St[15] = "indices 1 and 2 swapped.";
            St[16] = "When you push 'Show me the money' it plots";
            St[17] = "S1 and S2.  Do this.  Look at the picture in";
            St[18] = "ELEV2. Note that ELEV2(S1) and ELEV(S2)";
            St[19] = "seem to only intersect in a single point.";
            St[20] = "This fact is the key to the GP conjecture.";

            St[22] = "12. R1 is the unique R-circle contained in S1.";
            St[23] = "R1 intersects each hemisphere of S1 in a pair";
            St[24] = "of R-arcs.  R1 is called the R-axis of S1.";
            St[25] = "Plot pictures of R1 and S1 and you can see that";
            St[26] = "R1 is an axis of bilateral symmetry for S1.";
            St[27] = "If you want to see this bilateral symmetry.";
            St[28] = "go to the gallery and push button 5.";
            St[29] = "R2 plays the same role for S2.";

            St[31] = "Remark: The objects NS(...) and SS(..)";
            St[32] = "are made from the short parts of the SLSCs.";
            St[33] = "These objects are not actually";
            St[34] = "subsets of the spheres S1 and S2. However,";
            St[35] = "they help explain the structure of S1 and S2.";

            St[45] = "S10";
            St[46] = "triangle groups";
            St[47] = "Property 1";
            St[48] = "";

            L[5].future = 260;
            L[6].future = 280;
            L[7].future = 910;
            L[8].future = 208;

            D.pix = 1;
            D.ACTIVE = 0;
            D.repaint();
        }


        if (MESSAGE == 210)
        {
            St[0] = "C-circles and C-arcs";
            St[1] = "A C-circle is the intersection of a complex line";
            St[2] = "with S^3.  This object is a round circle.";
            St[3] = "Every two distinct points in S^3 determine a";
            St[4] = "unique C-circle.";
            St[5] = "The group PU(2,1) permutes the C-circles.";
            St[7] = "A C-arc is a proper arc of a C-circle.";

            St[18] = "To learn more C-circles, see Bill's book.";

            St[45] = "PU(2,1)";
            St[46] = "Bill's book";
            St[47] = "";
            St[48] = "";

            L[5].future = 220;
            L[6].future = 240;
            L[7].future = 211;
            L[8].future = 211;
        }

        if (MESSAGE == 211)
        {
            St[0] = "R-circles and R-arcs";
            St[1] = "An R-circle is a curve in S^3 which is PU(2,1)";
            St[2] = "equivalent to the circle R^2 # S^3.";
            St[3] = "(Here # stands for intersection.)";
            St[4] = "Not all R-circles are round circles.";
            St[5] = "An R-arc is a proper arc of an R-circle.";

            St[7] = "Given 2 distinct points in S^3 there is a";
            St[8] = "circle's worth of R-arcs connecting them.";
            St[9] = "The union of these R-arcs is an analytically";
            St[10] = "embedded sphere called a spinal sphere.";

            St[18] = "To learn more about R-circles, see Bill's book.";

            St[45] = "PU(2,1)";
            St[46] = "Bill's book";
            St[47] = "";
            St[48] = "";

            L[5].future = 220;
            L[6].future = 240;
            L[7].future = 211;
            L[8].future = 211;
        }

        if (MESSAGE == 220)
        {
            St[0] = "PU(2,1)";

            St[1] = "PU(2,1) has many equivalent definitions.  Here";
            St[2] = "we will give 4 such definitions.";
            St[4] = "1. PU(2,1) is the group of bi-holomorphic";
            St[5] = "automorphisms of the open unit ball in C^2.";

            St[7] = "2. PU(2,1) is the group of complex projective";
            St[8] = "automorphisms of CP^2 which stabilize S^3,";
            St[9] = "when S^3 is considered as a subset of CP^2.";

            St[11] = "3. PU(2,1) is the projectivization of SU(2,1).";
            St[12] = "Concretely this means that PU(2,1) is the group";
            St[13] = "of maps (z,w) --> (z',w'), having the form";
            St[14] = "z'=t1/t3;";
            St[15] = "w'=t2/t3;";
            St[16] = "tj=Aj1 z + Aj2 w + Aj3.";
            St[17] = "The 3x3 matrix (Aij) preserves the";
            St[18] = "standard Hermitian form of type (2,1)";

            St[19] = "PU(2,1) is an 8-dimensional real lie";
            St[20] = "group, acting transitively on the open";
            St[21] = "unit ball in C^2. The open unit ball";
            St[22] = "admits a PU(2,1) invariant metric known";
            St[23] = "the complex hyperbolic metric.  When";
            St[24] = "equipped with this metric, the open unit";
            St[25] = "ball is denoted CH^2. Thus one final";
            St[26] = "definition of PU(2,1) is:";
            St[28] = "4. PU(2,1) is the holomorphic isometry";
            St[29] = "group of CH^2.";

            St[36] = "See Bill's book for more information.";

            St[45] = "Bill's book";
            St[46] = "type (2,1) form";
            St[47] = "";
            St[48] = "";

            L[5].future = 240;
            L[6].future = 221;
            L[7].future = 220;
            L[8].future = 220;
        }

        if (MESSAGE == 221)
        {
            St[0] = "type (2,1) form";
            St[1] = "Given two vectors v and w in C3 define";
            St[3] = "<v,w>=v1 [w1] + v2 [w2] - v3[w3].";
            St[5] = "Here v=(v1,v2,v3) and w=(w1,w2,w3) and";
            St[7] = "[z] denotes the complex conjugate of z";
            St[9] = "To say that a matrix A preserves this form";
            St[10] = "is to say that <A(v),A(w)>=<v,w> for all";
            St[11] = "choices of v and w.";
            St[13] = "The form above is the standard Hermitian";
            St[14] = "form of type (2,1).";
            St[18] = "See Bill's book for more information.";
            St[45] = "Bill's book";
            St[46] = "";
            St[47] = "";
            St[48] = "";

            L[5].future = 240;
            L[6].future = 221;
            L[7].future = 221;
            L[8].future = 221;
        }

        if (MESSAGE == 240)
        {
            St[0] = "Bill's book";
            St[1] = "Bill wrote a great book on this subject:";
            St[3] = "William M. Goldman";
            St[4] = "Complex Hyperbolic Geometry";
            St[5] = "Oxford University Press, 1999";
            St[7] = "The ISBN number is 0 19 853793 X";

            St[45] = "";
            St[46] = "";
            St[47] = "";
            St[48] = "";

            L[5].future = 240;
            L[6].future = 240;
            L[7].future = 240;
            L[8].future = 240;
        }

        if (MESSAGE == 250)
        {
            St[0] = "S1";
            St[1] = "S1 is a piecewise analytic sphere embedded";
            St[2] = "in S^3. This object is the union of two";
            St[3] = "piecewise analytic embedded disks:";
            St[5] = "S10 and S21";
            St[7] = "S1 and its companion S2 are the basic objects";
            St[8] = "in the proof of the Goldman-Parker Conjecture";

            St[45] = "S10";
            St[46] = "S12";
            St[47] = "S2";
            St[48] = "";

            L[5].future = 260;
            L[6].future = 261;
            L[7].future = 251;
            L[8].future = 250;
        }

        if (MESSAGE == 251)
        {
            St[0] = "S2";
            St[1] = "S2 is a piecewise analytic sphere embedded";
            St[2] = "in S^3. This object is the union of two";
            St[3] = "piecewise analytic embedded disks:";
            St[5] = "S20 and S21";
            St[7] = "S2 and its companion S1 are the basic objects";
            St[8] = "in the proof of the Goldman-Parker Conjecture";

            St[45] = "S20";
            St[46] = "S21";
            St[47] = "S1";
            St[48] = "";

            L[5].future = 262;
            L[6].future = 263;
            L[7].future = 250;
            L[8].future = 251;
        }

        if (MESSAGE == 260)
        {
            St[0] = "S10";
            St[1] = "HARMONIC POSITION:";
            St[2] = "4 cyclically ordered points a1,b1,a2,b2";
            St[3] = "on a C-circle are in harmonic position if";
            St[4] = "there is a conformal automorphism of the";
            St[5] = "complex line containing the C-circle which";
            St[6] = "maps these points to the vertices of a square.";

            St[8] = "MAIN CONSTRUCTION";
            St[9] = "Given x in C1 there is a unique R-circle R(x)";
            St[10] = "with the following properties:";
            St[11] = "R(x) contains x and intersects E0 twice,";
            St[12] = "once in Q0 and once in E0-Q0. Let a1 and";
            St[13] = "a2 be these intersection points.";
            St[14] = "Let b1 and b2 be the endpoints of Q0.  Then";
            St[15] = "The 4 points a1,b1,a2,b2 are in harmonic pos.";
            St[16] = "S(E0,Q0;x) is defined as the R-arc of R(x)";
            St[17] = "which connects x to Q0.";

            St[19] = "END OF CONSTRUCTION:";
            St[20] = "S10 is defined as the";
            St[21] = "union, over x in C1, of the R-arcs S(E0,Q0;x).";
            St[22] = "If you already know how to use the applet";
            St[23] = "you can plot this object, in whole or part";
            St[24] = "and see what it looks like.";

            St[26] = "SEE THE PICTURE";
            St[27] = "1. Plot C1 and S10.";
            St[28] = "2. Set the 'truncate arcs' slider to auto. replot";
            St[29] = "3. Click on the left part of the 'truncate arcs'";
            St[30] = "slider and incrementally move it to the right.";
            St[31] = "Watch S10 grow out of C1.";
            St[32] = "Try the plot at different parameters.";

            St[45] = "C-circles and C-arcs";
            St[46] = "R-circles and R-arcs";
            St[47] = "PU(2,1)";
            St[48] = "triangle group";

            L[5].future = 210;
            L[6].future = 211;
            L[7].future = 220;
            L[8].future = 280;

            D.pix = 2;
            D.ACTIVE = 0;
            D.repaint();
        }

        if (MESSAGE == 261)
        {
            St[0] = "S20";
            St[1] = "This object has the same definition as";
            St[2] = "S10, but with the indices";
            St[3] = "1 and 2 switched";

            St[45] = "S10";
            St[46] = "";
            St[47] = "";
            St[48] = "";

            L[5].future = 260;
            L[6].future = 261;
            L[7].future = 261;
            L[8].future = 261;
        }

        if (MESSAGE == 262)
        {
            St[0] = "S21";
            St[1] = "This object has the same definition as";
            St[2] = "S10, but with the indices";
            St[3] = "0 and 2 switched.";
            St[5] = "Alternatively, S21=I1(S10).";
            St[6] = "Try plotting S21 and doing the same";
            St[7] = "business with the 'truncate arcs' slider";
            St[8] = "that we suggested for S10.";

            St[45] = "S10";
            St[46] = "";
            St[47] = "";
            St[48] = "";

            L[5].future = 260;
            L[6].future = 262;
            L[7].future = 262;
            L[8].future = 262;
        }

        if (MESSAGE == 263)
        {
            St[0] = "S21";
            St[1] = "This object has the same definition as";
            St[2] = "S10, but with the indices";
            St[3] = "permuted as 0->1->2";

            St[45] = "S10";
            St[46] = "";
            St[47] = "";
            St[48] = "";

            L[5].future = 260;
            L[6].future = 263;
            L[7].future = 263;
            L[8].future = 263;
        }

        if (MESSAGE == 270)
        {
            St[0] = "coords.";
            St[1] = "There are 6 coordinate systems used:";
            St[3] = "1a. ELEV1;  1b. ELEV2";
            St[4] = "2a. PLAN1;  2b. PLAN2";
            St[5] = "3a. HYP1;   3b HYP2";
            St[7] = "These coorespond to the 3 windows on";
            St[8] = "the applet with the same names.";
            St[9] = "The left  window toggles ELEV1/ELEV2.";
            St[10] = "The middle window toggles PLAN1/PLAN2.";
            St[11] = "right window toggles HYP1/HYP2.";
            St[12] = "These coordinates are all maps from S^3,";
            St[13] = "the 3-sphere, to surfaces. Click on";
            St[14] = "the  buttons below to get more info";
            St[15] = "about the coordinate systems.";
            St[17] = "There is an analogy between the";
            St[18] = "coordinate systems and the three planar";
            St[19] = "projections of cylindrical coordinates.";
            St[20] = "Let (r,theta,z) be the standard cylindrical";
            St[21] = "coordinate system in R^3.  Then";
            St[22] = "ELEV is like projection to the (theta,z) cylinder.";
            St[23] = "PLAN is like projection to the (r,theta) plane";
            St[24] = "HYP is like projection to the (r,z) plane.";
            St[25] = "The 3 projections ELEV2, HYP2, PLAN2";
            St[26] = "are perfectly adapted to the geometric structure";
            St[27] = "of our construction. The coordinate systems";
            St[28] = "ELEV1, HYP1, PLAN1 are not quite as well";
            St[29] = "adapted to our construction, but they are";
            St[30] = "more familiar coordinate systems and sometimes";
            St[31] = "it is easier to compute in them.";
            St[32] = "Also, the coordinates HYP1 and PLAN1 are";
            St[33] = "less computationally intensive, and hence";
            St[34] = "the pictures in these coordinates are plotted";
            St[35] = "more quickly than in HYP2 and PLAN2.";

            St[45] = "PLAN1 and PLAN2";
            St[46] = "ELEV1 and ELEV2";
            St[47] = "HYP1 and HYP2";
            St[48] = "";

            L[5].future = 275;
            L[6].future = 276;
            L[7].future = 777;
            L[8].future = 270;
        }

        if (MESSAGE == 275)
        {
            St[0] = "PLAN1 and PLAN2";
            St[1] = "Both coord. systems are based on E0 and Q0.";
            St[2] = "PLAN1 is based on 1 special point contained";
            St[3] = "in the interior of Q0.  PLAN1 is naturally";
            St[4] = "adapted to the group of parabolic elements";
            St[5] = "which stabilize this special point.";
            St[6] = "This stabilizer group does not play a direct";
            St[7] = "role in our construction and for this reason";
            St[8] = "PLAN1 is not quite adapted to our constructions.";
            St[9] = "However, in practice it is much easier to";
            St[10] = "compute in PLAN1.";
            St[12] = "PLAN2 is based on the 2 endpoints of Q0.";
            St[13] = "PLAN2 is naturally adapted to the group of";
            St[14] = "loxodromic elements which stabilizer Q0.";
            St[15] = "This coordinate system is theoretically much";
            St[16] = "nicer, for our construction, but but we";
            St[17] = "don't have a good handle on how to compute";
            St[18] = "things in it.";

            St[19] = "To give just one example.  The images of";
            St[20] = "C-circles in PLAN1 are round circles.";
            St[21] = "The images of C-circles in PLAN2 are mystery";
            St[22] = "curves. We don't even have a formula!";
            St[23] = "Neither coordinate system plays a big role";
            St[24] = "in our proof. ELEV2 is the main system.";
            St[26] = "We note that PLAN1 and PLAN2 converge";
            St[27] = "to each other as the parameter s tends to";
            St[28] = "sqrt(125/3).  The point is that the 2 points";
            St[29] = "on which PLAN2 is based coalesce to the 1";
            St[30] = "point on which PLAN1 is based.";
            St[31] = "Try it out on the applet.";

            St[45] = "PLAN1";
            St[46] = "PLAN2";
            St[47] = "E0 and Q0";
            St[48] = "C-Circles and C-arcs";

            L[5].future = 701;
            L[6].future = 785;
            L[7].future = 800;
            L[8].future = 210;
        }

        if (MESSAGE == 276)
        {
            St[0] = "ELEV1 and ELEV2";
            St[1] = "Both coord. systems are based on E0 and Q0.";
            St[2] = "ELEV1 is based on 1 special point contained";
            St[3] = "in the interior of Q0.  ELEV1 is naturally";
            St[4] = "adapted to the group of parabolic elements";
            St[5] = "which stabilize this special point.";
            St[6] = "This stabilizer group does not play a direct";
            St[7] = "role in our construction and for this reason";
            St[8] = "ELEV1 is not quite adapted to our constructions.";
            St[9] = "However, in practice it is much easier to";
            St[10] = "compute in ELEV1.";
            St[12] = "ELEV2 is based on the 2 endpoints of Q0.";
            St[13] = "ELEV2 is naturally adapted to the group of";
            St[14] = "loxodromic elements which stabilizer Q0.";
            St[15] = "This coordinate system is theoretically much";
            St[16] = "nicer, for our construction, but but we";
            St[17] = "don't have a good handle on how to compute";
            St[18] = "things in it.";
            St[19] = "In spite of the competing benefits of the";
            St[20] = "two coordinate systems, we overwhelmingly use";
            St[21] = "ELEV2 in our proof.  ELEV2 is the main";
            St[22] = "coordinate system in the applet and the other";
            St[23] = "coordinate systems, including ELEV1, are added";
            St[24] = "mainly to complement ELEV2";
            St[26] = "We note that ELEV1 and ELEV2 converge";
            St[27] = "to each other as the parameter s tends to";
            St[28] = "sqrt(125/3).  The point is that the 2 points";
            St[29] = "on which ELEV2 is based coalesce to the 1";
            St[30] = "point on which ELEV1 is based.";
            St[31] = "Try it out on the applet.";
            St[32] = "Actually, the convergence is only up to";
            St[33] = "a scaling factor in the vertical direction.";
            St[34] = "I adjusted the scaling factor by hand.";

            St[45] = "ELEV1";
            St[46] = "ELEV2";
            St[47] = "E0 and Q0";
            St[48] = "C-Circles and C-arcs";

            L[5].future = 924;
            L[6].future = 700;
            L[7].future = 800;
            L[8].future = 210;
        }

        if (MESSAGE == 280)
        {
            St[0] = "triangle groups";
            St[1] = "In this applet, 'triangle group' always stands";
            St[2] = "for complex hyperbolic reflection ideal triangle";
            St[3] = "group.  Such a group is constructed as follows:";
            St[4] = "Let p0,p1,p2 be three points in S^3";
            St[5] = "which do not all lie in the same complex line.";
            St[6] = "Let Cj be the complex line which contains";
            St[7] = "p(j-1) and p(j+1), with indices taken mod 3.";
            St[8] = "Let Ij be the C-reflection fixing Cj.  Then";
            St[9] = "The triangle group is the group generated by";
            St[10] = "I0,I1 and I2.";
            St[12] = "We can get the complete family of groups";
            St[13] = "up to conjugation in PU(2,1), by taking";
            St[15] = "p0=(b,[b]);";
            St[16] = "p1=(b,b);";
            St[17] = "p2=([b],[b])";
            St[19] = "Here b=(s+i)/sqrt(2+s^2) and";
            St[20] = "[b] is the complex conjugate of b.";
            St[22] = "The parameter is s and the parameter";
            St[23] = "range of interest is [0,sqrt(125/3)).";
            St[24] = "In this range the product I0.I1.I2 is loxodromic.";

            St[45] = "PU(2,1)";
            St[46] = "C-reflections";
            St[47] = "";
            St[48] = "loxodromic elements";

            L[5].future = 220;
            L[6].future = 350;
            L[7].future = 280;
            L[8].future = 362;
            D.pix = 3;
            D.ACTIVE = 0;
            D.repaint();
        }

        if (MESSAGE == 300)
        {
            St[0] = "the conjecture";
            St[1] = "The Goldman-Parker Conjecture classifies ";
            St[2] = "the complex hyperbolic ideal reflection";
            St[3] = "triangle groups according to discreteness.";
            St[5] = "Bill Goldman and John Parker made their";
            St[6] = "Conjecture in [GP] and there made partial";
            St[7] = "progress on it.";

            St[9] = "In 1997 I found a computer-aided proof of";
            St[10] = "the conjecture, which I published in [S1].";
            St[11] = "The proof was quite complicated.";

            St[13] = "This year (2004) I found a much better";
            St[14] = "proof, which I've written down in [S2].";
            St[15] = "This applet is based on the material in [S2]";

            St[17] = "To read about the precise statement of the";
            St[18] = "conjecture, click the link below.";

            St[19] = "REFERENCES:";

            St[21] = "[GP] W. Goldman and J.R. Parker,";
            St[22] = "Complex Hyperbolic Triangle Groups,";
            St[23] = "J. reine agnew Math. #425, 1992";

            St[25] = "[S1] R.E. Schwartz,";
            St[26] = "Ideal Triangle Groups, Dented Tori, and";
            St[27] = "Numerical Analysis'.";
            St[28] = "Annals of Math #153, 2001";

            St[30] = "[S2] R.E. Schwartz,";
            St[31] = "A Better Proof of the Goldman-Parker Conj.";
            St[32] = "preprint, 2004";

            St[45] = "triangle groups";
            St[46] = "precise statement";
            St[47] = "";
            St[48] = "";

            L[5].future = 280;
            L[6].future = 500;
            L[7].future = 300;
            L[8].future = 300;
        }

        if (MESSAGE == 350)
        {
            St[0] = "C-reflections";
            St[1] = "A C-reflection is a certain kind of order 2";
            St[2] = "element in PU(2,1).  Here are 2 equivalent:";
            St[3] = "definitions:";
            St[5] = "1.  A C-reflection is an element conjugate";
            St[6] = "in PU(2,1) to the map (z,w) --> (z,-w).";
            St[8] = "2. A C-reflection is an involution which fixes";
            St[9] = "A C-circle.";
            St[11] = "The C-reflections are the building blocks of the";
            St[12] = "triangle groups.";

            St[45] = "PU(2,1)";
            St[46] = "C-circles and C-arcs";
            St[47] = "triangle groups";
            St[48] = "";

            L[5].future = 220;
            L[6].future = 210;
            L[7].future = 280;
            L[8].future = 350;
        }

        if (MESSAGE == 360)
        {
            St[0] = "elliptic elements";
            St[1] = "An elliptic element of PU(2,1) is";
            St[2] = "an element which fixes a point inside";
            St[3] = "the open unit ball in C^2.";

            St[45] = "PU(2,1)";
            St[46] = "parabolic elements";
            St[47] = "loxodromic elements";
            St[48] = "Bill's book";

            L[5].future = 220;
            L[6].future = 361;
            L[7].future = 362;
            L[8].future = 240;
        }

        if (MESSAGE == 361)
        {

            St[0] = "parabolic elements";
            St[1] = "An parabolic element of PU(2,1) is";
            St[2] = "an element which fixes a unique point";
            St[3] = "on S^3";

            St[5] = "parabolic elements have no fixed points";
            St[6] = "in CH^2.  However, for any d>0 there are points";
            St[7] = "in CH^2 which are moved less than d by and";
            St[8] = "given parabolic element.";

            St[45] = "PU(2,1)";
            St[46] = "elliptic elements";
            St[47] = "loxodromic elements";
            St[48] = "Bill's book";

            L[5].future = 220;
            L[6].future = 360;
            L[7].future = 362;
            L[8].future = 240;
        }

        if (MESSAGE == 362)
        {
            St[0] = "loxodromic elements";
            St[1] = "An loxodromic element of PU(2,1) is";
            St[2] = "an element which fixes exactly 2 points";
            St[3] = "on S^3.";

            St[5] = "For any given loxodromic element there is some";
            St[6] = "d>0 such that every point in CH^2 is moved";
            St[7] = "more than d units by the loxodromic element.";

            St[45] = "PU(2,1)";
            St[46] = "elliptic elements";
            St[47] = "parabolic elements";
            St[48] = "Bill's book";

            L[5].future = 220;
            L[6].future = 360;
            L[7].future = 361;
            L[8].future = 240;
        }

        if (MESSAGE == 400)
        {
            St[0] = "acknowledgements";
            St[1] = "This work is supported by:";
            St[3] = "The University of Maryland";
            St[4] = "www.math.umd.edu";
            St[6] = "The Institute for Advanced Study";
            St[7] = "www.ias.edu";
            St[9] = "The National Science Foundation";
            St[10] = "grant DMS-0305047";
            St[11] = "www.nsf.gov";
            St[13] = "A Guggenheim Fellowship";
            St[14] = "www.gf.org";
            St[16] = "I'd like to thank these institutions for their";
            St[17] = "generous support.  I'd also like to thank";

            St[19] = "My wife Brienne Brown, for design suggestions.";

            St[21] = "Nathan Dunfield, for much helpful feedback.";
            St[22] = "www.its.caltech.edu/~dunfield";

            St[25] = "Bill Goldman and John Parker, for many helpful";
            St[26] = "conversations over the years, and also for";
            St[27] = "their conjecture.";
            St[28] = "www.math.umd.edu/~wmg";
            St[29] = "maths.dur.ac.uk/~dma0jrp";


            St[45] = "";
            St[46] = "";
            St[47] = "";
            St[48] = "";
            L[5].future = 400;
            L[6].future = 400;
            L[7].future = 400;
            L[8].future = 400;
        }

        if (MESSAGE == 500)
        {
            St[0] = "precise statement";
            St[1] = "In this applet, 'triangle group' always stands for";
            St[2] = "complex hyperbolic reflection ideal triangle group.";
            St[3] = "The triangle groups are generated by three";
            St[4] = "C-reflections I0,I1,I2 in PU(2,1).  Up to";
            St[5] = "conjugation in PU(2,1) there is a 1-parameter";
            St[6] = "family of such groups.";
            St[8] = "THE CONJECTURE:";
            St[9] = "A triangle group is discrete if and only if";
            St[10] = "the product I0.I1.I2 is not an elliptic element.";
            St[11] = "This range corresponds to parameters in the";
            St[12] = "interval [0,sqrt(125/3)].";
            St[13] = "Goldman and Parker verified their conjecture for";
            St[14] = "parameters in the interval [0,sqrt(35)) but";
            St[16] = "couldn't analyze the interval (sqrt(35),sqrt(125/3]";
            St[18] = "When s>sqrt(125/3) the element I0.I1.I2 is an";
            St[19] = "infinite order elliptic element and hence the";
            St[20] = "group is indiscrete.  Some of our constructions,";
            St[21] = "when suitably modified, also work for s in this";
            St[22] = "range, and some of the pix would look similar.";
            St[23] = "However our discreteness proof (obviously) breaks";
            St[24] = "down for s>sqrt(125/3).";

            St[45] = "triangle groups";
            St[46] = "C-reflections";
            St[47] = "PU(2,1)";
            St[48] = "elliptic elements";

            L[5].future = 280;
            L[6].future = 350;
            L[7].future = 220;
            L[8].future = 360;
        }


        if (MESSAGE == 550)
        {
            St[0] = "checks";
            St[1] = "Here we explain some sanity checks which";
            St[2] = "convince us that we have not made";
            St[3] = "programming mistakes.  You can test these";
            St[4] = "for yourself (except #7) using the applet.";
            St[6] = "1. The C-circles C1,C1,E1,E2 map to round";
            St[7] = "circles in PLAN1 and HYP1.";
            St[8] = "2. The R-arcs of S10A, etc. map to arcs of";
            St[9] = "lemniscates in PLAN1.";
            St[10] = "3. Each arc of S10A, S10B, S20A, S20B maps to";
            St[11] = "a point in ELEV2.";
            St[13] = "4. R1 bisects C1 in HYP2 and PLAN2, as it should.";
            St[14] = "Likewise for R2 and C2.";
            St[15] = "5. ELEV2(C1) and ELEV2(C2) are isometric to each.";
            St[16] = "other and each curve has 180 degree rotational";
            St[17] = "symmetry.";
            St[18] = "6. Each arc X of S12A, aside from the middle";
            St[19] = "arc, has a partner arc Y such that ELEV2(X)";
            St[20] = "and ELEV2(Y) share an endpoint.  Moreover, the";
            St[21] = "other endpoints of ELEV2(X) and ELEV2(Y) have";
            St[22] = "the same height. Likewise for S12B,S21A,S21B.";
            St[23] = "7. The plots of ELEV1(C1) and ELEV1(C2) agree";
            St[24] = "visually with plots using the direct formulas.";
            St[25] = "8. The arcs of S10A,S10B,S20A,S20B are straight";
            St[26] = "horizontal rays in HYP2 and circular arcs in HYP1.";
            St[27] = "9. Each arc of S10A makes a 180 degree angle with";
            St[28] = "the corresponding arc of S12A, at an endpoint,";
            St[29] = "in PLAN1 and PLAN2. Likewise for S10B and S12B.";
            St[30] = "Likewise for S20A and S21A, and for S20B and S21B.";
            St[32] = "Each of these phenomena is predicted from the";
            St[33] = "theory. If we made a gross programming error";
            St[34] = "in our code, the chances would be almost nil";
            St[35] = "that the 9 above listed properties would still";
            St[36] = "hold.";

            St[45] = "C-circles and C-arcs";
            St[46] = "R-circles and R-arcs";
            St[47] = "";
            St[48] = "";

            L[5].future = 210;
            L[6].future = 211;
            L[7].future = 550;
            L[8].future = 550;
        }


        if (MESSAGE == 700)
        {

            St[0] = "ELEV2 part 1";

            St[1] = "The ELEV2 coordinate system is the main";
            St[2] = "one of the 6 coordinate systems used.";
            St[3] = "ELEV2 is based on the C-arc Q0 and the";
            St[4] = "C-circle E0 which contains it.";

            St[6] = "HARMONIC POSITION:";
            St[7] = "4 cyclically ordered points a1,b1,a2,b2";
            St[8] = "on a C-circle are in harmonic position if";
            St[9] = "there is a conformal automorphism of the";
            St[10] = "complex line containing the C-circle which";
            St[11] = "maps these points to the vertices of a square.";

            St[13] = "HARMONIC R-ARCS:";
            St[14] = "Let b1 and b2 be the endpoints of Q0";
            St[15] = "An R-arc A is harmonic if:";
            St[16] = "A intersects Q0 in a point a1;";
            St[17] = "A intersects E0-Q0 in a point a2;";
            St[18] = "the points a1,b1,a2,b2, are in harmonic pos.";

            St[19] = "SPACE OF HARMONIC ARCS";
            St[20] = "Note that there is a circle's worth";
            St[21] = "of R-arcs joining a1 from a2.  From this";
            St[22] = "we see that the space H of harmonic arcs";
            St[23] = "is a cylinder, essentially the interior";
            St[24] = "of Q0 cross a circle";

            St[26] = "MAIN DEFINITION:";
            St[27] = "Every point x in S^3-E0 is contained in a";
            St[28] = "unique harmonic arc A(x).  ELEV2 is";
            St[29] = "the map which sends x to the arc A(x).";
            St[30] = "Here we interpret A(x) as a point in H.";
            St[31] = "Thus ELEV2 maps S^3-E0 to the cylinder H.";
            St[33] = "The black cylinder in the ELEV2 window is H";
            St[34] = "Try plotting some objects in ELEV2 coordinates.";

            St[45] = "C-circles and C-arcs";
            St[48] = "R-circles and R-arcs";
            St[47] = "Q0 and E0";
            St[46] = "ELEV2 part 2";

            L[5].future = 210;
            L[8].future = 211;
            L[7].future = 800;
            L[6].future = 750;

            D.pix = 5;
            D.ACTIVE = 0;
            D.repaint();
        }

        if (MESSAGE == 701)
        {
            St[0] = "PLAN1 part 1";
            St[1] = "The PLAN1 coordinate system is an auxilliary";
            St[2] = "coordinate system designed to complement and";
            St[3] = "sometimes clarify the images in the main";
            St[4] = "coordinate system, ELEV2. Here we discuss";
            St[5] = "the structure of PLAN1.";
            St[7] = "A SPECIAL INVOLUTION";
            St[8] = "Given the construction of our triangle groups";
            St[9] = "The map J(z,w)=([w],[z]). plays a special role.";
            St[10] = "(Here [z] is the complex conjugate of z.)";
            St[11] = "J fixes the point p0 and swaps p1 and p2.";
            St[12] = "Hence, conjugation by J is an automorphism of";
            St[13] = "the triangle group.  The map J stabilizes";
            St[14] = "the arc Q0 and has a unique fixed point q in Q0.";
            St[16] = "TWO PROJECTIONS";
            St[17] = "Let Y: S^3-q --> CxR be stereographic proj.";
            St[18] = "Let P: CxR --> C be ordinary projection.";

            St[19] = "MAIN DEFINITION";
            St[20] = "We have PLAN1=PY: S^3-q -->C.";
            St[21] = "By construction PLAN1 maps E0-q to 0.";

            St[23] = "LINKING CRITERION";
            St[24] = "One useful feature of PLAN1 is that it";
            St[25] = "let's us see easily if a curve in S^3 links";
            St[26] = "E0.  If A is a curve in S^3 which links";
            St[27] = "E0 then PLAN1(A) is a closed loop in C";
            St[28] = "which surrounds 0. For the curves of";
            St[29] = "interest to us, the converse is also true:";
            St[30] = "PLAN1(A) surrounds 0 only if A links E0";
            St[31] = "In the next section we discuss these curves.";

            St[48] = "stereographic proj.";
            St[46] = "E0 and Q0";
            St[47] = "triangle groups";
            St[45] = "PLAN1 part 2";

            L[8].future = 780;
            L[6].future = 800;
            L[7].future = 280;
            L[5].future = 705;
        }

        if (MESSAGE == 705)
        {
            St[0] = "PLAN1 part 2";
            St[1] = "The curves of interest to us are C-circles,";
            St[2] = "C-arcs, R-circles, and R-arcs";

            St[4] = "IMAGES OF C-CIRCLES:";
            St[5] = "If C is a C-circle in S^3-q then PLAN1(C) is a";
            St[6] = "round circle which surrounds 0. The idea of";
            St[7] = "proof is that PLAN1 is holomorphic on complex";
            St[8] = "and hence acts as a Mobius transformation";
            St[9] = "on such lines.  Try plotting C1,C2,E1,E2";
            St[10] = "and looking at the image in PLAN1.  Notice";
            St[11] = "that PLAN1(C1) and PLAN1(C2) are symmetrically";
            St[12] = "placed with repect to the real axis.  The same";
            St[13] = "goes for PLAN1(E1) and PLAN1(E2).  This is one";
            St[14] = "of the consequences of our choice of the";
            St[15] = "point q fixed by the map J discussed above.";
            St[16] = "PLAN1(C) surrounds the origin iff C links E0.";
            St[17] = "The image of a C-arc in PLAN1 is an arc of";
            St[18] = "a circle.";
            St[19] = "IMAGES OF R-CIRCLES";
            St[20] = "If A is an R-CIRCLE in S^3-q then PLAN1(A) is a";
            St[21] = "lemniscate.  I don't have a good geometric";
            St[22] = "reason for this.  See Bill's book for a proof.";
            St[23] = "You can see the lemniscates in action by";
            St[24] = "plotting a short-long-short combinaction";
            St[25] = "of thin rectangles from the object selectors";
            St[26] = "and looking at the image in PLAN1.  Such a";
            St[27] = "trio of rectangles corresponds to an R-circle";
            St[28] = "You can see that these R-circles all link E0";
            St[29] = "though sometimes it is a close call and";
            St[30] = "you have to zoom in to see it.  My preprint";
            St[31] = "gives a precise statement and a proof of";
            St[32] = "the linking result.";

            St[48] = "C-circles and C-arcs.";
            St[46] = "R-circles and R-arcs";
            St[47] = "Bill's book";
            St[45] = "PLAN1 part 3";

            L[8].future = 210;
            L[6].future = 211;
            L[7].future = 240;
            L[5].future = 706;

            D.pix = 6;
            D.ACTIVE = 0;
            D.repaint();
        }

        if (MESSAGE == 706)
        {
            St[0] = "PLAN1 part 3";
            St[1] = "CHECKING R-SPHERES";
            St[2] = "PLAN1 can be used as a check that we have";
            St[3] = "constructed our R-spheres as claimed.";
            St[4] = "For instance, the R-arcs of S1 which connect";
            St[5] = "E0 to C1 should join 0 to a point on C1.";
            St[6] = "Referring to object selector 1 on the control";
            St[7] = "panel, these are the arcs of S10";
            St[8] = "The figure above shows the relevant plot";
            St[13] = "FORMULA FOR PLAN1";
            St[14] = "Let A1,A2,B be the eigenvectors for the matrix";
            St[15] = "G0 representing the product I1.I0.I2, as we";
            St[16] = "discussed for ELEV2.  Here we just use B";
            St[17] = "Given x=(x1,x2) in S^3-q we define X=(x1,x2,1)";
            St[18] = "as we did for ELEV2. Writing q=(q1,q2) let";
            St[19] = "Let Q=(q1,q2,1).  Then";
            St[20] = "PLAN1(x)=<X,B>/<X,Q>.";
            St[25] = "Here we are using the standard Hermitian";
            St[26] = "form of type (2,1), as we did for ELEV2.";
            St[27] = "Note that PLAN1 is holomorphic.";

            St[45] = "R-sphere";
            St[46] = "ELEV2";
            St[47] = "type (2,1) form";
            St[48] = "";

            L[5].future = 150;
            L[6].future = 700;
            L[7].future = 221;
            L[8].future = 793;

            D.pix = 7;
            D.ACTIVE = 0;
            D.repaint();
        }

        if (MESSAGE == 750)
        {
            St[0] = "ELEV2 part 2";
            St[1] = "FLAT STRUCTURE";
            St[2] = "The PU(2,1) stabilizer of Q0 acts transitively";
            St[3] = "on the cylinder H of harmonic arcs. This";
            St[4] = "endows H with a flat Euclidean structure.";
            St[5] = "Thus, up to scaling, we can identify H with the";
            St[6] = "standard flat cylinder - e.g. the white one";
            St[7] = "in the ELEV plotting window. ELEV2 conjugates";
            St[8] = "The stabilizer of Q0 to the group of isometries";
            St[9] = "of the flat cylinder. This phenomenon is";
            St[10] = "manifested in the fact that the objects";
            St[11] = "corresponding to the object selector 2";
            St[12] = "are isometric images of the objects";
            St[13] = "corresponding to the object selector 1.";
            St[15] = "FORMULAS";
            St[16] = "When the identification with the flat cylinder";
            St[17] = "is made, there are nice formulas for ELEV2,";
            St[18] = "which we now describe.";

            St[19] = "The product I1.I0.I2 is represented by a";
            St[20] = "a matrix G0 whose eigenvectors are A1,A2,B";
            St[21] = "Here A1 and A2 correspond to the two";
            St[22] = "fixed points of I1.I0.I2 on S^3 and B is the";
            St[23] = "vector which is perpendicular to A1 and A2";
            St[24] = "in the type (2,1) form.";
            St[26] = "Given x=(x1,x2) in S^3-E0 let X=(x1,x2,1).";
            St[27] = "Then ELEV2(x)=(e1(x),e2(x));";
            St[29] = "e1(x)=2 arg(<X,B>) - arg(<X,A1>) - arg(<A,A2>)";
            St[30] = "e2(x)=log |<X,A1>| - log |<X-A2>|";
            St[32] = "Different choices of A1,A2,B lead to a new map";
            St[33] = "which differs only by scaling.  Here we are";
            St[34] = "using the type (2,1) form in our formulas.";

            St[45] = "type (2,1) form";
            St[48] = "PU(2,1)";
            St[47] = "Q0 and E0";
            St[46] = "ELEV2 part 3";

            L[5].future = 239;
            L[8].future = 220;
            L[7].future = 800;
            L[6].future = 760;
        }

        if (MESSAGE == 760)
        {
            St[0] = "ELEV2 part 3";
            St[1] = "WHY THE FORMULAS WORK";
            St[2] = "Up to scaling, ELEV2 is characterized by the";
            St[3] = "propety that it conjugates the PU(2,1)";
            St[4] = "stabilizer of Q0 to the isometry group of H. The";
            St[5] = "elements in the stabilizer are diagonalized";
            St[6] = "by the basis A1,A2,B above.  Hence the eqns.";
            St[7] = "give a map which has the defining property.";

            St[9] = "IMAGES OF C-CIRCLES";
            St[10] = "If C is a C-circle which links E0 then ELEV2(C)";
            St[11] = "is a smooth loop which winds once around H.";
            St[12] = "This applies to the C-circles C1,C2,E1,E2";
            St[13] = "on the object selectors. Try it out.";
            St[14] = "I give a proof in my preprint.";
            St[15] = "The main idea is that C-circles are transverse";
            St[16] = "to the contact structure in S^3, but the fibers";
            St[17] = "of ELEV2 are integral to the contact structure.  ";

            St[19] = "IMAGES OF R-CIRCLES";
            St[20] = "If A is an R-circle which links E0 then ELEV2(A)";
            St[21] = "is a loop which winds once around the cylinder.";
            St[22] = "This loop is the union of two smooth arcs of";
            St[23] = "nonzero slope, joined at 2 cusps.";
            St[24] = "I give a proof in my preprint.  The idea is";
            St[25] = "that R-circles are integral to the contact";
            St[26] = "structure and a linked R-circle is tangent";
            St[27] = "to exactly two fibers of ELEV2.  The tangency.";
            St[28] = "points account for the cusps.  If you plot";
            St[29] = "a short-long-short combo of thin rectangles,";
            St[30] = "joining C1 to E2, you will see the picture. How";
            St[31] = "do we know that the said s-l-s combo gives";
            St[32] = "a linked R-circle.  Look in PLAN1 and see a";
            St[33] = "curve (a lemniscate actually) which links the";
            St[34] = "origin!";

            St[45] = "What is PU(2,1)";
            St[46] = "contact structure";
            St[47] = "Q0 and E0";
            St[48] = "about the coords.";

            L[5].future = 239;
            L[6].future = 770;
            L[7].future = 800;
            L[8].future = 270;

            D.pix = 6;
            D.ACTIVE = 0;
            D.repaint();
        }

        if (MESSAGE == 770)
        {
            St[0] = "contact structure";
            St[1] = "The tangent space to a point on S^3 is a";
            St[2] = "copy of R3 and contains a unique complex";
            St[3] = "complex line.  Thus there is unique";
            St[4] = "complex line tangent to each point of S^3.";
            St[5] = "The union of these complex lines is the";
            St[6] = "standard contact structure on S^3. Elements";
            St[7] = "of PU(2,1) preserve the contact structure.";

            St[9] = "C-circles are transverse to the contact";
            St[10] = "structure at every point.  In other words, the";
            St[11] = "tangent vector to a C-circle never lies in the";
            St[12] = "complex line tangent to S^3 at the same point.";

            St[14] = "R-circles are integral to the contact";
            St[15] = "structure at every point.  In other words, the";
            St[16] = "tangent vector to a R-circle always lies in the";
            St[17] = "complex line tangent to S^3 at the same point.";

            St[19] = "The image of the contact structure under";
            St[20] = "Stereographic projection has a nice formula:";
            St[21] = "It is given by the kernel of the 1-form";
            St[22] = "w=dt-xdy-ydx.  Here we are coordinatizing";
            St[23] = "CxR, the range of stereographic projection";
            St[24] = "as (x+iy,t).";
            St[26] = "Green's theorem has a beautiful geometric";
            St[27] = "interpretation in this context: a closed loop";
            St[28] = "in C lifts to an integral closed loop in CxR";
            St[29] = "iff it bounds a region of 0 signed area.";
            St[30] = "more general, the height difference between";
            St[31] = "the endpoints of the lift of a closed loop";
            St[32] = "equals the signed area of the region bounded";
            St[33] = "by the loop";

            L[5].future = 210;
            L[6].future = 211;
            L[7].future = 220;
            L[8].future = 780;

            St[45] = "C-circles and C-arcs";
            St[46] = "R-circles and R-arcs";
            St[47] = "PU(2,1)";
            St[48] = "stereographic proj.";
        }

        if (MESSAGE == 777)
        {
            St[0] = "HYP1 and HYP2";
            St[1] = "HYP1 and HYP2 are both based";
            St[2] = "on the projection map from S^3 to disk bounded";
            St[3] = "by the C-circle E0.  For HYP1, this disk";
            St[4] = "is identified with a half-plane via the usual";
            St[5] = "conformal stereographic projection.  For HYP2.";
            St[6] = "The disk is identified with a half plane in such";
            St[7] = "a way that a certain family of geodesics are";
            St[8] = "are mapped isometrically to horizontal straight";
            St[9] = "lines.  Click on the links below for details";
            St[10] = "We adjusted the various scaling factors";
            St[11] = "so that the two coordinate systems";
            St[12] = "converge to each other as the parameter";
            St[13] = "s converges to sqrt(125/3). Try it out.";

            St[17] = "lines.  Click on the links below for details";
            St[18] = "about HYP1 and HYP2.";
            L[5].future = 792;
            L[7].future = 797;
            L[6].future = 210;
            L[8].future = 777;

            St[45] = "HYP1";
            St[47] = "HYP2";
            St[46] = "C-circles and C-arcs";
            St[48] = "";
        }

        if (MESSAGE == 780)
        {
            St[0] = "stereographic proj.";
            St[1] = "Let q be a point in S^3.  Stereographic";
            St[2] = "projection maps S^3-q to the Heisenberg group.";
            St[3] = "The Heisenberg group has the underlying";
            St[4] = "space CxR, but with a nonabelian group law.";
            St[5] = "This map is the basis for the PLAN1 coordinates.";
            St[6] = "Here we sketch the construction.";

            St[8] = "THE SIEGEL DOMAIN";
            St[9] = "The Siegel domain Z is the unbounded subset of";
            St[10] = "C2 defined as the set (z,w) such that Re(w)>|z|^2";
            St[11] = "This set of complex projectively equivalent to";
            St[12] = "the open unit ball in C2.";

            St[14] = "STEP 1:";
            St[15] = "Embed C2 as an affine subset of CP^2.";
            St[16] = "Let B be a complex projective automorphism";
            St[17] = "which carries the open unit ball to Z";
            St[18] = "in such a way that B(q) is not in C2.";

            St[19] = "STEP 2";
            St[20] = "B carries S^3-p to boundary(Z)";
            St[21] = "Let A: boundary(Z) --> CxR be given by the map";
            St[22] = "A(z,w)=(w,Im(w)).";
            St[23] = "Then the composition AB: S^3-q --> CxR";
            St[24] = "is a stereographic projection map.";

            St[26] = "This construction is more formally known as";
            St[27] = "Heisenberg stereographic projection.  For more";
            St[28] = "details, see my preprint, or Bill's book.";

            L[5].future = 781;
            L[6].future = 240;
            L[7].future = 701;
            L[8].future = 780;

            St[45] = "CP^2";
            St[46] = "Bill's book";
            St[47] = "PLAN1";
            St[48] = "";
        }

        if (MESSAGE == 781)
        {
            St[0] = "CP^2";
            St[1] = "CP^2 is the space of 1-dimensional complex";
            St[2] = "linear subspaces of C^3.  That is, CP^2";
            St[3] = "is the usual projectivization of C^3.";
            St[4] = "Complex linear maps automatically act on";
            St[5] = "CP^2 as complex projective transformations.";
            St[6] = "because they permute the 1-dimensional";
            St[7] = "subspaces. The term 'complex projective";
            St[8] = "transformation' may sound fancy, but it is";
            St[9] = "just the name for a linear transformation acting";
            St[10] = "on the 1-dimensional subspaces of C^3.";

            St[18] = "For more information, see Bill's book.";

            L[5].future = 240;
            L[6].future = 110;
            L[7].future = 781;
            L[8].future = 781;

            St[45] = "Bill's book.";
            St[46] = "Hermitian geometry";
            St[47] = "";
            St[48] = "";
        }

        if (MESSAGE == 792)
        {
            St[0] = "HYP1 part 1";
            St[1] = "The HYP1 coordinate system is an auxilliary";
            St[2] = "coordinate system designed to complement and";
            St[3] = "sometimes clarify the images in the main";
            St[4] = "coordinate system, ELEV2. Here we discuss";
            St[5] = "the structure of HYP1.";

            St[7] = "MAIN DEFINITION";
            St[8] = "When E0 is normalized to be the circle";
            St[9] = "C^1 # S^3, HYP1 is defined as the map";
            St[10] = "(z,w) --> z.  (We will give a direct formula";
            St[11] = "in Part 3).  The image of HYP1 is the";
            St[12] = "right half-plane, which we think of as the";
            St[13] = "HYPerbolic half-plane.";

            St[19] = "FOR EXPERTS:";
            St[20] = "HYP1 is the following composition.  First";
            St[21] = "project orthogonally, in the CH^2 sense,";
            St[22] = "onto the C-slice bounded by E0, then";
            St[23] = "identify this slice with the a half-plane";
            St[24] = "via a conformal transformation.";

            St[26] = "SYMMETRIES";
            St[27] = "HYP1 conjugates the stabilizers of E0 to ";
            St[28] = "conformal automorphisms of hyperbolic plane.";
            St[29] = "Within this, HYP1 conjugates the stabilizers";
            St[30] = "of Q0 to the subgroup which preserves the";
            St[31] = "endpoints of HYP1(Q0). Note that HYP1(E0) is";
            St[32] = "a line and HYP1(Q0) is a sub-arc. When";

            St[45] = "ELEV2";
            St[46] = "Q0 and E0";
            St[47] = "HYP1 part 2";
            St[48] = "PU(2,1)";

            L[5].future = 700;
            L[6].future = 270;
            L[7].future = 793;
            L[8].future = 220;
        }

        if (MESSAGE == 785)
        {
            St[0] = "PLAN2 part 1";
            St[1] = "The PLAN2 coordinate system is an auxilliary";
            St[2] = "coordinate system designed to complement and";
            St[3] = "sometimes clarify the images in the main";
            St[4] = "coordinate system, ELEV2. Here we discuss";
            St[5] = "the structure of PLAN2.  I do not have such";
            St[6] = "an elementary description of PLAN2, and";
            St[7] = "the description I give depends heavily on ELEV2.";
            St[8] = "You should learn about ELEV2 first.";

            St[10] = "HORIZONTAL FIBER";
            St[11] = "Let h be a horizontal line in cylinder H,";
            St[12] = "the range of ELEV2.  The inverse image";
            St[13] = "if h undee ELEV2 is a sphere S, consisting";
            St[14] = "of all the R-arcs which join a certain pair";
            St[15] = "of points on E0.  To be canonical, we can";
            St[16] = "choose one of these points to be q, the point";
            St[17] = "used in the definition of PLAN1.";
            St[18] = "S has a natural complex structure";

            St[19] = "Every point x of S^3-E0 is equivalent to a";
            St[20] = "point x' of S via an unique element g of the ";
            St[21] = "stabilizer of Q0 which acts as a pure";
            St[22] = "translation.  We mean that ELEV2 conjugates";
            St[23] = "g to a vertical translation of the cylinder.";
            St[24] = "When we identify S-Q0 with the complex plane,";
            St[25] = "PLAN2 is precisely the map x --> x'=g(x).";

            St[27] = "GEOMETRIC PROPERTIES:";
            St[28] = "--PLAN2 conjugates the stabilizer of Q0 to the";
            St[29] = "the group of rotations of the plane fixing 0.";
            St[30] = "--PLAN2 maps the fibers of ELEV2 to rays";
            St[31] = "through the origin!";
            St[32] = "--In general I don't have a characterization";
            St[33] = "of the images, under plan, of C-circles";
            St[34] = "and R-circles.";

            St[45] = "ELEV2";
            St[46] = "Q0 and E0";
            St[48] = "PLAN2 part 2";
            St[47] = "PLAN1";

            L[5].future = 700;
            L[6].future = 270;
            L[8].future = 786;
            L[7].future = 701;
        }

        if (MESSAGE == 786)
        {
            St[0] = "PLAN2 part 2";
            St[1] = "FORMULA FOR PLAN2";
            St[2] = "Given v in S^3-E0 we have";
            St[4] = "PLAN2(v)=exp(d)*exp(ix), where";
            St[6] = "x is the x-coordinate of ELEV2.";
            St[8] = "d is the signed hyperbolic distance from HYP1(v)";
            St[9] = "to the hyperbolic geodesic which connects the";
            St[10] = "endpoints of HYP1(Q0).  The quantity exp(d)";
            St[11] = "has a natural interpretation in terms of";
            St[12] = "cross ratios, which makes it easy to compute.";

            St[13] = "CROSS RATIO COMPUTATION OF exp(d):";
            St[14] = "Let v1 and v2 be the two points where the arc";
            St[15] = "S(E0,Q0;v) intersects E0.  We take v1 in Q0";
            St[16] = "a=HYP1(v1)";
            St[17] = "b=HYP1(an endpoint of Q0)";
            St[18] = "c=HYP1(v2)";
            St[19] = "HYP1(v1)";
            St[20] = "Then exp(d) is the imaginary part of:";
            St[21] = "sqrt(((e-a)(c-b))/((b-a)(c-e)))";

            St[45] = "ELEV2";
            St[46] = "Q0 and E0";
            St[47] = "HYP1 and HYP2";
            St[48] = "";

            L[5].future = 770;
            L[6].future = 270;
            L[7].future = 792;
            L[8].future = 786;
        }

        if (MESSAGE == 793)
        {
            St[0] = "HYP1 part 2";
            St[1] = "IMAGES OF C-CIRCLES";
            St[2] = "If C is a C-circle in S^3 then HYP1(C) is a round";
            St[3] = "circle in the half-plane. Try this out by plotting";
            St[4] = "C1,C2,E1,E2.  Note that HYP1(C1) and HYP1(C2)";
            St[5] = "are symmetrically placed.  Likewise for HYP1(E1)";
            St[6] = "and HYP1(E2).  We arranged this by normalizing";
            St[7] = "by a conformal transformation so that HYP1(Q0)";
            St[8] = "is symmetrically placed with respect to the";
            St[9] = "R axis.";

            St[11] = "IMAGES OF R-CIRCLES";
            St[12] = "In general, the image of an R-circle under HYP1";
            St[13] = "is a complicated curve. We don't know the";
            St[14] = "general formula.  But, if A is an R-arc which";
            St[15] = "is harmonic with respect to Q0, in the sense we";
            St[16] = "discussed in connection with ELEV2, then";
            St[17] = "HYP1(A) is a circular arc which meets the unit";
            St[18] = "disk at right angles.  That is, HYP1(A)";

            St[19] = "is a hyperbolic geodesic in the half-plane";
            St[20] = "when the half-plane is equipped with the";
            St[21] = "usual hyperbolic metric.  Moreover, HYP1(A)";
            St[22] = "is orthogonal to the hyperbolic geodesic";
            St[23] = "which joins the endpoints of HYP1(Q0).  We call";
            St[24] = "HYP1(A) a 'fiber geodesic' in this case.";
            St[26] = "Our claims here follow from the symmetry.";
            St[27] = "that HYP1 conjugates the stabilizer of Q0";
            St[28] = "to the stabilizer of the geodesic joining";
            St[29] = "the endpoints of HYP1(Q0). You can test";
            St[30] = "these claims by looking at the plots.";

            St[45] = "C-circles and C-arcs";
            St[46] = "R-circles and R-arcs";
            St[47] = "HYP1 part 3";
            St[48] = "ELEV2";

            L[5].future = 210;
            L[6].future = 211;
            L[7].future = 794;
            L[8].future = 700;

            D.pix = 7;
            D.ACTIVE = 0;
            D.repaint();
        }

        if (MESSAGE == 794)
        {
            St[0] = "HYP1 part 3";
            St[1] = "FORMULA FOR HYP1";
            St[2] = "Let A1,A2,B be the eigenvectors for the matrix";
            St[3] = "G0 representing the product I1.I0.I2, as we";
            St[4] = "discussed for ELEV2.  Here we just use B";
            St[5] = "Given x=(x1,x2) in S^3 we define X=(x1,x2,1)";
            St[6] = "as we did for ELEV2. We first define the vector";
            St[8] = "V(x)=X-(<X,B>/<B,B>) B";
            St[10] = "(Basically we are doing Gram Schmidt with";
            St[11] = "respect to the type (2,1) Hermitian form.)";
            St[12] = "Writing V=(v1,v2,v3) we define";
            St[14] = "HYP1(x)=v1/v3";
            St[16] = "and apply a suitable conformal transformation.";

            St[19] = "HEIGHT PRINCIPLE:";
            St[20] = "There is a structural relation between";
            St[21] = "ELEV2 and HYP1.  If ELEV2(x1) and ELEV2(x2)";
            St[22] = "have the same height in the cylinder-i.e.";
            St[23] = "the same y coordinate-then HYP1(x1) and";
            St[24] = "HYP1(x2) lie on the same fiber geodesic.";
            St[25] = "(Recall that the fiber geodesics are";
            St[26] = "the images of the fibers of ELEV2, in HYP1.)";
            St[27] = "The height principle follows from symmetry";
            St[28] = "and can also be seen by calculation. If you";
            St[29] = "play with the applet, comparing images";
            St[30] = "in ELEV2 and HYP1, you will see the principle";
            St[31] = "in action.";

            St[45] = "ELEV2";
            St[46] = "type (2,1) form";
            St[47] = "";
            St[48] = "";
            L[5].future = 700;
            L[6].future = 221;
            L[7].future = 794;
            L[8].future = 794;
        }

        if (MESSAGE == 797)
        {
            St[0] = "HYP2";
            St[1] = "The HYP2 coordinate system is an auxilliary";
            St[2] = "coordinate system designed to complement and";
            St[3] = "sometimes clarify the images in the main";
            St[4] = "coordinate system, ELEV2. Here we discuss";
            St[5] = "the structure of HYP2.  HYP2 is build out";
            St[6] = "of ELEV2 and PLAN2.  You should read about";
            St[7] = "these coordinates first.";

            St[8] = "MAIN DEFINITION";
            St[10] = "The x-coordinate of HYP2 is the square";
            St[11] = "of the radial coordinate of PLAN2.";
            St[12] = "The y-coordinate of HYP2 is the y-coordinate";
            St[13] = "of ELEV2.";
            St[14] = "Note that HYP2 is not defined on Q0 and E0.";

            St[19] = "GEOMETRIC INTERPRETATION";
            St[20] = "First, a word about HYP1.  The range";
            St[21] = "of HYP1 is the right half-plane, which we";
            St[22] = "think of the hyperbolic 'upper half-plane'";
            St[23] = "turned on its side.";
            St[24] = "There is a natural map B which carries the";
            St[25] = "HYP1 half-plane to itself such that:";
            St[26] = "1. B maps the geodesic HQ0 connecting the";
            St[27] = "endpoints of HYP1(Q0) to a vertical line.";
            St[28] = "2. B maps the geodesics perpendicular to HQ0";
            St[30] = "(the fiber geodesics) isometrically to";
            St[31] = "horizontal rays.";
            St[32] = "Then HYP2=B composed with HYP1.";

            St[45] = "ELEV2";
            St[46] = "PLAN2";
            St[47] = "HYP1";
            St[48] = "E0 and Q0";
            L[5].future = 700;
            L[6].future = 785;
            L[7].future = 792;
            L[8].future = 800;
        }

        if (MESSAGE == 800)
        {
            St[0] = "Q0 and E0";
            St[1] = "Let I0.I1.I2 be the product of the generators.";
            St[2] = "Of the triangle group.  We are interested in the";
            St[3] = "parameters where the product g0=I1.I0.I2 is";
            St[4] = "loxodromic.  In this case, g0 fixes 2";
            St[5] = "points in S^3.";

            St[7] = "E0 is the C-circle determined by these 2 points.";
            St[8] = "Q0 is one of the two arcs of E0 bounded by the";
            St[9] = "2 points.  Of the two possibilities, we choose.";
            St[10] = "So that Q0 varies continuously with the";
            St[11] = "parameter and shrinks to a point as the";
            St[12] = "parameter tends to sqrt(125/3).";

            St[14] = "try plotting Q0 and E0.  Due to the way our";
            St[15] = "coordinates are defined,";
            St[16] = "Q0 and E0 both map to 0 in PLAN1";
            St[17] = "Q0 and E0 are not defined in ELEV2 or HYP2";

            St[19] = "This is a good opportunity to talk about";
            St[20] = "The special object HQ0.  This object";
            St[21] = "is the union of all R-arcs which";
            St[22] = "contain the endpoints of Q0. The image.";
            St[23] = "HYP1(HQ0) is the hyperbolic geodesic which";
            St[24] = "joins the endpoints of HYP1(Q0).";
            St[25] = "The image HYP2(HQ0) is a vertical line.";
            St[26] = "The images in the other coordinate systems";
            St[27] = "would be surfaces; but we do not plot";
            St[28] = "HQ0 in the other coordinate systems.";

            L[5].future = 280;
            L[6].future = 362;
            L[7].future = 211;
            L[8].future = 210;

            St[45] = "triangle groups";
            St[46] = "loxodromic elements";
            St[47] = "R-circles and R-arcs";
            St[48] = "C-circles and C-arcs";
        }

        if (MESSAGE == 900)
        {
            St[0] = "the proof";

            St[1] = "This discussion assumes that you know about";
            St[2] = "the the other 5 portions of the text, namely:";
            St[3] = "1. how to operate the applet.";
            St[4] = "2, the topics in the math.";
            St[5] = "3. the objects in the applet.";
            St[6] = "4. the coordinate systems.";
            St[7] = "5. the conjecture.";
            St[8] = "However, you can forge ahead without this";
            St[9] = "background and look things up as you go.";

            St[11] = "Our main idea is to create the 3 R-spheres";
            St[12] = "S0,S1,S2 in the 3-sphere S^3 such that";
            St[13] = "1. Sj is an embedded piecewise analytic sphere.";
            St[14] = "2. Si and Sj intersect in a contractible graph";
            St[15] = "3. Ij(Sj)=Sj.";
            St[16] = "4. Ij interchanges the two components of S^3-Sj";

            St[18] = "given Properties 1 and 2, Properties 3 and 4";
            St[19] = "follow from the symmetry of the construction.";

            St[20] = "From Properties 1-4 there are balls B0,B1,B2,";
            St[21] = "bounded by S0,S1,S2, which have pairwise";
            St[22] = "disjoint interiors.  This situation immediately";
            St[23] = "implies that G is a discrete group.";
            St[25] = "The Goldman-Parker conjecture boils down";
            St[26] = "to establishing Properties 1 and 2.";
            St[28] = "One can make the stronger statement that";
            St[29] = "the set F is a fundamental domain for the";
            St[30] = "action of G:";
            St[31] = "F=(S^3-X) u. (Y-Z)";
            St[32] = "X=B0 u. B1 u. B2;";
            St[33] = "Y=S0 u. S1 u. S2";
            St[34] = "Z=p0 u. p1 u. p2";
            St[35] = "However, we shall not give the argument here.";

            L[5].future = 900;
            L[6].future = 700;
            L[7].future = 910;
            L[8].future = 920;

            St[45] = "";
            St[46] = "ELEV2";
            St[47] = "Property 1";
            St[48] = "Property 2";

            D.pix = 4;
            D.ACTIVE = 0;
            D.repaint();
        }

        if (MESSAGE == 910)
        {
            St[0] = "Property 1 part 1";
            St[1] = "First, we'll do an experiment.";
            St[2] = "1. Push 'Show me the money!'. Wait";
            St[3] = "2. Erase the red objects and replot.";
            St[4] = "3. You are now looking plots of S1-E0";
            St[5] = "Looking in PLAN2 you can see clearly that";
            St[6] = "S1 consists of 2 embedded hemispheres,";
            St[7] = "a dark blue one: S10";
            St[8] = "a light blue one: S21";
            St[9] = "and that these hemispheres intersect only";
            St[10] = "at the equator C1. (Or see gallery picture 9.)";
            St[11] = "We don't know theoretically about PLAN2";
            St[12] = "so we will take a different approach";
            St[13] = "in explaining why S1 is an embedded sphere.";
            St[15] = "WHY THE HEMISPHERES ARE EMBEDDED";
            St[16] = "(Erase the dark blue objects and replot.)";
            St[17] = "You are looking at S21";
            St[18] = "The space S21-C1 fibers over";
            St[19] = "C1, with the fibers being half-open R-arcs.";
            St[20] = "The closed endpoints of the fibers are on C1";
            St[21] = "the open endpoints limit on Q2 and are";
            St[22] = "identified in pairs.  (To see this, add Q2 to";
            St[23] = "the mix, in a different color, and replot.";
            St[24] = "Look in ELEV2.) This structure shows that";
            St[25] = "S21 is an embedded disk. The case";
            St[26] = "S10 follows from symmetry.";
            St[28] = "If you want to see how the fibration works.";
            St[29] = "toggle the 'truncate arcs' slider so that";
            St[30] = "the picture automatically replots, and then";
            St[31] = "click on this slider, starting at left and";
            St[32] = "moving incrementally to the right.";

            St[45] = "about the coords.";
            St[47] = "Property 1 part 2";
            St[48] = "PLAN2";
            St[46] = "S1";

            L[5].future = 270;
            L[7].future = 911;
            L[8].future = 785;
            L[6].future = 250;
        }

        if (MESSAGE == 911)
        {
            St[0] = "Property 1 part 2";
            St[1] = "HOW THE HEMISPHERES INTERSECT";
            St[2] = "Comparing the definition of ELEV2 to";
            St[3] = "the construction of S1 we see that";
            St[4] = "ELEV2(S10-E0)=ELEV2(C1). On the";
            St[5] = "other hand, all the arcs of the set";
            St[6] = "ELEV2(S(E2,Q2;C2)-E0), which are near";
            St[7] = "ELEV2(C1), move away from ELEV2(C1)";
            St[8] = "monotonically. From this picture we see that";
            St[9] = "S10 # S21 is a subset";
            St[10] = "of C1 u. E0.  By symmetry the same";
            St[11] = "intersection is a subset of C1 u. E2.";
            St[12] = "But E0 and E2 are disjoint, as one can";
            St[13] = "see by plotting these objects in HYP1.";
            St[14] = "Hence S10 # S21=C1.";
            St[19] = "You can see a clearer picture of";
            St[20] = "How the hemispheres intersect by plotting S1";
            St[21] = "and then playing the same game with the";
            St[22] = "the 'truncate arcs' slider we described in";
            St[23] = "part 1.";

            St[45] = "about the coords.";
            St[46] = "ELEV2";
            St[48] = "monotonicity";
            St[47] = "S1";

            L[5].future = 270;
            L[6].future = 700;
            L[8].future = 939;
            L[7].future = 250;
        }

        if (MESSAGE == 920)
        {
            St[0] = "Property 2";
            St[1] = "Push 'Show me the money!'. Wait. Now";
            St[2] = "adjust the ELEV2 window to your liking. The";

            St[3] = "ELEV2 window shows ELEV2(S1-E0) in blue";
            St[4] = "and ELEV2(S2-E0) in red.  Note that the 2";
            St[5] = "sets appear to intersect in just a single";
            St[6] = "point, which must be ELEV2(p0).";
            St[7] = "Try the plots at other parameters.";

            St[9] = "Assume for now that";
            St[10] = "(*) ELEV2(S1-E0)#ELEV2(S1-E0)=ELEV2(p0).";
            St[11] = "The fibers of ELEV2 are R-arcs.  Hence";
            St[12] = "(S1-E0)#(S2-E0) is contained in an R-arc";
            St[13] = "Let A=S(E0,Q0;p0).";
            St[14] = "Click the link below to see why, in fact:";
            St[15] = "(S1-E0) # (S2-E0) = A";

            St[17] = "S1 # E0 and S2 # E0 are overlapping arcs.";
            St[18] = "(Add E0 to the plot and look in HYP1.)";
            St[19] = "Hence S1 # S2 # E = B, another arc.";
            St[20] = "S1 # S1=A u. B, two arcs which meet";
            St[21] = "in a 'T' formation. Property 2 follows.";

            St[25] = "The main steps in showing (*) are:";
            St[27] = "1. Show that the curves ELEV2(C1) and";
            St[28] = "ELEV2(C2) intersect in a single point.";
            St[29] = "This a kind of calculus problem.";
            St[31] = "2. Show that the arcs of ELEV2(S1), which";
            St[32] = "are near ELEV(C1)#ELEV(C2), rise";
            St[33] = "monotonically away from ELEV2(C2).";
            St[34] = "This monotonicity property is the heart of";
            St[35] = "matter, the main point of the proof.";

            St[45] = "ELEV2";
            St[46] = "why A";
            St[48] = "monotonicity";
            St[47] = "calculus problem";

            L[5].future = 700;
            L[6].future = 927;
            L[8].future = 939;
            L[7].future = 922;

            D.pix = 8;
            D.ACTIVE = 0;
            D.repaint();
        }

        if (MESSAGE == 922)
        {
            St[0] = "calculus problem";
            St[1] = "First of all, plot C1 and C2.  Move the";
            St[2] = "parameter slider around to see the";
            St[3] = "picture for many parameters.  Look in";
            St[4] = "the ELEV window.  Zoom in on the point";
            St[5] = "of intersection and see that it remains a";
            St[6] = "point even at the highest dilation.  All";
            St[7] = "this is good experimental evidence that the";
            St[8] = "ELEV2(C1) # ELEV2(C2)=ELEV2(p0).";
            St[10] = "One idea for a proof is that ELEV2(C1) and";
            St[11] = "ELEV2(C2) have the same slope at ELEV2(p0)";
            St[12] = "by symmetry.  Thus, the second derivative";
            St[13] = "determines the local picture.  It seems";
            St[14] = "that ELEV2(C1) is convex in a neighborhood.";
            St[15] = "of ELEV2(p0) and that ELEV2(C2) is concave";
            St[16] = "in a neighborhood of ELEV2(C1). Thus";
            St[17] = "ELEV2(C1) stays above ELEV2(C2) in a";
            St[18] = "neighborhood of ELEV2(p0).  By the time";
            St[19] = "the curves lose their respective convexity";
            St[20] = "and concavity, ELEV2(C1) is so far above";
            St[21] = "ELEV2(C2) that the result is obvious.";
            St[23] = "To get a precise picture of what seems";
            St[24] = "to be going on, clear the screen and";
            St[25] = "then plot C1 and E2.  Note that ELEV2(C1)";
            St[26] = "and ELEV2(E2) intersect in two points.";
            St[27] = "These two points are both points of";
            St[28] = "isometric symmetry for ELEV2(C1) and";
            St[29] = "ELEV2(E2) and hence are inflection points";
            St[30] = "for both curves.  It seems that these are the";
            St[31] = "only inflection points for ELEV2(C1).  A";
            St[32] = "similar result holds for ELEV2(C2), by symmetry.";
            St[33] = "This result on inflection points, would";
            St[34] = "prove ELEV2(C1) # ELEV2(C2)=ELEV2(p0).";

            St[45] = "the catch";
            St[46] = "";
            St[47] = "";
            St[48] = "";

            L[5].future = 923;
            L[6].future = 922;
            L[7].future = 922;
            L[8].future = 922;
        }

        if (MESSAGE == 923)
        {
            St[0] = "the catch";
            St[1] = "There is a catch:";
            St[2] = "The approach we just described has a";
            St[3] = "hidden difficulty which prevents us from";
            St[4] = "using it as we have written. While the";
            St[5] = "formulas for the first and second derivatives";
            St[6] = "ELEV2(C1) and ELEV2(C2) are easy to write down";
            St[7] = "in terms of the eigenvectors of the product";
            St[8] = "I1.I0.I2 in the generators of the triangle group,";
            St[9] = "the formula for these eigenvectors, in terms";
            St[10] = "of the rational functions describing the";
            St[11] = "matrix entries for the group elements, is";
            St[12] = "extremely complicated.  For any given";
            St[13] = "parameter I can easily calculate things out";
            St[14] = "but I couldn't get an estimate that works for";
            St[15] = "all parameters at once.";
            St[17] = "To get around the problem I introduce another";
            St[18] = "coordinate system, ELEV1, which is simpler";
            St[19] = "than ELEV2 in terms of formulas.  and closely";
            St[20] = "related to PLAN1.  Given ELEV1, I then:";
            St[22] = "1. Prove the 2-inflection point result for";
            St[23] = "ELEV1(C1) and ELEV1(C2);";
            St[24] = "2. Use an elementary geometric comparison";
            St[25] = "comparison argument to show that the result";
            St[26] = "for ELEV1 implies the result for ELEV2.";
            St[28] = "This approach doesn't work for all parameters";
            St[29] = "but it does work for all parameters in the";
            St[30] = "interval (sqrt(35),sqrt(125/3)), and this is";
            St[31] = "enough to establish the conjecture. See my";
            St[32] = "preprint for the details.";

            St[45] = "triangle group";
            St[46] = "ELEV1";
            St[47] = "why sqrt(35)";
            St[48] = "";

            L[5].future = 280;
            L[6].future = 924;
            L[7].future = 925;
            L[8].future = 922;
        }

        if (MESSAGE == 924)
        {
            St[0] = "ELEV1";
            St[1] = "ELEV1 is closely related to PLAN1";
            St[2] = "Let Y be the Heisenberg stereographic";
            St[3] = "projection used in the definition of ELEV2";
            St[4] = "Let H* be the cylinder (R/2 Pi Z) x S1";
            St[5] = "Let P*: (C-0)xR --> H* be the map";
            St[6] = "given by P*(z,t)=(arg(z),t).";
            St[7] = "Then ELEV1=P*Y.";

            St[9] = "If we identify H* with H, the cylinder used in";
            St[10] = "the definition of ELEV2, then ELEV2 and ELEV1";
            St[11] = "have the same range and can be compared.";

            St[45] = "ELEV2";
            St[46] = "PLAN1";
            St[47] = "stereographic proj.";

            L[5].future = 700;
            L[6].future = 701;
            L[7].future = 780;
            L[8].future = 924;
        }

        if (MESSAGE == 925)
        {
            St[0] = "why sqrt(35)";
            St[1] = "Goldman and Parker prove their";
            St[2] = "Conjecture for all parameters in";
            St[3] = "the range [0,sqrt(35)].  See the precise";
            St[4] = "statement of the conjecture";
            St[6] = "Actually, our 2-inflection-point result";
            St[7] = "holds in a slightly larger interval than";
            St[8] = "[sqrt(35),sqrt(125/3)).  Also, even";
            St[9] = "in the worst case, there are just 4 inflection";
            St[10] = "points and these do not occur near the";
            St[11] = "point of tangency of ELEV1(C1) and";
            St[12] = "ELEV1(C2)-the point of interest to us.";
            St[13] = "I could actually push through the";
            St[14] = "calculus proof for all parameters, but";
            St[15] = "I didn't do this because it is tedious.";
            St[17] = "I hope to figure out a direct way to compute";
            St[18] = "the inflection points of ELEV2(C1) and ELEV2(C2).";

            St[45] = "precise statement";
            St[46] = "what is ELEV1";
            St[47] = "";
            St[48] = "";

            L[5].future = 500;
            L[6].future = 924;
            L[7].future = 925;
            L[8].future = 922;
        }

        if (MESSAGE == 927)
        {
            St[0] = "why A";
            St[1] = "STEP 1";
            St[2] = "Comparing the construction of S1 and the";
            St[3] = "definition of ELEV2 we get that";
            St[4] = "ELEV2(S(E0,Q0;x)-E0)=ELEV2(x), all x in C1";
            St[5] = "ELEV2(S(E0,Q0;x)-E0)=ELEV2(x), all x in C2";
            St[6] = "Assuming ELEV2(C1) # ELEV2(C2)=ELEV2(p0),";
            St[7] = "we get that";
            St[8] = "(S10-E0) # (S20-E0)=A.";
            St[10] = "STEP 2:";
            St[11] = "Erase the plot windows and plot";
            St[12] = "ELEV2(S21 and ELEV2(C1). Note";
            St[13] = "that each arc of ELEV2(S12), which";
            St[14] = "is near ELEV2(S21, rises";
            St[15] = "monotonically up and away from ELEV(C1).";
            St[16] = "Thus, if A' is an R-arc of S21 then";
            St[17] = "ELEV2(A'-C1) # ELEV2(C1) = (()).  Hence";
            St[18] = "ELEV2(S21-C1) # ELEV2(C1)=(()).";
            St[19] = "So ELEV2(S21-C1) # ELEV2(p0)=(())";
            St[20] = "Given ELEV2(S1-E0) # ELEV2(S2-E0)=ELEV2(p0)";
            St[21] = "we have (S21-C1) # (S2-E0)=(()). Also";
            St[22] = "(S21-C2) # (S1-E0)=(()).";

            St[25] = "STEP 3";
            St[26] = "Combining STEP 1 and STEP 2, and noting";
            St[27] = "that C1 is a subset of S10 and";
            St[28] = "that C1 is a subset of S20 we";
            St[29] = "see that (S1-E0) # (S2-E0) = A.";
            St[31] = "Our proof here reduces the 'why A'";
            St[32] = "question to the monotonicity property and the";
            St[33] = "calculus problem mentioned above.";

            St[45] = "S1";
            St[46] = "ELEV2";
            St[47] = "monotonicity property";
            St[48] = "calculus problem";

            L[5].future = 250;
            L[6].future = 700;
            L[7].future = 939;
            L[8].future = 922;

            D.pix = 8;
            D.ACTIVE = 0;
            D.repaint();
        }

        if (MESSAGE == 939)
        {
            St[0] = "monotonicity";

            St[1] = "Let's see the monotonicity property in action.";
            St[2] = "1. Erase everything.";
            St[3] = "2. Set the plot windows to";
            St[4] = "ELEV2, PLAN2, HYP2.";
            St[5] = "3. Select:";
            St[6] = "C1,E2,R1 all in different colors.";
            St[7] = "S12A in red.";
            St[8] = "S12B in blue.";
            St[9] = "4. Plot the picture.";
            St[10] = "5. move the 'truncate arcs' slider to the left.";
            St[11] = "6. Start clicking on the slider so that it";
            St[12] = "goes to the right incrementally, replotting.";

            St[13] = "You can watch the arcs of";
            St[14] = "ELEV2(S12B) fall monotonically and";
            St[15] = "ELEV2(S12A) rise monotonically.";
            St[16] = "You can see the same rising and falling in HYP2.";
            St[17] = "With 2 exceptions, half the arcs (red) rise up";
            St[18] = "and the other half (blue) fall down. The two";
            St[19] = "exceptions are the arcs R1 # S21.";
            St[20] = "You could say that the exceptions are";
            St[21] = "the 2 arcs which divide the red from the blue.";
            St[22] = "Click the link below to learn more about";
            St[23] = "these 2 exceptional arcs.";

            St[25] = "The monotonicity property is really the heart";
            St[26] = "of our proof.  It is the property that";
            St[27] = "theoretically lets us justify that the";
            St[28] = "`show me the money' picture is really";
            St[29] = "what it seems to be.";

            St[48] = "monotonicity, part 2";
            St[47] = "ELEV2";
            St[46] = "S21";
            St[45] = "2 exceptions";

            L[8].future = 941;
            L[7].future = 700;
            L[6].future = 261;
            L[5].future = 980;
        }

        if (MESSAGE == 941)
        {
            St[0] = "monotonicity, part 2";
            St[1] = "Let's analyze the monotonicity property closely.";
            St[2] = "1. Plot a short-long-short combo of thin";
            St[3] = "rectangles connecting C1 to E2, as shown";
            St[4] = "at right.  You can pick the combo at random";
            St[5] = "from the 30 choices, but use the colors";
            St[6] = "indicated at right.";
            St[7] = "2. Repeat the plot for different combos and";
            St[8] = "different parameters.  ";
            St[9] = "3. Click on the 'truncate arcs' slider & replot.";
            St[10] = "4. Put the 'truncate arcs' slider to the right.";
            St[12] = "You see a union of 2 smooth arcs of nonzero";
            St[13] = "slope, joined at 2 cusps. This is discussed";
            St[14] = "in ELEV2 part 3.  Notice that the blue";
            St[15] = "arc never contains a cusp in it's interior.";
            St[16] = "Cusp 1 is the blue/red intersection and";
            St[17] = "Cusp 2 is disjoint from the blue arc.";
            St[18] = "Click the links for the explanation.";

            St[19] = "Now add C1 and Q2 to the plot.  Note that the";
            St[20] = "blue arc joins ELEV2(C1) to ELEV2(Q2). Note";
            St[21] = "also that the slope of the blue arc near";
            St[22] = "ELEV2(C1) is larger than the slope of ELEV2(C1)";
            St[23] = "This comes from the fact that C1 is a C-circle";
            St[24] = "transverse to the contact structure whereas";
            St[25] = "each R-arc of S21 is integral to the";
            St[26] = "contact structure. Hence the blue arc is";
            St[27] = "transverse to ELEV2(C1) at the point";
            St[28] = "A single plot is enough to see that the";
            St[29] = "slope of the blue arc is always greater";
            St[30] = "rather than always lesser than the";
            St[31] = "slope of ELEV2(C1) at this point.";
            St[32] = "The inequality persists for enough of";
            St[33] = "the arc to give the monotonicity property.";
            St[34] = "See me preprint for more details.";

            St[48] = "contact structure";
            St[47] = "ELEV2";
            St[46] = "about cusp 2";
            St[45] = "about cusp 1";

            L[8].future = 770;
            L[7].future = 700;
            L[6].future = 960;
            L[5].future = 950;

            D.pix = 6;
            D.ACTIVE = 0;
            D.repaint();
        }

        if (MESSAGE == 950)
        {
            St[0] = "about cusp 1";
            St[1] = "Repeat the experiment at the top of";
            St[2] = "the previous page, this time plotting";
            St[3] = "the objects shown at right.";
            St[5] = "Look at the picture in PLAN1.  Note that";
            St[6] = "the sky blue arc and the blue arc have the";
            St[7] = "the same tangent line at the green circle.";
            St[8] = "The same thing goes in HYP1.  In ELEV2";
            St[9] = "The sky blue arc maps to a point.";

            St[11] = "The R-arcs, represented by these plots";
            St[12] = "indeed have the same tangent line at";
            St[13] = "their common point, which lies on C1.";
            St[14] = "The idea is that these R-arcs are tangent to";
            St[15] = "the contact structure, and the map I1 rotates";
            St[16] = "the contact planes, based at points of C1, by";
            St[17] = "180 degrees. Moreover, I1 swaps the arcs.";

            St[19] = "The sky blue arc, being an arc of";
            St[20] = "S10, is contained in a fiber of ELEV2.";
            St[21] = "Hence the blue arc is tangent to a fiber of";
            St[22] = "ELEV2 at its endpoint.  Such tangencies";
            St[23] = "correspond to the cusps. Hence, Cusp 1";
            St[24] = "is at the endpoint of the blue arc in";
            St[25] = "ELEV2, as claimed above.";

            St[48] = "contact structure";
            St[47] = "coords";
            St[46] = "C-reflections";
            St[45] = "triangle groups";

            L[8].future = 770;
            L[7].future = 700;
            L[6].future = 350;
            L[5].future = 280;

            D.pix = 9;
            D.ACTIVE = 0;
            D.repaint();
        }

        if (MESSAGE == 960)
        {
            St[0] = "about cusp 2";
            St[1] = "Our argument is based on the height principle";
            St[2] = "discussed in HYP1 part 3.  Say that a 'relevant";
            St[3] = "R-circle' is one of the R-circles which";
            St[4] = "contains an arc of S21.  These";
            St[5] = "R-circles correspond to the red-blue-green";
            St[6] = "combos we have been plotting. Plot one now.";
            St[8] = "Certainly there are some relevant R-circles";
            St[9] = "for which the second cusp is disjoint from the";
            St[10] = "blue arc-e.g. the one you just plotted!";
            St[11] = "If the property ever fails then there is some";
            St[12] = "relevant R-circle for which the second cusp";
            St[13] = "is an endpoint of the blue arc. (Here we mean";
            St[14] = "the hypothetical continuum of relevant";
            St[15] = "R-circles, not just the 30 you can plot.) Given";
            St[16] = "we know about the first cusp, we would have";
            St[17] = "produced a relevant R-circle X for which";
            St[18] = "the two cusps of ELEV2(X) are the endpoints of";
            St[19] = "the blue arc of X, namely X # S21.";
            St[20] = "Call X a wierd R-circle.  We have to rule";
            St[21] = "out the existence of wierd R-circles.";
            St[23] = "Suppose X is a wierd R-circle.  By symmetry";
            St[24] = "we can assume that X is in S12B";
            St[25] = "(exercise: plot such an X.";
            St[26] = "Of course, the one you plot";
            St[27] = "won't be wierd; there aren't any wierd ones!";
            St[28] = "now add C1 and E2 to the plot.)";
            St[30] = "Note that the blue-green and red-green";
            St[31] = "intersection points of ELEV2(X) both lie on";
            St[32] = "on ELEV2(E2).  The cusp points of ELEV2(X) are";
            St[33] = "the points of extreme height.  Hence, the";
            St[34] = "b/g point lies above the r/g point.";

            St[48] = "ELEV2";
            St[47] = "HYP1 part 3";
            St[46] = "cusp 2, part 2.";
            St[45] = "about cusp 1";

            L[8].future = 700;
            L[7].future = 794;
            L[6].future = 961;
            L[5].future = 950;

            D.pix = 6;
            D.ACTIVE = 0;
            D.repaint();
        }

        if (MESSAGE == 961)
        {
            St[0] = "cusp 2, part 2.";
            St[1] = "For the R-circle you have";
            St[2] = "plotted the r/g point lies above the";
            St[3] = "the b/g point.  Hence, by continuity there is";
            St[4] = "some relevant R-circle Y for which the b/g";
            St[5] = "point and the r/g point have the same";
            St[6] = "height.  By the height principle for HYP1,";
            St[7] = "the b/g points and r/g points of HYP1(Y)";
            St[8] = "lie one the same fiber geodesic.";

            St[10] = "What is wrong with this picture. If";
            St[11] = "(the parameter) s=0 there is nothing wrong";
            St[12] = "with this picture. (exercise: do the plot for";
            St[13] = "s=0 note that the b/g and r/g points lie";
            St[14] = "on the same fiber geodesic in HYP1.) In";
            St[15] = "this case we contradict the existence of";
            St[16] = "X directly: the b/g and r/g points of HYP1(X)";
            St[17] = "do not lie on the same fiber geodesic.)";
            St[18] = "Henceforth assume s>0.";

            St[19] = "Assume that the b/g and r/g points of";
            St[20] = "HYP1(Y) lie on the same fiber geodesic g";
            St[21] = "Recalling the construction of S21";
            St[22] = "we note that the r/g and b/g points of Y";
            St[23] = "are harmonic partners with respect to (E2,Q2)";
            St[24] = "Since HYP1 is linear fractional when restricted";
            St[25] = "to E2 we see from basic conformal geometry";
            St[26] = "that g is perpendicular to the circle V2";
            St[27] = "which is perpendicular to HYP1(E2) and contains";
            St[28] = "the endpoints of HYP1(Q2).";
            St[29] = "On the other hand, g is also perpendicular";
            St[30] = "to the circle V0 which is perpendicular to";
            St[31] = "HYP1(E0) and contains th endpoints of HYP1(Q0)";
            St[32] = "Finally, g is perpendicular to the line V1";
            St[33] = "bounding the half-plane.  In summary g is perp.";
            St[34] = "to V0,V1,V2. We will now rules this out.";

            St[48] = "ELEV2";
            St[47] = "HYP1 part 3";
            St[46] = "cusp 2, part 3.";
            St[45] = "S21";

            L[8].future = 700;
            L[7].future = 794;
            L[6].future = 962;
            L[5].future = 261;
        }

        if (MESSAGE == 962)
        {
            St[0] = "cusp 2, part 3.";
            St[1] = "STEP 1";
            St[2] = "The geodesic g lies on a circle which";
            St[3] = "is perpendicular to the three circles V0,V1,V2";
            St[4] = "We think of the line V1 as a generalized circle";
            St[5] = "Such a 'triply perpendicular circle' is unique";
            St[6] = "unless V0 # V1 # V2 is nonempty.";

            St[8] = "STEP 2";
            St[9] = "In fact V0#V1#V2=(()) for s>0. This is hard";
            St[10] = "to see for s small, but for (say) s>4 it is";
            St[11] = "obvious from the plots.  To actually prove the";
            St[12] = "Goldman-Parker conjecture we only need the";
            St[13] = "case s>sqrt(35)>4.  So we will not further";
            St[14] = "justify the claim that V0#V1#V2=(()).";

            St[19] = "STEP 3:";
            St[20] = "Given that V0#V1#V2=(()), the geodesic g is";
            St[21] = "UNIQUE. Let R1 be the axis of S1.  Then";
            St[22] = "HYP1(R1) is a fiber geodesic which, by symmetry";
            St[23] = "is simultaneously perpendicular to V0,V1,V2.";
            St[24] = "By uniqueness g=HYP1(R1).";

            St[26] = "STEP 4:";
            St[27] = "Given that g=HYP1(R1) the height principle";
            St[28] = "tells us that ELEV2 maps the b/g and r/g";
            St[29] = "points of Y into the set ELEV2(C1) # ELEV2(E2)";
            St[30] = "But then ELEV2(Y) is neither up-monotonicity nor";
            St[31] = "nor down monotonicity.  It is one of the 2";
            St[32] = "exceptions.  This is a contradiction.";

            St[48] = "precise statment";
            St[47] = "HYP1 part 3";
            St[46] = "2 exceptions";
            St[45] = "";

            L[8].future = 500;
            L[7].future = 794;
            L[6].future = 980;
            L[5].future = 962;
        }

        if (MESSAGE == 980)
        {
            St[0] = "2 exceptions";
            St[1] = "S1 is made from the union of 2 hemispheres,";
            St[2] = "each foliated by R-arcs.  It turns out that";
            St[3] = "there is a unique R-circle R1 contained in S1.";
            St[4] = "R1 intersects each hemisphere in 2 of the";
            St[5] = "R-arcs.  In my preprint I call R1 the R-axis";
            St[6] = "You can plot a picture of R1 using the applet,";
            St[7] = "The axis R2 is defined similarly for S2.";
            St[9] = "We have ELEV2(R1-E0)=ELEV2(C1) # ELEV2(E2).";
            St[10] = "ELEV2 maps the 2 R-arcs of R1 # S21";
            St[11] = "to the two points of ELEV2(C1) # ELEV2(E2).";
            St[12] = "this accounts for the two exceptional arcs";
            St[13] = "which neither rise nor fall. These";
            St[14] = "exceptional arcs must exist, by symmetry.";
            St[15] = "There is no other way to make the transition";
            St[16] = "from rising to falling.";

            St[48] = "ELEV2";
            St[47] = "S1";
            St[46] = "";
            St[45] = "";

            L[8].future = 700;
            L[7].future = 250;
            L[6].future = 980;
            L[5].future = 980;
        }


        if (MESSAGE == 998)
        {
            St[0] = "gallery";
            St[1] = "Click the purple buttons to see some nice";
            St[2] = "plots.  Once a plot is done you can use";
            St[3] = "the applet controls to adjust it any way";
            St[4] = "you like.";

            St[48] = "";
            St[47] = "";
            St[46] = "";
            St[45] = "";

            L[8].future = 998;
            L[7].future = 998;
            L[6].future = 998;
            L[5].future = 998;

            GALLERY = 1;
            repaint();
        }


        if (MESSAGE == 999)
        {
            St[0] = "notation";
            St[1] = "R and C denote the real and complex numbers.";
            St[2] = "R^n is real n-space; C^n is complex n-space";
            St[3] = "CP^2 denotes the complex projective plane.";
            St[4] = "S^3 is the unit 3-sphere in C^2.";
            St[5] = "CH^2 is the open unit ball in C^2.";
            St[6] = "PU(2,1) is the automorphism group of CH^2.";
            St[8] = "[z] = the complex conjugate of z";
            St[9] = "<v,w> = (2,1) Hermitian dot of x and y.";
            St[10] = "A # B = the intersection of A and B.";
            St[11] = "A u. B = the union of A and B.";
            St[12] = "(()) = emptyset.";

            St[13] = "p0,p1,p2: vertices of the ideal triangle.";
            St[14] = "I0,I1,I2: generators of the group";
            St[15] = "C0,C1,C2: C-circles fixed by I0,I1,I2";
            St[16] = "E0: C-circle stabilized by I1.I0.I2";
            St[17] = "Q0: C-arc of E0 stabilized by I1.I0.I2.";
            St[18] = "E1=I2(E0) and E2=I1(E0).  Likewise for Q1,Q2.";
            St[19] = "S0,S1,S2: R-spheres stabilized by I0,I1,I2";
            St[20] = "S10, S21: hemispheres of S1.";
            St[21] = "S20, S21: hemispheres of S2.";
            St[22] = "R1,R2: R-axes of S1 and S2.";
            St[23] = "S(E0,Q0;x): ELEV2 fiber connecting x to Q0.";
            St[24] = "A=S(E0,Q0;p0).";
            St[25] = "Some Relations:";
            St[26] = "S10=S10A u. S10B, etc.";
            St[27] = "S1=S10 u. S12";
            St[28] = "S2=S20 u. S21";
            St[29] = "C1=S10 # S12";
            St[30] = "C2=S20 # S21";
            St[31] = "S10 # S12=C1";
            St[32] = "S20 # S21=C2.";
            St[33] = "(S10A u. S12A) # (S10B u. S12B)=R1";
            St[34] = "(S20A u. S21A) # (S20B u. S21B)=R2";
            St[35] = "Q1#=E1 # S2= Q1 # S2";
            St[36] = "Q2#=E2 # S1= Q2 # S1";

            St[48] = "type (2,1) form";
            St[47] = "triangle group";
            St[46] = "C-circles and C-arcs";
            St[45] = "R-circles and R-arcs";

            L[8].future = 221;
            L[7].future = 280;
            L[6].future = 210;
            L[5].future = 211;
        }

        if (MESSAGE == 1000)
        {
            St[0] = "math";
            St[1] = "Here is a list of the basic math topics";
            St[2] = "underlying the applet.  This is not the";
            St[3] = "complete list.  Some additional topics are";
            St[4] = "introduced elsewhere in the text.  However,";
            St[5] = "this is a pretty good foundation.";

            St[7] = "1. Hermitian Geometry";
            St[8] = "a. The form of type (2,1)";
            St[9] = "b. CP^2";
            St[10] = "c. PU(2,1)";
            St[11] = "d. The element classification in PU(2,1)";

            St[13] = "2. CR Objects";
            St[14] = "a. C-circles and C-arcs";
            St[15] = "b. R-circles and R-arcs";
            St[16] = "c. The contact structure";

            St[18] = "3. Triangle Groups";

            St[20] = "Bill Goldman's book has information about all";
            St[21] = "these topics, and so does my preprint.";

            L[8].future = 240;
            L[6].future = 120;
            L[5].future = 110;
            L[7].future = 280;

            St[48] = "Bill's book";
            St[46] = "2. CR objects";
            St[45] = "1. Hermitian Geometry";
            St[47] = "3. triangle groups";
        }

        if (MESSAGE == 1001)
        {
            St[0] = "modify applet";
            St[1] = "If you click the button below, the";
            St[2] = "background color in this window changes";
            St[3] = "to the color shown on the color selector.";

            St[13] = "These buttons control the font type.";
            St[14] = "The first 3 buttons let you choose between 3";
            St[15] = "different fonts.";
            St[16] = "The second 6 buttons let you choose the";
            St[17] = "font size, from 9 pts to 14 pts.";
            St[18] = "The last button resets the font to the";
            St[19] = "original value, which is 12pt Helvetica.";

            St[25] = "These buttons toggle the double buffering";
            St[26] = "feature.  The double buffering makes the";
            St[27] = "graphics look better but some browsers have";
            St[28] = "trouble with it.  If the applet seems to be";
            St[29] = "running fine, it is best to leave these buttons";
            St[30] = "alone. The 6 buttons correspond in an obvious way";
            St[31] = "to the 6 windows of the applet (yellow=on)";

            L[8].future = 1001;
            L[6].future = 1001;
            L[5].future = 1001;
            L[7].future = 1001;

            St[48] = "";
            St[46] = "";
            St[45] = "";
            St[47] = "";
        }
    }
}
