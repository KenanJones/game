package edu.jalc.game;

import java.awt.Rectangle;

public class EffectArea extends Rectangle{

  private EffectArea(int x,int y,int w,int h){
    super(x,y,w,h);
  }
  
  public static EffectArea EffectAreaBuilder(int x, int y, int w, int h){
    return new EffectArea(0,0,0,0);
  }
}