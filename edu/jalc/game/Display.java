package edu.jalc.game;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class Display extends JPanel
   {
   public final int XSIZE = 25;
   public final int YSIZE = 25;
   @Override
   public void paintComponent(Graphics g)
      {
      super.paintComponent(g);
      show(g);
      player.show(g);
      for(Character guy: guys2)
         {
         guy.show(g);
         }
      }
   public void build()
      {
      String icon;
      terrain = new int[XSIZE][YSIZE];
      icons = new ImageIcon[XSIZE][YSIZE];
      for(int row = 0; row < XSIZE; row++)
         {
         for(int col = 0; col < YSIZE; col++)
            {
            int val = random.nextInt(3)+random.nextInt(3)+random.nextInt(3);
            terrain[row][col] = val;
            switch(val)
            {
            case 0: icon = "jRocks.png"; break;//1
            case 1: icon = "jRocks.png"; break;//3
            case 2: icon = "jGrass.png"; break;//6
            case 3: icon = "jWater.png"; break;//7
            case 4: icon = "jGrass.png"; break;//6
            case 5: icon = "jPond.png"; break;//3
            case 6: icon = "jCave.png"; break;//1
            default: icon = "edu.jalc/game/images/jBlack.png"; break;
            }
            icons[row][col] = new ImageIcon("edu.jalc/game/images/" + icon);
            }
         }
      }
      public void show(Graphics g)
      {
      int xStart = player.xP/50 - 6;
      int yStart = player.yP/50 - 6;
      int xShift = player.xP%50-12;
      int yShift = player.yP%50-12;
      
      for(int i = -1; i < 14; i++)//this would be 0-13, the extra numbers prevent white on the borders
         {
         for(int j = -1; j < 14; j++)
            {
            try
               {
               icons[xStart+i][yStart+j].paintIcon(this,g,i*50-xShift,j*50-yShift);
               }
            catch(ArrayIndexOutOfBoundsException e)
               {
               ImageIcon icon = new ImageIcon("jBlack.png");
               icon.paintIcon(this,g,i*50-xShift,j*50-yShift);
               }
            }
         }
      }
   }