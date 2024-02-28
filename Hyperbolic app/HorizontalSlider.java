/*horizontal slider class*/

import java.awt.*;

class HorizontalSlider {
    int x,y,w,h;
    int ACTIVE;
    int POS;
    Color C;
    HorizontalSlider(int x,int y,int w,int h,int POS,Color C) {
        this.x=x;
        this.y=y;
        this.h=h;
        this.w=w;
        this.POS=POS;
        this.C=C;
        ACTIVE=0;
    }

    void render(Graphics g) {
        g.setColor(C);
        g.fillRect(x,y,w,h);
        g.setColor(Color.black);
        g.drawRect(x,y,w,h);
        g.setColor(Color.white);
        g.fillRect(POS-1,y+1,2,h-2);
        g.drawRect(POS-1,y+1,2,h-2);
    }

    void configure(Point p) {
        if((ACTIVE==1)&&(p.x<x+w)&&(p.x>x)) POS=p.x;
    }

    int inside(Point p) {
        int test=0;
        if((p.x>x)&&(p.x<x+w)&&(p.y>y)&&(p.y<y+h)) test=1;
        return(test);
    }
}