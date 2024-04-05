import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class DocumentCanvas extends DoubleBufferedCanvas implements MouseListener
{
    String St[]=new String[30];
    int MESSAGE,history;
    int ACTIVE;
    Font font;
    int pix;
    Color COL,TCOL;

    DocumentCanvas() {
        addMouseListener(this);
        setBackground(Color.black);
        MESSAGE=50;
        history=-1;
        on=0;
        font=new Font("Helvetica",Font.PLAIN,11);
        ACTIVE=0;
        TCOL=Color.white;
        COL=new Color(0,0,200);
    }


    public void paint(Graphics gfx) {
        Graphics2D g=(Graphics2D) gfx;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        setMESSAGE();
        g.setFont(font);

        if(ACTIVE==1) {

            g.setColor(Color.white);
            g.drawRect(0,0,301,221);
            for(int i=2;i<=21;++i) {
                g.drawString(St[i],10,-13+14*i);
                history=MESSAGE;
            }

        }

        if(ACTIVE==0) pixRender(g);
    }






    void pixRender(Graphics g) {

        g.setColor(COL);
        g.fillRect(0,0,301,221);
        g.setColor(TCOL);
        g.drawRect(0,0,301,221);

        if(pix==0) {
            g.setFont(font);
            g.setColor(TCOL);
            g.drawString("Rich: Applet 45",10,20);
            g.drawString("June 23, 2004",100,20);
            g.translate(0,-3);
            g.drawString("An",10,60);
            g.drawString("Interactive",10,75);
            g.drawString("Proof",10,90);
            g.drawString("of",10,105);
            g.drawString("the",10,120);
            g.drawString("Goldman-Parker",10,135);
            g.drawString("Conjecture",10,150);
            g.translate(0,3);
            g.drawString("Click on this window or",10,183);
            g.drawString("on the 'doc:on' button",10,198);
            g.drawString("to activate the documentation text.",10,213);
        }

        if(pix==1) {
            g.translate(0,-13);
            g.setColor(Color.red);
            g.fillRect(20,20,15,30);
            g.setColor(TCOL);
            g.drawRect(20,20,15,30);
            g.setColor(new Color(100,100,255));
            g.fillRect(20,50,15,100);
            g.setColor(TCOL);
            g.drawRect(20,50,15,100);
            g.setColor(new Color(0,200,0));
            g.fillRect(20,150,15,30);
            g.setColor(TCOL);
            g.drawRect(20,150,15,30);
            g.drawString("a short-long-short combo",43,30);
            g.translate(10,-30);
            g.setColor(new Color(0,200,0));
            g.drawArc(80,80,160,160,90,180);
            g.setColor(new Color(100,100,255));
            g.drawArc(80,80,160,160,270,90);
            g.setColor(Color.red);
            g.drawArc(80,80,160,160,0,90);
            g.setColor(TCOL);
            g.drawLine(160,50,160,260);
            g.drawString("Ej",145,255);
            g.drawLine(200,160,280,160);
            g.drawString("C1",265,150);
            g.translate(-10,40);
        }

        if(pix==2) {
            g.translate(-20,-68);
            g.setColor(new Color(0,200,0));
            g.drawArc(80,80,160,160,90,180);
            g.setColor(new Color(100,100,255));
            g.drawArc(80,80,160,160,270,90);
            g.setColor(new Color(0,200,0));
            g.drawArc(80,80,160,160,0,90);
            g.setColor(Color.red);
            g.drawLine(160,0,160,320);
            g.setColor(TCOL);
            g.fillOval(157,77,6,6);
            g.fillOval(157,197,6,6);
            g.fillOval(157,237,6,6);
            g.fillOval(237,157,6,6);
            g.fillOval(157,277,6,6);
            g.drawLine(160,200,160,280);
            g.drawString("Q0",140,260);
            g.drawString("E0",140,150);
            g.drawString("b1",165,200);
            g.drawString("a1",165,232);
            g.drawString("a2",165,92);
            g.drawString("b2",165,280);
            g.drawString("R(x)",83,85);
            g.drawLine(200,160,280,160);
            g.drawString("C1",265,150);
            g.drawString("x",243,170);
            g.drawString("S(E0,Q0;x)",230,230);
            g.translate(20,40);
        }

        if(pix==3) {
            g.translate(0,-40);
            g.setColor(Color.red);
            g.drawOval(50,50,200,200);
            g.setColor(new Color(100,100,255));
            g.drawLine(0,100,300,100);
            g.drawLine(235,0,235,300);
            g.drawLine(0,65,300,235);
            g.setColor(TCOL);
            g.fillOval(232,97,6,6);
            g.fillOval(60,97,6,6);
            g.fillOval(233,196,6,6);
            g.drawString("C1",240,60);
            g.drawString("p2",240,92);
            g.drawString("C0",280,92);
            g.drawString("p1",60,118);
            g.drawString("p0",215,205);
            g.drawString("C2",280,220);
            g.drawString("S^3",70,63);
            g.translate(0,40);
        }

        if(pix==4) {
            g.translate(-20,-10);
            g.setColor(new Color(200,200,200));
            g.fillOval(63,70,100,100);
            g.drawOval(63,70,100,100);
            g.setColor(new Color(255,0,0));
            g.fillOval(150,120,100,100);
            g.drawOval(150,120,100,100);
            g.setColor(new Color(100,100,255));
            g.fillOval(150,20,100,100);
            g.drawOval(150,20,100,100);
            g.setColor(TCOL);
            g.drawString("S1",250,52);
            g.drawString("S0",55,90);
            g.drawString("S2",140,203);
        }

        if(pix==5) {
            g.translate(-20,-65);
            g.setColor(new Color(100,100,255));
            g.drawArc(80,80,160,160,270,90);
            g.setColor(new Color(100,100,255));
            g.drawArc(80,80,160,160,0,90);
            g.setColor(Color.red);
            g.drawLine(160,0,160,320);
            g.setColor(TCOL);
            g.fillOval(157,77,6,6);
            g.fillOval(157,197,6,6);
            g.fillOval(157,237,6,6);
            g.fillOval(237,157,6,6);
            g.fillOval(157,277,6,6);
            g.drawLine(160,200,160,280);
            g.drawString("Q0",140,260);
            g.drawString("E0",140,150);
            g.drawString("b1",165,200);
            g.drawString("a1",165,232);
            g.drawString("a2",165,94);
            g.drawString("b2",165,280);
            g.drawString("A(x)",190,83);
            g.drawString("x",243,170);
            g.translate(20,65);
        }

        if(pix==6) {
            g.translate(0,-35);
            g.setColor(Color.black);
            g.fillRect(30,40,250,30);
            g.fillRect(30,220,250,30);
            g.setColor(new Color(150,150,150));
            g.drawRect(30,40,250,30);
            g.drawRect(30,220,250,30);
            g.drawString("C1",120,62);
            g.drawString("E2",120,242);
            g.translate(50,50);
            g.setColor(Color.red);
            g.fillRect(20,20,15,30);
            g.setColor(TCOL);
            g.drawRect(20,20,15,30);
            g.setColor(new Color(100,100,255));
            g.fillRect(20,50,15,100);
            g.setColor(TCOL);
            g.drawRect(20,50,15,90);
            g.setColor(new Color(0,200,0));
            g.fillRect(20,140,15,30);
            g.setColor(TCOL);
            g.drawRect(20,140,15,30);
            g.drawString("a short-long-short combo",43,58);
            g.drawString("connecting C1 to E2",43,75);
            g.translate(-50,-50);
            g.translate(0,35);
        }

        if(pix==7) {
            g.translate(0,-35);
            g.setColor(Color.black);
            g.fillRect(30,40,250,30);
            g.fillRect(30,220,250,30);
            g.fillRect(70,70,15,30);
            g.fillRect(70,190,15,30);
            g.setColor(new Color(100,100,255));
            g.fillRect(70,100,15,90);
            g.setColor(new Color(150,150,150));
            g.drawRect(30,40,250,30);
            g.drawRect(30,220,250,30);
            g.drawRect(70,70,15,30);
            g.drawRect(70,190,15,30);
            g.drawRect(70,100,15,90);
            g.drawString("E0",120,62);
            g.drawString("C1",120,242);
            g.translate(0,35);
        }

        if(pix==8) {
            g.setColor(TCOL);
            g.translate(0,-12);
            g.drawString("Downward flow of implications:",10,25);
            g.translate(0,-6);
            g.drawString("monotonicity",10,50);
            g.drawString("calculus problem",120,50);
            //g.setFont(new Font("Helvetica",Font.PLAIN,10));
            g.drawString("ELEV2(S1-E0) # ELEV2(S2-E0)=ELEV2(p0)",35,140);
            g.drawString("ELEV2(C1) # ELEV2(C2)=ELEV2(p0)",100,85);
            g.setFont(font);
            g.drawString("properties",50,170);
            g.drawString("of ELEV2 & S1",50,185);
            g.drawString("(S1-E0) # (S2-E0) = A",130,235);
            g.drawLine(150,55,150,70);
            g.drawLine(30,55,150,125);
            g.drawLine(150,90,150,125);
            g.drawLine(282,90,282,150);
            g.drawLine(10,55,10,210);
            g.drawLine(282,150,160,220);
            g.drawLine(10,210,160,220);
            g.drawLine(200,150,160,220);
            g.drawLine(80,190,160,220);
            g.translate(0,18);
        }


        if(pix==9) {
            g.translate(0,-35);
            g.setColor(Color.black);
            g.fillRect(100,40,10,150);
            g.setColor(new Color(0,170,0));
            g.fillRect(30,140,230,20);
            g.setColor(TCOL);
            g.drawString("C1",120,155);
            g.setColor(new Color(100,100,255));
            g.fillRect(100,180,10,70);
            g.setColor(new Color(180,180,255));
            g.fillRect(100,40,10,80);
            g.setColor(new Color(150,150,150));
            g.drawRect(100,160,10,20);
            g.drawRect(100,180,10,70);
            g.drawRect(100,120,10,20);
            g.drawRect(100,40,10,80);
            g.translate(0,35);
        }
    }

    void setMESSAGE() {
        for(int i=1;i<=21;++i)           St[i]="";

        if(MESSAGE==-1) {
            St[2]="You are not currently interacting.";
            St[3]="with the applet.  Move the cursor";
            St[4]="into one of the windows of the";
            St[5]="applet to interact with it";
        }

        if(MESSAGE==0) {
            St[2]="This is the documentation window.";
            St[3]="If you want to learn about some part of";
            St[4]="the applet, move the mouse over that part";
            St[5]="and then read about it here.";
            St[6]="When the documentation feature is not active";
            St[7]="this window is used to display pictures";
            St[8]="which accompany the text.  You can";
            St[9]="switch between the two modes of this window";
            St[10]="either by clicking on this window or by";
            St[11]="using the 'doc:on' and 'doc:off' keys below.";
        }

        if(MESSAGE==1) {
            St[2]="You have entered the control panel.";
            St[3]="In the control panel you can select";
            St[4]="objects to plot, and control the";
            St[5]="parameters of the plot.  Once you";
            St[6]="push the plot button, the selected objects";
            St[7]="are plotted in 6 coordinate systems:";
            St[8]="ELEV1/ELEV2, PLAN1/PLAN2, HYP1/HYP2.";
        }

        if(MESSAGE==2) {
            St[2]="You've entered the text illustrator.";
            St[3]="This window draws figures which";
            St[4]="accompany the text.";
        }


        if(MESSAGE==3) {
            St[2]="You've entered the text window.";
            St[3]="Click on a key to read about the displayed";
            St[4]="topic.  The current topic title is shown at";
            St[5]="the top left.  The left 6 and right 8 keys";
            St[6]="do not change.  The middle 4 keys change";
            St[7]="according to the displayed topic.";
            St[8]="Some specially important things:";
            St[9]="Click the 'restart' to return to the intro.";
            St[10]="Click the 'back' text page to backtrack.";
            St[11]="Click on the 'notation' key for notation.";
            St[12]="Click the 'modify' key to change some";
            St[13]="basic features of the applet's operation,";
            St[14]="including the font and text size.";
        }

        if(MESSAGE==4) {
            St[2]="You've entered the ELEV window.";
            St[3]="The black vertical stripe represents a cylinder";
            St[4]="and the edges are meant to be identified.";
            St[5]="The cylinder is the range for all the plots.";
        }

        if(MESSAGE==5) {
            St[2]="You've entered the PLAN window.";
            St[3]="The white dot is the origin.";
            St[4]="The plane is the range for all the plots.";
        }

        if(MESSAGE==6) {
            St[2]="You've entered the HYP window.";
            St[3]="The black region is a half-plane.";
            St[4]="This half-plane is the range for the plots.";
        }

        if((MESSAGE==43)||((MESSAGE>=4)&&(MESSAGE<=6))) {
            St[6]="Once the picture is plotted you can";
            St[7]="rescale/recenter it by clicking on the window.";
            St[8]="If you click on the window the picture is scaled";
            St[9]="about that point by a factor controlled by the";
            St[10]="blue slider labelled 'scale'.";
            St[11]="The scale factor is less than 1 when the white";
            St[12]="bar on this slider is to the left of center.";
            St[13]="The scale factor is greater than 1 when";
            St[14]="the white bar on the slider is to the right";
            St[15]="of center.  You can scale up by a factor of 20";
            St[16]="and down by a factor of 5.";
        }

        if(MESSAGE==7) {
            St[2]="This is the parameter slider.  The objects in";
            St[3]="the applet are parametrized by a number in the";
            St[4]="interval [0,sqrt(125/3)).  Click the slider to";
            St[5]="select a parameter.  Depending on the mode (as";
            St[6]="controlled by the leftside button) the picture";
            St[7]="is automatically replotted at the new parameter";
            St[8]="value or not.  The parameter value s is displayed";
            St[9]="in black.  The 2 lighter numbers display";
            St[10]="s/sqrt(35) and s/sqrt(125/3).  The parameter";
            St[11]="selection is nonlinear: more of the bar is";
            St[12]="dedicated to [sqrt(35),sqrt(125/3)), where the";
            St[13]="conjecture was open.  For computational reasons";
            St[14]="any s>s0=sqrt(125/3)-.00001 is reset  to s0.";
        }

        if(MESSAGE==8) {
            St[2]="This is the color selector.  It";
            St[3]="consists of 35 little squares and";
            St[4]="a big rectangle.  Click on a";
            St[5]="little square and the big rectangle";
            St[6]="will change to the color on that square.";
            St[7]="When you select an object to plot, it will";
            St[8]="be plotted in the color shown on the big";
            St[9]="rectangle.  You can also use the color";
            St[10]="selector in tandem with the 'modify applet'";
            St[11]="button to change the colors of";
            St[12]="of the text window.";
        }

        if(MESSAGE==9) {
            St[2]="This is the plot density selector.";
            St[3]="When the marker is at the left";
            St[4]="the curves are drawn at low density.";
            St[5]="When the marker is at the right";
            St[6]="the curves are drawn at high density.";
            St[7]="Referring to the number D shown at the";
            St[8]="the right hand side of the slider:";
            St[9]="the curves E1,E2 are plotted using 3xD points.";
            St[10]="the curves C1,C2 are plotted using 2xD points.";
            St[11]="All other curves are plotted using D points.";
            St[12]="The picture is NOT automatically replotted";
            St[13]="after you change the plot density.";
            St[14]="On a slow computer you should keep the";
            St[15]="plot density low to avoid delays.";
        }

        if(MESSAGE==10) {
            St[2]="This is the erase switch.  Click to activate.";
            St[3]="The erase button is active when colored black.  ";
            St[4]="When the switch is active, you can de-select";
            St[5]="objects on the object selectors.  You de-select";
            St[6]="objects by clicking on them, or in some cases";
            St[7]="by dragging the mouse over them.";
        }


        if(MESSAGE==11) {

            St[2]="This is the plot button.  When you push";
            St[3]="it, the selected objects are plotted in 3";
            St[4]="coordinate systems: ELEV, PLAN, HYP.";
            St[7]="When 'plot' is pushed, the words";
            St[8]="'computing, please wait' appear. Assuming";
            St[9]="the mouse button has been subsequently,";
            St[10]="moved, these words disappear when the.";
            St[11]="plot is done.";
        }


        if(MESSAGE==12) {
            St[2]="This is the 'truncate arcs' slider.";
            St[3]="This slider determines how much of";
            St[4]="the selected R-arcs are plotted.";
            St[5]="This function is rather hard to";
            St[6]="describe.  If you click the 'money' button";
            St[7]="then click various parts of the slider,";
            St[8]="and replot, you will get the idea.";
            St[9]="The number printed on the slider";
            St[10]="indicates the fraction that is drawn.";
            St[11]="The rightside button resets the value";
            St[12]="to 1, so that the arcs are fully drawn.";
        }

        if(MESSAGE==13) {
            St[2]="This is the clear button.";
            St[3]="When you push this button the ";
            St[4]="object selectors are cleared.";
        }

        if(MESSAGE==14) {
            St[2]="'Show me the money' button: our proof of the";
            St[3]="conjecture comes down to showing, for each";
            St[4]="parameter, that a certain pair of subsets of the";
            St[5]="cylinder intersect in just one point.  If you push";
            St[6]="this button you will see a plot of the two subsets,";
            St[7]="one red and one blue.  Incidentally, the phrase";
            St[8]="'show me the money' means";
            St[9]="'come right away to the central point'.";
            St[10]="You can see the plot in the HYP and PLAN";
            St[11]="windows by turning these windows on.  (Use the";
            St[12]="blue on/off button.)  The HYP2 and PLAN2";
            St[13]="coords are better adapted to the picture but the";
            St[14]="HYP1 and PLAN1 coords require less computation.";
        }


        if(MESSAGE==15) {
            St[2]="This is the part of object selector 1 ";
            St[3]="which selects the C-circle E0.";
            St[4]="This object is only plotted in HYP1 coords.";
            St[5]="Click on this object to select it for plotting.";
            St[6]="Note that there is a button for E0 also on";
            St[7]="the object selector 2.  This comes about";
            St[8]="because E0 is naturally involved in the";
            St[9]="definitions of both S1 and S2.";
            St[10]="If you select E0 on both selectors, the right";
            St[11]="one is plotted on top of the left one";
        }

        if(MESSAGE==16) {
            St[2]="This is the part of object selector 1 ";
            St[3]="which selects the object NS10.";
        }

        if(MESSAGE==17) {
            St[2]="This is the part of object selector 1 ";
            St[3]="which selects the object S10.";
            St[10]="You can select the whole object at once";
            St[11]="by clicking on the S10 button.";
        }

        if(MESSAGE==18) {
            St[2]="This is the part of object selector 1 ";
            St[3]="which selects the object SS10.";
        }


        if(MESSAGE==19) {
            St[2]="This is the part of object selector 1 ";
            St[3]="which selects the C-circle C1.";
            St[5]="Click on this object to select it for plotting.";
        }

        if(MESSAGE==20) {
            St[2]="This is the part of object selector 1 ";
            St[3]="which selects the object NS21.";
        }


        if(MESSAGE==21) {
            St[2]="This is the part of object selector 1 ";
            St[3]="which selects the object S21.";
        }


        if(MESSAGE==22) {
            St[2]="This is the part of object selector 1 ";
            St[3]="which selects the object SS21.";
        }

        if(((MESSAGE>=16)&&(MESSAGE<=18))||((MESSAGE>=20)&&(MESSAGE<=22))) {
            St[5]="This object is an infinite union of arcs,";
            St[6]="one per point of C1. The applet lets you";
            St[7]="select 30 of these arcs.  Click the mouse";
            St[8]="to select the arcs individually.  Drag the";
            St[9]="mouse to select the arcs more rapidly.";
            St[10]="N stands for 'North' and S stands for 'South'";
        }


        if((MESSAGE==17)||(MESSAGE==21)) {
            St[10]="We have broken this object up as";
            St[11]="S(E,Q;C)=SA u. SB.  For instance:";
            St[12]="S10=S10A u. S10B.";
            St[13]="You can select all 15 arcs at once";
            St[14]="by clicking on the SA and SB buttons.";
        }






        if(MESSAGE==23) {
            St[2]="This is the part of object selector 1 ";
            St[3]="which selects the C-circle E2. ";
            St[5]="Click on this object to select it for plotting.";
        }

        if(MESSAGE==24) {
            St[2]="This is the part of object selector 1 ";
            St[3]="which selects the C-arc Q0. ";
            St[4]="This object is only plotted in HYP1 coords.";
            St[5]="Click on this object to select it for plotting.";
            St[6]="Note that there is a button for Q0 also on";
            St[7]="the object selector 2.  This comes about";
            St[8]="because Q0 is naturally involved in the";
            St[9]="definitions of both S1 and S2.";
            St[10]="If you select Q0 on both selectors, the right";
            St[11]="one is plotted on top of the left one";
        }

        if(MESSAGE==25) {

            St[2]="This is the part of object selector 1 ";
            St[3]="which selects the C-arc Q2.  ";
            St[5]="Click on this object to select it for plotting.";
        }


        if(MESSAGE==26) {
            St[2]="This is the control button which controls the";
            St[3]="mode of the truncate arcs bar.  In one mode";
            St[4]="you need to push the plot button when you";
            St[5]="want to replot the image.  In the other modes";
            St[6]="the picture is replotted automatically.";
        }


        if(MESSAGE==27) {
            St[2]="This is the control button which controls the";
            St[3]="mode of the parameter selector.  In one mode";
            St[4]="you need to push the plot button when you";
            St[5]="want to replot the image.  In the other modes";
            St[6]="the picture is replotted automatically";
        }

        if(MESSAGE==28) {
            St[2]="This is the part of object selector 2 ";
            St[3]="which selects the C-circle E0. ";
            St[4]="This object is only plotted in HYP1 coords.";
            St[5]="Click on this object to select it for plotting.";
            St[6]="Note that there is a button for E0 also on";
            St[7]="the object selector 1.  This comes about";
            St[8]="because E0 is naturally involved in the";
            St[9]="definitions of both S1 and S2.";
        }

        if(MESSAGE==29) {
            St[2]="This is the part of object selector 2 ";
            St[3]="which selects the object NS20.";
        }


        if(MESSAGE==30) {
            St[2]="This is the part of object selector 2 ";
            St[3]="which selects the object S20.";
            St[10]="You can select the whole object at once";
            St[11]="by clicking on the S20 button.";
        }

        if(MESSAGE==31) {
            St[2]="This is the part of object selector 2 ";
            St[3]="which selects the object SS20.";
        }

        if(MESSAGE==32) {
            St[2]="This is the part of object selector 2 ";
            St[3]="which selects the C-circle C2.  ";
            St[5]="Click on this object to select it for plotting.";
        }

        if(MESSAGE==33) {
            St[2]="This is the part of object selector 2 ";
            St[3]="which selects the object NS21.";
        }

        if(MESSAGE==34) {
            St[2]="This is the part of object selector 2 ";
            St[3]="which selects the object S21.";
            St[10]="You can select the whole object at once";
            St[11]="by clicking on the S21 button.";
        }

        if(MESSAGE==35) {
            St[2]="This is the part of object selector 2 ";
            St[3]="which selects the object SS21.";
        }

        if(((MESSAGE>=29)&&(MESSAGE<=31))||((MESSAGE>=33)&&(MESSAGE<=35))) {
            St[5]="This object is an infinite union of arcs,";
            St[6]="one per point of C2. The applet lets you";
            St[7]="select 30 of these arcs.  Click the mouse";
            St[8]="to select the arcs individually.  Drag the";
            St[9]="mouse to select the arcs more rapidly.";
            St[10]="N stands for 'North' and S stands for 'South'";
        }


        if((MESSAGE==30)||(MESSAGE==34)) {
            St[10]="We have broken this object up as";
            St[11]="S(E,Q;C)=SA u. SB. For instance:";
            St[12]="S20=S20A u. S20B.";
            St[13]="You can select all 15 arcs at once";
            St[14]="by clicking on the SA and SB buttons.";
        }





        if(MESSAGE==36) {

            St[2]="This is the part of object selector 2 ";
            St[3]="which selects the C-circle E1. ";
            St[5]="Click on this object to select it for plotting.";
        }


        if(MESSAGE==37) {

            St[2]="This is the part of object selector 2 ";
            St[3]="which selects the C-arc Q0. ";
            St[4]="This object is only plotted in HYP1 coords.";
            St[5]="Click on this object to select it for plotting.";
            St[6]="Note that there is a button for Q0 also on";
            St[7]="the object selector 1.  This comes about";
            St[8]="because Q0 is naturally involved in the";
            St[9]="definitions of both S1 and S2.";
            St[10]="If you select Q0 on both selectors, the right";
            St[11]="one is plotted on top of the left one";
        }

        if(MESSAGE==38) {

            St[2]="This is the part of object selector 2 ";
            St[3]="which selects the C-arc Q1.  ";
            St[5]="Click on this object to select it for plotting.";
        }

        if((MESSAGE==1)||((MESSAGE>=7)&&(MESSAGE<=39))) {
            St[15]="Press the 'objects' and 'coords'";
            St[16]="buttons for info about the objects and coords.";
        }

        if((MESSAGE>=84)&&(MESSAGE<=86)) {
            St[5]="Click on this object to select it for plotting.";
            St[15]="Press the 'objects' and 'coords'";
            St[16]="buttons for info about the objects and coords.";
        }

        if((MESSAGE>=97)&&(MESSAGE<=99)) {
            St[5]="Click on this object to select it for plotting.";
            St[15]="Press the 'objects' and 'coords'";
            St[16]="buttons for info about the objects and coords.";
        }

        if(MESSAGE==43) {
            St[2]="Click on this slider to change the scale factor.";

        }

        if(MESSAGE==44) {
            St[2]="This button toggles between 2 different but";
            St[3]="related coordinate systems:";
            St[4]="ELEV1 and ELEV2 in the ELEV window";
            St[5]="PLAN1 and PLAN2 in the PLAN window";
            St[6]="HYP1 and HYP2 in the HYP window";
            St[7]="To learn about these coordinate systems,";
            St[8]="click on the 'coords' button.";
        }


        if(MESSAGE==45) {
            St[2]="This button resets the scaling, translation";
            St[3]="and aspect ratio factors to their";
            St[4]="initial settings.";
        }

        if(MESSAGE==46) {
            St[2]="Click on this slider to change the";
            St[3]="aspect ratio by rescaling the x-axis.";

        }

        if(MESSAGE==47) {
            St[2]="This button turns the plotting window on";
            St[3]="and off.  If you are interested in one of";
            St[4]="plot windows but not the other two, you";
            St[5]="can turn the other ones off, and thereby";
            St[6]="get a faster plot in the window of interest.";
        }

        if(MESSAGE==50) {
            for(int i=1;i<=21;++i) {
                St[i]="";
            }
            St[2]="COMPUTING. PLEASE WAIT...";
            St[4]="It is best not to click the mouse while";
            St[5]="a computation is going on.";
            St[6]="To test if the computation is done, move";
            St[7]="the mouse around over a wide range of";
            St[8]="the applet.  This MESSAGE will disappear";
            St[9]="(and be replaced by a different MESSAGE)";
            St[10]="when the computation is done.";
        }

        if(MESSAGE==55) {
            St[2]="Push this button to take the tutorial";
            St[3]="The tutorial explains how you use";
            St[4]="the applet.  After you push the button,";
            St[5]="you will see the tutorial in the text window.";
        }

        if(MESSAGE==56) {
            St[2]="Push this button to learn about the objects";
            St[3]="which the applet plots.  After you push this";
            St[4]="button you will see the information in the text.";
            St[5]="window.";
        }



        if(MESSAGE==57) {
            St[2]="Push this button to learn about the";
            St[3]="coordinate systems in which the objects";
            St[4]="are plotted.  After you push the button";
            St[5]="you will see the information in the text";
            St[6]="window.";
        }

        if(MESSAGE==70) {
            St[2]="This cluster of 4 buttons controls";
            St[3]="The plotting detail.  When the button";
            St[4]="N is activated, each tall thin rectangle";
            St[5]="on the object selector stands for N arcs.";
            St[6]="Here N=1,2,4,8.  If you are working on a";
            St[7]="slow computer, it is good practice to";
            St[8]="first make the plots with N=1 and only";
            St[9]="increase N if the plot for N=1 is done";
            St[10]="quickly.  If you set N large and don't";
            St[11]="check the plot in advance you could be";
            St[12]="in for a long delay.";
        }

        if(MESSAGE==71) {
            St[2]="This button resets the 'truncate arcs' slider";
            St[3]="to the rightmost position.  In this position";
            St[4]="the arcs are completely plotted.";
        }

        if(MESSAGE==84) {
            St[2]="This is the part of object selector 1 ";
            St[3]="Which selects the auxilliary object HQ0";
            St[4]="This object is only plotted in HYP coordinates.";
            St[5]="HQ0 can also be selected from object selector 2.";
        }

        if(MESSAGE==85) {
            St[2]="This is the part of object selector 1 ";
            St[3]="Which selects the R-axis R1";
            St[4]="This object is not plotted in ELEV coordinates.";
        }
        if(MESSAGE==86) {
            St[2]="This is the part of object selector 1 ";
            St[3]="Which selects the C-arc Q2#";
        }

        if(MESSAGE==99) {
            St[2]="This is the part of object selector 2 ";
            St[3]="Which selects the C-arc Q1#";
        }
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {
        MESSAGE=0;
        if(ACTIVE==1) repaint();
    }
    public void mouseExited(MouseEvent e) {
        MESSAGE=-1;
        if(ACTIVE==1) repaint();
    }

    public void mouseClicked(MouseEvent e) {
        ACTIVE=1-ACTIVE;
        repaint();
    }
}
