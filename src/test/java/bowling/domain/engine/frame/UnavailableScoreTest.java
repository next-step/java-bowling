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
        UnavailableScore score = UnavailableScore.of(6);
        assertAll(
            () -> assertThatThrownBy(() -> score.add(RollResult.of(10))).isInstanceOf(IllegalStateException.class),
            () -> assertThat(score.getValue()).isEqualTo(6)
        );
    }

    @Test
    @DisplayName("항상 점수 계산이 완료된 상태로 간주한다.")
    void considerItAlwaysCompleteScore() {
        assertThat(UnavailableScore.init().isCalculationCompleted()).isTrue();
    }

    @Test
    @DisplayName("항상 사용할 수는 없는 상태이다.")
    void alwaysBeUnavailable() {
        assertThat(UnavailableScore.init().isUnavailable()).isTrue();
    }

}
