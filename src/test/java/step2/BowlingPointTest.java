package step2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step2.domain.BowlingPoint;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class BowlingPointTest {

    @DisplayName("볼링점수 생성 테스트")
    @Test
    void create() {
        BowlingPoint of = BowlingPoint.of(4, 6);
        assertThat(BowlingPoint.of(10)).isEqualTo(new BowlingPoint(10, "X", true));
        assertThat(BowlingPoint.of(9)).isEqualTo(new BowlingPoint(9, "9", true));
        assertThat(BowlingPoint.of(4, 6)).isEqualTo(new BowlingPoint(4, "/", true));
        assertThat(BowlingPoint.of(0)).isEqualTo(new BowlingPoint(0, "-", true));
    }

    @DisplayName("볼링점수 예외 발생 테스트")
    @Test
    void occursBowlingPointException() {
        assertThatIllegalArgumentException()
                .isThrownBy(()->{
                    BowlingPoint of = BowlingPoint.of(11);
                }).withMessage(BowlingPoint.INVALID_PITCHES_COUNT);
    }
}
