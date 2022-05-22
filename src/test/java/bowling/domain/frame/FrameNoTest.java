package bowling.domain.frame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrameNoTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 11})
    void validation(int invalidNo) {
        assertThatThrownBy(() -> FrameNo.of(invalidNo))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void cache() {
        assertThat(FrameNo.of(1)).isSameAs(FrameNo.of(1));
    }

    @Test
    void next() {
        assertThat(FrameNo.of(1).next()).isEqualTo(2);
    }

    @Test
    void next_Exception() {
        FrameNo maxFrameNo = FrameNo.of(10);

        assertThatThrownBy(maxFrameNo::next)
                .isInstanceOf(IllegalStateException.class);
    }
}
