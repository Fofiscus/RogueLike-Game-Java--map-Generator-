/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameterminal;

/**
 * @author Yakus
 */
public class GameActions {
    String error = "";

    public String getError() {
        return error;
    }
    
    public String[][] dig(int x, int y, String[][] map, String direction) {
        switch (direction){
            case "up": 
                y = y-1;
                map[x][y] = (map[x][y].equals("#")) ? "+" : map[x][y];
                break;
            case "down":
                y = y+1;
                map[x][y] = (map[x][y].equals("#")) ? "+" : map[x][y];
                break;
            case "left" :
                x = x - 1;
                map[x][y] = (map[x][y].equals("#")) ? "+" : map[x][y];
                break;
            case "right":
                x = x + 1;
                map[x][y] = (map[x][y].equals("#")) ? "+" : map[x][y];
                break;
            default: 
                error = "Wrong value for dig attribute.";
                
        }
        return map;
    }
    
    public boolean rest() {
        return true;
    }
    
}