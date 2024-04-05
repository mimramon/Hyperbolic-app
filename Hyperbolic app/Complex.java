import java.awt.*;

class Complex
{
    double x, y;

    Complex()
    {
        x = 0;
        y = 0;
    }

    Complex(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    static Complex convert(double x, double y)
    {
        return new Complex(x, y);
    }

    static double arg(Complex z)
    {
        double d = Math.atan2(z.y, z.x);
        if (z.y < 0.0)
        {
            d = d + 2.0 * Math.PI;
        }
        return d;
    }


    static double guided_arg(Complex z1, Complex z2)
    {
        double d1, d2, d3;
        d1 = Complex.arg(z1);
        d2 = Complex.arg(z2);
        d3 = 0.0;
        if (Math.abs(d1 - d2) < Math.PI)
        {
            d3 = (d1 / 2.0) + (d2 / 2.0);
        }
        if (Math.abs(d1 - d2) >= Math.PI)
        {
            d3 = Math.PI + d1 / 2.0 + d2 / 2.0;
        }
        return (d3);
    }

    double norm(Complex z)
    {
        double d = Math.sqrt(z.x * z.x + z.y * z.y);
        return (d);
    }

    Complex square_root(Complex z)
    {
        Complex w = new Complex();
        double r = z.norm(z);
        r = Math.sqrt(r);
        double theta = z.arg(z);
        w = w.convert(r * Math.cos(theta / 2.0), r * Math.sin(theta / 2.0));
        return (w);
    }

    Complex unit(Complex z)
    {
        Complex w = new Complex();
        double d;
        d = z.norm(z);
        w.x = z.x / d;
        w.y = z.y / d;
        return (w);
    }

    Complex plus(Complex z1, Complex z2)
    {
        Complex w = new Complex();
        w.x = z1.x + z2.x;
        w.y = z1.y + z2.y;
        return (w);
    }

    Complex minus(Complex z1, Complex z2)
    {
        Complex w = new Complex();
        w.x = z1.x - z2.x;
        w.y = z1.y - z2.y;
        return (w);
    }

    Complex times(Complex z1, Complex z2)
    {
        Complex w = new Complex();
        w.x = z1.x * z2.x - z1.y * z2.y;
        w.y = z1.x * z2.y + z1.y * z2.x;
        return (w);
    }

    Complex inverse(Complex z)
    {
        Complex w = new Complex();
        double d;
        d = z.x * z.x + z.y * z.y;
        w.x = z.x / d;
        w.y = -z.y / d;
        return (w);
    }

    Complex divide(Complex z1, Complex z2)
    {
        Complex w = new Complex();
        w = w.times(z1, w.inverse(z2));
        return (w);
    }

    Complex conjugate(Complex z)
    {
        Complex w = new Complex();
        w.x = z.x;
        w.y = -z.y;
        return (w);
    }

    Complex beta(double s)
    {
        Complex z = new Complex();
        z.x = s / Math.sqrt(2.0 + 2.0 * s * s);
        z.y = 1.0 / Math.sqrt(2.0 + 2.0 * s * s);
        return (z);
    }

    void print()
    {
        System.out.println(x + "    " + y + " I");
    }


    void doubleRender(double d, Graphics g, int x, int y)
    {
        double[] e = new double[7];
        int[] n = new int[7];
        Integer I = new Integer(0);
        e[0] = d;
        n[0] = (int) (e[0]);
        g.drawString(I.toString(n[0]), x + 10, y);
        g.fillOval(x + 18, y - 2, 2, 2);
        for (int i = 1; i <= 5; ++i)
        {
            e[i] = 10.0 * (e[i - 1] - n[i - 1]);
            n[i] = (int) (e[i]);
            g.drawString(I.toString(n[i]), x + 15 + 8 * i, y);
        }
    }

    void doubleRender2(double d, Graphics g, int x, int y)
    {
        double[] e = new double[7];
        int[] n = new int[7];
        Integer I = new Integer(0);
        e[0] = d;
        n[0] = (int) (e[0]);
        g.drawString(I.toString(n[0]), x + 10, y);
        g.fillOval(x + 18, y - 2, 2, 2);
        for (int i = 1; i <= 3; ++i)
        {
            e[i] = 10.0 * (e[i - 1] - n[i - 1]);
            n[i] = (int) (e[i]);
            g.drawString(I.toString(n[i]), x + 15 + 8 * i, y);
        }
    }


}