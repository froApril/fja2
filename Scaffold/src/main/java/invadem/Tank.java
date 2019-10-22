package invadem;

import processing.core.PImage;

public class Tank {
    public int x_pos;
    public int y_pos;
    public String image;

    public Tank(int x_pos, int y_pos, String image){
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.image = image;
    }
}
