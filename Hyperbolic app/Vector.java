
class Vector
{
    Complex a, b, c;

    Vector()
    {
        this.a = new Complex();
        this.b = new Complex();
        this.c = new Complex();
    }

    Vector(Complex a, Complex b, Complex c)
    {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    void print()
    {
        this.a.print();
        this.b.print();
        this.c.print();
    }


    static Vector plus(Vector v, Vector w)
    {
        return new Vector(Complex.plus(v.a, w.a), Complex.plus(v.b, w.b), Complex.plus(v.c, w.c));
    }

    static Vector minus(Vector v, Vector w)
    {
        return new Vector(Complex.minus(v.a, w.a), Complex.minus(v.b, w.b), Complex.minus(v.c, w.c));
    }

    static Vector scale(Complex a, Vector v)
    {
        return new Vector(Complex.times(a, v.a), Complex.times(a, v.b), Complex.times(a, v.c));
    }

    static Complex dot(Vector v, Vector w)
    {
        return Complex.plus(Complex.plus(Complex.times(v.a, w.a), Complex.times(v.b, w.b)), Complex.times(v.c, w.c));
    }


    static double norm(Vector v)
    {
        return v.a.x * v.a.x + v.b.x * v.b.x + v.c.x * v.c.x;
    }

    static double dist(Vector v, Vector w)
    {
        Vector vv = Vector.normalize(v);
        Vector ww = Vector.normalize(w);
        return Vector.norm(Vector.minus(vv, ww));
    }


    static Complex hermitian_dot(Vector v, Vector w)
    {
        Complex c1 = Complex.times(v.a, Complex.conjugate(w.a));
        Complex c2 = Complex.times(v.b, Complex.conjugate(w.b));
        Complex c3 = Complex.times(v.c, Complex.conjugate(w.c));
        return Complex.minus(Complex.plus(c1, c2), c3);
    }


    static Complex angular_invariant(Vector v1, Vector v2, Vector v3)
    {
        Complex z1 = Vector.hermitian_dot(v1, v2);
        Complex z2 = Vector.hermitian_dot(v2, v3);
        Complex z3 = Vector.hermitian_dot(v3, v1);
        return Complex.times(z1, Complex.times(z2, z3));
    }


    static Vector hermitian_cross(Vector v, Vector w)
    {
        Vector x = new Vector();
        x.a = Complex.conjugate(Complex.minus(Complex.times(v.b, w.c), Complex.times(v.c, w.b)));
        x.b = Complex.conjugate(Complex.minus(Complex.times(v.c, w.a), Complex.times(v.a, w.c)));
        x.c = Complex.conjugate(Complex.minus(Complex.times(v.b, w.a), Complex.times(v.a, w.b)));
        return x;
    }


    static Vector normalize(Vector v)
    {
        return new Vector(Complex.divide(v.a, v.c), Complex.divide(v.b, v.c), Complex.divide(v.c, v.c));
    }

    //Discuss with anna to refactor this
    Complex projection(Vector v)
    {
        Complex z = Complex.divide(hermitian_dot(v, this), hermitian_dot(this, this));
        z = Complex.times(z, new Complex(-1, 0));
        Vector w = Vector.plus(v, Vector.scale(z, this));
        return Complex.divide(w.a, w.c);
    }

    static Vector P0(double s)
    {
        return new Vector(Complex.beta(s), Complex.conjugate(Complex.beta(s)), new Complex(1, 0));
    }


    static Vector P1(double s)
    {
        return new Vector(Complex.beta(s), Complex.beta(s), new Complex(1, 0));
    }


    static Vector P2(double s)
    {
        return new Vector(Complex.conjugate(Complex.beta(s)), Complex.conjugate(Complex.beta(s)), new Complex(1, 0));
    }

    //Function requires further refactoring
    static Vector C21(double s)
    {
        Vector v1 = P0(s);
        Vector v2 = P1(s);

        Complex z1 = Vector.hermitian_dot(P0(s), P2(s));
        Complex z2 = Vector.hermitian_dot(P1(s), P2(s));
        v1 = Vector.scale(Complex.inverse(z1), v1);
        v2 = Vector.scale(Complex.inverse(z2), v2);
        Complex z3 = Vector.hermitian_dot(v1, v2);
        z3 = Complex.unit(z3);
        Complex z4 = Complex.times(z3, new Complex(0.0, 1.0));
        v2 = Vector.scale(z4, v2);
        return Vector.plus(v1, v2);
    }

    //Function requires further refactoring
    static Vector C22(double s)
    {
        Vector v1 = P0(s);
        Vector v2 = P1(s);
        Vector v3 = P2(s);

        Complex z1 = Vector.hermitian_dot(v1, v3);
        Complex z2 = Vector.hermitian_dot(v2, v3);
        v1 = Vector.scale(Complex.inverse(z1), v1);
        v2 = Vector.scale(Complex.inverse(z2), v2);
        Complex z3 = Vector.hermitian_dot(v1, v2);
        z3 = Complex.unit(z3);
        Complex z4 = Complex.times(z3, new Complex(0.0, 1.0));
        v2 = Vector.scale(z4, v2);
        v3 = Vector.minus(v1, v2);
        return v3;
    }


    //requires more refactoring
    static Vector C11(double s)
    {
        Vector v1 = P0(s);
        Vector v2 = P1(s);
        Vector v3 = P2(s);

        Complex z1 = Vector.hermitian_dot(v1, v2);
        Complex z3 = Vector.hermitian_dot(v3, v2);
        v1 = Vector.scale(Complex.inverse(z1), v1);
        v3 = Vector.scale(Complex.inverse(z3), v3);
        Complex z2 = hermitian_dot(v1, v3);
        z2 = Complex.unit(z2);
        Complex z4 = Complex.times(z2, new Complex(0.0, 1.0));
        v3 = Vector.scale(z4, v3);
        v2 = Vector.plus(v1, v3);
        return (v2);
    }

    //requires more refactoring
    static Vector C12(double s)
    {
        Vector v1 = P0(s);
        Vector v2 = P1(s);
        Vector v3 = P2(s);

        Complex z1 = Vector.hermitian_dot(v1, v2);
        Complex z3 = Vector.hermitian_dot(v3, v2);
        v1 = Vector.scale(Complex.inverse(z1), v1);
        v3 = Vector.scale(Complex.inverse(z3), v3);
        Complex z2 = Vector.hermitian_dot(v1, v3);
        z2 = Complex.unit(z2);
        Complex z4 = Complex.times(z2, new Complex(0.0, 1.0));
        v3 = Vector.scale(z4, v3);
        v2 = Vector.minus(v1, v3);
        return (v2);
    }

    //Requires Matrix class to be refactored
    Vector fix1(double s)
    {
        Matrix m = Matrix.J1(s);
        Vector v1 = Vector.normalize(Matrix.critical_axis().b);
        Vector v2;
        double test = 1.0;
        while (test > 0.000000000001)
        {
            v2 = Vector.normalize(m.act(v1));
            test = Vector.dist(v1, v2);
            v1 = v2;
        }
        return v1;
    }

    //Requires matrix class to be refactored
    Vector fix2(double s)
    {
        Vector v2;
        Matrix m = Matrix.J2(s);
        double test = 1.0;
        Matrix mm = Matrix.critical_axis();
        Vector v1 = Vector.normalize(mm.b);
        while (test > 0.000000000001)
        {
            v2 = Vector.normalize(m.act(v1));
            test = Vector.dist(v1, v2);
            v1 = v2;
        }
        return (v1);
    }

    //Requires matrix class to be refactored
    Vector torus_vector1(double s)
    {
        Matrix m = Matrix.FIX(s);
        m.a = Vector.normalize(m.a);
        Complex z1 = new Complex(1, Math.sqrt(2.0 * Complex.norm(m.a.a) * Complex.norm(m.a.a) - 1.0));
        Complex z3 = Complex.divide(z1, z1);
        Complex z4 = Complex.conjugate(z3);
        m.a = new Vector(z3, z4, m.a.c);
        return m.a;
    }

    //Requires matrix class to be refactored
    Vector torus_vector2(double s)
    {
        Matrix m = Matrix.FIX(s);
        Complex[] z = new Complex[5];
        m.a = Vector.normalize(m.a);
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

    //further refactoring required
    static Vector spinalExtension(Vector v, Vector w, Vector X)
    {
        Vector[] x = new Vector[10];
        Complex[] z = new Complex[10];
        x[1] = X;
        z[1] = Vector.hermitian_dot(x[1], v);
        z[2] = new Complex(0.0, -1.0);
        z[3] = Complex.times(z[1], z[2]);
        x[2] = Vector.scale(Complex.inverse(z[3]), x[1]);
        z[7] = Vector.hermitian_dot(w, v);
        x[4] = Vector.scale(Complex.inverse(z[7]), w);
        z[5] = Vector.hermitian_dot(x[4], x[2]);
        z[6] = new Complex(-z[5].x, 0.0);
        x[5] = Vector.plus(x[2], Vector.scale(z[6], v));
        return (x[5]);
    }
}