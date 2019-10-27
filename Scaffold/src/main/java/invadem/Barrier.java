package invadem;

public class Barrier {

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


}
