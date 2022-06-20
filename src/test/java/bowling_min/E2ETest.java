package bowling_min;

import bowling_step3.domain.Frame;
import bowling_step3.domain.Frames;
import bowling_step3.domain.Subtotals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class E2ETest {
    private Frames frames;
    private Frame frame;
    private Subtotals subtotals;

    @BeforeEach
    public void setup() {
        frames = Frames.create();
        frame = frames.first();
        subtotals = new Subtotals();
    }

    @Test
    public void roll_strike() throws Exception {
        frame = frame.play(10);
        assertThat(frames.get(0).scores().getFirstScore()).isEqualTo(10);
        frame = frame.play(8);
        assertThat(frames.get(1).scores().getFirstScore()).isEqualTo(8);
    }

    @Test
    public void roll_spare() throws Exception {
        frame = frame.play(9);
        frame = frame.play(1);
        assertThat(frames.get(0).scores().scores()).isEqualTo(Arrays.asList(9, 1));
        frame = frame.play(10);
        assertThat(frames.get(1).scores().getFirstScore()).isEqualTo(10);
    }

    @Test
    public void roll_miss() throws Exception {
        frame.play(8);
        assertThat(frames.first().scores().getFirstScore()).isEqualTo(8);
        frame.play(1);
        assertThat(frames.first().scores().scores()).isEqualTo(Arrays.asList(8, 1));
    }

//        @Test
//    public void createResult() throws Exception {
//        List<Integer> falledPins = Arrays.asList(10, 0, 9, 1, 8, 1);
//        List<String> result = bowling.createResult(falledPins);
//        assertThat(result.get(0)).isEqualTo("X");
//        assertThat(result.get(1)).isEqualTo("9/");
//        assertThat(result.get(2)).isEqualTo("81");
//    }

    //    @Test
//    public void createResult_10frame_miss() throws Exception {
//        List<Integer> falledPinsOf10 = Arrays.asList(8);
//        String result = bowling.createResultOf10(falledPinsOf10);
//        assertThat(result).isEqualTo("8");
//
//        falledPinsOf10 = Arrays.asList(8, 1);
//        result = bowling.createResultOf10(falledPinsOf10);
//        assertThat(result).isEqualTo("81");
//    }
//
//    @Test
//    public void createResult_10frame_spare() throws Exception {
//        List<Integer> falledPinsOf10 = Arrays.asList(8, 2, 7);
//        String result = bowling.createResultOf10(falledPinsOf10);
//        assertThat(result).isEqualTo("8/7");
//    }
//
//    @Test
//    public void createResult_10frame_strike() throws Exception {
//        List<Integer> falledPinsOf10 = Arrays.asList(10, 10, 8);
//        String result = bowling.createResultOf10(falledPinsOf10);
//        assertThat(result).isEqualTo("XX8");
//    }
//
    @Test
    public void all_strike() throws Exception {
        for (int i = 0; i < 12; i++) {
            frame = frame.play(10);
        }
        assertThat(frames.last().scores().scores()).isEqualTo(Arrays.asList(10, 10, 10));
    }

//    @Test
//    public void tenframe_miss() throws Exception {
//        for (int i = 0; i < 9; i++) {
//            bowling.roll(10);
//        }
//        bowling.roll(8);
//        List<String> result = bowling.roll(1);
//        assertThat(result.get(9)).isEqualTo("81");
//    }
//
//    @Test
//    public void tenframe_spare() throws Exception {
//        for (int i = 0; i < 9; i++) {
//            bowling.roll(10);
//        }
//        bowling.roll(8);
//        bowling.roll(2);
//        List<String> result = bowling.roll(9);
//        assertThat(result.get(9)).isEqualTo("8/9");
//    }


    @Test
    void e2e() {
//        Frame next = frame.playManual(10);
//        if (next.scores().scores().size() == 0) {
//
//        }
//        subtotals.add(current.subtotal(subtotals));
        Stream.iterate(0, i -> i < 10, i -> ++i)
                .forEach(i -> {
                            while (!frame.done()) {
                                frame = frame.play(10);
                                subtotals.add(frames.get(i).subtotal(subtotals));
                            }
                        }
                );
        System.out.println(subtotals);

    }
}
