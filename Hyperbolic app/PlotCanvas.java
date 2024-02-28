/*plotting windows*/

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

class PlotCanvas extends DoubleBufferedCanvas implements MouseListener, MouseMotionListener
{
    int TYPE, MODE, ACTIVE, COMPUTE, MULTIPLICITY, DENSITY;
    DocumentCanvas D;
    double XORIGIN, YORIGIN, SCALE, XSCALE, YSCALE, NUMBER, TRUNCATE;
    RSphere S1, S2;
    Segment[] A = new Segment[2000];
    Color C, COL, TCOL;
    PlotCanvas(int j,DocumentCanvas D) {
        this.TYPE = j;
        this.D = D;
        COMPUTE = 0;
        COMPUTE = 0;
        MODE = 1;
        ACTIVE = 1;


        SCALE = 1.0;
        MULTIPLICITY = 1;
        COL = Color.black;
        TCOL = new Color(0,0,100);
        this.XSCALE = 60.0;
        this.YSCALE = 60.0;
        this.SCALE = 1;
        this.XORIGIN = 0.0;
        if(TYPE == 3) XORIGIN =- 120;
        this.YORIGIN = 0.0;
        this.C = Color.white;
        addMouseListener(this);
        addMouseMotionListener(this);
        setBackground(Color.black);
        S1 = new RSphere(0,0,0,0);
        S2 = new RSphere(0,0,0,0);
        for(int i = 1; i <= 1999; i++) A[i] = new Segment();
        NUMBER = 0;
    }


    public void paint(Graphics gfx)
    {
        Graphics2D g = (Graphics2D)gfx;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if(ACTIVE == 1)
        {
            g.setColor(Color.white);
            g.drawRect(0,0,302,269);
            g.clipRect(1,1,301,268);
            NUMBER = 0;
            for(int k = 1; k <= 6; k++)
            {
                for(int i = 1;i <= 30; i++)
                {
                    if(S1.A[k][i].select == 1)
                    {
                        for(int m = 1; m <= MULTIPLICITY; m++)
                        {
                            double mm = (m - 1.0)/MULTIPLICITY;
                            mm = mm - 1.0 + 0.5/MULTIPLICITY;
                            S_set(1, k, S1.A[k][i].C, i + mm);
                            currentRender(g);
                        }
                    }
                }
            }

            for(int k = 1; k <= 6; k++)
            {
                for(int i = 1;i <= 30; i++)
                {
                    if(S2.A[k][i].select == 1)
                    {
                        for(int m = 1; m <= MULTIPLICITY; m++)
                        {
                            double mm = (m - 1.0)/MULTIPLICITY;
                            mm = mm - 1.0 + 0.5/MULTIPLICITY;
                            S_set(2, k, S2.A[k][i].C, i + mm);
                            currentRender(g);
                        }
                    }
                }
            }

            if(S1.S[9].select == 1)
            {
                C_set(1, S1.S[9].C);
                currentRender(g);
            }
            if(S2.S[9].select == 1)
            {
                C_set(2, S2.S[9].C);
                currentRender(g);
            }
            if(S1.S[10].select == 1)
            {
                E_set(2, S1.S[10].C);
                currentRender(g);
            }
            if(S1.S[11].select == 1)
            {
                Q_set(2, S1.S[11].C);
                currentRender(g);
            }
            if(S2.S[10].select == 1)
            {
                E_set(1, S2.S[10].C);
                currentRender(g);
            }
            if(S2.S[11].select == 1)
            {
                Q_set(1, S2.S[11].C);
                currentRender(g);
            }
            if(S1.S[14].select == 1)
            {
                HQ_set(S1.S[14].C);
                currentRender(g);
            }
            if(S2.S[14].select == 1)
            {
                HQ_set(S2.S[14].C);
                currentRender(g);
            }
            if(S1.S[15].select == 1)
            {
                R_set(1,S1.S[15].C);
                currentRender(g);
            }
            if(S2.S[15].select == 1)
            {
                R_set(2,S2.S[15].C);
                currentRender(g);
            }
            if(S1.S[18].select == 1)
            {
                QS_set(2, S1.S[18].C);
                currentRender(g);
            }
            if(S2.S[18].select == 1)
            {
                QS_set(1, S2.S[18].C);
                currentRender(g);
            }

            if(S1.S[7].select == 1)
            {
                E_set(0, S1.S[7].C);
                currentRender(g);
            }
            if(S2.S[7].select == 1)
            {
                E_set(0, S2.S[7].C);
                currentRender(g);
            }
            if(S1.S[8].select == 1)
            {
                Q_set(0, S1.S[8].C);
                currentRender(g);
            }
            if(S2.S[8].select == 1)
            {
                Q_set(0, S2.S[8].C);
                currentRender(g);
            }

            borderRender(g);
        }
    }

    void borderRender(Graphics g)
    {
        int xTrans=150;
        g.translate(150, 135);
        int x = (int)(XORIGIN);
        int y = (int)(YORIGIN);
        int r = (int)(XSCALE);
        int sx = (int)(XSCALE);
        int sy = (int)(YSCALE);
        Color C = new Color(0, 0, 100);
        Color C1 = new Color(0, 0, 255);

        if(TYPE == 1)
        {
            g.setColor(C);
            g.fillRect(x-22*sx, y-20*sy, 20*sx, 40*sy);
            g.fillRect(x+2*sx, y-20*sy, 20*sx, 40*sy);
            g.setColor(C1);
            g.drawRect(x-22*sx, y-20*sy, 20*sx, 40*sy);
            g.drawRect(x+2*sx, y-20*sy, 20*sx, 40*sy);
        }

        if(TYPE == 3)
        {
            g.setColor(C);
            g.fillRect(x-20*sx, y-20*sy, 20*sx-1, 40*sy);
            g.setColor(C1);
            g.drawRect(x-20*sx, y-20*sy, 20*sx-1, 40*sy);
        }

        if(TYPE == 2)
        {
            g.setColor(Color.white);
            g.drawOval(x-1, y-1, 1, 1);
        }
        g.translate(-150, -135);
    }

    void currentRender(Graphics g)
    {
        g.setColor(C);
        g.translate(150, 135);
        double test = 0;
        Complex w1 = new Complex(0, 0);
        Complex w2 = new Complex(0, 0);
        Segment B = new Segment(w1, w2, C);
        for(int i = 1;i <= NUMBER; i++)
        {
            test = A[i].z1.x - A[i].z2.x;
            test = Math.abs(test);
            if(test < 2.0)
            {
                B.z1.x = A[i].z1.x * XSCALE + XORIGIN;
                B.z1.y = A[i].z1.y * YSCALE + YORIGIN;
                B.z2.x = A[i].z2.x * XSCALE + XORIGIN;
                B.z2.y = A[i].z2.y * YSCALE + YORIGIN;
                B.C = C;
                B.render(g);
            }

            if(TYPE == 1)
            {
                if((A[i].z2.x < A[i].z1.x) && (test > 2.0))
                {
                    B.z1.x = (A[i].z1.x) * XSCALE + XORIGIN;
                    B.z1.y = A[i].z1.y * YSCALE + YORIGIN;
                    B.z2.x = (4.0 + A[i].z2.x) * XSCALE + XORIGIN;
                    B.z2.y = A[i].z2.y * YSCALE + YORIGIN;
                    B.C = C;
                    B.render(g);
                }

                if((A[i].z2.x > A[i].z1.x) && (test > 2.0))
                {
                    B.z1.x = (4.0 + A[i].z1.x) * XSCALE + XORIGIN;
                    B.z1.y = A[i].z1.y * YSCALE + YORIGIN;
                    B.z2.x = (A[i].z2.x) * XSCALE + XORIGIN;
                    B.z2.y = A[i].z2.y * YSCALE + YORIGIN;
                    B.C = C;
                    B.render(g);
                }

                if((A[i].z2.x < A[i].z1.x) && (test > 2.0))
                {
                    B.z1.x = (-4.0 + A[i].z1.x) * XSCALE + XORIGIN;
                    B.z1.y = A[i].z1.y * YSCALE + YORIGIN;
                    B.z2.x = (A[i].z2.x) * XSCALE + XORIGIN;
                    B.z2.y = A[i].z2.y * YSCALE + YORIGIN;
                    B.C = C;
                    B.render(g);
                }

                if((A[i].z2.x > A[i].z1.x) && (test > 2.0))
                {
                    B.z1.x = (A[i].z1.x) * XSCALE + XORIGIN;
                    B.z1.y = A[i].z1.y * YSCALE + YORIGIN;
                    B.z2.x = (-4.0 + A[i].z2.x) * XSCALE + XORIGIN;
                    B.z2.y = A[i].z2.y * YSCALE + YORIGIN;
                    B.C = C;
                    B.render(g);
                }
            }
        }
        g.translate(-150, -135);
    }

    void C_set(int q, Color C)
    {
        int n = 2 * DENSITY;
        Matrix m = new Matrix();
        Vector v1, v2;
        NUMBER = n;
        this.C = C;
        double u1 = 0.0;
        double u2 = 0.0;

        if(q == 1)
        {
            m.a = S1.P0;
            m.b = S1.P2;
            v1 = S1.M2.c;
            v1 = v1.normalize(v1);
            m.b.a = v1.a;
        }

        if(q == 2)
        {
            m.a = S1.P0;
            m.b = S1.P1;
            v1 = S1.M2.c;
            v1 = v1.normalize(v1);
            m.b.b = v1.b;
            m.a = S1.C21;
            m.b = S1.C22;
        }

        Complex z1 = new Complex();
        v1 = m.C_param(0.0);
        z1 = doCoords(v1);
        for(int i = 1; i <= n; i++)
        {
            A[i].z1 = z1;
            u2 = i;
            u2 = Math.PI * u2/n;
            v2 = m.C_param(u2);
            A[i].z2 = doCoords(v2);
            z1 = A[i].z2;
        }
    }


    void R_set(int q, Color C)
    {
        int n = DENSITY;
        this.C = C;
        Matrix M = new Matrix();
        double u1 = 0;
        double u2 = 0;
        Vector v1 = new Vector();
        Vector v2 = new Vector();
        if(q == 1) M = S1.M1.doCone(S1.C11);
        if(q == 2) M = S1.M1.doCone(S1.C21);
        int lim1 = 1;
        int lim2 = n;
        int num = n;
        NUMBER = 0;
        if(TYPE != 1)
        {
            NUMBER = 3 * num;
            v1 = M.R_param(0);
            Complex z1 = doCoords(v1);
            for(int i = lim1; i <= lim2; i++)
            {
                A[i].z1 = z1;
                u2 = i;
                u2 = u2/n;
                v2 = M.R_param(u2);
                A[i].z2 = doCoords(v2);
                z1 = A[i].z2;
            }

            if(q == 1) M = S1.M1.doLCone(S1.C11);
            if(q == 2) M = S1.M1.doLCone(S1.C21);
            v1 = M.R_param(0);
            z1 = doCoords(v1);
            for(int i = lim1; i <= lim2; i++)
            {
                A[i + num].z1 = z1;
                u2 = i;
                u2 = u2/n;
                v2 = M.R_param(u2);
                A[i + num].z2 = doCoords(v2);
                z1 = A[i + num].z2;
            }

            if(q == 1) M = S1.M1.doRCone(S1.C11);
            if(q == 2) M = S1.M1.doRCone(S1.C21);

            v1 = M.R_param(0);
            z1 = doCoords(v1);
            for(int i = lim1; i <= lim2; i++)
            {
                A[i + 2 * num].z1 = z1;
                u2 = i;
                u2 = u2/n;
                v2 = M.R_param(u2);
                A[i + 2 * num].z2 = doCoords(v2);
                z1 = A[i + 2 * num].z2;
            }
        }
    }

    void E_set(int q, Color C)
    {
        int n = 3 * DENSITY;
        Matrix m = new Matrix();
        Vector v1, v2;
        NUMBER = 0;
        if((q != 0) || (TYPE == 2) || ((TYPE == 3) && (MODE == 0)))
        {
            NUMBER = n;
            this.C = C;
            double u1 = 0.0;
            double u2 = 0.0;
            m.a = S1.M2.b;
            m.b = S1.M2.c;

            v1 = m.C_param(0.0);
            if(q == 1) v1 = S1.I2.act(v1);
            if(q == 2) v1 = S1.I1.act(v1);
            Complex z1 = doCoords(v1);
            for(int i = 1; i <= n; i++)
            {
                A[i].z1 = z1;
                u2 = i;
                u2 = Math.PI * u2/n;
                v2 = m.C_param(u2);
                if(q == 1) v2 = S1.I2.act(v2);
                if(q == 2) v2 = S1.I1.act(v2);

                A[i].z2 = doCoords(v2);
                z1 = A[i].z2;
            }
        }
    }

    void Q_set(int q, Color C)
    {
        int n = DENSITY;
        Matrix m = new Matrix();
        Vector v1, v2;
        NUMBER = 0;
        this.C = C;
        if((q != 0) || (TYPE==2) || ((TYPE == 3) && (MODE == 0)))
        {
            NUMBER = n;
            this.C = C;
            double u1 = 0.0;
            double u2 = 0.0;
            m.a = S1.M1.b;
            m.b = S1.M1.c;

            v1 = m.C_param(0.0);
            if(q == 1) v1 = S1.I2.act(v1);
            if(q == 2) v1 = S1.I1.act(v1);
            Complex z1 = doCoords(v1);
            for(int i = 1; i <= n; i++)
            {
                A[i].z1 = z1;
                u2 = i;
                u2 = 0.5 * Math.PI * u2/n;
                v2 = m.C_param(u2);
                if(q == 1) v2 = S1.I2.act(v2);
                if(q == 2) v2 = S1.I1.act(v2);
                A[i].z2 = doCoords(v2);
                z1 = A[i].z2;
            }
        }
    }


    void QS_set(int q, Color C)
    {
        int n = DENSITY;
        Matrix m = new Matrix();
        Vector v1, v2;
        NUMBER = 0;
        this.C = C;
        if((q != 0) || (TYPE == 2) || ((TYPE == 3) && (MODE == 0)))
        {
            NUMBER = n;
            this.C = C;
            double u1 = 0.0;
            double u2 = 0.0;

            if(q == 1)
            {
                m.b = S1.C21;
                m.a = S1.C22;
            }
            if(q == 2)
            {
                m.a = S1.C11;
                m.b = S1.C12;
            }
            Matrix mm = new Matrix();

            mm.b = S1.M1.harmonic1(m.C_param(Math.PI/4.0));
            mm.a = S1.M1.harmonic1(m.C_param(-Math.PI/4.0));

            v1 = mm.C_param(0.0);
            if(q == 1) v1 = S1.I2.act(v1);
            if(q == 2) v1 = S1.I1.act(v1);
            Complex z1 = doCoords(v1);
            for(int i = 1; i <= n; i++)
            {
                A[i].z1 = z1;
                u2 = i;
                u2 = 0.5 * Math.PI * u2/n;
                v2 = mm.C_param(u2);
                if(q == 1) v2 = S1.I2.act(v2);
                if(q == 2) v2 = S1.I1.act(v2);
                A[i].z2 = doCoords(v2);
                z1 = A[i].z2;
            }
        }
    }

    void S_set(int q, int k, Color C, double POS)
    {
        int n = DENSITY;
        Matrix m = new Matrix();
        Vector v1, v2, v3;
        NUMBER = TRUNCATE * n;
        this.C = C;
        double u1 = 0.0;
        double u2 = 0.0;

        if(q == 1)
        {
            m.a = S1.C12;
            m.b = S1.C11;
        }

        if(q == 2)
        {
            m.a = S1.C21;
            m.b = S1.C22;
        }

        double theta = Math.PI * POS/30.0;
        v3 = m.C_param(theta);
        Matrix M = new Matrix();
        if(k == 1) M = S1.M1.doLCone(v3);
        if(k == 2) M = S1.M1.doCone(v3);
        if(k == 3) M = S1.M1.doRCone(v3);
        if(k == 4) M = S1.M1.doLCone(v3);
        if(k == 5) M = S1.M1.doCone(v3);
        if(k == 6) M = S1.M1.doRCone(v3);
        NUMBER = 0;

        int lim1 = 1;
        int lim2 = n;
        int num = n;

        if((k <= 3) && (TYPE == 1))
        {
            lim1 = 1;
            lim2 = n - 1;
            num = n - 1;
        }

        NUMBER = num * TRUNCATE;
        v1 = M.R_param((lim1 + .0000001)/n);
        if((k != 3) || (TYPE != 1)) v1 = M.R_param(0);
        if((q == 1) && (k > 3)) v1 = S1.I1.act(v1);
        if((q == 2) && (k > 3)) v1 = S1.I2.act(v1);
        Complex z1 = doCoords(v1);

        for(int i = lim1; i <= lim2; i++)
        {
            A[i].z1 = z1;
            u2 = i;
            u2 = u2/n;
            v2 = M.R_param(u2);
            if((q == 1) && (k > 3)) v2 = S1.I1.act(v2);
            if((q == 2) && (k > 3)) v2 = S1.I2.act(v2);
            A[i].z2 = doCoords(v2);
            z1 = A[i].z2;
        }
    }


    void HQ_set(Color C)
    {
        int n = DENSITY;
        Matrix m = new Matrix();
        Matrix m2 = new Matrix();
        Vector v1 = new Vector();
        Vector v2 = new Vector();
        double u1 = 0;
        double u2 = 0;
        v1.a = v1.a.convert(1.0, 0.0);
        v1.b = v1.b.convert(0.0, 0.0);
        v1.c = v1.c.convert(1.0, 0.0);
        NUMBER = 0;
        if(TYPE == 3)
        {
            NUMBER = n;
            this.C = C;
            m.a = S1.M1.b;
            m.b = S1.M1.c;
            m.c = m.a.spinalExtension(m.a, m.b, v1);
            m2.a = m.c;
            m2.b = m.b;
            m2.c = m.a;

            v1 = m2.R_param(0);
            Complex z1 = doCoords(v1);

            for(int i = 1; i <= n; i++)
            {
                A[i].z1 = z1;
                u2 = i;
                u2 = u2/n;
                v2 = m2.R_param(u2);
                A[i].z2 = doCoords(v2);
                z1 = A[i].z2;
            }
        }
    }

    Complex doCoords(Vector v1)
    {
        Complex A = new Complex();

        if((TYPE == 1) && (MODE == 0))
        {
            Complex u = new Complex(1.0, 0.0);
            u = u.inverse(S1.Z3);
            u.x = -u.x;
            u.y = -u.y;
            A = S1.M2.ELEV1(v1, u);
            A.x = 4 * A.x - 2;
            A.y = -2.0 * A.y/S1.Z5.y;
        }

        if((TYPE==1)&&(MODE==1)) {

            double top=Math.sqrt(125.0/3.0);
            A=S1.M1.ELEV2(v1);
            A.x=4*A.x-2;
            A.y=2.0*A.y/S1.Z8.y;
        }


        if((TYPE==2)&&(MODE==0)) {

            Complex w=new Complex();
            w=w.inverse(S1.Z3);
            w=w.unit(w);
            A=S1.M2.PLAN1(v1);
            A=A.times(A,w);
            A.y=-A.y/S1.Z6.y;
            A.x=A.x/S1.Z6.y;
        }


        if((TYPE==2)&&(MODE==1)) {
            Complex w=new Complex();
            w=w.inverse(S1.Z4);
            w=w.unit(w);
            A=S1.M1.PLAN2(v1);
            A=A.times(A,w);
            A.y=-A.y/S1.Z7.y;
            A.x=A.x/S1.Z7.y;
            A.x=A.x*S1.Z7.x;
        }

        if((TYPE==3)&&(MODE==0)) {
            A=S1.M1.HYP1(v1);
            Complex z1=A.minus(A,S1.Z1);
            Complex z2=A.minus(A,S1.Z2);
            A=z1.divide(z1,z2);
            A=z1.divide(A,new Complex(S1.Z5.y,0.0));
            A.x=-2.0*A.x;
            A.y=2.0*A.y;
        }

        if((TYPE==3) && (MODE==1)) {
            A=S1.M1.HYP2(v1);
            A.x=2.0*A.x/S1.Z8.y;
            A.y=2.0*A.y/S1.Z8.y;
            A.x=A.x/S1.Z8.x;
        }
        return(A);
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}

    public void mouseMoved(MouseEvent e) {
        if(TYPE == 1) D.MESSAGE = 4;
        if(TYPE == 2) D.MESSAGE = 5;
        if(TYPE == 3) D.MESSAGE = 6;
        if((D.ACTIVE == 1) && (D.MESSAGE != D.history)) D.repaint();
    }

    public void mouseClicked(MouseEvent e) {
        D.MESSAGE=50;
        D.repaint();
        Point p=pinpoint(e);

        if((p.x<300)&&(p.y>18)&&(p.y<282)) {
            double xx=p.x-150.0;
            double yy=p.y-135.0;
            if(SCALE>=1) {
                if((XSCALE<1200)&&(YSCALE<1200)) {
                    XSCALE=XSCALE*SCALE;
                    YSCALE=YSCALE*SCALE;
                    XORIGIN=xx+SCALE*(XORIGIN-xx);
                    YORIGIN=yy+SCALE*(YORIGIN-yy);
                    repaint();
                }}

            if(SCALE<1) {
                if((XSCALE>12)&&(YSCALE>12)) {
                    XSCALE=XSCALE*SCALE;
                    YSCALE=YSCALE*SCALE;
                    XORIGIN=xx+SCALE*(XORIGIN-xx);
                    YORIGIN=yy+SCALE*(YORIGIN-yy);
                    repaint();
                }}
        }
    }

    public void mouseEntered(MouseEvent e) {
        if(TYPE==1) D.MESSAGE=4;
        if(TYPE==2) D.MESSAGE=5;
        if(TYPE==3) D.MESSAGE=6;
        if(D.ACTIVE==1) D.repaint();
    }

    public void mouseExited(MouseEvent e) {
        D.MESSAGE=-1;
        if(D.ACTIVE==1)      D.repaint();
    }
}