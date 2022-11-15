package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

class FinalStrategyTest {

    @Test
    void 프레임종료_판단() {
        FrameStrategy strategy = new FinalStrategy();

        assertThat(strategy.isFinal()).isTrue();
        assertThat(strategy.isFrameEnd(1, Result.STRIKE)).isFalse();

        assertThat(strategy.isFrameEnd(2, Result.STRIKE)).isFalse();
        assertThat(strategy.isFrameEnd(2, Result.SPARE)).isFalse();
        assertThat(strategy.isFrameEnd(2, Result.MISS)).isTrue();
        assertThat(strategy.isFrameEnd(2, Result.GUTTER)).isTrue();


    }

    @Test
    void 프레임종료_에러() {
        FrameStrategy strategy = new FinalStrategy();
        assertThatIllegalArgumentException().isThrownBy(() -> strategy.isFrameEnd(3, Result.MISS));
        assertThatIllegalArgumentException().isThrownBy(() -> strategy.isFrameEnd(3, Result.GUTTER));
    }
}
