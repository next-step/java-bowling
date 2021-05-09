package bowling.domain.state;

import bowling.domain.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        State oneAdd = Strike.of().next(3);
        assertThat(oneAdd.eval()).isEqualTo(Score.of(13, 1));
        assertThat(oneAdd.canAccumulate()).isTrue();
    }

    @Test
    void 스트라이크의_점수추가는_두번까지가능하다() {
        State twoAdd = Strike.of().next(3).next(5);
        assertThat(twoAdd.eval()).isEqualTo(Score.of(18, 0));
        assertThat(twoAdd.canAccumulate()).isFalse();

        assertThatThrownBy(() -> {
            twoAdd.next(3);
        }).isInstanceOf(IllegalStateException.class);
    }
}
