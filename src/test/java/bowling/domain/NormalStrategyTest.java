package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalStrategyTest {

    @Test
    void 프레임종료_판단() {
        NormalStrategy strategy = new NormalStrategy();

        assertThat(strategy.isFinal()).isFalse();
        assertThat(strategy.isFrameEnd(2, Result.MISS)).isTrue();
        assertThat(strategy.isFrameEnd(1, Result.STRIKE)).isTrue();
    }
}
