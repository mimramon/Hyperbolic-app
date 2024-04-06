class Vector
{
    Complex a, b, c;

    Vector()
    {
        this.a = new Complex(0.0, 0.0);
        this.b = new Complex(0.0, 0.0);
        this.c = new Complex(0.0, 0.0);
    }

    void print()
    {
        this.a.print();
        this.b.print();
        this.c.print();
    }


    Vector plus(Vector v, Vector w)
    {
        Vector x = new Vector();
        x.a = Complex.add(v.a, w.a);
        x.b = Complex.add(v.b, w.b);
        x.c = Complex.add(v.c, w.c);
        return x;
    }

    Vector minus(Vector v, Vector w)
    {
        Vector x = new Vector();
        x.a = Complex.subtract(v.a, w.a);
        x.b = Complex.subtract(v.b, w.b);
        x.c = Complex.subtract(v.c, w.c);
        return x;
    }

    Vector scale(Complex a, Vector v)
    {
        Vector w = new Vector();
        w.a = Complex.multiply(a, v.a);
        w.b = Complex.multiply(a, v.b);
        w.c = Complex.multiply(a, v.c);
        return w;
    }

    Complex dot(Vector v, Vector w)
    {
        return Complex.add(Complex.add(Complex.multiply(v.a, w.a), Complex.multiply(v.b, w.b)), Complex.multiply(v.c, w.c));
    }


    double norm(Vector x)
    {
        return (x.a.x * x.a.x + x.b.x * x.b.x + x.c.x * x.c.x);
    }

    double dist(Vector v, Vector w)
    {
        Vector vv = v.normalize(v);
        Vector ww = w.normalize(w);
        return (vv.norm(vv.minus(vv, ww)));
    }


    Complex hermitian_dot(Vector v, Vector w)
    {
        Complex c1 = Complex.multiply(v.a, Complex.conjugate(w.a));
        Complex c2 = Complex.multiply(v.b, Complex.conjugate(w.b));
        Complex c3 = Complex.multiply(v.c, Complex.conjugate(w.c));
        return Complex.subtract(Complex.add(c1, c2), c3);
    }


    Complex angular_invariant(Vector v1, Vector v2, Vector v3)
    {
        Complex z1 = hermitian_dot(v1, v2);
        Complex z2 = hermitian_dot(v2, v3);
        Complex z3 = hermitian_dot(v3, v1);
        return Complex.multiply(z1, Complex.multiply(z2, z3));
    }


    Vector hermitian_cross(Vector v, Vector w)
    {
        Vector x = new Vector();
        x.a = Complex.subtract(Complex.multiply(v.b, w.c), Complex.multiply(v.c, w.b));
        x.b = Complex.subtract(Complex.multiply(v.c, w.a), Complex.multiply(v.a, w.c));
        x.c = Complex.subtract(Complex.multiply(v.b, w.a), Complex.multiply(v.a, w.b));
        x.a = Complex.conjugate(x.a);
        x.b = Complex.conjugate(x.b);
        x.c = Complex.conjugate(x.c);
        return x;
    }


    Vector normalize(Vector v)
    {
        Vector x = new Vector();
        x.a = Complex.divide(v.a, v.c);
        x.b = Complex.divide(v.b, v.c);
        x.c = Complex.divide(v.c, v.c);
        return x;
    }


    Complex projection(Vector v)
    {
        Vector w = new Vector();
        Complex[] z = new Complex[5];
        z[1] = hermitian_dot(v, this);
        z[2] = hermitian_dot(this, this);
        z[3] = Complex.divide(z[1], z[2]);
        z[3].x = -z[3].x;
        z[3].y = -z[3].y;
        w = w.plus(v, w.scale(z[3], this));
        z[4] = Complex.divide(w.a, w.c);
        return z[4];
    }

    Vector P0(double s)
    {
        Vector v = new Vector();
        v.a = Complex.beta(s);
        v.b = Complex.conjugate(Complex.beta(s));
        v.c.x = 1;
        v.c.y = 0;
        return v;
    }


    Vector P1(double s)
    {
        Vector v = new Vector();
        v.a = Complex.beta(s);
        v.b = Complex.beta(s);
        v.c.x = 1;
        v.c.y = 0;
        return v;
    }


    Vector P2(double s)
    {
        Vector v = new Vector();
        v.a = Complex.conjugate(Complex.beta(s));
        v.b = Complex.conjugate(Complex.beta(s));
        v.c.x = 1;
        v.c.y = 0;
        return v;
    }


    Vector C21(double s)
    {
        Vector[] v = new Vector[10];
        double[] d = new double[10];
        Complex[] z = new Complex[10];

        v[0] = P0(s);
        v[1] = P1(s);
        v[2] = P2(s);

        z[0] = v[1].hermitian_dot(v[0], v[2]);
        z[1] = v[1].hermitian_dot(v[1], v[2]);
        v[0] = v[0].scale(Complex.inverse(z[0]), v[0]);
        v[1] = v[1].scale(Complex.inverse(z[1]), v[1]);
        z[2] = hermitian_dot(v[0], v[1]);
        z[2] = Complex.unit(z[2]);
        z[3] = Complex.multiply(z[2], new Complex(0, 1));
        v[1] = v[1].scale(z[3], v[1]);
        v[2] = v[1].plus(v[0], v[1]);
        return (v[2]);
    }

    Vector C22(double s)
    {
        Vector[] v = new Vector[10];
        double[] d = new double[10];
        Complex[] z = new Complex[10];

        v[0] = P0(s);
        v[1] = P1(s);
        v[2] = P2(s);

        z[0] = v[1].hermitian_dot(v[0], v[2]);
        z[1] = v[1].hermitian_dot(v[1], v[2]);
        v[0] = v[0].scale(Complex.inverse(z[0]), v[0]);
        v[1] = v[1].scale(Complex.inverse(z[1]), v[1]);
        z[2] = hermitian_dot(v[0], v[1]);
        z[2] = Complex.unit(z[2]);
        z[3] = Complex.multiply(z[2], new Complex(0, 1));
        v[1] = v[1].scale(z[3], v[1]);
        v[2] = v[1].minus(v[0], v[1]);
        return (v[2]);
    }


    Vector C11(double s)
    {
        Vector[] v = new Vector[10];
        double[] d = new double[10];
        Complex[] z = new Complex[10];

        v[0] = P0(s);
        v[1] = P1(s);
        v[2] = P2(s);

        z[0] = v[1].hermitian_dot(v[0], v[1]);
        z[2] = v[1].hermitian_dot(v[2], v[1]);
        v[0] = v[0].scale(Complex.inverse(z[0]), v[0]);
        v[2] = v[2].scale(Complex.inverse(z[2]), v[2]);
        z[1] = hermitian_dot(v[0], v[2]);
        z[1] = Complex.unit(z[1]);
        z[3] = Complex.multiply(z[1], new Complex(0, 1));
        v[2] = v[2].scale(z[3], v[2]);
        v[1] = v[2].plus(v[0], v[2]);
        return (v[1]);
    }


    Vector C12(double s)
    {
        Vector[] v = new Vector[10];
        double[] d = new double[10];
        Complex[] z = new Complex[10];

        v[0] = P0(s);
        v[1] = P1(s);
        v[2] = P2(s);

        z[0] = v[1].hermitian_dot(v[0], v[1]);
        z[2] = v[1].hermitian_dot(v[2], v[1]);
        v[0] = v[0].scale(Complex.inverse(z[0]), v[0]);
        v[2] = v[2].scale(Complex.inverse(z[2]), v[2]);
        z[1] = hermitian_dot(v[0], v[2]);
        z[1] = Complex.unit(z[1]);
        z[3] = Complex.multiply(z[1], new Complex(0.0, 1.0));
        v[2] = v[2].scale(z[3], v[2]);
        v[1] = v[2].minus(v[0], v[2]);
        return v[1];
    }


    Vector fix1(double s)
    {
        Matrix m = new Matrix();
        Matrix mm = new Matrix();
        double test = 0;
        Vector v1 = new Vector();
        Vector v2 = new Vector();
        int i = 0;
        int count = 0;
        m = m.J1(s);
        test = 1.0;
        mm = mm.critical_axis();
        v1 = v1.normalize(mm.b);
        while (test > 0.000000000001)
        {
            v2 = v2.normalize(m.act(v1));
            test = v1.dist(v1, v2);
            v1 = v2;
        }
        return (v1);
    }

    Vector fix2(double s)
    {
        Matrix m = new Matrix();
        Matrix mm = new Matrix();
        double test = 0;
        Vector v1 = new Vector();
        Vector v2 = new Vector();
        int i = 0;
        int count = 0;
        m = m.J2(s);
        test = 1.0;
        mm = mm.critical_axis();
        v1 = v1.normalize(mm.b);
        while (test > 0.000000000001)
        {
            v2 = v2.normalize(m.act(v1));
            test = v1.dist(v1, v2);
            v1 = v2;
        }
        return (v1);
    }

    Vector torus_vector1(double s)
    {
        Matrix m = new Matrix();
        m = m.FIX(s);
        Complex[] z = new Complex[5];
        m.a = m.a.normalize(m.a);
        for (int i = 1; i <= 4; ++i) z[i] = new Complex();
        z[1].x = 1.0;
        z[1].y = Math.sqrt(2.0 * Complex.norm(m.a.a) * Complex.norm(m.a.a) - 1.0);
        z[2].x = 2.0 * m.a.b.x;
        z[2].y = 2.0 * m.a.b.y;
        z[3] = Complex.divide(z[1], z[2]);
        z[4] = Complex.conjugate(z[3]);
        m.a.a = z[3];
        m.a.b = z[4];
        return (m.a);
    }

    Vector torus_vector2(double s)
    {
        Matrix m = new Matrix();
        m = m.FIX(s);
        Complex[] z = new Complex[5];
        m.a = m.a.normalize(m.a);
        for (int i = 1; i <= 4; ++i) z[i] = new Complex();
        z[1].x = 1.0;
        z[1].y = -Math.sqrt(2.0 * Complex.norm(m.a.a) * Complex.norm(m.a.a) - 1.0);
        z[2].x = 2.0 * m.a.b.x;
        z[2].y = 2.0 * m.a.b.y;
        z[3] = Complex.divide(z[1], z[2]);
        z[4] = Complex.conjugate(z[3]);
        m.a.a = z[3];
        m.a.b = z[4];
        return (m.a);
    }


    Vector spinalExtension(Vector v, Vector w, Vector X)
    {
        Vector[] x = new Vector[10];
        Complex[] z = new Complex[10];
        x[1] = X;
        z[1] = X.hermitian_dot(x[1], v);
        z[2] = new Complex(0.0, -1.0);
        z[3] = Complex.multiply(z[1], z[2]);
        x[2] = X.scale(Complex.inverse(z[3]), x[1]);
        z[7] = X.hermitian_dot(w, v);
        x[4] = X.scale(Complex.inverse(z[7]), w);
        z[5] = X.hermitian_dot(x[4], x[2]);
        z[6] = new Complex(-z[5].x, 0.0);
        x[5] = X.plus(x[2], X.scale(z[6], v));
        return (x[5]);
    }

}