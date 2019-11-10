package invadem;

import org.junit.Test;
import static org.junit.Assert.*;

public class BarrierTest {
    Barrier barrier;

    @Test
    public void barrierConstruction() {
        barrier = new Barrier();
        assertNotNull(barrier);
    }

    @Test
    public void barrierLeftHit() {
        barrier = new Barrier();
        barrier.left_hit();
        assertNotNull(barrier);
        assertEquals(barrier.left_status,2);
    }

    @Test
    public void barrierRightHit() {
        barrier = new Barrier();
        barrier.right_hit();
        assertNotNull(barrier);
        assertEquals(barrier.right_status,2);
    }

    @Test
    public void barrierMidHit() {
        barrier = new Barrier();
        barrier.mid_hit();
        assertNotNull(barrier);
        assertEquals(barrier.top_status,2);
    }

    @Test
    public void barrierAlienHit(){
        barrier = new Barrier();
        barrier.left_pos = 0;
        barrier.right_pos =10;
        barrier.bot_pos =0;
        barrier.top_pos =10;
        boolean flag =  barrier.AlienHitscheck(5,5);
        assertEquals(flag,true);
        flag =  barrier.AlienHitscheck(0,5);
        assertEquals(flag,true);
        // After the third hit, it should return false;
        flag =  barrier.AlienHitscheck(10,5);
        assertEquals(flag,false);
        flag =  barrier.AlienHitscheck(0,10);
        assertEquals(flag,false);
        flag =  barrier.AlienHitscheck(1000,100);
        assertEquals(flag,false);
    }

//    @Test
//    public void testBarrierNotDestroyed() {
//        Barrier b = /* Your Constructor Here */
//        assertEquals(false, b.isDestroyed());
//    }

//    @Test
//    public void testBarrierHitPointsMax() {
//        Barrier b = /* Your Constructor Here */
//        assertEquals(3, b.hitPoints());
//    }

//    @Test
//    public void testBarrierHitPointsMax() {
//        Barrier b = /* Your Constructor Here */
//        b.hit();
//        assertEquals(2, b.hitPoints());
//    }

//    @Test
//    public void testBarrierHitPointsMax() {
//        Barrier b = /* Your Constructor Here */
//        b.hit();
//        b.hit();
//        assertEquals(1, b.hitPoints());
//    }


//    @Test
//    public void testBarrierIsDestroyed() {
//        Barrier b = /* Your Constructor Here */
//        b.hit();
//        b.hit();
//        b.hit();
//        assertEquals(false, b.isDestroyed());
//    }

}
