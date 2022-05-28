package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {
    @Test
    @DisplayName("볼링 상태에 대해서 확인한다")
    void checkedPitchingState() {
        // given
        State ready = Ready.create();
        Pitching pitching = new Pitching(3);

        // when
        State result = ready.bowl(pitching);

        // then
        assertThat(result).isInstanceOf(FirstPitching.class);
    }

    @Test
    @DisplayName("볼링이 종료상태가 아닌것을 확인한다")
    void checkedReadyStateIsNotEnd() {
        // given
        State ready = Ready.create();

        // when
        boolean result = ready.isEnd();

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("진행 상테 에 대한 symbol을 확인한다")
    void checkedReadySymbol() {
        // given
        State ready = Ready.create();

        // when
        String symbol = ready.symbol();

        // then
        assertThat(symbol).isBlank();
    }
}
