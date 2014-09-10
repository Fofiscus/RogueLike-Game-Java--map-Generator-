/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapgenerator;

/**
 *
 * @author x12340121
 */

public class Rng { 
    public static int rand(int min, int max){ 
        return (int) (Math.random() * (max + 1 - min)) + min; 
    } 
      
    public static void main (String args[]){ 
        for (int i = 0; i < 20; i++){ 
            System.out.println(Rng.rand(0, 2)); 
        } 
    } 
  
}
