package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameScoreTest {
    @DisplayName("프레임의 투구결과를 반환한다.")
    @Test
    void score() {
        Frame frame = new Frame(1);
        frame.pitch(8);
        frame.pitch(2);

        FrameScore frameScore = new FrameScore(frame);
        assertThat(frameScore.score()).isEqualTo(new Score(10));
    }

    @DisplayName("스페어 처리의 경우, 투구 합산 가능여부는 True 를 반환한다.")
    @Test
    void isExistsAddCount_spare() {
        Frame frame = new Frame(1);
        frame.pitch(8);
        frame.pitch(2);

        FrameScore frameScore = new FrameScore(frame);
        assertThat(frameScore.isExistsAddCount()).isTrue();
    }

    @DisplayName("스페어 처리의 경우, 투구 합산 가능여부는 True 를 반환한다.")
    @Test
    void isExistsAddCount_strike() {
        Frame frame = new Frame(1);
        frame.pitch(10);

        FrameScore frameScore = new FrameScore(frame);
        assertThat(frameScore.isExistsAddCount()).isTrue();
    }

    @DisplayName("오픈의 경우, 투구 합산 가능여부는 False 를 반환한다.")
    @Test
    void isExistsAddCount_open() {
        Frame frame = new Frame(1);
        frame.pitch(5);
        frame.pitch(0);

        FrameScore frameScore = new FrameScore(frame);
        assertThat(frameScore.isExistsAddCount()).isFalse();
    }
}
