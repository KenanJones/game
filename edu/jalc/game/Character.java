package edu.jalc.game;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
 
 public class Character
   {
   private Random random = new Random();
   
   private int xP;
   private int yP;
   private int xSize;
   private int ySize;
   private Color type;
   private double life;
   private int speed;
   
   public Character(Display board)
      {
      xSize = 26;
      ySize = 26;
      xP = random.nextInt(board.XSIZE)*50+(50-xSize)/2;
      yP = random.nextInt(board.YSIZE)*50+(50-ySize)/2;
      type = Color.green;
      speed = 2;
      }
   public Character(Display board, Color color)
      {
      xSize = 26;
      ySize = 26;
      xP = random.nextInt(board.XSIZE)*50+(50-xSize)/2;
      yP = random.nextInt(board.YSIZE)*50+(50-ySize)/2;
      type = color;
      if(color == Color.red) speed = 3;
      else if(color == Color.green) speed = 2;
      else speed = 1;
      }
   
   public void move()
      {
      int xMove = random.nextInt(speed + 1);
      int yMove = speed - xMove;
      if(player().xP > xP)
         {xP += xMove*5;}
      else{xP -= xMove*5;}
      if(player().yP > yP)
         {yP += yMove*5;}
      else{yP -= yMove*5;}
      }
   
   public void show(Graphics g)
      {
      move();
      int x = xP - player.xP + player.XP-12;
      int y = yP - player.yP + player.YP-12;
      Graphics2D g2 = (Graphics2D) g;
      g2.setStroke(new BasicStroke(1));
      
      g2.setColor(type);
      g2.drawOval(15+x,10+y,20,20);//the body
      g2.drawRect(15+x,20+y,20,15);
      g2.fillOval(15+x,10+y,20,20);
      g2.fillRect(15+x,20+y,20,15);
      g2.setColor(Color.white);//the eyes
      g2.fillOval(18+x,20+y,6,5);
      g2.fillOval(27+x,20+y,6,5);
      g2.setColor(Color.black);
      g2.drawOval(18+x,20+y,6,5);//the outlines
      g2.drawOval(27+x,20+y,6,5);
      g2.drawLine(22+x,30+y,29+x,30+y);
      }
   }