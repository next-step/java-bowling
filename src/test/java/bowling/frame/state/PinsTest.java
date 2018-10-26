package bowling.frame.state;

import static org.junit.Assert.*;

import bowling.frame.Score;
import org.junit.Test;

public class PinsTest {
    @Test(expected = IllegalArgumentException.class)
    public void createWhenOverMaxPins() throws Exception {
        Pins.bowl(11);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void createWhenUnderMinPins() throws Exception {
        Pins.bowl(-1);
    }
    
    @Test
    public void strike() throws Exception {
        Pins pins = Pins.bowl(10);
        assertTrue(pins.isStrike());
    }
    
    @Test
    public void spare() throws Exception {
        Pins pins = Pins.bowl(8);
        assertTrue(pins.isSpare(Pins.bowl(2)));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void spareWhenOverMaxPins() throws Exception {
        Pins pins = Pins.bowl(8);
        pins.isSpare(Pins.bowl(3));
    }
    
    @Test
    public void miss() throws Exception {
        Pins pins = Pins.bowl(8);
        assertTrue(pins.isMiss(Pins.bowl(1)));
    }
    
    @Test
    public void totalPins() throws Exception {
        Pins pins = Pins.bowl(8);
        assertEquals(9, pins.totalPins(Pins.bowl(1)));
    }
    
    @Test
    public void sumScore() throws Exception {
        Score score = new Score(10, 1);
        Pins pins = Pins.bowl(8);
        assertEquals(new Score(18, 0), pins.sumScore(score));
    }
    
    @Test
    public void getDesc() throws Exception {
        assertEquals("X", Pins.bowl(10).getDesc());
        assertEquals("9 | /", Pins.bowl(9).getDesc(Pins.bowl(1)));
        assertEquals("9 | ", Pins.bowl(9).getDesc());
        assertEquals("8 | 1", Pins.bowl(8).getDesc(Pins.bowl(1)));
    }
}
