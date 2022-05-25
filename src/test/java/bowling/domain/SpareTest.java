package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpareTest {
    @Test
    @DisplayName("스페어처리를 통해 현재 프레임이 종료되었는지 확인한다")
    void checkedCurrentFrameFinishForSpareState() {
        // given
        Spare spare = new Spare(new Pitching(5), new Pitching(5));

        // when & then
        assertThat(spare.isEnd()).isTrue();
    }

    @Test
    @DisplayName("스페어처리 상태가 아닌경우, 예외처리를 한다.")
    void exceptionNotSpareState() {
        // when & then
        assertThatThrownBy(() -> new Spare(new Pitching(3), new Pitching(5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("스페어처리의 symbol을 확인한다")
    void checkedSpareStateSymbol() {
        // given
        Spare spare = new Spare(new Pitching(5), new Pitching(5));

        // when
        String symbol = spare.symbol();

        // then
        assertThat(symbol).isEqualTo("5|/");
    }
}
