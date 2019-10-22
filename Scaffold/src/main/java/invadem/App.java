package invadem;

import processing.core.PApplet;

public class App extends PApplet {

    static int BULLET_SIZE =2000;
    static int INVADER_NUMBER =40;
    static int spacer = 80;
    static int HITS_NUM = 0;
    static int CONDITION = 0;
    static int BARRIER_BASE_X = 160;
    static int BARRIER_NUM = 3;

    Tank tank;
    Barrier barriers[];
    Bullet[] bullets = new Bullet[BULLET_SIZE];
    Invader[] invaders = new Invader[INVADER_NUMBER];
    int saved_time;


    public App() {
        //Set up your objects
        tank = new Tank(320,400,"tank1.png");
        barriers = new Barrier[3];
        //init data
        init();
    }

    public void setup() {
        frameRate(60);

    }

    public void settings() {
        size(640, 480);
    }

    public void draw() {
        //Main Game Loop
        background(0);

        switch (CONDITION){
            case 0:

                //show barriers
                showBarriers();

                //show tank
                TankDisplay();

                //check destroy
                checkAliensDestoried();

                //check BarrierHit
                checkBarrierHit();

                //show bullets
                showBullets();

                //show aliens
                showAliens();

                //Check condition
                ConditionSwitch();
                break;

            case 1:
                //show next stage image
                nextStage();

                break;
            default:
                break;
        }

    }

    public void keyPressed() {
        if(keyCode==LEFT){
            tank.x_pos-=4;
        }
        if(keyCode==RIGHT){
            tank.x_pos+=4;
        }
        if(key==' '){
            for(int i=0;i<BULLET_SIZE;i++){
                if(bullets[i].flag==0){
                    bullets[i].shot(tank.x_pos,tank.y_pos);
                    break;
                }
            }
        }

    }

    public void init(){
        for(int i=0;i<BULLET_SIZE;i++){
            bullets[i] = new Bullet();
        }
        for(int i=0;i<INVADER_NUMBER;i++){
            invaders[i] = new Invader();
        }

        int index =0;
        for(int i =0;i<4;i++){
            for(int j = 0;j<10;j++){
                invaders[index].x_pos=spacer+10+spacer/2*j;
                invaders[index].y_pos=spacer+i*spacer/2-30;
                index++;
            }
        }

        for(int i=0;i<BARRIER_NUM;i++){
            barriers[i] = new Barrier();
        }
    }


    public void showBarriers(){

        for(int i=0;i<barriers.length; i++){
            image(loadImage(barriers[i].img_left),BARRIER_BASE_X*(i+1)-8,360);
            image(loadImage(barriers[i].img_top),BARRIER_BASE_X*(i+1),360);
            image(loadImage(barriers[i].img_right),BARRIER_BASE_X*(i+1)+8,360);
            barriers[i].top_pos = 360;
            barriers[i].bot_pos = 368;
            barriers[i].left_pos = BARRIER_BASE_X*(i+1)-8;
            barriers[i].right_pos = BARRIER_BASE_X*(i+1)+8;
//
//            Solid[]left_solid = barriers[i].left_solids;
//            Solid[]right_solid = barriers[i].right_solids;
//
//            int index_l=0;
//            for(Solid s:left_solid){
//                image(loadImage(s.img),BARRIER_BASE_X*(i+1)-8,368+index_l*8);
//                s.top_pos = 368+index_l*8;
//                s.bot_pos = 368+index_l*8+8;
//                s.left_pos = BARRIER_BASE_X*(i+1)-8;
//                s.right_pos = BARRIER_BASE_X*(i+1);
//                index_l++;
//            }
//
//            int index_r =0;
//            for(Solid s:right_solid){
//                image(loadImage(s.img),BARRIER_BASE_X*(i+1)+8,368+index_r*8);
//                s.top_pos = 368+index_r*8;
//                s.bot_pos = 368+index_r*8+8;
//                s.left_pos = BARRIER_BASE_X*(i+1)+8;
//                s.right_pos = BARRIER_BASE_X*(i+1)+16;
//                index_r++;
//            }
        }

    }
    public void TankDisplay(){
        image(loadImage(tank.image),tank.x_pos,tank.y_pos);
    }

    public void checkAliensDestoried(){
        for(Bullet b: bullets){
            for(Invader invader: invaders){
                if(b.miss==1 && invader.destroy==0){
                    if((b.x_pos<= invader.x_pos+10 && b.x_pos>=invader.x_pos-10)
                            && (b.y_pos<= invader.y_pos+5 && b.y_pos>=invader.y_pos-1)){
                        b.miss =0;
                        invader.destroy =1;
                        HITS_NUM++;
                    }
                }
            }
        }
    }

    public void showBullets(){
        for(int i=0; i<BULLET_SIZE;i++){
            if(bullets[i].flag==1 && bullets[i].miss==1){
                image(loadImage(bullets[i].img),bullets[i].x_pos,bullets[i].y_pos);
                bullets[i].fly();
            }
        }
    }

    public void showAliens(){
        for(int i=0;i<INVADER_NUMBER;i++){
            if(invaders[i].destroy==0){
                image(loadImage(invaders[i].hello),invaders[i].x_pos,invaders[i].y_pos);
                invaders[i].move();
            }

        }
    }

    public void ConditionSwitch(){
        if(HITS_NUM==40){
            CONDITION=1;
            saved_time =millis();
        }
    }

    public void nextStage(){
        if(millis()-saved_time < 1000){
            image(loadImage("nextlevel.png"),260,240);
        }
        else{
            CONDITION =0;
            HITS_NUM=0;
            init();
        }
    }

    public void checkBarrierHit(){

        for(Bullet bullet:bullets){
            if(bullet.flag==1){
                for(Barrier barrier:barriers){
                    if(barrier.check(bullet.x_pos,bullet.y_pos)){
                        System.out.println("here");
                        bullet.miss=0;
                        bullet.flag=0;
                    }
                }
            }
        }

    }



    public static void main(String[] args) {
        PApplet.main("invadem.App");
    }

}
