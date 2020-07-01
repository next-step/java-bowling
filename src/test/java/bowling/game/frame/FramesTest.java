package bowling.game.frame;

import bowling.game.Score;
import bowling.game.frame.Frame;
import bowling.game.frame.Frames;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FramesTest {

    @DisplayName("프레임을 만들면 첫번째 프레임이 생성되어있다.")
    @Test
    void createFrames() {
        Frames frames = new Frames();

        assertThat(frames.getCurrentFrameNumber()).isEqualTo(1);
    }

    @DisplayName("다음 프레임을 생성해서 추가한다.")
    @Test
    void createNextFrame() {
        Frames frames = new Frames();

        frames.createNextFrame();

        assertThat(frames.getCurrentFrameNumber()).isEqualTo(2);
    }

    @DisplayName("현재 프레임에 투구 기회가 있는지 반환한다.")
    @Test
    void hasRemainChance() {
        Frames frames = new Frames();

        frames.bowlCurrentFrame(8);
        assertThat(frames.hasRemainChance()).isTrue();
    }

    @DisplayName("게임이 끝났는지 반환한다.")
    @Test
    void isEndAllFrames() {
        Frames frames = new Frames();

        for (int i = 0; i < 9; i++) {
            frames.createNextFrame();
        }

        assertAll(
                () -> {
                    frames.bowlCurrentFrame(8);
                    assertThat(frames.isEndAllFrames()).isFalse();
                },

                () -> {
                    frames.bowlCurrentFrame(1);
                    assertThat(frames.isEndAllFrames()).isTrue();
                }
        );
    }

    @DisplayName("현재까지의 점수를 계산해서 반환한다.")
    @Test
    void getScores() {
        Frames frames = new Frames();

        frames.bowlCurrentFrame(1);
        frames.bowlCurrentFrame(9);
        frames.bowlCurrentFrame(7);
        frames.bowlCurrentFrame(2);
        frames.bowlCurrentFrame(3);

        List<Score> scores = frames.getScores();

        assertThat(scores.size()).isEqualTo(2);
        assertThat(scores.get(0).getScore()).isEqualTo(17);
        assertThat(scores.get(1).getScore()).isEqualTo(9);
    }
}