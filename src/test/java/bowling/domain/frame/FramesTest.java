package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.Score;
import bowling.domain.status.Strike;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @Test
    void bowl() {
        Frames frames = Frames.init();
        frames.bowl(new Pin(10));
        assertThat(frames.getCurrentFrame().getStatus()).isInstanceOf(Strike.class);
    }

    @Test
    void bowl2() {
        Frames frames = Frames.init();
        Frame frame = frames.bowl(new Pin(10));
        frames = frames.addNextFrame(frame);
        assertThat(frames.getCurrentFrameNumber()).isEqualTo(2);
    }

    @Test
    void score() {
        Frames frames = Frames.init();
        Frame frame = frames.bowl(new Pin(10));
        frames = frames.addNextFrame(frame);
        frames.bowl(new Pin(7));
        frame = frames.bowl(new Pin(3));
        frames = frames.addNextFrame(frame);
        frames.bowl(new Pin(1));
        frames.bowl(new Pin(1));

        List<Integer> scores = frames.getFrameScores().stream()
                .map(Score::getValue)
                .collect(Collectors.toList());

        assertThat(scores).containsExactly(20, 11, 2);
    }

    @Test
    void score_all() {
        Frames frames = Frames.init();
        Frame frame = frames.bowl(new Pin(10));
        frames = frames.addNextFrame(frame);
        frames.bowl(new Pin(7));
        frame = frames.bowl(new Pin(3));
        frames = frames.addNextFrame(frame);
        frames.bowl(new Pin(1));
        frame = frames.bowl(new Pin(1));
        frames = frames.addNextFrame(frame);
        frames.bowl(new Pin(1));
        frame = frames.bowl(new Pin(1));
        frames = frames.addNextFrame(frame);
        frames.bowl(new Pin(1));
        frame = frames.bowl(new Pin(1));
        frames = frames.addNextFrame(frame);
        frames.bowl(new Pin(1));
        frame = frames.bowl(new Pin(1));
        frames = frames.addNextFrame(frame);
        frames.bowl(new Pin(1));
        frame = frames.bowl(new Pin(1));
        frames = frames.addNextFrame(frame);
        frames.bowl(new Pin(1));
        frame = frames.bowl(new Pin(1));
        frames = frames.addNextFrame(frame);
        frames.bowl(new Pin(1));
        frame = frames.bowl(new Pin(9));
        frames = frames.addNextFrame(frame);
        frames.bowl(new Pin(1));
        frames.bowl(new Pin(1));

        List<Integer> scores = frames.getFrameScores().stream()
                .map(Score::getValue)
                .collect(Collectors.toList());

        assertThat(scores).containsExactly(20, 11, 2, 2, 2, 2, 2, 2, 11);
    }
}
