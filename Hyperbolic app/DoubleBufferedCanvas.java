/*
This class implements the double
buffering.  The idea is to override the update
command to have the new picture drawn all
at once.
*/

import java.awt.*;
import java.awt.event.MouseEvent;

class DoubleBufferedCanvas extends Canvas
{
    Boolean on = true;

    public void update(Graphics g)
    {
        if (!on)
        {
            g.setColor(Color.black);
            g.fillRect(0, 0, getWidth(), getHeight());
            paint(g);
        } else
        {
            Graphics g2;
            Image offscreen = createImage(getSize().width, getSize().height);
            g2 = offscreen.getGraphics();
            paint(g2);
            g.drawImage(offscreen, 0, 0, this);
            offscreen.flush();
        }
    }

    Point pinpoint(MouseEvent e)
    {
        e.consume();
        Point p = new Point();
        p.x = e.getX();
        p.y = e.getY();
        return (p);
    }
}