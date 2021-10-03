package bowling.domain.state;

import bowling.domain.Left;
import bowling.domain.PinCount;
import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class MissTest {

    @DisplayName("first, second 합이 10일 경우 spare 상태이므로 IllegalArgumentException 발생")
    @Test
    void create_ThrowsIllegalArgumentException_IfFirstAndSecondInputSumIsTen() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new Miss(PinCount.of(5), PinCount.of(5))
        );
    }

    @DisplayName("생성")
    @Test
    void create() {
        State miss = new Miss(PinCount.of(5), PinCount.of(4));
        assertThat(miss.getScore()).isEqualTo(new Score(9, Left.ZERO));
    }

    @DisplayName("이전 스코어가 스페어 일 때 첫 번째 핀을 더한 스코어를 반환")
    @Test
    void calculateBonusScore_ReturnPlusFirst_IfBeforeScoreIsSpare() {
        PinCount first = PinCount.of(5);
        PinCount second = PinCount.of(4);
        State miss = new Miss(first, second);

        Score beforeScore = Score.SPARE;
        Score score = miss.calculateBonusScore(beforeScore);
        assertThat(score).isEqualTo(beforeScore.plusAdditionalPinCount(first));
    }

    @DisplayName("이전 스코어가 스트라이크 일 때 첫 번째 핀과 두 번째 핀을 더한 스코어를 반환")
    @Test
    void calculateBonusScore_ReturnPlusFirstAndSecond_IfBeforeScoreIsStrike() {
        PinCount first = PinCount.of(5);
        PinCount second = PinCount.of(4);
        State miss = new Miss(first, second);

        Score beforeScore = Score.STRIKE;
        Score score = miss.calculateBonusScore(beforeScore);
        assertThat(score).isEqualTo(beforeScore.plusAdditionalPinCount(first)
                .plusAdditionalPinCount(second));
    }

    @DisplayName("이전 스코어 Left가 zero인 경우 IllegalArgumentException 발생")
    @Test
    void calculateBonusScore_ThrowsIllegalArgumentException_IfBeforeScoreLeftIsZero() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new Miss(PinCount.of(5), PinCount.of(4))
                        .calculateBonusScore(new Score(6, Left.ZERO))
        );
    }
}