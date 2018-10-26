package bowling;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BowlingTest {
    private Bowling bowling;

    @Before
    public void setup() {
        bowling = new Bowling();
    }

    @Test
    public void roll_strike() throws Exception {
        List<String> result = bowling.roll(10);
        assertEquals("X", result.get(0));

        result = bowling.roll(8);
        assertEquals("8", result.get(1));
    }

    @Test
    public void roll_spare() throws Exception {
        List<String> result = bowling.roll(9);
        assertEquals("9", result.get(0));

        result = bowling.roll(1);
        assertEquals("9/", result.get(0));

        result = bowling.roll(10);
        assertEquals("X", result.get(1));
    }

    @Test
    public void roll_miss() throws Exception {
        List<String> result = bowling.roll(8);
        assertEquals("8", result.get(0));

        result = bowling.roll(1);
        assertEquals("81", result.get(0));
    }

    @Test
    public void createResult() throws Exception {
        List<Integer> falledPins = Arrays.asList(10, 0, 9, 1, 8, 1);
        List<String> result = bowling.createResult(falledPins);
        assertEquals("X", result.get(0));
        assertEquals("9/", result.get(1));
        assertEquals("81", result.get(2));
    }

    @Test
    public void createResult_10frame_miss() throws Exception {
        List<Integer> falledPinsOf10 = Arrays.asList(8);
        String result = bowling.createResultOf10(falledPinsOf10);
        assertEquals("8", result);

        falledPinsOf10 = Arrays.asList(8, 1);
        result = bowling.createResultOf10(falledPinsOf10);
        assertEquals("81", result);
    }

    @Test
    public void createResult_10frame_spare() throws Exception {
        List<Integer> falledPinsOf10 = Arrays.asList(8, 2, 7);
        String result = bowling.createResultOf10(falledPinsOf10);
        assertEquals("8/7", result);
    }

    @Test
    public void createResult_10frame_strike() throws Exception {
        List<Integer> falledPinsOf10 = Arrays.asList(10, 10, 8);
        String result = bowling.createResultOf10(falledPinsOf10);
        assertEquals("XX8", result);
    }

    @Test
    public void all_strike() throws Exception {
        List<String> result = null;
        for (int i = 0; i < 12; i++) {
            result = bowling.roll(10);
        }
        assertEquals("XXX", result.get(9));
    }

    @Test
    public void tenframe_miss() throws Exception {
        for (int i = 0; i < 9; i++) {
            bowling.roll(10);
        }
        bowling.roll(8);
        List<String> result = bowling.roll(1);
        assertEquals("81", result.get(9));
    }

    @Test
    public void tenframe_spare() throws Exception {
        for (int i = 0; i < 9; i++) {
            bowling.roll(10);
        }
        bowling.roll(8);
        bowling.roll(2);
        List<String> result = bowling.roll(9);
        assertEquals("8/9", result.get(9));
    }
}
