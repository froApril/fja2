package invadem;

import org.junit.Test;
import static org.junit.Assert.*;


public class InvaderTest {

    @Test
    public void testInvaderConstruction() {
        Invader inv = new Invader();
        assertNotNull(inv);

    }

    @Test
    public void testInvaderFireProjectile() {
        Invader inv = new Invader();
        assertEquals(true,inv.isAlive());
    }

    @Test
    public void testInvaderIsDead() {
        Invader inv = new Invader();
        inv.destroy = 1;
        assertEquals(false,inv.isAlive());
    }


}
