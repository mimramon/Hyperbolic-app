/*
This class has been refactored
*/

import java.awt.*;

class Complex
{
    double x, y;

    Complex()
    {
        this.x = 0;
        this.y = 0;
    }

    Complex(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    static double arg(Complex z)
    {
        double d = Math.atan2(z.y, z.x);
        if (z.y < 0) d += 2 * Math.PI;
        return (d);
    }


    double guided_arg(Complex z1, Complex z2)
    {
        double d1, d2, d3;
        d1 = Complex.arg(z1);
        d2 = Complex.arg(z2);
        d3 = 0;
        if (Math.abs(d1 - d2) < Math.PI) d3 = d1 / 2 + d2 / 2;
        if (Math.abs(d1 - d2) >= Math.PI) d3 = Math.PI + d1 / 2 + d2 / 2;
        return (d3);
    }

    static double norm(Complex z)
    {
        return Math.sqrt(z.x * z.x + z.y * z.y);
    }

    static Complex square_root(Complex z)
    {
        double r = Complex.norm(z);
        r = Math.sqrt(r);
        double theta = Complex.arg(z);

        return new Complex(r * Math.cos(theta / 2), r * Math.sin(theta / 2));
    }

    static Complex unit(Complex z)
    {
        double d = Complex.norm(z);
        return new Complex(z.x / d, z.y / d);
    }

    static Complex plus(Complex z1, Complex z2)
    {
        return new Complex(z1.x + z2.x, z1.y + z2.y);
    }

    static Complex minus(Complex z1, Complex z2)
    {
        return new Complex(z1.x - z2.x, z1.y - z2.y);
    }

    static Complex times(Complex z1, Complex z2)
    {
        return new Complex(z1.x * z2.x - z1.y * z2.y, z1.x * z2.y + z1.y * z2.x);
    }

    static Complex inverse(Complex z)
    {
        Complex w = new Complex();
        double d = z.x * z.x + z.y * z.y;
        w.x = z.x / d;
        w.y = -z.y / d;

        return w;
    }

    static Complex divide(Complex z1, Complex z2)
    {
        return Complex.times(z1, Complex.inverse(z2));
    }

    static Complex conjugate(Complex z)
    {
        return new Complex(z.x, -z.y);
    }

    static Complex beta(double s)
    {
        return new Complex(s / Math.sqrt(2 + 2 * s * s), 1 / Math.sqrt(2 + 2 * s * s));
    }


    //the following three methods need to be analysed
    void print()
    {
        System.out.println(x + "    " + y + " I");
    }


    void doubleRender(double d, Graphics g, int x, int y)
    {
        double[] e = new double[7];
        int[] n = new int[7];
        e[0] = d;
        n[0] = (int) (e[0]);
        g.drawString(Integer.toString(n[0]), x + 10, y);
        g.fillOval(x + 18, y - 2, 2, 2);
        for (int i = 1; i <= 5; i++)
        {
            e[i] = 10.0 * (e[i - 1] - n[i - 1]);
            n[i] = (int) (e[i]);
            g.drawString(Integer.toString(n[i]), x + 15 + 8 * i, y);
        }
    }

    void doubleRender2(double d, Graphics g, int x, int y)
    {
        double[] e = new double[7];
        int[] n = new int[7];
        e[0] = d;
        n[0] = (int) (e[0]);
        g.drawString(Integer.toString(n[0]), x + 10, y);
        g.fillOval(x + 18, y - 2, 2, 2);
        for (int i = 1; i <= 3; i++)
        {
            e[i] = 10.0 * (e[i - 1] - n[i - 1]);
            n[i] = (int) (e[i]);
            g.drawString(Integer.toString(n[i]), x + 15 + 8 * i, y);
        }
    }
}