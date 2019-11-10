package invadem;

public class Bullet implements checkAble{
    public int x_pos;
    public int y_pos;
    public String img = "projectile.png";
    public int flag = 0;
    public int miss = 1;
    public int shoter = 0;
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
            miss=1;
        }
    }

    public void shot(int x, int y){
        this.x_pos = x+10;
        this.y_pos = y+10;
        this.flag=1;
    }

    public void aliensShot(){
        if(y_pos<480){
            y_pos = y_pos+2;
        }
        else{
            flag =0;
            miss =1;
        }
    }

    /**
     * check if the bullet is flying
     * @return
     */

    @Override
    public boolean isAlive() {
       return flag==1;
    }
}
