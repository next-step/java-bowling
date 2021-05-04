package bowling.domain.rollresult;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StrikeTest {
    public static final Strike ST1 = Strike.of();

    @Test
    void 스트라이크생성() {
        assertThat(ST1.isStrike()).isTrue();
        assertThat(ST1.isSpare()).isFalse();
        assertThat(ST1.eval()).isEqualTo(10);
    }

    @Test
    void 스트라이크의_점수추가는_두번까지가능하다() {
        RollResultType strike = Strike.of().next(3).next(5);
        assertThat(strike.eval()).isEqualTo(18);
        assertThat(strike.next(10).eval()).isEqualTo(18);
    }
}
