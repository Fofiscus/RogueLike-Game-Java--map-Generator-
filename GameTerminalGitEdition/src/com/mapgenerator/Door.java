/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapgenerator;

/**
 *
 * @author x12340121
 */
public class Door { 
    private int x; 
    private int y; 
    private int direction; 
    private int fDirection; 
    private int wall; 
  
    public Door(int x, int y, int wall, int direction, int fDirection) { 
        this.x = x; 
        this.y = y; 
        this.wall = wall; 
        this.direction = direction; 
        this.fDirection = fDirection; 
    } 
      
    public Door(int x, int y) { 
        this.x = x; 
        this.y = y; 
    } 
  
    public int getX() { 
        return x; 
    } 
  
    public int getY() { 
        return y; 
    } 
      
    public int getDirection(){ 
        return direction; 
    } 
      
    public int getFDirection(){ 
        return fDirection; 
    } 
} 
