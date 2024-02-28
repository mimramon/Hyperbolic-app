import java.awt.*;

class Segment
{
    Complex z1, z2;
    Color C;

    Segment(Complex z1, Complex z2, Color C)
    {
        this.z1 = z1;
        this.z2 = z2;
        this.C = C;
    }

    Segment()
    {
        this.z1 = new Complex();
        this.z2 = new Complex();
        this.C = Color.white;
    }

    void render(Graphics g)
    {
        g.setColor(C);
        int x1 = (int) (z1.x);
        int y1 = (int) (z1.y);
        int x2 = (int) (z2.x);
        int y2 = (int) (z2.y);
        g.drawLine(x1, y1, x2, y2);
    }
}