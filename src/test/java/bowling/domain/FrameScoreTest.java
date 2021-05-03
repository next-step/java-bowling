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

    @DisplayName("스페어 처리한 후 첫번째 투구를 마치면 보너스투구 존재 여부는 거짓을 반환한다.")
    @Test
    void sumScore_spare() {
        int score = 10;
        FrameScore frameScore = new FrameScore(new Score(score), new BonusPitch(new Spare()));

        assertThat(frameScore.isExistsAddCount()).isTrue();

        FrameScore afterFirstPitch = frameScore.sumScore(8);

        assertThat(afterFirstPitch.isExistsAddCount()).isFalse();
    }

    @DisplayName("스트라이크 처리한 후 두번째 투구를 마치면 보너스투구 존재 여부는 거짓을 반환한다.")
    @Test
    void sumScore_strike() {
        int score = 10;
        FrameScore frameScore = new FrameScore(new Score(score), new BonusPitch(new Strike()));

        assertThat(frameScore.isExistsAddCount()).isTrue();

        FrameScore afterFirstPitch = frameScore.sumScore(8);

        assertThat(afterFirstPitch.isExistsAddCount()).isTrue();

        FrameScore afterSecondPitch = frameScore.sumScore(2);

        assertThat(afterSecondPitch.isExistsAddCount()).isFalse();
    }

    @DisplayName("미스의 경우, 보너스투구 존재 여부는 거짓을 반환한다.")
    @Test
    void sumScore_miss() {
        int score = 8;
        FrameScore frameScore = new FrameScore(new Score(score), new BonusPitch(new Miss(new Pitch(score))));

        assertThat(frameScore.isExistsAddCount()).isFalse();
    }
}
