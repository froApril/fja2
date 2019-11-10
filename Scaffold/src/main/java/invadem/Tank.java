package invadem;

import processing.core.PImage;

public class Tank implements checkAble {
    public int x_pos;
    public int y_pos;
    public String image;
    public boolean crashed = false;
    int hit_num = 0;

    public Tank(int x_pos, int y_pos, String image){
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.image = image;
    }

    /**
     * Check if the tank is destroyed.
     * @param x
     * @param y
     * @return
     */
    public boolean check(int x,int y){
        if(hit_num<3){
            if(x<=x_pos+22 && x>=x_pos-6 && y>=y_pos-5 && y<=y_pos){
                hit_num++;
                return true;
            }
        }
        else{
            crashed=true;
        }
        return crashed;
    }

    @Override
    public boolean isAlive() {
        return !crashed;
    }
}
