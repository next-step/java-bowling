package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MissTest {
    @Test
    @DisplayName("해당 프레임이 Miss상태로 종료되었는지 확인한다")
    void checkedFrameStateIsMiss() {
        // given
        Miss miss = new Miss(new Pitching(3), new Pitching(4));

        // when & then
        assertThat(miss.isEnd()).isTrue();
    }

    @Test
    @DisplayName("Miss상태의 symbol을 확인한다")
    void checkedMissSymbol() {
        // given
        Miss miss = new Miss(new Pitching(3), new Pitching(4));

        // when
        String symbol = miss.symbol();

        // then
        assertThat(symbol).isEqualTo("3|4");
    }
}
