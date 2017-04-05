package edu.jalc.game;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class Display extends JPanel
   {
   public final int XSIZE;
   public final int YSIZE;
   private final Game game;
   
   public Display(Game game, int x, int y){
     this.game = game;
     this.XSIZE = x;
     this.YSIZE = y;
   }
   
   public Game getGame(){return this.game;}
   
   @Override
   public void paintComponent(Graphics g)
      {
      super.paintComponent(g);
      show(g);
      game.player().show(g);
      for(Character guy: game.getGuys())
         {
         guy.show(g);
         }
      }
   public void build()
      {
      Random random = new Random();
      String icon;
      int[][] terrain = new int[XSIZE][YSIZE];
      ImageIcon[][] icons = new ImageIcon[XSIZE][YSIZE];
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
            default: icon = "edu/jalc/game/images/jBlack.png"; break;
            }
            icons[row][col] = new ImageIcon("edu/jalc/game/images/" + icon);
            }
         }
         game.setIcons(icons);
         game.setTerrain(terrain);
      }
      public void show(Graphics g)
      {
      int xStart = game.player().xP()/50 - 6;
      int yStart = game.player().yP()/50 - 6;
      int xShift = game.player().xP()%50-12;
      int yShift = game.player().yP()%50-12;
      
      for(int i = -1; i < 14; i++)//this would be 0-13, the extra numbers prevent white on the borders
         {
         for(int j = -1; j < 14; j++)
            {
            try
               {
               game.icons()[xStart+i][yStart+j].paintIcon(this,g,i*50-xShift,j*50-yShift);
               }
            catch(ArrayIndexOutOfBoundsException e)
               {
               ImageIcon icon = new ImageIcon("edu/jalc/game/images/jBlack.png");
               icon.paintIcon(this,g,i*50-xShift,j*50-yShift);
               }
            }
         }
      }
   }