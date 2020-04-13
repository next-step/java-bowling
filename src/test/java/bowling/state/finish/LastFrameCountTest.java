package bowling.state.finish;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LastFrameCountTest {

    @DisplayName("마지막 프레임은 0 ~ 3 회까지 시도할 수 있다.")
    @Test
    public void lastFrameCountTest() {
        LastFrameCount count = LastFrameCount.of();

        int initCount = count.getCount();
        assertThat(initCount).isEqualTo(0);

        count.increase();
        count.increase();
        count.increase();

        assertThat(count.getCount()).isEqualTo(3);
        assertThat(count.isMax()).isEqualTo(true);

        assertThatThrownBy(() -> count.increase())
                .isInstanceOf(IllegalArgumentException.class);
    }

}
