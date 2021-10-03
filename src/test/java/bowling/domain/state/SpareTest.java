package bowling.domain.state;

import bowling.domain.Left;
import bowling.domain.PinCount;
import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class SpareTest {
    @DisplayName("first, second 합이 10이 아닐 경우 miss 상태이므로 IllegalArgumentException 발생")
    @Test
    void create_ThrowsIllegalArgumentException_IfFirstAndSecondInputSumIsNotTen() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new Spare(PinCount.of(5), PinCount.of(3))
        );
    }

    @DisplayName("생성")
    @Test
    void create() {
        State spare = new Spare(PinCount.of(7), PinCount.of(3));
        assertThat(spare.getScore()).isEqualTo(Score.SPARE);
    }

    @DisplayName("이전 스코어가 스페어 일 때 첫 번째 핀을 더한 스코어를 반환")
    @Test
    void calculateBonusScore_ReturnPlusFirst_IfBeforeScoreIsSpare() {
        PinCount first = PinCount.of(5);
        PinCount second = PinCount.of(4);
        State spare = new Spare(first, second);

        Score beforeScore = Score.SPARE;
        Score score = spare.calculateBonusScore(beforeScore);
        assertThat(score).isEqualTo(beforeScore.plusAdditionalPinCount(first));
    }

    @DisplayName("이전 스코어가 스트라이크 일 때 첫 번째 핀과 두 번째 핀을 더한 스코어를 반환")
    @Test
    void calculateBonusScore_ReturnPlusFirstAndSecond_IfBeforeScoreIsStrike() {
        PinCount first = PinCount.of(5);
        PinCount second = PinCount.of(4);
        State spare = new Spare(first, second);

        Score beforeScore = Score.STRIKE;
        Score score = spare.calculateBonusScore(beforeScore);
        assertThat(score).isEqualTo(beforeScore.plusAdditionalPinCount(first)
                .plusAdditionalPinCount(second));
    }

    @DisplayName("이전 스코어 Left가 zero인 경우 IllegalArgumentException 발생")
    @Test
    void calculateBonusScore_ThrowsIllegalArgumentException_IfBeforeScoreLeftIsZero() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new Spare(PinCount.of(5), PinCount.of(5))
                        .calculateBonusScore(new Score(6, Left.ZERO))
        );
    }
}
