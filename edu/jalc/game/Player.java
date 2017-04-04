package edu.jalc.game;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class Player
   {
   private final Game game;
   private Random random = new Random();
   private int xP;//position on display in pixels
   private int yP;//position on display in pixels
   private final int XP = 312;//the position on the screen in pixels
   private final int YP = 312;//the position on the screen in pixels
   private double life;
   private int direction;
   private int[] area; //the weapon effect area
   private Rectangle rect; //the weapon affect area
   private String[] tools;
   private String weapon;
   
   public Player(Game game, int xVal, int yVal)
   {
   this.game = game;
   xP = xVal*50 + 12;
   yP = yVal*50 + 12;
   area = new int[4];
   tools = new String[]{"torch","ladder","boat","potion"};
   weapon = "gun";
   }
   
   public void move(int[] key){
      if(key[0] > 0)
         {
         key[0] -= 1;
         xP -= 10;
         direction = 37;
         }
      if(key[1] > 0)
         {
         key[1] -= 1;
         yP -= 10;
         direction = 38;
         }
      if(key[2] > 0)
         {
         key[2] -= 1;
         xP += 10;
         direction = 39;
         }
      if(key[3] > 0)
         {
         key[3] -= 1;
         yP += 10;
         direction = 40;
         }
      badMove(key);
   }
   public void badMove(int[] key)
   {
   boolean test = true;
   while(test)
      {
      if(xP < 0) {xP += 20; key[0] = 0;}
      else if(xP > game.board().XSIZE*50-26) {xP -= 20; key[2] = 0;}
      else if(yP < 0) {yP += 20; key[1] = 0;}
      else if(yP > game.board().YSIZE*50-26) {yP -= 20; key[3] = 0;}
      else test = false;
      }
   }
      
   public void teleport()
   {
   int x = random.nextInt(game.board().XSIZE);
   int y = random.nextInt(game.board().YSIZE);
   xP = x*50 + 12;
   yP = y*50 + 12;
   }
   
   public void show(Graphics g)
      {
      Graphics2D g2 = (Graphics2D) g;
      
      move(game.key());
      if(game.getAction() == "attack")
         showWeapon(g2);
      
      g2.setColor(new Color(250,220,180));
         //the face
      g2.drawOval(8+XP,1+YP,8,6);
      g2.fillOval(8+XP,1+YP,8,6);
      g2.setColor(Color.blue);
      g2.drawLine(10+XP,3+XP,10+XP,3+XP);
      g2.drawLine(14+XP,3+XP,14+XP,3+XP);
      g2.setColor(new Color(130,60,30));
         //the hair
      int[] points = new int[]{8,2,10,0,8,3,11,0,14,0,16,2,13,0,16,3,12,0,12,0};
      for(int i = 0; i < points.length; i++)
         {
         g2.drawLine(points[i]+XP,points[++i]+YP,points[++i]+XP,points[++i]+YP);
         }
      g2.setColor(new Color(160,130,90));
      g2.drawLine(12+XP,4+YP,12+XP,4+YP);
         //the body
      g2.fillOval(9+XP,9+YP,9,9);
      g2.setStroke(new BasicStroke(3,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
      int [] xPoints = new int[]{1,1,3,8,9,5,4,5,14,15,18,20,18,15,14,18,24,17,16,9};
      int [] yPoints = new int[]{5,9,11,11,19,19,25,19,19,22,23,25,23,22,19,10,17,10,9,9};
      for(int i = 1; i < xPoints.length; i++)
         {
         g2.drawLine(xPoints[i-1]+XP,yPoints[i-1]+YP,xPoints[i]+XP,yPoints[i]+YP);
         }
      }
   
   public void showWeapon(Graphics2D g2)
      {
      game.setAction("none");
      int[] range = getArea();
      
      if(weapon == "sword")
         {
         g2.setColor(Color.white);
         g2.drawLine(XP,YP,XP-range[0],YP);
         g2.drawLine(XP,YP,XP,YP-range[1]);
         g2.drawLine(XP,YP,XP+range[0],YP);
         g2.drawLine(XP,YP,XP,YP+range[1]);
         }
      if(weapon == "gun")
         {
         g2.setColor(Color.white);
         g2.drawLine(XP,YP,XP+range[0],YP+range[1]);
         }
      }
   
   public boolean has(String... neededTools)
      {
      boolean returned = true;
      boolean hasTool = false;
      for(String tool: neededTools)
         {
         for(String toolHad: tools)
            {
            if(tool.equals(toolHad))
            hasTool = true;
            }
         if(hasTool == false)
         returned = false;
         }
      return returned;
      }
   
   public int[] getArea()
      {
      int[] a;
      int xD = direction==37?-1:direction==39?1:0;
      int yD = direction==38?-1:direction==40?1:0;
      switch(weapon)
         {
         case "sword": a = new int[]{30,30};
            //area = new int[]{xP-40,yP-40,80,80};
            rect = new Rectangle(xP+30*xD, yP+30*yD, 30, 30); break;
         case "gun": a = new int[]{300*xD, 300*yD};
            //area = new int[]{xP-5,yP-5,200*xD+10,200*yD+10};
            rect = new Rectangle(xD<0?xP-300:xP+10*xD-10,
               yD<0?yP-300:yP+10*yD-10, xD!=0?300:20, yD!=0?300:20); break;
         default: a = new int[]{10,10}; break;
         }
      return a;
      }
   public int xP(){return this.xP;}
   public int yP(){return this.yP;}
   }