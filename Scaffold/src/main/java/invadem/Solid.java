package invadem;

public class Solid {
    int status = 1;
    String img = "barrier_solid1.png";
    int left_pos;
    int right_pos;
    int top_pos;
    int bot_pos;

    public void Hit(){
        status++;
        img = "barrier_solid"+status+".png";
    }
}
