/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapgenerator;

/**
 *
 * @author x12340121
 */
import java.util.ArrayList; 
  
public class MapGenerator { 
  
    /** 
     * @param args the command line arguments 
     */
    // Initializing main variables 
    int height = 45; 
    int width = 98; 
      
    public String map[][] = new String[height][width]; 
    public ArrayList<Door> doors = new ArrayList<Door>(); 
      
    public static final String WALL = "H"; 
    public static final String DOOR = "D"; 
    public static final String PATH = " "; 
    public static final String SPACE = "X"; 
    public static final String PLAYER = "@";
    public static final String SHADOW = "█";
    public static final String DUGUP = "+";
    public static final String ENEMY = "E";

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String[][] getMap() {
        return map;
    }

    public ArrayList<Door> getDoors() {
        return doors;
    }

    public String getWALL() {
        return WALL;
    }

    public String getDOOR() {
        return DOOR;
    }

    public String getPATH() {
        return PATH;
    }

    public String getSPACE() {
        return SPACE;
    }

    public String getPLAYER() {
        return PLAYER;
    }
    
    // Generating Map 
    public void genMap() { 
        // Filling the map 
        for (int i = 0; i <= height  - 1; i++) { 
            for (int j = 0; j <= width - 1; j++) { 
                map[i][j] = SPACE; 
            } 
        } 
  
        // Creating map borders 
        for (int i = 0; i <= width - 1; i++) { 
            map[0][i] = WALL; 
            map[height - 1][i] = WALL; 
        } 
        for (int i = 0; i <= height - 1; i++) { 
            map[i][0] = WALL; 
            map[i][width - 1] = WALL; 
        } 
    } 
  
    // Detecting obstacles 
    public boolean detObstacle(int x, int y) { 
        String[] block = {WALL, DOOR}; 
        boolean isColliding = false; 
        for (int i = 0; i <= 1; i++) { 
            if (x == 0 || x == height - 1 || y == 0 || y == width - 1) { 
                isColliding = true; 
            } else { 
                if (map[x][y].equals(block[i])) { 
                    isColliding = true; 
                } else { 
                    isColliding = false; 
                } 
            } 
        } 
        return isColliding; 
    } 
  
    // Generating single path 
    // ///////////////////////////////////////////////////////////////////////////////// 
    // Generating doors 
    // Checking if doors are too close to the wall; 
    public boolean checkDoorProximity(int x, int y) { 
        boolean isClose = false; 
        if (x == 0 && (y <= 2 || y >= width - 3)) { 
            isClose = true; 
        } else if (y == 0 && (x <= 2 || x >= height - 3)) { 
            isClose = true; 
        } else if (x == height - 1 && (y <= 2 || y >= width - 3)) { 
            isClose = true; 
        } else if (y == width - 1 && (x <= 2 || x >= height - 3)) { 
            isClose = true; 
        } else { 
            if (doors.isEmpty()) { 
                isClose = false; 
            } else { 
                int i = 0; 
                while (isClose == false && i < doors.size()) { 
                    int dx = doors.get(i).getX(); 
                    int dy = doors.get(i).getY(); 
                    if (y == 0 || y == width - 1) { 
                        isClose = (x == dx - 1 || x == dx + 1 || x == dx) ? true : false; 
                    } else if (x == 0 || x == height - 1) { 
                        isClose = (y == dy - 1 || y == dy + 1 || y == dy) ? true : false; 
                    } else { 
                        isClose = false; 
                    } 
                    i++; 
                } 
            } 
        } 
        return isClose; 
    } 
  
    public void genDoors() { 
        //choose wall  
        int wall = Rng.rand(1, 4); 
        System.out.println("wall:" + wall); 
        int direction = 0; 
        int fDirection = 0; 
        int x = 0; 
        int y = 0; 
          
        do { 
            switch (wall) { 
                case 1: 
                    direction = 6; 
                    fDirection = 5; 
                    x = height - 1; 
                    y = Rng.rand(3, width - 4); 
                    break; 
                case 2: 
                    direction = 5; 
                    fDirection = 6; 
                    x = 0; 
                    y = Rng.rand(3, width - 4); 
                    break; 
                case 3: 
                    direction = 8; 
                    fDirection = 7; 
                    y = width - 1; 
                    x = Rng.rand(3, height - 4); 
                    break; 
                case 4: 
                    direction = 7; 
                    fDirection = 8; 
                    y = 0; 
                    x = Rng.rand(3, height - 4); 
                    break; 
            } 
            System.out.println("x:"+ x + ", y:" + y + ", prox:" + checkDoorProximity(x, y)); 
        } while (checkDoorProximity(x, y) == true); 
        map[x][y] = DOOR; 
        doors.add(new Door(x, y, wall, direction, fDirection)); 
        System.out.println("dir:" + direction); 
    } 
      
    public void genCorridor(int index){ 
        Door d = doors.get(index); 
        boolean collided = false; 
        int direction = d.getDirection(); 
        int fDirection = d.getFDirection(); 
        int x = d.getX(); 
        int y = d.getY(); 
        int nextX = 0; 
        int nextY = 0; 
        int len = Rng.rand(3,8); 
        int stepsMade = 0; 
          
          
         // Generating the first two steps of the corridor 
        for (int i = 0; i < Rng.rand(1,2); i++){ 
            switch (direction){ 
                case 6:  
                    nextX = x - 1; 
                    nextY = y; 
                    break; 
                case 5: 
                    nextX = x + 1; 
                    nextY = y; 
                    break; 
                case 8: 
                    nextX = x; 
                    nextY = y - 1; 
                    break; 
                case 7:  
                    nextX = x; 
                    nextY = y + 1; 
                    break; 
                default: 
                    nextX = 0; 
                    nextY = 0; 
            } 
              
            System.out.println("dir:" + direction + ", fDir:" + fDirection + ", x:" + x + ", y:" + y + ", nextX:" + nextX + ", nextY:" + nextY); 
            map[nextX][nextY] = PATH; 
            x = nextX; 
            y = nextY; 
        } 
          
        // Generating path of the corridor 
        while (stepsMade <= len || collided == false) { 
            ArrayList<Integer> dirs = new ArrayList<Integer>(); 
            for(int i=5; i < 9; i++){ 
                dirs.add(i); 
            } 
            dirs.remove(dirs.indexOf(fDirection)); 
            direction = dirs.get(Rng.rand(0,2)); 
              
            switch (direction){ 
                case 6:  
                    nextX = x - 1; 
                    nextY = y; 
                    break; 
                case 5: 
                    nextX = x + 1; 
                    nextY = y; 
                    break; 
                case 8: 
                    nextX = x; 
                    nextY = y - 1; 
                    break; 
                case 7:  
                    nextX = x; 
                    nextY = y + 1; 
                    break; 
                default: 
                    nextX = 0; 
                    nextY = 0; 
            } 
            if (detObstacle(nextX, nextY) == false) { 
                map[nextX][nextY] = PATH; 
                x = nextX; 
                y = nextY; 
                stepsMade++; 
            } else { 
                collided = true; 
            } 
        } 
    } 
  
    public static void main(String[] args) { 
        MapGenerator mg = new MapGenerator(); 
  
        // Generate map 
        mg.genMap(); 
  
        // Generate doors 
        for(int i = 0; i < Rng.rand(2,20); i++) { 
            mg.genDoors(); 
        } 
          
        // Generate corridor 
        if (mg.doors.isEmpty()){ 
            System.out.println("Error: No doors generated!"); 
        } else { 
            for (int i = 0; i < mg.doors.size(); i++){ 
                mg.genCorridor(i); 
            } 
        } 
  
        // Printing out the map 
        for (int i = 0; i < mg.height; i++) { 
            for (int j = 0; j < mg.width; j++) { 
                if (j != mg.width - 1) { 
                    System.out.print(mg.map[i][j]); 
                } else { 
                    System.out.println(mg.map[i][j]); 
                } 
            } 
        } 
    } 
}
