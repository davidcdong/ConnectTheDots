
/**********************************************************************
* to connect dots...
* Author: David C. Dong, Hing Nian Chan
* id:7595
* E-mail Address: ddong@sparky.ic.sunysb.edu
* Project 
* Date Due: May 8,2001.
**********************************************************************/import
javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class ConnectDots extends JApplet 
  implements MouseListener,
	ActionListener,KeyListener,MouseMotionListener
{  
  
  private Vector pt=new Vector();
  private Vector redPt=new Vector();
  private Vector setOfPts=new Vector();
  
  JTextField text; 
  private boolean newPoint=true,clearC=false,shiftTest=false;
  private boolean connect=false,samePt=false, unHighLight = false;
  private boolean clearC2=true,clickNoShift=false,notClicked=true;
  private boolean isSomethingThere=false,c1=false,highlight=true,clear=false;
  private int count=0,ptI=0,i=0;
  private int index,mouseR=200;
  
  
  public void init()
    {  
      
      Container contentPane=getContentPane();
      contentPane.setLayout(new BorderLayout());
      JPanel p1=new JPanel();
      p1.setBackground(Color.white);
      JButton a=new JButton("Unhighlight");
      a.setBackground(Color.gray);
      a.addActionListener(this);
      JButton b=new JButton("Connect Selected Dots");
      b.setBackground(Color.gray);
      b.addActionListener(this);
      JButton c=new JButton("Clear Connections");
      c.setBackground(Color.gray);
      c.addActionListener(this);
      JButton d=new JButton("Clear Dots");
      d.setBackground(Color.gray);
      d.addActionListener(this);
      text=new JTextField(5);
      text.setBackground(Color.white);
      text.setEditable(false);
      
      p1.add(a);
      p1.add(b);
      p1.add(c);
      p1.add(d);
      p1.add(text);
      contentPane.add(p1,BorderLayout.NORTH);
      JPanel p2=new JPanel();
      p2.addMouseListener(this);
      p2.setBackground(Color.black);   
      contentPane.add(p2,BorderLayout.CENTER);
      addMouseMotionListener(this);
      addKeyListener(this);
         
    }
  
  //For each dot, draw the dot, and then draw a line from
  //it to the previous dot.
  
  public void paint(Graphics g)
    {
      int nextX=0,nextY=0;
      super.paint(g); //ensure the method that will do correctly    
    
  //create the color of dots and dots.
      for(int i=0;i<pt.size();i++)
	{
	  nextX=(int)(((Point)( pt.elementAt(i))).getX());
	  nextY=(int)(((Point)( pt.elementAt(i))).getY());
	  if( (((Point)(pt.elementAt(i))).getShiftTest())==false)
	    g.setColor(Color.white);
	  else 
	    g.setColor(Color.red);
	  
	  g.fillOval(nextX-5,nextY+33,10,10);
	  g.drawString(Integer.toString(i),nextX+6,nextY+48);
	  
        }
      //create random color for the lines

      Random rand=new Random();
      Color randColor;	
      int randNum=Math.abs(rand.nextInt()%3);     
      if(randNum==0)          
	randColor=new Color(100,255,0);     
      else if(randNum==1)          
	randColor=new Color(100,244,16);
      else   
	randColor=Color.red;

//if a position is clicked without pressed connect button, this will run
//and draw the old lines.

      if(connect)
	{
		if(highlight)
		{
		for(int i=0;i<pt.size();i++)
		 {
                 Point p=((Point)pt.elementAt(i));
                 if(p.getShiftTest())
	    		redPt.addElement(p);
		 }

            for(int i=0;i<(redPt.size()-1);i++)
	    {
		Point p=((Point)redPt.elementAt(i));
		
              int xC=((Point)redPt.elementAt(i)).getX();
	      int yC=((Point)redPt.elementAt(i)).getY();
	    
	      int x1=(((Point)redPt.elementAt(i+1))).getX();
	      int y1=(((Point)redPt.elementAt(i+1))).getY();     
	 
      		g.setColor(Color.blue); 
    
	g.drawLine(xC-2,yC+38,x1-2,y1+38);	
	     c1=true; 
	    }
	if (c1)
	{
 	  int x0=((((Point)redPt.elementAt(0))).getX());
          int y0=((((Point)redPt.elementAt(0))).getY());
         int xF=(((Point)redPt.elementAt(redPt.size()-1)).getX());
 	 int yF=(((Point)redPt.elementAt(redPt.size()-1)).getY());

		g.setColor(Color.blue);
          g.drawLine(x0-2,y0+38,xF-2,yF+38);
        }
		highlight=false;
    }//end unhighlight
}//end connect 	
	if(setOfPts.size()>1 && !clear)
	{	
	for(int set=1; set < setOfPts.size(); set++)
	    {
       for(int b=0; b< (((Vector)(setOfPts.elementAt(set))).size())-1; b++)
                 {
		int x1=((Point)((Vector)setOfPts.elementAt(set)).elementAt(b)).getX();
		int y1=((Point)((Vector)setOfPts.elementAt(set)).elementAt(b)).getY();
		int x2=((Point)((Vector)setOfPts.elementAt(set)).elementAt(b+1)).getX();
		int y2=((Point)((Vector)setOfPts.elementAt(set)).elementAt(b+1)).getY();
	           g.setColor(Color.red);
                   g.drawLine(x1-2,y1+38,x2-2,y2+38);
	         }
		int last=((Vector)setOfPts.elementAt(set)).size()-1;
		int xL=((Point)((Vector)setOfPts.elementAt(set)).elementAt(0)).getX();
		int yL=((Point)((Vector)setOfPts.elementAt(set)).elementAt(0)).getY();
		int xL1=((Point)((Vector)setOfPts.elementAt(set)).elementAt(last)).getX();
		int yL1=((Point)((Vector)setOfPts.elementAt(set)).elementAt(last)).getY();
		g.setColor(Color.red);
		g.drawLine(xL-2,yL+38,xL1-2,yL1+38);  
	 	
	       }
	}
    
}//end paint
public void actionPerformed(ActionEvent e)
	{
      String action=e.getActionCommand();
      if(action.equals("Unhighlight"))
	{
		for(int i=0;i<pt.size();i++)
		{
		 ((Point)pt.elementAt(i)).setShiftTest(false);
		}
		highlight=false;

		repaint();
	}
      else if(action.equals("Connect Selected Dots"))
	{
	  connect=true;
	  
        setOfPts.addElement(new Vector());

         for ( int set=0; set < pt.size(); set++)
           {
            if(  ((Point)(pt.elementAt(set))).getShiftTest() )
               {
              ((Vector)(setOfPts.elementAt(setOfPts.size()-1))).addElement(
                          pt.elementAt(set));
               }
           }
	  clear=false;
	  repaint();
          
	}
      else if(action.equals("Clear Connections"))
	{
	  connect=false;
	  clear=true;
	  repaint();
	}
      else if(action.equals("Clear Dots"))
	{
	 	 notClicked=true;
               pt.removeAllElements();
		for(int i=0;i<setOfPts.size();i++)
		  ((Vector)setOfPts.elementAt(i)).removeAllElements();
		redPt.removeAllElements();
		
  Vector pt=new Vector();
  Vector redPt=new Vector();
  Vector setOfPts=new Vector();
  
  
  boolean newPoint=true,clearC=false,shiftTest=false;
  boolean connect=false,samePt=false, unHighLight = false;
  boolean clearC2=true,clickNoShift=false,notClicked=true;
  boolean isSomethingThere=false,c1=false,highlight=true,clear=false;
  int count=0,ptI=0,i=0;
  int index,mouseR=200;

                             
	  
	  repaint();
	}
      
    }
  
  public void mouseDragged(MouseEvent e)
  {
	       for(int i=0;i<pt.size();i++)
           {
          int xA=((int)((Point)(pt.elementAt(i))).getX());
          int yA=((int)((Point)(pt.elementAt(i))).getY());
          int yE=e.getY();
          int xE=e.getX();
            if(Math.sqrt(Math.pow(xE-xA,2.0)+Math.pow(yE-yA,2.0))<=5.0)
              {
                 updateSize(e,i);
                 mouseR=i;
              }
         }
		connect=true;
                repaint();
  }
 
  public void mouseMoved(MouseEvent e)
  {

  }

  public void keyTyped(KeyEvent e)
    {

    }

  public void keyReleased(KeyEvent e)
    {
      System.out.println("Key Released");
      shiftTest=false;
      text.setText("");
      //repaint();
      
    }
  public void keyPressed(KeyEvent e)
    {
      
      
      if(e.isShiftDown())
	{
	  
	  text.setText("shift");
	  //repaint();
	}
      
      
    }
   

  private void updateSize(MouseEvent e,int a)
    {
                   (((Point)(pt.elementAt(a))).setX(e.getX()));
                   (((Point)(pt.elementAt(a))).setY(e.getY()));


   }
  //When the mouse is clicked, write the coordinates to the console
  //window and push them (wrapped as objects) onto the dots
    //stack.
  
  public void mouseClicked(MouseEvent event)
    { 
         if( notClicked )
        {
        setOfPts.addElement( pt );
        notClicked = false;
        }    

          int yE=event.getY();
          int xE=event.getX();
      System.out.println("Mouse clicked. x = " 
			 + event.getX() + " y = " + event.getY());
      Point a=new Point(event.getX(),event.getY());
      pt.addElement(a);
		
   
      
      if(event.isShiftDown())
	 {
	   if(pt.size()<=1)
                ((Point)pt.elementAt(i)).setShiftTest(true);

           else
           {
          for(int i=0;i<pt.size()-1;i++)
              {
          int xA=(((Point)(pt.elementAt(i))).getX());
          int yA=(((Point)(pt.elementAt(i))).getY());
        
            if(Math.sqrt(Math.pow(xE-xA,2.0)+Math.pow(yE-yA,2.0))<=5.0)
                {
                pt.removeElementAt(pt.size()-1);
                if(((Point)(pt.elementAt(i))).getShiftTest())
                 
                  (((Point)(pt.elementAt(i))).setShiftTest(false));
                 
                else
		 
                  (((Point)(pt.elementAt(i))).setShiftTest(true));
                 
		 break;
		}
	    else
		newPoint=true; 
		  
	       }//end for loop	

          if(newPoint)
              {

             ( (((Point) (pt.elementAt(pt.size()-1) )).setShiftTest(true)));
		newPoint=false;
              }
          
          }//end else pt>1
         } //end isSHiftDown

       if(!(event.isShiftDown()))
                {

                for(int i=0;i<pt.size();i++)
                      {

               (((Point)(pt.elementAt(i) ) ).setShiftTest(false));

                      }
                }
      
	connect=false;	
	highlight=false;		 
        repaint();
    }
 
  
  
  //When the mouse enters the frame, write the coordinates to the
  //console window.

    public void mouseEntered(MouseEvent event)
    {  
     //	System.out.println("Mouse entered. x = " 
     //             + event.getX() + " y = " + event.getY());
    }

    //When the mouse exits the frame, write the coordinates to the
    //console window.
        
    public void mouseExited(MouseEvent event)
    {  
      //  System.out.println("Mouse exited. x = " 
      //       + event.getX() + " y = " + event.getY());
	
    }

    //When the mouse is pressed, write the coordinates to the
    //console window.

    public void mousePressed(MouseEvent event)
    { 
        System.out.println("Mouse pressed. x = " 
             + event.getX() + " y = " + event.getY());
	
          for(int i=0;i<pt.size();i++)
           {
          int xA=((int)((Point)(pt.elementAt(i))).getX());
          int yA=((int)((Point)(pt.elementAt(i))).getY());
          int yE=event.getY();
          int xE=event.getX();
            if(Math.sqrt(Math.pow(xE-xA,2.0)+Math.pow(yE-yA,2.0))<=5.0)
                mouseDragged(event);
          }


	
    }

    //When the mouse is released, write the coordinates to the
    //console window.

    public void mouseReleased(MouseEvent event)

    {  
	
	//mouseR come from mouse dragged

	if(mouseR!=200)
	{
          int yA=((int)((Point)(pt.elementAt(i))).getY());
          int yE=event.getY();
          int xE=event.getX();
         ( ((Point)pt.elementAt(mouseR)).setX(event.getX()));
         ( ((Point)pt.elementAt(mouseR)).setY(event.getY()));
                mouseR=200;  
	}
        repaint();
    }
   
}

