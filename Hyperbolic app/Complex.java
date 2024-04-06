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

    static double arg(Complex z)
    {
        double d = Math.atan2(z.y, z.x);
        if (z.y < 0)
        {
            d += 2 * Math.PI;
        }
        return d;
    }


    static double guidedArg(Complex z, Complex w)
    {
        double guidedArg = (Complex.arg(z) / 2.0) + (Complex.arg(w) / 2.0);
        if (Math.abs(Complex.arg(z) - Complex.arg(w)) >= Math.PI)
        {
            guidedArg += Math.PI;
        }
        return guidedArg;
    }

    static double norm(Complex z)
    {
        return Math.sqrt(z.x * z.x + z.y * z.y);
    }

    static Complex squareRoot(Complex z)
    {
        double r = Math.sqrt(Complex.norm(z));
        double theta = Complex.arg(z);
        return new Complex(r * Math.cos(theta / 2), r * Math.sin(theta / 2));
    }

    static Complex unit(Complex z)
    {
        return new Complex(z.x / Complex.norm(z), z.y / Complex.norm(z));
    }

    static Complex add(Complex z, Complex w)
    {
        return new Complex(z.x + w.x, z.y + w.y);
    }

    static Complex subtract(Complex z, Complex w)
    {
        return new Complex(z.x - w.x, z.y - w.y);
    }

    static Complex multiply(Complex z, Complex w)
    {
        return new Complex((z.x * w.x) - (z.y * w.y), (z.x * w.y) + (z.y * w.x));
    }

    static Complex inverse(Complex z)
    {
        double d = z.x * z.x + z.y * z.y;
        return new Complex(z.x / d, -z.y / d);
    }

    static Complex divide(Complex z, Complex w)
    {
        return Complex.multiply(z, Complex.inverse(w));
    }

    static Complex conjugate(Complex z)
    {
        return new Complex(z.x, -z.y);
    }

    static Complex beta(double s)
    {
        return new Complex(s / Math.sqrt(2 + 2 * s * s), 1 / Math.sqrt(2 + 2 * s * s));
    }

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