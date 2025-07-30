/*This is the object selector*/

import java.awt.*;

class RSphere
{
    int x, y, w, h;
    ListenSquare[] S = new ListenSquare[20];
    ListenSquare[][] A = new ListenSquare[7][45];
    Vector P0, P1, P2;
    Vector C21, C22, C11, C12;
    Matrix M1, M2, I0, I1, I2;
    Complex Z1, Z2, Z3, Z4, Z5, Z6, Z7, Z8;
    double s;

    //sets GUI elements
    RSphere(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        /*w is a multiple of 30 and h is a multiple of 32*/
        int ww = w / 30;
        int hh = h / 32;
        int www = ww - 1;
        S[0] = new ListenSquare(x, y, w, h - 5 * hh - 6);              //frame
        S[1] = new ListenSquare(x, y + 19, w, 2 * hh);            //NS10
        S[2] = new ListenSquare(x, y + 5 * hh - 2, w, 5 * hh);          //S10
        S[3] = new ListenSquare(x, y + 10 * hh - 2, w, 2 * hh);          //SS10;
        S[4] = new ListenSquare(x, y + 15 * hh - 4, w, 2 * hh);         //NS21
        S[5] = new ListenSquare(x, y + 17 * hh - 4, w, 5 * hh);   //S21
        S[6] = new ListenSquare(x, y + 22 * hh - 4, w, 2 * hh);        //SS21
        S[7] = new ListenSquare(x, y, w, 19);                 //E0
        S[8] = new ListenSquare(x, y, 4 * ww + 4, 19);              //Q0
        S[9] = new ListenSquare(x, y + 12 * hh - 2, w, 19);          //C1&C2
        S[10] = new ListenSquare(x, y + 24 * hh - 4, w, 19);         //E2&E1
        S[11] = new ListenSquare(x, y + 24 * hh - 4, 4 * ww + 4, 19);      //Q2&Q1
        S[12] = new ListenSquare(x + 40, y + 7 * hh, 5 * www + 2, 19);  //S10A
        S[13] = new ListenSquare(x + 40, y + 17 * hh - 4, 5 * www + 2, 19);  //S12A
        S[16] = new ListenSquare(x + 24 * www - 4, y + 7 * hh, 5 * www + 2, 19);  //S10B
        S[17] = new ListenSquare(x + 24 * www - 4, y + 17 * hh - 4, 5 * www + 2, 19);  //S12B
        S[14] = new ListenSquare(x + 26 * ww - 4, y, 4 * ww + 4, 19);              //HQ0
        S[15] = new ListenSquare(x + 14 * ww - 4, y + 19, 2 * ww + 8, 21 * hh - 2); //R1
        S[18] = new ListenSquare(x + 26 * ww - 4, y + 24 * hh - 4, 4 * ww + 4, 19); //Q1#
        for (int i = 1; i <= 6; ++i)
        {

            for (int j = 0; j <= 6; ++j)
            {
                A[i][j + 1] = new ListenSquare(x + 8 * j, S[i].y, 8, S[i].h);
            }
            for (int j = 7; j <= 7; ++j)
            {
                A[i][j + 1] = new ListenSquare(x + 8 * j, S[i].y, 10, S[i].h);
            }
            for (int j = 8; j <= 15; ++j)
            {
                A[i][j + 1] = new ListenSquare(x + 8 * j + 2, S[i].y, 8, S[i].h);
            }


            for (int j = 15; j <= 21; ++j)
            {
                A[i][j + 1] = new ListenSquare(30 + x + 8 * j - 2, S[i].y, 8, S[i].h);
            }

            for (int j = 22; j <= 22; ++j)
            {
                A[i][j + 1] = new ListenSquare(30 + x + 8 * j - 2, S[i].y, 10, S[i].h);
            }

            for (int j = 23; j <= 29; ++j)
            {
                A[i][j + 1] = new ListenSquare(30 + x + 8 * j, S[i].y, 8, S[i].h);
            }

        }
    }

    Color contrast(Color C)
    {
        int r, g, b;
        r = C.getRed();
        g = C.getGreen();
        b = C.getBlue();
        Color C1 = Color.white;
        if (r + g + b > 400) C1 = Color.black;
        if (g >= 240) C1 = Color.black;
        return (C1);
    }


    Color contrast2(Color C)
    {
        int r, g, b;
        r = C.getRed();
        g = C.getGreen();
        b = C.getBlue();
        Color C1 = new Color(40, 40, 255);
        int test = 0;
        test = test + (40 - r) * (40 - r);
        test = test + (40 - g) * (40 - g);
        test = test + (255 - b) * (255 - b);
        if (test < 100) C1 = new Color(100, 100, 255);
        return (C1);
    }


    void render(Graphics g)
    {


        S[0].render(g, Color.black);
        for (int i = 7; i <= 11; ++i)
        {
            S[i].render3(g, S[i].C, contrast2(S[i].C));
        }

        for (int i = 1; i <= 6; ++i)
        {
            for (int j = 1; j <= 30; ++j)
            {
                A[i][j].render3(g, A[i][j].C, contrast2(A[i][j].C));
            }
        }

        for (int i = 12; i <= 18; ++i) S[i].render3(g, S[i].C, contrast2(S[i].C));
        S[9].render3(g, S[9].C, contrast2(S[9].C));

        int hh = h / 30;
        int ww = w / 32;
        g.setColor(contrast(S[8].C));
        g.drawString("Q0", x + hh, y + 12);
        g.setColor(contrast(S[7].C));
        g.drawString("E0", x + 18 * hh + 3, y + 14);
        g.setColor(contrast(S[14].C));
        g.drawString("HQ0", x + 34 * hh, y + 14);

        g.setColor(contrast(S[9].C));
        if (x < 100) g.drawString("C1", x + 18 * hh + 3, y + 96);
        if (x >= 100) g.drawString("C2", x + 18 * hh + 3, y + 96);
        g.setColor(contrast(S[10].C));
        if (x < 100) g.drawString("E2", x + 18 * hh + 3, y + 23 * ww - 6);
        if (x >= 100) g.drawString("E1", x + 18 * hh + 3, y + 23 * ww - 6);
        g.setColor(contrast(S[11].C));
        if (x < 100) g.drawString("Q2", x + hh, y + 23 * ww - 6);
        if (x >= 100) g.drawString("Q1", x + hh, y + 23 * ww - 6);
        g.setColor(contrast(S[18].C));
        if (x < 100) g.drawString("Q2#", x + 34 * hh, y + 23 * ww - 6);
        if (x >= 100) g.drawString("Q1#", x + 34 * hh, y + 23 * ww - 6);

        g.setColor(contrast(S[15].C));
        if (x < 100) g.drawString("R1", x + 18 * hh + 3, y + 7 * ww);
        if (x < 100) g.drawString("R1", x + 18 * hh + 3, y + 18 * ww - 2);
        if (x > 100) g.drawString("R2", x + 18 * hh + 3, y + 7 * ww);
        if (x > 100) g.drawString("R2", x + 18 * hh + 3, y + 18 * ww - 2);
        g.setColor(contrast(S[12].C));
        if (x < 100) g.drawString("S10A", x + 7 * hh - 4, y + 8 * ww);
        if (x > 100) g.drawString("S20A", x + 7 * hh - 4, y + 8 * ww);
        g.setColor(contrast(S[13].C));
        if (x < 100) g.drawString("S12A", x + 7 * hh - 4, y + 17 * ww - 6);
        if (x > 100) g.drawString("S21A", x + 7 * hh - 4, y + 17 * ww - 6);

        g.setColor(contrast(S[16].C));
        if (x < 100) g.drawString("S10B", x + 27 * hh + 4, y + 8 * ww);
        if (x > 100) g.drawString("S20B", x + 27 * hh + 4, y + 8 * ww);
        g.setColor(contrast(S[17].C));
        if (x < 100) g.drawString("S12B", x + 27 * hh + 4, y + 17 * ww - 6);
        if (x > 100) g.drawString("S21B", x + 27 * hh + 4, y + 17 * ww - 6);

    }

    void setStructure(double s, double p)
    {
        Vector v = new Vector();
        Matrix m = new Matrix();
        P0 = Vector.P0(s, p);
        P1 = Vector.P1(s, p);
        P2 = Vector.P2(s, p);
        C21 = v.C21(s, p);
        C22 = v.C22(s, p);
        C11 = v.C11(s, p);
        C12 = v.C12(s, p);
        M1 = m.FIX(s, p);
        M2 = m.TOR(s, p);
        I0 = Matrix.I0(s, p); 
        I1 = Matrix.I1(s, p);
        I2 = Matrix.I2(s, p);
        this.s = s;

        Z1 = M1.HYP1(M2.b);
        Z2 = M1.HYP1(M2.c);
        Z3 = M2.PLAN1(P0);
        Z4 = M1.PLAN2(P0);

        double sym = getOptimalWeight();
        C11 = Vector.scale(new Complex(sym, 0.0), C11);
        C22 = Vector.scale(new Complex(sym, 0.0), C22);
        Z5 = new Complex();
        Z7 = new Complex();
        Z6 = new Complex();
        Z8 = new Complex();
        Z5.y = getELEV1Scale();
        Z8.x = getHYP2Aspect();
        Z8.y = getELEV2Scale();
        Z7.x = getPLAN2Aspect();
        Z7.y = getPLAN2Scale();
        Z6.y = getPLAN1Scale();
    }

    double getELEV2Scale()
    {
        Matrix m = new Matrix();
        m.b = C11;
        m.a = C12;
        double d = M1.map2(m.C_param(Math.PI / 4));
        d = Math.abs(d);
        return (d);
    }

    double getPLAN2Scale()
    {
        Complex z = M1.PLAN2(C11);
        double d = 0.775 * Math.abs(z.y);
        return (d);
    }

    double getPLAN1Scale()
    {
        Complex z = M2.PLAN1(C11);
        double d = Math.abs(z.y);
        return (d);
    }

    double getHYP2Aspect()
    {
        Matrix m = new Matrix();
        m.b = C12;
        m.a = C11;
        Complex z1 = M1.HYP2(m.C_param(Math.PI / 4.0));
        Complex z2 = M1.HYP2(m.C_param(-Math.PI / 4.0));
        Complex z3 = M1.HYP2(m.C_param(Math.PI / 2.0));
        Complex z4 = M1.HYP2(m.C_param(0.0));
        double d = (z4.x - z3.x) / (z2.y - z1.y);
        d = Math.abs(d);
        return (d);
    }

    double getPLAN2Aspect()
    {
        Matrix m = new Matrix();
        m.b = C11;
        m.a = C12;
        Complex z1 = M1.PLAN2(m.C_param(Math.PI / 4.0));
        Complex z2 = M1.PLAN2(m.C_param(3.0 * Math.PI / 4.0));
        Complex z3 = M1.PLAN2(m.C_param(Math.PI / 2.0));
        Complex z4 = M1.PLAN2(m.C_param(0.0));
        Complex z5 = Complex.subtract(z4, z3);
        Complex z6 = Complex.subtract(z1, z2);
        double d = Complex.norm(z5) / Complex.norm(z6);
        return (d);
    }

    double getELEV1Scale()
    {
        double t1, t2, t3;
        Complex x1, x2, x3;
        double test = 1.0;
        int sign = 0;
        Complex dummy = new Complex();
        Matrix m = new Matrix();
        t1 = 0.0;
        t2 = 4.0;
        t3 = 2.0;
        x2 = new Complex();
        sign = 0;
        while (sign == 0)
        {
            t3 = 0.5 * t1 + 0.5 * t2;
            if (s <= 2)
            {
                m.b = Vector.scale(new Complex(t3, 0.0), P0);
                m.a = C12;
            }
            if (s > 2)
            {
                m.b = Vector.scale(new Complex(t3, 0.0), C11);
                m.a = C12;
            }
            x1 = M2.ELEV1(m.C_param(Math.PI / 4.0 - .00001), dummy);
            x2 = M2.ELEV1(m.C_param(Math.PI / 4.0), dummy);
            x3 = M2.ELEV1(m.C_param(Math.PI / 4.0 + .00001), dummy);
            test = (x1.y - x2.y) * (x2.y - x3.y);
            if (test > 0) sign = 0;
            if (test <= 0) sign = 1;
            if (x1.y < x2.y) t1 = t3;
            if (x1.y > x2.y) t2 = t3;
        }
        return (Math.abs(x2.y));
    }


    double getOptimalWeight()
    {
        double t1, t2, t3;
        double x1, x2, x3;
        double test = 1.0;
        int sign = 0;
        Matrix m = new Matrix();
        t1 = 0.0;
        t2 = 4.0;
        t3 = 2.0;
        x1 = 0.0;
        x2 = 1.0;
        sign = 0;
        while (sign == 0)
        {
            t3 = 0.5 * t1 + 0.5 * t2;
            m.a = Vector.scale(new Complex(t3, 0.0), C11);
            m.b = C12;
            x1 = M1.map2(m.C_param(Math.PI / 4.0 - .00001));
            x2 = M1.map2(m.C_param(Math.PI / 4.0));
            x3 = M1.map2(m.C_param(Math.PI / 4.0 + .00001));
            test = (x1 - x2) * (x2 - x3);
            if (test > 0) sign = 0;
            if (test <= 0) sign = 1;
            if (x1 < x2) t2 = t3;
            if (x1 > x2) t1 = t3;
        }
        return (t3);
    }


    int MESSAGE(Point p)
    {
        int test = 0;
        int MESSAGE = 0;
        for (int i = 1; i <= 18; ++i)
        {
            if (S[i].inside(p) == 1) test = i;
        }
        if (S[9].inside(p) == 1) test = 9;
        if (test == 7) MESSAGE = 1;
        if (test == 8) MESSAGE = 10;
        if (test == 9) MESSAGE = 5;
        if (test == 10) MESSAGE = 9;
        if (test == 11) MESSAGE = 11;
        if (test == 1) MESSAGE = 2;
        if (test == 2) MESSAGE = 3;
        if (test == 3) MESSAGE = 4;
        if (test == 4) MESSAGE = 6;
        if (test == 5) MESSAGE = 7;
        if (test == 6) MESSAGE = 8;
        if (test == 12) MESSAGE = 3;
        if (test == 13) MESSAGE = 7;
        if (test == 14) MESSAGE = 70;
        if (test == 15) MESSAGE = 71;
        if (test == 18) MESSAGE = 72;
        return (MESSAGE);
    }

    void select(Point p, Color COL, int DENS)
    {
        int test = 0;
        for (int i = 1; i <= 18; ++i)
        {
            if (S[i].inside(p) == 1) test = i;
        }
        if (S[9].inside(p) == 1) test = 9;

        if ((test == 14) || (test == 15) || (test == 18) || ((test >= 7) && (test <= 11)))
        {
            S[test].select = 1;
            S[test].C = COL;
            S[test].dens = DENS;
        }

        if (test == 12)
        {
            S[12].select = 1;
            S[12].dens = DENS;
            for (int i = 1; i <= 15; ++i)
            {
                A[2][i].select = 1;
                A[2][i].C = COL;
                A[2][i].dens = DENS;
            }
        }

        if (test == 13)
        {
            S[13].select = 1;
            S[13].dens = DENS;
            for (int i = 1; i <= 15; ++i)
            {
                A[5][i].select = 1;
                A[5][i].C = COL;
                A[5][i].dens = DENS;
            }
        }

        if (test == 16)
        {
            S[12].select = 1;
            S[12].dens = DENS;
            for (int i = 16; i <= 30; ++i)
            {
                A[2][i].select = 1;
                A[2][i].C = COL;
                A[2][i].dens = DENS;
            }
        }

        if (test == 17)
        {
            S[13].select = 1;
            S[13].dens = DENS;
            for (int i = 16; i <= 30; ++i)
            {
                A[5][i].select = 1;
                A[5][i].C = COL;
                A[5][i].dens = DENS;
            }
        }


        int test2 = 0;
        if ((test >= 1) && (test <= 6))
        {
            for (int i = 1; i <= 30; ++i)
            {
                if (A[test][i].inside(p) == 1) test2 = i;
            }
            if (test2 > 0)
            {
                A[test][test2].C = COL;
                A[test][test2].select = 1;
                A[test][test2].dens = DENS;
            }
        }
    }

    void select2(Point p, Color COL, int DENS)
    {
        int test = 0;
        for (int i = 1; i <= 6; ++i)
        {
            if (S[i].inside(p) == 1) test = i;
        }

        int test2 = 0;
        if ((test >= 1) && (test <= 6))
        {
            for (int i = 1; i <= 30; ++i)
            {
                if (A[test][i].inside(p) == 1) test2 = i;
            }
            if (test2 > 0)
            {
                A[test][test2].C = COL;
                A[test][test2].select = 1;
                A[test][test2].dens = DENS;
            }
        }
    }

    void erase(Point p)
    {
        int test = 0;
        int test2 = 0;
        for (int i = 1; i <= 18; ++i)
        {
            if (S[i].inside(p) == 1) test = i;
        }
        if (S[9].inside(p) == 1) test = 9;


        if ((test == 14) || (test == 15) || (test == 18) || ((test >= 7) && (test <= 11)))
        {
            S[test].select = 0;
            S[test].C = Color.black;
        }


        if (test == 12)
        {
            S[12].select = 0;
            for (int i = 1; i <= 15; ++i)
            {
                A[2][i].select = 0;
                A[2][i].C = Color.black;
            }
        }

        if (test == 13)
        {
            S[13].select = 0;
            for (int i = 1; i <= 15; ++i)
            {
                A[5][i].select = 0;
                A[5][i].C = Color.black;
            }
        }

        if (test == 16)
        {
            S[12].select = 0;
            for (int i = 16; i <= 30; ++i)
            {
                A[2][i].select = 0;
                A[2][i].C = Color.black;
            }
        }

        if (test == 17)
        {
            S[13].select = 0;
            for (int i = 16; i <= 30; ++i)
            {
                A[5][i].select = 0;
                A[5][i].C = Color.black;
            }
        }


        if ((test >= 1) && (test <= 6))
        {
            for (int i = 1; i <= 30; ++i)
            {
                if (A[test][i].inside(p) == 1) test2 = i;
            }
            if (test2 > 0)
            {
                A[test][test2].C = Color.black;
                A[test][test2].select = 0;
            }
        }
    }

    void erase2(Point p)
    {
        int test = 0;
        for (int i = 1; i <= 6; ++i)
        {
            if (S[i].inside(p) == 1) test = i;
        }
        int test2 = 0;
        if ((test >= 1) && (test <= 6))
        {
            for (int i = 1; i <= 30; ++i)
            {
                if (A[test][i].inside(p) == 1) test2 = i;
            }
            if (test2 > 0)
            {
                A[test][test2].C = Color.black;
                A[test][test2].select = 0;
            }
        }
    }

    void clear()
    {
        for (int i = 7; i <= 18; ++i)
        {
            S[i].select = 0;
            S[i].C = Color.black;
        }
        for (int j = 1; j <= 6; ++j)
        {
            for (int i = 1; i <= 30; ++i)
            {
                A[j][i].C = Color.black;
                A[j][i].select = 0;
            }
        }
    }

    void showMoney(int d, Color C1, Color C2, Color C3, Color C4)
    {
        S[9].select = 1;
        S[9].dens = d;
        S[9].C = C1;
        S[18].select = 1;
        S[18].dens = d;
        S[18].C = C1;

        for (int i = 1; i <= 30; ++i)
        {
            A[2][i].select = 1;
            A[2][i].dens = d / 2;
            A[2][i].C = C2;
        }


        for (int i = 1; i <= 15; ++i)
        {
            A[5][i].select = 1;
            A[5][i].dens = d / 2;
            A[5][i].C = C3;
        }

        for (int i = 16; i <= 30; ++i)
        {
            A[5][i].select = 1;
            A[5][i].dens = d / 2;
            A[5][i].C = C4;
        }

    }
}
