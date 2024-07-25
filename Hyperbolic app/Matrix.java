/*computational kernel*/

class Matrix
{
    Vector a, b, c;

    Matrix()
    {
        a = new Vector();
        b = new Vector();
        c = new Vector();
    }

    Matrix(Vector _a, Vector _b, Vector _c)
    {
        a = _a;
        b = _b;
        c = _c;
    }

    //investigate further
    Vector act(Vector v)
    {
        Vector w = new Vector();
        w.a = Vector.dot(a, v);
        w.b = Vector.dot(b, v);
        w.c = Vector.dot(c, v);
        return (w);
    }

    //investigate further
    static Matrix transpose(Matrix m)
    {
        return new Matrix(
                new Vector(m.a.a, m.b.a, m.c.a),
                new Vector(m.a.b, m.b.b, m.c.b),
                new Vector(m.a.c, m.b.c, m.c.c)
        );
    }


    static Matrix times(Matrix m, Matrix n)
    {
        Matrix newMat = Matrix.transpose(n);
        return new Matrix(
                new Vector(Vector.dot(m.a, newMat.a), Vector.dot(m.a, newMat.b), Vector.dot(m.a, newMat.c)),
                new Vector(Vector.dot(m.b, newMat.a), Vector.dot(m.b, newMat.b), Vector.dot(m.b, newMat.c)),
                new Vector(Vector.dot(m.c, newMat.a), Vector.dot(m.c, newMat.b), Vector.dot(m.c, newMat.c))
        );
    }
    
    static Complex determinant(Matrix m)
    {
    	Complex firstTerm = 
    			Complex.multiply(m.a.a, Complex.subtract(Complex.multiply(m.b.b, m.c.c), Complex.multiply(m.b.c, m.c.b)));
    	Complex secondTerm = 
    			Complex.multiply(m.b.a, Complex.subtract(Complex.multiply(m.a.b, m.c.c), Complex.multiply(m.a.c, m.c.b)));
    	Complex thirdTerm =
    			Complex.multiply(m.c.a, Complex.subtract(Complex.multiply(m.a.b, m.b.c), Complex.multiply(m.b.b, m.a.c)));
    	return Complex.add(Complex.subtract(firstTerm, secondTerm), thirdTerm);
    }
    
    static Matrix scale(Complex z, Matrix m )
    {
    	return new Matrix(Vector.scale(z, m.a), Vector.scale(z, m.b), Vector.scale(z, m.c));
    }
    
    static Matrix normaliseDeterminant(Matrix m)
    {
    	Complex det = Matrix.determinant(m);
    	return Matrix.scale(Complex.divide(new Complex(1, 0), Complex.multiply(det, Complex.multiply(det, det))), m);
    }

    static Matrix I0(double s)
    {

        return new Matrix(
                new Vector(new Complex(0, 0), new Complex(-1, 0), new Complex(0, 0)),
                new Vector(new Complex(-1, 0), new Complex(0, 0), new Complex(0, 0)),
                new Vector(new Complex(0, 0), new Complex(0, 0), new Complex(-1, 0))
        );
    }


    static Matrix I1(double s, double p)
    {
        Complex b = Complex.beta(s, p);
        return new Matrix(
                new Vector(new Complex(-1, 0), new Complex(0, 0), new Complex(0, 0)),
                new Vector(new Complex(0, 0), new Complex(3, 0), new Complex(-4 * b.x, 4 * b.y)),
                new Vector(new Complex(0, 0), new Complex(4 * b.x, 4 * b.y), new Complex(-3, 0))
        );
    }


    static Matrix I2(double s, double p)
    {
        Complex b = Complex.beta(s, p);
        return new Matrix(
                new Vector(new Complex(3, 0), new Complex(0, 0), new Complex(-4 * b.x, -4 * b.y)),
                new Vector(new Complex(0, 0), new Complex(-1, 0), new Complex(0, 0)),
                new Vector(new Complex(4 * b.x, -4 * b.y), new Complex(0, 0), new Complex(-3, 0))
        );
    }


    Matrix J1(double s, double p)
    {
        return Matrix.times(Matrix.times(Matrix.I1(s, p), Matrix.I0(s)), Matrix.I2(s, p));
    }

    Matrix J2(double s, double p)
    {
        return Matrix.times(Matrix.times(Matrix.I2(s, p), Matrix.I0(s)), Matrix.I1(s, p));
    }


    Matrix critical_axis()
    {
        Matrix m = new Matrix();
        Complex lambda = new Complex();
        Complex mu = new Complex();
        lambda.x = 5.0 * Math.sqrt(5.0) / 16.0;
        lambda.y = Math.sqrt(3.0) / 16.0;
        mu.x = Math.sqrt(5.0) / 4.0;
        mu.y = Math.sqrt(3.0) / 4.0;
        m.b.a = Complex.conjugate(lambda);
        m.b.b = lambda;
        m.b.c = new Complex(1.0, 0.0);
        m.c.a = mu;
        m.c.b = Complex.conjugate(mu);
        m.c.c = new Complex(1.0, 0.0);
        m.a = Vector.hermitianCross(m.b, m.c);
        m.a = Vector.normalize(m.a);
        return (m);
    }


    Matrix FIX(double s, double p)
    {
        Matrix m = new Matrix();
        Complex[] z = new Complex[10];
        double[] d = new double[5];
        Vector v = new Vector();
        m.b = m.b.fix1(s, p);
        m.c = m.b.fix2(s, p);
        /********adjusting the lifts**********/
        z[1] = new Complex(0.0, 1.0);
        z[2] = Vector.hermitianDot(m.b, m.c);
        z[3] = Complex.divide(z[1], z[2]);
        m.b = Vector.scale(z[3], m.b);
        d[1] = Math.sqrt(Complex.norm(m.b.c));
        z[4] = new Complex(d[1], 0.0);
        z[5] = new Complex(1.0 / d[1], 0.0);
        m.b = Vector.scale(z[5], m.b);
        m.c = Vector.scale(z[4], m.c);
        z[6] = Complex.inverse(Complex.unit(Complex.squareRoot(m.b.c)));
        m.b = Vector.scale(z[6], m.b);
        m.c = Vector.scale(z[6], m.c);
        /**************************************/
        m.a = Vector.hermitianCross(m.b, m.c);
        return (m);
    }


    Matrix TOR(double s, double p)
    {
        Matrix m = new Matrix();
        Vector v1 = new Vector();
        Vector v2 = new Vector();
        v1 = v1.torus_vector1(s, p);
        v2 = v2.torus_vector2(s, p);
        m.a = Vector.hermitianCross(v1, v2);
        m.b = v2;
        m.c = v1;
        return (m);
    }


    double map1(Vector v)
    {
        Complex[] z = new Complex[5];
        double d = 0.0;
        z[1] = Vector.hermitianDot(v, this.a);
        z[2] = Vector.hermitianDot(v, this.b);
        z[3] = Vector.hermitianDot(v, this.c);
        d = Complex.arg(z[1]) - Complex.guidedArg(z[2], z[3]);
        d = d - Math.PI;
        if (d < 0) d = d + 2.0 * Math.PI;
        if (d < 0) d = d + 2.0 * Math.PI;
        d = d / (2.0 * Math.PI);
        return (d);
    }

    double map2(Vector v)
    {
        Complex[] z = new Complex[6];
        double d = 0.0;
        double dd = 0.0;
        z[2] = Vector.hermitianDot(v, this.b);
        z[3] = Vector.hermitianDot(v, this.c);
        z[4] = Complex.divide(z[2], z[3]);
        d = Math.log(Complex.norm(z[4]));
        return d;
    }

    double map3(Vector v)
    {
        Complex aa = a.projection(harmonic2(v));
        Complex bb = a.projection(this.b);
        Complex cc = a.projection(harmonic1(v));
        Complex ee = a.projection(v);
        Complex z1 = Complex.subtract(aa, ee);
        Complex z2 = Complex.subtract(cc, bb);
        Complex z3 = Complex.subtract(bb, aa);
        Complex z4 = Complex.subtract(cc, ee);
        Complex z5 = Complex.multiply(z1, z2);
        Complex z6 = Complex.multiply(z3, z4);
        Complex z7 = Complex.divide(z5, z6);
        double d2 = z7.y;
        d2 = Math.sqrt(d2);
        return d2;
    }

    /*these are the main coordinate projections*/

    Complex PLAN1(Vector v)
    {
        Vector w = new Vector();
        double test = 0.0;
        Complex z = new Complex();
        w.a = Vector.hermitianDot(v, this.a);
        w.b = Vector.hermitianDot(v, this.b);
        w.c = Vector.hermitianDot(v, this.c);
        test = Complex.norm(w.c);
        if (test > .00001) z = Complex.divide(w.a, w.c);
        return z;
    }

    Complex ELEV1(Vector v, Complex u)
    {
        Vector w = new Vector();
        double test = 0.0;
        Complex z1 = new Complex(1.0, 0.0);
        Complex z2 = new Complex(1.0, 0.0);
        Complex z3 = new Complex(1.0, 0.0);
        w.a = Vector.hermitianDot(v, this.a);
        w.b = Vector.hermitianDot(v, this.b);
        w.c = Vector.hermitianDot(v, this.c);
        test = Complex.norm(w.c);
        if (test > .00001) z1 = Complex.divide(w.a, w.c);
        z1 = Complex.multiply(u, z1);
        z2 = Complex.divide(w.b, w.c);
        z3.x = Complex.arg(z1) / (Math.PI * 2);
        z3.y = z2.y;
        return (z3);
    }


    Complex HYP1(Vector v)
    {
        Complex z = a.projection(v);
        return (z);
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
        double d = 0.0;
        Complex cc = new Complex();
        Complex ss = new Complex();
        Vector w2 = new Vector();
        d = map2(v);
        d = Math.exp(d * Complex.norm(Vector.hermitianDot(b, c)));
        d = 1.0 / d;
        cc.x = 1.0 / Math.sqrt(1.0 + d * d);
        ss.x = d / Math.sqrt(1.0 + d * d);
        cc.y = 0.0;
        ss.y = 0.0;
        w2 = Vector.add(Vector.scale(ss, b), Vector.scale(cc, c));
        w2 = Vector.normalize(w2);
        return w2;
    }

    Vector harmonic2(Vector v)
    {
        double d = 0.0;
        Complex cc = new Complex();
        Complex ss = new Complex();
        Vector w2 = new Vector();
        d = map2(v);
        d = Math.exp(d * Complex.norm(Vector.hermitianDot(b, c)));
        d = 1.0 / d;
        cc.x = -1.0 / Math.sqrt(1.0 + d * d);
        ss.x = d / Math.sqrt(1.0 + d * d);
        cc.y = 0.0;
        ss.y = 0.0;
        w2 = Vector.add(Vector.scale(ss, b), Vector.scale(cc, c));
        w2 = Vector.normalize(w2);
        return w2;
    }

    Matrix doCone(Vector v)
    {
        Matrix m = new Matrix();
        Matrix m2 = new Matrix();
        m.a = harmonic2(v);
        m.c = v;
        m.b = harmonic1(v);
        m2 = m.perfect_basis();
        return m2;
    }

    Matrix doLCone(Vector v)
    {
        Matrix m = new Matrix();
        Matrix m2 = new Matrix();
        m.b = harmonic2(v);
        m.c = v;
        m.a = harmonic1(v);
        m2 = m.perfect_basis();
        return (m2);
    }

    Matrix doRCone(Vector v)
    {
        Matrix m = new Matrix();
        Matrix m2 = new Matrix();
        m.c = harmonic2(v);
        m.a = v;
        m.b = harmonic1(v);
        m2 = m.perfect_basis();
        return (m2);
    }

    /*these are the routines for parameterizing C-arcs and R-arcs*/

    /*C-arcs*/
    Vector C_param(double t)
    {
        Vector p = new Vector();
        Vector w = new Vector();
        Complex[] z = new Complex[7];
        z[1] = new Complex();
        z[1] = new Complex(0.0, 1.0);
        z[2] = Vector.hermitianDot(a, b);
        z[2] = Complex.unit(z[2]);
        z[3] = Complex.divide(z[1], z[2]);
        w = Vector.scale(z[3], a);
        z[4] = new Complex(Math.cos(t), 0.0);
        z[5] = new Complex(Math.sin(t), 0.0);
        p = Vector.add(Vector.scale(z[4], w), Vector.scale(z[5], b));
        p = Vector.normalize(p);
        return (p);
    }

    /*R-arcs*/

    Matrix perfect_basis()
    {
        Vector[] w = new Vector[7];
        Complex[] z = new Complex[5];
        Matrix m = new Matrix();
        w[1] = new Vector();
        w[1] = Vector.scale(Vector.hermitianDot(b, c), a);
        w[2] = Vector.scale(Vector.hermitianDot(c, a), b);
        w[3] = Vector.scale(Vector.hermitianDot(a, b), c);
        z[1] = Vector.hermitianDot(w[1], w[2]);
        z[1] = Complex.unit(z[1]);
        z[1] = Complex.inverse(z[1]);
        z[2] = Vector.hermitianDot(w[2], w[3]);
        z[2] = Complex.unit(z[2]);
        z[3] = new Complex(-1.0, 0.0);
        m.a = Vector.scale(z[1], w[1]);
        m.c = Vector.scale(z[2], w[3]);
        m.b = Vector.scale(z[3], w[2]);
        return m;
    }

    /*this routine supposes that the vectors lie in an R-circle*/

    Vector R_param(double t)
    {
        Complex[] z = new Complex[5];
        Matrix m = new Matrix();
        Vector[] w = new Vector[6];
        m = this.perfect_basis();
        w[1] = new Vector();
        z[1] = new Complex();
        z[1] = new Complex(t * (t - 1.0), 0);
        z[2] = new Complex(t, 0.0);
        z[3] = new Complex(1.0 - t, 0.0);
        w[1] = Vector.scale(z[1], m.a);
        w[2] = Vector.scale(z[2], m.b);
        w[3] = Vector.scale(z[3], m.c);
        w[4] = Vector.add(Vector.add(w[1], w[2]), w[3]);
        w[5] = Vector.normalize(w[4]);
        return w[5];
    }
}