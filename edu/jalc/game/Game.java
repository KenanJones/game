package edu.jalc.game;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.Timer; //tells the computer to use the swing timer

public class Game implements KeyListener, ActionListener
{
private int[][] terrain;
private ImageIcon[][] icons;
private int[] key;

private static JFrame frame;
private static JTextField field;
private Display board;

private Player player;
private ArrayList<Character> guys = new ArrayList<Character>();

private Random random;
private Timer clock;
public String action;
   
   public Game()
   {
   action = "none";
   key = new int[4];
   random = new Random();
   clock = new Timer(100,this);
   frame = new JFrame();
   frame.setSize(666,689);
   frame.setLocation(320,90);
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   player = new Player(this,6,6);
   buildPanel();
   frame.setVisible(true);
   guys.add(new Character(board, Color.green));
   guys.add(new Character(board, Color.green));
   guys.add(new Character(board, Color.green));
   guys.add(new Character(board, Color.red));
   guys.add(new Character(board, Color.blue));
   clock.start();
   }
   
   public void buildPanel()
   {
   field = new JTextField();
   field.addKeyListener(this);
   frame.add(field);
   board = new Display(this, 25, 25);
   board.build();
   frame.add(board);
   }
   
   public static void main (String[] args)
   {
   new Game();
   }
   
   public void doAction()
   {
   if(action.equals("teleport"))
      player.teleport();
   if(action.equals("explore"))
      try{
         if(terrain[player.xP()/50][player.yP()/50] == 6 && player.has("torch","ladder"))
            System.out.println("Cave Explored!");
         }
      catch(ArrayIndexOutOfBoundsException e){System.out.println("Error detected");}
   if(action.equals("attack"))
      removeCharacters();
   }
   
   public void removeCharacters()
   {
   int[] pA = player.getArea();
   int x = player.xP(), y = player.yP();
   //Rectangle pRect = player.rect;
   /*if(player.area[2]>0)
      {pRect = new Rectangle(player.area[0],player.area[1],player.area[2],player.area[3]);}//(x-pA[0],y-pA[1],pA[0]+pA[2],pA[1]+pA[3]);
   else {pRect = new Rectangle(player.area[0]+player.area[2],player.area[1]+player.area[3],player.area[2],player.area[3]);}*/
   for(int j = 0; j < guys.size(); j++)
      {
      //Rectangle cRect = new Rectangle(guys.get(j).xP(), guys.get(j).yP(), guys.get(j).xSize, guys.get(j).ySize);
      //if (cRect.intersects(pRect))
        // guys.remove(j);
      }
   }
   
   public Player player(){
     return this.player;
   }
   public Display board(){
     return this.board;
   }
   public int[] key(){
     return this.key;
   }
   public String getAction(){
     return this.action;
   }
   public void setAction(String action){
     this.action = action;
   }
   public ArrayList<Character> getGuys(){
     return this.guys;
   }
   public int[][] terrain(){
     return this.terrain;
   }
   public ImageIcon[][] icons(){
     return this.icons;
   }
   public void setTerrain(int[][] terrain){
     this.terrain = terrain;
   }
   public void setIcons(ImageIcon[][] icons){
     this.icons = icons;
   }
   
   public void actionPerformed(ActionEvent evt)
   {
   board.repaint();
   }

   public void keyPressed(KeyEvent e)
   {
   action = "none";
   //System.out.println(e.getKeyCode());
   switch(e.getKeyCode())
      {
      case 37: if(key[0] == 0)key[0] = (key[2]==0)? 5: 10-key[2]; break;
      case 38: if(key[1] == 0)key[1] = (key[3]==0)? 5: 10-key[3]; break;
      case 39: if(key[2] == 0)key[2] = (key[0]==0)? 5: 10-key[0]; break;
      case 40: if(key[3] == 0)key[3] = (key[1]==0)? 5: 10-key[1]; break;
      case 68: action = "dive"; break;
      case 69: action = "explore"; break;
      case 84: action = "teleport"; break;
      case 32: action = "attack"; break;
      }
   doAction();
   }
   public void keyTyped(KeyEvent e) {}
   public void keyReleased(KeyEvent e) {}
}