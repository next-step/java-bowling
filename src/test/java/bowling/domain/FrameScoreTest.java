package bowling.domain;

import bowling.domain.status.Hold;
import bowling.domain.status.Miss;
import bowling.domain.status.Spare;
import bowling.domain.status.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameScoreTest {
    @DisplayName("프레임의 투구결과를 반환한다.")
    @Test
    void score() {
        int score = 10;
        FrameScore frameScore = new FrameScore(new Score(score), new BonusPitch(new Hold(new Pitch(score))));
        
        assertThat(frameScore.score()).isEqualTo(new Score(score));
    }

    @DisplayName("스페어 처리의 경우, 투구 합산 가능여부는 True 를 반환한다.")
    @Test
    void isExistsAddCount_spare() {
        int score = 10;
        FrameScore frameScore = new FrameScore(new Score(score), new BonusPitch(new Spare()));

        assertThat(frameScore.isExistsAddCount()).isTrue();
    }

    @DisplayName("스페어 처리의 경우, 투구 합산 가능여부는 True 를 반환한다.")
    @Test
    void isExistsAddCount_strike() {
        int score = 10;
        FrameScore frameScore = new FrameScore(new Score(score), new BonusPitch(new Strike()));

        assertThat(frameScore.isExistsAddCount()).isTrue();
    }

    @DisplayName("미스의 경우, 투구 합산 가능여부는 False 를 반환한다.")
    @Test
    void isExistsAddCount_open() {
        int score = 8;
        FrameScore frameScore = new FrameScore(new Score(score), new BonusPitch(new Miss(new Pitch(score))));

        assertThat(frameScore.isExistsAddCount()).isFalse();
    }
}
