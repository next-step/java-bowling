package bowling_min;

import bowling_step3.domain.Frame;
import bowling_step3.domain.Frames;
import bowling_step3.domain.Player;
import bowling_step3.view.Output;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class E2ETest {
    private Frames frames;
    private Frame frame;

    @BeforeEach
    public void setup() {
        frames = Frames.create();
        frame = frames.first();
    }

    @Test
    public void roll_strike() throws Exception {
        frame = frame.playManual(10, frames);
        assertThat(frames.get(0).scores().getFirstScore()).isEqualTo(10);
//        Frame second = frames.next(first);
        frame = frame.playManual(8, frames);
        assertThat(frames.get(1).scores().getFirstScore()).isEqualTo(8);
    }

    @Test
    public void roll_spare() throws Exception {
        frame = frame.playManual(9, frames);
        frame = frame.playManual(1, frames);
        assertThat(frames.get(0).scores().scores()).isEqualTo(Arrays.asList(9, 1));
        frame = frame.playManual(10, frames);
        assertThat(frames.get(1).scores().getFirstScore()).isEqualTo(10);
    }

    @Test
    public void roll_miss() throws Exception {
        frame.playManual(8, frames);
        assertThat(frames.first().scores().getFirstScore()).isEqualTo(8);
        frame.playManual(1, frames);
        assertThat(frames.first().scores().scores()).isEqualTo(Arrays.asList(8, 1));
    }

    //    @Test
//    public void createResult() throws Exception {
//        List<Integer> falledPins = Arrays.asList(10, 0, 9, 1, 8, 1);
//        List<String> result = bowling.createResult(falledPins);
//        assertThat(result.get(0)).isEqualTo("X");
//        assertThat(result.get(1)).isEqualTo("9/");
//        assertThat(result.get(2)).isEqualTo("81");
//    }
//
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
        Player player = new Player("usr");
        for (int i = 1; i <= 12; i++) {
            frame = frame.playManual(10, frames);
            System.out.println(frame);
            Output.printFrames(i > 10 ? 10 : i, frames, player);
            System.out.println(frames);
        }
        assertThat(frames.last().scores().scores()).isEqualTo(Arrays.asList(10, 10, 10));
    }

    @Test
    void name() {
       List<Integer> a = new LinkedList<>();
        System.out.println(a.hashCode());
        a.add(1);
        System.out.println(a.hashCode());
        a.add(1);
        System.out.println(a.hashCode());
    }

    //
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
}
