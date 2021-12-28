package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class IndexTest {
    @DisplayName("max함수는 해당 Index가 마지막인지 boolean을 반환한다.")
    @Test
    void max() {
        assertAll(
                () -> assertThat(FrameIndex.first().max()).isFalse(),
                () -> assertThat(FrameIndex.of(FrameIndex.MAX).max()).isTrue()
        );
    }
}
