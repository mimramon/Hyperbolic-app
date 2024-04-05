class Vector {
    Complex a,b,c;

    Vector() {
        this.a=new Complex(0.0,0.0);
        this.b=new Complex(0.0,0.0);
        this.c=new Complex(0.0,0.0);
    }

    void print() {
        this.a.print();
        this.b.print();
        this.c.print();
    }


    Vector plus(Vector v,Vector w) {
        Vector x=new Vector();
        x.a=x.a.plus(v.a,w.a);
        x.b=x.b.plus(v.b,w.b);
        x.c=x.c.plus(v.c,w.c);
        return(x);
    }

    Vector minus(Vector v,Vector w) {
        Vector x=new Vector();
        x.a=x.a.minus(v.a,w.a);
        x.b=x.b.minus(v.b,w.b);
        x.c=x.c.minus(v.c,w.c);
        return(x);
    }

    Vector scale(Complex a,Vector v) {
        Vector w=new Vector();
        w.a=w.a.times(a,v.a);
        w.b=w.b.times(a,v.b);
        w.c=w.c.times(a,v.c);
        return(w);
    }

    Complex dot(Vector v,Vector w)
    {
        Complex d=new Complex();
        d=d.plus(d.plus(d.times(v.a,w.a),d.times(v.b,w.b)),d.times(v.c,w.c));
        return(d);
    }


    double norm(Vector x) {
        return(x.a.x*x.a.x+x.b.x*x.b.x+x.c.x*x.c.x);
    }

    double dist(Vector v,Vector w) {
        Vector vv=v.normalize(v);
        Vector  ww=w.normalize(w);
        return(vv.norm(vv.minus(vv,ww)));
    }


    Complex hermitian_dot(Vector v,Vector w) {
        Complex c1=new Complex();
        Complex c2=new Complex();
        Complex c3=new Complex();
        Complex c=new Complex();
        c1=c1.times(v.a,c1.conjugate(w.a));
        c2=c2.times(v.b,c2.conjugate(w.b));
        c3=c2.times(v.c,c3.conjugate(w.c));
        c=c.minus(c.plus(c1,c2),c3);
        return(c);
    }


    Complex angular_invariant(Vector v1,Vector v2,Vector v3) {
        Complex z1=new Complex();
        Complex z2=new Complex();
        Complex z3=new Complex();
        Complex z=new Complex();
        z1=hermitian_dot(v1,v2);
        z2=hermitian_dot(v2,v3);
        z3=hermitian_dot(v3,v1);
        z=z.times(z1,z.times(z2,z3));
        return(z);
    }


    Vector hermitian_cross(Vector v,Vector w) {
        Vector x=new Vector();
        x.a=x.a.minus(x.a.times(v.b,w.c),x.a.times(v.c,w.b));
        x.b=x.b.minus(x.b.times(v.c,w.a),x.b.times(v.a,w.c));
        x.c=x.c.minus(x.c.times(v.b,w.a),x.c.times(v.a,w.b));
        x.a=x.a.conjugate(x.a);
        x.b=x.b.conjugate(x.b);
        x.c=x.c.conjugate(x.c);
        return(x);
    }


    Vector normalize(Vector v) {
        Vector x=new Vector();
        x.a=x.a.divide(v.a,v.c);
        x.b=x.b.divide(v.b,v.c);
        x.c=x.c.divide(v.c,v.c);
        return(x);
    }


    Complex projection(Vector v) {
        Vector w=new Vector();
        Complex[] z=new Complex[5];
        z[1]=hermitian_dot(v,this);
        z[2]=hermitian_dot(this,this);
        z[3]=z[2].divide(z[1],z[2]);
        z[3].x=-z[3].x;
        z[3].y=-z[3].y;
        w=w.plus(v,w.scale(z[3],this));
        z[4]=z[3].divide(w.a,w.c);
        return(z[4]);
    }

    Vector P0(double s) {
        Vector v=new Vector();
        v.a=v.a.beta(s);
        v.b=v.b.conjugate(v.b.beta(s));
        v.c.x=1.0;
        v.c.y=0.0;
        return(v);
    }


    Vector P1(double s) {
        Vector v=new Vector();
        v.a=v.a.beta(s);
        v.b=v.b.beta(s);
        v.c.x=1.0;
        v.c.y=0.0;
        return(v);
    }


    Vector P2(double s) {
        Vector v=new Vector();
        v.a=v.a.conjugate(v.a.beta(s));
        v.b=v.b.conjugate(v.b.beta(s));
        v.c.x=1.0;
        v.c.y=0.0;
        return(v);
    }


    Vector C21(double s) {
        Vector[] v=new Vector[10];
        double[] d=new double[10];
        Complex[] z=new Complex[10];

        v[0]=P0(s);
        v[1]=P1(s);
        v[2]=P2(s);

        z[0]=v[1].hermitian_dot(v[0],v[2]);
        z[1]=v[1].hermitian_dot(v[1],v[2]);
        v[0]=v[0].scale(z[0].inverse(z[0]),v[0]);
        v[1]=v[1].scale(z[1].inverse(z[1]),v[1]);
        z[2]=hermitian_dot(v[0],v[1]);
        z[2]=z[2].unit(z[2]);
        z[3]=z[2].times(z[2],z[2].convert(0.0,1.0));
        v[1]=v[1].scale(z[3],v[1]);
        v[2]=v[1].plus(v[0],v[1]);
        return(v[2]);
    }

    Vector C22(double s) {
        Vector[] v=new Vector[10];
        double[] d=new double[10];
        Complex[] z=new Complex[10];

        v[0]=P0(s);
        v[1]=P1(s);
        v[2]=P2(s);

        z[0]=v[1].hermitian_dot(v[0],v[2]);
        z[1]=v[1].hermitian_dot(v[1],v[2]);
        v[0]=v[0].scale(z[0].inverse(z[0]),v[0]);
        v[1]=v[1].scale(z[1].inverse(z[1]),v[1]);
        z[2]=hermitian_dot(v[0],v[1]);
        z[2]=z[2].unit(z[2]);
        z[3]=z[2].times(z[2],z[2].convert(0.0,1.0));
        v[1]=v[1].scale(z[3],v[1]);
        v[2]=v[1].minus(v[0],v[1]);
        return(v[2]);
    }




    Vector C11(double s) {
        Vector[] v=new Vector[10];
        double[] d=new double[10];
        Complex[] z=new Complex[10];

        v[0]=P0(s);
        v[1]=P1(s);
        v[2]=P2(s);

        z[0]=v[1].hermitian_dot(v[0],v[1]);
        z[2]=v[1].hermitian_dot(v[2],v[1]);
        v[0]=v[0].scale(z[0].inverse(z[0]),v[0]);
        v[2]=v[2].scale(z[2].inverse(z[2]),v[2]);
        z[1]=hermitian_dot(v[0],v[2]);
        z[1]=z[1].unit(z[1]);
        z[3]=z[1].times(z[1],z[1].convert(0.0,1.0));
        v[2]=v[2].scale(z[3],v[2]);
        v[1]=v[2].plus(v[0],v[2]);
        return(v[1]);
    }



    Vector C12(double s) {
        Vector[] v=new Vector[10];
        double[] d=new double[10];
        Complex[] z=new Complex[10];

        v[0]=P0(s);
        v[1]=P1(s);
        v[2]=P2(s);

        z[0]=v[1].hermitian_dot(v[0],v[1]);
        z[2]=v[1].hermitian_dot(v[2],v[1]);
        v[0]=v[0].scale(z[0].inverse(z[0]),v[0]);
        v[2]=v[2].scale(z[2].inverse(z[2]),v[2]);
        z[1]=hermitian_dot(v[0],v[2]);
        z[1]=z[1].unit(z[1]);
        z[3]=z[1].times(z[1],z[1].convert(0.0,1.0));
        v[2]=v[2].scale(z[3],v[2]);
        v[1]=v[2].minus(v[0],v[2]);
        return(v[1]);
    }






    Vector fix1(double s) {
        Matrix m=new Matrix();
        Matrix mm=new Matrix();
        double test=0;
        Vector v1=new Vector();
        Vector v2=new Vector();
        int i=0;
        int count=0;
        m=m.J1(s);
        test=1.0;
        mm=mm.critical_axis();
        v1=v1.normalize(mm.b);
        while(test>0.000000000001) {
            v2=v2.normalize(m.act(v1));
            test=v1.dist(v1,v2);
            v1=v2;
        }
        return(v1);
    }

    Vector fix2(double s) {
        Matrix m=new Matrix();
        Matrix mm=new Matrix();
        double test=0;
        Vector v1=new Vector();
        Vector v2=new Vector();
        int i=0;
        int count=0;
        m=m.J2(s);
        test=1.0;
        mm=mm.critical_axis();
        v1=v1.normalize(mm.b);
        while(test>0.000000000001) {
            v2=v2.normalize(m.act(v1));
            test=v1.dist(v1,v2);
            v1=v2;
        }
        return(v1);
    }

    Vector torus_vector1(double s) {
        Matrix m=new Matrix();
        m=m.FIX(s);
        Complex[] z=new Complex[5];
        m.a=m.a.normalize(m.a);
        for(int i=1;i<=4;++i) z[i]=new Complex();
        z[1].x=1.0;
        z[1].y=Math.sqrt(2.0*z[1].norm(m.a.a)*z[1].norm(m.a.a)-1.0);
        z[2].x=2.0*m.a.b.x;
        z[2].y=2.0*m.a.b.y;
        z[3]=z[3].divide(z[1],z[2]);
        z[4]=z[3].conjugate(z[3]);
        m.a.a=z[3];
        m.a.b=z[4];
        return(m.a);
    }

    Vector torus_vector2(double s) {
        Matrix m=new Matrix();
        m=m.FIX(s);
        Complex[] z=new Complex[5];
        m.a=m.a.normalize(m.a);
        for(int i=1;i<=4;++i) z[i]=new Complex();
        z[1].x=1.0;
        z[1].y=-Math.sqrt(2.0*z[1].norm(m.a.a)*z[1].norm(m.a.a)-1.0);
        z[2].x=2.0*m.a.b.x;
        z[2].y=2.0*m.a.b.y;
        z[3]=z[3].divide(z[1],z[2]);
        z[4]=z[3].conjugate(z[3]);
        m.a.a=z[3];
        m.a.b=z[4];
        return(m.a);
    }


    Vector spinalExtension(Vector v,Vector w,Vector X) {
        Vector[] x=new Vector[10];
        Complex[] z=new Complex[10];
        x[1]=X;
        z[1]=X.hermitian_dot(x[1],v);
        z[2]=z[1].convert(0.0,-1.0);
        z[3]=z[1].times(z[1],z[2]);
        x[2]=X.scale(z[1].inverse(z[3]),x[1]);
        z[7]=X.hermitian_dot(w,v);
        x[4]=X.scale(z[7].inverse(z[7]),w);
        z[5]=X.hermitian_dot(x[4],x[2]);
        z[6]=z[5].convert(-z[5].x,0.0);
        x[5]=X.plus(x[2],X.scale(z[6],v));
        return(x[5]);
    }

}