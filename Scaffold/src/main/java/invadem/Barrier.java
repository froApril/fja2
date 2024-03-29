package invadem;

public class Barrier implements checkAble{

    public String img_left = "barrier_left1.png";
    public String img_right = "barrier_right1.png";
    public String img_top = "barrier_top1.png";

    Solid[] left_solids = new Solid[3];
    Solid[] right_solids = new Solid[3];

    int top_pos;
    int bot_pos;
    int left_pos;
    int right_pos;

    int left_status=1;
    int top_status =1;
    int right_status =1;

    public Barrier(){
        init();
    }

    public void init(){
        for(int i=0;i<3;i++){
            left_solids[i]=new Solid();
            right_solids[i]=new Solid();
        }
    }

    /**
     * Check if the given position contained in the barrier area
     * @param x x position
     * @param y y position
     * @return boolean
     */

    public boolean check(int x, int y){
        boolean flag = false;
        if(x<=right_pos+3 && x>=left_pos-3 && y<=top_pos+5 && y>=bot_pos-5){
            if(x-left_pos<=7){
               flag = left_hit();
            }
            else if(x-left_pos<16){
                flag = mid_hit();
            }
            else{
                flag = right_hit();
            }
        }
        return flag;
    }

    public boolean left_hit(){
        left_status++;
        if(left_status<=3){
            img_left = "barrier_left"+left_status+".png";
            return true;
        }
        else{
            img_left = "empty.png";
            return false;
        }
    }

    public boolean mid_hit(){
        top_status++;
        if(top_status<=3){
            img_top = "barrier_top"+top_status+".png";
            return true;
        }
        else{
            img_top = "empty.png";
            return false;
        }
    }
    public boolean right_hit(){
        right_status++;
        if(right_status<=3){
            img_right = "barrier_right"+right_status+".png";
            return true;
        }
        else{
            img_right = "empty.png";
            return false;
        }
    }

    public boolean checkPowerBullet(int x, int y){
        if(x<=right_pos+3 && x>=left_pos-3 && y<=top_pos+5 && y>=bot_pos-5){
            if(x-left_pos<=7){
                if(left_status<=3){
                    left_status = 4;
                    img_left = "empty.png";
                    return true;
                }
                return false;

            }
            else if(x-left_pos<16){
                if(top_status<=3){
                    top_status = 4;
                    img_top = "empty.png";
                    return true;
                }
                return false;
            }
            else{
                if(right_status<=3){
                    right_status = 4;
                    img_top = "empty.png";
                    return true;
                }
                return false;
            }
        }
        return false;
    }



    /**
     * Check the attack comes from Invaders
     * @param x Projectile x position
     * @param y Projectile y position
     * @return
     */
    public boolean AlienHitscheck(int x, int y){
        boolean flag = false;
        if(x<=right_pos+15 && x>=left_pos-15 && y<=top_pos && y>=bot_pos-30){
            if(x-left_pos<=12){
                flag = left_hit();
            }
            else if(x-left_pos<21){
                flag = mid_hit();
            }
            else{
                flag = right_hit();
            }
        }
        return flag;
    }


    @Override
    public boolean isAlive() {
        return left_status<=3 && top_status<=3 && right_status<=3;
    }
}
