package bowling.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    @Test
    @DisplayName("miss 로 만들어진 Score 는 추가 점수가 없습니다.")
    void createMissScore() {
        Score missScore = Score.toMiss(5);

        assertAll(
                () -> {
                    assertThatThrownBy(() -> missScore.bonusScore(5))
                            .isInstanceOf(IllegalStateException.class);
                    assertThat(missScore.canCalculate()).isFalse();
                    assertThat(missScore.score()).isEqualTo(5);
                }
        );
    }

    @Test
    @DisplayName("spare 로 만들어진 Score 는 단 한 번의 보너스 점수가 있습니다.")
    void createSpareScore() {
        Score spareScore = Score.toSpare();

        assertAll(
                () -> {
                    assertThat(spareScore.canCalculate()).isTrue();
                    assertThat(spareScore.score()).isEqualTo(10);
                    spareScore.bonusScore(5);
                    assertThat(spareScore.canCalculate()).isFalse();
                    assertThat(spareScore.score()).isEqualTo(15);
                }
        );
    }

    @Test
    @DisplayName("strike 로 만들어진 Score 는 두 번의 보너스 점수가 있습니다.")
    void createStrikeScore() {
        Score strikeScore = Score.toStrike();

        assertAll(
                () -> {
                    assertThat(strikeScore.canCalculate()).isTrue();
                    assertThat(strikeScore.score()).isEqualTo(10);
                    strikeScore.bonusScore(5);
                    assertThat(strikeScore.canCalculate()).isTrue();
                    assertThat(strikeScore.score()).isEqualTo(15);
                    strikeScore.bonusScore(5);
                    assertThat(strikeScore.canCalculate()).isFalse();
                    assertThat(strikeScore.score()).isEqualTo(20);
                }
        );
    }
}