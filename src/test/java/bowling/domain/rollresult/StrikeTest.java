package bowling.domain.rollresult;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StrikeTest {
    public static final Strike STRIKE = Strike.of();

    @Test
    void 스트라이크생성() {
        assertThat(STRIKE.isStrike()).isTrue();
        assertThat(STRIKE.isSpare()).isFalse();
        assertThat(STRIKE.hasNext()).isFalse();
        assertThat(STRIKE.eval().isStrike()).isTrue();
        assertThat(STRIKE.canAccumulate()).isTrue();
    }

    @Test
    void 스트라이크는_한번의추가점수가_가능하다() {
        RollResultType strike = Strike.of().next(3);

        assertThat(strike.canAccumulate()).isFalse();
    }

    @Test
    void 스트라이크의_점수추가는_두번까지가능하다() {
        RollResultType strike = Strike.of().next(3).next(5);
        assertThat(strike.eval().compareTo(18)).isEqualTo(0);
        assertThat(strike.next(10).eval().compareTo(18)).isEqualTo(0);
    }
}
