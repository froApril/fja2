package invadem;

public class Invader implements checkAble{

    public String hello = "invader1.png";
    private static int LIMITATION_X = 50;
    private static int LIMITATION_Y = 5;
    private int status_x=0;
    private int status_y=0;
    public int horizontal =0;
    public int vertical =0;
    public int x_pos;
    public int y_pos;
    public int destroy=0;


    public void move(){
        // to right
        if(horizontal==0 && vertical==0){
            hello = "invader1.png";
            if(status_x++ <=LIMITATION_X){
               x_pos++;
           }
           else{
               vertical =1;
               status_x=0;
           }
           return;

        }
        if(horizontal==1 && vertical==0){
            hello = "invader1.png";
            if(status_x++ <=LIMITATION_X){
                x_pos--;
            }
            else{
                vertical =1;
                status_x=0;
            }
            return;
        }
        if(vertical==1){
            hello = "invader2.png";
            if(status_y++<=LIMITATION_Y){
                y_pos++;
            }
            else{
                vertical =0;
                status_y=0;
                if(horizontal==1){
                    horizontal=0;
                }
                else{
                    horizontal=1;
                }
            }

        }

    }

    @Override
    public boolean isAlive() {
        return destroy==0;
    }
}
