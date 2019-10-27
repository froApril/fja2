package invadem;

public class Solid {
    int status = 1;
    String img = "barrier_solid1.png";
    boolean flag = false;
    int left_pos;
    int right_pos;
    int top_pos;
    int bot_pos;

    public void Hit(){
        if(status<3){
            status++;
            img = "barrier_solid"+status+".png";
        }
        else{
            flag =true;
            img = "empty.png";
        }
    }

    public boolean check(int x, int y){
        if(x<=right_pos && x>=left_pos && y<=top_pos+5 && y>=bot_pos-5){
            Hit();
            return true;
        }
        return false;
    }

    public boolean AliensHitCheck(int x, int y){
        if(x<=right_pos+20 && x>=left_pos-20 && y<=top_pos+15 && y>=bot_pos-15){
            System.out.println("1111");
            Hit();
            return true;
        }
        return false;
    }
}
