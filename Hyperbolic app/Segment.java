import java.awt.*;

class Segment
{
    Complex z, w;
    Color C;

    Segment(Complex z, Complex w, Color C)
    {
        this.z = z;
        this.w = w;
        this.C = C;
    }

    Segment()
    {
        z = new Complex();
        w = new Complex();
        C = Color.white;
    }

    void render(Graphics g)
    {
        g.setColor(C);
        int x1 = (int) (z.x);
        int y1 = (int) (z.y);
        int x2 = (int) (w.x);
        int y2 = (int) (w.y);
        g.drawLine(x1, y1, x2, y2);
    }
}