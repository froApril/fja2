package invadem;

import processing.core.PImage;

public class Tank {
    public int x_pos;
    public int y_pos;
    public String image;
    public boolean crashed = false;

    public Tank(int x_pos, int y_pos, String image){
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.image = image;
    }

    public boolean check(int x,int y){
        if(x<=x_pos+22 && x>=x_pos-6 && y>=y_pos-5 && y<=y_pos){
            crashed = true;
        }
        return crashed;
    }
}
