package bowling.domain.engine.frame;

import bowling.domain.RollResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class UnavailableScoreTest {

    @Test
    @DisplayName("점수 계산 관련 인터페이스를 사용할 수 없다.")
    void cannotUseInterfacesOfUnavailableScore() {
        UnavailableScore score = UnavailableScore.init();
        assertAll(
            () -> assertThatThrownBy(() -> score.add(RollResult.of(10))).isInstanceOf(IllegalStateException.class),
            () -> assertThatThrownBy(score::getValue).isInstanceOf(IllegalStateException.class)
        );
    }

    @Test
    @DisplayName("항상 점수 계산이 완료되지 않은 상태로 남아있다.")
    void alwaysUnableCompleteScore() {
        assertThat(UnavailableScore.init().isCalculationCompleted()).isFalse();
    }

}
