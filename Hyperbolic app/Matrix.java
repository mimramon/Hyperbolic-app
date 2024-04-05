/*computational kernel*/

class Matrix {
    Vector a,b,c;

    Matrix() {
        this.a=new Vector();
        this.b=new Vector();
        this.c=new Vector();
    }


    Vector act(Vector v) {
        Vector w=new Vector();
        w.a=w.dot(a,v);
        w.b=w.dot(b,v);
        w.c=w.dot(c,v);
        return(w);
    }

    Matrix transpose() {
        Matrix w=new Matrix();
        w.a.a=a.a;
        w.a.b=b.a;
        w.a.c=c.a;
        w.b.a=a.b;
        w.b.b=b.b;
        w.b.c=c.b;
        w.c.a=a.c;
        w.c.b=b.c;
        w.c.c=c.c;
        return(w);
    }

    Matrix times(Matrix m1,Matrix m2) {
        Matrix w=new Matrix();
        Matrix mm2=new Matrix();
        mm2=m2.transpose();
        w.a.a=w.a.dot(m1.a,mm2.a);
        w.a.b=w.a.dot(m1.a,mm2.b);
        w.a.c=w.a.dot(m1.a,mm2.c);
        w.b.a=w.a.dot(m1.b,mm2.a);
        w.b.b=w.a.dot(m1.b,mm2.b);
        w.b.c=w.a.dot(m1.b,mm2.c);
        w.c.a=w.a.dot(m1.c,mm2.a);
        w.c.b=w.a.dot(m1.c,mm2.b);
        w.c.c=w.a.dot(m1.c,mm2.c);
        return(w);
    }

    Matrix I0(double s) {

        Matrix m0= new Matrix();
        m0.a.a.x=0;               m0.a.a.y=0;
        m0.a.b.x=-1;               m0.a.b.y=0;
        m0.a.c.x=0;               m0.a.c.y=0;
        m0.b.a.x=-1;               m0.b.a.y=0;
        m0.b.b.x=0;               m0.b.b.y=0;
        m0.b.c.x=0;               m0.b.c.y=0;
        m0.c.a.x=0;               m0.c.a.y=0;
        m0.c.b.x=0;               m0.c.b.y=0;
        m0.c.c.x=-1;               m0.c.c.y=0;
        return(m0);
    }


    Matrix I1(double s) {
        Complex b=new Complex();
        b=b.beta(s);
        Matrix m1=new Matrix();
        m1.a.a.x=-1;                      m1.a.a.y=0;
        m1.a.b.x=0;                       m1.a.b.y=0;
        m1.a.c.x=0;                       m1.a.c.y=0;
        m1.b.a.x=0;                       m1.b.a.y=0;
        m1.b.b.x=3;                       m1.b.b.y=0;
        m1.b.c.x=-4.0*b.x;                m1.b.c.y=4.0*b.y;
        m1.c.a.x=0;                       m1.c.a.y=0;
        m1.c.b.x=4.0*b.x;                 m1.c.b.y=4.0*b.y;
        m1.c.c.x=-3;                      m1.c.c.y=0;
        return(m1);
    }


    Matrix I2(double s) {
        Complex b=new Complex();
        b=b.beta(s);
        Matrix m1=new Matrix();
        m1.a.a.x=3;                       m1.a.a.y=0;
        m1.a.b.x=0;                       m1.a.b.y=0;
        m1.a.c.x=-4.0*b.x;                m1.a.c.y=-4.0*b.y;
        m1.b.a.x=0;                       m1.b.a.y=0;
        m1.b.b.x=-1;                      m1.b.b.y=0;
        m1.b.c.x=0.0;                     m1.b.c.y=0.0;
        m1.c.a.x=4.0*b.x;                 m1.c.a.y=-4.0*b.y;
        m1.c.b.x=0;                       m1.c.b.y=0;
        m1.c.c.x=-3;                      m1.c.c.y=0;
        return(m1);
    }


    Matrix J1(double s) {
        Matrix m=new Matrix();
        m=m.times(m.times(m.I1(s),m.I0(s)),m.I2(s));
        return(m);
    }

    Matrix J2(double s) {
        Matrix m=new Matrix();
        m=m.times(m.times(m.I2(s),m.I0(s)),m.I1(s));
        return(m);
    }


    Matrix critical_axis() {
        Matrix m=new Matrix();
        Complex lambda=new Complex();
        Complex mu=new Complex();
        lambda.x=5.0*Math.sqrt(5.0)/16.0;
        lambda.y=Math.sqrt(3.0)/16.0;
        mu.x=Math.sqrt(5.0)/4.0;
        mu.y=Math.sqrt(3.0)/4.0;
        m.b.a=lambda.conjugate(lambda);
        m.b.b=lambda;
        m.b.c=lambda.convert(1.0,0.0);
        m.c.a=mu;
        m.c.b=mu.conjugate(mu);
        m.c.c=lambda.convert(1.0,0.0);
        m.a=m.a.hermitian_cross(m.b,m.c);
        m.a=m.a.normalize(m.a);
        return(m);
    }


    Matrix FIX(double s) {
        Matrix m=new Matrix();
        Complex[] z=new Complex[10];
        double[] d=new double[5];
        Vector v=new Vector();
        m.b=m.b.fix1(s);
        m.c=m.b.fix2(s);
        /********adjusting the lifts**********/
        z[1]=new Complex(0.0,1.0);
        z[2]=v.hermitian_dot(m.b,m.c);
        z[3]=z[2].divide(z[1],z[2]);
        m.b=v.scale(z[3],m.b);
        d[1]=Math.sqrt(m.b.c.norm(m.b.c));
        z[4]=z[3].convert(d[1],0.0);
        z[5]=z[3].convert(1.0/d[1],0.0);
        m.b=v.scale(z[5],m.b);
        m.c=v.scale(z[4],m.c);
        z[6]=z[1].inverse(z[1].unit(z[1].square_root(m.b.c)));
        m.b=m.b.scale(z[6],m.b);
        m.c=m.b.scale(z[6],m.c);
        /**************************************/
        m.a=v.hermitian_cross(m.b,m.c);
        return(m);
    }


    Matrix TOR(double s) {
        Matrix m=new Matrix();
        Vector v1=new Vector();
        Vector v2=new Vector();
        v1=v1.torus_vector1(s);
        v2=v2.torus_vector2(s);
        m.a=v1.hermitian_cross(v1,v2);
        m.b=v2;
        m.c=v1;
        return(m);
    }


    double map1(Vector v) {
        Complex[] z=new Complex[5];
        double d=0.0;
        z[1]=v.hermitian_dot(v,this.a);
        z[2]=v.hermitian_dot(v,this.b);
        z[3]=v.hermitian_dot(v,this.c);
        d=z[1].arg(z[1])-z[1].guided_arg(z[2],z[3]);
        d=d-Math.PI;
        if(d<0) d=d+2.0*Math.PI;
        if(d<0) d=d+2.0*Math.PI;
        d=d/(2.0*Math.PI);
        return(d);
    }

    double map2(Vector v) {
        Complex[] z=new Complex[6];
        double d=0.0;
        double dd=0.0;
        z[2]=v.hermitian_dot(v,this.b);
        z[3]=v.hermitian_dot(v,this.c);
        z[4]=z[3].divide(z[2],z[3]);
        d=Math.log(z[2].norm(z[4]));
        return(d);
    }

    double map3(Vector v) {
        Complex aa=a.projection(harmonic2(v));
        Complex bb=a.projection(this.b);
        Complex cc=a.projection(harmonic1(v));
        Complex ee=a.projection(v);
        Complex z1=aa.minus(aa,ee);
        Complex z2=aa.minus(cc,bb);
        Complex z3=aa.minus(bb,aa);
        Complex z4=aa.minus(cc,ee);
        Complex z5=z1.times(z1,z2);
        Complex z6=z3.times(z3,z4);
        Complex z7=z1.divide(z5,z6);
        double d2=z7.y;
        d2=Math.sqrt(d2);
        return(d2);
    }

    /*these are the main coordinate projections*/

    Complex PLAN1(Vector v) {
        Vector w=new Vector();
        double test=0.0;
        Complex z=new Complex();
        w.a=w.hermitian_dot(v,this.a);
        w.b=w.hermitian_dot(v,this.b);
        w.c=w.hermitian_dot(v,this.c);
        test=w.c.norm(w.c);
        if(test>.00001) z=z.divide(w.a,w.c);
        return(z);
    }

    Complex ELEV1(Vector v,Complex u) {
        Vector w=new Vector();
        double test=0.0;
        Complex z1=new Complex(1.0,0.0);
        Complex z2=new Complex(1.0,0.0);
        Complex z3=new Complex(1.0,0.0);
        w.a=w.hermitian_dot(v,this.a);
        w.b=w.hermitian_dot(v,this.b);
        w.c=w.hermitian_dot(v,this.c);
        test=w.c.norm(w.c);
        if(test>.00001) z1=z1.divide(w.a,w.c);
        z1=z1.times(u,z1);
        z2=z2.divide(w.b,w.c);
        z3.x=z1.arg(z1)/(Math.PI*2);
        z3.y=z2.y;
        return(z3);
    }


    Complex HYP1(Vector v) {
        Complex z=new Complex();
        z=a.projection(v);
        return(z);
    }


    Complex ELEV2(Vector v) {
        Complex z=new Complex();
        z.x=map1(v);
        z.y=map2(v);
        return(z);
    }

    Complex HYP2(Vector v) {
        Complex z=new Complex();
        z.x=map3(v)*map3(v);
        z.y=map2(v);
        return(z);
    }


    Complex PLAN2(Vector v) {
        double d1=map1(v);
        double x=2*Math.PI;
        Complex z=new Complex(Math.cos(x*d1),Math.sin(x*d1));
        double d2=map3(v);
        z.x=z.x*d2;
        z.y=z.y*d2;
        return(z);
    }

    /*these routines are the basis for the 
      loxodromic cone construction */

    Vector harmonic1(Vector v) {
        double d=0.0;
        Complex cc=new Complex();
        Complex ss=new Complex();
        Vector w2=new Vector();
        d=map2(v);
        d=Math.exp(d*cc.norm(w2.hermitian_dot(b,c)));
        d=1.0/d;
        cc.x=1.0/Math.sqrt(1.0+d*d);
        ss.x=d/Math.sqrt(1.0+d*d);
        cc.y=0.0;
        ss.y=0.0;
        w2=w2.plus(w2.scale(ss,b),w2.scale(cc,c));
        w2=w2.normalize(w2);
        return(w2);
    }

    Vector harmonic2(Vector v) {
        double d=0.0;
        Complex cc=new Complex();
        Complex ss=new Complex();
        Vector w2=new Vector();
        d=map2(v);
        d=Math.exp(d*cc.norm(w2.hermitian_dot(b,c)));
        d=1.0/d;
        cc.x=-1.0/Math.sqrt(1.0+d*d);
        ss.x=d/Math.sqrt(1.0+d*d);
        cc.y=0.0;
        ss.y=0.0;
        w2=w2.plus(w2.scale(ss,b),w2.scale(cc,c));
        w2=w2.normalize(w2);
        return(w2);
    }

    Matrix doCone(Vector v) {
        Matrix m=new Matrix();
        Matrix m2=new Matrix();
        m.a=harmonic2(v);
        m.c=v;
        m.b=harmonic1(v);
        m2=m.perfect_basis();
        return(m2);
    }

    Matrix doLCone(Vector v) {
        Matrix m=new Matrix();
        Matrix m2=new Matrix();
        m.b=harmonic2(v);
        m.c=v;
        m.a=harmonic1(v);
        m2=m.perfect_basis();
        return(m2);
    }

    Matrix doRCone(Vector v) {
        Matrix m=new Matrix();
        Matrix m2=new Matrix();
        m.c=harmonic2(v);
        m.a=v;
        m.b=harmonic1(v);
        m2=m.perfect_basis();
        return(m2);
    }

    /*these are the routines for parameterizing C-arcs and R-arcs*/

    /*C-arcs*/
    Vector C_param(double t) {
        Vector p=new Vector();
        Vector w=new Vector();
        Complex[] z=new Complex[7];
        z[1]=new Complex();
        z[1]=z[1].convert(0.0,1.0);
        z[2]=p.hermitian_dot(a,b);
        z[2]=z[1].unit(z[2]);
        z[3]=z[2].divide(z[1],z[2]);
        w=w.scale(z[3],a);
        z[4]=z[1].convert(Math.cos(t),0.0);
        z[5]=z[1].convert(Math.sin(t),0.0);
        p=p.plus(p.scale(z[4],w),p.scale(z[5],b));
        p=p.normalize(p);
        return(p);
    }

    /*R-arcs*/

    Matrix perfect_basis() {
        Vector[] w=new Vector[7];
        Complex[] z=new Complex[5];
        Matrix m=new Matrix();
        w[1]=new Vector();
        w[1]=w[1].scale(w[1].hermitian_dot(b,c),a);
        w[2]=w[1].scale(w[1].hermitian_dot(c,a),b);
        w[3]=w[1].scale(w[1].hermitian_dot(a,b),c);
        z[1]=w[1].hermitian_dot(w[1],w[2]);
        z[1]=z[1].unit(z[1]);
        z[1]=z[1].inverse(z[1]);
        z[2]=w[1].hermitian_dot(w[2],w[3]);
        z[2]=z[2].unit(z[2]);
        z[3]=z[1].convert(-1.0,0.0);
        m.a=w[1].scale(z[1],w[1]);
        m.c=w[1].scale(z[2],w[3]);
        m.b=w[1].scale(z[3],w[2]);
        return(m);
    }

    /*this routine supposes that the vectors lie in an R-circle*/

    Vector R_param(double t) {
        Complex[] z=new Complex[5];
        Matrix m=new Matrix();
        Vector[] w=new Vector[6];
        m=this.perfect_basis();
        w[1]=new Vector();
        z[1]=new Complex();
        z[1]=z[1].convert(t*(t-1.0),0);
        z[2]=z[1].convert(t,0.0);
        z[3]=z[1].convert(1.0-t,0.0);
        w[1]=w[1].scale(z[1],m.a);
        w[2]=w[1].scale(z[2],m.b);
        w[3]=w[1].scale(z[3],m.c);
        w[4]=w[1].plus(w[1].plus(w[1],w[2]),w[3]);
        w[5]=w[1].normalize(w[4]);
        return(w[5]);
    }
}