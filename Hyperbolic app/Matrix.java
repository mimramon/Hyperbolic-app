/*computational kernel*/

class Matrix
{
    Vector a, b, c;

    Matrix()
    {
        this.a = new Vector();
        this.b = new Vector();
        this.c = new Vector();
    }

    Matrix(Vector a, Vector b, Vector c)
    {
        this.a = a;
        this.b = b;
        this.c = c;
    }


    Vector act(Vector v)
    {
        return new Vector(Vector.dot(a, v), Vector.dot(b, v), Vector.dot(c, v));
    }

    static Matrix transpose(Matrix m)
    {
        return new Matrix(new Vector(m.a.a, m.b.a, m.c.a), new Vector(m.a.b, m.b.b, m.c.b), new Vector(m.a.c, m.b.c, m.c.c));
    }

    static Matrix times(Matrix m1, Matrix m2)
    {
        Matrix m2Trans = transpose(m2);
        Vector v1 = new Vector(Vector.dot(m1.a, m2Trans.a), Vector.dot(m1.a, m2Trans.b), Vector.dot(m1.a, m2Trans.c));
        Vector v2 = new Vector(Vector.dot(m1.b, m2Trans.a), Vector.dot(m1.b, m2Trans.b), Vector.dot(m1.b, m2Trans.c));
        Vector v3 = new Vector(Vector.dot(m1.c, m2Trans.a), Vector.dot(m1.c, m2Trans.b), Vector.dot(m1.c, m2Trans.c));

        return new Matrix(v1, v2, v3);
    }

    static Matrix I0()
    {
        Complex zero = new Complex();
        Complex minusOne = new Complex(-1, 0);
        Vector v1 = new Vector(zero, minusOne, zero);
        Vector v2 = new Vector(minusOne, zero, zero);
        Vector v3 = new Vector(zero, zero, minusOne);
        return new Matrix(v1, v2, v3);
    }


    static Matrix I1(double s)
    {
        Complex b = Complex.beta(s);
        Matrix m = new Matrix();
        m.a.a = new Complex(-1, 0);
        m.a.b = new Complex();
        m.a.c = new Complex();
        m.b.a = new Complex();
        m.b.b = new Complex(3, 0);
        m.b.c = new Complex(-4 * b.x, 4 * b.y);
        m.c.a = new Complex();
        m.c.b = new Complex(4 * b.x, 4 * b.y);
        m.c.c = new Complex(-3, 0);
        return m;
    }

    static Matrix I2(double s)
    {
        Complex b = Complex.beta(s);
        Matrix m = new Matrix();
        m.a.a = new Complex(3, 0);
        m.a.b = new Complex();
        m.a.c = new Complex(-4 * b.x, -4 * b.y);
        m.b.a = new Complex();
        m.b.b = new Complex(-1, 0);
        m.b.c = new Complex();
        m.c.a = new Complex(4 * b.x, -4 * b.y);
        m.c.b = new Complex();
        m.c.b = new Complex(-3, 0);
        return m;
    }


    static Matrix J1(double s)
    {
        return Matrix.times(Matrix.times(Matrix.I1(s), Matrix.I0()), Matrix.I2(s));
    }

    static Matrix J2(double s)
    {
        return Matrix.times(Matrix.times(Matrix.I2(s), Matrix.I0()), Matrix.I1(s));
    }

    static Matrix critical_axis()
    {
        Matrix m = new Matrix();
        Complex lambda = new Complex(5.0 * Math.sqrt(5.0) / 16.0, Math.sqrt(3.0) / 16.0);
        Complex mu = new Complex(Math.sqrt(5.0) / 4.0, Math.sqrt(3.0) / 4.0);
        m.b.a = Complex.conjugate(lambda);
        m.b.b = lambda;
        m.b.c = new Complex(1.0, 0.0);
        m.c.a = mu;
        m.c.b = Complex.conjugate(mu);
        m.c.c = new Complex(1.0, 0.0);
        m.a = Vector.hermitian_cross(m.b, m.c);
        m.a = Vector.normalize(m.a);
        return m;
    }


    //More refactoring required
    static Matrix FIX(double s)
    {
        Matrix m = new Matrix();
        m.b = m.b.fix1(s);
        m.c = m.b.fix2(s);

        //adjusting the lifts
        Complex z2 = new Complex(0.0, 1.0);
        Complex z3 = Vector.hermitian_dot(m.b, m.c);
        Complex z4 = Complex.divide(z2, z3);
        m.b = Vector.scale(z4, m.b);
        double d = Math.sqrt(Complex.norm(m.b.c));
        Complex z5 = new Complex(d, 0.0);
        Complex z6 = new Complex(1.0 / d, 0.0);
        m.b = Vector.scale(z6, m.b);
        m.c = Vector.scale(z5, m.c);
        Complex z7 = Complex.inverse(Complex.unit(Complex.square_root(m.b.c)));
        m.b = Vector.scale(z7, m.b);
        m.c = Vector.scale(z7, m.c);
        //

        m.a = Vector.hermitian_cross(m.b, m.c);
        return m;
    }


    Matrix TOR(double s)
    {
        Matrix m = new Matrix();
        Vector v1 = new Vector();
        Vector v2 = new Vector();
        v1 = v1.torus_vector1(s);
        v2 = v2.torus_vector2(s);
        m.a = Vector.hermitian_cross(v1, v2);
        m.b = v2;
        m.c = v1;
        return (m);
    }


    double map1(Vector v)
    {
        Complex[] z = new Complex[5];
        z[1] = Vector.hermitian_dot(v, this.a);
        z[2] = Vector.hermitian_dot(v, this.b);
        z[3] = Vector.hermitian_dot(v, this.c);
        double d = Complex.arg(z[1]) - z[1].guided_arg(z[2], z[3]);
        d = d - Math.PI;
        if (d < 0) d = d + 2.0 * Math.PI;
        if (d < 0) d = d + 2.0 * Math.PI;
        d = d / (2.0 * Math.PI);
        return (d);
    }

    double map2(Vector v)
    {
        Complex[] z = new Complex[6];
        z[2] = Vector.hermitian_dot(v, this.b);
        z[3] = Vector.hermitian_dot(v, this.c);
        z[4] = Complex.divide(z[2], z[3]);
        return Math.log(Complex.norm(z[4]));
    }

    double map3(Vector v)
    {
        Complex aa = a.projection(harmonic2(v));
        Complex bb = a.projection(this.b);
        Complex cc = a.projection(harmonic1(v));
        Complex ee = a.projection(v);
        Complex z1 = Complex.minus(aa, ee);
        Complex z2 = Complex.minus(cc, bb);
        Complex z3 = Complex.minus(bb, aa);
        Complex z4 = Complex.minus(cc, ee);
        Complex z5 = Complex.times(z1, z2);
        Complex z6 = Complex.times(z3, z4);
        Complex z7 = Complex.divide(z5, z6);
        double d2 = z7.y;
        d2 = Math.sqrt(d2);
        return (d2);
    }

    /*these are the main coordinate projections*/

    Complex PLAN1(Vector v)
    {
        Vector w = new Vector();
        Complex z = new Complex();
        w.a = Vector.hermitian_dot(v, this.a);
        w.b = Vector.hermitian_dot(v, this.b);
        w.c = Vector.hermitian_dot(v, this.c);
        double test = Complex.norm(w.c);
        if (test > .00001) z = Complex.divide(w.a, w.c);
        return (z);
    }

    Complex ELEV1(Vector v, Complex u)
    {
        Vector w = new Vector();
        Complex z1 = new Complex(1.0, 0.0);
        Complex z3 = new Complex(1.0, 0.0);
        w.a = Vector.hermitian_dot(v, this.a);
        w.b = Vector.hermitian_dot(v, this.b);
        w.c = Vector.hermitian_dot(v, this.c);
        double test = Complex.norm(w.c);
        if (test > .00001) z1 = Complex.divide(w.a, w.c);
        z1 = Complex.times(u, z1);
        Complex z2 = Complex.divide(w.b, w.c);
        z3.x = Complex.arg(z1) / (Math.PI * 2);
        z3.y = z2.y;
        return (z3);
    }


    Complex HYP1(Vector v)
    {
        return a.projection(v);
    }


    Complex ELEV2(Vector v)
    {
        Complex z = new Complex();
        z.x = map1(v);
        z.y = map2(v);
        return (z);
    }

    Complex HYP2(Vector v)
    {
        Complex z = new Complex();
        z.x = map3(v) * map3(v);
        z.y = map2(v);
        return (z);
    }


    Complex PLAN2(Vector v)
    {
        double d1 = map1(v);
        double x = 2 * Math.PI;
        Complex z = new Complex(Math.cos(x * d1), Math.sin(x * d1));
        double d2 = map3(v);
        z.x = z.x * d2;
        z.y = z.y * d2;
        return (z);
    }

    /*these routines are the basis for the
      loxodromic cone construction */

    Vector harmonic1(Vector v)
    {
        Complex cc = new Complex();
        Complex ss = new Complex();
        double d = map2(v);
        d = Math.exp(d * Complex.norm(Vector.hermitian_dot(b, c)));
        d = 1.0 / d;
        cc.x = 1.0 / Math.sqrt(1.0 + d * d);
        ss.x = d / Math.sqrt(1.0 + d * d);
        cc.y = 0.0;
        ss.y = 0.0;
        Vector w2 = Vector.plus(Vector.scale(ss, b), Vector.scale(cc, c));
        w2 = Vector.normalize(w2);
        return (w2);
    }

    Vector harmonic2(Vector v)
    {
        Complex cc = new Complex();
        Complex ss = new Complex();
        double d = map2(v);
        d = Math.exp(d * Complex.norm(Vector.hermitian_dot(b, c)));
        d = 1.0 / d;
        cc.x = -1.0 / Math.sqrt(1.0 + d * d);
        ss.x = d / Math.sqrt(1.0 + d * d);
        cc.y = 0.0;
        ss.y = 0.0;
        Vector w2 = Vector.plus(Vector.scale(ss, b), Vector.scale(cc, c));
        w2 = Vector.normalize(w2);
        return (w2);
    }

    Matrix doCone(Vector v)
    {
        Matrix m = new Matrix();
        m.a = harmonic2(v);
        m.c = v;
        m.b = harmonic1(v);
        return m.perfect_basis();
    }

    Matrix doLCone(Vector v)
    {
        Matrix m = new Matrix();
        m.b = harmonic2(v);
        m.c = v;
        m.a = harmonic1(v);
        return m.perfect_basis();
    }

    Matrix doRCone(Vector v)
    {
        Matrix m = new Matrix();
        m.c = harmonic2(v);
        m.a = v;
        m.b = harmonic1(v);
        return m.perfect_basis();
    }

    /*these are the routines for parameterizing C-arcs and R-arcs*/

    /*C-arcs*/

    //needs to be refactored
    Vector C_param(double t)
    {
        Complex[] z = new Complex[7];
        z[1] = new Complex(0.0, 1.0);
        z[2] = Complex.unit(Vector.hermitian_dot(a, b));
        z[3] = Complex.divide(z[1], z[2]);
        Vector w = Vector.scale(z[3], a);
        z[4] = new Complex(Math.cos(t), 0.0);
        z[5] = new Complex(Math.sin(t), 0.0);

        return Vector.normalize(Vector.plus(Vector.scale(z[4], w), Vector.scale(z[5], b)));
    }

    /*R-arcs*/
    // needs to be refactored
    Matrix perfect_basis()
    {
        Vector[] w = new Vector[7];
        Complex[] z = new Complex[5];
        Matrix m = new Matrix();
        w[1] = Vector.scale(Vector.hermitian_dot(b, c), a);
        w[2] = Vector.scale(Vector.hermitian_dot(c, a), b);
        w[3] = Vector.scale(Vector.hermitian_dot(a, b), c);
        z[1] = Vector.hermitian_dot(w[1], w[2]);
        z[1] = Complex.unit(z[1]);
        z[1] = Complex.inverse(z[1]);
        z[2] = Vector.hermitian_dot(w[2], w[3]);
        z[2] = Complex.unit(z[2]);
        z[3] = new Complex(-1.0, 0.0);
        m.a = Vector.scale(z[1], w[1]);
        m.c = Vector.scale(z[2], w[3]);
        m.b = Vector.scale(z[3], w[2]);
        return (m);
    }

    /*this routine supposes that the vectors lie in an R-circle*/

    //needs refactoring
    Vector R_param(double t)
    {
        Complex[] z = new Complex[5];
        Vector[] w = new Vector[6];
        Matrix m = this.perfect_basis();
        w[1] = new Vector();
        z[1] = new Complex(t * (t - 1.0), 0);
        z[2] = new Complex(t, 0.0);
        z[3] = new Complex(1.0 - t, 0.0);
        w[1] = Vector.scale(z[1], m.a);
        w[2] = Vector.scale(z[2], m.b);
        w[3] = Vector.scale(z[3], m.c);
        w[4] = Vector.plus(Vector.plus(w[1], w[2]), w[3]);
        return Vector.normalize(w[4]);
    }
}
