package bowling.state.finish;

import bowling.state.State;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LastFrameStateTest {

    @DisplayName("마지막 프레임에서 거터 상태가 되면 프레임이 종료 된다.")
    @Test
    public void gutterTest() {
        State lastFrameState = LastFrameState.init();
        State gutter = lastFrameState.bowl(0).bowl(0);

        Assertions.assertThat(gutter.isFinished()).isTrue();
    }

    @DisplayName("마지막 프레임에서 미스 상태가 되면 프레임이 종료 된다.")
    @Test
    public void missTest() {
        State lastFrameState = LastFrameState.init();
        State miss = lastFrameState.bowl(1).bowl(2);

        Assertions.assertThat(miss.isFinished()).isTrue();
    }

    @DisplayName("마지막 프레임에서 스페어 상태가 되면 프레임이 1회 연장 된다.")
    @Test
    public void spareTest() {
        State lastFrameState = LastFrameState.init();
        State miss = lastFrameState.bowl(1).bowl(9);

        Assertions.assertThat(miss.isFinished()).isFalse();
    }

    @DisplayName("마지막 프레임에서 스트라이크 상태가 되면 프레임이 1회 연장 된다.")
    @Test
    public void strikeTest() {
        State lastFrameState = LastFrameState.init();
        State miss = lastFrameState.bowl(10).bowl(10);

        Assertions.assertThat(miss.isFinished()).isFalse();
    }



}
