package bowling.domain.attempt;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FinalAttemptNumberTest {

    @Test
    void 생성_테스트() {
        assertThat(FinalAttemptNumber.of(3)).isEqualTo(FinalAttemptNumber.of(3));
        assertThat(FinalAttemptNumber.first()).isEqualTo(FinalAttemptNumber.of(0));
    }

    @Test
    void 시도_횟수_유효성_테스트() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> FinalAttemptNumber.of(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> FinalAttemptNumber.of(4));
        Assertions.assertThrows(IllegalArgumentException.class, () -> FinalAttemptNumber.of(5));
    }

    @Test
    void 시도_횟수_테스트() {
        assertThat(FinalAttemptNumber.of(3).isLastAttempt()).isTrue();
        assertThat(FinalAttemptNumber.of(2).isLastAttempt()).isTrue();
        assertThat(FinalAttemptNumber.of(1).isLastAttempt()).isFalse();
        assertThat(FinalAttemptNumber.of(0).isFirstAttempt()).isTrue();
        assertThat(FinalAttemptNumber.of(1).isFirstAttempt()).isFalse();
    }

    @Test
    void 증가_테스트() {
        // given
        AttemptNumber attemptNumber = FinalAttemptNumber.of(1);
        // when & then
        assertThat(attemptNumber.increase()).isEqualTo(2);
    }

    @Test
    void 보너스_증가_테스트() {
        // given
        AttemptNumber attemptNumber = FinalAttemptNumber.of(2);
        AttemptNumber attemptNumber2 = FinalAttemptNumber.of(3);
        // when & then
        assertThat(attemptNumber.increase()).isEqualTo(3);
        Assertions.assertThrows(IllegalStateException.class, () -> attemptNumber2.increase());
    }

    @Test
    void 보너스_시도_횟수_테스트() {
        assertThat(FinalAttemptNumber.of(1).isBonusAttempt()).isFalse();
        assertThat(FinalAttemptNumber.of(2).isBonusAttempt()).isFalse();
        assertThat(FinalAttemptNumber.of(3).isBonusAttempt()).isTrue();
    }

    @Test
    void 두번째_투구_전_확인_테스트() {
        // given
        AttemptNumber attemptNumber = FinalAttemptNumber.of(1);
        AttemptNumber attemptNumber2 = FinalAttemptNumber.of(2);
        AttemptNumber attemptNumber3 = FinalAttemptNumber.of(3);
        // when & then
        assertThat(attemptNumber.isSecondAttempt()).isTrue();
        assertThat(attemptNumber2.isSecondAttempt()).isFalse();
        assertThat(attemptNumber3.isSecondAttempt()).isFalse();
    }
}
