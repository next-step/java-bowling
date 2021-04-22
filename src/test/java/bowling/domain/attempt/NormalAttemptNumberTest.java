package bowling.domain.attempt;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NormalAttemptNumberTest {

    @Test
    void 생성_테스트() {
        assertThat(NormalAttemptNumber.of(1)).isEqualTo(NormalAttemptNumber.of(1));
        assertThat(NormalAttemptNumber.first()).isEqualTo(NormalAttemptNumber.of(0));
    }

    @Test
    void 시도_횟수_유효성_테스트() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> NormalAttemptNumber.of(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> NormalAttemptNumber.of(3));
        Assertions.assertThrows(IllegalArgumentException.class, () -> NormalAttemptNumber.of(5));
    }

    @Test
    void 시도_횟수_테스트() {
        assertThat(NormalAttemptNumber.of(2).isLastAttempt()).isTrue();
        assertThat(NormalAttemptNumber.of(1).isLastAttempt()).isFalse();
        assertThat(NormalAttemptNumber.of(0).isFirstAttempt()).isTrue();
        assertThat(NormalAttemptNumber.of(1).isFirstAttempt()).isFalse();
    }

    @Test
    void 증가_테스트() {
        // given
        AttemptNumber attemptNumber = NormalAttemptNumber.of(1);
        // when & then
        assertThat(attemptNumber.increase()).isEqualTo(2);
    }

    @Test
    void 보너스_증가_테스트() {
        // given
        AttemptNumber attemptNumber = NormalAttemptNumber.of(2);
        // when & then
        Assertions.assertThrows(IllegalStateException.class, () -> attemptNumber.increase());
    }

    @Test
    void 보너스_시도_테스트() {
        // given
        AttemptNumber attemptNumber = NormalAttemptNumber.of(2);
        // when & then
        Assertions.assertThrows(IllegalStateException.class, () -> attemptNumber.isBonusAttempt());
    }

}
