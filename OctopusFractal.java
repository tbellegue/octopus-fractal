import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;

import javax.swing.JFrame;

public class OctopusFractal extends JFrame {
	
   private static int level;   // stores current level of fractal
   // set the initial fractal level to the value specified
   // and set up JFrame specifications
   
   public OctopusFractal ( int currentLevel ) {
      level = currentLevel; // set initial fractal level
      setBounds(250, 250, 800, 600);  
  	  setResizable(false); // false implies cannot resize the structure developed
  	  setDefaultCloseOperation(EXIT_ON_CLOSE);
   }

   // draw fractal recursively
   public void drawFractal( int level, int xA, int yA, int xB, 
      int yB, Graphics g ) {
      // base case: draw a line connecting two given points
      if ( level == 0 ) 
         g.drawLine( xA, yA, xB, yB );
      //general cases; recursion step: determine new points, draw next level
      else 
      {	
         // calculate midpoint between (xA, yA) and (xB, yB)
         int xC = ( xA + xB ) / 2;
         int yC = ( yA + yB ) / 2;

         int xD = xA + ( xC - xA ) / 2 - ( yC - yA ) / 2;
         int yD = yA + ( yC - yA ) / 2 + ( xC - xA ) / 2;
         
         // recursively draw the Fractal
         drawFractal( level - 1, xD, yD, xA, yA, g );
         drawFractal( level - 1, xD, yD, xB, yB, g );
         drawFractal( level - 1, xB, yB, xC, yC, g );  
      } // end else
   }
   
   //unclosed line segments have a rounded cap, and where the line segments join is also rounded.
   public void lineStyle (Graphics g) {
	   Graphics2D g2 = (Graphics2D) g;
	   
	   BasicStroke bs = new BasicStroke(1.3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
	   g2.setStroke(bs);
   }
   
   //create a 2 color gradient. the x & y start & end coordinates also determines
   //the angle of the gradient. 
   public void gradient(Graphics g) {
	   Graphics2D g2d = (Graphics2D) g;
	   //I can't decide which colors to use to turn this in!!!!
	   Color startColor = Color.magenta;
	   Color endColor = Color.cyan;

	   int startX = 150, startY = 10, endX = 650, endY = 40;
	      
	   GradientPaint gradient = new GradientPaint(startX, startY, 
			   startColor, endX, endY, endColor);
	   g2d.setPaint(gradient); 
   }
   
   // start the drawing of fractal
   public void paint( Graphics g ) {
      super.paint( g );

      // draw fractal pattern with rounded caps & joins & a color gradient.
      lineStyle(g);
      gradient(g);
      drawFractal( level, 20, 0, 750, 250, g );
   
   }
   
   // set the new level of recursion
   public void setLevel( int currentLevel ) {
      level = currentLevel;
   }

   // returns level of recursion 
   public int getLevel() {
      return level;
   }
   
public static void main(String[] args) {
	
	new OctopusFractal(10).setVisible(true);	
	}
}