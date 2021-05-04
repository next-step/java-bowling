package bowling.domain.rollresult;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StrikeTest {
    public static final Strike ST1 = Strike.of();

    @Test
    void 스트라이크생성() {
        assertThat(ST1.isStrike()).isTrue();
        assertThat(ST1.isSpare()).isFalse();
        assertThat(ST1.eval().compareTo(10)).isEqualTo(0);
    }

    @Test
    void 스트라이크의_점수추가는_두번까지가능하다() {
        RollResultType strike = Strike.of().next(3).next(5);
        assertThat(strike.eval().compareTo(18)).isEqualTo(0);
        assertThat(strike.next(10).eval().compareTo(18)).isEqualTo(0);
    }
}
