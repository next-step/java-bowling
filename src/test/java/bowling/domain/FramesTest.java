package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Frames 클래스 테스트")
class FramesTest {
    final Frames frames = new Frames(new NormalFrame());

    @Test
    void create() {
        assertThat(frames.getCurrentFrame()).isInstanceOf(NormalFrame.class);
    }

    @Test
    void add() {
        frames.add(new NormalFrame());

        assertThat(frames.getFrames()).hasSize(2);
    }

    @Test
    void getCurrentFrameNumber() {
        frames.add(new NormalFrame());

        assertThat(frames.getCurrentFrameNumber()).isEqualTo(1);
    }

    @Test
    void isEndGame() {
        frames.add(new NormalFrame());

        assertThat(frames.isEndGame()).isFalse();
    }

    @Test
    void getScores() {
        int firstPin = 8;
        int secondPin = 1;
        BowlingGame game = new BowlingGame(new Player("ksj"));
        game.play(new Pin(firstPin));
        game.play(new Pin(secondPin));

        Frames frames = game.getFrames();
        List<Integer> scores = frames.getScores();

        assertThat(scores.get(0)).isEqualTo(firstPin + secondPin);
    }

    @Test
    void getFrame() {
        int frameNumber = 1;
        Frame frame = frames.getFrame(frameNumber);

        assertThat(frame).isInstanceOf(NormalFrame.class);
    }
}
