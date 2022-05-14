package bowling.domain.frame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class NormalFrameNoTest {

    @ParameterizedTest(name = "일반 프레임 번호는 1부터 9다.")
    @ValueSource(ints = {0, 10})
    void validation(int no) {
        assertThatThrownBy(() -> NormalFrameNo.of(no))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void cache() {
        assertThat(NormalFrameNo.of(1)).isSameAs(NormalFrameNo.of(1));
        assertThat(NormalFrameNo.of(9)).isSameAs(NormalFrameNo.of(9));
    }

    @Test
    void next() {
        assertThat(NormalFrameNo.of(1).next()).isEqualTo(2);
    }

    @Test
    void next_Exception() {
        assertThatThrownBy(() -> NormalFrameNo.of(9).next())
                .isInstanceOf(IllegalStateException.class);
    }
}

