/*this is the button class*/

import java.awt.*;

class ListenSquare
{
    int x, y, w, h;
    Color C;
    String S;
    int select;
    int current;
    int future;
    int dens;

    ListenSquare(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
        this.C = Color.black;
        this.select = 0;
        S = "";
    }

    void render(Graphics g, Color C)
    {
        g.setColor(C);
        g.fillRect(x, y, w, h);
        g.setColor(Color.black);
        g.drawRect(x, y, w, h);
    }

    void render2(Graphics g, Color C)
    {
        if (select == 1)
        {
            g.setColor(C);
            g.fillRect(x, y, w, h);
            g.setColor(Color.black);
            g.drawRect(x, y, w, h);
        }
    }

    void render3(Graphics g, Color C, Color line)
    {
        g.setColor(C);
        g.fillRect(x, y, w, h);
        g.setColor(line);
        g.drawRect(x, y, w, h);
    }

    void render4(Graphics g, Color C, Color TC)
    {
        if (select == 1)
        {
            g.setColor(C);
            g.fillRect(x, y, w, h);
            g.setColor(TC);
            g.drawRect(x, y, w, h);
        }
    }


    int inside(Point p)
    {
        int test = 0;
        if ((p.x > x) && (p.x < x + w) && (p.y > y) && (p.y < y + h)) test = 1;
        return (test);
    }
}