package bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingTest {
    private Bowling bowling;

    @BeforeEach
    public void setup() {
        bowling = new Bowling();
    }

    @Test
    public void roll_strike() throws Exception {
        List<String> result = bowling.roll(10);
        assertThat(result.get(0)).isEqualTo("X");

        result = bowling.roll(8);
        assertThat(result.get(1)).isEqualTo("8");
    }

    @Test
    public void roll_spare() throws Exception {
        List<String> result = bowling.roll(9);
        assertThat(result.get(0)).isEqualTo("9");

        result = bowling.roll(1);
        assertThat(result.get(0)).isEqualTo("9/");

        result = bowling.roll(10);
        assertThat(result.get(1)).isEqualTo("X");
    }

    @Test
    public void roll_miss() throws Exception {
        List<String> result = bowling.roll(8);
        assertThat(result.get(0)).isEqualTo("8");

        result = bowling.roll(1);
        assertThat(result.get(0)).isEqualTo("81");
    }

    @Test
    public void createResult() throws Exception {
        List<Integer> falledPins = Arrays.asList(10, 0, 9, 1, 8, 1);
        List<String> result = bowling.createResult(falledPins);
        assertThat(result.get(0)).isEqualTo("X");
        assertThat(result.get(1)).isEqualTo("9/");
        assertThat(result.get(2)).isEqualTo("81");
    }

    @Test
    public void createResult_10frame_miss() throws Exception {
        List<Integer> falledPinsOf10 = Arrays.asList(8);
        String result = bowling.createResultOf10(falledPinsOf10);
        assertThat(result).isEqualTo("8");

        falledPinsOf10 = Arrays.asList(8, 1);
        result = bowling.createResultOf10(falledPinsOf10);
        assertThat(result).isEqualTo("81");
    }

    @Test
    public void createResult_10frame_spare() throws Exception {
        List<Integer> falledPinsOf10 = Arrays.asList(8, 2, 7);
        String result = bowling.createResultOf10(falledPinsOf10);
        assertThat(result).isEqualTo("8/7");
    }

    @Test
    public void createResult_10frame_strike() throws Exception {
        List<Integer> falledPinsOf10 = Arrays.asList(10, 10, 8);
        String result = bowling.createResultOf10(falledPinsOf10);
        assertThat(result).isEqualTo("XX8");
    }

    @Test
    public void all_strike() throws Exception {
        List<String> result = null;
        for (int i = 0; i < 12; i++) {
            result = bowling.roll(10);
        }
        assertThat(result.get(9)).isEqualTo("XXX");
    }

    @Test
    public void tenframe_miss() throws Exception {
        for (int i = 0; i < 9; i++) {
            bowling.roll(10);
        }
        bowling.roll(8);
        List<String> result = bowling.roll(1);
        assertThat(result.get(9)).isEqualTo("81");
    }

    @Test
    public void tenframe_spare() throws Exception {
        for (int i = 0; i < 9; i++) {
            bowling.roll(10);
        }
        bowling.roll(8);
        bowling.roll(2);
        List<String> result = bowling.roll(9);
        assertThat(result.get(9)).isEqualTo("8/9");
    }
}
