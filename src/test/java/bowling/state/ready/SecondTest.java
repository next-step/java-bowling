package bowling.state.ready;

import bowling.state.State;
import bowling.state.finish.Gutter;
import bowling.state.finish.Miss;
import bowling.state.finish.Spare;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class SecondTest {

    @DisplayName("두번째 시도에 남은 모든 핀을 쓰러트리면 스페어 상태가 된다.")
    @Test
    public void spareTest() {
        State second = Second.of(5);

        State spare = second.bowl(5);

        assertThat(spare).isInstanceOf(Spare.class);
    }

    @DisplayName("두번째 시도까지 쓰러트린 핀의 합이 0개이면 거터 상태가 된다.")
    @Test
    public void gutterTest() {
        State second = Second.of(0);

        State gutter = second.bowl(0);

        assertThat(gutter).isInstanceOf(Gutter.class);
    }

    @DisplayName("두번째 시도까지 쓰러트린 핀의 합이 9이하 이면 미스 상태가 된다.")
    @Test
    public void missTest() {
        State second = Second.of(0);

        State miss = second.bowl(9);

        assertThat(miss).isInstanceOf(Miss.class);
    }

    @DisplayName("프레임이 끝났는지의 상태를 알 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {10, 9, 0})
    public void isDoneTest(int source) {
        State second = Second.of(0);
        State frameState = second.bowl(source);

        assertThat(frameState.isFinished()).isEqualTo(true);
    }

}
