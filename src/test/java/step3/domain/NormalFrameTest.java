package step3.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class NormalFrameTest {
    @Test
    void test() {
        NormalFrame frame = new NormalFrame(1);
        frame.bowl(4);
        frame.bowl(6);
        System.out.println(frame.getSymbol());
    }

    @Test
    void test2() {
        List<Frame> frames = new ArrayList<>();
        Frame frame = new NormalFrame(0);
        frame.bowl(10);
        frames.add(frame);

        frame = frame.createFrame();
        frame.bowl(10);
        frames.add(frame);

        frame = frame.createFrame();
        frame.bowl(10);
        frames.add(frame);

        frame = frame.createFrame();
        frame.bowl(4);
        System.out.println(frames.get(0).getScore().getScore());
        System.out.println(frames.size());
    }
}