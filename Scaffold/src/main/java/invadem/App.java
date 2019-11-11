package invadem;

import processing.core.PApplet;

import java.awt.*;
import java.util.Random;

public class App extends PApplet {

    static int BULLET_SIZE =2000;
    static int POWER_BULLET_SIZE = 100;
    static int INVADER_NUMBER =40;
    static int spacer = 80;
    static int HITS_NUM = 0;
    static int CONDITION = 0;
    static int BARRIER_BASE_X = 160;
    static int BARRIER_NUM = 3;
    static boolean ALIENS_FLAG = false;
    static int FRAME_INDEX =0;
    static int FRAME_RATE = 100;

    Tank tank;
    Barrier barriers[];
    Projectile[] projectiles = new Projectile[BULLET_SIZE];
    PowerProjectile[] powerProjectiles = new PowerProjectile[POWER_BULLET_SIZE];
    Invader[] invaders = new Invader[INVADER_NUMBER];
    int saved_time;


    public App() {
        //Set up your objects

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
        FRAME_INDEX++;

        switch (CONDITION){
            case 0:

                // aliens shot
                aliensShot();

                //check tank;
                checkTank();

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
            case 2:
                gameOver();
            default:
                break;
        }

    }

    public void keyPressed() {
        if(keyCode==LEFT){
            if(tank.x_pos<4){
                return;
            }
            tank.x_pos-=4;
        }
        if(keyCode==RIGHT){
            if(tank.x_pos>612){
                return;
            }
            tank.x_pos+=4;
        }
        if(key==' '){
            for(int i=0;i<BULLET_SIZE;i++){
                if(projectiles[i].flag==0 && projectiles[i].shoter==0){
                    projectiles[i].shot(tank.x_pos,tank.y_pos);
                    break;
                }
            }
        }

    }

    public void init(){
        tank = new Tank(320,400,"tank1.png");
        barriers = new Barrier[3];
        //normal bullet
        for(int i=0;i<BULLET_SIZE;i++){
            projectiles[i] = new Projectile();
        }
        //Power bullet
        for(int i=0; i< POWER_BULLET_SIZE;i++){
            powerProjectiles[i]= new PowerProjectile();
        }
        //Armoured Invaders
        for(int i=0;i<10;i++){
            invaders[i] = new ArmouredInvader();
        }
        //Power Invaders
        for(int i=10;i<20;i++){
            invaders[i] = new PowerInvader();
        }
        //Normal
        for(int i=20;i<40;i++){
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
//        for(int i=0;i<ALIENS_BULLETS;i++){
//            aliens_bullets[i] = new Bullet();
//        }

        for(int i=0;i<BARRIER_NUM;i++){
            barriers[i] = new Barrier();
        }
    }

    /**
     * Set all the barriers on the screen, including the solids inside that
     */
    public void showBarriers(){

        for(int i=0;i<barriers.length; i++){
            image(loadImage(barriers[i].img_left),BARRIER_BASE_X*(i+1)-8,360);
            image(loadImage(barriers[i].img_top),BARRIER_BASE_X*(i+1),360);
            image(loadImage(barriers[i].img_right),BARRIER_BASE_X*(i+1)+8,360);
            barriers[i].top_pos = 360;
            barriers[i].bot_pos = 368;
            barriers[i].left_pos = BARRIER_BASE_X*(i+1)-8;
            barriers[i].right_pos = BARRIER_BASE_X*(i+1)+8;

            Solid[]left_solid = barriers[i].left_solids;
            Solid[]right_solid = barriers[i].right_solids;

            int index_l=0;
            for(Solid s:left_solid){
                image(loadImage(s.img),BARRIER_BASE_X*(i+1)-8,368+index_l*8);
                s.top_pos = 368+index_l*8;
                s.bot_pos = 368+index_l*8+8;
                s.left_pos = BARRIER_BASE_X*(i+1)-8;
                s.right_pos = BARRIER_BASE_X*(i+1);
                index_l++;
            }

            int index_r =0;
            for(Solid s:right_solid){
                image(loadImage(s.img),BARRIER_BASE_X*(i+1)+8,368+index_r*8);
                s.top_pos = 368+index_r*8;
                s.bot_pos = 368+index_r*8+8;
                s.left_pos = BARRIER_BASE_X*(i+1)+8;
                s.right_pos = BARRIER_BASE_X*(i+1)+16;
                index_r++;
            }
        }

    }

    /**
     * Display the tank on the screen
     */
    public void TankDisplay(){
        if(!tank.crashed){
            image(loadImage(tank.image),tank.x_pos,tank.y_pos);
        }
    }

    /**
     * Check both Projectile and invader iteratively,
     * if the projectile is flying and hit one of the invaders,
     * then set them both destroyed.
     */

    public void checkAliensDestoried(){
        for(Projectile b: projectiles){
            for(Invader invader: invaders){
                if(b.miss==1 && invader.destroy==0 && b.shoter==0){
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

    /**
     * Display all the bullets shot out, both from tank and invaders
     */

    public void showBullets(){
        for(int i=0; i<BULLET_SIZE;i++){
            if(projectiles[i].flag==1 && projectiles[i].miss==1 ){
                if(projectiles[i].shoter==0){
                    image(loadImage(projectiles[i].img), projectiles[i].x_pos, projectiles[i].y_pos);
                    projectiles[i].fly();
                }
                else{
                    image(loadImage(projectiles[i].img), projectiles[i].x_pos, projectiles[i].y_pos);
                    projectiles[i].aliensShot();
                }
            }
        }

        for(int i=0;i<POWER_BULLET_SIZE;i++){
            if(powerProjectiles[i].flag==1){
                image(loadImage(powerProjectiles[i].getImg()),powerProjectiles[i].x_pos,powerProjectiles[i].y_pos);
                powerProjectiles[i].aliensShot();
            }
        }
    }

    /**
     * Display all the existing invaders
     */

    public void showAliens(){
        for(int i=0;i<INVADER_NUMBER;i++){
            if(invaders[i].y_pos>=330){
                ALIENS_FLAG =true;
                break;
            }
            if(invaders[i].destroy==0){
                image(loadImage(invaders[i].getImg()),invaders[i].x_pos,invaders[i].y_pos);
                invaders[i].move();
            }

        }
    }

    /**
     * This method is used for change the condition of the game
     * if the tank is destroyed or invaders are too close, then
     * the game is over.
     * if all the invaders are destroyed, then it should be go to
     * next stage.
     */

    public void ConditionSwitch(){
        if(HITS_NUM==40){
            CONDITION=1;
            saved_time =millis();
        }
        if(tank.crashed || ALIENS_FLAG){
            Toolkit.getDefaultToolkit().beep();
            CONDITION =2;
            saved_time =millis();
        }
    }

    /**
     * Display next stage image, and start next turn.
     */

    public void nextStage(){
        if(millis()-saved_time < 1000){
            image(loadImage("nextlevel.png"),260,240);
        }
        else{
            CONDITION =0;
            HITS_NUM=0;
            if(FRAME_RATE-1>=60){
                FRAME_RATE--;
            }
            init();
        }
    }

    /**
     * Display game over image, and start next turn.
     */

    public void gameOver(){
        if(millis()-saved_time < 1000){
            image(loadImage("gameover.png"),260,240);
        }
        else{
            CONDITION =0;
            HITS_NUM=0;
            tank.crashed =false;
            ALIENS_FLAG= false;
            FRAME_INDEX=0;
            FRAME_RATE=100;
            init();
        }
    }

    /**
     * Check if any Projectile hits on barrier, using barrier functions to
     * show the damage.
     * Also, both tank and invaders may attack barrier, hence we need to check
     * about that.
     */
    public void checkBarrierHit(){

        for(Projectile projectile : projectiles){
            if(projectile.flag==1){
                for(Barrier barrier:barriers){
                    Solid[] left_solid = barrier.left_solids;
                    Solid[] right_solid = barrier.right_solids;

                    // check left destroy
                    for(Solid s:left_solid){
                        if(!s.flag){
                            if(s.check(projectile.x_pos, projectile.y_pos)){
                                projectile.miss=0;
                                projectile.flag=0;
                            }
                        }
                    }
                    for(Solid s:right_solid){
                        if(!s.flag){
                            if(s.check(projectile.x_pos, projectile.y_pos)){
                                projectile.miss=0;
                                projectile.flag=0;
                            }
                        }
                    }

                    if(barrier.check(projectile.x_pos, projectile.y_pos)){
                        projectile.miss=0;
                        projectile.flag=0;
                    }
                }
            }
        }
        for(Invader invader:invaders){
            if(invader.destroy==0){
                for(Barrier barrier:barriers){
                    Solid[] left_solid = barrier.left_solids;
                    Solid[] right_solid = barrier.right_solids;
                    // check left destroy
                    for(Solid s:left_solid){
                        if(!s.flag){
                            if(s.AliensHitCheck(invader.x_pos,invader.y_pos)){
                                invader.destroy=1;
                            }
                        }
                    }
                    for(Solid s:right_solid){
                        if(!s.flag){
                                if(s.AliensHitCheck(invader.x_pos,invader.y_pos)){
                                    invader.destroy=1;
                                }
                        }
                    }
                    if(barrier.AlienHitscheck(invader.x_pos,invader.y_pos)){
                        invader.destroy=1;
                    }
                }
            }
        }

    }

    /**
     * Check if tank is under attack
     * Tank can handle three bullets.
     */
    public void checkTank(){
        //Crashed by aliens (not be used, but )
        for(Invader invader:invaders){
            if(invader.destroy== 0){
                if(tank.check(invader.x_pos,invader.y_pos)){
                    invader.destroy=1;
                }
            }
        }
        for(Projectile projectile : projectiles){
            if(projectile.shoter==1 && projectile.flag==1){
                if(tank.check(projectile.x_pos, projectile.y_pos)){
                    projectile.flag=0;
                }
            }
        }


    }


    /**
     * Randomly choose an existing invader to shot
     */
    public void aliensShot(){
        if(FRAME_INDEX%FRAME_RATE==0){
            Random random = new Random();
            int random_index = random.nextInt(40);
            Invader shoter = invaders[random_index];
            if(shoter.getType()==2){
                System.out.println("here");
                for(PowerProjectile powerProjectile: powerProjectiles){
                    if(powerProjectile.flag==0 && shoter.destroy!=1){
                        powerProjectile.x_pos = shoter.x_pos;
                        powerProjectile.y_pos = shoter.y_pos+5;
                        powerProjectile.flag =1;
                        powerProjectile.aliensShot();
                        break;
                    }
                }
            }
            else{
                for(Projectile projectile : projectiles){
                    if(projectile.flag==0 && shoter.destroy!=1){
                        projectile.x_pos = shoter.x_pos;
                        projectile.y_pos = shoter.y_pos+5;
                        projectile.flag =1;
                        projectile.shoter =1;
                        projectile.aliensShot();
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        PApplet.main("invadem.App");
    }

}
