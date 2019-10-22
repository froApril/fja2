package invadem;

public class Bullet {
    public int x_pos;
    public int y_pos;
    public String img = "projectile.png";
    public int flag = 0;
    public int miss = 1;
    Bullet(){

    }

    Bullet(int x_pos,int y_pos){
        this.x_pos =x_pos;
        this.y_pos = y_pos;
    }

    public void fly(){
        if(y_pos>0){
            y_pos=y_pos-3;
        }
        else{
            flag=0;
        }
    }

    public void shot(int x, int y){
        this.x_pos = x+10;
        this.y_pos = y+10;
        this.flag=1;
    }


}
