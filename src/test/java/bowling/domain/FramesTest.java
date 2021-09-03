package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {

    @Test
    void create() {
        LinkedList<Frame> frameLinkedList = new LinkedList<>();

        Frames frames = Frames.of(frameLinkedList);
        frames.add(new NormalFrame(1));
    }

    @Test
    void isFinish() {
        Frames frames = new Frames();
        frames.add(new NormalFrame(1));

        assertThat(frames.isFinish()).isFalse();
    }
}
