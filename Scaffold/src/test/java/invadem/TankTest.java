package invadem;

import org.junit.Test;
import static org.junit.Assert.*;

public class TankTest {

    @Test
    public void testTankConstruction() {
        Tank tank = new Tank(0, 0, "tank1.png");
        assertNotNull(tank);
    }

    @Test
    public void testCheckTankAlive() {
        Tank tank = new Tank(0, 0, "tank1.png");
        tank.check(100,100);
        assertEquals(tank.crashed,false);
    }
    @Test
    public void testCheckTankDead() {
        Tank tank = new Tank(0, 0, "tank1.png");
        tank.check(1,-1);
        assertEquals(tank.crashed,true);
        assertEquals(tank.isAlive(),false);
    }

//    @Test
//    public void testTankIsNotDead() {
//        Tank tank = new Tank(null, 0, 0);
//        assertEquals(true, tank.isDead());
//    }

}
