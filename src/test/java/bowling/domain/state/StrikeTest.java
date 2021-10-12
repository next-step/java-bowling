package bowling.domain.state;

import bowling.domain.Left;
import bowling.domain.PinCount;
import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class StrikeTest {
    @DisplayName("이전 스코어가 스페어 일 때 첫 번째 핀을 더한 스코어를 반환")
    @Test
    void calculateBonusScore_ReturnPlusMaxPins_IfBeforeScoreIsSpare() {
        State strike = new Strike();
        Score beforeScore = Score.SPARE;
        Score score = strike.calculateBonusScore(beforeScore);
        assertThat(score).isEqualTo(beforeScore.plusAdditionalPinCount(PinCount.MAX_PINS));
    }

    @DisplayName("이전 스코어가 스트라이크 일 때 첫 번째 핀과 두 번째 핀을 더한 스코어를 반환")
    @Test
    void calculateBonusScore_ReturnPlusDoubleMaxPins_IfBeforeScoreIsStrike() {
        State strike = new Strike();
        Score beforeScore = Score.STRIKE;
        Score score = strike.calculateBonusScore(beforeScore);

        strike = new Strike();
        score = strike.calculateBonusScore(score);
        assertThat(score).isEqualTo(beforeScore.plusAdditionalPinCount(PinCount.MAX_PINS)
                .plusAdditionalPinCount(PinCount.MAX_PINS));
    }

    @DisplayName("이전 스코어 Left가 zero인 경우 IllegalArgumentException 발생")
    @Test
    void calculateBonusScore_ThrowsIllegalArgumentException_IfBeforeScoreLeftIsZero() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new Strike()
                        .calculateBonusScore(new Score(6, Left.ZERO))
        );
    }
}
